package com.hieuminh.repository.impl;

import com.hieuminh.entity.TransporterEntity;
import com.hieuminh.repository.TransporterDao;
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

@Repository("TransporterDao")
@Transactional
public class TransporterDaoImpl  extends AbstractDao<Integer, TransporterEntity> implements TransporterDao {

    private final Logger log = Logger.getLogger(this.getClass());

    @Autowired
    SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }


    @Override
    public List<Integer> getListIdBillOnDayByIdUser(Integer idUser,String day,String month,String year,String status) {
        List<Integer> list = new ArrayList<>();
        String sql;
        try{
           if(day.equals("") && month.equals("") && !year.equals("")){
               sql= "Select transBillEntity.idBill FROM TransporterEntity model WHERE model.transUserEntity.idUser="+idUser+" AND YEAR(model.timeStart) ="+year+" AND model.status="+status+"";
           }else if(day.equals("") && !month.equals("") && !year.equals("")){
               sql= "Select transBillEntity.idBill FROM TransporterEntity model WHERE model.transUserEntity.idUser="+idUser+" AND YEAR(model.timeStart) ="+year+" AND MONTH(model.timeStart) = "+month+" AND model.status="+status+"";
           }else{
               sql= "Select transBillEntity.idBill FROM TransporterEntity model WHERE model.transUserEntity.idUser="+idUser+" AND YEAR(model.timeStart) ="+year+" AND MONTH(model.timeStart) = "+month+" AND DAY(model.timeStart) = "+day+" AND model.status="+status+"";
           }
            Query query = getSession().createQuery(sql.toString());

            list= query.list();

        }catch (HibernateException e){

            log.error(e.getMessage(),e);
            throw  e;

        }


        return list;
    }


}
