package booking_movies_tickets;

import java.time.LocalDate;

public class CinemaOperator {
    private Movie movie1, movie2, movie3, movie4, movie5, movie6;
    private Theater theater1, theater2, theater3, theater4, theater5, theater6;
    private CinemaOperator(){
        movie1 = new Movie("It Chapter Two", "โผล่จากนรก 2",
                "น 15+", "สยองขวัญ / ระทึกขวัญ", "/image/image_poster/ItChapterTwo.jpg", 169, LocalDate.parse("2019-09-05"));
        movie2 = new Movie("47 Meters Down Uncaged", "47 ดิ่งลึกสุดนรก",
                "น 15+", "ผจญภัย / ชีวิต / สยองขวัญ", "/image/image_poster/47 MetersDownUncaged.jpg", 90, LocalDate.parse("2019-08-09"));
        movie3 = new Movie("Angel Has Fallen", "ผ่ายุทธการ ดับแผนอหังการ์",
                "น 15+", "แอ็คชัน / ชีวิต / ระทึกขวัญ", "/image/image_poster/AngelHasFallen.jpg", 120, LocalDate.parse("2019-08-22"));
        movie4 = new Movie("Weathering with You", "ฤดูฝัน ฉันมีเธอ",
                "น 13+", "แอนิเมชัน / แฟนตาซี / โรแมนติก", "/image/image_poster/WeatheringwithYou.jpg", 112, LocalDate.parse("2019-09-05"));
        movie5 = new Movie("Pro May", "โปรเม อัจฉริยะต้องสร้าง",
                "ทั่วไป", "ชีวิต / ไทย", "/image/image_poster/ProMay.jpg", 103, LocalDate.parse("2019-08-15"));
        movie6 = new Movie("Fast And Furious Hobbs and Shaw", "เร็ว แรงทะลุนรกฮ็อบส์ แอนด์ ชอว์",
                "น 15+", "แอ็คชัน", "/image/image_poster/FastAndFuriousHobbsandShaw.jpg", 133, LocalDate.parse("2019-07-31"));

        theater1 = new Theater("Theater1");
        theater2 = new Theater("Theater2");
        theater3 = new Theater("Theater3");
        theater4 = new Theater("Theater4");
        theater5 = new Theater("Theater5");
        theater6 = new Theater("Theater6");


        theater1.addRound(new Round(movie1, "11:00"));
        movie1.addTheater(theater1);

    }
    private static CinemaOperator cinemaInstance;
    public static synchronized CinemaOperator getInstance(){
        if(cinemaInstance == null){
            cinemaInstance = new CinemaOperator();
        }
        return cinemaInstance;
    }



    private Round round1;

    public static void test(){

    }

    public Movie getMovie1() {
        return movie1;
    }

    public Movie getMovie2() {
        return movie2;
    }

    public Movie getMovie3() {
        return movie3;
    }

    public Movie getMovie4() {
        return movie4;
    }

    public Movie getMovie5() {
        return movie5;
    }

    public Movie getMovie6() {
        return movie6;
    }
}
