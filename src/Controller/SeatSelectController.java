package Controller;

import Class.*;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

public class SeatSelectController {
    private Round round;

    HashSet<Button> buttonsList;
    ArrayList<String> seatsSelect;
    HashMap<String, Seat> seatsList;
    Button button[][] = new Button[15][20];
    CinemaOperator cinema = CinemaOperator.getInstance();

    @FXML GridPane chairGridPane;
    @FXML Button confirm;
    @FXML Text seatsSelectDisplay;

    @FXML public void initialize() {
        buttonsList = new HashSet<>();
        seatsSelect = new ArrayList<>();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                seatsList = round.getSeatsList();
                char alphabet = 65+14;
                for (int i = 0; i < 20; i++) {
                    for (int j = 0; j < 15; j++) {
                        button[j][i] = new Button();
                        button[j][i].setMaxSize(44, 30);
                        button[j][i].setId(""+(char)(alphabet-j)+(i<9?"0":"")+(i+1));
                        if(round.getTheater().getSeatType().equals("Mix")){
                            if(j>9){
                                if(!seatsList.get(""+(char)(alphabet-j)+(i<9?"0":"")+(i+1)).isBooked()){
                                    button[j][i].setStyle("-fx-background-image: url('/image/seat-3.png');-fx-background-size: 44 30;-fx-background-position: center center");
                                }
                                else{
                                    button[j][i].setStyle("-fx-background-image: url('/image/seat-3-booked.png');-fx-background-size: 44 30;-fx-background-position: center center");
                                }
                            }
                            else {
                                if(!seatsList.get(""+(char)(alphabet-j)+(i<9?"0":"")+(i+1)).isBooked()){
                                    button[j][i].setStyle("-fx-background-image: url('/image/seat-2.png');-fx-background-size: 44 30;-fx-background-position: center center");
                                }
                                else{
                                    button[j][i].setStyle("-fx-background-image: url('/image/seat-2-booked.png');-fx-background-size: 44 30;-fx-background-position: center center");
                                }
                            }
                        }
                        else {
                            if(!seatsList.get(""+(char)(alphabet-j)+(i<9?"0":"")+(i+1)).isBooked()){
                                button[j][i].setStyle("-fx-background-image: url('/image/seat-2.png');-fx-background-size: 44 30;-fx-background-position: center center");
                            }
                            else{
                                button[j][i].setStyle("-fx-background-image: url('/image/seat-2-booked.png');-fx-background-size: 44 30;-fx-background-position: center center");
                            }
                        }
                        button[j][i].setOnAction(event -> handleChairBtn(event));
                        chairGridPane.add(button[j][i], i, j, 1, 1);
                    }
                }
            }
        });
    }

    public void setRound(Round round) {
        this.round = round;
    }

    public void confirmBooking(){
        for(Button b:buttonsList){
            seatsList.get(b.getId()).setBooked();
        }
    }

    @FXML public void handleChairBtn(ActionEvent e) {
        Button b = (Button) e.getSource();
        if(!seatsList.get(b.getId()).isBooked()){
            if(!seatsSelect.contains(b.getId()) && !buttonsList.contains(b)){
                seatsSelect.add(b.getId());
                buttonsList.add(b);
                if(seatsList.get(b.getId()).getType().equals("Normal")){
                    b.setStyle("-fx-background-image: url('/image/seat-2-select.png');-fx-background-size: 44 30;-fx-background-position: center center");
                }
                else{
                    b.setStyle("-fx-background-image: url('/image/seat-3-select.png');-fx-background-size: 44 30;-fx-background-position: center center");
                }
            }
            else{
                seatsSelect.remove(b.getId());
                buttonsList.remove(b);
                if(seatsList.get(b.getId()).getType().equals("Normal")){
                    b.setStyle("-fx-background-image: url('/image/seat-2.png');-fx-background-size: 44 30;-fx-background-position: center center");
                }
                else{
                    b.setStyle("-fx-background-image: url('/image/seat-3.png');-fx-background-size: 44 30;-fx-background-position: center center");
                }
            }
        }
        Collections.sort(seatsSelect);
        seatsSelectDisplay.setText(seatsSelect.toString());
    }

    @FXML public void handleConfirmBtn(ActionEvent event) {
        confirmBooking();
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/movie_select.fxml"));
        try {
            stage.setScene(new Scene(loader.load(), 1280, 720));
        } catch (IOException e) {
            e.printStackTrace();
        }
        MovieSelectController movieSelectController = loader.getController();
        stage.show();
    }
}
