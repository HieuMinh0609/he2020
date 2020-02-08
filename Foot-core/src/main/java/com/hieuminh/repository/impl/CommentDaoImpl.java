package com.hieuminh.repository.impl;

import com.hieuminh.dto.CommentDTO;
import com.hieuminh.entity.CommentEntity;
import com.hieuminh.repository.CommentDao;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository("CommentDao")
public class CommentDaoImpl extends AbstractDao<Integer, CommentEntity> implements CommentDao {
    private final Logger log = Logger.getLogger(this.getClass());
    @Autowired
    SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }



    @Override
    @Transactional
    public Integer getCountComment(String id) {
        Integer cost=0;

        try {
            StringBuffer sql1 = new StringBuffer(" SELECT COUNT(content) FROM CommentEntity ce WHERE  ce.idProductEntity.idProduct='"+id+"'");
            Query query1 =  getSession().createQuery(sql1.toString());


            cost= ((Long) query1.uniqueResult()).intValue();

        }catch (Exception e) {

            log.error(e.getMessage(),e);
            return cost;
        }
         return cost;


    }

    @Override
    @Transactional
    public Object[] findByPropertyAs(Integer id) {
        List<CommentEntity> list =new ArrayList<CommentEntity>();

        try {
            StringBuffer sql1 = new StringBuffer("FROM CommentEntity ce WHERE  ce.idProductEntity.idProduct="+id);
            Query query1 =  getSession().createQuery(sql1.toString());

            list = query1.list();


        }catch (Exception e) {

            log.error(e.getMessage(),e);
            return new Object[]{null};
        }
        return new Object[]{list};



    }

}
