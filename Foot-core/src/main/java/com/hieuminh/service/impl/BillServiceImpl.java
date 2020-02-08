package com.hieuminh.service.impl;

import com.hieuminh.converter.BillConverter;
import com.hieuminh.converter.ProductConverter;
import com.hieuminh.dto.BillDTO;
import com.hieuminh.entity.BillEntity;
import com.hieuminh.entity.ProductEntity;
import com.hieuminh.repository.BillDao;
import com.hieuminh.service.BillService;
import org.apache.commons.lang.time.DateUtils;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service("Billservice")
@Transactional
public class BillServiceImpl implements BillService {

    @Autowired
    BillDao billDao;

    @Override
    public Object[] findByProberty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        Object[] objects = billDao.findByProperty(property, sortExpression, sortDirection, offset, limit);
        List<BillDTO> list = new ArrayList<>();
        for (BillEntity billEntity : (List<BillEntity>) objects[1]) {
            BillDTO billDTO = BillConverter.entityToDto(billEntity);
            list.add(billDTO);
        }
        objects[1] = list;
        return objects;
    }

    @Override
    public BillDTO findById(Integer idBill) {
        BillEntity entity = billDao.findById(idBill);
        BillDTO dto = BillConverter.entityToDto(entity);
        return dto;


    }

    @Override
    public BillDTO updateBill(BillDTO billDTO) {
        BillEntity billEntity = BillConverter.dtoToEntity(billDTO);
        billEntity = billDao.update(billEntity);
        billDTO = BillConverter.entityToDto(billEntity);
        return billDTO;
    }

    @Override
    public void SaveBill(BillDTO billDTO) {
        Timestamp createDate = new Timestamp(System.currentTimeMillis());
        billDTO.setOnline(0);
        billDTO.setStatus(0);
        billDTO.setDateTime(createDate);
        BillEntity entity = BillConverter.dtoToEntity(billDTO);

        billDao.save(entity);
    }

    @Override
    public Integer deleteBill(List<Integer> ids) throws HibernateException {
        Integer result = billDao.delete(ids);
        return result;
    }

    @Override
    public List<BillDTO> findListBillOnNew() {
        Object[] objects = billDao.findListBillOnNew();
        List<BillDTO> list = new ArrayList<>();
        if (objects != null) {

            for (BillEntity billEntity : (List<BillEntity>) objects[0]) {
                BillDTO billDTO = BillConverter.entityToDto(billEntity);
                list.add(billDTO);
            }
        }

        return list;
    }

    @Override
    public Integer findSumRevenue(String property, String day,String month,String year) {
        Object[] objects = billDao.findSumRevenue(property, day,month,year);
        Integer sum = 0;
        if (objects != null) {
            sum = Integer.parseInt(objects[0].toString());
        }
        return sum;
    }

    @Override
    public List<BillDTO> findOnBillTransport() {
        Object[] objects = billDao.findOnBillTransport();
        List<BillDTO> list = new ArrayList<>();
        if (objects != null) {
            for (BillEntity billEntity : (List<BillEntity>) objects[0]) {
                BillDTO billDTO = BillConverter.entityToDto(billEntity);
                list.add(billDTO);
            }
        }

        return list;
    }

    @Override
    public List<Integer> findRevenue7Day() {
        List<Integer> list = new ArrayList<>();


        for (int i = 6; i >= 0; i--) {
            Date dateBefore30Days = DateUtils.addDays(new Date(), -i);
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String date_fomat = formatter.format(dateBefore30Days);
            Object[] objects = billDao.findSumRevenue("DAY", "DAY('" + date_fomat + "')","MONTH('"+date_fomat + "')","YEAR('"+date_fomat + "')");
            Integer sum = 0;
            if (objects != null) {
                sum = Integer.parseInt(objects[0].toString());
            }
            list.add(sum);
        }
        return list;
    }




    @Override
    public List<Integer> findSumRevenueOn3Year() {
        List<Integer> list = new ArrayList<>();
        for (int i = 2; i >=0; i--) {

            Date dateBefore30Days =  DateUtils.addYears(new Date(), -i);
            DateFormat formatter = new SimpleDateFormat("yyyy");
            String date_fomat = formatter.format(dateBefore30Days);
            for(int j=1;j<=12;j++){
                Object[] objects = billDao.findSumRevenueOn3Year("YEAR", "'" + date_fomat + "'","'"+j+"'");

                list.add(Integer.parseInt(objects[0].toString()));

            }
        }
        return list;
    }



    @Override
    public Object[] findByPropertyMapNotLike(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        Object[] objects = billDao.findByPropertyMapNotLike(property, sortExpression, sortDirection, offset, limit);
        List<BillDTO> list = new ArrayList<>();
        for (BillEntity billEntity : (List<BillEntity>) objects[1]) {
            BillDTO billDTO = BillConverter.entityToDto(billEntity);
            list.add(billDTO);
        }
        objects[1] = list;
        return objects;
    }

    @Override
    public Integer sumMoneyByIdBill(List<Integer> idBill) {
        Integer sumMoney=0;
        for (Integer i:idBill){
            sumMoney+=billDao.sumMoneyByIdBill(i);
        }
        return sumMoney;
    }
}