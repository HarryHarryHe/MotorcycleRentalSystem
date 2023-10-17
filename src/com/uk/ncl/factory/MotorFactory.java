package com.uk.ncl.factory;

import com.uk.ncl.entity.Motor;

import java.util.List;

public abstract class MotorFactory {
    public abstract List<Motor> create();
}
