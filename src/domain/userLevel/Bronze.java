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
public class Bronze implements UserLevel {

    private double conversionRate = 1;
    private int lowerLimit = 0;
    private int upperLimit = 4999;

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

    @Override
    public void setConversionRate(double conversionRate) {
        this.conversionRate = conversionRate;
    }

    @Override
    public double getConversionRate() {
        return this.conversionRate;
    }

    public int toInt() {
        return 1;
    }

}
