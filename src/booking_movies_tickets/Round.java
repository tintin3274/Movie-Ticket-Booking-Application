package booking_movies_tickets;

import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.HashMap;

public class Round {
    private Theater theater;
    private Movie movie;
    private String time;
    private ArrayList<Button> buttonsList = null;
    private Button button[][] = null;
    private HashMap<String, Seat> seatsList = new HashMap<>();


    public Round(Theater theater, Movie movie, String time) {
        this.theater = theater;
        this.movie = movie;
        this.time = time;
    }

    public Theater getTheater() {
        return theater;
    }

    public Movie getMovie() {
        return movie;
    }

    public String getTime() {
        return time;
    }

    public void setButtonsList(ArrayList<Button> buttonsList) {
        this.buttonsList = buttonsList;
    }

    public void setSeatsList(HashMap<String, Seat> seatsList) {
        this.seatsList.putAll(seatsList);
    }

    public void setButton(Button[][] button) {
        this.button = button;
    }

    public ArrayList<Button> getButtonsList() {
        return buttonsList;
    }

    public HashMap<String, Seat> getSeatsList() {
        return seatsList;
    }

    public Button[][] getButton() {
        return button;
    }
}
