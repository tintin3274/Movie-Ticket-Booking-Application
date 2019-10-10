package Class;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

public class Movie {
    private String nameEn;
    private String nameTh;
    private String rate;
    private String genre;
    private String imgPosterPath;
    private int length;
    private LocalDate releaseDate;
    private Set<String> systemType = new LinkedHashSet<>();

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

    public void addSystemType(String systemType){
        this.systemType.add(systemType);
    }

    public Set<String> getSystemType() {
        return systemType;
    }

    @Override
    public String toString() {
        return nameEn+"\n"+nameTh+"\n"+"Release Date: "+releaseDate;
    }
}