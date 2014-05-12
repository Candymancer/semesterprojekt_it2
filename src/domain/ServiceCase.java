/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.Date;

/**
 *
 * @author Bastian
 */
public class ServiceCase {

    private int serviceCaseID;
    private static int totalServiceCases = 0;
    private Transaction transaction;
    private Date date;
    
    private String caseWorker;
    private int pointsTransferred = 0;

    public ServiceCase(int transactionID) {
        //Caseworker defineres ud fra loginoplysninger.
        date = new Date();
        serviceCaseID = totalServiceCases;
        totalServiceCases++;
        transaction = getTransaction(transactionID);
    }

    public Transaction getTransaction(int transactionId) {
        return DatabaseInterface.getInstance().getTransaction(transactionId);
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public Date getDate() {
        return date;
    }

    public void setPointsTransferred(int points) {
        pointsTransferred = points;
    }

    public String generateMessage() {
        String message;
        message = "Vi har " + date.toString() + " overført " + pointsTransferred
                + "point til deres brugerkonti hos os pga. fejl i overførsel " + transaction.getTransactionId()
                + " \nMed venlig hilsen CCS.";
        return message;
    }

}
