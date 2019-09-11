package booking_movies_tickets;

import javafx.scene.control.Button;

import java.util.ArrayList;

public class Round {
    private Movie movie;
    private String time;
    private ArrayList<Button> buttonsList = null;
    private ArrayList<String> seat = null;

    public Round(Movie movie, String time) {
        this.movie = movie;
        this.time = time;
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

    public void setSeat(ArrayList<String> chair) {
        this.seat = chair;
    }


}
