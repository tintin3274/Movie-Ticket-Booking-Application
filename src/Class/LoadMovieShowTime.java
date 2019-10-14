package Class;

import java.io.*;

public class LoadMovieShowTime {
    private CinemaOperator cinema = CinemaOperator.getInstance();
    private MoviesManage moviesManage = MoviesManage.getInstance();

    public void readMovieShowTimeData(){
        String line;
        String[] data;
        File dir = new File("csvData");
        if (!dir.exists()){
            dir.mkdirs();
        }
        try {
            File file = new File("csvData/MovieShowTimeData.csv");
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);

            while ((line = reader.readLine()) != null){
                data = line.split(",");
                cinema.setMovie1(findMovie(data[0]));
                cinema.setMovie2(findMovie(data[1]));
                cinema.setMovie3(findMovie(data[2]));
                cinema.setMovie4(findMovie(data[3]));
                cinema.setMovie5(findMovie(data[4]));
                cinema.setMovie6(findMovie(data[5]));
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Movie findMovie(String nameEn){
        for(Movie m : moviesManage.getMovieHashSet()){
            if(nameEn.equals(m.getNameEn())) return m;
        }
        return null;
    }
}
