package domain;

import domain.userLevel.Bronze;
import domain.userLevel.Gold;
import domain.userLevel.Silver;
import domain.userLevel.UserLevel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import storage.DatabaseFacade;

public class User {
    private DatabaseFacade databaseFacade = new DatabaseFacade();

    private UserLevel level;
    private Date creationDate;
    private double pointBalance;
    private double amountSpentThisYear;
    private String name;
    private int userId;
    private String macaddress;
    private String email;
    private String tlf;
    private String address;

    public User() {

    }

    public User(UserLevel level, Date creationDate, double pointBalance, double amountSpentThisYear, String name,
            int userId, String macaddress, String email, String tlf, String address) {
        this.level = level;
        this.creationDate = creationDate;
        this.pointBalance = pointBalance;
        this.amountSpentThisYear = amountSpentThisYear;
        this.name = name;
        this.userId = userId;
        this.macaddress = macaddress;
        this.email = email;
        this.tlf = tlf;
        this.address = address;
    }

    public void getUser(int userId) {
        ResultSet rs = databaseFacade.findUser(userId);
        try {
        while(rs.next()){
            if(rs.getInt("level")==1){
                this.level = new Bronze();
            } else if (rs.getInt("level")==2){
                this.level = new Silver();
            } else if (rs.getInt("level")==3){
                this.level = new Gold();
            }
            this.creationDate = rs.getDate("creation_date");
            this.pointBalance = rs.getDouble("point_balance");
            this.amountSpentThisYear = rs.getDouble("amount_spent");
            this.name = rs.getString("name");
            this.userId = rs.getInt("user_id");
            this.macaddress = rs.getString("mac_address");
            this.email = rs.getString("email");
            this.tlf = rs.getString("tlf");
            this.address = rs.getString("address");
        }
        } catch (SQLException e){
            System.out.println("Fejl med database");
        }
    }

    public void write() {
        databaseFacade.writeUser(this);
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTlf() {
        return this.tlf;
    }

    public void setTlf(String tlf) {
        this.tlf = tlf;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMacadresse() {
        return this.macaddress;
    }

    public void setMacadresse(String macadresse) {
        this.macaddress = macadresse;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmountSpentThisYear() {
        return this.amountSpentThisYear;
    }

    public void setAmountSpentThisYear(double amountSpentThisYear) {
        this.amountSpentThisYear = amountSpentThisYear;
    }

    public double getPointBalance() {
        return this.pointBalance;
    }

    public void setPointBalance(double pointBalance) {
        this.pointBalance = pointBalance;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public UserLevel getLevel() {
        return this.level;
    }

    public void setLevel(UserLevel level) {
        this.level = level;
    }
}
