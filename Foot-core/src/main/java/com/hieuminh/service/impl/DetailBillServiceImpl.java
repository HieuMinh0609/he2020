package com.hieuminh.service.impl;

import com.hieuminh.controller.web.api.DetailProductAPI;
import com.hieuminh.converter.BillConverter;
import com.hieuminh.converter.DetailBillConverter;
import com.hieuminh.converter.DetailSaleConverter;
import com.hieuminh.dto.DetailBillDTO;
import com.hieuminh.dto.DetailSaleDTO;
import com.hieuminh.dto.TransporterDTO;
import com.hieuminh.dto.TypeDTO;
import com.hieuminh.dto.utils.DateDTO;
import com.hieuminh.entity.BillEntity;
import com.hieuminh.entity.DetailBillEntity;
import com.hieuminh.entity.DetailSaleEntity;
import com.hieuminh.entity.TypeEntity;
import com.hieuminh.repository.BillDao;
import com.hieuminh.repository.DetailBillDao;
import com.hieuminh.repository.TypeDao;
import com.hieuminh.service.DetailBillService;
import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service("DetailBillService")
@Transactional
public class DetailBillServiceImpl implements DetailBillService {
    private Logger LOGGER = Logger.getLogger(DetailBillServiceImpl.class);

    @Autowired
    DetailBillDao detailBillDao;
    @Autowired
    TypeDao typeDao;

    @Override
    public void SaveDetailBill(DetailBillDTO detailBillDTO) {
        try {
            DetailBillEntity entity = DetailBillConverter.dtoToEntity(detailBillDTO);

            detailBillDao.save(entity);
        }catch (Exception e){
            LOGGER.debug("Delte Fail........");
            e.getMessage();
        }

    }

    @Override
    public List<DetailBillDTO> findByPropertyMapNotLike(Map<String, Object> property) {
        List<DetailBillDTO> detailSaleDTOS=new ArrayList<DetailBillDTO>();
        Object[] objects = detailBillDao.findByPropertyMapNotLike(property,null,null,null,null);
        for(DetailBillEntity item:(List<DetailBillEntity>)objects[1]){
            DetailBillDTO detailBillDTO = DetailBillConverter.entityToDto(item);
            detailSaleDTOS.add(detailBillDTO);
        }
        return detailSaleDTOS;


    }

    @Override
    public List<Integer> findSumRevenueOnType() {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


        List<TypeEntity>  typeDTOS = typeDao.findAll();

        List<Integer> list = new ArrayList<>();
        for (int i = typeDTOS.size(); i >= 1; i--) {
            Object[] objects = detailBillDao.findSumRevenueOnType("" + i, date.format(formatter));
            Integer sum = 0;
            if (objects != null) {
                sum = Integer.parseInt(objects[0].toString());
            }
            list.add(sum);
        }
        return list;
    }

    @Override
    public List<Integer> findSumRevenueOnYear() {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String day_bill = "YEAR('" + date.format(formatter) + "')";

        List<TypeEntity>  typeDTOS = typeDao.findAll();

        List<Integer> list = new ArrayList<>();
        for (TypeEntity entity:typeDTOS) {
            Object[] objects = detailBillDao.findSumRevenueOnTypeOnYear("" + entity.getTypeId(), day_bill, "YEAR");
            Integer sum = 0;
            if (objects != null) {
                sum = Integer.parseInt(objects[0].toString());
            }
            list.add(sum);
        }
        return list;
    }

    @Override
    public List<Integer> findCostOnProperty() {
        List<Integer> list = new ArrayList<>();
        Date dateBefore30Days = DateUtils.addDays(new Date(), 0);
        DateFormat formatter1 = new SimpleDateFormat("dd");
        DateFormat formatter2 = new SimpleDateFormat("MM");
        DateFormat formatter3 = new SimpleDateFormat("yyyy");
        String day = formatter1.format(dateBefore30Days);
        String month = formatter2.format(dateBefore30Days);
        String year = formatter3.format(dateBefore30Days);

        List<TypeEntity>  typeDTOS = typeDao.findAll();

        for (TypeEntity entity:typeDTOS){
            Map<String,Object> property = new HashMap<>();
            property.put("idDBillProductEntity.typeIdEntity.typeId",entity.getTypeId());
            property.put("DAY(idDetailBillBillEntity.dateTime)","'"+day+"'");
            property.put("MONTH(idDetailBillBillEntity.dateTime)","'"+month+"'");
            property.put("YEAR(idDetailBillBillEntity.dateTime)","'"+year+"'");
            property.put("idDetailBillBillEntity.status",1);
            Object[] objects = detailBillDao.findCostOnProperty(property);
            list.add(Integer.parseInt(objects[0].toString()));
        }




        return list;
    }

    @Override
    public Integer  getSumProductByIdBill(List<Integer> idBill) {
        Integer sumProduct =0;
        for (Integer i:idBill){
         sumProduct+=detailBillDao.getSumProductByIdBill(i);
        }
        return sumProduct;
    }

    @Override
    public List<DetailBillDTO> findListByIdBill(Integer idBill) {
        List<DetailBillDTO> list = new ArrayList<>();
        List<DetailBillEntity> detailBillEntityList = detailBillDao.findListByIdBill(idBill);
          for (DetailBillEntity d:detailBillEntityList){
              DetailBillDTO detailBillDTO = DetailBillConverter.entityToDto(d);
              list.add(detailBillDTO);
          }
        return list;
    }

    @Override
    public List<DetailBillDTO> findListByDate(Integer typeid, DateDTO dateDTO,List<TransporterDTO> transporterDTOList) {
        List<DetailBillEntity> list = new ArrayList<>();
        for (TransporterDTO t:transporterDTOList){

            List<DetailBillEntity> detailBillEntitys = detailBillDao.findListByDate(typeid,dateDTO,t.getTransBillEntity().getIdBill());

            if(detailBillEntitys.size()>0){
                for (DetailBillEntity d:detailBillEntitys){
                    list.add(d);
                }
            }

        }
        List<DetailBillDTO> detailBillDTOS = new ArrayList<>();
        if(list.size()>0){

            for (DetailBillEntity entity: list){
                DetailBillDTO detailBillDTO = DetailBillConverter.entityToDto(entity);
                detailBillDTOS.add(detailBillDTO);
            }
        }

        return detailBillDTOS;
    }
}
