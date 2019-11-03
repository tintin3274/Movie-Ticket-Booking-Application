package Controller;

import Class.*;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;


public class ReceiptController {
    @FXML ImageView ticketImageView;

    private String ref;
    private Image ticket;

    @FXML public void initialize() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                //initialize by get ref and ticket from ticket page and display ticket
                ticket = TicketController.image;
                ref = TicketController.ref;
                ticketImageView.setImage(TicketController.image);
            }
        });
    }

    //Write image file from Image ticket to save image ticket
    @FXML public void handleSaveTicketButton() {
        File dir = new File("ticketsImage");
        if (!dir.exists()){
            dir.mkdirs();
        }

        FileChooser fileChooser = new FileChooser();
        File defaultDirectory = new File("./ticketsImage/");
        fileChooser.setInitialDirectory(defaultDirectory);
        fileChooser.setInitialFileName(ref.replace(":", "").replace(".", ""));

        //Set extension filter
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("png files (*.png)", "*.png"));

        //Prompt user to select a file
        File file = fileChooser.showSaveDialog(null);

        if(file != null){
            try {
                //Cast Image snapshot to WritableImage
                WritableImage writableImage = (WritableImage) ticket;
                RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
                //Write the snapshot to the chosen file
                ImageIO.write(renderedImage, "png", file);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
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
}
