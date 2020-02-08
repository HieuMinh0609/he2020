package com.hieuminh.service;

import com.hieuminh.dto.SaleDTO;
import com.hieuminh.entity.SaleEntity;
import org.hibernate.HibernateException;

import java.util.List;
import java.util.Map;

public interface SaleService {
    Object[] findByProberty(Map<String,Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit);
    SaleDTO findById(Integer idSale);
    void saveSale(SaleDTO saleDTO);
    SaleDTO updateSale(SaleDTO saleDTO);
    Integer deleteSale(List<Integer> ids) throws HibernateException;
    List<SaleDTO> getSaleNow();

}
