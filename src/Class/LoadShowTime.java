package Class;

import java.io.*;

public class LoadShowTime {
    private CinemaOperator cinema = CinemaOperator.getInstance();
    private MoviesManage moviesManage = MoviesManage.getInstance();
    private Theater theater;
    private Movie movie;

    public void readShowTimeData(){
        String line;
        String[] data;
        File dir = new File("csvData");
        if (!dir.exists()){
            dir.mkdirs();
        }
        try {
            File file = new File("csvData/ShowTimeData.csv");
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);

            while ((line = reader.readLine()) != null){
                data = line.split(",");

                switch (data[0]){
                    case "Theater1": theater = cinema.getTheater1(); break;
                    case "Theater2": theater = cinema.getTheater2(); break;
                    case "Theater3": theater = cinema.getTheater3(); break;
                    case "Theater4": theater = cinema.getTheater4(); break;
                    case "Theater5": theater = cinema.getTheater5(); break;
                    case "Theater6": theater = cinema.getTheater6(); break;
                }

                for(Movie m : moviesManage.getMovieHashSet()){
                    if(data[1].equals(m.getNameEn())){
                        movie = m;
                        break;
                    }
                }

                theater.addRound(new Round(theater, movie, data[2]));
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
