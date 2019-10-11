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

public class LoginController {
    @FXML TextField usernameTextField, passwordTextField;
    @FXML Button loginButton, signUpButton;
    @FXML Text display;

    AccountsManage accountsManage = AccountsManage.getInstance();

    @FXML public void initialize(){

    }

    @FXML public void handleLoginButton(ActionEvent event){
        if(accountsManage.haveAccountAvailable(usernameTextField.getText())){
            if(accountsManage.loginAccount(usernameTextField.getText(), passwordTextField.getText())){
                display.setText("Login Success.");
            }
            else {
                display.setText("Incorrect password.");
            }
        }
        else {
            display.setText("Incorrect username.");
        }
    }

    @FXML public void handleSignUpButton(ActionEvent event){
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/register.fxml"));
        try {
            stage.setScene(new Scene(loader.load(), 1280, 720));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.show();
    }
}
