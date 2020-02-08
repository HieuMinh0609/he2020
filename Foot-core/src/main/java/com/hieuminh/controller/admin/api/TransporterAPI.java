package com.hieuminh.controller.admin.api;

import com.hieuminh.constant.CustomMessages;
import com.hieuminh.constant.SystemConstant;
import com.hieuminh.dto.*;
import com.hieuminh.dto.utils.DateDTO;
import com.hieuminh.service.*;
import com.hieuminh.utils.handlingTSP.GPsMapUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.NumberFormat;
import java.util.*;

@Controller
public class TransporterAPI {
    private final Logger log = Logger.getLogger(TransporterAPI.class);


    @Autowired
    private StoreService storeService;
    @Autowired
    private TransporterService transporterService;
    @Autowired
    private DetailBillService detailBillService;
    @Autowired
    private BillService billService;
    @Autowired
    private LocationService locationService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private GPsMapUtils gPsMapUtils;


    @RequestMapping(value = "/api/admin/transporter/nottrans", method = RequestMethod.POST)
    @ResponseBody
    public List<BillDTO> showListNotTrans(@RequestBody BillDTO model, HttpServletRequest request) {
        try{
            Map<String,Object> property = new HashMap<String,Object>();
            property.put("status","0");
            property.put("online","0");
            property.put("printed","1");

            Integer maxPage = model.getMaxPageItems();
            Integer setFirstItem=((model.getPage() - 1) * model.getMaxPageItems());

            Object[] objects = billService.findByProberty(property,null,null,setFirstItem,maxPage);
            List<BillDTO> listBill = (List<BillDTO>) objects[1];

            return listBill;
        }catch (Exception e){
            return null;
        }

    }

    @RequestMapping(value = "/api/admin/transporter/trans", method = RequestMethod.POST)
    @ResponseBody
    public List<TransporterDTO> showBillBeingTrans(@RequestBody TransporterDTO model, HttpServletRequest request) {
        try{
            Map<String,Object> property = new HashMap<String,Object>();
            property.put("status",0);
            property.put("transUserEntity.idUser", Integer.parseInt(model.getId().toString()));

            Integer maxPage = model.getMaxPageItems();
            Integer setFirstItem=((model.getPage() - 1) * model.getMaxPageItems());

            Object[] objects = transporterService.findByPropertyMapNotLike(property,null,null,setFirstItem,maxPage);
            List<TransporterDTO> listBill = (List<TransporterDTO>) objects[1];

            return listBill;
        }catch (Exception e){
            return null;
        }
    }


    @RequestMapping(value = "/api/admin/transporter/infor/{id}", method = RequestMethod.GET)
    public ModelAndView showInforTrasporter(@ModelAttribute(SystemConstant.MODEL) LoginDTO model, @PathVariable("id") Integer id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/admin/transporter/statistic");

        model = loginService.findByIdUser("idLoginUserEntity.idUser", id);
        LocationDTO locationDTO = locationService.findEqualUnique("userEntityLocation.idUser",id);

        mav.addObject(SystemConstant.ITEMS, locationDTO);
        mav.addObject(SystemConstant.MODEL, model);
        return mav;

    }


    @RequestMapping(value = "/api/admin/transporter/transOld", method = RequestMethod.POST)
    @ResponseBody
    public List<TransporterDTO> showBillsTransOld(@RequestBody TransporterDTO model, HttpServletRequest request) {
        try{
            Map<String,Object> property = new HashMap<String,Object>();
            property.put("status",1);
            property.put("transUserEntity.idUser", Integer.parseInt(model.getId().toString()));

            Integer maxPage = model.getMaxPageItems();
            Integer setFirstItem=((model.getPage() - 1) * model.getMaxPageItems());

            Object[] objects = transporterService.findByPropertyMapNotLike(property,null,null,setFirstItem,maxPage);
            List<TransporterDTO> listBill = (List<TransporterDTO>) objects[1];

            return listBill;
        }catch (Exception e){
            return null;
        }

    }

