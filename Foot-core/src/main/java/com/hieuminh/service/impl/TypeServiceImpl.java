package com.hieuminh.service.impl;

import com.hieuminh.converter.TypeConverter;
import com.hieuminh.dto.TypeDTO;
import com.hieuminh.entity.TypeEntity;
import com.hieuminh.repository.TypeDao;
import com.hieuminh.service.TypeService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service("TypeService")
@Transactional
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeDao typeDao;


    @Override
    public Map<String, String> getTypes() {
        Map<String,String> typeTerm = new HashMap<>();
        List<TypeEntity> typeEntities = typeDao.findAll();
        typeEntities.forEach(entity ->{
            TypeDTO typeDTO = TypeConverter.entityToDto(entity);
            typeTerm.put(String.valueOf(typeDTO.getTypeId()), typeDTO.getTypeName());
        });
        return typeTerm;
    }

    @Override
    public List<TypeDTO> findAll() {
        List<TypeEntity> entity = typeDao.findAll() ;
        List<TypeDTO>  typeDTOS = new ArrayList<>();
        for(TypeEntity item:entity){
            typeDTOS.add(TypeConverter.entityToDto(item));
        }
        return  typeDTOS;
    }

    @Override
    public TypeDTO findEqualUnique(String property, Object value) {

        TypeEntity entity = typeDao.findEqualUnique(property,value);
        TypeDTO typeDTO = TypeConverter.entityToDto(entity);

        return typeDTO;
    }

    @Override
    public Object[] findByProberty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {
             Object[] objects= typeDao.findByProperty(property,sortExpression,sortDirection,offset,limit);
            List<TypeDTO> list = new ArrayList<>();
            for(TypeEntity productEntity: (List<TypeEntity>) objects[1]){
                TypeDTO typeDTO = TypeConverter.entityToDto(productEntity);
                list.add(typeDTO);
            }
            objects[1]=list;
            return objects;
        }

    @Override
    public TypeDTO findById(Integer id) {
        TypeEntity entity = typeDao.findById(id) ;
        TypeDTO dto = TypeConverter.entityToDto(entity);
        return  dto;
    }

    @Override
    public TypeDTO updateType(TypeDTO typeDTO) {
        TypeEntity typeEntity = TypeConverter.dtoToEntity(typeDTO);
        typeEntity = typeDao.update(typeEntity);
        typeDTO = TypeConverter.entityToDto(typeEntity);
        return typeDTO;
    }

    @Override
    public void SaveType(TypeDTO typeDTO) {

        TypeEntity entity = TypeConverter.dtoToEntity(typeDTO);
        typeDao.save(entity);
    }

    @Override
    public Integer deleteType(List<Integer> ids) throws HibernateException {
        Integer result = typeDao.delete(ids);
        return result;

    }


}
