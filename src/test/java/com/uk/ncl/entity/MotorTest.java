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
        Client client = new Client("Haitao", "He", Tools.parseToDate("1996-07-20"));
        License license = new License(Tools.parseToDate("2016-09-01"), true);
        license.setClient(client);
        license.setLicenseID(license.genLicenseID());
        int consumption = 0;
        System.out.println("Rent Large: " + rentalCompanyService.issueMotorToClient(client, license, new LargeMotorcycle()));
        Motor motor = rentalCompanyService.getRentedMotorByClient(client);
        consumption = motor.ride(client, 40);
        assertEquals(80, consumption);
//        System.out.println("consumption: " + consumption);
        assertEquals(-5, motor.getBatteryLevel());
//        System.out.println("motor battery level before charging: " + motor.getBatteryLevel());
        motor.chargeMotor(500);
        assertEquals(70, motor.getBatteryLevel());
//        System.out.println("motor battery level after charging: " + motor.getBatteryLevel());
    }

    @Test
    public void ride() {
        Client client = new Client("Haitao", "He", Tools.parseToDate("1996-07-20"));
        License license = new License(Tools.parseToDate("2016-09-01"), true);
        license.setClient(client);
        license.setLicenseID(license.genLicenseID());
        int consumption = 0;
        System.out.println("Rent Large: " + rentalCompanyService.issueMotorToClient(client, license, new LargeMotorcycle()));
        Motor motor = rentalCompanyService.getRentedMotorByClient(client);
        consumption = motor.ride(client, 80);
        System.out.println("consumption: " + consumption);
        System.out.println("motor battery level: " + motor.getBatteryLevel());
        System.out.println("Rent Large: " + rentalCompanyService.issueMotorToClient(client, license, new LargeMotorcycle()));
        System.out.println("Rent Small: " + rentalCompanyService.issueMotorToClient(client, license, new SmallMotorcycle()));
//        consumption = smallMotor.ride(client, 20);
//        System.out.println(consumption);
    }
}