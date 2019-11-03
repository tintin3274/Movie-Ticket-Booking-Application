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
    private String description;
    private String videoPath;
    private int length;
    private LocalDate releaseDate;
    private Set<String> systemType = new LinkedHashSet<>();

    public Movie(String nameEn, String nameTh, String rate, String genre, String imgPosterPath, int length, LocalDate releaseDate, String description, String videoPath) {
        this.nameEn = nameEn;
        this.nameTh = nameTh;
        this.rate = rate;
        this.genre = genre;
        this.imgPosterPath = imgPosterPath;
        this.length = length;
        this.releaseDate = releaseDate;
        this.description = description;
        this.videoPath = videoPath;
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

    public String getDescription() {
        return description;
    }

    public String getVideoPath() {
        return videoPath;
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

    public String showShortDetail() {
        return nameEn+"\n"+nameTh+"\n"+"Release Date: "+releaseDate;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "nameEn='" + nameEn + '\'' +
                ", nameTh='" + nameTh + '\'' +
                ", rate='" + rate + '\'' +
                ", genre='" + genre + '\'' +
                ", imgPosterPath='" + imgPosterPath + '\'' +
                ", description='" + description + '\'' +
                ", length=" + length +
                ", releaseDate=" + releaseDate +
                ", systemType=" + systemType +
                '}';
    }
}