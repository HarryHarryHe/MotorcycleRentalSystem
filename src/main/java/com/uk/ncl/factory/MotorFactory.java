package com.uk.ncl.factory;

import com.uk.ncl.entity.Motor;

import java.util.List;

public abstract class MotorFactory {
    public abstract boolean create();

    public abstract List<Motor> getMotorcycles();
}
