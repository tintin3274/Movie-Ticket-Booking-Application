package Controller;

import Class.*;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import java.io.IOException;


public class ReceiptController {
    @FXML MediaView mv;
    @FXML ImageView ticketImageView;

    private Round round;
    private String seatNo;
    private Double price;

    @FXML public void initialize() {
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
