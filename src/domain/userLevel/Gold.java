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
public class Gold implements UserLevel {

    private double conversionRate = 2;
    private int lowerLimit = 10000;

    @Override
    public int getLowerLimit() {
        return lowerLimit;
    }

    @Override
    public void setLowerLimit(int lowerLimit) {
        this.lowerLimit = lowerLimit;
    }
 
    @Override
    public void setConversionRate(double conversionRate) {
        this.conversionRate = conversionRate;
    }

    @Override
    public double getConversionRate() {
        return this.conversionRate;
    }

    @Override
    public void setUpperLimit(int higherLimit) {
    }

    @Override
    public int getUpperLimit() {
        return 0;
    }
    
    public int toInt(){
        return 3;
    }

}
