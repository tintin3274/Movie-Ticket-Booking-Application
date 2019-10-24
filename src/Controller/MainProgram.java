package Controller;

import Class.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainProgram extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/login.fxml"));
        primaryStage.setTitle("Booking Movies Tickets 6110405949");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
        primaryStage.setResizable(false);
    }


    public static void main(String[] args) {
        LoadAccountData loadAccountData = new LoadAccountData();
        LoadBookingData loadBookingData = new LoadBookingData();
        LoadMovieData loadMovieData = new LoadMovieData();
        LoadMovieShowTime loadMovieShowTime = new LoadMovieShowTime();
        LoadShowTime loadShowTime = new LoadShowTime();

        loadAccountData.readAccountData();
        loadMovieData.readMovieData();
        loadMovieShowTime.readMovieShowTimeData();
        loadShowTime.readShowTimeData();
        loadBookingData.readBookingData();

        launch(args);
    }
}
