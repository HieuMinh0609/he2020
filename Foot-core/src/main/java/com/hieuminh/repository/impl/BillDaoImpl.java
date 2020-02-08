package com.hieuminh.repository.impl;

import com.hieuminh.entity.BillEntity;
import com.hieuminh.entity.UserEntity;
import com.hieuminh.repository.BillDao;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class BillDaoImpl extends AbstractDao<Integer, BillEntity> implements BillDao {
    private final Logger log = Logger.getLogger(this.getClass());
    @Autowired
    SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }


    @Override
    @Transactional
    public Object[] findListBillOnNew() {
        List<BillEntity> list =new ArrayList<BillEntity>();

        try {
            StringBuffer sql1 = new StringBuffer("FROM BillEntity be WHERE be.status= 0 AND be.online= 0 AND be.printed= 0");
            Query query1 =  getSession().createQuery(sql1.toString());


            list=query1.list();

        }catch (HibernateException e) {

            log.error(e.getMessage(),e);
            throw e;
        }
        return new Object[]{list};

    }

    @Override
    @Transactional
    public Object[] findSumRevenue(String dayProperty,String dayValue,String month,String year) {
        Integer cost=0;
        try {
            StringBuffer sql1 = new StringBuffer(" SELECT sum(cost) FROM BillEntity WHERE "+dayProperty+"(datetime) ="+dayValue+" AND status=1 AND MONTH(datetime)="+month+" AND YEAR(datetime)="+year);
            Query query1 =  getSession().createQuery(sql1.toString());


            cost= ((Long) query1.uniqueResult()).intValue();

        }catch (Exception e) {

            log.error(e.getMessage(),e);
            return new Object[]{cost};
        }
        return new Object[]{cost};

    }

    @Override
    @Transactional
    public Object[] findOnBillTransport() {
        List<BillEntity> list =new ArrayList<BillEntity>();

        try {
            StringBuffer sql1 = new StringBuffer("FROM BillEntity be WHERE be.status= 0 AND be.online= 1");
            Query query1 =  getSession().createQuery(sql1.toString());


            list=query1.list();

        }catch (HibernateException e) {
            log.error(e.getMessage(),e);
            throw e;
        }
        return new Object[]{list};

    }

    @Override
    @Transactional
    public Object[] findSumRevenueOn3Year(String date, String dayValue,String month) {
        Integer cost=0;
        try {
            StringBuffer sql1 = new StringBuffer(" SELECT sum(cost) FROM BillEntity WHERE "+date+"(datetime) ="+dayValue+" AND status=1 AND MONTH(datetime)="+month+"");
            Query query1 =  getSession().createQuery(sql1.toString());


            cost= ((Long) query1.uniqueResult()).intValue();

        }catch (Exception e) {

            log.error(e.getMessage(),e);
            return new Object[]{cost};
        }
        return new Object[]{cost};
    }

    @Override
    public Integer sumMoneyByIdBill(Integer idBill) {
        Integer cost=0;

        try {
            StringBuffer sql1 = new StringBuffer(" SELECT SUM(be.cost) FROM BillEntity be WHERE be.idBill="+idBill+"");
            Query query1 =  getSession().createQuery(sql1.toString());


            cost= ((Long) query1.uniqueResult()).intValue();

        }catch (Exception e) {

            log.error(e.getMessage(),e);

        }
        return cost;
    }

}