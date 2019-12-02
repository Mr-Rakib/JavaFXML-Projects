/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package randomnumbergenerator;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author hraki
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private TextField countryCode;
    @FXML
    private TextField startNumber;
    @FXML
    private TextField endNumber;
    private TextField intersector;
    @FXML
    private TextField range;
    @FXML
    private TextArea display;
    @FXML
    private Label total;
    
    
    File file;
    @FXML
    private TextField fileName;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void generateNumber(ActionEvent event) {
        
        display.setText("");
        
       
        int Range = Integer.parseInt(range.getText());
        int start = Integer.parseInt(startNumber.getText());
        int end   = Integer.parseInt(endNumber.getText());
        
        int Number,countTotal=0;
        
        
        Random random = new Random();
        for(int a=1 ;a<=Range;a++)
        {
           Number =(start) + random.nextInt(end-start);
           
           String set = Number+","+"\n";
           display.appendText(countryCode.getText()+set);
           countTotal++;
           
        }
            total.setText(countTotal+"");
    }

    @FXML
    private void saveIntoFile(ActionEvent event) throws IOException {
        
        String Display = display.getText();
              
        Path path = Paths.get(fileName.getText());
        System.out.println(Display);
       
        Files.write(path,Display.getBytes(),StandardOpenOption.CREATE_NEW);
    }
    
}
