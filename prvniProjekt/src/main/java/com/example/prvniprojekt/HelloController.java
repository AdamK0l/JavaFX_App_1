package com.example.prvniprojekt;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.*;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import java.io.*;
import java.nio.channels.FileChannel;

public class HelloController {

    public ImageView imageView1;
    public ImageView imageView2;


    @FXML
    void imageViewDragDropped1(DragEvent event) {
        Dragboard dragboard = event.getDragboard();
        if(dragboard.hasImage() || dragboard.hasFiles()) {
            try {
                imageView1.setImage(new Image((new FileInputStream(dragboard.getFiles().get(0)))));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

        }
    }

    @FXML
    void imageViewDragDropped2(DragEvent event) {
        Dragboard dragboard = event.getDragboard();
        if(dragboard.hasImage() || dragboard.hasFiles()) {
            try {
                imageView2.setImage(new Image((new FileInputStream(dragboard.getFiles().get(0)))));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

        }
    }

    @FXML
    void imageViewDragOver(DragEvent event) {
        Dragboard dragboard = event.getDragboard();
        if(dragboard.hasImage() || dragboard.hasFiles()){
            event.acceptTransferModes(TransferMode.COPY);
        }

        event.consume();
    }

    @FXML
    void closeWindow (ActionEvent event) {
            javafx.application.Platform.exit();
    }

    @FXML
    void blackWhite (ActionEvent event) {
        ColorAdjust colorAdjust1= new ColorAdjust();
        colorAdjust1.setSaturation(-1);
        imageView2.setEffect(colorAdjust1);
    }

    @FXML
    void invertColours(ActionEvent event) {
        Image originalImage = imageView1.getImage();
        int width = (int) originalImage.getWidth();
        int height = (int) originalImage.getHeight();
        WritableImage invertedImage = new WritableImage(width, height);
        PixelWriter pixelWriter = invertedImage.getPixelWriter();
        PixelReader pixelReader = originalImage.getPixelReader();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = pixelReader.getColor(x, y);

                Color invertedColor = Color.color(1.0 - color.getRed(), 1.0 - color.getGreen(), 1.0 - color.getBlue());

                pixelWriter.setColor(x, y, invertedColor);
            }
        }
        imageView1.setImage(invertedImage);

    }

    public void save(ActionEvent event) {
        if (imageView1.getImage() != null) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save Image");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png"));

            File file = fileChooser.showSaveDialog(imageView1.getScene().getWindow());

            if (file != null) {
                try {
                    FileChannel source = new FileInputStream(imageView1.getImage().getUrl().replace("file:/", "")).getChannel();
                    FileChannel destination = new FileOutputStream(file).getChannel();
                    destination.transferFrom(source, 0, source.size());
                    source.close();
                    destination.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML
    void clearImageView1(ActionEvent event) {
        imageView1.setImage(null);
    }
    @FXML
    void clearImageView2(ActionEvent event) {
        imageView2.setImage(null);
    }
}