package com.hieuminh.repository;

import com.hieuminh.entity.TransporterEntity;

import java.util.List;

public interface TransporterDao  extends  GenericDao<Integer, TransporterEntity> {
    List<Integer>  getListIdBillOnDayByIdUser(Integer idUser,String day,String month,String year,String status);

}
