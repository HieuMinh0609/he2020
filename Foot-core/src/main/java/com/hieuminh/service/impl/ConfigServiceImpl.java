package com.hieuminh.service.impl;

import com.hieuminh.converter.ConfigConverter;
import com.hieuminh.converter.LocationConverter;
import com.hieuminh.dto.ConfigurationDTO;
import com.hieuminh.dto.LocationDTO;
import com.hieuminh.entity.ConfigurationEntity;
import com.hieuminh.entity.LocationEntity;
import com.hieuminh.repository.ConfigurationDao;
import com.hieuminh.repository.LocationDao;
import com.hieuminh.service.ConfigService;
import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("ConfigService")
public class ConfigServiceImpl implements ConfigService {
    @Autowired
    ConfigurationDao configurationDao;
    @Override
    public Object[] findByProberty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        Object[] objects= configurationDao.findByProperty(property,sortExpression,sortDirection,offset,limit);
        List<ConfigurationDTO> list = new ArrayList<>();
        for(ConfigurationEntity configurationEntity: (List<ConfigurationEntity>) objects[1]){
            ConfigurationDTO configurationDTO = ConfigConverter.entityToDto(configurationEntity);
            list.add(configurationDTO);
        }
        objects[1]=list;
        return objects;
    }

    @Override
    public ConfigurationDTO findEqualUnique(String property, Object value) {
        try{
            ConfigurationEntity configurationEntity = configurationDao.findEqualUnique(property,value);
            ConfigurationDTO configurationDTO = ConfigConverter.entityToDto(configurationEntity);

            return configurationDTO;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public ConfigurationDTO update(ConfigurationDTO configurationDTO) {

        ConfigurationEntity configurationEntity = ConfigConverter.dtoToEntity(configurationDTO);
        configurationEntity = configurationDao.update(configurationEntity);
        configurationDTO = ConfigConverter.entityToDto(configurationEntity);
        return configurationDTO;

    }
}
