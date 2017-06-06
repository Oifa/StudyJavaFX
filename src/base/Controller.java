package base;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import javafx.scene.control.Label;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXSlider;


import javafx.scene.control.Slider;

import javax.xml.ws.Binding;
import javax.xml.ws.BindingProvider;
import java.io.File;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import java.text.DecimalFormat;
import javafx.beans.binding.Bindings;



public class Controller implements Initializable {

    @FXML
    private JFXButton btnStop, btnPlay, btnPause, btnOpen, btnBack, btnForward;

    @FXML
    private JFXSlider volume, stateOfSong;

    @FXML
    private Label time;

    @FXML
    private Slider state, volumeSlider;

    private final DecimalFormat formatter = new DecimalFormat("00.00");
    private Duration totalTime;



    Media media = new Media(new File("B:/Programs/Java/StudyJavaFX/src/base/vacation-1.mp3").toURI().toString());
    MediaPlayer mediaPlayer = new MediaPlayer(media);



    @FXML
    public void Play() {
        btnPlay.setOnAction((ActionEvent event) -> {
            System.out.println("Playing...");
            mediaPlayer.play();
//            mediaPlayer.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
//                time.textProperty().bind(Bindings.format("%.2f", (state.getValue() / 100.0)));
//                //label.textProperty().bind(Bindings.format("%.2f", slider.valueProperty()));
//
//            });

            state.valueProperty().addListener((observable, oldValue, newValue) -> {
                time.textProperty().bind(Bindings.format("%.2f", (state.getValue() / 100.0)));
            });

            volumeSlider.setValue(mediaPlayer.getVolume() * 100); //1.0 = max\ 0.0 = min
            volumeSlider.valueProperty().addListener(new InvalidationListener() {
                @Override
                public void invalidated(Observable observable) {
                    mediaPlayer.setVolume(volumeSlider.getValue() / 100);
                }
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
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stateOfSong.valueProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Player:" + observable + " | Changed from playing at: " + oldValue + " to play at " + newValue);
        });
    }


}