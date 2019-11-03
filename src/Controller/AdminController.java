package Controller;

import Class.*;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;

public class AdminController {
    @FXML TextField nameEnTextField, nameThTextField, genreTextField, lengthTextField, addRoundTimeTextField, videoTextField;
    @FXML TextArea descriptionTextArea;
    @FXML CheckBox systemType1CheckBox, systemType2CheckBox, systemType3CheckBox, systemType4CheckBox, systemType5CheckBox;
    @FXML DatePicker releaseDateDatePicker;
    @FXML ImageView posterImageView;
    @FXML ComboBox rateComboBox = new ComboBox();
    @FXML ComboBox theaterComboBox = new ComboBox();
    @FXML ComboBox movieComboBox = new ComboBox();
    @FXML TableView<Movie> movieTable;
    @FXML TableColumn<Movie, String> nameEnTable;
    @FXML TableColumn<Movie, String> nameThTable;
    @FXML TableColumn<Movie, String> rateTable;
    @FXML TableColumn<Movie, Integer> lengthTable;
    @FXML TableColumn<Movie, LocalDate> releaseDateTable;
    @FXML TableView<Round> roundTable;
    @FXML TableColumn<Round, String> timeRoundTable;
    @FXML Label addRoundTheaterLabel, addRoundMovieLabel;


    private CinemaManage cinema = CinemaManage.getInstance();
    private MoviesManage moviesManage = MoviesManage.getInstance();

    private int index;
    private Movie movieSelect;
    private Theater theaterSelect;
    private Round roundSelect;
    private String theater, movieShowTime;

    private String nameEn;
    private String nameTh;
    private String description;
    private String rate;
    private String genre;
    private String imgPosterPath;
    private String videoPath;
    private int length;
    private LocalDate releaseDate;

    final ObservableList<Movie> listMovieData = FXCollections.observableArrayList();
    final ObservableList<Round> listRoundData = FXCollections.observableArrayList();

