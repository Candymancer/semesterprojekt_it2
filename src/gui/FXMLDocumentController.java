/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import domain.Facade;
import domain.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
        amountSpentLabel.setText(""+user.getAmountSpentThisYear());
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
    }
    
}
