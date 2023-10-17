package com.uk.ncl.po;

import com.uk.ncl.constant.MyConstants;

import java.util.UUID;

public class SmallMotorcycle extends Motor {
    private int batteryLevel = MyConstants.SMALL_BATTERY_LEVEL;
    private int consumedPerKM = MyConstants.SMALL_CONSUMED_PER_KM;
    private int rentedMaxNum = MyConstants.RENTED_SMALL_MAX_NUM;
    private int rentedNum = 0;
    private int availableNum = rentedMaxNum - rentedNum;

    private String regNum;
    public SmallMotorcycle() {
    }

    public SmallMotorcycle(int rentedMaxNum) {
    }

    public String getRegNum() {
        return regNum;
    }

    public void setRegNum(String regNum) {
        this.regNum = regNum;
    }
}
