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
public class Silver extends AbstractLevel{

//    private double conversionRate = 1.5;
//    private int lowerLimit = 5000;
//    private int upperLimit = 9999;

    public Silver() {
        conversionRate = 1.5;
        lowerLimit = 5000;
        upperLimit = 9999;
    }

    @Override
    public int toInt() {
        return 2;
    }
    
    @Override
    public String toString(){
        return "Silver";
    }
}
