package com.hieuminh.dto.utils;

import com.hieuminh.dto.AbstractDTO;
import com.hieuminh.dto.ProductDTO;
import com.hieuminh.dto.StoreDTO;

import java.sql.Timestamp;

public class ReportDTO  extends AbstractDTO<ReportDTO> {
    private static final long serialVersionUID = 5830881211101027382L;


    private StoreDTO storeDTO;
    private ProductDTO productDTO;
    private String dateTime;
    private Integer countProduct;



    public StoreDTO getStoreDTO() {
        return storeDTO;
    }

    public void setStoreDTO(StoreDTO storeDTO) {
        this.storeDTO = storeDTO;
    }

    public ProductDTO getProductDTO() {
        return productDTO;
    }

    public void setProductDTO(ProductDTO productDTO) {
        this.productDTO = productDTO;
    }

    public Integer getCountProduct() {
        return countProduct;
    }

    public void setCountProduct(Integer countProduct) {
        this.countProduct = countProduct;
    }


    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
