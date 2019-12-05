/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.pkg1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author RAKIB
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private TextField inputText;
    @FXML
    private TextField outputText;
    @FXML
    private TextField numberofVowel;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleCopyButton(ActionEvent event) {
        
        String text = inputText.getText();
        int vowelCount=0;
        text = text.trim();
        text = text.toUpperCase();
        
        outputText.setText(text);
        
        for(int x =0;x<text.length();x++)
        {
            if(text.charAt(x)=='A'||text.charAt(x)=='E'|| text.charAt(x)=='I'||text.charAt(x)=='O'||text.charAt(x)=='U' ){
                vowelCount++;
            }
                
        }
        
        numberofVowel.setText(""+vowelCount);
        
        inputText.clear();
    }
    
}
