package com.hieuminh.controller.admin.api;
import com.hieuminh.constant.CustomMessages;
import com.hieuminh.constant.SystemConstant;
import com.hieuminh.dto.ImportProductDTO;
import com.hieuminh.dto.ProductDTO;
import com.hieuminh.dto.TypeDTO;
import com.hieuminh.repository.ProductDao;
import com.hieuminh.service.ImageProductService;
import com.hieuminh.service.ProductService;
import com.hieuminh.service.TypeService;
import com.hieuminh.utils.HistoryUtil;
import com.hieuminh.utils.SecurityUtils;
import com.hieuminh.utils.SessionUtil;
import com.hieuminh.utils.UploadFileUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductAPI {

    private Logger LOGGER = Logger.getLogger(ProductAPI.class);

    @Autowired
    private ProductService productService;
    @Autowired
    private ImageProductService imageProductService;
    @Autowired
    private UploadFileUtils uploadFileUtils;
    @Autowired
    private TypeService typeService;
    @Autowired
    HistoryUtil historyUtil;



    @RequestMapping(value = "/api/admin/product/edit", method = RequestMethod.POST)
    @ResponseBody
    public Boolean addProduct(@RequestBody ProductDTO model, HttpServletRequest request) {

        setTypeId(model);

        try{
            saveImage(model);
            model.setStatus(0);
            model.setView(0);
            productService.SaveProduct(model);
            historyUtil.updateHistoryConverterBill(SecurityUtils.getPrincipal().getUsername(),"Add "+model.getNameProduct()+" "+ CustomMessages.HISTORY_PRODUCT);

        }catch (Exception e){
            LOGGER.error("Have to Error :"+e.getMessage(),e);
            LOGGER.debug("Have to Error :"+e.getMessage(),e);

            return false;

        }
        return true;

    }

    private void setTypeId(ProductDTO model) {
        TypeDTO typeDTO = new TypeDTO();
        typeDTO.setTypeId(model.getTypeId());
        model.setTypeIdEntity(typeDTO);
    }

    @RequestMapping(value = "/api/admin/product/edit/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public Boolean updateProduct(@RequestBody ProductDTO model,@PathVariable("id") Integer id, HttpServletRequest request) {
        setTypeId(model);
        ProductDTO productDTO = productService.findById(id);
        model.setView(productDTO.getView());
        model.setStatus(productDTO.getStatus());
       try{
           if(model.getThumbnailBase64()!=null){
               saveImage(model);
           }else{
               model.setImage(productDTO.getImage());
           }


           productService.updateProduct(model);
           historyUtil.updateHistoryConverterBill(SecurityUtils.getPrincipal().getUsername(),"Edit ID:"+model.getIdProduct()+" "+ CustomMessages.HISTORY_PRODUCT);

       }catch (Exception e){
           LOGGER.error(e.getMessage(),e);
           LOGGER.debug("Have to Error :"+e.getMessage(),e);
           return false;
       }
       return true;

    }
    @RequestMapping(value = "/api/admin/product/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public Boolean deleteProduct(@RequestBody List<Integer> idList) {
        try{
            for(Integer item:idList){
                imageProductService.deleteImageList("idImageProductEntity.idProduct",item);

            }
            Integer resultProduct = productService.deleteProduct(idList);
            historyUtil.updateHistoryConverterBill(SecurityUtils.getPrincipal().getUsername(),"Delete "+idList.size() +" "+ CustomMessages.HISTORY_PRODUCT);

        }catch (Exception e){
            LOGGER.error(e.getMessage(),e);
            return false;
        }

        return true;

    }
    private void saveImage(ProductDTO model) {
        if (model.getThumbnailBase64() != null) {
            byte[] decodedBase64 = DatatypeConverter.parseBase64Binary(model.getThumbnailBase64().substring(model.getThumbnailBase64().indexOf(",") + 1));
            String path = SystemConstant.PRODUCT_PROFILE + "/" + model.getImage();
            uploadFileUtils.writeOrUpdate(path, decodedBase64);
            model.setImage(path);
        }

    }

    @RequestMapping(value = "/api/admin/product/importExcel", method = RequestMethod.GET)
    @ResponseBody
    public Boolean importExcel(HttpServletRequest request) {
        List<ImportProductDTO> importProductDTOS = (List<ImportProductDTO>) SessionUtil.getInstance().getValue(request,"listImport");
        if(importProductDTOS.size()>0){
            try{
                for(ImportProductDTO item: importProductDTOS){
                   if(item.isValid()){
                       TypeDTO typeDTO = typeService.findEqualUnique("typeName",item.getTypeProduct().toUpperCase().toString());
                       ProductDTO productDTO = setProduct(item,typeDTO);

                       productService.SaveProduct(productDTO);
                   }
                }
                historyUtil.updateHistoryConverterBill(SecurityUtils.getPrincipal().getUsername(),"Import "+importProductDTOS.size() +" "+ CustomMessages.HISTORY_PRODUCT);

                SessionUtil.getInstance().remove(request,"listImport");

            }catch (Exception e){
                LOGGER.error(e.getMessage(),e);
                return false;

            }
        }else{
            return false;
        }


        return true;

    }

    private ProductDTO setProduct(ImportProductDTO item, TypeDTO typeDTO) {
        ProductDTO productDTO=new ProductDTO();
        productDTO.setNameProduct(item.getNameProduct());
        productDTO.setCost(Integer.parseInt(item.getCost()));
        productDTO.setInformation(item.getInformation());
        productDTO.setTypeIdEntity(typeDTO);
        productDTO.setImage(item.getImage());
        productDTO.setView(0);
        productDTO.setStatus(0);

        return  productDTO;
    }

}
