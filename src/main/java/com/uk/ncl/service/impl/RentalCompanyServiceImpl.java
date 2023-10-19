package com.uk.ncl.service.impl;

import com.uk.ncl.Tools;
import com.uk.ncl.constant.MyConstants;
import com.uk.ncl.entity.*;
import com.uk.ncl.factory.LargeMotorFactory;
import com.uk.ncl.factory.SmallMotorFactory;
import com.uk.ncl.service.RentalCompanyService;

import java.util.*;
import java.util.stream.Collectors;

public class RentalCompanyServiceImpl implements RentalCompanyService {

    public RentalCompany rentalCompany = RentalCompany.getRentalCompany();
    private LargeMotorFactory largeMotorFactory;
    private SmallMotorFactory smallMotorFactory;

    public RentalCompanyServiceImpl() {
    }

    @Override
    public <T extends Motor> int getAvailableMotorByType(Class<T> motorClazz) {
        int count = 0;
        if (motorClazz.equals(LargeMotorcycle.class)) {
            List<HashMap<Motor, Client>> largeMotorWithClientList = rentalCompany.getLargeMotorWithClientList();
            for (HashMap<Motor, Client> motorClientHashMap : largeMotorWithClientList) {
                for (Map.Entry<Motor, Client> entry : motorClientHashMap.entrySet()) {
                    if (Objects.isNull(entry.getValue())) {
                        count++;
                    }
                }
            }
        }
        if (motorClazz.equals(SmallMotorcycle.class)) {
            List<HashMap<Motor, Client>> smallMotorWithClientList = rentalCompany.getSmallMotorWithClientList();
            for (HashMap<Motor, Client> motorClientHashMap : smallMotorWithClientList) {
                for (Map.Entry<Motor, Client> entry : motorClientHashMap.entrySet()) {
                    if (Objects.isNull(entry.getValue())) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    @Override
    public Motor getRentedMotorByClient(Client client) {
        List<HashMap<Motor, Client>> largeMotorWithClientList = rentalCompany.getLargeMotorWithClientList();
        List<HashMap<Motor, Client>> smallMotorWithClientList = rentalCompany.getSmallMotorWithClientList();
        //Get the Motor corresponding to the specified Client
        List<Motor> smallCollect = smallMotorWithClientList.stream()
                .flatMap(map -> map.entrySet().stream())
                .filter(entry -> client.equals(entry.getValue()))
                .map(Map.Entry::getKey) // get Motor
                .toList();
        //it means it find a motor link to the client
        if (smallCollect.size() != 0) {
            return smallCollect.get(0);
        }
        List<Motor> largeCollect = largeMotorWithClientList.stream()
                .flatMap(map -> map.entrySet().stream())
                .filter(entry -> client.equals(entry.getValue()))
                .map(Map.Entry::getKey) // get Motor
                .toList();
        if (largeCollect.size() != 0) {
            return largeCollect.get(0);
        }
        //it doesn't find any motor by the client
        return null;
    }

    @Override
    public List<Motor> getRentedMotors() {
        List<HashMap<Motor, Client>> smallMotorWithClientList = rentalCompany.getSmallMotorWithClientList();
        List<HashMap<Motor, Client>> largeMotorWithClientList = rentalCompany.getLargeMotorWithClientList();
        //according to the null of Client to check whether the motor has been rented, then collect into List
        List<Motor> rentedSmallList = smallMotorWithClientList.stream()
                .flatMap(map -> map.entrySet().stream())
                .filter(entry -> entry.getValue() != null)
                .map(Map.Entry::getKey)
                .toList();
        List<Motor> rentedLargeList = largeMotorWithClientList.stream()
                .flatMap(map -> map.entrySet().stream())
                .filter(entry -> entry.getValue() != null)
                .map(Map.Entry::getKey)
                .toList();
        List<Motor> totalRentedMotors = new ArrayList<>();
        totalRentedMotors.addAll(rentedSmallList);
        totalRentedMotors.addAll(rentedLargeList);
        return totalRentedMotors;
    }

    @Override
    public boolean issueMotorToClient(Client client, License license, Motor motor) {
        client.setLicense(license);
        if (canIssue(motor, client) != 0) {
            return false;
        }

        String motorType = getMotorType(motor);
        if (!MyConstants.TYPE_UNKNOWN.equals(motorType)) {
            if (MyConstants.TYPE_LARGE.equals(motorType)) {
                List<HashMap<Motor, Client>> largeMotorWithClientList = rentalCompany.getLargeMotorWithClientList();
                for (HashMap<Motor, Client> hashMap : largeMotorWithClientList) {
                    for (Map.Entry<Motor, Client> entry : hashMap.entrySet()) {
                        if (entry.getValue() == null) {
                            //issue motor
                            entry.setValue(client);
                            int largeRentedNum = rentalCompany.getLargeRentedNum();
                            rentalCompany.setLargeRentedNum(largeRentedNum + 1);
                            return true;
                        }
                    }
                }
            }
            if (MyConstants.TYPE_SMALL.equals(motorType)) {
                List<HashMap<Motor, Client>> smallMotorWithClientList = rentalCompany.getSmallMotorWithClientList();
                for (HashMap<Motor, Client> hashMap : smallMotorWithClientList) {
                    for (Map.Entry<Motor, Client> entry : hashMap.entrySet()) {
                        if (entry.getValue() == null) {
                            //issue motor
                            entry.setValue(client);
                            int smallRentedNum = rentalCompany.getSmallRentedNum();
                            rentalCompany.setSmallRentedNum(smallRentedNum + 1);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * get the type by motor
     *
     * @param motor motor instance
     * @return string of the motor type
     */
    private String getMotorType(Motor motor) {
        if (motor.getClass().equals(LargeMotorcycle.class)) {
            return MyConstants.TYPE_LARGE;
        }
        if (motor.getClass().equals(SmallMotorcycle.class)) {
            return MyConstants.TYPE_SMALL;
        }
        return MyConstants.TYPE_UNKNOWN;
    }

    //get the type of motor by client
    public String getMotorType(Client client) {
        List<HashMap<Motor, Client>> largeMotorWithClientList = rentalCompany.getLargeMotorWithClientList();
        for (HashMap<Motor, Client> motorClientHashMap : largeMotorWithClientList) {
            for (Map.Entry<Motor, Client> entry : motorClientHashMap.entrySet()) {
                if (client.equals(entry.getValue())) {
                    return entry.getKey().getClass().equals(LargeMotorcycle.class)
                            ? MyConstants.TYPE_LARGE : MyConstants.TYPE_SMALL;
                }
            }
        }
        return MyConstants.TYPE_UNKNOWN;
    }

    @Override
    public int canIssue(Motor motor, Client client) {
        if (motor == null || client == null) {
            //invalid param
            return -2;
        }

        License license = client.getLicense();
        boolean isFormal = license.getIsFormal();
        if (!isFormal) {
            //is not formal
            return 2;
        }
        int age = client.getAge();
        Date issueDate = license.getIssueDate();
        int issuedYear = Tools.getYearByDate(issueDate);

        //rent largeMotor: whether it outnumber or equal the figure of max num
        //and check the age and issuedYear
        if (motor.getClass().equals(LargeMotorcycle.class)) {
            if ((age < MyConstants.RENT_LARGE_LIMIT_AGE || issuedYear < MyConstants.RENT_LARGE_LICENSE_LIMIT_YEAR)) {
                //proof fail
                return 3;
            }
            List<HashMap<Motor, Client>> largeMotorWithClientList = rentalCompany.getLargeMotorWithClientList();
            // if the client had rented or no available motors return 1
            if (beOccupied(client, largeMotorWithClientList)) return 1;
            if (rentalCompany.getLargeRentedNum() >= MyConstants.RENTED_LARGE_MAX_NUM) {
                    //proof fail
                    return 3;
            }

        } else if (motor.getClass().equals(SmallMotorcycle.class)) {
            if ((age < MyConstants.RENT_SMALL_LIMIT_AGE || issuedYear < MyConstants.RENT_SMALL_LICENSE_LIMIT_YEAR)) {
                return 3;
            }
            List<HashMap<Motor, Client>> largeMotorWithClientList = rentalCompany.getLargeMotorWithClientList();
            // if the client had rented or no available motors return 1
            if (beOccupied(client, largeMotorWithClientList)) return 1;
            if (rentalCompany.getSmallRentedNum() >= MyConstants.RENTED_SMALL_MAX_NUM) {
                //proof fail)
                return 3;
            }
        }
        //could tent
        return 0;
    }

    private boolean beOccupied(Client client, List<HashMap<Motor, Client>> motorWithClientList) {
        boolean beOccupied = true;
        for (HashMap<Motor, Client> motorClientHashMap : motorWithClientList) {
            for (Map.Entry<Motor, Client> entry : motorClientHashMap.entrySet()) {
                //motor has been rented to others or client has rented
                if (entry.getValue() == null) {
                    //available
                    beOccupied = false;
                }
                //motor has been rented to this client, return directly
                if (entry.getValue() == client) {
                    //had been rented
                    beOccupied = true;
                    return beOccupied;
                }
            }
        }
        return beOccupied;
    }

    @Override
    public int terminateRental(Client client) {
        Motor motor = null;
        int batteryLevel = 0;
        List<HashMap<Motor, Client>> smallMotorWithClientList = rentalCompany.getSmallMotorWithClientList();
        List<HashMap<Motor, Client>> largeMotorWithClientList = rentalCompany.getLargeMotorWithClientList();
        for (HashMap<Motor, Client> hashMap : smallMotorWithClientList) {
            for (Map.Entry<Motor, Client> entry : hashMap.entrySet()) {
                if (entry.getValue() == client) {
                    //delete record
                    entry.setValue(null);
                    int smallRentedNum = rentalCompany.getSmallRentedNum();
                    rentalCompany.setSmallRentedNum(smallRentedNum - 1);
                    motor = (SmallMotorcycle) entry.getKey();
                    batteryLevel = MyConstants.SMALL_BATTERY_LEVEL - motor.getBatteryLevel();
                    //recharge the battery and then return
                    motor.setBatteryLevel(MyConstants.SMALL_BATTERY_LEVEL);
                    return batteryLevel;
                }
            }
        }

        for (HashMap<Motor, Client> hashMap : largeMotorWithClientList) {
            for (Map.Entry<Motor, Client> entry : hashMap.entrySet()) {
                if (entry.getValue() == client) {
                    //delete record
                    entry.setValue(null);
                    int largeRentedNum = rentalCompany.getLargeRentedNum();
                    rentalCompany.setLargeRentedNum(largeRentedNum - 1);
                    motor = (LargeMotorcycle) entry.getKey();
                    batteryLevel = MyConstants.LARGE_BATTERY_LEVEL - motor.getBatteryLevel();
                    //recharge the battery and then return
                    motor.setBatteryLevel(MyConstants.LARGE_BATTERY_LEVEL);
                    return batteryLevel;
                }
            }
        }
        //didn't find this client
        return -1;
    }
}
