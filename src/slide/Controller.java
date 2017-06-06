package slide;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;

import java.awt.*;
import java.io.File;


public class Controller {

    @FXML
    private JFXButton btnStop, btnPlay, btnPause, btnOpen, btnBack, btnForward;


    Media media = new Media(new File("B:/Programs/Java/StudyJavaFX/src/base/vacation-1.mp3").toURI().toString());
    MediaPlayer mediaPlayer = new MediaPlayer(media);

    @FXML
    private Label time;


    @FXML
    public void Play() {
        btnPlay.setOnAction((ActionEvent event) -> {
            System.out.println("Playing...");
            mediaPlayer.play();
            mediaPlayer.currentTimeProperty().addListener((observableValue, oldDuration, newDuration) -> {
                //System.out.println("Player:" + observableValue + " | Changed from playing at: " + oldDuration + " to play at " + newDuration);
            });

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
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("*.mp3", "All files", "*.*"));
            File file = fileChooser.showOpenDialog(null);
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