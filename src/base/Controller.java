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
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javax.xml.ws.Binding;
import javax.xml.ws.BindingProvider;
import javafx.util.Duration;
import javafx.beans.binding.Bindings;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaView;



public class Controller implements Initializable {

    @FXML
    private JFXButton btnStop, btnPlay, btnPause, btnOpen, btnBack, btnForward, btnReload, btnPrev, btnNext;

    @FXML
    private Label currentTime, totalDuration;

    @FXML
    private Slider stateSlider, volumeSlider;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private ListView<String> listOfSong;

    @FXML
    private MediaView videoView;

    private static final double MIN_CHANGE = 0.5;

        String path = new File("src/base/vacation-1.mp3").getAbsolutePath();
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);



    @FXML
    private void LoadMusic(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("music.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Music");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            System.out.println("Cant load new window");
        }
    }

    @FXML
    private void LoadVideo(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("video.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Video");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            System.out.println("Cant load new window");
        }
    }

    @FXML
    private void LoadSlide(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("slide.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Slide");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            System.out.println("Cant load new window");
        }
    }

    @FXML
    private void LoadText(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("text.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Text");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            System.out.println("Cant load new window");
        }
    }

    @FXML
    private void LoadDocument(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("document.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Document");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            System.out.println("Cant load new window");
        }
    }


    @FXML
    private void LoadAnsware(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("answare.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Ask");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            System.out.println("Cant load new window");
        }
    }

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
                    totalDuration.setText(String.format("%2s", (int)volumeSlider.getValue()));
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
    private void Open() {
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
                File tempFile = new File(path.trim());
                String fileName = tempFile.getName();
                listOfSong.getItems().addAll(fileName);
            } catch (Exception e1) {
                System.out.println("Exit");
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }


}