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
public abstract class AbstractLevel implements UserLevel{
    
    protected double conversionRate;
    protected int lowerLimit;
    protected int upperLimit;
    
    @Override
    public void setLowerLimit(int lowerLimit){
        this.lowerLimit = lowerLimit;
    }
    
    @Override
    public int getLowerLimit(){
        return this.lowerLimit;
    }
    
    @Override
    public void setUpperLimit(int upperLimit){
        this.upperLimit = upperLimit;
    }
    
    @Override
    public int getUpperLimit(){
        return this.upperLimit;
    }

    @Override
    public void setConversionRate(double conversionRate){
        this.conversionRate = conversionRate;
    }

    @Override
    public double getConversionRate(){
        return this.conversionRate;
    }
}
