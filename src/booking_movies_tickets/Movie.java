package booking_movies_tickets;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Movie {
    private String nameEn;
    private String nameTh;
    private String rate;
    private String genre;
    private String imgPosterPath;
    private int length;
    private LocalDate releaseDate;
    ArrayList<Theater> theatersList = new ArrayList<>();

    public Movie(String nameEn, String nameTh, String rate, String genre, String imgPosterPath, int length, LocalDate releaseDate) {
        this.nameEn = nameEn;
        this.nameTh = nameTh;
        this.rate = rate;
        this.genre = genre;
        this.imgPosterPath = imgPosterPath;
        this.length = length;
        this.releaseDate = releaseDate;
    }

    public String getNameEn() {
        return nameEn;
    }

    public String getNameTh() {
        return nameTh;
    }

    public String getRate() {
        return rate;
    }

    public String getGenre() {
        return genre;
    }

    public String getImgPosterPath() {
        return imgPosterPath;
    }

    public int getLength() {
        return length;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void addTheater(Theater theater){
        theatersList.add(theater);
    }

    @Override
    public String toString() {
        return nameEn+"\n"+nameTh+"\n"+"Release Date: "+releaseDate;
    }
}
