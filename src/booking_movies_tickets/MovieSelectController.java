package booking_movies_tickets;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;


public class MovieSelectController {
    @FXML Label label1, label2, label3, label4, label5, label6;
    @FXML ImageView moviePoster1, moviePoster2, moviePoster3, moviePoster4, moviePoster5, moviePoster6;

    CinemaOperator cinema = CinemaOperator.getInstance();

    private Movie movie1 = cinema.getMovie1();
    private Movie movie2 = cinema.getMovie2();
    private Movie movie3 = cinema.getMovie3();
    private Movie movie4 = cinema.getMovie4();
    private Movie movie5 = cinema.getMovie5();
    private Movie movie6 = cinema.getMovie6();


    @FXML
    public void initialize() {
        moviePoster1.setImage(new Image(movie1.getImgPosterPath()));
        moviePoster2.setImage(new Image(movie2.getImgPosterPath()));
        moviePoster3.setImage(new Image(movie3.getImgPosterPath()));
        moviePoster4.setImage(new Image(movie4.getImgPosterPath()));
        moviePoster5.setImage(new Image(movie5.getImgPosterPath()));
        moviePoster6.setImage(new Image(movie6.getImgPosterPath()));
        label1.setText(movie1.toString());
        label2.setText(movie2.toString());
        label3.setText(movie3.toString());
        label4.setText(movie4.toString());
        label5.setText(movie5.toString());
        label6.setText(movie6.toString());

    }

    @FXML public void mouseEnterImage(MouseEvent event){
        ImageView effectedImage = (ImageView) event.getSource();
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(15);
        dropShadow.setHeight(25);
        dropShadow.setWidth(25);
        dropShadow.setColor(Color.color(0.9, 0.5, 0.3));
        effectedImage.setEffect(dropShadow);
    }

    @FXML public void mouseExitImage(MouseEvent event){
        ImageView effectedImage = (ImageView) event.getSource();
        effectedImage.setEffect(null);
    }

    @FXML public void mouseClickImage(MouseEvent event) throws IOException {
        ImageView image = (ImageView) event.getSource();
        System.out.println(image.getId());
        Stage stage = (Stage) image.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("theater_round_select.fxml"));
        stage.setScene(new Scene(loader.load(), 1280, 720));
        TheaterRoundSelectController theaterRoundSelectController = loader.getController();
        switch (image.getId()){
            case "moviePoster1": theaterRoundSelectController.setMovie(movie1); break;
            case "moviePoster2": theaterRoundSelectController.setMovie(movie2); break;
            case "moviePoster3": theaterRoundSelectController.setMovie(movie3); break;
            case "moviePoster4": theaterRoundSelectController.setMovie(movie4); break;
            case "moviePoster5": theaterRoundSelectController.setMovie(movie5); break;
            case "moviePoster6": theaterRoundSelectController.setMovie(movie6); break;
        }
        stage.show();
    }

}
