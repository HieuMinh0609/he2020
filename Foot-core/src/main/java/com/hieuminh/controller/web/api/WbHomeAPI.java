package com.hieuminh.controller.web.api;

import com.hieuminh.dto.*;
import com.hieuminh.service.CommentService;
import com.hieuminh.service.DetailSaleService;
import com.hieuminh.service.ProductService;
import com.hieuminh.service.TypeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class WbHomeAPI {

    @Autowired
    ProductService productService;
    @Autowired
    DetailSaleService detailSaleService;
    @Autowired
    TypeService typeService;
    @Autowired
    CommentService commentService;




    @RequestMapping(value = "api/wBHome/loadMenuPage", method = RequestMethod.POST)
    @ResponseBody
    public List<ProductOpenDTO> showMenuProduct(@RequestBody ProductOpenDTO model, HttpServletRequest request) {
        Map<String,Object> map = new HashMap<>();
        List<ProductDTO> productDTOS = new ArrayList<>();
        List<ProductOpenDTO> productOpenDTOList = new ArrayList<>();

        Integer maxPage = model.getMaxPageItems();
        Integer setFirstItem=((model.getPage() - 1) * model.getMaxPageItems());

        Object[] objects = productService.findByProberty(map,"view","2",setFirstItem,maxPage);
        productDTOS = (List<ProductDTO>) objects[1];

        List<DetailSaleDTO> detailSaleDTOs= detailSaleService.getIdProAndDown();
        setProductOpenDTO(productDTOS,detailSaleDTOs,productOpenDTOList);
        setAmountComment(productOpenDTOList);

        return productOpenDTOList;
    }
    private void setAmountComment(List<ProductOpenDTO> productOpenDTOList) {
        for(ProductOpenDTO openDTO:productOpenDTOList){
            Integer count=commentService.getCountComment(openDTO.getIdProduct().toString());
            openDTO.setComment(count);
        }

    }

    @RequestMapping(value = "/api/wBHome/loadType", method = RequestMethod.GET)
    @ResponseBody
    public List<TypeDTO> LoadType(HttpServletRequest request) {
        List<TypeDTO> typeDTOList = new ArrayList<>();
        typeDTOList = typeService.findAll();
        return typeDTOList;
    }


    @RequestMapping(value = "/api/wBHome/homepage", method = RequestMethod.POST)
    @ResponseBody
    public List<ProductOpenDTO> showProduct(@RequestBody ProductOpenDTO model ,HttpServletRequest request) {
        Map<String,Object> map = new HashMap<>();

        List<ProductDTO> productDTOS = new ArrayList<>();
        List<ProductOpenDTO> productOpenDTOList = new ArrayList<>();


         Integer maxPage = model.getMaxPageItems();
        Integer setFirstItem=((model.getPage() - 1) * model.getMaxPageItems());

        Object[] objects = productService.findByProberty(map,null,null,setFirstItem,maxPage);
        productDTOS = (List<ProductDTO>) objects[1];
        List<DetailSaleDTO> detailSaleDTOs= detailSaleService.getIdProAndDown();

        setProductOpenDTO(productDTOS,detailSaleDTOs,productOpenDTOList);







        return productOpenDTOList;

    }

    private void setProductOpenDTO(List<ProductDTO> productDTOS, List<DetailSaleDTO> detailSaleDTOs, List<ProductOpenDTO> productOpenDTOList) {
        for (ProductDTO productDTO : productDTOS) {
            ProductOpenDTO model = new ProductOpenDTO();

            model.setDownpercen(0);
            model.setIdProduct(productDTO.getIdProduct());
            model.setCost(productDTO.getCost());
            model.setImage(productDTO.getImage());
            model.setInformation(productDTO.getInformation());
            model.setView(productDTO.getView());
            model.setNameProduct(productDTO.getNameProduct());
            model.setTypeIdEntity(productDTO.getTypeIdEntity());
            model.setStatus(productDTO.getStatus());

            productOpenDTOList.add(model);
        }
        for (ProductOpenDTO productOpenDTO : productOpenDTOList) {
            for (DetailSaleDTO detailSaleDTO : detailSaleDTOs) {
                if (productOpenDTO.getIdProduct() == detailSaleDTO.getIdProductSaleEntity().getIdProduct()) {
                    productOpenDTO.setDownpercen(detailSaleDTO.getDownpercen());
                }
            }
        }
    }


}
