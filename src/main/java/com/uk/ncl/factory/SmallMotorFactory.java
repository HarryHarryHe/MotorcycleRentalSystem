package com.uk.ncl.factory;

import com.uk.ncl.constant.MyConstants;
import com.uk.ncl.entity.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SmallMotorFactory extends MotorFactory {
    private RentalCompany rentalCompany = RentalCompany.getRentalCompany();
    private int rentedMaxNum = MyConstants.RENTED_SMALL_MAX_NUM;

    private int batteryLevel = MyConstants.SMALL_BATTERY_LEVEL;
    private int consumedPerKM = MyConstants.SMALL_CONSUMED_PER_KM;
    private int rentedNum = 0;
    private int availableNum = rentedMaxNum - rentedNum;
    private List<Motor> motorcycles = new ArrayList<>(rentedMaxNum);

    @Override
    public boolean create() {
        List<HashMap<Motor, Client>> smallMotorWithClientList = new ArrayList<>();
        for (int i = 0; i < rentedMaxNum; i++) {
            SmallMotorcycle smallMotorcycle = new SmallMotorcycle();
            smallMotorcycle.setBatteryLevel(batteryLevel);
            smallMotorcycle.setConsumedPerKM(consumedPerKM);
            smallMotorcycle.setRegNum(smallMotorcycle.genRegNum());
            //将组装好的摩托车加入规定大小的摩托车列表中
            motorcycles.add(smallMotorcycle);
            HashMap<Motor, Client> smallMotorWithClientMap = new HashMap<>();
            //初始化各摩托车与用户的绑定关系
            smallMotorWithClientMap.put(smallMotorcycle, null);
            //将绑定关系添加进列表中
            smallMotorWithClientList.add(smallMotorWithClientMap);
        }
        //公司管理摩托车与人绑定关系列表
        rentalCompany.setSmallMotorWithClientList(smallMotorWithClientList);
        return true;
    }

    @Override
    public List<Motor> getMotorcycles() {
        return motorcycles;
    }

    public void setMotorcycles(List<Motor> motorcycles) {
        this.motorcycles = motorcycles;
    }
}
