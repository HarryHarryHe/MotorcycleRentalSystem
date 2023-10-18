package com.uk.ncl.service;

import com.uk.ncl.entity.Client;
import com.uk.ncl.entity.License;
import com.uk.ncl.entity.Motor;

import java.util.List;

public interface RentalCompanyService {
    int getAvailableMotorByType(Motor motor);

    Motor getRentedMotorByClient(Client client);

    List<Motor> getRentedMotors();

    int issueMotorToClient(Client client, License license, Motor motor);

    /**
     * according to client's info to decide whether can clients rent the motor
     * @param client
     * @return 0: yes
     *         1: no license
     *         2: multi rent
     *         3: rentSmall should age >= 20 and license >= 1 year
     *         4: rentLarge should age >= 25 and license >= 5 years
     */
    int canIssue(Client client);

    /**
     * calculate the gap of battery level to max level
     * @param client
     * @return
     */
    int terminateRental(Client client);


}
