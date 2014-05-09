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
public class Bronze extends AbstractLevel {

    public Bronze() {
        conversionRate = 1;
        lowerLimit = 0;
        upperLimit = 4999;
    }

    @Override
    public int toInt() {
        return 1;
    }

    @Override
    public String toString() {
        return "Bronze";
    }

}
