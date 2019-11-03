package Controller;

import Class.*;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;


public class MovieSelectController {
    @FXML Label label1, label2, label3, label4, label5, label6;
    @FXML ImageView moviePoster1, moviePoster2, moviePoster3, moviePoster4, moviePoster5, moviePoster6;
    @FXML Button adminSettingButton;

    CinemaManage cinema = CinemaManage.getInstance();

    private Movie movie1 = cinema.getMovie1();
    private Movie movie2 = cinema.getMovie2();
    private Movie movie3 = cinema.getMovie3();
    private Movie movie4 = cinema.getMovie4();
    private Movie movie5 = cinema.getMovie5();
    private Movie movie6 = cinema.getMovie6();

    //Use movie0 when not found movie data
    private static Movie movie0 = new Movie("<<Coming Soon>>","<<เร็ว ๆ นี้>>","-","<<->>","/poster/ComingSoon.jpg",0, null,"<<...>>", "");

    @FXML
    public void initialize() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                //initialize movie0 in select page if get movie form CinemaManage is null
                if(movie1 == null) {movie1 = movie0; cinema.setMovie1(movie0);}
                if(movie2 == null) {movie2 = movie0; cinema.setMovie2(movie0);}
                if(movie3 == null) {movie3 = movie0; cinema.setMovie3(movie0);}
                if(movie4 == null) {movie4 = movie0; cinema.setMovie4(movie0);}
                if(movie5 == null) {movie5 = movie0; cinema.setMovie5(movie0);}
                if(movie6 == null) {movie6 = movie0; cinema.setMovie6(movie0);}

                //initialize display movie detail
                moviePoster1.setImage(new Image(movie1.getImgPosterPath()));
                moviePoster2.setImage(new Image(movie2.getImgPosterPath()));
                moviePoster3.setImage(new Image(movie3.getImgPosterPath()));
                moviePoster4.setImage(new Image(movie4.getImgPosterPath()));
                moviePoster5.setImage(new Image(movie5.getImgPosterPath()));
                moviePoster6.setImage(new Image(movie6.getImgPosterPath()));
                label1.setText(movie1.showShortDetail());
                label2.setText(movie2.showShortDetail());
                label3.setText(movie3.showShortDetail());
                label4.setText(movie4.showShortDetail());
                label5.setText(movie5.showShortDetail());
                label6.setText(movie6.showShortDetail());

                //If login with username "admin" will see Admin button
                adminSettingButton.setVisible((cinema.getAccount().getUsername().equals("admin")));
            }
        });
    }

    //Display effect ImageView when mouse in to image
    @FXML public void mouseEnterImage(MouseEvent event){
        ImageView effectedImage = (ImageView) event.getSource();
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(15);
        dropShadow.setHeight(25);
        dropShadow.setWidth(25);
        dropShadow.setColor(Color.color(0.9, 0.5, 0.3));
        effectedImage.setEffect(dropShadow);
    }

    //Remove effect ImageView when mouse out from image
    @FXML public void mouseExitImage(MouseEvent event){
        ImageView effectedImage = (ImageView) event.getSource();
        effectedImage.setEffect(null);
    }

    //Load theater round select page when mouse click image
    @FXML public void mouseClickImage(MouseEvent event){
        Movie movie = null;
        ImageView image = (ImageView) event.getSource();
        //System.out.println(image.getId());

        switch (image.getId()){
            case "moviePoster1": movie = movie1; break;
            case "moviePoster2": movie = movie2; break;
            case "moviePoster3": movie = movie3; break;
            case "moviePoster4": movie = movie4; break;
            case "moviePoster5": movie = movie5; break;
            case "moviePoster6": movie = movie6; break;
        }

        if (movie != movie0){
            Stage stage = (Stage) image.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/theater_round_select.fxml"));
            try {
                stage.setScene(new Scene(loader.load(), 1280, 720));
            } catch (IOException e) {
                e.printStackTrace();
            }
            TheaterRoundSelectController theaterRoundSelectController = loader.getController();
            theaterRoundSelectController.setMovie(movie);
            stage.show();
        }
    }

    //Load login page and remove Account from CinemaManage
    @FXML public void handleLogoutButton(ActionEvent event){
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/login.fxml"));
        try {
            stage.setScene(new Scene(loader.load(), 1280, 720));
        } catch (IOException e) {
            e.printStackTrace();
        }
        cinema.logoutAccount();
        stage.show();
    }

    //Load admin page
    @FXML public void handleAdminSettingButton(ActionEvent event){
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/admin.fxml"));
        try {
            stage.setScene(new Scene(loader.load(), 1280, 720));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.show();
    }
}
