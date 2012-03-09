package org.ktm.domain.quantity;

/*
 * The SystemOfUnits represents a set of related Units defined by a 
 * standard such as SI
 */
public final class SystemOfUnits {
    
    private static SystemOfUnits theInstance = null;
    
    public static SystemOfUnits getInstance() {
        if (theInstance == null) {
            theInstance = new SystemOfUnits();
        }
        return theInstance;
    }

    public String getNameOfSystem() {
        return "SI";
    }

    public String getNameOfStandardizationBody() {
        return "BIPM";
    }

    public final SIBaseUnit METER = new Meter();
    public final SIBaseUnit KILOGRAM = new Kilogram();
    public final SIBaseUnit BATH = new Bath();
    
}
