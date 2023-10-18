package com.uk.ncl.service.impl;

import com.uk.ncl.entity.*;
import com.uk.ncl.factory.LargeMotorFactory;
import com.uk.ncl.factory.SmallMotorFactory;
import com.uk.ncl.service.RentalCompanyService;

import java.util.List;

public class RentalCompanyServiceImpl  {

    private RentalCompany rentalCompany = RentalCompany.getRentalCompany();
    private LargeMotorFactory largeMotorFactory;
    private SmallMotorFactory smallMotorFactory;


    public static void main(String[] args) {
        RentalCompanyServiceImpl rentalCompanyService = new RentalCompanyServiceImpl();
        LargeMotorcycle largeMotorcycle = new LargeMotorcycle();
//        int availableMotorByType = rentalCompanyService.getAvailableMotorByType(largeMotorcycle);


    }
}
