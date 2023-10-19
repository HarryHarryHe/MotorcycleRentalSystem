package com.uk.ncl.entity;

import com.uk.ncl.constant.MyConstants;

import java.util.UUID;

public class SmallMotorcycle extends Motor {
    private int batteryLevel = MyConstants.SMALL_BATTERY_LEVEL;
    private int consumedPerKM = MyConstants.SMALL_CONSUMED_PER_KM;
    private String regNum;
    public SmallMotorcycle() {
    }

    public SmallMotorcycle(int batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(int batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public int getConsumedPerKM() {
        return consumedPerKM;
    }

    public void setConsumedPerKM(int consumedPerKM) {
        this.consumedPerKM = consumedPerKM;
    }

    @Override
    public boolean isFullyCharged() {
        boolean isFully = true;
                if (batteryLevel < MyConstants.SMALL_BATTERY_LEVEL) {
                    isFully = false;
                }
            return isFully;

    }

    @Override
    public int chargeMotor(int capacity) {
        int gap2Max = 0;
        if (capacity < MyConstants.SMALL_BATTERY_LEVEL) {
            gap2Max = MyConstants.SMALL_BATTERY_LEVEL - capacity;
        }
        return gap2Max;
    }

    @Override
    public int ride(Client client, int distance) {
        if (!canRide(client)){
            return 0;
        }
        int consumption = calConsumption(client, distance);
        Motor motor = super.getRentedMotorByClient(client);
        //cal the motor battery level
        motor.setBatteryLevel(motor.getBatteryLevel() - consumption);
        return consumption;
    }

    public String getRegNum() {
        return regNum;
    }

    public void setRegNum(String regNum) {
        this.regNum = regNum;
    }

    @Override
    public String toString() {
        return "SmallMotorcycle{" +
                "batteryLevel=" + batteryLevel +
                ", consumedPerKM=" + consumedPerKM +
                ", regNum='" + regNum + '\'' +
                '}';
    }
}
