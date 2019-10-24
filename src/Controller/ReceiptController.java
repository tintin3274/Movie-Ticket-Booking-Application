package Controller;

import Class.*;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ReceiptController {
    @FXML MediaView mv;
    @FXML ImageView ticketImageView;

    private Round round;
    private String seatNo;
    private Double price;

    @FXML public void initialize() {
//        File file = new File("./WELCOME TO DREAM.mp4");
//        Media media = new Media(file.toURI().toString());
//        MediaPlayer mp = new MediaPlayer(media);
//        mv.setMediaPlayer(mp);
//        mp.play();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                ticketImageView.setImage(TicketController.image);
            }
        });
    }

    public void setRound(Round round) {
        this.round = round;
    }

    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @FXML public void saveReceiptImage(ActionEvent event) {
        TicketController.saveTicketImage();
    }

    @FXML public void loadMovieSelectPage(ActionEvent event){
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
}
