package com.hieuminh.converter;

import com.hieuminh.dto.ConfigurationDTO;
import com.hieuminh.entity.ConfigurationEntity;

public class ConfigConverter {


    public static ConfigurationDTO entityToDto(ConfigurationEntity configurationEntity) {
        ConfigurationDTO configurationDTO = new ConfigurationDTO();
        configurationDTO.setKey(configurationEntity.getKey());
        configurationDTO.setType(configurationEntity.getType());
        configurationDTO.setName(configurationEntity.getName());
        configurationDTO.setValue(configurationEntity.getValue());

        return configurationDTO;
    }
    public static ConfigurationEntity dtoToEntity(ConfigurationDTO configurationDTO) {
        ConfigurationEntity configurationEntity = new ConfigurationEntity();
        configurationEntity.setKey(configurationDTO.getKey());
        configurationEntity.setType(configurationDTO.getType());
        configurationEntity.setName(configurationDTO.getName());
        configurationEntity.setValue(configurationDTO.getValue());

        return configurationEntity;
    }
}
