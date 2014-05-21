/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Parser.TransactionParser;
import domain.DatabaseInterface;
import domain.Facade;
import domain.ServiceCase;
import domain.ServiceCaseManager;
import domain.Transaction;
import domain.User;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 * FXML Controller class
 *
 * @author emilfrisk
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label niveauLabel;

    Facade facade = new Facade();

    @FXML
    private TextField userIdInputField;
    @FXML
    private Label nameLabel;
    @FXML
    private Label creationDateLabel;
    @FXML
    private Label amountSpentLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label addressLabel;
    @FXML
    private Button checkExpiredPointsButton;
    @FXML
    private Button recieveTransactionsButton;
    @FXML
    private Label checkExpiredPointsLabel;
    @FXML
    private Label recieveTransactionsLabel;
    @FXML
    private TabPane userPane;
    @FXML
    private ImageView logInImage;
    @FXML
    private TextField usernameInput;
    @FXML
    private PasswordField passwordInput;
    @FXML
    private Button logInButton;
    @FXML
    private Pane loginPane;
    @FXML
    private Button getInfoButton;
    @FXML
    private TextArea transactionOutput;
    @FXML
    private TextField addPointsInput;
    @FXML
    private Button addPointsButton;
    @FXML
    private TextField transactionIdInput;
    @FXML
    private Button checkinButton;
    @FXML
    private Button checkoutButton;
    @FXML
    private TextField userIdInputTF;
    @FXML
    private TextField parkingIdInputTF;
    @FXML
    private Button payCashButton;
    @FXML
    private Button payPPButton;
    @FXML
    private Label costOutput;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void displayNiveau(ActionEvent event) {
        int userId = Integer.parseInt(userIdInputField.getText());
        User user = facade.findUser(userId);

        nameLabel.setText(user.getName());
        niveauLabel.setText(user.getLevel().toString());
        creationDateLabel.setText(user.getCreationDate().toString());
        amountSpentLabel.setText("" + user.getAmountSpentThisYear());
        emailLabel.setText(user.getEmail());
        addressLabel.setText(user.getAddress());

    }

    @FXML
    private void checkExpiredPointsAction(ActionEvent event) {
        facade.checkExpiredTransactions();
        checkExpiredPointsLabel.setText("Checked Transactions!");
    }

    @FXML
    private void recieveTransactionsAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File file = fileChooser.showOpenDialog(null);

        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("TXT Files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(filter);

        TransactionParser transactionParser = new TransactionParser();

        List<Transaction> transactionList = null;
        try {
            transactionList = transactionParser.readFile(file);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            Logger lgr = Logger.getLogger(FXMLDocumentController.class.getName());
            lgr.log(Level.SEVERE, e.getMessage(), e);
        }

        facade.recieveTransactions(transactionList);

        recieveTransactionsLabel.setText("Transactions processed!");
    }

    @FXML
    private void checkLogInInformation(ActionEvent event) {
        User user = facade.findUser(usernameInput.getText());
        if (user == null) {
            //fortæl at brugeren ikke eksistere
        } else if (user.getPassword().equals(passwordInput.getText())) {
            loginPane.setVisible(false);
            userPane.setVisible(true);
        }

        //tjekke om brugerens login og password matcher det indskrevne. 
        //find brugeren efter emailadresse
        //hvis type er normalbruger - vis normalview
        //hvis typen er admin - vis administratorview
    }

    @FXML
    private void getTransactionInfo(ActionEvent event) {
        String error = "Invalid input.";
        try {
            Transaction transaction = facade.createServicecase(Integer.parseInt(transactionIdInput.getText())).getTransaction();
            User user = DatabaseInterface.getInstance().getUser(transaction.getUserId());
            transactionOutput.setText("TransactionId: " + transaction.getTransactionId() + "\n Store: " + transaction.getStore() + "\n User: " + user.getName() + "\n Amount: " + transaction.getAmount());
        } catch (Exception ex) {
            transactionOutput.setText(error);
        }
    }

    @FXML
    private void addPoints(ActionEvent event) {
        String error = "Invalid input.";
        String success = "Done.";
        try {
            facade.addPoints(ServiceCaseManager.getInstance().getCurrentServiceCase(), ServiceCaseManager.getInstance().getCurrentServiceCase().getTransaction().getUserId(), Integer.parseInt(addPointsInput.getText()));
            addPointsInput.setText(success);
        } catch (Exception ex) {
            addPointsInput.setText(error);
        }
    }

    @FXML
    private void checkIn(ActionEvent event) {
        int parkingId = facade.checkIn(Integer.parseInt(userIdInputField.getText()));
        userIdInputField.setText("Check in confirmed. Parking id is " + parkingId);
    }

    @FXML
    private void checkOut(ActionEvent event) {
        double price = facade.checkOut(Integer.parseInt(parkingIdInputTF.getText()));
        double pricePP = price * 100;
        costOutput.setText(price + " kr. / " + pricePP + " PP");
        payCashButton.setVisible(true);
        payPPButton.setVisible(true);
    }

    @FXML
    private void payCash(ActionEvent event) {
        //opret transaktion, så rette mængde PP går til brugeren
        costOutput.setText("Payment confirmed");
        payCashButton.setVisible(false);
        payPPButton.setVisible(false);
    }

    @FXML
    private void payPP(ActionEvent event) {

        // if (brugeren har nok PP til at betale
        // ) {
        // betal fra brugerens PP
        costOutput.setText("Payment confirmed");
        payCashButton.setVisible(false);
        payPPButton.setVisible(false);
        // }

    }

}
