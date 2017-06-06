package slide;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import com.jfoenix.controls.JFXButton;
import java.awt.*;
import java.io.File;
import javafx.event.EventHandler;
import java.util.List;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser.ExtensionFilter;




public class Controller {

    @FXML
    private JFXButton btnOpens, btnOp;

    @FXML
    private ListView listImages;

    @FXML
    public void Opens() {
        btnOpens.setOnAction((ActionEvent e) -> {
            FileChooser fc = new FileChooser();
            fc.setInitialDirectory(new File ("C:/Temp"));
            fc.getExtensionFilters().addAll(new ExtensionFilter("png files","*.png"));
            File selectedFile = fc.showOpenDialog(null);
            if(selectedFile != null){
                listImages.getItems().add(selectedFile.getAbsolutePath());
            } else {
                System.out.println("File is not valid");
            }
        });
    }

    @FXML
    public void Op() {
        btnOp.setOnAction((ActionEvent e) -> {
            FileChooser fc = new FileChooser();
            fc.setInitialDirectory(new File("C:/Temp"));
            fc.getExtensionFilters().addAll(new ExtensionFilter("png files", "*.png"));
            List<File> selectedFiles = fc.showOpenMultipleDialog(null);
            if (selectedFiles != null) {
                for (int i = 0; i < selectedFiles.size(); i++) {
                    listImages.getItems().add(selectedFiles.get(i).getAbsolutePath());
                }

            } else {
                System.out.println("File is not valid");
            }
        });
    }

}