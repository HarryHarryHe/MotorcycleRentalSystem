package com.uk.ncl.factory;

import com.uk.ncl.constant.MyConstants;
import com.uk.ncl.entity.Client;
import com.uk.ncl.entity.LargeMotorcycle;
import com.uk.ncl.entity.Motor;
import com.uk.ncl.entity.RentalCompany;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LargeMotorFactory extends MotorFactory {

    private RentalCompany rentalCompany = RentalCompany.getRentalCompany();
    private int rentedMaxNum = MyConstants.RENTED_LARGE_MAX_NUM;
    private int batteryLevel = MyConstants.LARGE_BATTERY_LEVEL;
    private int consumedPerKM = MyConstants.LARGE_CONSUMED_PER_KM;
    private int rentedNum = 0;
    private int availableNum = rentedMaxNum - rentedNum;

    private List<Motor> motorcycles = new ArrayList<>(rentedMaxNum);

    /**
     * Generate corresponding motorcycles based on production quantity, capacitor size, and power consumption
     * @return
     */
    @Override
    public boolean create() {
        List<HashMap<Motor, Client>> largeMotorWithClientList = new ArrayList<>();
        for (int i = 0; i < rentedMaxNum; i++) {
            LargeMotorcycle largeMotorcycle = new LargeMotorcycle();
            largeMotorcycle.setBatteryLevel(batteryLevel);
            largeMotorcycle.setConsumedPerKM(consumedPerKM);
            largeMotorcycle.setRegNum(largeMotorcycle.genRegNum());
            //Add the assembled motorcycle to the list of motorcycles of specified sizes
            motorcycles.add(largeMotorcycle);
            HashMap<Motor, Client> largeMotorWithClientMap = new HashMap<>();
            //Initialize the binding relationship between each motorcycle and the user
            largeMotorWithClientMap.put(largeMotorcycle, null);
            //Add bindings to the list
            largeMotorWithClientList.add(largeMotorWithClientMap);
        }
        //List of binding relationships between company-managed motorcycles and people
        rentalCompany.setLargeMotorWithClientList(largeMotorWithClientList);
        return true;
    }

    public List<Motor> getMotorcycles() {
        return motorcycles;
    }

    public void setMotorcycles(List<Motor> motorcycles) {
        this.motorcycles = motorcycles;
    }



}
