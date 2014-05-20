package domain;

import java.util.List;

public class Facade {

    System_ system_ = new System_();

    public void recieveTransactions(List<Transaction> transactionList) {
        system_.receiveTransactions(transactionList);
    }

    public void checkExpiredTransactions() {
        system_.checkExpiredTransactions();
    }

    public User findUser(int userId) {
        User user = system_.findUser(userId);
        return user;
    }

    public User findUser(String email) {
        User user = system_.findUser(email);
        return user;
    }

    public ServiceCase createServicecase(int transactionId) {
        ServiceCase sc = ServiceCaseManager.getInstance().createServiceCase(transactionId);
        return sc;
    }

    public void addPoints(ServiceCase sc, int customerId, int points) {
        DatabaseInterface.getInstance().addPointsToUser(customerId, points);
        EmailFacade.getInstance().sendEmail(sc.generateMessage(), points);
        System.out.println("Pointoverførsel afsluttet. Email sendt til kunde.");
    }
    public int checkIn(int userId){
        int parkingId;
        return parkingId;
    }
    
    public void checkOut(int parkingId){
        
    }
    
}
