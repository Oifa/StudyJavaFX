package base;

import com.jfoenix.controls.JFXButton;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import javafx.scene.layout.HBox;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class SlideController implements Initializable {

	@FXML
	private JFXButton btnPlay, btnOpen, btnBack, btnNext;

	@FXML
	private ImageView slideView;

	@FXML
	private HBox hBox;


	String path = new File("B:/Programs/Java/StudyJavaFX/src/base/imge.jpg").getAbsolutePath();
	Image image = new Image(new File(path).toURI().toString());


//	@FXML
//	public void Next() {
//		btnNext.setOnAction((ActionEvent e) -> {
//			System.out.println("next");
//		});
//	}
//
//	@FXML
//	public void Back() {
//		btnBack.setOnAction((ActionEvent e) -> {
//		});
//	}

	@FXML
	private void Open() {
		btnOpen.setOnAction((ActionEvent e) -> {
			try {
				FileChooser fileChooser = new FileChooser();
				fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(".jpg", "All files", "*.*"));
				File file = fileChooser.showOpenDialog(null);
				String path = file.getAbsolutePath();
				path = path.replace("\\", "/");
				image = new Image(new File(path).toURI().toString());
				slideView.setImage(image);
				hBox.setPrefSize(image.getWidth(), image.getHeight());
			} catch (Exception e1) {
				System.out.println("Exit");
			}
		});
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {


	}


}