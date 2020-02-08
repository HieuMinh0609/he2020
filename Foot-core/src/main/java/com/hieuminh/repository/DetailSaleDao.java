package com.hieuminh.repository;

import com.hieuminh.dto.DetailSaleDTO;
import com.hieuminh.entity.DetailSaleEntity;

import java.util.List;

public interface DetailSaleDao extends  GenericDao<Integer, DetailSaleEntity> {
    void deleteDetailSale(String property,Integer value);
    List<DetailSaleEntity> findListById(String property, Object value);
    Integer valideteDetailSale(String idSale,String idProduct);
    Integer checkInSale(Integer id,String nowDate);
    List<DetailSaleEntity> getIdProAndDownForSale(String nowDate,Integer maxPage,Integer setFirstItem);
    List<DetailSaleEntity> getIdProAndDown(String nowDate);
}
