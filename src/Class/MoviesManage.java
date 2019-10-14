package Class;

import java.time.LocalDate;
import java.util.HashSet;

public class MoviesManage {
    private Movie movie1, movie2, movie3, movie4, movie5, movie6;
    private HashSet<Movie> movieHashSet = new HashSet<>();
    private CinemaOperator cinema = CinemaOperator.getInstance();

    private MoviesManage(){
//        movie1 = new Movie("It Chapter Two", "โผล่จากนรก 2",
//                "น 15+", "สยองขวัญ / ระทึกขวัญ", "/image/image_poster/ItChapterTwo.jpg", 169, LocalDate.parse("2019-09-05"),
//                "Twenty-seven years after their first encounter with the terrifying Pennywise, the Losers Club have grown up and moved away, until a devastating phone call brings them back.");
//        movie2 = new Movie("47 Meters Down Uncaged", "47 ดิ่งลึกสุดนรก",
//                "น 15+", "ผจญภัย / ชีวิต / สยองขวัญ", "/image/image_poster/47MetersDownUncaged.jpg", 90, LocalDate.parse("2019-08-09"),
//                "Four teen girls diving in a ruined underwater city quickly learn they've entered the territory of the deadliest shark species in the claustrophobic labyrinth of submerged caves.");
//        movie3 = new Movie("Angel Has Fallen", "ผ่ายุทธการ ดับแผนอหังการ์",
//                "น 15+", "แอ็คชัน / ชีวิต / ระทึกขวัญ", "/image/image_poster/AngelHasFallen.jpg", 120, LocalDate.parse("2019-08-22"),
//                "Secret Service Agent Mike Banning is framed for the attempted assassination of the President and must evade his own agency and the FBI as he tries to uncover the real threat.");
//        movie4 = new Movie("Weathering with You", "ฤดูฝัน ฉันมีเธอ",
//                "น 13+", "แอนิเมชัน / แฟนตาซี / โรแมนติก", "/image/image_poster/WeatheringwithYou.jpg", 112, LocalDate.parse("2019-09-05"),
//                "A high-school boy who has run away to Tokyo befriends a girl who appears to be able to manipulate the weather.");
//        movie5 = new Movie("Pro May", "โปรเม อัจฉริยะต้องสร้าง",
//                "ทั่วไป", "ชีวิต / ไทย", "/image/image_poster/ProMay.jpg", 103, LocalDate.parse("2019-08-15"),
//                "นี่คือ เรื่องราวทั้งหมด ของ “เม เอรียา จุฑานุกาล” และครอบครัวของเธอ ที่เริ่มต้นจาก การทิ้งโลกวัยเด็กของเธอไว้เบื้องหลังและเริ่มเล่นกอล์ฟ ตั้งแต่ยังเด็ก ตลอดเวลา เม และครอบครัว ต้องผ่านการฝึกฝนอย่างหนัก ทั้งเรื่องกอล์ฟ และบททดสอบ ความยากลำบากในการใช้ชีวิต ช่วงเวลาที่บาดเจ็บ การตกลงสู่จุดที่แถบจะเรียกได้ว่า ต่ำที่สุดของชีวิต ของเธอ และครอบครัว");
//        movie6 = new Movie("Fast And Furious Hobbs and Shaw", "เร็ว แรงทะลุนรกฮ็อบส์ แอนด์ ชอว์",
//                "น 15+", "แอ็คชัน", "/image/image_poster/FastAndFuriousHobbsandShaw.jpg", 133, LocalDate.parse("2019-07-31"),
//                "Lawman Luke Hobbs and outcast Deckard Shaw form an unlikely alliance when a cyber-genetically enhanced villain threatens the future of humanity.");
//        movie1.addSystemType("2D");
//        movie1.addSystemType("4K");
//        movie1.addSystemType("3D");
//        movie1.addSystemType("4DX");
//
//
//        movie2.addSystemType("2D");
//        movie2.addSystemType("4K");
//        movie2.addSystemType("3D");
//        movie2.addSystemType("4DX");
//
//        movie3.addSystemType("2D");
//        movie3.addSystemType("4K");
//        movie3.addSystemType("3D");
//        movie3.addSystemType("4DX");
//
//        movie4.addSystemType("2D");
//        movie4.addSystemType("4K");
//        movie4.addSystemType("3D");
//        movie4.addSystemType("4DX");
//
//        movie5.addSystemType("2D");
//        movie5.addSystemType("4K");
//        movie5.addSystemType("3D");
//        movie5.addSystemType("4DX");
//
//        movie6.addSystemType("2D");
//        movie6.addSystemType("4K");
//        movie6.addSystemType("3D");
//        movie6.addSystemType("4DX");
//
//        movieHashSet.add(movie1);
//        movieHashSet.add(movie2);
//        movieHashSet.add(movie3);
//        movieHashSet.add(movie4);
//        movieHashSet.add(movie5);
//        movieHashSet.add(movie6);

//        cinema.setMovie1(movie1);
//        cinema.setMovie2(movie2);
//        cinema.setMovie3(movie3);
//        cinema.setMovie4(movie4);
//        cinema.setMovie5(movie5);
//        cinema.setMovie6(movie6);
    }

    private static MoviesManage moviesManageInstance;
    public static synchronized MoviesManage getInstance(){
        if(moviesManageInstance == null){
            moviesManageInstance = new MoviesManage();
        }
        return moviesManageInstance;
    }

    public HashSet<Movie> getMovieHashSet() {
        return movieHashSet;
    }

    public void addMovie(Movie movie){
        movieHashSet.add(movie);
    }

    public void removeMovie(Movie movie){
        movieHashSet.remove(movie);
    }
}
