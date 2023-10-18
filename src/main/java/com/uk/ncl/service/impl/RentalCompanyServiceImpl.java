package com.uk.ncl.service.impl;

import com.uk.ncl.Tools;
import com.uk.ncl.constant.MyConstants;
import com.uk.ncl.entity.*;
import com.uk.ncl.factory.LargeMotorFactory;
import com.uk.ncl.factory.SmallMotorFactory;
import com.uk.ncl.service.RentalCompanyService;

import java.util.*;

public class RentalCompanyServiceImpl implements RentalCompanyService {

    public RentalCompany rentalCompany = RentalCompany.getRentalCompany();
    private LargeMotorFactory largeMotorFactory;
    private SmallMotorFactory smallMotorFactory;

    public RentalCompanyServiceImpl() {
    }

    public static void main(String[] args) {
        RentalCompanyServiceImpl rentalCompanyService = new RentalCompanyServiceImpl();
        LargeMotorcycle largeMotorcycle = new LargeMotorcycle();
//        int availableMotorByType = rentalCompanyService.getAvailableMotorByType(largeMotorcycle);


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
        return null;
    }

    @Override
    public List<Motor> getRentedMotors() {
        return null;
    }

    @Override
    public boolean issueMotorToClient(Client client, License license, Motor motor) {


        return false;
    }

    @Override
    public int canIssue(Motor motor, Client client) {
        List<HashMap<Motor, Client>> largeMotorWithClientList = rentalCompany.getLargeMotorWithClientList();
        for (HashMap<Motor, Client> motorClientHashMap : largeMotorWithClientList) {
            for (Map.Entry<Motor, Client> entry : motorClientHashMap.entrySet()) {
                if (client != null && client.equals(entry.getValue())) {
                    //already have rented
                    return 1;
                }
            }
        }
        List<HashMap<Motor, Client>> smallMotorWithClientList = rentalCompany.getSmallMotorWithClientList();
        for (HashMap<Motor, Client> motorClientHashMap : smallMotorWithClientList) {
            for (Map.Entry<Motor, Client> entry : motorClientHashMap.entrySet()) {
                if (client != null && client.equals(entry.getValue())) {
                    //already have rented
                    return 1;
                }
            }
        }
        License license = client.getLicense();
        boolean isFormal = license.isFormal();
        if (!isFormal) {
            //is not formal
            return 2;
        }
        int age = client.getAge();
        Date issueDate = license.getIssueDate();
        int issuedYear = Tools.getYearByDate(issueDate);
        if (motor.getClass().equals(LargeMotorcycle.class)
                && (age < MyConstants.RENT_LARGE_LIMIT_AGE || issuedYear < MyConstants.RENT_LARGE_LICENSE_LIMIT_YEAR)) {
            //proof fail
            return 3;
        } else if (motor.getClass().equals(SmallMotorcycle.class)) {

        }


        return 0;
    }

    @Override
    public int terminateRental(Client client) {
        return 0;
    }
}
