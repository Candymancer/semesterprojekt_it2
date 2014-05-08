package domain;

import java.util.Date;
import storage.DatabaseFacade;

public class Transaction {
	private String type;
	private Date date;
	private int transactionId;
	private double amount;
	private String store;
	private boolean active;
	private int userId;
        
        private DatabaseFacade databaseFacade = null;
        
        public Transaction(){
            
        }
        
        public Transaction(String type, Date date, int transactionId, double amount, 
                String store, boolean active, int userId){
                this.type = type;
                this.date = date;
                this.transactionId = transactionId;
                this.amount = amount;
                this.store = store;
                this.active = active;
                this.userId = userId;
        }

	public void write() {
		databaseFacade = new DatabaseFacade();
                databaseFacade.writeTransaction(this);
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public boolean getActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getStore() {
		return this.store;
	}

	public void setStore(String store) {
		this.store = store;
	}

	public double getAmount() {
		return this.amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getTransactionId() {
		return this.transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}
}