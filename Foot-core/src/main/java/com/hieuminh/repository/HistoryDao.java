package com.hieuminh.repository;

import com.hieuminh.entity.MyHistoryEntity;

import java.util.Map;


public interface HistoryDao  extends  GenericDao<Integer, MyHistoryEntity> {
    Object[] findByHistory(Integer offset, Integer limit);

}
