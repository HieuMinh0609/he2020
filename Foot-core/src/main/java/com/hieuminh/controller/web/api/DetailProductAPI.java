package com.hieuminh.controller.web.api;


import com.hieuminh.constant.CustomMessages;
import com.hieuminh.dto.*;
import com.hieuminh.entity.DetailBillEntity;
import com.hieuminh.service.*;
import com.hieuminh.utils.HistoryUtil;
import com.hieuminh.utils.SecurityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Security;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DetailProductAPI {
    private Logger LOGGER = Logger.getLogger(DetailProductAPI.class);
    @Autowired
    CommentService commentService;

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;
    @Autowired
    BillService billService;

    @Autowired
    DetailBillService detailBillService;

    @Autowired
    LoginService loginService;
    @Autowired
    HistoryUtil historyUtil;

    @Autowired
    DetailSaleService detailSaleService;


    @RequestMapping(value = "/api/product/comment/{id}", method = RequestMethod.POST)
    @ResponseBody
    public List<CommentDTO> showComment(@RequestBody CommentDTO model, @PathVariable("id") Integer id,HttpServletRequest request) {
        Map<String,Object> map = new HashMap<>();
        map.put("idProduct",id);
        Integer maxPage = model.getMaxPageItems();
        Integer setFirstItem=((model.getPage() - 1) * model.getMaxPageItems());
        Object[] objects = commentService.findByProberty(map,"dateTime","2",setFirstItem,maxPage);
        List<CommentDTO> commentDTOS = (List<CommentDTO>) objects[1];

        return commentDTOS;

    }

    @RequestMapping(value = "/api/user/order/add/bill/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Boolean addBillFromUser(@RequestBody BillDTO model, @PathVariable("id") Integer id, HttpServletRequest request) {
         boolean res=false;
         try{
             ProductDTO productDTO = productService.findById(id);
             List<DetailSaleDTO> detailSaleDTOs= detailSaleService.getIdProAndDown();
             LoginDTO userLogin= loginService.findByUserName("nameLogin",SecurityUtils.getPrincipal().getUsername());
             UserDTO userDTO =  userLogin.getIdLoginUserEntity(); if(detailSaleDTOs.size()>0){
                 for(DetailSaleDTO d:detailSaleDTOs){
                     if(d.getIdProductSaleEntity().getIdProduct()==id){
                         productDTO.setCost(productDTO.getCost()-(productDTO.getCost()*d.getDownpercen())/100);
                     }
                 }
             }

             setBillAndDetail(model,productDTO,userDTO,userLogin);


             res=true;
         }catch (Exception e){
             e.getMessage();

         }

        return res;
    }




    @RequestMapping(value = "/api/admin/delete/comment", method = RequestMethod.POST)
    @ResponseBody
    public Boolean deleteComment(@RequestBody CommentDTO model,HttpServletRequest request) {

        List<Integer> idList = new ArrayList<>();
        idList.add(model.getIdComment());
        Integer result = commentService.deleteComment(idList);
        historyUtil.updateHistoryConverterBill(SecurityUtils.getPrincipal().getUsername(),"Delete Id:"+model.getIdComment()+" of comment ");
        if (result != idList.size()) {
            LOGGER.debug("Delte Fail........");
            return false;

        }
        return true;

    }

    @RequestMapping(value = "/api/user/product/comment/add/{id}", method = RequestMethod.POST)
    @ResponseBody
    public  CommentDTO addComment(@RequestBody CommentDTO model, @PathVariable("id") Integer id,HttpServletRequest request) {
        LoginDTO userLogin= loginService.findByUserName("nameLogin",SecurityUtils.getPrincipal().getUsername());
        UserDTO userDTO =  userLogin.getIdLoginUserEntity();
        ProductDTO productDTO = productService.findById(id);

        Timestamp createDate = new Timestamp(System.currentTimeMillis());

        model.setDateTime(createDate);
        model.setIdUserEntity(userDTO);
        model.setIdProductEntity(productDTO);

        commentService.saveComment(model);

        return model;
    }

    private void setBillAndDetail(BillDTO model, ProductDTO productDTO, UserDTO userDTO,LoginDTO loginDTO) {
        if(model.getPhoneNumber().equals("") && model.getPlace().equals("") && model.getId()!=0){
            saveDetailBillAndUpdateBill(model,userDTO,productDTO);

        }else if(!model.getPhoneNumber().equals("") && !model.getPlace().equals("") && model.getId()==0 ) {

            BillDTO billDTO = setBillDTO(model,userDTO);
            billService.SaveBill(billDTO);

            Map<String,Object> map = new HashMap<String, Object>();
            map.put("idUserEntity",userDTO.getIdUser());
            map.put("status",0);

            List<BillDTO> list = (List<BillDTO>) billService.findByProberty(map,"dateTime","2",null,null)[1];

            model.setIdBill(list.get(0).getIdBill());
            saveDetailBillAndUpdateBill(model,userDTO,productDTO);
        }

    }

    private void saveDetailBillAndUpdateBill(BillDTO model, UserDTO userDTO,ProductDTO productDTO) {
        BillDTO billDTO = new BillDTO();
        if(model.getId()!=0){
            billDTO = billService.findById(Integer.parseInt(model.getId().toString()));
        }else{
            billDTO = billService.findById(model.getIdBill());;
        }

        DetailBillDTO detailBillDTO = new DetailBillDTO();
        detailBillDTO.setCount(model.getCount());
        detailBillDTO.setIdDetailBillBillEntity(billDTO);
        detailBillDTO.setIdDBillProductEntity(productDTO);

        detailBillService.SaveDetailBill(detailBillDTO);


        billDTO.setCost(billDTO.getCost()+(model.getCost()*model.getCount()));

        billService.updateBill(billDTO);

    }

    private BillDTO setBillDTO(BillDTO model, UserDTO userDTO) {
        BillDTO billDTO = new BillDTO();
        billDTO.setIdUserEntity(userDTO);
        billDTO.setPhoneNumber(model.getPhoneNumber());
        billDTO.setPlace(model.getPlace());
        billDTO.setLongitude(model.getLongitude());
        billDTO.setLatitude(model.getLatitude());
        billDTO.setCount(model.getCount());
        billDTO.setCost(0);
        billDTO.setPrinted(0);
        return billDTO;
    }
}
