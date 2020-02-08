package com.hieuminh.repository.impl;

import com.hieuminh.entity.FeedBackEntity;
import com.hieuminh.repository.FeedBackDao;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("FeedBackDao")
public class FeedBackDaoImpl extends AbstractDao<Integer, FeedBackEntity> implements FeedBackDao {
    private final Logger log = Logger.getLogger(this.getClass());
    @Autowired
    SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }



    @Override
    @Transactional
    public void updateFeedBack() {
        Integer cost=0;
        try {
            StringBuffer sql1 = new StringBuffer("UPDATE FeedBackEntity s set s.check=0 WHERE s.check=1");
            Query query1 =  getSession().createQuery(sql1.toString());


            cost= query1.executeUpdate();

        }catch (Exception e) {

            log.error(e.getMessage(),e);

        }
    }
}
