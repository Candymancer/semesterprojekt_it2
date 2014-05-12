package domain;

import java.util.List;

public class TransactionManager {

    Transaction transaction = null;

    public void writeTransactions(List<Transaction> transactionList) {
        for (Transaction t : transactionList) {
            t.write();
        }
    }

    public void updateTransactions(List<Transaction> transactionList) {
        for (Transaction t : transactionList) {
            t.update();
        }
    }

    public void setTransactionsNotActive(List<Transaction> transactionList) {
        for (Transaction t : transactionList) {
            t.setActive(false);
        }
    }
}
