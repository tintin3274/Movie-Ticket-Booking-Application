package booking_movies_tickets;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.time.LocalDate;

public class MovieSelectController {
    @FXML Label label1, label2, label3, label4, label5, label6;
    @FXML ImageView moviePoster1, moviePoster2, moviePoster3, moviePoster4, moviePoster5, moviePoster6;

    Movie movie1 = new Movie("It Chapter Two", "โผล่จากนรก 2",
            "น 15+", "สยองขวัญ / ระทึกขวัญ", "/image/image_poster/ItChapterTwo.jpg", 169, LocalDate.parse("2019-09-05"));
    Movie movie2 = new Movie("47 Meters Down Uncaged", "47 ดิ่งลึกสุดนรก",
            "น 15+", "ผจญภัย / ชีวิต / สยองขวัญ", "/image/image_poster/47 MetersDownUncaged.jpg", 90, LocalDate.parse("2019-08-09"));
    Movie movie3 = new Movie("Angel Has Fallen", "ผ่ายุทธการ ดับแผนอหังการ์",
            "น 15+", "แอ็คชัน / ชีวิต / ระทึกขวัญ", "/image/image_poster/AngelHasFallen.jpg", 120, LocalDate.parse("2019-08-22"));
    Movie movie4 = new Movie("Weathering with You", "ฤดูฝัน ฉันมีเธอ",
            "น 13+", "แอนิเมชัน / แฟนตาซี / โรแมนติก", "/image/image_poster/WeatheringwithYou.jpg", 112, LocalDate.parse("2019-09-05"));
    Movie movie5 = new Movie("Pro May", "โปรเม อัจฉริยะต้องสร้าง",
            "ทั่วไป", "ชีวิต / ไทย", "/image/image_poster/ProMay.jpg", 103, LocalDate.parse("2019-08-15"));
    Movie movie6 = new Movie("Fast And Furious Hobbs and Shaw", "เร็ว แรงทะลุนรกฮ็อบส์ แอนด์ ชอว์",
            "น 15+", "แอ็คชัน", "/image/image_poster/FastAndFuriousHobbsandShaw.jpg", 133, LocalDate.parse("2019-07-31"));

    @FXML
    public void initialize() {
        moviePoster1.setImage(new Image(movie1.getImgPosterPath()));
        moviePoster2.setImage(new Image(movie2.getImgPosterPath()));
        moviePoster3.setImage(new Image(movie3.getImgPosterPath()));
        moviePoster4.setImage(new Image(movie4.getImgPosterPath()));
        moviePoster5.setImage(new Image(movie5.getImgPosterPath()));
        moviePoster6.setImage(new Image(movie6.getImgPosterPath()));
        label1.setText(movie1.toString());
        label2.setText(movie2.toString());
        label3.setText(movie3.toString());
        label4.setText(movie4.toString());
        label5.setText(movie5.toString());
        label6.setText(movie6.toString());
    }

    @FXML public void mouseEnterImage(MouseEvent event){
        ImageView effectedImage = (ImageView) event.getSource();
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(15);
        dropShadow.setHeight(25);
        dropShadow.setWidth(25);
        dropShadow.setColor(Color.color(0.9, 0.5, 0.3));
        effectedImage.setEffect(dropShadow);
    }

    @FXML public void mouseExitImage(MouseEvent event){
        ImageView effectedImage = (ImageView) event.getSource();
        effectedImage.setEffect(null);
    }

}
