package com.uk.ncl.service;

import com.uk.ncl.entity.Client;
import com.uk.ncl.entity.Motor;

public interface ClientService {
    int rentMotor(Client client, Motor motor);
    boolean isRenting(Client client);
    int returnMotor(Client client, Motor motor);
}
