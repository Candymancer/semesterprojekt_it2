package domain;

import domain.userLevel.UserLevel;
import java.util.Date;

public class User {
//    private DatabaseInterface databaseFacade = DatabaseInterface.getInstance();

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
    private String password;

    public User() {

    }

    public User(UserLevel level, Date creationDate, double pointBalance, double amountSpentThisYear, String name,
            int userId, String macaddress, String email, String tlf, String address, String password) {
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
        this.password = password;
    }

    public void write() {
        DatabaseInterface.getInstance().writeUser(this);
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
