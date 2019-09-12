package booking_movies_tickets;

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

    ArrayList<Button> buttonsList = new ArrayList<>();
    ArrayList<String> seatsSelect = new ArrayList<>();
    HashMap<String, Seat> seatsList;
    Button button[][] = new Button[15][20];
    CinemaOperator cinema = CinemaOperator.getInstance();

    @FXML GridPane chairGridPane;
    @FXML Button confirm;
    @FXML Text seatsSelectDisplay;

    @FXML public void initialize() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if(round.getButtonsList() == null){
                    seatsList = new HashMap<>();
                    char alphabet = 65+14;
                    for (int i = 0; i < 20; i++) {
                        for (int j = 0; j < 15; j++) {
                            button[j][i] = new Button();
                            button[j][i].setMaxSize(44, 30);
                            if(i<9){
                                if(round.getTheater().getSeatType().equals("Mix")){
                                    if(j>9){
                                        button[j][i].setId(""+(char)(alphabet-j)+"0"+(i+1));
                                        seatsList.put(""+(char)(alphabet-j)+"0"+(i+1), (new Seat(""+(char)(alphabet-j)+"0"+(i+1), "Honeymoon", 220)));
                                        button[j][i].setStyle("-fx-background-image: url('/image/seat-3.png');-fx-background-size: 44 30;-fx-background-position: center center");
                                    }
                                    else {
                                        button[j][i].setId(""+(char)(alphabet-j)+"0"+(i+1));
                                        seatsList.put(""+(char)(alphabet-j)+"0"+(i+1), (new Seat(""+(char)(alphabet-j)+"0"+(i+1), "Normal", 220)));
                                        button[j][i].setStyle("-fx-background-image: url('/image/seat-2.png');-fx-background-size: 44 30;-fx-background-position: center center");
                                    }
                                }
                                else {
                                    button[j][i].setId(""+(char)(alphabet-j)+"0"+(i+1));
                                    seatsList.put(""+(char)(alphabet-j)+"0"+(i+1), (new Seat(""+(char)(alphabet-j)+"0"+(i+1), "Normal", 220)));
                                    button[j][i].setStyle("-fx-background-image: url('/image/seat-2.png');-fx-background-size: 44 30;-fx-background-position: center center");
                                }


                            }
                            else{
                                if(round.getTheater().getSeatType().equals("Mix")){
                                    if(j>9){
                                        button[j][i].setId((""+(char)(alphabet-j)+(i+1)));
                                        seatsList.put(""+(char)(alphabet-j)+(i+1), (new Seat(""+(char)(alphabet-j)+(i+1), "Honeymoon", 240)));
                                        button[j][i].setStyle("-fx-background-image: url('/image/seat-3.png');-fx-background-size: 44 30;-fx-background-position: center center");
                                    }
                                    else{
                                        button[j][i].setId((""+(char)(alphabet-j)+(i+1)));
                                        seatsList.put(""+(char)(alphabet-j)+(i+1), (new Seat(""+(char)(alphabet-j)+(i+1), "Normal", 240)));
                                        button[j][i].setStyle("-fx-background-image: url('/image/seat-2.png');-fx-background-size: 44 30;-fx-background-position: center center");
                                    }
                                }

                                else{
                                    button[j][i].setId((""+(char)(alphabet-j)+(i+1)));
                                    seatsList.put(""+(char)(alphabet-j)+(i+1), (new Seat(""+(char)(alphabet-j)+(i+1), "Normal", 240)));
                                    button[j][i].setStyle("-fx-background-image: url('/image/seat-2.png');-fx-background-size: 44 30;-fx-background-position: center center");
                                }
                            }
                            button[j][i].setOnAction(event -> handleChairBtn(event));
                            chairGridPane.add(button[j][i], i, j, 1, 1);
                        }
                    }
                }

                else {
                    buttonsList = round.getButtonsList();
                    seatsList = round.getSeatsList();
                    button = round.getButton();


                    for (int i = 0; i < 20; i++) {
                        for (int j = 0; j < 15; j++) {
                            if(!seatsList.get(button[j][i].getId()).isBooked())
                                if(round.getTheater().getSeatType().equals("Mix")){
                                    if (j>9) {
                                        button[j][i].setStyle("-fx-background-image: url('/image/seat-3.png');-fx-background-size: 44 30;-fx-background-position: center center");
                                    } else {
                                        button[j][i].setStyle("-fx-background-image: url('/image/seat-2.png');-fx-background-size: 44 30;-fx-background-position: center center");
                                    }
                                }
                                else {
                                    button[j][i].setStyle("-fx-background-image: url('/image/seat-2.png');-fx-background-size: 44 30;-fx-background-position: center center");
                                }



                            chairGridPane.add(button[j][i], i, j, 1, 1);
                        }
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
            if(seatsList.get(b.getId()).getType().equals("Honeymoon")){
                b.setStyle("-fx-background-image: url('/image/seat-3-booked.png');-fx-background-size: 44 30;-fx-background-position: center center");
            }
            else{
                b.setStyle("-fx-background-image: url('/image/seat-2-booked.png');-fx-background-size: 44 30;-fx-background-position: center center");
            }

        }
        round.setButtonsList(buttonsList);
        round.setSeatsList(seatsList);
        round.setButton(button);
        seatsSelect.clear();
    }


    @FXML public void handleChairBtn(ActionEvent e) {
        Button b = (Button) e.getSource();
        //System.out.println(b.getId());

        if(!seatsList.get(b.getId()).isBooked()){
            if (!seatsSelect.contains(b.getId()) && !buttonsList.contains(b)){
                buttonsList.add(b);
                if(seatsList.get(b.getId()).getType().equals("Normal")){
                    b.setStyle("-fx-background-image: url('/image/seat-2-select.png');-fx-background-size: 44 30;-fx-background-position: center center");
                }
                else{
                    b.setStyle("-fx-background-image: url('/image/seat-3-select.png');-fx-background-size: 44 30;-fx-background-position: center center");
                }
                seatsSelect.add(b.getId());
            }

            else {
                buttonsList.remove(b);
                if(seatsList.get(b.getId()).getType().equals("Normal")){
                    b.setStyle("-fx-background-image: url('/image/seat-2.png');-fx-background-size: 44 30;-fx-background-position: center center");
                }
                else{
                    b.setStyle("-fx-background-image: url('/image/seat-3.png');-fx-background-size: 44 30;-fx-background-position: center center");
                }
                seatsSelect.remove(b.getId());
            }
        }

        Collections.sort(seatsSelect);
        seatsSelectDisplay.setText(seatsSelect.toString());
        //System.out.println(output);
    }

    @FXML public void handleConfirmBtn(ActionEvent event) throws IOException {
        confirmBooking();
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("movie_select.fxml"));
        stage.setScene(new Scene(loader.load(), 1280, 720));
        MovieSelectController movieSelectController = loader.getController();
        stage.show();
    }
}