    @RequestMapping(value = "/api/admin/show/statistic", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> showStatistic(@RequestBody DateDTO model, HttpServletRequest request) {
        Map<String,String> list =  new HashMap<>();
        try{
            Integer countProductFinal=0;
            Integer sumMoneyFinal=0;
            Integer sumMoneyProcess=0;
            Integer countProductProcess=0;

            List<Integer> idBillProcessTrans = (List<Integer>) transporterService.getListIdBillOnDayByIdUser(Integer.parseInt(model.getId().toString()),model.getDay(),model.getMonth(),model.getYear(),"0");



            if(idBillProcessTrans.size()>0){
                  countProductProcess = detailBillService.getSumProductByIdBill(idBillProcessTrans);
                  sumMoneyProcess = billService.sumMoneyByIdBill(idBillProcessTrans);
            }

            List<Integer> idBillFinalTrans = (List<Integer>) transporterService.getListIdBillOnDayByIdUser(Integer.parseInt(model.getId().toString()),model.getDay(),model.getMonth(),model.getYear(),"1");

            if(idBillFinalTrans.size()>0){
                countProductFinal= detailBillService.getSumProductByIdBill(idBillFinalTrans);
                sumMoneyFinal = billService.sumMoneyByIdBill(idBillFinalTrans);
            }

            list = addListStatis(idBillProcessTrans,idBillFinalTrans,countProductFinal,countProductProcess,sumMoneyFinal,sumMoneyProcess);

        }catch (Exception e){
            return null;
        }
        return list;
    }




    @RequestMapping(value = "/api/admin/transporter/give", method = RequestMethod.GET,produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String PassToTransporter(HttpServletRequest request) {
        try{

            List<StoreDTO> storeDTOS = getAllStore();
            String message = null;
            boolean kt=true;
            while(kt){
                List<BillDTO> billDTOList = getAllBillNotFinal();

                List<Integer> list = gPsMapUtils.setClassifyMatrix(billDTOList,storeDTOS);
                if(list.size()>0){
                    List<LocationDTO> locationDTOS =  setListReady(list.get(0));
                    if(locationDTOS.size()>0){
                        Object[] objects = gPsMapUtils.getDataForTrans(billDTOList,storeDTOS,locationDTOS.get(0));

                        if(objects.length>0){
                           List<TransporterDTO> transporterDTOs = setTransporter(objects);
                           for (TransporterDTO t:transporterDTOs){
                               transporterService.SaveTransporter(t);
                           }
                            updateBillAndLocation(locationDTOS,transporterDTOs,objects);
                        }else{
                            message =  CustomMessages.N0_TRANSPORTER;
                        }
                    }else{

                        message= CustomMessages.N0_TRANSPORTER;


                    }
                }else{
                    message= CustomMessages.PASS_DISTANCE;
                    kt=false;
                }

                }

            return message;
        }catch (Exception e){
            log.debug(e);
            log.error(e);
            return CustomMessages.ERROR;
        }
    }

    private void updateBillAndLocation(List<LocationDTO> locationDTOS, List<TransporterDTO> transporterDTOs,Object[] objects) {
        for (TransporterDTO tr: transporterDTOs){
            BillDTO billDTO =billService.findById(tr.getTransBillEntity().getIdBill());
            billDTO.setOnline(1);
            billService.updateBill(billDTO);
        }


        LocationDTO locationDTO = locationDTOS.get(0);
        locationDTO.setReady(0);
        locationDTO.setKm((Double) objects[2]);
        List<BillDTO> b = (List<BillDTO>) objects[0];
        locationDTO.setCountBill(locationDTO.getCountBill()+(b.size()-1));
        locationService.updateLocation(locationDTO);
    }

    private List<TransporterDTO> setTransporter(Object[] objects) {
        int indexStart =0;
        List<BillDTO> b = (List<BillDTO>) objects[0];
        List<TransporterDTO> transporterDTOList = new ArrayList<>();
        for (int i=1;i<b.size();i++){
            TransporterDTO transporterDTO = new TransporterDTO();
            UserDTO userDTO = (UserDTO) objects[3];


            transporterDTO.setTransUserEntity(userDTO);
            transporterDTO.setTransBillEntity(b.get(i));
            List<List<Integer>> listS = (List<List<Integer>>) objects[1];
            for(int j=1;j<listS.size();j++){
                if(listS.get(j).get(0)== (i) ){
                    transporterDTO.setPrioritize(j);
                }
            }
        transporterDTOList.add(transporterDTO);
        }
        return transporterDTOList;
    }

    private List<StoreDTO> getAllStore() {
        List<StoreDTO> list = storeService.findAll();

        return list;

    }

    private List<LocationDTO> setListReady(Integer idstore) {
        Map<String,Object> property = new HashMap<String,Object>();
        property.put("ready",1);
        property.put("storeLocationEntity.idstore",idstore);
        property.put("userEntityLocation.block",0);
        Object[] objects = locationService.findByPropertyMapNotLike(property,"countbill","1",null,null);
        List<LocationDTO> locationDTOS = (List<LocationDTO>) objects[1];

        return locationDTOS;

    }


    private List<BillDTO> getAllBillNotFinal() {
        Map<String,Object> property = new HashMap<String,Object>();
        property.put("status",0);
        property.put("online",0);
        property.put("printed",1);

        Object[] objects = billService.findByPropertyMapNotLike(property,null,null,null,null);
        List<BillDTO> listBill = (List<BillDTO>) objects[1];

        return listBill;
    }

    private Map<String, String> addListStatis(List<Integer> idBillProcessTrans, List<Integer> idBillFinalTrans, Integer countProductFinal, Integer countProductProcess, Integer sumMoneyFinal, Integer sumMoneyProcess) {
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        String costOld = currencyVN.format(sumMoneyFinal);
        String costProcess = currencyVN.format(sumMoneyProcess);

        Map<String,String> map = new HashMap<>();
        map.put(SystemConstant.BILL_OLD_TRANS,idBillFinalTrans.size()+"");
        map.put(SystemConstant.BILL_PROCESS_TRANS,idBillProcessTrans.size()+"");
        map.put(SystemConstant.PRODUCT_OLD_TRANS,countProductFinal+"");
        map.put(SystemConstant.PRODUCT_PROCESS_TRANS,countProductProcess+"");
        map.put(SystemConstant.MONEY_OLD_TRANS,costOld+"");
        map.put(SystemConstant.MONEY_PROCESS_TRANS,costProcess+"");

        return  map ;
    }
}
