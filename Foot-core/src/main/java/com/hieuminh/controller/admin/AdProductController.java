package com.hieuminh.controller.admin;


import com.hieuminh.constant.SystemConstant;
import com.hieuminh.dto.ImportProductDTO;
import com.hieuminh.dto.ProductDTO;
import com.hieuminh.service.ProductService;
import com.hieuminh.service.TypeService;
import com.hieuminh.utils.*;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

@Controller
public class AdProductController {
    @Autowired
    private  MessageUtils messageUtils;

    @Autowired
    private ProductService productService;


    @Autowired
    private TypeService typeService;

    @Autowired
    private UploadFileUtils uploadFileUtils;

    @RequestMapping(value = "/admin/product/list", method = RequestMethod.GET)
    public ModelAndView getProduct(@ModelAttribute(SystemConstant.MODEL) ProductDTO model, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/product/list");
        List<ImportProductDTO> list = (List<ImportProductDTO>) SessionUtil.getInstance().getValue(request,"listImport");
        if(list!=null){
            SessionUtil.getInstance().remove(request,"listImport");
        }
        excuteSearchBill(request, model);
        mav.addObject(SystemConstant.MODEL, model);
        initMessageResponse(mav, request);
        return mav;
    }

    @RequestMapping(value = "/admin/product/edit", method = RequestMethod.GET)
    public ModelAndView addProduct(@ModelAttribute(SystemConstant.MODEL) ProductDTO model, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/product/edit");
        model.setTypeDTOList(typeService.findAll());
        mav.addObject(SystemConstant.MODEL, model);

        return mav;
    }
    @RequestMapping(value = "/admin/product/edit/{id}", method = RequestMethod.GET)
    public ModelAndView updateProduct(@ModelAttribute(SystemConstant.MODEL) ProductDTO model, @PathVariable("id") Integer id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/product/edit");
        model = productService.findById(id);
        model.setTypeDTOList(typeService.findAll());
        mav.addObject(SystemConstant.MODEL, model);

        return mav;
    }

    @RequestMapping(value = "/admin/product/readExcel", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView readExcel(@ModelAttribute(SystemConstant.MODEL) ProductDTO model,@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        ModelAndView mav = new ModelAndView("admin/product/import");

        if(!file.isEmpty()){
            byte[] bytes = file.getBytes();
            String name=file.getOriginalFilename();
            saveExcel(name,bytes);
            String fileLocation =  SystemConstant.BASE_DIR+ "/" +SystemConstant.PRODUCT_EXCEL +"/" + name;

            List<ImportProductDTO>  excelValue = returnValueFromExcel(name,fileLocation);
            validateData(excelValue);
            model.setTotalItems(excelValue.size());
            model.setMaxPageItems(excelValue.size());
            model.setImportProductDTOS(excelValue);
            SessionUtil.getInstance().putValue(request,"listImport",excelValue);
            mav.addObject(SystemConstant.MODEL, model);
        }

        return mav;
    }



    private void saveExcel(String name,byte[] bytes) {
         String path = SystemConstant.PRODUCT_EXCEL + "/" + name;
         uploadFileUtils.writeOrUpdate(path, bytes);
    }


    @RequestMapping(value = "/admin/product/import", method = RequestMethod.GET)
    public String redirect(){
       return "admin/product/import";
    }


    private void excuteSearchBill(HttpServletRequest request, ProductDTO model) {
        Map<String, Object> mapProperty = buildMapProperties(model);
        DisplayTagUtils.initSearchBean(request, model);
        Object[] objects = productService.findByProberty(mapProperty, model.getSortExpression(), model.getSortDirection(), model.getFirstItem(), model.getMaxPageItems());
        model.setListResult((List<ProductDTO>) objects[1]);
        model.setTotalItems(Integer.parseInt(objects[0].toString()));
    }

    private Map<String, Object> buildMapProperties(ProductDTO model) {
        Map<String, Object> properties = new HashMap<String, Object>();

        if (StringUtils.isNotBlank(model.getNameProduct()) && model.getNameProduct() != null) {
            properties.put("nameProduct", model.getNameProduct());
        }
        return properties;
    }

    private void initMessageResponse(ModelAndView mav, HttpServletRequest request) {
        String message = request.getParameter("message");
        if (message != null && StringUtils.isNotEmpty(message)) {
            Map<String, String> messageMap = messageUtils.getMessageResponse(message);
            mav.addObject(SystemConstant.ALERT, messageMap.get(SystemConstant.ALERT));
            mav.addObject(SystemConstant.MESSAGE_RESPONSE, messageMap.get(SystemConstant.MESSAGE_RESPONSE));
        }
    }



    private List<ImportProductDTO> returnValueFromExcel(String fileName, String fileLocation) throws IOException {
        Workbook workbook = ExcelPoiUtil.getWordBook(fileName,fileLocation);
        Sheet sheet = workbook.getSheetAt(0);
        List<ImportProductDTO>  excelValue = new ArrayList<ImportProductDTO>();
        for (int i=1;i<=sheet.getLastRowNum();i++) {
            Row row = sheet.getRow(i);
            ImportProductDTO importProductDTO = readDataFormExcell(row);
            excelValue.add(importProductDTO);
        }
        return excelValue;
    }
    private ImportProductDTO readDataFormExcell(Row row) {
        ImportProductDTO importProductDTO = new ImportProductDTO();
        importProductDTO.setNameProduct(ExcelPoiUtil.getCellValue(row.getCell(0)));
        importProductDTO.setCost(ExcelPoiUtil.getCellValue(row.getCell(1)));
        importProductDTO.setInformation(ExcelPoiUtil.getCellValue(row.getCell(2)));
        importProductDTO.setTypeProduct(ExcelPoiUtil.getCellValue(row.getCell(3)));
        importProductDTO.setImage(ExcelPoiUtil.getCellValue(row.getCell(4)));

        return  importProductDTO;
    }
    private void validateData(List<ImportProductDTO> excelValue) {
        Set<String> stringSet = new HashSet<String>();

        for (ImportProductDTO item:excelValue){
            checkRequireField(item);
            validateDuplicate(item,stringSet);

        }
          productService.ValidateImportProduct(excelValue);
    }

    private void validateDuplicate(ImportProductDTO item, Set<String> stringSet) {
        String message =item.getError();

        if (!stringSet.contains(item.getNameProduct())){
            stringSet.add(item.getNameProduct());
        }else{
            if(item.isValid()) {
                message+="<br/>";
                message += "duplicate name product";
            }
        }
        if(StringUtils.isNotBlank(message)){
            item.setValid(false);
            item.setError(message);
        }
    }


    private void checkRequireField(ImportProductDTO item) {
        String message = "";
        if(StringUtils.isBlank(item.getNameProduct())){
            message+="<br/>";
            message+="Not name Product";
        }
        if(StringUtils.isBlank(item.getCost())){
            message+="<br/>";
            message+="not cost";
        }
        if(StringUtils.isBlank(item.getInformation())){
            message+="<br/>";
            message+="not information";
        }
        if(StringUtils.isBlank(item.getTypeProduct())){
            message+="<br/>";
            message+="not type Product";
        }
        if(StringUtils.isNotBlank(message)){
            item.setValid(false);
        }
        item.setError(message);
    }

}
