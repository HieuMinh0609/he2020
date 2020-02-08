package com.hieuminh.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by MERCYMAGIE on 3/1/2019.
 */
public interface GenericDao<ID extends Serializable, T> {
    List<T> findAll();
    T update(T entity);
    T updateList(String proprety, Object value, String where, Object valuewhere);
    T save(T entity);
    T findById(ID id);
    Object[] findByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit);
    Integer delete(List<ID> ids);
    T findEqualUnique(String property, Object value);
    Object[] findByPropertyNotLike(Map<String, Object> property);
    Object[] findByPropertyMapNotLike(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit);


}
