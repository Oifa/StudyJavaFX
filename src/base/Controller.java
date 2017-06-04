package base;

import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import java.awt.Label;
import java.io.File;


public class Controller {

    @FXML
    private JFXButton btnStop, btnPlay, btnPause, btnOpen, btnBack, btnForward;

    @FXML
    private Label timer;


    Media media = new Media(new File("B:/Programs/Java/StudyJavaFX/src/base/vacation-1.mp3").toURI().toString());
    MediaPlayer mediaPlayer = new MediaPlayer(media);

    @FXML
    public void Play() {
        btnPlay.setOnAction((ActionEvent event) -> {
            System.out.println("Playing...");
            mediaPlayer.play();
        });
    }

    @FXML
    public void Pause() {
        btnPause.setOnAction((ActionEvent e) -> {
            System.out.println("PAUSE");
            mediaPlayer.pause();
        });
    }

    @FXML
    public void Stop() {
        btnStop.setOnAction((ActionEvent e) -> {
            System.out.println("stop");
            mediaPlayer.stop();
        });
    }

    @FXML
    public void Forward() {
        btnForward.setOnAction((ActionEvent e) -> {
            mediaPlayer.seek(mediaPlayer.getCurrentTime().multiply(1.5));
        });
    }

    @FXML
    public void Back() {
        btnBack.setOnAction((ActionEvent e) -> {
            mediaPlayer.seek(mediaPlayer.getCurrentTime().divide(1.5));
        });
    }


    @FXML
    public void Open() {
        btnOpen.setOnAction((ActionEvent e) -> {
            FileChooser fc = new FileChooser();
            fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("*.mp3", "All files", "*.*"));
            File file = fc.showOpenDialog(null);
            String path = file.getAbsolutePath();
            path = path.replace("\\", "/");
            media = new Media(new File(path).toURI().toString());
            mediaPlayer.stop();
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setAutoPlay(true);
            //mediaView.setMediaPlayer(mediaPlayer);
        });
    }


}