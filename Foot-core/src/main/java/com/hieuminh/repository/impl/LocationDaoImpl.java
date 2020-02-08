package com.hieuminh.repository.impl;

import com.hieuminh.entity.LocationEntity;
import com.hieuminh.repository.LocationDao;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.ParameterizedType;

@Repository("LocationDao")
public class LocationDaoImpl  extends AbstractDao<Integer, LocationEntity> implements LocationDao {





}
