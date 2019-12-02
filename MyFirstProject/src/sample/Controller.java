package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.*;
import java.util.Scanner;

import static java.lang.System.exit;

public class Controller {


    public TextArea display;
    private  Stage stage ;


    public void fileOpen() throws IOException {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text File","*.txt")
        );

        File file = fileChooser.showOpenDialog(this.stage);

        FileInputStream fileInputStream = new FileInputStream(file);
        while ((fileInputStream.read())!= -1) {
            System.out.print((char) fileInputStream.read());
        }



    }


    public void init(Stage primaryStage) {
          this.stage=primaryStage;
    }



///Other Parts




    public void CloseAll(ActionEvent actionEvent) {

        exit(0);
    }

    public void Delete(ActionEvent actionEvent) {
        display.setText("");
    }

    public void Production(ActionEvent actionEvent) {
        display.setText("Introduction\n" +
                "\n" +
                "In Bangladesh lots of vacancies have in planning department. This training will give you chance to fight\n" +
                " in interview board to make you qualified. Also make you confident from your colleagues. Here you will find\n" +
                " some formula & answer what you can use your practical job life. Also can gather some practical knowledge\n" +
                " sharing which can help for future.\n" +
                "\n" +
                "Benefits of the training:\n" +
                "After getting this training you -\n" +
                "1. Can calculate CM within 30 seconds.\n" +
                "2. Will confident in planning jobs.\n" +
                "3. Can face viva easily.\n" +
                "4. Can do all calculation perfectly & within short time.\n" +
                "5. Can understand your plan make profit or loss. ");
    }

    public void Inventory(ActionEvent actionEvent) {
    }

    public void Accounting(ActionEvent actionEvent) {
    }

    public void Finamce(ActionEvent actionEvent) {
    }

    public void Findamce(ActionEvent actionEvent) {
    }

    public void HumanReesource(ActionEvent actionEvent) {

    }

    public void LogIn(ActionEvent actionEvent) {
    }


    public void SaveAll(ActionEvent actionEvent) {
    }
}
