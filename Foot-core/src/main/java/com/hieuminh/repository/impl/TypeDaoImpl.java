package com.hieuminh.repository.impl;

import com.hieuminh.entity.ProductEntity;
import com.hieuminh.entity.TypeEntity;
import com.hieuminh.repository.TypeDao;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository("TypeDao")
public class TypeDaoImpl extends AbstractDao<Integer, TypeEntity> implements TypeDao {
    @Autowired
    SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    @Transactional
    public List<TypeEntity> findByTypes(List<String> types) {

        List<TypeEntity> typeEntities = new ArrayList<TypeEntity>();
        try{
            StringBuffer sql = new StringBuffer("FROM TypeEntity me WHERE me.typeName IN(:types) ");
            Query query = getSession().createQuery(sql.toString());
            query.setParameterList("types",types);
            typeEntities =query.list();
        }catch (HibernateException e){

            throw e;
        }
        return typeEntities;
    }
}

