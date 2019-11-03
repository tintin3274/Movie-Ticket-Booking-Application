package Class;

public class CinemaManage {
    private Account account;
    private Movie movie1, movie2, movie3, movie4, movie5, movie6;
    private Theater theater1, theater2, theater3, theater4, theater5, theater6;
    private CinemaManage() {
        theater1 = new Theater("Theater1", "2D", "Normal");
        theater2 = new Theater("Theater2","4K", "Normal");
        theater3 = new Theater("Theater3", "4K", "Mix");
        theater4 = new Theater("Theater4", "3D", "Mix");
        theater5 = new Theater("Theater5", "IMAX Digital 2D", "Mix");
        theater6 = new Theater("Theater6", "4DX", "Mix");

        theater1.addSeatPrice("Normal", 220.0);
        theater2.addSeatPrice("Normal", 240.0);
        theater3.addSeatPrice("Normal", 240.0);
        theater3.addSeatPrice("Honeymoon", 260.0);
        theater4.addSeatPrice("Normal", 290.0);
        theater4.addSeatPrice("Honeymoon", 320.0);
        theater5.addSeatPrice("Normal", 300.0);
        theater5.addSeatPrice("Honeymoon", 340.0);
        theater6.addSeatPrice("Normal", 330.0);
        theater6.addSeatPrice("Honeymoon", 380.0);
    }

    private static CinemaManage cinemaInstance;
    public static synchronized CinemaManage getInstance(){
        if(cinemaInstance == null){
            cinemaInstance = new CinemaManage();
        }
        return cinemaInstance;
    }


    public void setAccount(Account account) {
        this.account = account;
    }

    public void setMovie1(Movie movie1) {
        this.movie1 = movie1;
    }

    public void setMovie2(Movie movie2) {
        this.movie2 = movie2;
    }

    public void setMovie3(Movie movie3) {
        this.movie3 = movie3;
    }

    public void setMovie4(Movie movie4) {
        this.movie4 = movie4;
    }

    public void setMovie5(Movie movie5) {
        this.movie5 = movie5;
    }

    public void setMovie6(Movie movie6) {
        this.movie6 = movie6;
    }

    public void logoutAccount() {
        this.account = null;
    }

    public Account getAccount() {
        return account;
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