package com.example.prvniprojekt;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.input.DragEvent;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

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
}