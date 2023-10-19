package com.uk.ncl.factory;

import com.uk.ncl.entity.Motor;

import java.util.List;

public abstract class MotorFactory {
    /**
     * Create large and small motorcycle factories
     * @return
     */
    public abstract boolean create();

    public abstract List<Motor> getMotorcycles();
}
