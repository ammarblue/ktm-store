package org.ktm.domain.quantity;

/*
 * The Unit represent a type of Metric that is part of a SystemOUnits.
 */
public class Unit extends Metric {

    public SystemOfUnits getSystemOfUnits() {
        return SystemOfUnits.getInstance();
    }
}
