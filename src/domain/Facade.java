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

    public int checkIn(int userId) {
        return ParkingManager.getInstance().checkIn(userId);
    }

    public double checkOut(int parkingId) {
        return ParkingManager.getInstance().checkOut(parkingId);
    }

    public void payCash(int userId, double price, String type, String store) {
        Transaction transaction = new Transaction(type, price, store, userId);
        DatabaseInterface.getInstance().writeTransaction(transaction);
        double pointsGiven = price * 100;
        DatabaseInterface.getInstance().addPointsToUser(userId, pointsGiven);
    }

    public boolean payPP(int userId, double price, String type, String store) {
        double points = price * 100;
        if (points > DatabaseInterface.getInstance().getUser(userId).getPointBalance()) {
            return false;
        } else {
            Transaction transaction = new Transaction(type, price, store, userId);
            DatabaseInterface.getInstance().writeTransaction(transaction);
            DatabaseInterface.getInstance().addPointsToUser(userId, points*-1);
            return true;
        }
    }

}
