package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = fxmlLoader.load();

        //stay in the same window
        Controller controller = fxmlLoader.getController();
        controller.init(primaryStage);


        primaryStage.setTitle("Din Apparels Limied");
        primaryStage.setFullScreen(false);
        primaryStage.setScene(new Scene(root, 900, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
