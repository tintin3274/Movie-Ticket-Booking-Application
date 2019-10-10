package Class;

import java.util.HashMap;

public class Round {
    private Theater theater;
    private Movie movie;
    private String time;
    private HashMap<String, Seat> seatsList = new HashMap<>();


    public Round(Theater theater, Movie movie, String time) {
        this.theater = theater;
        this.movie = movie;
        this.time = time;
        createSeats();
    }

    private void createSeats(){
        char alphabet = 65+14;
        for (int i = 0; i < 20; i++){
            for (int j = 0; j < 15; j++) {
                if(this.getTheater().getSeatType().equals("Mix")){
                    if(j>9){
                        seatsList.put(""+(char)(alphabet-j)+(i<9?"0":"")+(i+1), (new Seat(""+(char)(alphabet-j)+(i<9?"0":"")+(i+1), "Honeymoon", 240)));
                    }
                    else {
                        seatsList.put(""+(char)(alphabet-j)+(i<9?"0":"")+(i+1), (new Seat(""+(char)(alphabet-j)+(i<9?"0":"")+(i+1), "Normal", 220)));
                    }
                }
                else {
                    seatsList.put(""+(char)(alphabet-j)+(i<9?"0":"")+(i+1), (new Seat(""+(char)(alphabet-j)+(i<9?"0":"")+(i+1), "Normal", 220)));
                }
            }
        }
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

    public HashMap<String, Seat> getSeatsList() {
        return seatsList;
    }
}
