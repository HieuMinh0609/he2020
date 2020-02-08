package com.hieuminh.controller.admin.api;

import com.hieuminh.constant.CustomMessages;
import com.hieuminh.dto.*;
import com.hieuminh.dto.utils.DateDTO;
import com.hieuminh.dto.utils.ReportDTO;
import com.hieuminh.dto.utils.ReportRevenueDTO;
import com.hieuminh.service.*;
import com.hieuminh.utils.ExcelPoiUtil;
import com.hieuminh.utils.HistoryUtil;
import com.hieuminh.utils.SecurityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ReportAPI {
    private Logger LOGGER = Logger.getLogger(ReportAPI.class);

    @Autowired
    TypeService typeService;
    @Autowired
    ExcelPoiUtil excelPoiUtil;
    @Autowired
    DetailBillService detailBillService;
    @Autowired
    TransporterService transporterService;
    @Autowired
    LocationService locationService;
    @Autowired
    StoreService storeService;
    @Autowired
    HistoryUtil historyUtil;



    @RequestMapping(value = "api/admin/report/product", method = RequestMethod.POST)
    @ResponseBody
    public List<ReportDTO> ReportProduct(@RequestBody DateDTO model, HttpServletRequest request) {

        try{
            List<TransporterDTO> transporterDTOList = setTransporterFinal(model);

            List<ReportDTO> reportDTOS = setListReportDTO(transporterDTOList,model);
            if(reportDTOS.size()>0){
                reportDTOS = filterDetailBill(reportDTOS);

            }
            return reportDTOS;
        }catch (Exception e){
            LOGGER.error(e);
        }
       return null;
    }

    @RequestMapping(value = "/api/admin/report/export", method = RequestMethod.POST)
    @ResponseBody
    public boolean exportExcel(@RequestBody DateDTO model, HttpServletRequest request) {
        try{
            List<TypeDTO> typeDTOList =typeService.findAll();
            List<TransporterDTO> transporterDTOList = setTransporterFinal(model);

            List<ReportDTO> reportDTOS = setListReportDTO(transporterDTOList,model);
            if(reportDTOS.size()>0){
                reportDTOS = filterDetailBill(reportDTOS);

            }
            List<ReportRevenueDTO> reportRevenueDTOS =  setDetailBillByDate(model,typeDTOList,transporterDTOList);

            excelPoiUtil.ExportExcel(reportDTOS,reportRevenueDTOS);
            historyUtil.updateHistoryConverterBill(SecurityUtils.getPrincipal().getUsername(),"Export excel"+model.getDay()+model.getMonth()+model.getYear() +" "+ CustomMessages.HISTORY_REPORT);
        }catch (Exception e){
            LOGGER.error(e);
            return false;
        }
        return true;

    }

    @RequestMapping(value = "api/admin/report/revenue", method = RequestMethod.POST)
    @ResponseBody
    public List<ReportRevenueDTO> ReportRvenue(@RequestBody DateDTO model, HttpServletRequest request) {
        try{
            List<TypeDTO> typeDTOList =typeService.findAll();
            List<TransporterDTO> transporterDTOList = setTransporterFinal(model);



            List<ReportRevenueDTO> reportDTOS =  setDetailBillByDate(model,typeDTOList,transporterDTOList);

            return reportDTOS;
        }catch (Exception e){
            LOGGER.error(e);
        }
        return null;

    }

    private List<ReportDTO> filterDetailBill(List<ReportDTO> reportDTOS) {

        for (int i=0;i<reportDTOS.size()-1;i++){
            Integer count = reportDTOS.get(i).getCountProduct();
            for (int j=i+1;j<reportDTOS.size();j++){
                if(reportDTOS.get(i).getProductDTO().getIdProduct() ==
                        reportDTOS.get(j).getProductDTO().getIdProduct()){
                    count+= reportDTOS.get(j).getCountProduct();
                    reportDTOS.get(i).setCountProduct(count);
                    reportDTOS.remove(j);
                }
            }
        }
        return reportDTOS;
    }

    private List<ReportDTO> setListReportDTO(List<TransporterDTO> transporterDTOList,DateDTO model) {
        List<ReportDTO> list = new ArrayList<>();
        StoreDTO storeDTO = storeService.findById(Integer.parseInt(model.getId().toString()));


        List<DetailBillDTO> detailBillDTOS = detailBillService.findListByDate(null,model,transporterDTOList);
        if(detailBillDTOS.size()>0) {
            for (DetailBillDTO d : detailBillDTOS) {
                ReportDTO reportDTO = new ReportDTO();
                reportDTO.setStoreDTO(storeDTO);
                reportDTO.setProductDTO(d.getIdDBillProductEntity());
                reportDTO.setCountProduct(d.getCount());
                reportDTO.setDateTime(model.getDay()+"/"+ model.getMonth()+"/"+model.getYear());
                list.add(reportDTO);
            }
        }
        return list;
    }



    private List<TransporterDTO> setTransporterFinal(DateDTO model) {
        List<TransporterDTO> transporterDTOList = new ArrayList<>();

        Map<String,Object> mapLocation = new HashMap<>();
        mapLocation.put("storeLocationEntity.idstore",Integer.parseInt(model.getId().toString()));
        Object[] objectsLocation= locationService.findByPropertyMapNotLike(mapLocation,null,null,null,null);
        List<LocationDTO> locationDTOS = (List<LocationDTO>) objectsLocation[1];

        for (LocationDTO l : locationDTOS){
            Map<String,Object> mapTrans = setMapTransporter(l,model);

                List<TransporterDTO> transporterDTOS  = (List<TransporterDTO>) transporterService.findByPropertyMapNotLike(mapTrans,null,null,null,null)[1];
                for (TransporterDTO t:transporterDTOS){
                    transporterDTOList.add(t);
                }

        }
        return  transporterDTOList;
    }

    private Map<String, Object> setMapTransporter(LocationDTO l,DateDTO dateDTO) {
        Map<String,Object> mapTrans = new HashMap<>();
        mapTrans.put("transUserEntity.idUser",l.getUserEntityLocation().getIdUser());
        if (dateDTO.getDay() == "" && dateDTO.getMonth() == "" && dateDTO.getYear() != "") {
            mapTrans.put("YEAR(timeStart)",Integer.parseInt(dateDTO.getYear()));
        } else if (dateDTO.getDay() == "" && dateDTO.getMonth() != "" && dateDTO.getYear() != "") {
            mapTrans.put("MONTH(timeStart)",Integer.parseInt(dateDTO.getMonth()));
            mapTrans.put("YEAR(timeStart)",Integer.parseInt(dateDTO.getYear()));
        } else {
            mapTrans.put("MONTH(timeStart)",Integer.parseInt(dateDTO.getMonth()));
            mapTrans.put("DAY(timeStart)",Integer.parseInt(dateDTO.getDay()));
            mapTrans.put("YEAR(timeStart)",Integer.parseInt(dateDTO.getYear()));
        }
        mapTrans.put("status",1);

        return mapTrans;
    }

    private List<ReportRevenueDTO> setDetailBillByDate(DateDTO model, List<TypeDTO> typeDTOList, List<TransporterDTO> transporterDTOList) {
        List<ReportRevenueDTO> reportDTOList = new ArrayList<>();

        for (TypeDTO i:typeDTOList){
            try{
                List<DetailBillDTO> detailBillDTOS = detailBillService.findListByDate(i.getTypeId(),model,transporterDTOList);


                if(detailBillDTOS.size()>0){
                    ReportRevenueDTO revenueDTO = setRevennueDTO(detailBillDTOS,i);
                    reportDTOList.add(revenueDTO);
                }
            }catch (Exception e){
                LOGGER.error(e);
            }

        }
        reportDTOList =setPercenReportDTO(reportDTOList);


        return reportDTOList;
    }

    private List<ReportRevenueDTO> setPercenReportDTO( List<ReportRevenueDTO> reportRevenueDTOS) {
       Integer sumCount = 0;
       for (ReportRevenueDTO re:reportRevenueDTOS){
           sumCount+=re.getCountType();
       }
        for (ReportRevenueDTO re:reportRevenueDTOS){
            if(sumCount!=0){
                float percen = ((float) re.getCountType()/sumCount)*100;
                re.setPercen(Double.parseDouble(percen+""));
            }else{
                re.setPercen(0);
            }


        }
        return  reportRevenueDTOS;
    }

    private ReportRevenueDTO setRevennueDTO(List<DetailBillDTO> detailBillDTOS, TypeDTO i) {
        ReportRevenueDTO revenueDTO = new ReportRevenueDTO();
        Integer sumMoney=0;

        Integer count=0;
        revenueDTO.setTypeDTO(i);

        for (DetailBillDTO d:detailBillDTOS){
            sumMoney+=d.getIdDetailBillBillEntity().getCost();
            count+=d.getCount();
        }

        revenueDTO.setCountType(count);
        revenueDTO.setSumMoneyType(sumMoney);
        return revenueDTO;
    }

}
