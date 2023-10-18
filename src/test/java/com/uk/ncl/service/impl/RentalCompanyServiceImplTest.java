package com.uk.ncl.service.impl;

import com.uk.ncl.Tools;
import com.uk.ncl.entity.*;
import com.uk.ncl.factory.LargeMotorFactory;
import com.uk.ncl.factory.MotorFactory;
import com.uk.ncl.factory.SmallMotorFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class RentalCompanyServiceImplTest{

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
    public void getAvailableMotorByType() {

        System.out.println("Large available: " + rentalCompanyService.getAvailableMotorByType(LargeMotorcycle.class));
        System.out.println("Small available: " + rentalCompanyService.getAvailableMotorByType(SmallMotorcycle.class));
    }

    @Test
    public void canIssue() {
        Client client = new Client("Haitao", "He", Tools.parseToDate("1999-04-28"));
        License license = new License();
        license.setClient(client);
        license.setIssueDate(Tools.parseToDate("2016-09-09"));
        license.setLicenseID(license.genLicenseID());
        license.setIsFormal(true);
        client.setLicense(license);
        List<HashMap<Motor, Client>> largeMotorWithClientList = rentalCompany.getLargeMotorWithClientList();
        LargeMotorcycle motor = (LargeMotorcycle) largeMotorWithClientList.get(0).entrySet().iterator().next().getKey();
        System.out.println(rentalCompanyService.canIssue(motor,client));
    }

    @Test
    public void getRentedMotorByClient() {
        Client client = new Client("Haitao", "He", Tools.parseToDate("1999-04-28"));
        Motor rentedMotorByClient = rentalCompanyService.getRentedMotorByClient(client);
        System.out.println(rentedMotorByClient);
    }
}