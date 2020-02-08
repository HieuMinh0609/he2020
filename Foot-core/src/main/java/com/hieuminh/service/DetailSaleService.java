package com.hieuminh.service;

import com.hieuminh.dto.DetailSaleDTO;
import org.hibernate.HibernateException;

import java.util.List;
import java.util.Map;

public interface DetailSaleService {
    DetailSaleDTO findEqualUnique(String property, Object value);
    List<DetailSaleDTO> findByPropertyNotLike(Map<String,Object> property);
    void deleteSaleDetail(String property,Integer value) throws HibernateException;
    Integer deleteDetailById(List<Integer> ids) throws HibernateException;
    List<DetailSaleDTO> findListById(String property, Object value);
    void saveDetailSale(DetailSaleDTO detailSaleDTO);
    Integer valideteDetailSale(String idSale,String idProduct);
    Integer checkInSale(Integer id);
    List<DetailSaleDTO> getIdProAndDown();
    List<DetailSaleDTO> getIdProAndDownForSale(Integer maxPage,Integer setFirstItem);
}
