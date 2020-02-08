package com.hieuminh.service.impl;

import com.hieuminh.converter.DetailSaleConverter;
import com.hieuminh.dto.DetailSaleDTO;
import com.hieuminh.entity.DetailSaleEntity;
import com.hieuminh.repository.DetailSaleDao;
import com.hieuminh.service.DetailSaleService;
import org.apache.commons.lang.time.DateUtils;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("DetailSaleService")
public class DetailSaleServiceImpl implements DetailSaleService  {

    @Autowired
    DetailSaleDao detailSaleDao;

    @Override
    public DetailSaleDTO findEqualUnique(String property, Object value) {

        DetailSaleEntity entity = detailSaleDao.findEqualUnique(property,value);
        DetailSaleDTO detailSaleDTO = DetailSaleConverter.entityToDto(entity);

        return detailSaleDTO;
    }

    @Override
    public  List<DetailSaleDTO> findByPropertyNotLike(Map<String, Object> property) {

        List<DetailSaleDTO> detailSaleDTOS=new ArrayList<DetailSaleDTO>();
        Object[] objects = detailSaleDao.findByPropertyNotLike(property);
        for(DetailSaleEntity item:(List<DetailSaleEntity>)objects[0]){
            DetailSaleDTO detailSaleDTO = DetailSaleConverter.entityToDto(item);
            detailSaleDTOS.add(detailSaleDTO);
        }
        return detailSaleDTOS;
    }

    @Override
    public void deleteSaleDetail(String property, Integer value) throws HibernateException {
         detailSaleDao.deleteDetailSale(property,value);

    }

    @Override
    public Integer deleteDetailById(List<Integer> ids) throws HibernateException {
        Integer result  = detailSaleDao.delete(ids);
         return result;
    }

    @Override
    public List<DetailSaleDTO> findListById(String property, Object value) {
        List<DetailSaleEntity> entity = detailSaleDao.findListById(property,value);
        List<DetailSaleDTO>  detailSaleDTOS = new ArrayList<>();
        for(DetailSaleEntity item:entity){
            detailSaleDTOS.add(DetailSaleConverter.entityToDto(item));
        }
        return  detailSaleDTOS;
    }

    @Override
    public void saveDetailSale(DetailSaleDTO detailSaleDTO) {
        DetailSaleEntity entity = DetailSaleConverter.dtoToEntity(detailSaleDTO);
        detailSaleDao.save(entity);
    }

    @Override
    public Integer valideteDetailSale(String idSale, String idProduct) {
        return detailSaleDao.valideteDetailSale(idSale,idProduct);
    }

    @Override
    public Integer checkInSale(Integer id) {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Integer idPro = detailSaleDao.checkInSale(id,""+ date.format(formatter));

        return idPro;

    }

    @Override
    public List<DetailSaleDTO> getIdProAndDownForSale(Integer maxPage, Integer setFirstItem) {
        List<DetailSaleDTO> list = new ArrayList<>();
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        List<DetailSaleEntity> saleEntities=detailSaleDao.getIdProAndDownForSale(""+ date.format(formatter),maxPage, setFirstItem);

        for(DetailSaleEntity item:(List<DetailSaleEntity>)saleEntities ){
            DetailSaleDTO detailSaleDTO = DetailSaleConverter.entityToDto(item);
            list.add(detailSaleDTO);
        }


        return list;
    }

    @Override
    public  List<DetailSaleDTO> getIdProAndDown() {
        List<DetailSaleDTO> list = new ArrayList<>();
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        List<DetailSaleEntity> saleEntities=detailSaleDao.getIdProAndDown(""+ date.format(formatter));

        for(DetailSaleEntity item:(List<DetailSaleEntity>)saleEntities ){
            DetailSaleDTO detailSaleDTO = DetailSaleConverter.entityToDto(item);
            list.add(detailSaleDTO);
        }


        return list;
    }


}
