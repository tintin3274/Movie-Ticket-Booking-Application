package booking_movies_tickets;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ChairSelectController {
    Button button[][] = new Button[15][20];
    @FXML
    GridPane chairGridPane;

    @FXML
    public void initialize() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 15; j++) {
                button[j][i] = new Button("      ");
                button[j][i].setStyle("-fx-background-image: url('/image/chair-1-2-3-4-22.png');-fx-background-size: 39 25;-fx-background-position: center center");
                button[j][i].setOnAction(event -> handleChairBtn());
                chairGridPane.add(button[j][i], i, j, 1, 1);
            }
        }
    }
    @FXML public void handleChairBtn() {

    }













}