    @FXML public void initialize(){
        //initialize rate ComboBox
        rateComboBox.getItems().addAll("ทั่วไป", "น 13+", "น 15+", "น 18+", "ฉ 20+", "-");
        rateComboBox.setEditable(false);
        rateComboBox.setValue("-");
        rateComboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String t, String t1) {
                rate = t1;
            }
        });

        //initialize theater ComboBox
        theaterComboBox.getItems().addAll("Theater1", "Theater2", "Theater3", "Theater4", "Theater5", "Theater6");
        rateComboBox.setEditable(false);
        rateComboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String t, String t1) {
                theater = t1;
            }
        });

        //initialize movie ComboBox
        movieComboBox.getItems().addAll("Movie1", "Movie2", "Movie3", "Movie4", "Movie5", "Movie6");
        movieComboBox.setEditable(false);
        movieComboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String t, String t1) {
                movieShowTime = t1;
            }
        });

        //initialize movie TableView
        nameEnTable.setCellValueFactory(new PropertyValueFactory<>("nameEn"));
        nameThTable.setCellValueFactory(new PropertyValueFactory<>("nameTh"));
        rateTable.setCellValueFactory(new PropertyValueFactory<>("rate"));
        lengthTable.setCellValueFactory(new PropertyValueFactory<>("length"));
        releaseDateTable.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));

        movieTable.setItems(listMovieData);

        //initialize round TableView
        timeRoundTable.setCellValueFactory(new PropertyValueFactory<>("time"));
        roundTable.setItems(listRoundData);

        listMovieData.addAll(moviesManage.getMovieHashSet());
    }

    //Browse file image poster to collect path
    @FXML public void handleBrowseImageButton(ActionEvent event){
        File dir = new File("poster");
        if (!dir.exists()){
            dir.mkdirs();
        }

        String path = null;
        FileChooser fileChooser = new FileChooser();
        File defaultDirectory = new File("./poster/");
        fileChooser.setInitialDirectory(defaultDirectory);
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            path = file.toURI().toString();
            //System.out.println(file.toURI().toString());
            if(path.indexOf("/poster") >= 0){
                path = path.substring(path.indexOf("poster"));
                //System.out.println(path);
                posterImageView.setImage(new Image(path));
            }
        }
        imgPosterPath = path;
    }

    //Browse file video trailer to collect path
    @FXML public void handleBrowseVideoButton(ActionEvent event){
        File dir = new File("trailer");
        if (!dir.exists()){
            dir.mkdirs();
        }

        String path = null;
        FileChooser fileChooser = new FileChooser();
        File defaultDirectory = new File("./trailer/");
        fileChooser.setInitialDirectory(defaultDirectory);
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            path = file.toURI().getPath();
            if(path.indexOf("/trailer") >= 0){
                path = path.substring(path.indexOf("trailer"));
                videoTextField.setText(path);
            }
        }
        videoPath = path;
    }

    //Create object movie then Add movie to moviesManage and movie TableView
    @FXML public void handleAddMovieButton(ActionEvent event){
        if(!nameEnTextField.getText().equals("") && !nameThTextField.getText().equals("") && !genreTextField.getText().equals("") && !descriptionTextArea.getText().equals("") && !lengthTextField.getText().equals("") && releaseDateDatePicker.getValue()!=null && imgPosterPath!=null && videoPath!=null){
            boolean isNumeric = lengthTextField.getText().chars().allMatch(Character::isDigit);
            if(isNumeric){
                Movie movie;
                length = Integer.parseInt(lengthTextField.getText());
                nameEn = nameEnTextField.getText();
                nameTh = nameThTextField.getText();
                rate = (String) rateComboBox.getValue();
                genre = genreTextField.getText();
                description = descriptionTextArea.getText();
                if(description.contains("\n")) description= description.replaceAll("\n", " ");
                releaseDate = releaseDateDatePicker.getValue();

                movie = new Movie(nameEn, nameTh, rate, genre, imgPosterPath, length, releaseDate, description, videoPath);
                if(systemType1CheckBox.isSelected()) movie.addSystemType("2D");
                if(systemType2CheckBox.isSelected()) movie.addSystemType("4K");
                if(systemType3CheckBox.isSelected()) movie.addSystemType("3D");
                if(systemType4CheckBox.isSelected()) movie.addSystemType("IMAX Digital 2D");
                if(systemType5CheckBox.isSelected()) movie.addSystemType("4DX");
                //System.out.println(movie);

                moviesManage.addMovie(movie);
                listMovieData.add(movie);
                clear();
            }
        }
    }

    //Remove object movie from moviesManage and movie TableView
    @FXML public void handleRemoveMovieButton(ActionEvent event){
        if (movieSelect != null){
            if(cinema.getMovie1() == movieSelect) cinema.setMovie1(null);
            if(cinema.getMovie2() == movieSelect) cinema.setMovie2(null);
            if(cinema.getMovie3() == movieSelect) cinema.setMovie3(null);
            if(cinema.getMovie4() == movieSelect) cinema.setMovie4(null);
            if(cinema.getMovie5() == movieSelect) cinema.setMovie5(null);
            if(cinema.getMovie6() == movieSelect) cinema.setMovie6(null);
            moviesManage.removeMovie(movieSelect);
            listMovieData.remove(movieSelect);
            movieSelect = null;
            clear();
        }
    }

    //Select object movie from movie TableView
    @FXML private void handleMovieTableClicked(MouseEvent event){
        if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 1) {
            index = movieTable.getSelectionModel().getSelectedIndex();
            if(index != -1){
                movieSelect = listMovieData.get(index);
                showMovieData();
            }
        }
    }

    //Select object round from round TableView
    @FXML private void handleRoundTableClicked(MouseEvent event){
        if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 1) {
            index = roundTable.getSelectionModel().getSelectedIndex();
            if(index != -1){
                roundSelect = listRoundData.get(index);
                movieSelect = listRoundData.get(index).getMovie();
                showMovieData();
                movieComboBox.setValue("");
            }
        }
    }

    //Select object movie in ComboBox by get movie object from CinemaManage
    @FXML public void handleMovieComboBox(ActionEvent event){
        movieShowTime = (String) movieComboBox.getValue();
        switch (movieShowTime){
            case "Movie1": movieSelect = cinema.getMovie1();break;
            case "Movie2": movieSelect = cinema.getMovie2();break;
            case "Movie3": movieSelect = cinema.getMovie3();break;
            case "Movie4": movieSelect = cinema.getMovie4();break;
            case "Movie5": movieSelect = cinema.getMovie5();break;
            case "Movie6": movieSelect = cinema.getMovie6();break;
        }
        showMovieData();
    }

    //Set object movie selected keep in CinemaManage
    @FXML public void handleSetMovieButton(ActionEvent event){
        movieShowTime = (String) movieComboBox.getValue();
        if(movieShowTime != null){
            switch (movieShowTime){
                case "Movie1": cinema.setMovie1(movieSelect);break;
                case "Movie2": cinema.setMovie2(movieSelect);break;
                case "Movie3": cinema.setMovie3(movieSelect);break;
                case "Movie4": cinema.setMovie4(movieSelect);break;
                case "Movie5": cinema.setMovie5(movieSelect);break;
                case "Movie6": cinema.setMovie6(movieSelect);break;
            }
            showMovieData();
        }
    }

    //Select object theater in ComboBox by get theater object from CinemaManage and get round from theater add to round TableView
    @FXML public void handleTheaterComboBox(ActionEvent event){
        addRoundTheaterLabel.setText("Theater");
        theater = (String) theaterComboBox.getValue();
        switch (theater){
            case "Theater1": theaterSelect = cinema.getTheater1();break;
            case "Theater2": theaterSelect = cinema.getTheater2();break;
            case "Theater3": theaterSelect = cinema.getTheater3();break;
            case "Theater4": theaterSelect = cinema.getTheater4();break;
            case "Theater5": theaterSelect = cinema.getTheater5();break;
            case "Theater6": theaterSelect = cinema.getTheater6();break;
        }
        listRoundData.clear();
        for(Round r : theaterSelect.getRoundsList()){
            listRoundData.add(r);
        }
        addRoundTheaterLabel.setText(theaterSelect.getName());
    }

    //Display information of selected movie object
    @FXML private void showMovieData(){
        if(movieSelect != null){
            addRoundMovieLabel.setText(movieSelect.getNameEn());

            nameEnTextField.setText(movieSelect.getNameEn());
            nameThTextField.setText(movieSelect.getNameTh());
            genreTextField.setText(movieSelect.getGenre());
            descriptionTextArea.setText(movieSelect.getDescription());
            lengthTextField.setText(movieSelect.getLength()+"");
            rateComboBox.setValue(movieSelect.getRate());
            releaseDateDatePicker.setValue(movieSelect.getReleaseDate());
            imgPosterPath = movieSelect.getImgPosterPath();
            posterImageView.setImage(new Image(imgPosterPath));
            videoPath = movieSelect.getVideoPath();
            videoTextField.setText(videoPath);
            systemType1CheckBox.setSelected(false);
            systemType2CheckBox.setSelected(false);
            systemType3CheckBox.setSelected(false);
            systemType4CheckBox.setSelected(false);
            systemType5CheckBox.setSelected(false);
            for(String s : movieSelect.getSystemType()){
                switch (s){
                    case "2D": systemType1CheckBox.setSelected(true);break;
                    case "4K": systemType2CheckBox.setSelected(true);break;
                    case "3D": systemType3CheckBox.setSelected(true);break;
                    case "IMAX Digital 2D": systemType4CheckBox.setSelected(true);break;
                    case "4DX": systemType5CheckBox.setSelected(true);break;
                }
            }
        }
    }

    //Create object round and add to round TableView
    @FXML public void handleAddRoundButton(ActionEvent event){
        if(theaterSelect != null && movieSelect != null && !addRoundTimeTextField.getText().equals("")){
            String time = addRoundTimeTextField.getText();
            Round round = new Round(theaterSelect, movieSelect, time);
            theaterSelect.addRound(round);
            listRoundData.add(round);
            addRoundTimeTextField.setText("");
            clear();
        }
    }

    //Remove object round from round TableView
    @FXML public void handleRemoveRoundButton(ActionEvent event){
        if(theaterSelect != null && roundSelect != null){
            theaterSelect.removeRound(roundSelect);
            listRoundData.remove(roundSelect);
            roundSelect = null;
            clear();
        }
    }

    //Load movie select page
    @FXML public void handleMainPageButton(ActionEvent event){
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/movie_select.fxml"));
        try {
            stage.setScene(new Scene(loader.load(), 1280, 720));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.show();
    }

    //Write file MovieData.csv for save movie data
    @FXML public void exportMovieData(){
        File dir = new File("csvData");
        if (!dir.exists()){
            dir.mkdirs();
        }
        try {
            File file = new File("csvData/MovieData.csv");
            file.createNewFile();
            //FileWriter fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8));

            for(Movie m : moviesManage.getMovieHashSet()){
                writer.write(m.getNameEn()+"<,>"+m.getNameTh()+"<,>"+m.getRate()+"<,>"+m.getGenre()+"<,>"+m.getImgPosterPath()+"<,>"+m.getLength()+"<,>"+m.getReleaseDate()+"<,>"+m.getDescription());

                if(m.getSystemType().contains("2D")) writer.write("<,>"+"True_2D");
                else writer.write("<,>"+"False_2D");

                if(m.getSystemType().contains("4K")) writer.write("<,>"+"True_4K");
                else writer.write("<,>"+"False_4K");

                if(m.getSystemType().contains("3D")) writer.write("<,>"+"True_3D");
                else writer.write("<,>"+"False_3D");

                if(m.getSystemType().contains("IMAX Digital 2D")) writer.write("<,>"+"True_IMAX Digital 2D");
                else writer.write("<,>"+"False_IMAX Digital 2D");

                if(m.getSystemType().contains("4DX")) writer.write("<,>"+"True_4DX");
                else writer.write("<,>"+"False_4DX");

                writer.write("<,>"+m.getVideoPath());

                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Write file ShowTimeData.csv for save round data
    @FXML public void exportShowTimeData(){
        File dir = new File("csvData");
        if (!dir.exists()){
            dir.mkdirs();
        }
        try {
            File file = new File("csvData/ShowTimeData.csv");
            file.createNewFile();
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileWriter);

            for(Round r : cinema.getTheater1().getRoundsList()){
                writer.write(r.getTheater().getName()+","+r.getMovie().getNameEn()+","+r.getTime());
                writer.newLine();
            }

            for(Round r : cinema.getTheater2().getRoundsList()){
                writer.write(r.getTheater().getName()+","+r.getMovie().getNameEn()+","+r.getTime());
                writer.newLine();
            }

            for(Round r : cinema.getTheater3().getRoundsList()){
                writer.write(r.getTheater().getName()+","+r.getMovie().getNameEn()+","+r.getTime());
                writer.newLine();
            }

            for(Round r : cinema.getTheater4().getRoundsList()){
                writer.write(r.getTheater().getName()+","+r.getMovie().getNameEn()+","+r.getTime());
                writer.newLine();
            }

            for(Round r : cinema.getTheater5().getRoundsList()){
                writer.write(r.getTheater().getName()+","+r.getMovie().getNameEn()+","+r.getTime());
                writer.newLine();
            }

            for(Round r : cinema.getTheater6().getRoundsList()){
                writer.write(r.getTheater().getName()+","+r.getMovie().getNameEn()+","+r.getTime());
                writer.newLine();
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Write file MoviesShowingData.csv for save movie order in movie select page data
    @FXML public void exportMoviesShowingData(){
        File dir = new File("csvData");
        if (!dir.exists()){
            dir.mkdirs();
        }
        try {
            File file = new File("csvData/MoviesShowingData.csv");
            file.createNewFile();
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileWriter);

            writer.write(cinema.getMovie1().getNameEn()+",");
            writer.write(cinema.getMovie2().getNameEn()+",");
            writer.write(cinema.getMovie3().getNameEn()+",");
            writer.write(cinema.getMovie4().getNameEn()+",");
            writer.write(cinema.getMovie5().getNameEn()+",");
            writer.write(cinema.getMovie6().getNameEn());
            writer.newLine();

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Write file BookingData.csv for save booking data
    @FXML public void exportBookingData(){
        File dir = new File("csvData");
        if (!dir.exists()){
            dir.mkdirs();
        }
        try {
            File file = new File("csvData/BookingData.csv");
            file.createNewFile();
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileWriter);

            ArrayList<Round> roundsList;
            for(int i=1; i<=6; i++){
                roundsList = null;
                switch (i){
                    case 1: roundsList = cinema.getTheater1().getRoundsList(); break;
                    case 2: roundsList = cinema.getTheater2().getRoundsList(); break;
                    case 3: roundsList = cinema.getTheater3().getRoundsList(); break;
                    case 4: roundsList = cinema.getTheater4().getRoundsList(); break;
                    case 5: roundsList = cinema.getTheater5().getRoundsList(); break;
                    case 6: roundsList = cinema.getTheater6().getRoundsList(); break;
                }

                if(roundsList != null){
                    for(Round r : roundsList){
                        for (String k : r.getSeatsList().keySet()){
                            if(r.getSeatsList().get(k).isBooked()){
                                writer.write(r.getSeatsList().get(k).getAccount().getUsername()+","+r.getTheater().getName()+","+r.getMovie().getNameEn()+","+r.getTime()+","+k+","+"Booking");
                                writer.newLine();
                            }
                        }
                    }
                }
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Call all save data method
    @FXML public void exportAll(){
        exportMovieData();
        exportShowTimeData();
        exportMoviesShowingData();
        exportBookingData();
    }

    //Clear field and select object
    @FXML public void clear(){
        addRoundMovieLabel.setText("Title English");

        nameEnTextField.setText("");
        nameThTextField.setText("");
        genreTextField.setText("");
        descriptionTextArea.setText("");
        lengthTextField.setText("");
        rateComboBox.setValue("");
        releaseDateDatePicker.setValue(null);
        imgPosterPath = "";
        posterImageView.setImage(new Image("/poster/ComingSoon.jpg"));
        videoTextField.setText("");
        systemType1CheckBox.setSelected(false);
        systemType2CheckBox.setSelected(false);
        systemType3CheckBox.setSelected(false);
        systemType4CheckBox.setSelected(false);
        systemType5CheckBox.setSelected(false);

        movieSelect = null;
        roundSelect = null;
        movieComboBox.setValue("");
    }
}