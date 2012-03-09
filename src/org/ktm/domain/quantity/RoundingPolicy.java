package org.ktm.domain.quantity;

public class RoundingPolicy {

    private Integer numberOfDigits;
    private Integer roundingDigit;
    private Integer roundingStep;
    private RoundingStrategy roundingStrategy;
    
    public Integer getNumberOfDigits() {
        return numberOfDigits;
    }
    
    public void setNumberOfDigits(Integer numberOfDigits) {
        this.numberOfDigits = numberOfDigits;
    }
    
    public Integer getRoundingDigit() {
        return roundingDigit;
    }
    
    public void setRoundingDigit(Integer roundingDigit) {
        this.roundingDigit = roundingDigit;
    }
    
    public Integer getRoundingStep() {
        return roundingStep;
    }
    
    public void setRoundingStep(Integer roundingStep) {
        this.roundingStep = roundingStep;
    }

    public RoundingStrategy getRoundingStrategy() {
        return roundingStrategy;
    }

    public void setRoundingStrategy(RoundingStrategy roundingStrategy) {
        this.roundingStrategy = roundingStrategy;
    }
    
}
