package com.uk.ncl.entity;

import com.uk.ncl.Tools;
import com.uk.ncl.factory.LargeMotorFactory;
import com.uk.ncl.factory.MotorFactory;
import com.uk.ncl.factory.SmallMotorFactory;
import com.uk.ncl.service.impl.RentalCompanyServiceImpl;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class MotorTest {

    static MotorFactory largeMotorFactory = null;
    static MotorFactory smallMotorFactory = null;
    static RentalCompany rentalCompany = null;
    static RentalCompanyServiceImpl rentalCompanyService = null;

    @BeforeClass
    public static void begin() {
        largeMotorFactory = new LargeMotorFactory();
        smallMotorFactory = new SmallMotorFactory();
        rentalCompany = RentalCompany.getRentalCompany();
        rentalCompanyService = new RentalCompanyServiceImpl();
        largeMotorFactory.create();
        smallMotorFactory.create();
    }
    @Test
    public void isFullyCharged() {
        Motor largeMotor = new LargeMotorcycle(55);
        Motor smallMotor = new SmallMotorcycle(20);
        assertFalse(largeMotor.isFullyCharged());
        assertFalse(smallMotor.isFullyCharged());
    }

    @Test
    public void chargeMotor() {

    }

    @Test
    public void ride() {
        Client client = new Client("Haitao", "He", Tools.parseToDate("1996-07-20"));
        License license = new License(Tools.parseToDate("2016-09-01"), true);
        license.setClient(client);
        license.setLicenseID(license.genLicenseID());
        int consumption = 0;
        Motor largeMotor = new LargeMotorcycle();
        Motor smallMotor = new SmallMotorcycle();
        consumption = largeMotor.ride(client, 10);
        System.out.println(consumption);
        consumption = smallMotor.ride(client, 20);
        System.out.println(consumption);
    }
}