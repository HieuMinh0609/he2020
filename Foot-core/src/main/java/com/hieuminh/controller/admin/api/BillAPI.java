package com.hieuminh.controller.admin.api;


import com.hieuminh.constant.CoreConstant;
import com.hieuminh.constant.CustomMessages;
import com.hieuminh.constant.SystemConstant;
import com.hieuminh.dto.*;
import com.hieuminh.service.BillService;
import com.hieuminh.service.DetailBillService;
import com.hieuminh.service.HistoryService;
import com.hieuminh.service.UserService;
import com.hieuminh.utils.HistoryUtil;
import com.hieuminh.utils.PdfPoiUtil;
import com.hieuminh.utils.SecurityUtils;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.javafx.collections.MappingChange;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BillAPI {


    private Logger LOGGER = Logger.getLogger(BillAPI.class);

    @Autowired
    private BillService billService;
    @Autowired
    private UserService userService;
    @Autowired
    private DetailBillService detailBillService;
    @Autowired
    private HistoryUtil historyUtil;



    @Autowired
    private PdfPoiUtil pdfPoiUtil;

    @RequestMapping(value = "/api/admin/bill/detail/{id}", method = RequestMethod.GET)
    public ModelAndView detailBill(@ModelAttribute(SystemConstant.MODEL) BillDTO model, @PathVariable("id") Integer id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/bill/edit");
        Map<String,Object> map = new HashMap<>();
        map.put("idDetailBillBillEntity.idBill",id);
        List<DetailBillDTO> list = detailBillService.findByPropertyMapNotLike(map);

        model = billService.findById(id);

        mav.addObject(SystemConstant.MODEL, model);
        mav.addObject(SystemConstant.ITEMS, list);

        return mav;
    }
    @RequestMapping(value = "/api/admin/bill/details", method = RequestMethod.POST)
    @ResponseBody
    public List<DetailBillDTO> detailBills(@RequestBody DetailBillDTO model, HttpServletRequest request) {
        Map<String,Object> map = new HashMap<>();
        map.put("idDetailBillBillEntity.idBill",Integer.parseInt(""+model.getId()));
        List<DetailBillDTO> list = detailBillService.findByPropertyMapNotLike(map);
        return list;
    }

    @RequestMapping(value = "/api/admin/bill/converter", method = RequestMethod.PUT)
    @ResponseBody
    public Boolean converterBill(@RequestBody List<Integer> idList, HttpServletRequest request) {
        if(idList.size()>0){
            try{
                for(Integer item:idList){
                    BillDTO billDTO = billService.findById(item);
                    if(billDTO.getStatus()==0){
                        billDTO.setStatus(1);
                        billDTO.setOnline(0);
                        billService.updateBill(billDTO);
                    }else{
                        billDTO.setStatus(0);
                        billDTO.setOnline(0);
                        billService.updateBill(billDTO);
                    }
                }
                historyUtil.updateHistoryConverterBill(SecurityUtils.getPrincipal().getUsername(),"Converter "+idList.size()+" "+ CustomMessages.HISTORY_BIll);
            }catch (Exception e){
                LOGGER.error(e.getMessage(),e);
                return false;
            }
        }else{
            return false;
        }
        return true;
    }


    @RequestMapping(value = "/api/admin/bill/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public Boolean deleteProduct(@RequestBody List<Integer> idList) {

        Integer resultProduct = billService.deleteBill(idList);
        historyUtil.updateHistoryConverterBill(SecurityUtils.getPrincipal().getUsername(),"Delete "+idList.size()+" "+ CustomMessages.HISTORY_BIll);
        if (resultProduct != idList.size()) {
            LOGGER.debug("Delte Fail........");
            return false;

        }
        return true;

    }
    @RequestMapping(value = "/api/admin/bill/exports", method = RequestMethod.PUT)
    @ResponseBody
    public Boolean exports(@RequestBody List<Integer>  idBills) {
        for(Integer i:idBills){
           exportOneFile(i);
        }
        return true;
    }

    @RequestMapping(value = "/api/admin/bill/exportPdf", method = RequestMethod.POST)
    @ResponseBody
    public Boolean exportPdfOne(@RequestBody Integer  idBill) {
       if(exportOneFile(idBill)==0){
           return true;
       }else{
           return false;
       }
   }

    private int exportOneFile(int idBill){
        int kt=0;
        Map<String,Object> map = new HashMap<>();
        map.put("idDetailBillBillEntity.idBill",idBill);
        List<DetailBillDTO> detailBillDTOs = detailBillService.findByPropertyMapNotLike(map);

        BillDTO billDTO = billService.findById(idBill);

        billDTO.setPrinted(1);
        billService.updateBill(billDTO);

        String path = SystemConstant.BASE_DIR+"/"+"pdf"+"/bill_of_"+billDTO.getIdBill()+".pdf";
        try {


            Document document = new Document();

            File file = new File(StringUtils.substringBeforeLast(path, "/"));
            if (!Files.exists(Paths.get(path))) {
                file.mkdirs();
            }else{
                kt=1;
                return kt;
            }

            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(path));
            document.open();
            pdfPoiUtil.addMetaData(document,billDTO.getIdBill());
            pdfPoiUtil.addTitlePage(document,SecurityUtils.getPrincipal().getUsername(),detailBillDTOs);
            document.close();
            writer.close();
            historyUtil.updateHistoryConverterBill(SecurityUtils.getPrincipal().getUsername(),"ExportPdf ID_BILL:"+idBill+" "+ CustomMessages.HISTORY_BIll);

        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(),e);
        }
        return kt;
    }
}
