package domain;

import domain.userLevel.Bronze;
import domain.userLevel.Gold;
import domain.userLevel.Silver;
import java.util.List;

public class UserManager {
    
    User user = null;

    public void addPointsToUsers(List<Transaction> transactions) {
        for (Transaction transaction : transactions) {
            int userId = transaction.getUserId();
            user = new User();
            user.getUser(userId);
            double amount = transaction.getAmount();
            double conversionRate = user.getLevel().getConversionRate();
            double points = amount * conversionRate;
            double userPoints = user.getPointBalance();
            double userAmount = user.getAmountSpentThisYear();
            user.setAmountSpentThisYear(userAmount+amount);
            user.setPointBalance(userPoints + points);
            if (user.getAmountSpentThisYear() > user.getLevel().getUpperLimit()) {
                if (user.getLevel() instanceof Bronze) {
                    user.setLevel(new Silver());
                } else if (user.getLevel() instanceof Silver) {
                    user.setLevel(new Gold());
                } // User kan ikke gå direkte fra bronze -> guld på 1 transaktion. Dette er dog også usandsynligt da der er
                 //tale om store beløb
            } 
            user.write();
        }
    }

    public void subtractPointsFromUsers(List<Transaction> transactions) {
        for (Transaction transaction : transactions) {
            int userId = transaction.getUserId();
            user = new User();
            user.getUser(userId);
            double amount = transaction.getAmount();
            double userAmount = user.getAmountSpentThisYear();
            user.setAmountSpentThisYear(userAmount-amount);
            if (user.getAmountSpentThisYear() < user.getLevel().getLowerLimit()) {
                if (user.getLevel() instanceof Gold) {
                    user.setLevel(new Silver());
                } else if (user.getLevel() instanceof Silver) {
                    user.setLevel(new Bronze());
                }
            }
            user.write();
        }
    }
}
