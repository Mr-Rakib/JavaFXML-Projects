package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    @FXML
    private TableView<Student> table;

    @FXML
    private TableColumn<Student, Integer> id;

    @FXML
    private TableColumn<Student,String> name;

    @FXML
    private TableColumn<Student,String> address;

    Stage stage;

    public void tableview_new()throws Exception{


        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        address.setCellValueFactory(new PropertyValueFactory<>("adress"));




        FileChooser fileChooser =new FileChooser();

        File file = fileChooser.showOpenDialog(stage);
        Path path = Paths.get(file.toURI());
        //Files.write(path, value.getBytes(), StandardOpenOption.APPEND);


        //write to the display

        List<String> lines = Files.readAllLines(path);


        List<Student> studentList = new ArrayList<>();

        for (String line : lines)
        {
            String[] studentRecord = line.split("[:\t]");
            // System.out.println(studentRecord);
            studentList.add(new Student(Integer.parseInt(studentRecord[1]),studentRecord[3],studentRecord[5]));

        }


        ObservableList<Student> observableList = FXCollections.observableArrayList();
        observableList.addAll(studentList);
        table.setItems(observableList);


    }

    public void SEEALL(ActionEvent actionEvent)throws Exception {
        tableview_new();
    }
}
