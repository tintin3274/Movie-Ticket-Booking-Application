package Controller;

import Class.*;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class TicketController {
    @FXML AnchorPane ticketAnchorPane;
    @FXML Label titleLabel, dateLabel, showtimeLabel, theaterLabel, seatNoLabel, nameLabel, priceLabel, refLabel;
    @FXML ImageView posterImageView, qrCodeImageView;

    private Round round;
    private String seatNo;
    private Double price;
    public static Image image;
    public static String ref;
    CinemaManage cinema = CinemaManage.getInstance();

    @FXML public void initialize(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                ref = LocalDateTime.now().toString();

                titleLabel.setText(round.getMovie().getNameEn());
                LocalDate date = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY");
                dateLabel.setText(date.format(formatter));
                showtimeLabel.setText(round.getTime());
                theaterLabel.setText(round.getTheater().getName());
                seatNoLabel.setText(seatNo);
                posterImageView.setImage(new Image(round.getMovie().getImgPosterPath()));
                nameLabel.setText(cinema.getAccount().getFirstName()+" "+cinema.getAccount().getLastName());
                priceLabel.setText("PRICE Vat Included : "+String.format("%,.2f", price)+" baht");
                refLabel.setText("REF No. "+ref);

                String qrText = cinema.getAccount().getFirstName()+" "+cinema.getAccount().getLastName()+
                        "\nPRICE Vat Included : "+String.format("%,.2f", price)+" baht"+
                        "\nREF No. "+ ref+
                        "\nSEAT No.\n"+seatNo;

                int width = 1000;
                int height = 1000;
                QRCodeWriter qrCodeWriter = new QRCodeWriter();
                BufferedImage bufferedImage = null;
                try {
                    BitMatrix byteMatrix = qrCodeWriter.encode(qrText, BarcodeFormat.QR_CODE, width, height);
                    bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                    bufferedImage.createGraphics();

                    Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
                    graphics.setColor(Color.WHITE);
                    graphics.fillRect(0, 0, width, height);
                    graphics.setColor(Color.BLACK);

                    for (int i = 0; i < height; i++) {
                        for (int j = 0; j < width; j++) {
                            if (byteMatrix.get(i, j)) {
                                graphics.fillRect(i, j, 1, 1);
                            }
                        }
                    }
                } catch (WriterException e) {
                    e.printStackTrace();
                }
                qrCodeImageView.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
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

    //Snapshot data in AnchorPane to image and set to image
    @FXML private void generateTicket(){
        image = ticketAnchorPane.snapshot(new SnapshotParameters(), null);
    }
}