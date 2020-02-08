package com.hieuminh.repository;

import com.hieuminh.entity.SaleEntity;

import java.util.List;

public interface SaleDao extends  GenericDao<Integer, SaleEntity>  {
    List<SaleEntity> getSaleNow(String dateNow);
}
