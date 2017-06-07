package base;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.util.ResourceBundle;
import java.text.DecimalFormat;
import java.text.DecimalFormat;

import javafx.application.Platform;
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
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Duration;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javax.xml.ws.Binding;
import javax.xml.ws.BindingProvider;
import javafx.util.Duration;
import javafx.beans.binding.Bindings;




public class Controller implements Initializable {

    @FXML
    private JFXButton btnStop, btnPlay, btnPause, btnOpen, btnBack, btnForward, btnReload, btnPrev, btnNext;

    @FXML
    private Label currentTime, totalDuration;

    @FXML
    private Slider stateSlider, volumeSlider;

    @FXML
    private ListView<String> listOfSong;

    private static final double MIN_CHANGE = 0.5;


    File file = new File("B:\\Programs\\Java\\StudyJavaFX\\src\\base\\vacation-1.mp3");
    Media media = new Media(file.toURI().toString());
    MediaPlayer mediaPlayer = new MediaPlayer(media);





    @FXML
    public void Play() {
        btnPlay.setOnAction((ActionEvent event) -> {
            System.out.println("Playing...");
            mediaPlayer.play();

            /*  This code is used for the music slider.
            *   Code include: value of the music state, changing state, current time,
            *   total duration.
            */
            stateSlider.valueChangingProperty().addListener((obs, wasChanging, isChanging) -> {
                if (! isChanging) {
                    mediaPlayer.seek(Duration.seconds(stateSlider.getValue()));
                }
            });

            stateSlider.valueProperty().addListener((obs, oldValue, newValue) -> {
                if (! stateSlider.isValueChanging()) {
                    double currentTime = mediaPlayer.getCurrentTime().toSeconds();
                    if (Math.abs(currentTime - newValue.doubleValue()) > MIN_CHANGE) {
                        mediaPlayer.seek(Duration.seconds(newValue.doubleValue()));
                    }
                }
            });


            mediaPlayer.currentTimeProperty().addListener((observable, oldTime, newTime) -> {
                if (! stateSlider.isValueChanging()) {
                    stateSlider.setValue(newTime.toSeconds());
                }
            });

            stateSlider.valueProperty().addListener(new ChangeListener<Number>() {
                public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
                    currentTime.setText(String.format("%02d", newValue.intValue()));
                    totalDuration.setText(String.format("%.2f", (volumeSlider.getValue() / 180)*6));
                }
            });

            /*  This code changing volume of music.
            *   Min state = 0; max state = 100
            */
            volumeSlider.setValue(mediaPlayer.getVolume() * 100);
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
    public void Prev() {
        btnPrev.setOnAction((ActionEvent e) -> {
            System.out.println("prev");
            mediaPlayer.seek(mediaPlayer.getStartTime());
            mediaPlayer.stop();
        });
    }

    @FXML
    public void Next() {
        btnNext.setOnAction((ActionEvent e) -> {
            System.out.println("next");
            mediaPlayer.seek(mediaPlayer.getTotalDuration());
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
    public void Reload() {
        btnReload.setOnAction((ActionEvent e) -> {
            System.out.println("reload");
            mediaPlayer.seek(mediaPlayer.getStartTime());
        });
    }



    @FXML
    public void Open() {
        btnOpen.setOnAction((ActionEvent e) -> {
            try {
                FileChooser fileChooser = new FileChooser();
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("*.mp3", "All files", "*.*"));
                File file = fileChooser.showOpenDialog(null);
                String path = file.getAbsolutePath();
                path = path.replace("\\", "/");
                media = new Media(new File(path).toURI().toString());
                mediaPlayer.stop();
                mediaPlayer = new MediaPlayer(media);
            } catch (Exception e1) {
                System.out.println("Exit");
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        stateOfSong.valueProperty().addListener((observable, oldValue, newValue) -> {
//            System.out.println("Player:" + observable + " | Changed from playing at: " + oldValue + " to play at " + newValue);
//        });


    }


}