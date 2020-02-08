package com.hieuminh.controller.admin.api;

import com.hieuminh.constant.CustomMessages;
import com.hieuminh.constant.SystemConstant;
import com.hieuminh.dto.DetailSaleDTO;
import com.hieuminh.dto.ProductDTO;
import com.hieuminh.dto.SaleDTO;
import com.hieuminh.service.DetailSaleService;
import com.hieuminh.service.ProductService;
import com.hieuminh.service.SaleService;
import com.hieuminh.utils.HistoryUtil;
import com.hieuminh.utils.SecurityUtils;
import com.hieuminh.utils.UploadFileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
public class SaleAPI {
    private Logger LOGGER = Logger.getLogger(SaleAPI.class);

    @Autowired
    private SaleService saleService;
    @Autowired
    private ProductService productService;

    @Autowired
    private DetailSaleService detailSaleService;

    @Autowired
    private UploadFileUtils uploadFileUtils;

    @Autowired
    private HistoryUtil historyUtil;



    @RequestMapping(value = "/api/admin/sale/", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView showAddSale(@ModelAttribute(SystemConstant.MODEL) SaleDTO model, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/admin/sale/edit");
        mav.addObject(SystemConstant.MODEL, model);
        return mav;
    }

    @RequestMapping(value = "/api/admin/sale/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView showEditSale(@ModelAttribute(SystemConstant.MODEL) SaleDTO model, @PathVariable("id") Integer id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/admin/sale/edit");
        model = saleService.findById(id);

        mav.addObject(SystemConstant.MODEL, model);
        return mav;
    }

    @RequestMapping(value = "/api/admin/sale/loadProduct/{id}", method = RequestMethod.GET)
    public @ResponseBody  ProductDTO showProductSale(@PathVariable("id") Integer id, HttpServletRequest request) {
        ProductDTO model = new ProductDTO();
        try{

            model =productService.findById(id);
            if(detailSaleService.checkInSale(id)==id){
              model.setMessage("false");
            }else{
                model.setMessage("true");
            }
        }catch (Exception e){
            return model;
        }
       return model;

    }


    @RequestMapping(value = "/api/admin/sale/edit/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public Boolean editSale(@RequestBody SaleDTO model,@PathVariable("id") Integer id, HttpServletRequest request) {
        SaleDTO saleDTO = saleService.findById(id);

        try{
            if(model.getThumbnailBase64()!=null){
                saveImage(model);
            }else{
                model.setImage(saleDTO.getImage());
            }

            setTimeforSale(model);
            saleService.updateSale(model);

            historyUtil.updateHistoryConverterBill(SecurityUtils.getPrincipal().getUsername(),"Edit ID:"+model.getIdSale()+" "+ CustomMessages.HISTORY_SALE);

        }catch (Exception e){
            LOGGER.error(e.getMessage(),e);
            return false;
        }

        return true;
    }

    @RequestMapping(value = "/api/admin/sale/addDetail/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Boolean addDetailSale(@RequestBody Map<Object,String>  model,@PathVariable("id") Integer id, HttpServletRequest request) {
        DetailSaleDTO detailSaleDTO=new DetailSaleDTO();
        Integer validete=detailSaleService.valideteDetailSale(model.get("idSale"),model.get("idProduct"));
        if(validete==0){
            try{

                detailSaleDTO.setDownpercen(Integer.parseInt(model.get("downpercen")));
                detailSaleDTO.setIdProductSaleEntity(productService.findById(Integer.parseInt(model.get("idProduct"))));
                detailSaleDTO.setIdSaleEntity(saleService.findById(Integer.parseInt(model.get("idSale"))));


                detailSaleService.saveDetailSale(detailSaleDTO);

                historyUtil.updateHistoryConverterBill(SecurityUtils.getPrincipal().getUsername(),"Add ID_Sale:"+model.get("idSale")+" "+ CustomMessages.HISTORY_SALE_DETAIL);


            }catch (Exception e){
                LOGGER.error(e.getMessage(),e);
                return false;
            }

        }else{
            return false;
        }
        return true;
    }

    @RequestMapping(value = "/api/admin/sale/deleteDetail", method = RequestMethod.DELETE)
    @ResponseBody
    public Boolean deleteDetailSale(@RequestBody List<Integer> idList) {
        try{
                detailSaleService.deleteDetailById(idList);

            historyUtil.updateHistoryConverterBill(SecurityUtils.getPrincipal().getUsername(),"Delete :"+idList.size()+" "+ CustomMessages.HISTORY_SALE_DETAIL);

        }catch (Exception e){
            LOGGER.error(e.getMessage(),e);
            return false;
        }

        return true;

    }




    @RequestMapping(value = "/api/admin/sale/edit", method = RequestMethod.POST)
    @ResponseBody
    public Boolean addSale(@RequestBody SaleDTO model, HttpServletRequest request) {

        try{
            saveImage(model);
            setTimeforSale(model);
            saleService.saveSale(model);

            historyUtil.updateHistoryConverterBill(SecurityUtils.getPrincipal().getUsername(),"Add event:"+model.getName()+" "+ CustomMessages.HISTORY_SALE);

        }catch (Exception e){
            LOGGER.error(e.getMessage(),e);
            return false;
        }

        return true;
    }

    @RequestMapping(value = "/api/admin/sale/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public Boolean deleteProduct(@RequestBody List<Integer> idList) {
        try{
            for(Integer item:idList){
                detailSaleService.deleteSaleDetail("idSaleEntity.idSale",item);
            }
            Integer resultProduct = saleService.deleteSale(idList);
            historyUtil.updateHistoryConverterBill(SecurityUtils.getPrincipal().getUsername(),"Delete: "+idList.size()+" "+ CustomMessages.HISTORY_SALE);

        }catch (Exception e){
            LOGGER.error(e.getMessage(),e);
            return false;
        }


        return true;

    }




    private void setTimeforSale(SaleDTO model) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");

        String startTime =  model.getDateTimeStart().replace("T"," ");
        Date parsedDate1 = dateFormat.parse(startTime);
        model.setTimeStart(new java.sql.Timestamp(parsedDate1.getTime()));

        String EndTime =  model.getDateTimeEnd().replace("T"," ");
        Date parsedDate2 = dateFormat.parse(EndTime);
        model.setTimeEnd(new java.sql.Timestamp(parsedDate2.getTime()));


    }

    private void saveImage(SaleDTO saleDTO) {
        if (saleDTO.getThumbnailBase64() != null) {
            byte[] decodedBase64 = DatatypeConverter.parseBase64Binary(saleDTO.getThumbnailBase64().substring(saleDTO.getThumbnailBase64().indexOf(",") + 1));
            String path = SystemConstant.PRODUCT_PROFILE + "/" + saleDTO.getImage();
            uploadFileUtils.writeOrUpdate(path, decodedBase64);
            saleDTO.setImage(path);
        }
    }

}
