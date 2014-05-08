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
public interface UserLevel {
    
    public void setLowerLimit(int lowerLimit);
    
    public int getLowerLimit();
    
    public void setUpperLimit(int higherLimit);
    
    public int getUpperLimit();

    public void setConversionRate(double conversionRate);

    public double getConversionRate();
    
    public int toInt();

}
