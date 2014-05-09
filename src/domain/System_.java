package domain;

import java.sql.ResultSet;
import java.util.List;

public class System_ {
    private final TransactionManager transactionManager = new TransactionManager();
    private final UserManager userManager = new UserManager();
    
    public void receiveTransactions(List<Transaction> transactionList) {
        transactionManager.writeTransactions(transactionList);
        userManager.addPointsToUsers(transactionList);
    }

    public void checkExpiredTransactions() {
        ResultSet rs = DatabaseInterface.getInstance().getExpiredTransactions();
        List<Transaction> transactionList = DatabaseInterface.getInstance().processResultSet(rs);
        userManager.subtractPointsFromUsers(transactionList);
        transactionManager.setTransactionsNotActive(transactionList);
        transactionManager.writeTransactions(transactionList);
    }
    
    public User findUser(int userId){
        User user = DatabaseInterface.getInstance().getUser(userId);
        return user;
    }
}
