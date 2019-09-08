package booking_movies_tickets;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class ChairSelectController {
    ArrayList<Button> buttonList = new ArrayList<>();
    ArrayList<String> chair = new ArrayList<>();
    Button button[][] = new Button[15][20];
    @FXML
    GridPane chairGridPane;

    @FXML
    public void initialize() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 15; j++) {
                button[j][i] = new Button("      ");
                button[j][i].setStyle("-fx-background-image: url('/image/chair-1-2-3-4-22.png');-fx-background-size: 39 25;-fx-background-position: center center");
                if(i<10){button[j][i].setId(""+(char)(j+65)+"0"+i);}
                else{button[j][i].setId((""+(char)(j+65)+i));}
                button[j][i].setOnAction(event -> handleChairBtn(event));
                chairGridPane.add(button[j][i], i, j, 1, 1);
            }
        }
    }
    @FXML public void handleChairBtn(ActionEvent e) {
        Button b = (Button) e.getSource();
        System.out.println(b.getId());
        //System.out.println(b);
        if(buttonList.contains(b)){
            buttonList.remove(b);
            b.setStyle("-fx-background-image: url('/image/chair-1-2-3-4-22.png');-fx-background-size: 39 25;-fx-background-position: center center");
            chair.remove(b.getId());
        }
        else{
            buttonList.add(b);
            b.setStyle("");
            chair.add(b.getId());
        }
        Collections.sort(chair);
        System.out.println(chair);
    }















}
