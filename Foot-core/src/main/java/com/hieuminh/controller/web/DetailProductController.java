package com.hieuminh.controller.web;


import com.hieuminh.constant.SystemConstant;
import com.hieuminh.dto.*;
import com.hieuminh.service.*;
import com.hieuminh.utils.MessageUtils;
import com.hieuminh.utils.SecurityUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DetailProductController {

    @Autowired
    ProductService productService;
    @Autowired
    MessageUtils messageUtils;
    @Autowired
    ImageProductService imageProductService;
    @Autowired
    DetailSaleService detailSaleService;
    @Autowired
    CommentService commentService;
    @Autowired
    BillService billService;
    @Autowired
    TypeService typeService;
    @Autowired
    LoginService loginService;



    @RequestMapping(value="/home-order-product-{id}", method = RequestMethod.GET)
    public ModelAndView getOrder(@ModelAttribute(SystemConstant.MODEL) ProductDTO model, @PathVariable("id") Integer id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("web/product/order");

        ProductDTO productDTO = productService.findById(id);
        List<DetailSaleDTO> detailSaleDTOs= detailSaleService.getIdProAndDown();

        if(detailSaleDTOs.size()>0){
            for(DetailSaleDTO d:detailSaleDTOs){
                if(d.getIdProductSaleEntity().getIdProduct()==id){
                    productDTO.setCost(productDTO.getCost()-(productDTO.getCost()*d.getDownpercen())/100);
                }
            }
        }

        Map<String,Object> map = new HashMap<>();
        LoginDTO userLogin= loginService.findByUserName("nameLogin", SecurityUtils.getPrincipal().getUsername());
        UserDTO userDTO = userLogin.getIdLoginUserEntity();

        map.put("status",0);
        map.put("online",0);
        map.put("idUserEntity.idUser",userDTO.getIdUser());
        List<BillDTO> list = (List<BillDTO>) billService.findByPropertyMapNotLike(map,"dateTime","1",null,null)[1];


        mav.addObject(SystemConstant.MODEL,productDTO);
        mav.addObject(SystemConstant.ITEMS,list);
        initMessageResponse(mav, request);

        return mav;
    }

    private void initMessageResponse(ModelAndView mav, HttpServletRequest request) {
        String message = request.getParameter("message");
        if (message != null && StringUtils.isNotEmpty(message)) {
            Map<String, String> messageMap = messageUtils.getMessageResponse(message);
            mav.addObject(SystemConstant.ALERT, messageMap.get(SystemConstant.ALERT));
            mav.addObject(SystemConstant.MESSAGE_RESPONSE, messageMap.get(SystemConstant.MESSAGE_RESPONSE));
        }
    }




    @RequestMapping(value="product-{id}", method = RequestMethod.GET)
    public ModelAndView getHome(@ModelAttribute(SystemConstant.MODEL) ProductOpenDTO model, @PathVariable("id") Integer id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("web/product/detail");


        List<CommentDTO> commentDTOS =  commentService.findByPropertyAs(id);
        List<ImageProductDTO> imageProductDTO= imageProductService.findListById("idImageProductEntity.idProduct",id);

        ProductDTO productDTO = productService.findById(id);
        List<DetailSaleDTO> detailSaleDTOs= detailSaleService.getIdProAndDown();
        SetProductOpenDTO(productDTO,model,detailSaleDTOs,imageProductDTO,commentDTOS);

        mav.addObject(SystemConstant.MODEL,model);
        return mav;
    }

    private void SetProductOpenDTO(ProductDTO productDTO, ProductOpenDTO model, List<DetailSaleDTO> detailSaleDTOs,List<ImageProductDTO> imageProductDTOS,
    List<CommentDTO> commentDTOS) {
        Integer sumStar=0;
        DetailSaleDTO detailSaleDTO = new DetailSaleDTO();
        Integer count=commentService.getCountComment(productDTO.getIdProduct().toString());


        Integer sumRating=0;
        for(DetailSaleDTO dto:detailSaleDTOs){
            if(dto.getIdProductSaleEntity().getIdProduct() == productDTO.getIdProduct()){
                detailSaleDTO = dto;
            }
        }

        if(commentDTOS.size()>0){
            for(CommentDTO item:commentDTOS){
                sumRating+=item.getRate();
            }
             sumStar= (sumRating/(commentDTOS.size()));
        }else{
            sumStar=0;
        }


       if(detailSaleDTO.getDownpercen()==null){
           model.setDownpercen(0);
       }else{
           model.setDownpercen(detailSaleDTO.getDownpercen());
       }
        model.setIdProduct(productDTO.getIdProduct());
        model.setCost(productDTO.getCost());
        model.setImage(productDTO.getImage());
        model.setInformation(productDTO.getInformation());
        model.setView(productDTO.getView());
        model.setNameProduct(productDTO.getNameProduct());
        model.setTypeIdEntity(productDTO.getTypeIdEntity());
        model.setStatus(productDTO.getStatus());
        model.setComment(count);
        model.setImageProductDTOS(imageProductDTOS);
        model.setRating(sumStar);

    }
}
