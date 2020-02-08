package com.hieuminh.dto;

import java.io.Serializable;

public class CheckLogin implements Serializable {
    private boolean isMemberExist;



    public boolean isMemberExist() {
        return isMemberExist;
    }

    public void setMemberExist(boolean memberExist) {
        isMemberExist = memberExist;
    }


}
