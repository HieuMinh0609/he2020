package com.hieuminh.repository.impl;

import com.hieuminh.entity.ProductEntity;
import com.hieuminh.repository.ProductDao;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Repository("ProductDao")
public class ProductDaoImpl extends AbstractDao<Integer, ProductEntity> implements ProductDao {
    @Autowired
    SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    @Transactional
    public List<ProductEntity> findByProduct(List<String> name) {


            List<ProductEntity> productEntities = new ArrayList<ProductEntity>();
            try{
                StringBuffer sql = new StringBuffer("FROM ProductEntity me WHERE me.nameProduct IN(:name) ");
                Query query = getSession().createQuery(sql.toString());
                query.setParameterList("name",name);
                productEntities =query.list();
            }catch (HibernateException e){

                throw e;
            }
            return productEntities;
        }


}
