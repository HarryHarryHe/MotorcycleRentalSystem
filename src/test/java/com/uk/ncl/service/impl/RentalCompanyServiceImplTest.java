package com.uk.ncl.service.impl;

import com.uk.ncl.entity.LargeMotorcycle;
import com.uk.ncl.entity.RentalCompany;
import com.uk.ncl.entity.SmallMotorcycle;
import com.uk.ncl.factory.LargeMotorFactory;
import com.uk.ncl.factory.MotorFactory;
import com.uk.ncl.factory.SmallMotorFactory;
import org.junit.Test;

import static org.junit.Assert.*;

public class RentalCompanyServiceImplTest {

    private MotorFactory largeMotorFactory = new LargeMotorFactory();
    private MotorFactory smallMotorFactory = new SmallMotorFactory();
    private RentalCompanyServiceImpl rentalCompanyService = new RentalCompanyServiceImpl();
    @Test
    public void getAvailableMotorByType() {
        largeMotorFactory.create();
        smallMotorFactory.create();
        System.out.println("Large available: "+rentalCompanyService.getAvailableMotorByType(LargeMotorcycle.class));
        System.out.println("Small available: "+rentalCompanyService.getAvailableMotorByType(SmallMotorcycle.class));
    }
}