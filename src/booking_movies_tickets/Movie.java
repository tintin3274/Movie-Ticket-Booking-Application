package booking_movies_tickets;

import java.time.LocalDate;
import java.util.ArrayList;

public class Movie {
    private String nameEn;
    private String nameTh;
    private String description;
    private String rate;
    private String genre;
    private String imgPosterPath;
    private int length;
    private LocalDate releaseDate;

    public Movie(String nameEn, String nameTh, String description, String rate, String genre, String imgPosterPath, int length, LocalDate releaseDate) {
        this.nameEn = nameEn;
        this.nameTh = nameTh;
        this.description = description;
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

    public String getDescription() {
        return description;
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
}
