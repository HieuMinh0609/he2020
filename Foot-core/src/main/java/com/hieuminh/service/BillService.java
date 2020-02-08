package com.hieuminh.service;

import com.hieuminh.dto.BillDTO;
import com.hieuminh.dto.ProductDTO;
import org.hibernate.HibernateException;

import java.util.List;
import java.util.Map;

public interface BillService {
    Object[] findByProberty(Map<String,Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit);
    BillDTO findById(Integer idBill);
    BillDTO updateBill(BillDTO billDTO);
    void SaveBill(BillDTO billDTO);
    Integer deleteBill(List<Integer> ids) throws HibernateException;
    List<BillDTO> findListBillOnNew();
    Integer findSumRevenue(String property,String day,String month,String year);
    List<BillDTO> findOnBillTransport();
    List<Integer> findRevenue7Day();
    List<Integer> findSumRevenueOn3Year();
    Object[] findByPropertyMapNotLike(Map<String,Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit);
    Integer sumMoneyByIdBill(List<Integer> idBill);
}
