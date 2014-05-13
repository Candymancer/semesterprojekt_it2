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
    private String caseWorker = "";
    private int pointsTransferred = 0;

    public ServiceCase(int transactionID) {
        //Caseworker defineres ud fra loginoplysninger.
        date = new Date();
        serviceCaseID = totalServiceCases;
        totalServiceCases++;
        transaction = getTransactionFromDB();
    }

    public Transaction getTransactionFromDB() {
        return DatabaseInterface.getInstance().getTransaction(transaction.getTransactionId());
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

    public int getPointsTransferred() {
        return pointsTransferred;
    }

    public String generateMessage() {
        String message;
        message = "Vi har " + date.toString() + " overført " + pointsTransferred
                + "point til deres brugerkonti hos os pga. fejl i overførsel " + transaction.getTransactionId()
                + " \nMed venlig hilsen CCS.";
        return message;
    }

    public String getCaseWorker() {
        return caseWorker;
    }

    public int getServiceCaseID() {
        return serviceCaseID;
    }

}
