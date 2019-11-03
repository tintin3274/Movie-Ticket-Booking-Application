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
        primaryStage.setTitle("Movie Ticket Booking Application by Jittin Jindanoraseth 6110405949");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
        primaryStage.setResizable(false);
    }


    public static void main(String[] args) {
        LoadAccountData loadAccountData = new LoadAccountData();
        LoadBookingData loadBookingData = new LoadBookingData();
        LoadMovieData loadMovieData = new LoadMovieData();
        LoadMoviesShowingData loadMoviesShowingData = new LoadMoviesShowingData();
        LoadShowTime loadShowTime = new LoadShowTime();

        //Call method read .csv file
        loadAccountData.readAccountData();
        loadMovieData.readMovieData();
        loadMoviesShowingData.readMoviesShowingData();
        loadShowTime.readShowTimeData();
        loadBookingData.readBookingData();

        launch(args);
    }
}
