package com.uk.ncl.service;

import com.uk.ncl.entity.Client;
import com.uk.ncl.entity.License;
import com.uk.ncl.entity.Motor;

import java.util.List;

public interface RentalCompanyService {
    /**
     * This method returns the number of electric motorcycle of the specified type that are
     * available to rent
     *
     * @param motorClazz the clazz of the subclass of Motor
     *
     */
    <T extends Motor> int getAvailableMotorByType(Class<T> motorClazz);

    /**
     * Given a person, this method returns the electric motorcycle they are currently renting
     * (if any).
     */
    Motor getRentedMotorByClient(Client client);

    /**
     * This method returns a collection of all the electric motorcycle currently rented out (if
     * any).
     */
    List<Motor> getRentedMotors();

    /**
     * this method determines whether
     * the person is eligible to rent a motorcycle of the specified type and, if there is a
     * motorcycle available, issues an electric motorcycle of the specified type
     */
    int issueMotorToClient(Client client, License license, Motor motor);

    /**
     * according to client's info to decide whether can clients rent the motor
     *
     * @param client
     * @return 0: yes
     * 1: no license
     * 2: multi rent
     * 3: rentSmall should age >= 20 and license >= 1 year
     * 4: rentLarge should age >= 25 and license >= 5 years
     */
    int canIssue(Client client);

    /**
     * calculate the gap of battery level to max level
     *
     * @param client
     * @return
     */
    int terminateRental(Client client);


}
