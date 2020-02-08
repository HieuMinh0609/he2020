package com.hieuminh.service;

import com.hieuminh.dto.BillDTO;
import com.hieuminh.dto.DetailBillDTO;
import com.hieuminh.dto.DetailSaleDTO;
import com.hieuminh.dto.TransporterDTO;
import com.hieuminh.dto.utils.DateDTO;

import java.util.List;
import java.util.Map;

public interface DetailBillService {
    void SaveDetailBill(DetailBillDTO detailBillDTO);
    List<DetailBillDTO> findByPropertyMapNotLike(Map<String,Object> property);
    List<Integer> findSumRevenueOnType();
    List<Integer> findSumRevenueOnYear();
    List<Integer> findCostOnProperty();
    Integer  getSumProductByIdBill(List<Integer> listIdBill);
    List<DetailBillDTO> findListByIdBill(Integer idBill);
    List<DetailBillDTO>  findListByDate(Integer typeid, DateDTO dateDTO,List<TransporterDTO> transporterDTOList);
}
