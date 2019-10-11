package Class;

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

        movie1.addSystemType("2D");
        movie1.addSystemType("4K");
        movie1.addSystemType("3D");
        movie1.addSystemType("4DX");

        movie2.addSystemType("2D");
        movie2.addSystemType("4K");
        movie2.addSystemType("3D");
        movie2.addSystemType("4DX");

        movie3.addSystemType("2D");
        movie3.addSystemType("4K");
        movie3.addSystemType("3D");
        movie3.addSystemType("4DX");

        movie4.addSystemType("2D");
        movie4.addSystemType("4K");
        movie4.addSystemType("3D");
        movie4.addSystemType("4DX");

        movie5.addSystemType("2D");
        movie5.addSystemType("4K");
        movie5.addSystemType("3D");
        movie5.addSystemType("4DX");

        movie6.addSystemType("2D");
        movie6.addSystemType("4K");
        movie6.addSystemType("3D");
        movie6.addSystemType("4DX");

        theater1 = new Theater("Theater1", "2D", "Normal");
        theater2 = new Theater("Theater2","4K", "Normal");
        theater3 = new Theater("Theater3", "4K", "Mix");
        theater4 = new Theater("Theater4", "3D", "Mix");
        theater5 = new Theater("Theater5", "IMAX Digital 2D", "Mix");
        theater6 = new Theater("Theater6", "4DX", "Mix");


        theater1.addRound(new Round(theater1, movie1, "11:00"));
        theater1.addRound(new Round(theater1, movie2, "13:00"));
        theater1.addRound(new Round(theater1, movie1, "16:00"));
        theater1.addRound(new Round(theater1, movie3, "19:00"));

        theater2.addRound(new Round(theater2, movie4, "11:00"));
        theater2.addRound(new Round(theater2, movie3, "13:30"));
        theater2.addRound(new Round(theater2, movie6, "17:30"));
        theater2.addRound(new Round(theater2, movie5, "21:00"));

        theater3.addRound(new Round(theater3, movie2, "11:00"));
        theater3.addRound(new Round(theater3, movie5, "15:00"));
        theater3.addRound(new Round(theater3, movie4, "18:00"));
        theater3.addRound(new Round(theater3, movie6, "21:00"));

        theater4.addRound(new Round(theater4, movie1, "11:00"));
        theater4.addRound(new Round(theater4, movie3, "13:15"));
        theater4.addRound(new Round(theater4, movie6, "16:30"));
        theater4.addRound(new Round(theater4, movie6, "20:15"));

        theater5.addRound(new Round(theater5, movie4, "11:00"));
        theater5.addRound(new Round(theater5, movie6, "13:30"));
        theater5.addRound(new Round(theater5, movie1, "16:45"));
        theater5.addRound(new Round(theater5, movie4, "20:00"));

        theater6.addRound(new Round(theater6, movie1, "11:00"));
        theater6.addRound(new Round(theater6, movie3, "14:30"));
        theater6.addRound(new Round(theater6, movie6, "17:45"));
        theater6.addRound(new Round(theater6, movie2, "21:00"));

    }

    private static CinemaOperator cinemaInstance;
    public static synchronized CinemaOperator getInstance(){
        if(cinemaInstance == null){
            cinemaInstance = new CinemaOperator();
        }
        return cinemaInstance;
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

    public Theater getTheater1() {
        return theater1;
    }

    public Theater getTheater2() {
        return theater2;
    }

    public Theater getTheater3() {
        return theater3;
    }

    public Theater getTheater4() {
        return theater4;
    }

    public Theater getTheater5() {
        return theater5;
    }

    public Theater getTheater6() {
        return theater6;
    }
}
