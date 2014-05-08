/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javafxapplication13;

import domain.Facade;
import domain.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
        
        niveauLabel.setText(user.getLevel().toString());
    }
    
}
