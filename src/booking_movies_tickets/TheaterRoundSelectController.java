package booking_movies_tickets;

import javafx.application.Platform;
import javafx.fxml.FXML;

import java.util.ArrayList;

public class TheaterRoundSelectController {
    private Movie movie;

    @FXML public void initialize(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                movie.theatersList.get(0).getRoundsList();
            }
        });
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
