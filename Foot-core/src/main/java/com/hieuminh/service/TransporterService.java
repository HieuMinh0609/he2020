package com.hieuminh.service;

import com.hieuminh.dto.TransporterDTO;

import java.util.List;
import java.util.Map;

public interface TransporterService {
    Object[] findByProberty(Map<String,Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit);
    Object[] findByPropertyMapNotLike(Map<String,Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit);
    List<Integer> getListIdBillOnDayByIdUser(Integer id,String day,String month,String year,String status);
    void SaveTransporter(TransporterDTO transporterDTO);
    TransporterDTO findEqualUnique(String property, Object value);
    TransporterDTO updateTransporter(TransporterDTO transporterDTO);
}
