package Controller;

import Class.*;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.time.LocalDate;

public class AdminController {
    @FXML TextField nameEnTextField, nameThTextField, genreTextField, lengthTextField, addRoundTimeTextField;
    @FXML TextArea descriptionTextArea;
    @FXML CheckBox systemType1CheckBox, systemType2CheckBox, systemType3CheckBox, systemType4CheckBox, systemType5CheckBox;
    @FXML DatePicker releaseDateDatePicker;
    @FXML ImageView posterImageView;
    @FXML ComboBox rateComboBox = new ComboBox();
    @FXML ComboBox theaterComboBox = new ComboBox();
    @FXML TableView<Movie> movieTable;
    @FXML TableColumn<Movie, String> nameEnTable;
    @FXML TableColumn<Movie, String> nameThTable;
    @FXML TableColumn<Movie, String> rateTable;
    @FXML TableColumn<Movie, Integer> lengthTable;
    @FXML TableColumn<Movie, LocalDate> releaseDateTable;
    @FXML TableView<Round> roundTable;
    @FXML TableColumn<Round, String> timeRoundTable;
    @FXML Label addRoundTheaterLabel, addRoundMovieLabel;


    private CinemaOperator cinema = CinemaOperator.getInstance();
    private MoviesManage moviesManage = MoviesManage.getInstance();

    private int index;
    private Movie movieSelect;
    private Theater theaterSelect;
    private String theater;

    private String nameEn;
    private String nameTh;
    private String description;
    private String rate;
    private String genre;
    private String imgPosterPath;
    private int length;
    private LocalDate releaseDate;

    final ObservableList<Movie> listMovieData = FXCollections.observableArrayList();
    final ObservableList<Round> listRoundData = FXCollections.observableArrayList();

    @FXML public void initialize(){
        rateComboBox.getItems().addAll("ทั่วไป", "น 13+", "น 15+", "น 18+", "ฉ 20+", "-");
        rateComboBox.setEditable(false);
        rateComboBox.setValue("-");
        rateComboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String t, String t1) {
                rate = t1;
            }
        });

        nameEnTable.setCellValueFactory(new PropertyValueFactory<>("nameEn"));
        nameThTable.setCellValueFactory(new PropertyValueFactory<>("nameTh"));
        rateTable.setCellValueFactory(new PropertyValueFactory<>("rate"));
        lengthTable.setCellValueFactory(new PropertyValueFactory<>("length"));
        releaseDateTable.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));

        movieTable.setItems(listMovieData);

        theaterComboBox.getItems().addAll("Theater1", "Theater2", "Theater3", "Theater4", "Theater5", "Theater6");
        rateComboBox.setEditable(false);
        rateComboBox.setValue("-");
        rateComboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String t, String t1) {
                theater = t1;
            }
        });

        timeRoundTable.setCellValueFactory(new PropertyValueFactory<>("time"));
        roundTable.setItems(listRoundData);

        listMovieData.addAll(moviesManage.getMovieHashSet());
    }

    @FXML public void handleBrowseButton(ActionEvent event){
        String path = null;
        FileChooser fileChooser = new FileChooser();
        File defaultDirectory = new File("./");
        fileChooser.setInitialDirectory(defaultDirectory);
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            path = file.toURI().toString();
            //System.out.println(file.toURI().toString());
            if(path.indexOf("/image") >= 0){
                path = path.substring(path.indexOf("/image"));
                //System.out.println(path);
                posterImageView.setImage(new Image(path));
            }
        }
        imgPosterPath = path;
    }

    @FXML public void handleAddMovieButton(ActionEvent event){
        if(!nameEnTextField.getText().equals("") && !nameThTextField.getText().equals("") && !genreTextField.getText().equals("") && !descriptionTextArea.getText().equals("") && !lengthTextField.getText().equals("") && releaseDateDatePicker.getValue()!=null && imgPosterPath!=null){
            boolean isNumeric = lengthTextField.getText().chars().allMatch(Character::isDigit);
            if(isNumeric){
                Movie movie;
                length = Integer.parseInt(lengthTextField.getText());
                nameEn = nameEnTextField.getText();
                nameTh = nameThTextField.getText();
                rate = (String) rateComboBox.getValue();
                genre = genreTextField.getText();
                description = descriptionTextArea.getText();
                releaseDate = releaseDateDatePicker.getValue();

                movie = new Movie(nameEn, nameTh, rate, genre, imgPosterPath, length, releaseDate, description);
                if(systemType1CheckBox.isSelected()) movie.addSystemType("2D");
                if(systemType2CheckBox.isSelected()) movie.addSystemType("4K");
                if(systemType3CheckBox.isSelected()) movie.addSystemType("3D");
                if(systemType4CheckBox.isSelected()) movie.addSystemType("IMAX Digital 2D");
                if(systemType5CheckBox.isSelected()) movie.addSystemType("4DX");
                System.out.println(movie);

                listMovieData.add(movie);
            }
        }
    }

    @FXML private void handleMovieTableClicked(MouseEvent event){
        if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 1) {
            index = movieTable.getSelectionModel().getSelectedIndex();
            if(index != -1){
                movieSelect = listMovieData.get(index);
                showMovieData(event);
            }
        }
    }

    @FXML private void handleRoundTableClicked(MouseEvent event){
        if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 1) {
            index = roundTable.getSelectionModel().getSelectedIndex();
            if(index != -1){
                movieSelect = listRoundData.get(index).getMovie();
                showMovieData(event);
            }
        }
    }

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

    @FXML private void showMovieData(MouseEvent event){
        addRoundMovieLabel.setText("Title English");
        if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 1) {
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

    @FXML public void handleAddRoundButton(ActionEvent event){
        if(theaterSelect != null && movieSelect != null){
            String time = addRoundTimeTextField.getText();
            Round round = new Round(theaterSelect, movieSelect, time);
            theaterSelect.addRound(round);
            listRoundData.add(round);
        }
    }
}


