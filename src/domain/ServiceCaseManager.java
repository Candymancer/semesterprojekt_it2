/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author Bastian
 */
public class ServiceCaseManager {

    private ServiceCase sc;

    //ServiceCase skal gemmes i DB
    public void createServiceCase(int transactionID) {
        sc = new ServiceCase(transactionID);
    }

    public void getServicecase(int transactionID) {
        //DatabaseInterface.getInstance().getServiceCase();
    }

    public void sendEmail(ServiceCase sc) {
        EmailFacade.getInstance().sendEmail(sc.generateMessage(), sc.getTransaction().getUserId());
    }

}
