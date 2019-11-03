package Controller;

import Class.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML TextField usernameTextField;
    @FXML PasswordField passwordField;
    @FXML Button loginButton, signUpButton;
    @FXML Label display;

    AccountsManage accountsManage = AccountsManage.getInstance();
    CinemaManage cinema = CinemaManage.getInstance();


    @FXML public void initialize(){

    }

    //Check Input Username and Password and set Account to CinemaManage
    @FXML public void handleLoginButton(ActionEvent event){
        if(accountsManage.haveAccountAvailable(usernameTextField.getText())){
            if(accountsManage.loginAccount(usernameTextField.getText(), passwordField.getText())){
                display.setText("Login success.");
                cinema.setAccount(accountsManage.getAccount(usernameTextField.getText()));
                loginSuccess(event);
            }
            else {
                display.setText("Login failed. Incorrect password. Please try again.");
            }
        }
        else {
            display.setText("Login failed. Incorrect username. Please try again or Sign Up to create account.");
        }
    }

    //Load register page
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

    //Load movie select page
    @FXML public void loginSuccess(ActionEvent event){
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/movie_select.fxml"));
        try {
            stage.setScene(new Scene(loader.load(), 1280, 720));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.show();
    }

    //Load information page
    @FXML public void handleInfoButton(){
        Stage stage = (Stage) display.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/aboutme.fxml"));
        try {
            stage.setScene(new Scene(loader.load(), 1280, 720));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.show();
    }
}
