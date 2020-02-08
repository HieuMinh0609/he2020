package com.hieuminh.repository.impl;

import com.hieuminh.constant.CoreConstant;
import com.hieuminh.repository.GenericDao;
import org.apache.log4j.Logger;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.*;
import java.util.Map;

@SuppressWarnings("unchecked")
public class AbstractDao<ID extends Serializable, T> implements GenericDao<ID ,T>{
    private final Logger log = Logger.getLogger(this.getClass());

    @Autowired
    SessionFactory sessionFactory;

    protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    private Class <T> persistenceClass;


    public AbstractDao() {
        this.persistenceClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }


    public String getSessionClassName() {
        return persistenceClass.getSimpleName();
    }


    @Override
    public List<T> findAll() {
        List<T> list = new ArrayList<T>();
        try{
            StringBuffer sql = new StringBuffer("from ");
            sql.append(this.getSessionClassName());
            Query query = getSession().createQuery(sql.toString());
            list = query.list();

        }catch (HibernateException e){

            log.error(e.getMessage(),e);
            System.out.println("Problem creating session factory");
            throw e;
        }
        return list;
    }

    @Override
    @Transactional
    public T update(T entity) {
        T result=null;
        try {
            Object object = getSession().merge(entity);
            result = (T) object;
        }catch (HibernateException e) {
            log.error(e.getMessage(),e);
            throw e;
        }
        return result;
    }

    @Override
    public T updateList(String proprety, Object value, String where, Object valuewhere) {
        return null;
    }

    @Override
    @Transactional
    public T save(T entity) {

        try{
            getSession().persist(entity);

        }catch (HibernateException e){

            log.error(e.getMessage(), e);
            throw e;
        }
        return entity;
    }

    @Override
    @Transactional
    public T findById(ID id) {
        T result=null;
        try {
            result =  (T)  getSession().get(persistenceClass,id);
            if(result==null) {
                throw new ObjectNotFoundException(" NOT FOUND " + id,null);
            }
        }catch (HibernateException e) {

            log.error(e.getMessage(),e);
            throw e;
        }
        return result;
    }

