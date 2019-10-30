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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class RegisterController {
    @FXML TextField usernameTextField, emailTextField, firstNameTextField, lastNameTextField;
    @FXML PasswordField passwordField, confirmPasswordField;
    @FXML Button signInButton, registerButton;
    @FXML Label display;

    AccountsManage accountsManage = AccountsManage.getInstance();

    @FXML public void initialize(){

    }

    @FXML public void handleRegisterButton(ActionEvent event){
        String username = usernameTextField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();
        String email = emailTextField.getText();
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();

        if(!username.equals("") && !password.equals("") && !confirmPassword.equals("") && !email.equals("") && !firstName.equals("") && !lastName.equals("")){
            if(!accountsManage.haveAccountAvailable(username)){
                if(password.equals(confirmPassword)){
                    if(accountsManage.createAccount(username, password, email, firstName, lastName)) {
                        File dir = new File("csvData");
                        if (!dir.exists()){
                            dir.mkdirs();
                        }
                        try {
                            File file = new File("csvData/AccountData.csv");
                            file.createNewFile();
                            FileWriter fileWriter = new FileWriter(file, true);
                            BufferedWriter writer = new BufferedWriter(fileWriter);
                            writer.write(username+","+password+","+email+","+firstName+","+lastName);
                            writer.newLine();
                            writer.close();
                            display.setText("Create account success. You can now Sign In able.");

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        usernameTextField.setText("");
                        passwordField.setText("");
                        confirmPasswordField.setText("");
                        emailTextField.setText("");
                        firstNameTextField.setText("");
                        lastNameTextField.setText("");

                        //handleLoginButton(event);
                    }
                }
                else {
                    display.setText("Create account failed. Password not matched. Please try again.");
                }
            }
            else {
                display.setText("Create account failed. Username already exists. Please try again.");
            }
        }
        else {
            display.setText("Create account failed. Not complete filling information. Please try again.");
        }
    }

    @FXML public void handleLoginButton(ActionEvent event){
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
