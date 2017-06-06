package mouse;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        loader.setControllerFactory(t -> new Controller(new EditorModel()));
        primaryStage.setTitle("Text");
        primaryStage.setScene(new Scene(loader.load(), 800, 600));
        primaryStage.show();

//        stage.setScene(new Scene(loader.load()));
//        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
