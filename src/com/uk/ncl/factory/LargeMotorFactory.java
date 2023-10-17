package com.uk.ncl.factory;

import com.uk.ncl.Tools;
import com.uk.ncl.constant.MyConstants;
import com.uk.ncl.po.LargeMotorcycle;
import com.uk.ncl.po.Motor;

import java.util.ArrayList;
import java.util.List;

public class LargeMotorFactory extends MotorFactory {

    private int rentedMaxNum = MyConstants.RENTED_LARGE_MAX_NUM;
    private int batteryLevel = MyConstants.LARGE_BATTERY_LEVEL;
    private int consumedPerKM = MyConstants.LARGE_CONSUMED_PER_KM;
    private int rentedNum = 0;
    private int availableNum = rentedMaxNum - rentedNum;
    @Override
    public List<Motor> create() {
        List<LargeMotorcycle> largeMotorcycles =new ArrayList<>(rentedMaxNum);
        for (int i=0;i<rentedMaxNum;i++){
            LargeMotorcycle largeMotorcycle = new LargeMotorcycle();
            largeMotorcycle.setBatteryLevel(batteryLevel);
            largeMotorcycle.setConsumedPerKM(consumedPerKM);
            largeMotorcycle.setRegNum(largeMotorcycle.genRegNum());
        }
        return null;
    }
}
