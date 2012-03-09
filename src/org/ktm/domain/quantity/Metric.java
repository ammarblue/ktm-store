package org.ktm.domain.quantity;

/*
 * The Metric represent a standard of measurement
 */
public class Metric {
    
    private static Metric theInstance = null;
    
    public static Metric getInstance() {
        if (theInstance == null) {
            theInstance = new Metric();
        }
        return theInstance;
    }
    
    private String name;
    private String symbol;
    private String definition;

    /*
     * Return the name of the Metric, e.g., "meters"
     */
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    /*
     * Returns null or the standard symbol for the Metric, e.g., "m"
     */
    public String getSymbol() {
        return symbol;
    }
    
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    
    /*
     * Return the formal definition of the Metric, e.g., "The meter is the length
     * of the path travelled by light in vacuum during a time interval of 1/299792458
     * of a second
     */
    public String getDefinition() {
        return definition;
    }
    
    public void setDefinition(String definition) {
        this.definition = definition;
    }
    
    @Override
    public boolean equals(Object other) {
        if (other instanceof Metric) {
            return (this.getName().equals(((Metric) other).getName())) 
                    && (this.getSymbol().equals(((Metric) other).getSymbol()));
        }
        return false;
    }

}
