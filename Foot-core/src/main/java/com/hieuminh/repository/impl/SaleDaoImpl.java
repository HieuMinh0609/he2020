package com.hieuminh.repository.impl;

import com.hieuminh.entity.SaleEntity;
import com.hieuminh.repository.SaleDao;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository("SaleDao")
public class SaleDaoImpl extends AbstractDao<Integer, SaleEntity> implements SaleDao {

    private final Logger log = Logger.getLogger(this.getClass());
    @Autowired
    SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }



    @Override
    @Transactional
    public List<SaleEntity> getSaleNow(String dateNow) {

        List<SaleEntity> list =new ArrayList<SaleEntity>();

        try {
            StringBuffer sql1 = new StringBuffer("FROM SaleEntity be WHERE DATE(be.timeStart)<=DATE('"+dateNow+"') AND DATE(be.timeEnd)>=DATE('"+dateNow+"')");
            Query query1 =  getSession().createQuery(sql1.toString());


            list=query1.list();

        }catch (Exception e) {
            log.error(e.getMessage(),e);

            return list;
        }
        return list;
    }
}
