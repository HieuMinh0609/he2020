package com.hieuminh.dto.utils;

import java.util.List;

public class PagingModelDTO<T> {
    private List<T> listResult;
    private String tableId = "tableList";
    private Integer totalItems = 0;
    private Integer maxPageItems = 12;
    private Integer firstItem = 0;
    private Integer page = 1;
    private String sortExpression;
    private String sortDirection;
    private Integer totalPages;


    public List<T> getListResult() {
        return listResult;
    }

    public void setListResult(List<T> listResult) {
        this.listResult = listResult;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public Integer getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }

    public Integer getMaxPageItems() {
        return maxPageItems;
    }

    public void setMaxPageItems(Integer maxPageItems) {
        this.maxPageItems = maxPageItems;
    }

    public Integer getFirstItem() {
        return firstItem;
    }

    public void setFirstItem(Integer firstItem) {
        this.firstItem = firstItem;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getSortExpression() {
        return sortExpression;
    }

    public void setSortExpression(String sortExpression) {
        this.sortExpression = sortExpression;
    }

    public String getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(String sortDirection) {
        this.sortDirection = sortDirection;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }



}
