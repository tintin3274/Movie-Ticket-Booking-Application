package Controller;

import Class.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterController {
    @FXML TextField usernameTextField, passwordTextField, confirmPasswordTextField, emailTextField, firstNameTextField, lastNameTextField;
    @FXML Button signInButton, registerButton;
    @FXML Text display;

    AccountsManage accountsManage = AccountsManage.getInstance();

    @FXML public void initialize(){

    }

    @FXML public void handleRegisterButton(ActionEvent event){
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        String confirmPassword = confirmPasswordTextField.getText();
        String email = emailTextField.getText();
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();

        if(!username.equals("") && !password.equals("") && !confirmPassword.equals("")){
            if(!accountsManage.haveAccountAvailable(username)){
                if(password.equals(confirmPassword)){
                    if(accountsManage.createAccount(username, password, email, firstName, lastName)) loadLoginPage(event);
                }
                else {
                    display.setText("Password not matched.");
                }
            }
            else {
                display.setText("Username already exists.");
            }
        }
        else {
            display.setText("Not complete filling information.");
        }
    }

    @FXML public void loadLoginPage(ActionEvent event){
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/login.fxml"));
        try {
            stage.setScene(new Scene(loader.load(), 1280, 720));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.show();
    }
}
