package Class;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

public class LoadMovieData {
    private MoviesManage moviesManage = MoviesManage.getInstance();

    public void readMovieData(){
        String line;
        String[] data;
        File dir = new File("csvData");
        if (!dir.exists()){
            dir.mkdirs();
        }
        try {
            File file = new File("csvData/MovieData.csv");
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
            while ((line = reader.readLine()) != null){
                data = line.split("<,>");
                Movie movie = new Movie(data[0], data[1], data[2], data[3], data[4], Integer.parseInt(data[5]), LocalDate.parse(data[6]), data[7], data[13]);
                if(data[8].equals("True_2D")) movie.addSystemType("2D");
                if(data[9].equals("True_4K")) movie.addSystemType("4K");
                if(data[10].equals("True_3D")) movie.addSystemType("3D");
                if(data[11].equals("True_IMAX Digital 2D")) movie.addSystemType("IMAX Digital 2D");
                if(data[12].equals("True_4DX")) movie.addSystemType("4DX");
                moviesManage.addMovie(movie);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
            //e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


