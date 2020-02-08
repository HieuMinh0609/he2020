package com.hieuminh.controller.web;


import com.hieuminh.constant.SystemConstant;
import com.hieuminh.dto.DetailSaleDTO;
import com.hieuminh.dto.ProductDTO;
import com.hieuminh.dto.ProductOpenDTO;
import com.hieuminh.dto.SaleDTO;
import com.hieuminh.service.DetailSaleService;
import com.hieuminh.service.ProductService;
import com.hieuminh.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TypeController {
    @Autowired
    TypeService typeService;

    @Autowired
    ProductService productService;

    @Autowired
    DetailSaleService detailSaleService;

    @RequestMapping(value="/type-food-{id_food}-page-{id}", method = RequestMethod.GET)
    public ModelAndView showFlastFood(@ModelAttribute(SystemConstant.MODEL) ProductOpenDTO model, @PathVariable("id") Integer id, @PathVariable("id_food") Integer id_food, HttpServletRequest request) {
            ModelAndView mav = new ModelAndView("web/typeproduct/type");

            Map<String,Object> map = new HashMap<>();
            map.put("typeId",id_food);

            List<ProductOpenDTO> productOpenDTOList = new ArrayList<>();

            List<ProductDTO> productDTOS=  getFindProductPage(id,model,map);
            List<DetailSaleDTO> detailSaleDTOs= detailSaleService.getIdProAndDown();

            setProductOpenDTO(productDTOS,detailSaleDTOs,productOpenDTOList);
            mav.addObject(SystemConstant.MODEL,productOpenDTOList);
            mav.addObject(SystemConstant.NUMBER_PAGE,id);
            return mav;
    }




    @RequestMapping(value="/type-sale-page-{id}", method = RequestMethod.GET)
    public ModelAndView showSale(@ModelAttribute(SystemConstant.MODEL) ProductOpenDTO model, @PathVariable("id") Integer id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("web/typeproduct/type");
        Map<String,Object> map = new HashMap<>();

        Integer maxPage = model.getMaxPageItems();
        Integer setFirstItem=(( id - 1) * model.getMaxPageItems());


        List<ProductOpenDTO> productOpenDTOList = new ArrayList<>();
        List<ProductOpenDTO> saleDTO = new ArrayList<>();

        List<DetailSaleDTO> detailSaleDTOs= detailSaleService.getIdProAndDownForSale(maxPage,setFirstItem);

        List<ProductDTO> productDTOS =  getFindSalePage(detailSaleDTOs);

        setProductOpenDTO(productDTOS,detailSaleDTOs,productOpenDTOList);

        for(ProductOpenDTO productOpenDTO:productOpenDTOList){
            if (productOpenDTO.getDownpercen()!=0){
                saleDTO.add(productOpenDTO);
            }
        }

        mav.addObject(SystemConstant.MODEL,saleDTO);
        mav.addObject(SystemConstant.NUMBER_PAGE,id);
        return mav;


    }

    private List<ProductDTO> getFindSalePage(List<DetailSaleDTO> detailSaleDTOs ) {
        List<ProductDTO> productDTOS = new ArrayList<>();
       for (DetailSaleDTO detais : detailSaleDTOs){
           ProductDTO productDTO = productService.findById(detais.getIdProductSaleEntity().getIdProduct());
           productDTOS.add(productDTO);
       }
       return productDTOS;
    }




    private List<ProductDTO> getFindProductPage(Integer id,ProductOpenDTO model,Map map) {

        Integer maxPage = model.getMaxPageItems();
        Integer setFirstItem=(( id - 1) * model.getMaxPageItems());
        Object[] objects = productService.findByProberty(map,null,null,setFirstItem,maxPage);
        return   (List<ProductDTO>) objects[1];
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
