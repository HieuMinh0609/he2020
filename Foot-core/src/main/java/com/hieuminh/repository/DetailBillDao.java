package com.hieuminh.repository;

import com.hieuminh.dto.utils.DateDTO;
import com.hieuminh.entity.DetailBillEntity;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface DetailBillDao extends GenericDao<Integer, DetailBillEntity>  {
    Object[] findSumRevenueOnType(String TypeId, String date);
    Object[] findSumRevenueOnTypeOnYear(String TypeId,String date,String typeDate);
    Object[] findCostOnProperty(Map<String,Object> property);
    Integer getSumProductByIdBill(Integer idBill);

   List<DetailBillEntity>  findListByDate(Integer typeid,DateDTO dateDTO,Integer idBill);

    List<DetailBillEntity>  findListByIdBill(Integer idBill);
}
