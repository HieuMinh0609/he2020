package com.hieuminh.repository.impl;

import com.hieuminh.entity.DetailBillEntity;
import com.hieuminh.entity.StoreEntity;
import com.hieuminh.repository.DetailBillDao;
import com.hieuminh.repository.StoreDao;
import org.springframework.stereotype.Repository;

@Repository("StoreDao")
public class StoreDaoImpl  extends AbstractDao<Integer, StoreEntity> implements StoreDao {
}
