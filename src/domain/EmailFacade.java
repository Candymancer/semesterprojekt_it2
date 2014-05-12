/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author Bastian
 */
public class EmailFacade {

    private static EmailFacade instance = null;

    private EmailFacade() {

    }

    public static EmailFacade getInstance() {
        if (instance == null) {
            instance = new EmailFacade();
        }
        return instance;
    }

    public String getUserEmail(int userId) {
        String email;
        email = userId + "";
        return email;
    }

    public void sendEmail(String message, int recipient) {
        String email = "";
        email = DatabaseInterface.getInstance().getUser(recipient).getEmail();
        System.out.println("Modtager: " + email + " Message:" + message);
    }
}
