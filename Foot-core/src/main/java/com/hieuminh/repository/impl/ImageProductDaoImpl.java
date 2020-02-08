package com.hieuminh.repository.impl;

import com.hieuminh.dto.ImageProductDTO;
import com.hieuminh.entity.ImageProductEntity;
import com.hieuminh.repository.ImageProductDao;
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

@Repository("ImageProductDao")
public class ImageProductDaoImpl extends AbstractDao<Integer, ImageProductEntity> implements ImageProductDao {

    private final Logger log = Logger.getLogger(this.getClass());
    @Autowired
    SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }


    @Override
    public List<ImageProductEntity> findListById(String property, Object value) {

        List<ImageProductEntity> list =new ArrayList<ImageProductEntity>();
            try{
                String sql = "FROM "+getSessionClassName()+" ip WHERE ip."+property +" =:value";
                Query query = getSession().createQuery(sql.toString());
                query.setParameter("value",value);

                list= query.list();

            }catch (HibernateException e){

                log.error(e.getMessage(),e);
                throw  e;
            }
            return list;
        }

    @Override
    public void deleteListImage(String property, Integer value) {
        try{
            String sql = "DELETE FROM ImageProductEntity model WHERE model."+property +" =:value";
            Query query = getSession().createQuery(sql.toString());
            query.setParameter("value",value);
            Integer result=query.executeUpdate();

        }catch (HibernateException e){

            log.error(e.getMessage(),e);
            throw  e;
        }
    }


}

