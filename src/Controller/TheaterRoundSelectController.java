package Controller;

import Class.*;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;

public class TheaterRoundSelectController {
    private Movie movie;
    private MediaPlayer mp;
    private boolean fullscreen;

    @FXML GridPane rateSystemGridPane, roundGridPane;
    @FXML ImageView moviePoster;
    @FXML Label textTitleEn, textTitleTh, textGenre, textLength, textReleaseDate;
    @FXML TextArea textAreaDescription;
    @FXML MediaView mv;
    @FXML Pane pane;

    //@FXML Text theaterName1, theaterName2, theaterName3, theaterName4, theaterName5, theaterName6;
    CinemaManage cinema = CinemaManage.getInstance();

    @FXML public void initialize(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                //initialize Pane for MediaView
                fullscreen = false;
                pane.setVisible(false);
                pane.setPrefWidth(1280);
                pane.setPrefHeight(720);

                //Display information of movie
                textTitleEn.setText(movie.getNameEn());
                textTitleTh.setText(movie.getNameTh());
                textGenre.setText(movie.getGenre());
                textLength.setText(movie.getLength()+" นาที");
                textReleaseDate.setText(""+movie.getReleaseDate());
                moviePoster.setImage(new Image(movie.getImgPosterPath()));
                textAreaDescription.setText(movie.getDescription());

                //Display video trailer and initialize player function
                File file = new File(movie.getVideoPath());
                Media media = new Media(file.toURI().toString());
                mp = new MediaPlayer(media);

                mv.setMediaPlayer(mp);
                mv.setPreserveRatio(true);
                mp.setAutoPlay(true);
                mp.setCycleCount(MediaPlayer.INDEFINITE);
                mv.setOnScroll(new EventHandler<ScrollEvent>() {
                    @Override
                    public void handle(ScrollEvent event) {
                        if(event.getDeltaX() > 0) mp.setVolume(mp.getVolume()-0.10);
                        else if (event.getDeltaX() < 0) mp.setVolume(mp.getVolume()+0.10);
                        else if (event.getDeltaY() > 0) mp.seek(mp.getCurrentTime().add(Duration.seconds(5)));
                        else if (event.getDeltaY() < 0) mp.seek(mp.getCurrentTime().add(Duration.seconds(-5)));
                    }
                });

                mv.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 1){
                            switch (mp.getStatus()){
                                case PLAYING: mp.pause(); break;
                                case STOPPED:
                                case PAUSED:
                                case READY:
                                    mp.play(); break;
                            }
                        }
                        else if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2){
                            fullscreen = !fullscreen;
                            if(fullscreen){
                                pane.setVisible(true);
                                mv.setFitWidth(1280);
                                mv.setFitHeight(720);

                                mv.setLayoutX(0);
                                double ratio = (double) media.getWidth()/(double) media.getHeight();
                                double height = 1280/ratio;
                                mv.setLayoutY(((720-height)/2));
                            }
                            else {
                                pane.setVisible(false);
                                mv.setLayoutX(384);
                                mv.setLayoutY(176);
                                mv.setFitWidth(355.56);
                                mv.setFitHeight(200);
                            }
                        }
                    }
                });

                //Display rate and system of movie
                ImageView image;
                switch (movie.getRate()){
                    case "ทั่วไป": image = new ImageView("/image/image_detail/rating_1.png");break;
                    case "น 13+": image = new ImageView("/image/image_detail/rating_2.png");break;
                    case "น 15+": image = new ImageView("/image/image_detail/rating_3.png");break;
                    case "น 18+": image = new ImageView("/image/image_detail/rating_4.png");break;
                    case "ฉ 20+": image = new ImageView("/image/image_detail/rating_5.png");break;
                    default: image = new ImageView("/image/image_detail/rating_0.png");break;
                }
                image.setFitWidth(67);
                image.setFitHeight(35);
                rateSystemGridPane.add(image, 0,0);

                int i = 1;
                for(String type:movie.getSystemType()){
                    switch (type){
                        case "2D": image = new ImageView("/image/image_detail/digital.png");break;
                        case "4K": image = new ImageView("/image/image_detail/4k.png");break;
                        case "3D": image = new ImageView("/image/image_detail/3d.png");break;
                        case "IMAX Digital 2D": image = new ImageView("/image/image_detail/imax-digital.png");break;
                        case "4DX": image = new ImageView("/image/image_detail/4dx.png");break;
                        default: image = new ImageView("/image/image_detail/rating_0.png");break;
                    }
                    image.setFitWidth(67);
                    image.setFitHeight(35);
                    rateSystemGridPane.add(image, i,0);
                    i++;
                }

                //Display round from each theater
                i=1;
                for(Round round: cinema.getTheater1().getRoundsList()){
                    if(round.getMovie() == movie){
                        Button button = new Button(round.getTime());
                        button.setId("theater_1_round_"+cinema.getTheater1().getRoundsList().indexOf(round));
                        button.setOnAction(event ->handleRoundButton(event));
                        button.setMaxSize(70, 40);
                        button.setStyle("-fx-font-size: 14; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-color: #F1948A");
                        roundGridPane.add(button, i,1);
                        i++;
                    }
                }

                i=1;
                for(Round round: cinema.getTheater2().getRoundsList()){
                    if(round.getMovie() == movie){
                        Button button = new Button(round.getTime());
                        button.setId("theater_2_round_"+cinema.getTheater2().getRoundsList().indexOf(round));
                        button.setOnAction(event ->handleRoundButton(event));
                        button.setMaxSize(70, 40);
                        button.setStyle("-fx-font-size: 14; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-color: #F1948A");
                        roundGridPane.add(button, i,4);
                        i++;
                    }
                }

                i=1;
                for(Round round: cinema.getTheater3().getRoundsList()){
                    if(round.getMovie() == movie){
                        Button button = new Button(round.getTime());
                        button.setId("theater_3_round_"+cinema.getTheater3().getRoundsList().indexOf(round));
                        button.setOnAction(event ->handleRoundButton(event));
                        button.setMaxSize(70, 40);
                        button.setStyle("-fx-font-size: 14; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-color: #F1948A");
                        roundGridPane.add(button, i,7);
                        i++;
                    }
                }

                i=1;
                for(Round round: cinema.getTheater4().getRoundsList()){
                    if(round.getMovie() == movie){
                        Button button = new Button(round.getTime());
                        button.setId("theater_4_round_"+cinema.getTheater4().getRoundsList().indexOf(round));
                        button.setOnAction(event ->handleRoundButton(event));
                        button.setMaxSize(70, 40);
                        button.setStyle("-fx-font-size: 14; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-color: #F1948A");
                        roundGridPane.add(button, i,10);
                        i++;
                    }
                }

                i=1;
                for(Round round: cinema.getTheater5().getRoundsList()){
                    if(round.getMovie() == movie){
                        Button button = new Button(round.getTime());
                        button.setId("theater_5_round_"+cinema.getTheater5().getRoundsList().indexOf(round));
                        button.setOnAction(event ->handleRoundButton(event));
                        button.setMaxSize(70, 40);
                        button.setStyle("-fx-font-size: 14; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-color: #F1948A");
                        roundGridPane.add(button, i,13);
                        i++;
                    }
                }

                i=1;
                for(Round round: cinema.getTheater6().getRoundsList()){
                    if(round.getMovie() == movie){
                        Button button = new Button(round.getTime());
                        button.setId("theater_6_round_"+cinema.getTheater6().getRoundsList().indexOf(round));
                        button.setOnAction(event ->handleRoundButton(event));
                        button.setMaxSize(70, 40);
                        button.setStyle("-fx-font-size: 14; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-color: #F1948A");
                        roundGridPane.add(button, i,16);
                        i++;
                    }
                }
            }
        });
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    //Select round and Load seat select page
    @FXML public void handleRoundButton(ActionEvent event) {
        Button button = (Button) event.getSource();
        //System.out.println(button.getId());
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/seat_select.fxml"));
        try {
            stage.setScene(new Scene(loader.load(), 1280, 720));
        } catch (IOException e) {
            e.printStackTrace();
        }
        SeatSelectController seatSelectController = loader.getController();

        String s[] = button.getId().split("_");
        int indexRound = Integer.parseInt(s[3]);
        switch (Integer.parseInt(s[1])){
            case 1: seatSelectController.setRound(cinema.getTheater1().getRoundsList().get(indexRound));break;
            case 2: seatSelectController.setRound(cinema.getTheater2().getRoundsList().get(indexRound));break;
            case 3: seatSelectController.setRound(cinema.getTheater3().getRoundsList().get(indexRound));break;
            case 4: seatSelectController.setRound(cinema.getTheater4().getRoundsList().get(indexRound));break;
            case 5: seatSelectController.setRound(cinema.getTheater5().getRoundsList().get(indexRound));break;
            case 6: seatSelectController.setRound(cinema.getTheater6().getRoundsList().get(indexRound));break;
        }
        stage.show();
        mp.stop();
    }

    //Load movie select page
    @FXML public void handleMainPageButton(ActionEvent event){
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/movie_select.fxml"));
        try {
            stage.setScene(new Scene(loader.load(), 1280, 720));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.show();
        mp.stop();
    }
}
