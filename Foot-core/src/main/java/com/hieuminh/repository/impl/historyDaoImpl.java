package com.hieuminh.repository.impl;

import com.hieuminh.constant.CoreConstant;
import com.hieuminh.entity.MyHistoryEntity;
import com.hieuminh.repository.HistoryDao;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository("HistoryDao")
public class historyDaoImpl extends AbstractDao<Integer, MyHistoryEntity> implements HistoryDao {
    private final Logger log = Logger.getLogger(this.getClass());

    @Autowired
    SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Object[] findByHistory(Integer offset, Integer limit) {
        List<MyHistoryEntity> list =new ArrayList<MyHistoryEntity>();

        try {
            StringBuffer sql1 = new StringBuffer("FROM MyHistoryEntity ORDER BY dateTime DESC");


            Query query1 =  getSession().createQuery(sql1.toString());

            if (offset!=null && offset>=0) {
                query1.setFirstResult(offset);
            }
            if (limit!=null && limit>=0) {
                query1.setMaxResults(limit);
            }
            list=query1.list();

        }catch (HibernateException e) {

            log.error(e.getMessage(),e);
            throw e;
        }
        return new Object[]{list};
    }
}
