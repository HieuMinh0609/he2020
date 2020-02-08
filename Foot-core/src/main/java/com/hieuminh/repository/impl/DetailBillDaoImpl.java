package com.hieuminh.repository.impl;

import com.hieuminh.dto.utils.DateDTO;
import com.hieuminh.entity.DetailBillEntity;
import com.hieuminh.entity.ImageProductEntity;
import com.hieuminh.entity.TransporterEntity;
import com.hieuminh.repository.DetailBillDao;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository("DetailBillDao")
public class DetailBillDaoImpl  extends AbstractDao<Integer, DetailBillEntity> implements DetailBillDao {
    private final Logger log = Logger.getLogger(this.getClass());
    @Override
    public Object[] findSumRevenueOnType(String TypeId,String Date) {
        Integer cost=0;

        try {
            StringBuffer sql1 = new StringBuffer(" SELECT SUM(be.count) FROM DetailBillEntity be" +
                    " WHERE  be.idDBillProductEntity.typeIdEntity.typeId ="+TypeId+" AND  MONTH(be.idDetailBillBillEntity.dateTime) =   MONTH('"+Date+"')" +
                    " AND YEAR(be.idDetailBillBillEntity.dateTime) =   YEAR('"+Date+"') AND be.idDetailBillBillEntity.status = 1");
            Query query1 =  getSession().createQuery(sql1.toString());


            cost= ((Long) query1.uniqueResult()).intValue();

        }catch (Exception e) {

            log.error(e.getMessage(),e);
            return new Object[]{cost};
        }
        if(cost==null){
            cost=0;
        }
        return new Object[]{cost};

    }

    @Override
    public Object[] findSumRevenueOnTypeOnYear(String TypeId, String date, String typeDate) {
        Integer cost=0;

        try {
            StringBuffer sql1 = new StringBuffer(" SELECT SUM(be.count) FROM DetailBillEntity " +
                    "be WHERE  be.idDBillProductEntity.typeIdEntity.typeId ="+TypeId+" AND "+typeDate+"(be.idDetailBillBillEntity.dateTime) = "+date+" AND be.idDetailBillBillEntity.status = 1");
            Query query1 =  getSession().createQuery(sql1.toString());


            cost= ((Long) query1.uniqueResult()).intValue();

        }catch (Exception e) {

            log.error(e.getMessage(),e);
            return new Object[]{cost};
        }
        if(cost==null){
            cost=0;
        }
        return new Object[]{cost};
    }

    @Override
    @Transactional
    public Object[] findCostOnProperty(Map<String, Object> property) {

        Integer cost=0;

        String[] params = new String[property.size()];
        Object[] values = new Object[property.size()];
        int i=0;
        for(Map.Entry items:property.entrySet()){
            params[i] = (String) items.getKey();
            values[i] = (Object) items.getValue();
            i++;
        }
        try {
            StringBuffer sql1 = new StringBuffer("SELECT sum(idDetailBillBillEntity.cost) FROM ");
            sql1.append("DetailBillEntity ").append(" WHERE  1=1");
            if (property.size()>0) {
                for(int i1=0;i1<params.length;i1++){
                    sql1.append(" AND ").append(params[i1]).append("="+values[i1]);
                }
            }


            Query query1 = getSession().createQuery(sql1.toString());


            cost= ((Long) query1.uniqueResult()).intValue();

        }catch (Exception e) {
            log.error(e.getMessage(),e);

            return new Object[]{cost};

        }
        return new Object[]{cost};
    }

    @Override
    public Integer getSumProductByIdBill(Integer idBill) {
        Integer cost=0;

        try {
            StringBuffer sql1 = new StringBuffer(" SELECT SUM(be.count) FROM DetailBillEntity be WHERE idDetailBillBillEntity.idBill="+idBill+"");
            Query query1 =  getSession().createQuery(sql1.toString());


            cost= ((Long) query1.uniqueResult()).intValue();

        }catch (Exception e) {

            log.error(e.getMessage(),e);

        }
       return cost;
    }

    @Override
    public List<DetailBillEntity> findListByDate(Integer typeid, DateDTO dateDTO,Integer idBill) {
        List<DetailBillEntity> entitys =  new ArrayList<>();
        String sql=null;

        try{
            if(typeid!=null) {

                    sql = "FROM DetailBillEntity de WHERE de.idDBillProductEntity.typeIdEntity.typeId=" + typeid + " AND de.idDetailBillBillEntity.idBill =" + idBill + "";

            }else if (typeid==null){

                    sql = "FROM DetailBillEntity de WHERE de.idDetailBillBillEntity.idBill =" + idBill + "";

            }
            Query query = getSession().createQuery(sql.toString());

            entitys= (List<DetailBillEntity>) query.list();

        }catch (HibernateException e){

            log.error(e.getMessage(),e);

            return null;

        }



        return entitys;
    }

    @Override
    public  List<DetailBillEntity>  findListByIdBill(Integer idBill) {

        List<DetailBillEntity> list =new ArrayList<DetailBillEntity>();
        try{




                String sql = "FROM "+getSessionClassName()+" ip WHERE ip.idDetailBillBillEntity.idBill "+" =:value";
                Query query = getSession().createQuery(sql.toString());
                query.setParameter("value",idBill);



                list = query.list();



        }catch (HibernateException e){

            log.error(e.getMessage(),e);
            throw  e;
        }
        return list;


    }


}
