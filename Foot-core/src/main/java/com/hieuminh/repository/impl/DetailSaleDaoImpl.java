package com.hieuminh.repository.impl;

import com.hieuminh.dto.DetailSaleDTO;
import com.hieuminh.entity.DetailSaleEntity;
import com.hieuminh.repository.DetailSaleDao;
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

@Repository("DetailSaleDao")
@Transactional
public class DetailSaleDaoImpl extends AbstractDao<Integer, DetailSaleEntity> implements DetailSaleDao {

    private final Logger log = Logger.getLogger(this.getClass());

    @Autowired
    SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void deleteDetailSale(String property, Integer value) {
        try{
            String sql = "DELETE FROM DetailSaleEntity model WHERE model."+property +" =:value";
            Query query = getSession().createQuery(sql.toString());
            query.setParameter("value",value);
            Integer result=query.executeUpdate();

        }catch (HibernateException e){

            log.error(e.getMessage(),e);
            throw  e;
        }
    }

    @Override
    public List<DetailSaleEntity> findListById(String property, Object value) {


        List<DetailSaleEntity> list = new ArrayList<DetailSaleEntity>();
        try{
            String sql = "FROM DetailSaleEntity  ip WHERE ip."+property +" =:value";
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
    public Integer valideteDetailSale(String idSale, String idProduct) {
        Integer count=0;
        try{
            String sql = "SELECT COUNT(*) FROM DetailSaleEntity  ip WHERE ip.idSaleEntity.idSale=:idSaleValue  and ip.idProductSaleEntity.idProduct=:idProductValue";
            Query query = getSession().createQuery(sql.toString());
            query.setParameter("idSaleValue",Integer.parseInt(idSale));
            query.setParameter("idProductValue",Integer.parseInt(idProduct));

            count=  ((Long)query.uniqueResult()).intValue();

        }catch (HibernateException e){

            log.error(e.getMessage(),e);
            throw  e;
        }

         return count;
    }

    @Override
    public Integer checkInSale(Integer id,String nowDate) {
        Integer count=0;
        try{
            String sql = "SELECT e.idProductSaleEntity.idProduct  FROM DetailSaleEntity  e join e.idSaleEntity se  WHERE e.idProductSaleEntity.idProduct='"+id+"' AND DATE(se.timeStart) <= DATE('"+nowDate+"') AND DATE(se.timeEnd) >= DATE('"+nowDate+"')";

            Query query = getSession().createQuery(sql.toString());

            count = Integer.parseInt(query.uniqueResult().toString());

        }catch (Exception e){

            log.error(e.getMessage(),e);
            return count;
        }

        return count;
    }

    @Override
    public List<DetailSaleEntity> getIdProAndDownForSale(String nowDate, Integer maxPage, Integer setFirstItem) {
        List<DetailSaleEntity> list = new ArrayList<DetailSaleEntity>();
        try{
            String sql = "  FROM DetailSaleEntity  e WHERE  DATE(e.idSaleEntity.timeStart) <= DATE('"+nowDate+"') AND DATE(e.idSaleEntity.timeEnd) >= DATE('"+nowDate+"')";
            Query query = getSession().createQuery(sql.toString());
            query.setFirstResult(setFirstItem);
            query.setMaxResults(maxPage);
            list = query.list();

        }catch (Exception e){

            log.error(e.getMessage(),e);
            return list;
        }

        return list;
    }

    @Override
    public List<DetailSaleEntity> getIdProAndDown(String nowDate) {
        List<DetailSaleEntity> list = new ArrayList<DetailSaleEntity>();
        try{
            String sql = "  FROM DetailSaleEntity  e WHERE  DATE(e.idSaleEntity.timeStart) <= DATE('"+nowDate+"') AND DATE(e.idSaleEntity.timeEnd) >= DATE('"+nowDate+"')";

            Query query = getSession().createQuery(sql.toString());

           /* id = query.list();

            String sql2 = "SELECT e.idProductSaleEntity.idProduct  FROM DetailSaleEntity  e join e.idSaleEntity se  WHERE e.idProductSaleEntity.idProduct='"+id+"' AND DATE(se.timeStart) <= DATE('"+nowDate+"') AND DATE(se.timeEnd) >= DATE('"+nowDate+"')";

            Query query2 = getSession().createQuery(sql.toString());
*/
           list = query.list();

        }catch (Exception e){

            log.error(e.getMessage(),e);
            return list;
        }

        return list;
    }
}
