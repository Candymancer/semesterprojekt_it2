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
public class Gold extends AbstractLevel {

    public Gold() {
        conversionRate = 2;
        lowerLimit = 10000;   
    }
    
    @Override
    public int toInt(){
        return 3;
    }
    
    @Override
    public String toString(){
        return "Gold";
    }

}
