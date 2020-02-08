package com.hieuminh.controller.admin.api;


 import com.hieuminh.constant.CustomMessages;
 import com.hieuminh.constant.SystemConstant;
 import com.hieuminh.dto.ImageProductDTO;
 import com.hieuminh.dto.ProductDTO;
 import com.hieuminh.service.ImageProductService;
 import com.hieuminh.service.ProductService;
 import com.hieuminh.utils.HistoryUtil;
 import com.hieuminh.utils.SecurityUtils;
 import com.hieuminh.utils.UploadFileUtils;
 import org.apache.log4j.Logger;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.web.bind.annotation.*;

 import javax.xml.bind.DatatypeConverter;
 import java.util.List;

@Controller
public class ImageProductAPI {

    private Logger LOGGER = Logger.getLogger(ImageProductAPI.class);

    @Autowired
    private ImageProductService imageProductService;

    @Autowired
    private ProductService productService;

    @Autowired
    private HistoryUtil historyUtil;

    @Autowired
    private UploadFileUtils uploadFileUtils;

    @RequestMapping(value = "/api/admin/imageProduct/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public Boolean deleteProduct(@RequestBody List<Integer> idList) {


        Integer result=imageProductService.deleteProduct(idList);
        historyUtil.updateHistoryConverterBill(SecurityUtils.getPrincipal().getUsername(),"Delete "+idList.size()+" "+ CustomMessages.HISTORY_IMAGE_PRODUCT);

        if (result != idList.size()) {
            LOGGER.debug("Delte Fail........");
            return false;

        }
        return true;


    }

    @RequestMapping(value = "api/admin/imageProduct/add/{id}", method = RequestMethod.POST)
    @ResponseBody
    public boolean addProduct(@RequestBody List<String> images , @PathVariable("id") Integer id) {

        if(images.size()>0){
            try  {
               /* for (Map<String, String> file : listFiles) {*/
                    for (int i=0;i<images.size()-1;i+=2) {
                        ImageProductDTO imageProductDTO = new ImageProductDTO();
                        ProductDTO productDTO = productService.findById(id);
                        productDTO.setIdProduct(id);
                        imageProductDTO.setIdImageProductEntity(productDTO);
                        saveImage(images.get(i),images.get(i+1), imageProductDTO);
                        imageProductService.saveImageProduct(imageProductDTO);

                    }
                historyUtil.updateHistoryConverterBill(SecurityUtils.getPrincipal().getUsername(),"Add "+images.size()+" "+ CustomMessages.HISTORY_IMAGE_PRODUCT);

                /* }*/
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error(e.getMessage(),e);
                return false;
            }

        }else{
            return false;
        }

        return true;
    }
    private void saveImage(String key,String value,ImageProductDTO imageProductDTO) {

            byte[] decodedBase64 = DatatypeConverter.parseBase64Binary(value.substring(value.indexOf(",") + 1));
            String path = SystemConstant.PRODUCT_PROFILE + "/" + key;
            //String formatName =  productDTO.getImage().substring( productDTO.getImage().lastIndexOf('.') + 1);
            uploadFileUtils.writeOrUpdate(path, decodedBase64);
            imageProductDTO.setImages(path);

    }

}
