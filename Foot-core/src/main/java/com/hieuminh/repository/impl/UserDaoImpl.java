package com.hieuminh.repository.impl;

import com.hieuminh.dto.UserDTO;
import com.hieuminh.entity.MyHistoryEntity;
import com.hieuminh.entity.UserEntity;
import com.hieuminh.repository.UserDao;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

@Repository("UserDao")
public class UserDaoImpl extends AbstractDao<Integer,UserEntity> implements UserDao {
    private final Logger log = Logger.getLogger(this.getClass());

    @Autowired
    SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Object[] checkLogin(String NameLogin) {

        boolean isMemberExist = false;
        String roleName = null;
        Integer idMember = null;
        try {
            StringBuilder sql = new StringBuilder("FROM LoginEntity me WHERE me.nameLogin= :nameLogin");
            Query query = getSession().createQuery(sql.toString());
            query.setParameter("nameLogin", NameLogin);

            if (query.list().size() > 0) {
                isMemberExist = true;

            }
        } catch (HibernateException e) {

            throw e;
        }
            return new Object[]{isMemberExist};

    }

    @Override
    public Object[] listUserOnline() {

        List<UserEntity> list =new ArrayList<UserEntity>();

        try {
            StringBuffer sql1 = new StringBuffer("FROM UserEntity ue WHERE ue.check= :check ");
            Query query1 =  getSession().createQuery(sql1.toString());
            query1.setParameter("check", 1);

            list=query1.list();

        }catch (HibernateException e) {

            log.error(e.getMessage(),e);
            throw e;
        }
        return new Object[]{list};


    }
}
