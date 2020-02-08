package com.hieuminh.repository;

import com.hieuminh.entity.BillEntity;

import java.util.Map;

public interface BillDao  extends  GenericDao<Integer, BillEntity> {
    Object[] findListBillOnNew();
    Object[] findSumRevenue(String property,String day,String month,String year);
    Object[] findOnBillTransport();
    Object[] findSumRevenueOn3Year(String date,String typeDate,String month);
    Integer sumMoneyByIdBill(Integer idBill);

}
