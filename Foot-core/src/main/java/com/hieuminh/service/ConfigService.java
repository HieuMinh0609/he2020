package com.hieuminh.service;

import com.hieuminh.dto.ConfigurationDTO;

import java.util.Map;

public interface ConfigService {
    Object[] findByProberty(Map<String,Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit);
    ConfigurationDTO findEqualUnique(String property, Object value);
    ConfigurationDTO update(ConfigurationDTO configurationDTO);
}
