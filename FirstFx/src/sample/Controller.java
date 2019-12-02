package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {
    public TextArea display;
    public TextField search;

    public void TakeAction(ActionEvent event) {
        display.setText(search.getText());
    }
}
