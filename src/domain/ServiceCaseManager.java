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

    private static ServiceCaseManager instance;
    private ServiceCase sc;

    private ServiceCaseManager() {

    }

    public static ServiceCaseManager getInstance() {
        if (instance == null) {
            instance = new ServiceCaseManager();
        }
        return instance;
    }

    //ServiceCase skal gemmes i DB
    public ServiceCase createServiceCase(int transactionID) {
        sc = new ServiceCase(transactionID);
        return sc;
    }

    public ServiceCase getServicecase(int transactionID) {
        ServiceCase DBsc;
        //sc = DatabaseInterface.getInstance().getServiceCase();
        return DBsc;
    }

    public void addpoints(ServiceCase sc, int point) {
        sc.setPointsTransferred(point);
        DatabaseInterface.getInstance().addPointsToUser(sc.getTransaction().getUserId(), point);
    }

    public void sendEmail(ServiceCase sc) {
        EmailFacade.getInstance().sendEmail(sc.generateMessage(), sc.getTransaction().getUserId());
    }

}
