package Controller;

import Class.*;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class SeatSelectController {
    private Round round;
    private Movie movie;
    private Theater theater;
    private int amountNormal;
    private int amountHoneymoon;
    private double price;
    private String seatNo;

    HashSet<Button> buttonsList;
    ArrayList<String> seatsSelect;
    HashMap<String, Seat> seatsList;
    Button button[][] = new Button[15][20];
    CinemaManage cinema = CinemaManage.getInstance();

    @FXML GridPane chairGridPane;
    @FXML GridPane rateSystemGridPane;
    @FXML Button confirm;
    @FXML Label seatsSelectDisplay;
    @FXML Label textTitleEn, textTitleTh, textGenre, textLength, textReleaseDate, theaterName, textPrice, priceNormal, priceHoneymoon;
    @FXML ImageView moviePoster, imageNormal, imageHoneymoon;

    @FXML public void initialize() {
        price = 0;
        amountNormal = 0;
        amountHoneymoon = 0;
        buttonsList = new HashSet<>();
        seatsSelect = new ArrayList<>();
        confirm.setDisable(seatsSelect.isEmpty());
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                movie = round.getMovie();
                theater = round.getTheater();

                //Display information of movie and seat
                textTitleEn.setText(movie.getNameEn());
                textTitleTh.setText(movie.getNameTh());
                textGenre.setText(movie.getGenre());
                textLength.setText(movie.getLength()+" ????????????");
                textReleaseDate.setText(""+movie.getReleaseDate());
                theaterName.setText(theater.getName()+"\nRound "+round.getTime());
                textPrice.setText(String.format("%,.2f", price)+" ?????????");
                priceNormal.setText("Normal "+theater.getSeatPrice("Normal")+" ?????????");
                priceHoneymoon.setText("Honeymoon "+theater.getSeatPrice("Honeymoon")+" ?????????");
                moviePoster.setImage(new Image(movie.getImgPosterPath()));

                if (theater.getSeatType().equals("Normal")){
                    imageHoneymoon.setImage(null);
                    priceHoneymoon.setText("");
                }

                //Display rate of movie and system of theater
                ImageView image;
                switch (movie.getRate()){
                    case "??????????????????": image = new ImageView("/image/image_detail/rating_1.png");break;
                    case "??? 13+": image = new ImageView("/image/image_detail/rating_2.png");break;
                    case "??? 15+": image = new ImageView("/image/image_detail/rating_3.png");break;
                    case "??? 18+": image = new ImageView("/image/image_detail/rating_4.png");break;
                    case "??? 20+": image = new ImageView("/image/image_detail/rating_5.png");break;
                    default: image = new ImageView("/image/image_detail/rating_0.png");break;
                }
                image.setFitWidth(67);
                image.setFitHeight(35);

                rateSystemGridPane.add(image, 0,0);
                switch (theater.getSystemType()){
                    case "2D": image = new ImageView("/image/image_detail/digital.png");break;
                    case "4K": image = new ImageView("/image/image_detail/4k.png");break;
                    case "3D": image = new ImageView("/image/image_detail/3d.png");break;
                    case "IMAX Digital 2D": image = new ImageView("/image/image_detail/imax-digital.png");break;
                    case "4DX": image = new ImageView("/image/image_detail/4dx.png");break;
                    default: image = new ImageView("/image/image_detail/rating_0.png");break;
                }
                image.setFitWidth(67);
                image.setFitHeight(35);
                rateSystemGridPane.add(image, 1,0);

                //initialize seat and display
                seatsList = round.getSeatsList();
                char alphabet = 65+14;
                for (int i = 0; i < 20; i++) {
                    for (int j = 0; j < 15; j++) {
                        Seat seat = seatsList.get(""+(char)(alphabet-j)+(i<9?"0":"")+(i+1));
                        button[j][i] = new Button();
                        Button b = button[j][i];
                        b.setMaxSize(44, 30);
                        b.setId(""+(char)(alphabet-j)+(i<9?"0":"")+(i+1));

                        ContextMenu contextMenu = new ContextMenu();
                        MenuItem item1 = new MenuItem("Cancel Booking");
                        item1.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                seat.cancelBooking();
                                switch (seat.getType()){
                                    case "Honeymoon" : b.setStyle("-fx-background-image: url('/image/seat-3.png');-fx-background-size: 44 30;-fx-background-position: center center"); break;
                                    case "Normal" : b.setStyle("-fx-background-image: url('/image/seat-2.png');-fx-background-size: 44 30;-fx-background-position: center center"); break;
                                }
                                cancelBooking(b);
                            }
                        });

                        contextMenu.getItems().add(item1);
                        b.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
                            @Override
                            public void handle(ContextMenuEvent event) {
                                if(seat.getAccount() == cinema.getAccount()) {
                                    contextMenu.show(b, event.getScreenX(), event.getScreenY());
                                }
                            }
                        });


                        switch (seat.getType()){
                            case "Honeymoon" : {
                                if(!seat.isBooked()){
                                    b.setStyle("-fx-background-image: url('/image/seat-3.png');-fx-background-size: 44 30;-fx-background-position: center center");
                                }
                                else if(seat.getAccount() == cinema.getAccount()){
                                    b.setStyle("-fx-background-image: url('/image/seat-3-owner.png');-fx-background-size: 44 30;-fx-background-position: center center");
                                }
                                else{
                                    b.setStyle("-fx-background-image: url('/image/seat-3-booked.png');-fx-background-size: 44 30;-fx-background-position: center center");
                                }
                                break;
                            }
                            case "Normal" : {
                                if(!seat.isBooked()){
                                    b.setStyle("-fx-background-image: url('/image/seat-2.png');-fx-background-size: 44 30;-fx-background-position: center center");
                                }
                                else if(seat.getAccount() == cinema.getAccount()){
                                    b.setStyle("-fx-background-image: url('/image/seat-2-owner.png');-fx-background-size: 44 30;-fx-background-position: center center");
                                }
                                else{
                                    b.setStyle("-fx-background-image: url('/image/seat-2-booked.png');-fx-background-size: 44 30;-fx-background-position: center center");
                                }
                                break;
                            }
                        }
                        b.setOnAction(event -> handleSeatButton(event));
                        chairGridPane.add(b, i, j, 1, 1);
                    }
                }
            }
        });
    }

    public void setRound(Round round) {
        this.round = round;
    }

    //Write file BookingData.csv for save new booking data
    @FXML public void handleConfirmBookingButton(){
        File dir = new File("csvData");
        if (!dir.exists()){
            dir.mkdirs();
        }
        try {
            File file = new File("csvData/BookingData.csv");
            file.createNewFile();
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            for(Button b:buttonsList){
                seatsList.get(b.getId()).setBooking(cinema.getAccount());
                writer.write(cinema.getAccount().getUsername()+","+round.getTheater().getName()+","+round.getMovie().getNameEn()+","+round.getTime()+","+b.getId()+","+"Booking");
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Write file BookingData.csv for save new cancel booking data
    @FXML public void cancelBooking(Button b){
        File dir = new File("csvData");
        if (!dir.exists()){
            dir.mkdirs();
        }
        try {
            File file = new File("csvData/BookingData.csv");
            file.createNewFile();
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            writer.write(cinema.getAccount().getUsername()+","+round.getTheater().getName()+","+round.getMovie().getNameEn()+","+round.getTime()+","+b.getId()+","+"Cancel");
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Change seat display status And add or remove seat from seatsSelect And display seatsSelect and price
    @FXML public void handleSeatButton(ActionEvent e) {
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

                price += seatsList.get(b.getId()).getPrice();
                if(seatsList.get(b.getId()).getType() == "Normal") amountNormal++;
                else if(seatsList.get(b.getId()).getType() == "Honeymoon") amountHoneymoon++;
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
                price -= seatsList.get(b.getId()).getPrice();
                if(seatsList.get(b.getId()).getType() == "Normal") amountNormal--;
                else if(seatsList.get(b.getId()).getType() == "Honeymoon") amountHoneymoon--;
            }
        }
        Collections.sort(seatsSelect);
        if(!seatsSelect.isEmpty()) seatNo = seatsSelect.toString().replace("[", "").replace("]", "");
        else seatNo = "Selected Seats";
        seatsSelectDisplay.setText(seatNo);
        textPrice.setText(String.format("%,.2f", price)+" ?????????");
        confirm.setDisable(seatsSelect.isEmpty());
    }

    //Show DialogBox booking confirmation
    @FXML public void handleConfirmButton(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Booking Confirmation");
        alert.setHeaderText("Do you want to confirm booking of the selected seat?");
        if (theater.getSeatType().equals("Normal")){
            alert.setContentText("?????????????????????????????????????????????: Normal - "+theater.getSeatPrice("Normal")+" x "+amountNormal+"\n"+seatNo+"\n\n?????????????????????: "+ String.format("%,.2f", price)+" ?????????");
        }
        else {
            alert.setContentText("?????????????????????????????????????????????: Normal - "+theater.getSeatPrice("Normal")+" x "+amountNormal+" | Honeymoon - "+theater.getSeatPrice("Honeymoon")+ " x "+amountHoneymoon+"\n"+seatNo+"\n\n?????????????????????: "+ String.format("%,.2f", price)+" ?????????");
        }

        //If select OK booking will complete and Load receipt page
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            handleConfirmBookingButton();
            generateTicket();

            Button button = (Button) event.getSource();
            Stage stage = (Stage) button.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/receipt.fxml"));
            try {
                stage.setScene(new Scene(loader.load(), 1280, 720));
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.show();
        } else {
            // ... user chose CANCEL or closed the dialog
        }
    }

    //Pass information booking to ticket page for generate ticket
    @FXML public void generateTicket(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/ticket.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Ticket");
            stage.setScene(new Scene(root, 1080, 1920));
            TicketController ticketController = fxmlLoader.getController();
            ticketController.setRound(round);
            ticketController.setSeatNo(seatNo);
            ticketController.setPrice(price);
            //stage.show();
            stage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    }

    //Load movie select page
    @FXML public void handleBackButton(ActionEvent event){
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
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
