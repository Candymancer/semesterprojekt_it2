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
}