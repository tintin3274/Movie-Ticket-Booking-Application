package Controller;

import Class.*;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class TicketController {
    @FXML AnchorPane ticketAnchorPane;
    @FXML Label titleLabel, dateLabel, showtimeLabel, theaterLabel, seatNoLabel, nameLabel, priceLabel;
    @FXML ImageView posterImageView;

    private Round round;
    private String seatNo;
    private Double price;
    public static Image image;
    CinemaManage cinema = CinemaManage.getInstance();

    @FXML public void initialize(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                titleLabel.setText(round.getMovie().getNameEn());
                LocalDate date = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY");
                dateLabel.setText(date.format(formatter));
                showtimeLabel.setText(round.getTime());
                theaterLabel.setText(round.getTheater().getName());
                seatNoLabel.setText(seatNo);
                posterImageView.setImage(new Image(round.getMovie().getImgPosterPath()));
                nameLabel.setText(cinema.getAccount().getFirstName()+" "+cinema.getAccount().getLastName());
                priceLabel.setText("PRICE Vat Included : "+price+" baht");
                generateTicket();
            }
        });
    }

    public void setRound(Round round) {
        this.round = round;
    }

    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @FXML private void generateTicket(){
        image = ticketAnchorPane.snapshot(new SnapshotParameters(), null);
    }

    @FXML public static void saveTicketImage() {
        FileChooser fileChooser = new FileChooser();
        File defaultDirectory = new File("./ticketsImage/");
        fileChooser.setInitialDirectory(defaultDirectory);
        fileChooser.setInitialFileName("Test");

        //Set extension filter
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("png files (*.png)", "*.png"));

        //Prompt user to select a file
        File file = fileChooser.showSaveDialog(null);

        if(file != null){
            try {
                //Pad the capture area
                //WritableImage writableImage = ticketAnchorPane.snapshot(new SnapshotParameters(), null);
                WritableImage writableImage = (WritableImage) image;
                RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
                //Write the snapshot to the chosen file
                ImageIO.write(renderedImage, "png", file);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
