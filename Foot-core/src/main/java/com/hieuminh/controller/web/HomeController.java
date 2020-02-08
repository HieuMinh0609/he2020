package com.hieuminh.controller.web;

import com.hieuminh.constant.SystemConstant;
import com.hieuminh.dto.*;
import com.hieuminh.service.*;
import com.hieuminh.service.impl.UserServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {
    @Autowired
    ProductService productService;

    @Autowired
    SaleService saleService;

    @Autowired
    DetailSaleService detailSaleService;

    @Autowired
    CommentService commentService;


    @RequestMapping(value="/home", method = RequestMethod.GET)
    public ModelAndView getHome(@ModelAttribute(SystemConstant.MODEL) ProductOpenDTO model, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("web/home");

        List<SaleDTO> list = saleService.getSaleNow();
        mav.addObject(SystemConstant.MODEL,list);
        return mav;
    }

    @RequestMapping(value="/find", method = RequestMethod.GET)
    public ModelAndView findList(@ModelAttribute(SystemConstant.MODEL) ProductOpenDTO model, @RequestParam("nameProduct") String name,HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("web/product/find");

        Map<String,Object> map = new HashMap<>();
        if (StringUtils.isNotBlank(name) && name != null) {
            map.put("nameProduct", name);
        }
        List<ProductDTO> productDTOS = new ArrayList<>();
        List<ProductOpenDTO> productOpenDTOList = new ArrayList<>();



        Object[] objects = productService.findByProberty(map,null,null,model.getFirstItem(),model.getMaxPageItems());
        productDTOS = (List<ProductDTO>) objects[1];
        List<DetailSaleDTO> detailSaleDTOs= detailSaleService.getIdProAndDown();

        setProductOpenDTO(productDTOS,detailSaleDTOs,productOpenDTOList);





        mav.addObject(SystemConstant.MODEL,productOpenDTOList);

        return mav;
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
