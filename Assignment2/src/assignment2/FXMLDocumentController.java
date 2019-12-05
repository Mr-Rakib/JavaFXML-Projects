
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import static java.lang.Character.isDigit;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author RAKIB
 */
public class FXMLDocumentController implements Initializable {

    private Label label;
    @FXML
    private TextField filenameTextField;
    @FXML
    private TextArea display;
    @FXML
    private TextField lineText;
    @FXML
    private TextField wordtext;
    @FXML
    private TextField characterText;
    @FXML
    private TextField digitText;
    @FXML
    private TextField vowelText;

    int wordCount = 0;
    int lineCount = 0;
    int chaCount = 0;
    int digitCount = 0;
    int vowelCount = 0;

    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
    }

    @FXML
    private void handelOpen(ActionEvent event) {

        FileOpen();

    }

    private int countLines() {
        int count = 0;
        String line = display.getText();
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '\n') {
                count++;
            }
        }

        return count;

    }

    private int countChar() {
        int count = 0;

        String line = display.getText();
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == ' ' || line.charAt(i) == '\n') {
                count = count;
            } else {
                count++;
            }
        }
        return count;
    }

    private int countWord() {
        int count = 0;
        String line = display.getText();
        line = line.trim();

        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == ' ' || line.charAt(i) == ',' || line.charAt(i) == '.' || line.charAt(i) == '\n') {
                count++;
            }

        }
        return count;
    }

    private int countVowel() {

        int count = 0;
        String line = display.getText();
        line = line.trim();
        line = line.toUpperCase();

        for (int i = 0; i < line.length(); i++) {

            if (line.charAt(i) == 'A' || line.charAt(i) == 'E' || line.charAt(i) == 'I'
                    || line.charAt(i) == 'O' || line.charAt(i) == 'U') {
                count++;
            }

        }
        return count;
    }

    private int countDigit() {
        int count = 0;
        String line = display.getText();
        line = line.trim();

        for (int i = 0; i < line.length(); i++) {

            if (isDigit(line.charAt(i))) {
                count++;
            }

        }

        return count;
    }

    @FXML
    private void handleFileOpenAction(ActionEvent event) {
         FileOpen();
    }

    @FXML
    private void handleFileOpenAction2(KeyEvent event) {

        FileOpen();
    }

    public void FileOpen() {
        display.clear();

        try {
            String filename = filenameTextField.getText();
            RandomAccessFile raf = new RandomAccessFile(filename, "r");

            String line;
            while (true) {
                line = raf.readLine();
                if (line == null) {
                    break;
                } else {
                    display.appendText(line + "\n");
                }

            }

            lineText.setText(countLines() + "");

            characterText.setText(countChar() + "");

            wordtext.setText(countWord() + "");
            System.out.println(countWord());

            vowelText.setText(countVowel() + "");
            digitText.setText(countDigit() + "");

        } catch (Exception ex) {
            System.err.print(ex);
        }
    }

}
