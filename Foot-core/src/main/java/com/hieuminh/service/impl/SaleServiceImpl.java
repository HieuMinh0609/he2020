package com.hieuminh.service.impl;


import com.hieuminh.converter.SaleConverter;
import com.hieuminh.dto.SaleDTO;
import com.hieuminh.entity.SaleEntity;
import com.hieuminh.repository.DetailSaleDao;
import com.hieuminh.repository.SaleDao;
import com.hieuminh.service.SaleService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("SaleService")
public class  SaleServiceImpl implements SaleService {

    @Autowired
    SaleDao saleDao;



    @Override
    public Object[] findByProberty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        Object[] objects= saleDao.findByProperty(property,sortExpression,sortDirection,offset,limit);
        List<SaleDTO> list = new ArrayList<>();
        for(SaleEntity saleEntity: (List<SaleEntity>) objects[1]){
            SaleDTO saleDTO = SaleConverter.entityToDto(saleEntity);
            list.add(saleDTO);
        }
        objects[1]=list;
        return objects;
    }

    @Override
    public SaleDTO findById(Integer idSale) {
        SaleEntity entity = saleDao.findById(idSale) ;
        SaleDTO dto = SaleConverter.entityToDto(entity);
        return  dto;
    }

    @Override
    public void saveSale(SaleDTO saleDTO) {
        SaleEntity entity = SaleConverter.dtoToEntity(saleDTO);
        saleDao.save(entity);
    }

    @Override
    public SaleDTO updateSale(SaleDTO saleDTO) {
        SaleEntity saleEntity = SaleConverter.dtoToEntity(saleDTO);
        saleEntity = saleDao.update(saleEntity);
        saleDTO = SaleConverter.entityToDto(saleEntity);
        return saleDTO;
    }

    @Override
    public Integer deleteSale(List<Integer> ids) throws HibernateException {
        Integer result = saleDao.delete(ids);
        return result;

    }

    @Override
    public List<SaleDTO> getSaleNow() {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        List<SaleEntity> entityList =saleDao.getSaleNow(date.format(formatter));
        List<SaleDTO> list = new ArrayList<>();
        for(SaleEntity saleEntity:  entityList){
            SaleDTO saleDTO = SaleConverter.entityToDto(saleEntity);
            list.add(saleDTO);
        }
       return list;


    }


}
