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
}
