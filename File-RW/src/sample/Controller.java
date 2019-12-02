package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import static java.lang.System.exit;



public class Controller {
    public TextArea display;
    Stage stage ;

    public void Open(ActionEvent actionEvent)throws Exception {

        FileChooser fileChooser  = new FileChooser();
        fileChooser.setTitle("Open File");

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Any File", "*.*")
        );

        File file = fileChooser.showOpenDialog(this.stage);
        FileInputStream fileInputStream = new FileInputStream(file);

        //fileInputStream.read();
        Path path = Paths.get(file.toURI());
        List<String> lines = Files.readAllLines(path);
        for (int i=0;i<lines.size();i++)
        {

            //System.out.println(lines.get(i));
            display.appendText(lines.get(i)+"\n");
        }
        this.display.setEditable(true);

    }

    public void SaveAll(ActionEvent actionEvent) throws IOException {

        String  content = display.getText();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("File Operation -> Write");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text File","*.txt"),
                new FileChooser.ExtensionFilter("All File","*.*")
        );

        File f = fileChooser.showOpenDialog(this.stage);
        Path path = Paths.get(f.toURI());

        Files.write(path,content.getBytes(),StandardOpenOption.APPEND);
        display.setText("");
        this.display.setEditable(false);


    }






    public void DeleteAll(ActionEvent actionEvent) {
    }

    public void CloseAll(ActionEvent actionEvent) {
        exit(0);
    }

    public void init(Stage primaryStage) {

        this.stage=primaryStage;
        this.display.setEditable(false);
    }
}
