package com.hieuminh.service.impl;

import com.hieuminh.converter.ProductConverter;
import com.hieuminh.dto.ImportProductDTO;
import com.hieuminh.dto.ProductDTO;
import com.hieuminh.entity.ProductEntity;
import com.hieuminh.entity.TypeEntity;
import com.hieuminh.repository.ProductDao;
import com.hieuminh.repository.TypeDao;
import com.hieuminh.service.ProductService;
import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service("ProductService")
@Transactional
 public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductDao productDao;
    @Autowired
    TypeDao typeDao;



    @Override

    public Object[] findByProberty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        Object[] objects= productDao.findByProperty(property,sortExpression,sortDirection,offset,limit);
        List<ProductDTO> list = new ArrayList<>();
        for(ProductEntity productEntity: (List<ProductEntity>) objects[1]){
            ProductDTO productDTO = ProductConverter.entityToDto(productEntity);
            list.add(productDTO);
        }
        objects[1]=list;
        return objects;
    }

    @Override
    public ProductDTO findById(Integer idProduct) {
        ProductEntity entity = productDao.findById(idProduct) ;
        ProductDTO dto = ProductConverter.entityToDto(entity);
        return  dto;
    }

    @Override
    public void SaveProduct(ProductDTO productDTO) {

        ProductEntity entity = ProductConverter.dtoToEntity(productDTO);
        productDao.save(entity);
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO) {
        ProductEntity productEntity = ProductConverter.dtoToEntity(productDTO);
        productEntity = productDao.update(productEntity);
        productDTO = ProductConverter.entityToDto(productEntity);
        return productDTO;

    }

    @Override
    public Integer deleteProduct(List<Integer> ids) throws HibernateException {
        Integer result = productDao.delete(ids);
        return result;
    }

    @Override
    public ProductDTO findEqualUnique(String property, Object value) {
        return null;
    }

    /*@Override
    public ProductDTO findOneByUserName(String userName) {
        return null;
    }
*/
    @Override
    public void ValidateImportProduct(List<ImportProductDTO> importProductDTOS) {
        List<String> names =new ArrayList<String>();
        List<String> roles = new ArrayList<String>();

        for(ImportProductDTO item:importProductDTOS){
            names.add(item.getNameProduct());
            if (!roles.contains(item.getNameProduct())){
                roles.add(item.getTypeProduct());
            }
        }

        Map<String,ProductEntity> productEntityHashMap = new HashMap<String, ProductEntity>();
        Map<String, TypeEntity> roleEntityMap = new HashMap<String, TypeEntity>();

        if(names.size()>0){
            List<ProductEntity> productEntities =  productDao.findByProduct(names);
            for (ProductEntity item:productEntities ){
                productEntityHashMap.put(item.getNameProduct().toUpperCase(),item);
            }
        }
        if (roles.size()>0){
            List<TypeEntity> typeEntities = typeDao.findByTypes(roles);
            for (TypeEntity item:typeEntities){
                roleEntityMap.put(item.getTypeName().toUpperCase(),item);
            }
        }

        for (ImportProductDTO item: importProductDTOS){
            String message = item.getError();
            if (item.isValid()){
                ProductEntity productEntity = productEntityHashMap.get(item.getNameProduct().toUpperCase());
                if(productEntity!=null){
                    message+="<br/>";
                    message+="duplicate name product";

                }
                TypeEntity typeEntity = roleEntityMap.get(item.getTypeProduct().toUpperCase());
                if(typeEntity==null){
                    message+="<br/>";
                    message+="not type Product";
                }
                if(StringUtils.isNotBlank(message)){
                    item.setValid(false);
                    item.setError(message.substring(5));
                }

            }
        }
    }
}
