package com.hieuminh.dto;

import java.io.Serializable;
import java.util.List;

public class HomeAdminDTO implements Serializable {

    private List<UserDTO> userDTOList;
    private Integer RevenueOnDay;
    private List<BillDTO>  onBillTransport;
    private List<BillDTO> billOnNew;
    private List<Integer> listSaleTypeMonth;
    private List<Integer> listSaleTypeYear;
    private List<Integer> findRevenue7Day;
    private List<Integer> findRevenueType;
    private List<TypeDTO> typeDTOS;

    public List<UserDTO> getUserDTOList() {
        return userDTOList;
    }

    public void setUserDTOList(List<UserDTO> userDTOList) {
        this.userDTOList = userDTOList;
    }

    public Integer getRevenueOnDay() {
        return RevenueOnDay;
    }

    public void setRevenueOnDay(Integer revenueOnDay) {
        RevenueOnDay = revenueOnDay;
    }

    public List<BillDTO> getOnBillTransport() {
        return onBillTransport;
    }

    public void setOnBillTransport(List<BillDTO> onBillTransport) {
        this.onBillTransport = onBillTransport;
    }

    public List<BillDTO> getBillOnNew() {
        return billOnNew;
    }

    public void setBillOnNew(List<BillDTO> billOnNew) {
        this.billOnNew = billOnNew;
    }

    public List<Integer> getListSaleTypeMonth() {
        return listSaleTypeMonth;
    }

    public void setListSaleTypeMonth(List<Integer> listSaleTypeMonth) {
        this.listSaleTypeMonth = listSaleTypeMonth;
    }

    public List<Integer> getListSaleTypeYear() {
        return listSaleTypeYear;
    }

    public void setListSaleTypeYear(List<Integer> listSaleTypeYear) {
        this.listSaleTypeYear = listSaleTypeYear;
    }


    public List<Integer> getFindRevenue7Day() {
        return findRevenue7Day;
    }

    public void setFindRevenue7Day(List<Integer> findRevenue7Day) {
        this.findRevenue7Day = findRevenue7Day;
    }

    public List<Integer> getFindRevenueType() {
        return findRevenueType;
    }

    public void setFindRevenueType(List<Integer> findRevenueType) {
        this.findRevenueType = findRevenueType;
    }

    public List<TypeDTO> getTypeDTOS() {
        return typeDTOS;
    }

    public void setTypeDTOS(List<TypeDTO> typeDTOS) {
        this.typeDTOS = typeDTOS;
    }
}
