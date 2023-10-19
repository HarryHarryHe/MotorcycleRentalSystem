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
import java.util.Map;

import static org.junit.Assert.*;

public class RentalCompanyServiceImplTest {

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
        System.out.println(rentalCompanyService.canIssue(motor, client));
    }

    @Test
    public void getRentedMotorByClient() {
        Client client = new Client("Haitao", "He", Tools.parseToDate("1999-04-28"));
        Motor rentedMotorByClient = rentalCompanyService.getRentedMotorByClient(client);
        System.out.println(rentedMotorByClient);
    }

    @Test
    public void getRentedMotors() {
        for (Motor motor : rentalCompanyService.getRentedMotors()) {
            System.out.println(motor);
        }
    }

    @Test
    public void issueMotorToClient() {
        Client client = new Client("Haitao", "He", Tools.parseToDate("1996-07-20"));
        Client client1 = new Client("Yuxian", "Lai", Tools.parseToDate("2022-07-20"));
        Client client2 = new Client("Guangzhen", "Zhou", Tools.parseToDate("1999-07-20"));
        Client client3 = new Client("Zilong", "Zhou", Tools.parseToDate("2023-07-20"));
        License license = new License(Tools.parseToDate("2016-09-01"), true);
        License license1 = new License(Tools.parseToDate("2016-09-01"), true);
        License license2 = new License(Tools.parseToDate("2016-09-01"), true);
        License license3 = new License(Tools.parseToDate("2016-09-01"), true);
        license.setClient(client);
        license1.setClient(client1);
        license2.setClient(client2);
        license3.setClient(client3);
        license.setLicenseID(license.genLicenseID());
        license1.setLicenseID(license1.genLicenseID());
        license2.setLicenseID(license2.genLicenseID());
        license3.setLicenseID(license3.genLicenseID());
        boolean canIssue = rentalCompanyService.issueMotorToClient(client, license, new LargeMotorcycle());
        boolean canIssue1 = rentalCompanyService.issueMotorToClient(client1, license1, new LargeMotorcycle());
        boolean canIssue2 = rentalCompanyService.issueMotorToClient(client2, license2, new SmallMotorcycle());
        boolean canIssue3 = rentalCompanyService.issueMotorToClient(client3, license3, new SmallMotorcycle());
//        if (!canIssue) {
//            return;
//        }
        System.out.printf("%s %s %s %s", canIssue, canIssue1, canIssue2, canIssue3);
        System.out.println();
        List<HashMap<Motor, Client>> largeMotorWithClientList = rentalCompany.getLargeMotorWithClientList();
        largeMotorWithClientList.stream()
                .flatMap(map -> map.entrySet().stream())
                .filter(entry -> entry.getValue() != null)
                .forEach(entry -> {
                    Motor m = entry.getKey();
                    Client c = entry.getValue();
                    System.out.println("Motor: " + m.toString() + ", Client: " + c.toString());
                });
        System.out.println("getRentedMotorByClient: " + rentalCompanyService.getRentedMotorByClient(client));
        System.out.println("getMotorType: " + rentalCompanyService.getMotorType(client));
        System.out.println("getRentedMotors: " + rentalCompanyService.getRentedMotors());
        System.out.println("getAvailableMotorByType: " + rentalCompanyService.getAvailableMotorByType(LargeMotorcycle.class));
        System.out.println("getAvailableMotorByType: " + rentalCompanyService.getAvailableMotorByType(SmallMotorcycle.class));
    }

    @Test
    public void terminateRental(Client client) {
        rentalCompanyService.terminateRental(client);
    }
}