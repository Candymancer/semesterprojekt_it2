/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.userLevel;

/**
 *
 * @author emilfrisk
 */
public class Silver implements UserLevel {

    private double conversionRate = 1.5;
    private int lowerLimit = 5000;
    private int upperLimit = 9999;

    @Override
    public void setConversionRate(double conversionRate) {
        this.conversionRate = conversionRate;
    }

    @Override
    public double getConversionRate() {
        return this.conversionRate;
    }

    @Override
    public int getLowerLimit() {
        return lowerLimit;
    }

    @Override
    public void setLowerLimit(int lowerLimit) {
        this.lowerLimit = lowerLimit;
    }

    @Override
    public int getUpperLimit() {
        return upperLimit;
    }

    @Override
    public void setUpperLimit(int upperLimit) {
        this.upperLimit = upperLimit;
    }

    public int toInt() {
        return 2;
    }
}
