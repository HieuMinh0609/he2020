package com.hieuminh.dto.utils;

import com.hieuminh.dto.AbstractDTO;

public class DateDTO extends AbstractDTO<DateDTO> {

    private String day;
    private String month;
    private String year;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
