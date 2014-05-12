/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Parser.TransactionParser;
import domain.DatabaseInterface;
import domain.Facade;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
        User user = DatabaseInterface.getInstance().getUser(usernameInput.getText());
        if (user==null){
            //fortæl at brugeren ikke eksistere
        } else if (user.getPassword().equals(passwordInput.getText())){
            loginPane.setVisible(false);
            userPane.setVisible(true);
        }
        
        
        //tjekke om brugerens login og password matcher det indskrevne. 
            //find brugeren efter emailadresse
        //hvis type er normalbruger - vis normalview
        //hvis typen er admin - vis administratorview
    }

}
