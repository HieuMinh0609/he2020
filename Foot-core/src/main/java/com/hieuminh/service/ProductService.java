package com.hieuminh.service;

import com.hieuminh.dto.ImportProductDTO;
import com.hieuminh.dto.ProductDTO;
import org.hibernate.HibernateException;

import java.util.List;
import java.util.Map;

public interface ProductService {
    Object[] findByProberty(Map<String,Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit);
    ProductDTO findById(Integer idProduct);
    void SaveProduct(ProductDTO productDTO);
    ProductDTO updateProduct(ProductDTO productDTO);
    Integer deleteProduct(List<Integer> ids) throws HibernateException;
    ProductDTO findEqualUnique(String property, Object value);
    //ProductDTO findOneByUserName(String userName);
    void ValidateImportProduct(List<ImportProductDTO> importProductDTOS);
 }
