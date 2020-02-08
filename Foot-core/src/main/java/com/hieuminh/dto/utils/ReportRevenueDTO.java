package com.hieuminh.dto.utils;

import com.hieuminh.dto.AbstractDTO;
import com.hieuminh.dto.TypeDTO;

public class ReportRevenueDTO extends AbstractDTO<ReportRevenueDTO> {

    private static final long serialVersionUID = 4342281211101099382L;

    private TypeDTO typeDTO;
    private Integer sumMoneyType;
    private Integer countType;
    private double percen;


    public TypeDTO getTypeDTO() {
        return typeDTO;
    }

    public void setTypeDTO(TypeDTO typeDTO) {
        this.typeDTO = typeDTO;
    }







    public double getPercen() {
        return percen;
    }

    public void setPercen(double percen) {
        this.percen = percen;
    }

    public Integer getSumMoneyType() {
        return sumMoneyType;
    }

    public void setSumMoneyType(Integer sumMoneyType) {
        this.sumMoneyType = sumMoneyType;
    }

    public Integer getCountType() {
        return countType;
    }

    public void setCountType(Integer countType) {
        this.countType = countType;
    }
}
