package domain;

import domain.Transaction;
import domain.User;
import domain.userLevel.Bronze;
import domain.userLevel.Gold;
import domain.userLevel.Silver;
import domain.userLevel.UserLevel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseInterface {

    private final String db = "semesterprojekt";
    private final String user = "emilfrisk";
    private final String password = "";

    private static DatabaseInterface instance = null;

    private DatabaseInterface() {
    }

    public static DatabaseInterface getInstance() {
        if (instance == null) {
            instance = new DatabaseInterface();
        }
        return instance;
    }

    public void writeUser(User user) {
        UserLevel level = user.getLevel();
//        Date creationDate = user.getCreationDate();
//        Timestamp creationDateTimestamp = new Timestamp(creationDate.getTime());
        double pointBalance = user.getPointBalance();
        double amountSpentThisYear = user.getAmountSpentThisYear();
//        String name = user.getName();
        int userId = user.getUserId();
//        String macaddress = user.getMacadresse();
//        String email = user.getEmail();
//        String tlf = user.getTlf();
//        String address = user.getAddress();

        String query = "UPDATE users SET point_balance = " + pointBalance + ", amount_spent = " + amountSpentThisYear + ", level = " + level.toInt() + " WHERE user_id = " + userId + ";";

        executeUpdate(query);
    }

    public void writeTransaction(Transaction transaction) {
        String type = transaction.getType();
        Date date = transaction.getDate();
        Timestamp dateTimestamp = new Timestamp(date.getTime());
        int transactionId = transaction.getTransactionId();
        double amount = transaction.getAmount();
        String store = transaction.getStore();
        boolean active = transaction.getActive();
        int userId = transaction.getUserId();

        String query = "insert into transactions values (\n"
                + "	'" + type + "',\n"
                + "	'" + dateTimestamp + "',\n"
                + "	" + transactionId + ",\n"
                + "	" + amount + ",\n"
                + "	'" + store + "',\n"
                + "	" + active + ",\n"
                + "	" + userId + "\n"
                + "	);\n"
                + "	";

        executeUpdate(query);
    }

    public void updateTransaction(Transaction transaction) {
//        String type = transaction.getType();
//        Date date = transaction.getDate();
//        Timestamp dateTimestamp = new Timestamp(date.getTime());
//        int transactionId = transaction.getTransactionId();
//        double amount = transaction.getAmount();
//        String store = transaction.getStore();
        boolean active = transaction.getActive();
//        int userId = transaction.getUserId();

        String query = "UPDATE transactions SET active = " + active + ";";

        executeUpdate(query);
    }

    private ResultSet executeQuery(String query) {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        String url = "jdbc:postgresql://localhost/" + db;

        try {
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            rs = st.executeQuery("" + query);

        } catch (SQLException e) {
            if (e != null) {
                Logger lgr = Logger.getLogger(DatabaseInterface.class.getName());
                lgr.log(Level.SEVERE, e.getMessage(), e);
            }
        } finally {
            try {
                if (con != null) {
                    con.close();
                }

            } catch (SQLException e) {
                Logger lgr = Logger.getLogger(DatabaseInterface.class.getName());
                lgr.log(Level.SEVERE, e.getMessage(), e);
            }
        }
        return rs;
    }

    private void executeUpdate(String query) {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        String url = "jdbc:postgresql://localhost/" + db;

        try {
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            st.executeUpdate("" + query);

        } catch (SQLException e) {
            if (e != null) {
                Logger lgr = Logger.getLogger(DatabaseInterface.class.getName());
                lgr.log(Level.SEVERE, e.getMessage(), e);
            }
        } finally {
            try {
                if (con != null) {
                    con.close();
                }

            } catch (SQLException e) {
                Logger lgr = Logger.getLogger(DatabaseInterface.class.getName());
                lgr.log(Level.SEVERE, e.getMessage(), e);
            }
        }

    }

    public ResultSet getExpiredTransactions() {

        Calendar calendar = Calendar.getInstance();

        int day = (24 * 60 * 60 + 1) * 1000;

        int year = (365 * 24 * 60 * 60 + 1) * 1000;

        long tenYears = Long.parseLong("315569259747");

        long time = calendar.getTimeInMillis();

        long timeminustenyears = time - tenYears;

        Timestamp timestampBefore = new Timestamp(timeminustenyears);

        Timestamp timestampNow = new Timestamp(time);

        String query = "SELECT \n"
                + "  transactions.*\n"
                + "FROM \n"
                + "  transactions\n"
                + "WHERE \n"
                + "  transactions.date >= '" + timestampBefore + "'\n"
                + "	AND transactions.date <= '" + timestampNow + "'"
                + " AND transactions.active = true;";

        ResultSet rs = executeQuery(query);

        if (rs == null) {
            return null;
        }

        return rs;
    }

    public ResultSet findUser(int userId) {
        String query = "SELECT \n"
                + "  users.*\n"
                + "FROM \n"
                + "  public.users\n"
                + "WHERE \n"
                + "  users.user_id = " + userId + ";";

        ResultSet rs = executeQuery(query);

        if (rs == null) {
            return null;
        }

        return rs;
    }

    public ResultSet findUser(String email) {
        String query = "SELECT \n"
                + "  users.*\n"
                + "FROM \n"
                + "  public.users\n"
                + "WHERE \n"
                + "  users.email = '" + email + "';";

        ResultSet rs = executeQuery(query);

        if (rs == null) {
            return null;
        }

        return rs;
    }

    public User getUser(int userId) {
        User user = new User();
        ResultSet rs = DatabaseInterface.getInstance().findUser(userId);
        try {
            while (rs.next()) {
                if (rs.getInt("level") == 1) {
                    user.setLevel(new Bronze());
                } else if (rs.getInt("level") == 2) {
                    user.setLevel(new Silver());
                } else if (rs.getInt("level") == 3) {
                    user.setLevel(new Gold());
                }
                user.setCreationDate(rs.getDate("creation_date"));
                user.setPointBalance(rs.getDouble("point_balance"));
                user.setAmountSpentThisYear(rs.getDouble("amount_spent"));
                user.setName(rs.getString("name"));
                user.setUserId(rs.getInt("user_id"));
                user.setMacadresse(rs.getString("mac_address"));
                user.setEmail(rs.getString("email"));
                user.setTlf(rs.getString("tlf"));
                user.setAddress(rs.getString("address"));
                user.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            System.out.println("Fejl med database");
        }
        return user;
    }

    public User getUser(String email) {
        User user = new User();
        ResultSet rs = DatabaseInterface.getInstance().findUser(email);
        try {
            while (rs.next()) {
                if (rs.getInt("level") == 1) {
                    user.setLevel(new Bronze());
                } else if (rs.getInt("level") == 2) {
                    user.setLevel(new Silver());
                } else if (rs.getInt("level") == 3) {
                    user.setLevel(new Gold());
                }
                user.setCreationDate(rs.getDate("creation_date"));
                user.setPointBalance(rs.getDouble("point_balance"));
                user.setAmountSpentThisYear(rs.getDouble("amount_spent"));
                user.setName(rs.getString("name"));
                user.setUserId(rs.getInt("user_id"));
                user.setMacadresse(rs.getString("mac_address"));
                user.setEmail(rs.getString("email"));
                user.setTlf(rs.getString("tlf"));
                user.setAddress(rs.getString("address"));
                user.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            System.out.println("Fejl med database");
        }
        return user;
    }

    public List<Transaction> processResultSet(ResultSet rs) {
        List<Transaction> transactionList = new ArrayList<>();
        try {
            while (rs.next()) {
                String type = rs.getString("type");
                Date date = rs.getDate("date");
                int transactionId = rs.getInt("transaction_id");
                double amount = rs.getDouble("amount");
                String store = rs.getString("store");
                boolean active = rs.getBoolean("active");
                int userId = rs.getInt("user_id");

                Transaction transaction = new Transaction(type, date, transactionId, amount, store, active, userId);
                transactionList.add(transaction);
            }
        } catch (SQLException e) {
            Logger lgr = Logger.getLogger(DatabaseInterface.class.getName());
            lgr.log(Level.SEVERE, e.getMessage(), e);
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                Logger lgr = Logger.getLogger(DatabaseInterface.class.getName());
                lgr.log(Level.SEVERE, e.getMessage(), e);
            }
        }

        return transactionList;
    }

    public Transaction getTransaction(int transactionId) {
        //Lav query
        String SQLQuery = "SELECT * FROM Transaction WHERE transactionId=" + transactionId;
        
        ResultSet rs = executeQuery(SQLQuery);
        String transactionInfo = "";
        try{
             //vis al informationen fra vores query som en mere brugbar string
        transactionInfo = rs.getString(1);
       //udskriv vores resultatString
            System.out.println(transactionInfo);
        }catch(SQLException ex){
            
        }
        //Split resultatstring op i flere dele, så de kan bruges seperat
        String[] transactionParts = transactionInfo.split(" ");
        //Information i transaction tabellen i DB baseret på ER-diagrammet
        String type, deactivateTransaction, calcPoints, date, ID, store, amount, active, userID;
        //placering i resultsættet er getværk.
        type = transactionParts[0];
        deactivateTransaction = transactionParts[1];
        calcPoints = transactionParts[2];
        date = transactionParts[3];
        ID = transactionParts[4];
        store = transactionParts[5];
        amount = transactionParts[6];
        active = transactionParts[7];
        userID = transactionParts[8];
        Transaction result;
        //lav transaction objekt baseret på informationer hentet fra DB
        result =  new Transaction(type, new java.sql.Date(Long.parseLong(date)), transactionId, transactionId, store, true, transactionId);
        return result;
    }
}
