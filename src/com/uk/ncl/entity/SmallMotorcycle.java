package com.uk.ncl.entity;

import com.uk.ncl.constant.MyConstants;

public class SmallMotorcycle extends Motor {
    private int batteryLevel = MyConstants.SMALL_BATTERY_LEVEL;
    private int consumedPerKM = MyConstants.SMALL_CONSUMED_PER_KM;
    private int rentedMaxNum = MyConstants.RENTED_SMALL_MAX_NUM;
    private int rentedNum = 0;
    private int availableNum = rentedMaxNum - rentedNum;

    public SmallMotorcycle() {
    }

    @Override
    public void run() {

    }
}
