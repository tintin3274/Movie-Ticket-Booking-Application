package Class;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class LoadBookingData {
    private AccountsManage accountsManage = AccountsManage.getInstance();
    private CinemaManage cinema = CinemaManage.getInstance();
    private Account account;
    private Theater theater;
    private ArrayList<Round> roundsList;
    private Round round;
    private HashMap<String, Seat> seatsList;
    private Seat seat;

    public void readBookingData(){
        String line;
        String[] data;
        File dir = new File("csvData");
        if (!dir.exists()){
            dir.mkdirs();
        }
        try {
            File file = new File("csvData/BookingData.csv");
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);
            while ((line = reader.readLine()) != null){
                data = line.split(",");
                account = accountsManage.getAccount(data[0]);
                switch (data[1]){
                    case "Theater1": theater = cinema.getTheater1(); break;
                    case "Theater2": theater = cinema.getTheater2(); break;
                    case "Theater3": theater = cinema.getTheater3(); break;
                    case "Theater4": theater = cinema.getTheater4(); break;
                    case "Theater5": theater = cinema.getTheater5(); break;
                    case "Theater6": theater = cinema.getTheater6(); break;
                }
                roundsList = theater.getRoundsList();
                round = null;
                for(Round r : roundsList){
                    if(r.getTime().equals(data[3])) round = r;
                }
                seatsList = round.getSeatsList();
                seat = seatsList.get(data[4]);
                switch (data[5]){
                    case "Booking": if(account!=null && round!=null && round.getMovie().getNameEn().equals(data[2]) && !seat.isBooked()) seat.setBooking(account); break;
                    case "Cancel" : seat.cancelBooking();
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

