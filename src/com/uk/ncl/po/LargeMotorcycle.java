package com.uk.ncl.po;

import com.uk.ncl.constant.MyConstants;
import com.uk.ncl.factory.LargeMotorFactory;

public class LargeMotorcycle extends Motor {
    private int batteryLevel = MyConstants.LARGE_BATTERY_LEVEL;
    private int consumedPerKM = MyConstants.LARGE_CONSUMED_PER_KM;
    private int rentedMaxNum = MyConstants.RENTED_LARGE_MAX_NUM;
    private int rentedNum = 0;
    private int availableNum = rentedMaxNum - rentedNum;

    public LargeMotorcycle() {
    }

    @Override
    public void run() {

    }
}