    @Override
    @Transactional
    public Object[] findByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        List<T> list =new ArrayList<T>();
        Object totalItem = 0;
        String[] params = new String[property.size()];
        Object[] values = new Object[property.size()];
        int i=0;
        for(Map.Entry items:property.entrySet()){
            params[i] = (String) items.getKey();
            values[i] = (Object) items.getValue();
            i++;
        }
        try {
            StringBuffer sql1 = new StringBuffer("from ");
            sql1.append(getSessionClassName()).append(" where 1=1 ");
            if (property.size()>0) {
                for(int i1=0;i1<params.length;i1++){
                    sql1.append(" and ").append(params[i1]).append(" LIKE '%' ||  :"+params[i1]+" ||  '%'");
                }
            }
          /*  if (Property!=null && value!=null) {
                sql1.append(" where ").append(Property).append( "= :value");
            }*/
            if (sortExpression!=null && sortDirection!=null) {
                sql1.append(" order by ").append(sortExpression);
                sql1.append(" "+(sortDirection.equals(CoreConstant.SORT_ASC)?" asc ":" desc"));
            }
            Query query1 =  getSession().createQuery(sql1.toString());

           /* if (value!=null) {
                query1.setParameter("value",value);
            }*/
            if (property.size()>0) {
                for(int i2=0;i2<params.length;i2++){
                    query1.setParameter(params[i2],values[i2]);
                }

            }
            if (offset!=null && offset>=0) {
                query1.setFirstResult(offset);
            }
            if (limit!=null && limit>=0) {
                query1.setMaxResults(limit);
            }
            list=query1.list();
            StringBuffer sql2 = new StringBuffer("select count(*) from ");
            sql2.append(getSessionClassName()).append(" where 1=1 ");
            if (property.size()>0) {
                for (int k1 = 0; k1 < params.length; k1++) {
                    sql2.append(" and ").append(params[k1]).append(" LIKE '%' ||  :" + params[k1] + " ||  '%'");

                }
            }
           /* if (Property!=null && value!=null) {
                sql2.append(" where ").append(Property).append("= :value");
            }*/
            Query query2 =  getSession().createQuery(sql2.toString());
            if (property.size() > 0) {
                for (int k2 = 0; k2 < params.length; k2++) {
                    query2.setParameter(params[k2], values[k2]);
                }
            }
           /* if ( value!=null){
                query2.setParameter("value",value);
            }*/
            totalItem = query2.list().get(0);

        }catch (HibernateException e) {
            list = null;
            log.error(e.getMessage(),e);
            throw e;
        }
        return new Object[]{totalItem,list};
    }

    @Override
    @Transactional
    public Object[] findByPropertyMapNotLike(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        List<T> list =new ArrayList<T>();
        Object totalItem = 0;
        String[] params = new String[property.size()];
        Object[] values = new Object[property.size()];
        int i=0;
        for(Map.Entry items:property.entrySet()){
            params[i] = (String) items.getKey();
            values[i] = (Object) items.getValue();
            i++;
        }
        try {
            StringBuffer sql1 = new StringBuffer("FROM ");
            sql1.append(getSessionClassName()).append(" WHERE 1=1 ");
            if (property.size()>0) {
                for(int i1=0;i1<params.length;i1++){
                    sql1.append(" AND ").append(" "+params[i1]).append(" =:value"+i1+"");
                }
            }
          /*  if (Property!=null && value!=null) {
                sql1.append(" where ").append(Property).append( "= :value");
            }*/
            if (sortExpression!=null && sortDirection!=null) {
                sql1.append(" order by ").append(sortExpression);
                sql1.append(" "+(sortDirection.equals(CoreConstant.SORT_ASC)?" asc ":" desc"));
            }
            Query query1 =  getSession().createQuery(sql1.toString());

           /* if (value!=null) {
                query1.setParameter("value",value);
            }*/
            if (property.size()>0) {
                for(int i2=0;i2<params.length;i2++){
                    query1.setParameter("value"+i2,values[i2]);
                }

            }
            if (offset!=null && offset>=0) {
                query1.setFirstResult(offset);
            }
            if (limit!=null && limit>=0) {
                query1.setMaxResults(limit);
            }
            list=query1.list();
            StringBuffer sql2 = new StringBuffer("select count(*) from ");
            sql2.append(getSessionClassName()).append("  where 1=1 ");
            if (property.size()>0) {
                for (int k1 = 0; k1 < params.length; k1++) {
                    sql2.append(" and ").append(" "+params[k1]).append(" =:value"+k1+ "");

                }
            }
           /* if (Property!=null && value!=null) {
                sql2.append(" where ").append(Property).append("= :value");
            }*/
            Query query2 =  getSession().createQuery(sql2.toString());
            if (property.size() > 0) {
                for (int k2 = 0; k2 < params.length; k2++) {
                    query2.setParameter("value"+k2, values[k2]);
                }
            }
           /* if ( value!=null){
                query2.setParameter("value",value);
            }*/
            totalItem = query2.list().get(0);

        }catch (HibernateException e) {

            log.error(e.getMessage(),e);
            throw e;
        }
        return new Object[]{totalItem,list};
    }

    @Override
    @Transactional
    public Integer delete(List<ID> ids) {
        Integer count = 0 ;
        try {
            for (ID item: ids) {
                T t = (T) getSession().get(persistenceClass,item);
                getSession().delete(t);
                count++;
            }
        }catch (HibernateException e ) {

            log.error(e.getMessage(),e);
            throw e;
        }
        return count;
    }

    @Override
    @Transactional
    public T findEqualUnique(String property, Object value) {
        T result = null;
        try{
            String sql = "FROM "+getSessionClassName()+" model WHERE model."+property +" =:value";
            Query query = getSession().createQuery(sql.toString());
            query.setParameter("value",value);
            result = (T) query.uniqueResult();

        }catch (HibernateException e){

            log.error(e.getMessage(),e);
            throw  e;
        }
        return result;
    }

    @Override
    @Transactional
    public Object[] findByPropertyNotLike(Map<String, Object> property) {
        List<T> list =new ArrayList<T>();

        String[] params = new String[property.size()];
        Object[] values = new Object[property.size()];
        int i=0;
        for(Map.Entry items:property.entrySet()){
            params[i] = (String) items.getKey();
            values[i] = (Object) items.getValue();
            i++;
        }
        try {
            StringBuffer sql1 = new StringBuffer("FROM ");
            sql1.append(getSessionClassName()).append(" model WHERE 1=1 ");
            if (property.size()>0) {
                for(int i1=0;i1<params.length;i1++){
                    sql1.append(" and model.").append(params[i1]).append(" =:"+params[i1]);
                }
            }


            Query query1 = getSession().createQuery(sql1.toString());
            if (property.size()>0) {
                for(int i2=0;i2<params.length;i2++){
                    query1.setParameter(params[i2],values[i2]);
                }
            }
            list=query1.list();
        }catch (HibernateException e) {

            log.error(e.getMessage(),e);
            return new Object[]{null};
        }
        return new Object[]{list};
    }
}
