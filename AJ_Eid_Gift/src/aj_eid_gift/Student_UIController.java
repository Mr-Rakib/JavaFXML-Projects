/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj_eid_gift;

import dependencyClass.DBConnection;
import dependencyClass.StudentsRegistration;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author RAKIB
 */
public class Student_UIController implements Initializable {

    @FXML
    private AnchorPane studentUI;
    @FXML
    private ComboBox<?> courseCombobox;
    @FXML
    private ComboBox<?> instructorCombobox;
    @FXML
    private Label localtime;
    @FXML
    private Label localdate;
    @FXML
    private TextField groupname;
    @FXML
    private TextField researchtopic;
    @FXML
    private Label registrationalert;
    @FXML
    private Label studentname;
    @FXML
    private Label studentid;
    @FXML
    private Label registrationerr;
    @FXML
    private Button applyButton;

    Connection connection;
    Statement statement;
    ResultSet resultSet;
    @FXML
    private Label totalCourseLabel;
    @FXML
    private Label noticeText;

    LocalDate date;
    @FXML
    private TextField groupName;
    @FXML
    private TextField instructor;
    @FXML
    private TextField topic;
    @FXML
    private TextArea othersMember;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ///Connect With Database
        DBConnection conn = new DBConnection();
        connection = conn.startConnect();

        ClearAll();

        ///set The Vale of  Faculty into Combobox
        AddFacultyCombobox();

        ///set The Vale of  Courses into Combobox
        AddCourseCombobox();

        LocalDate currentDate = LocalDate.now();
        localdate.setText(currentDate.toString());

        LocalTime currentTime = LocalTime.now();
        localtime.setText(currentTime.toString());

        if ((getMainNoticeDate()).isAfter(currentDate)) {
            SetMainNotice();
        } else {
            applyButton.setDisable(true);
            noticeText.setText("No notice available");
        }

        ///Will Set The Chairman of SEU (SM Sir)
    }

    @FXML
    private void handleLogoutAction(ActionEvent event) throws IOException {

        ///page navigation
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/FXMLDocument.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handelApplyAction(ActionEvent event) {

        try {

            String[] s = (courseCombobox.getValue().toString()).split("-");

            String studentID = (studentid.getText().trim());
            String courseCode = s[0];
            String selectfaculty = instructorCombobox.getValue().toString().trim();
            String topic = researchtopic.getText().trim();
            String groupName = groupname.getText().trim().toUpperCase();

            if (courseCombobox.getSelectionModel().isEmpty() || instructorCombobox.getSelectionModel().isEmpty()
                    || topic.equals("") || groupName.equals("")) {

                registrationerr.setText("Please fill the form properly");
            } else {

                StudentsRegistration regStudent = new StudentsRegistration(studentID, courseCode,
                        selectfaculty, topic, groupName);

                String query = "insert into student_registration values('" + studentID + "','" + courseCode
                        + "','" + selectfaculty + "','" + topic + "','" + groupName + "','','NO');";

                System.out.println(query);

                try {
                    statement = connection.createStatement();
                    statement.executeUpdate(query);
                    registrationalert.setText("Successfully registered ...!");

                } catch (SQLException ex) {
                    registrationerr.setText("You Hava Already registed..");

                }
                regStudent.AllData();
                addAllLabels();
            }
        } catch (RuntimeException ex) {
            registrationerr.setText("Please Select The Field First");
        }

    }

    public void ClearAll() {
        researchtopic.clear();
        groupname.clear();
        registrationalert.setText(null);
        instructorCombobox.setValue(null);
        courseCombobox.setValue(null);
        registrationerr.setText(null);

    }

    @FXML
    private void handelResetAction(ActionEvent event) {

        ClearAll();
    }

    public void PassData(String text, String text1) {
        this.studentid.setText(text);
        this.studentname.setText(text1);
        addAllLabels();
        setDashBoardPermissionFileld();
    }

    ///Add All Faculty from Database
    private void AddFacultyCombobox() {
        ObservableList faculty = FXCollections.observableArrayList();

        String query = "select employeeName from employee;";

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                faculty.add(resultSet.getString("employeeName"));
            }

        } catch (SQLException ex) {
            registrationerr.setText("Some Error Happen In Faculty Combo Box..");

        }

        instructorCombobox.setItems(faculty);

    }

    ///Add all Courses With name from Database
    private void AddCourseCombobox() {

        ObservableList faculty = FXCollections.observableArrayList();

        String query = "select * from courses;";

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                faculty.add(resultSet.getString("courseCode") + " - " + resultSet.getString("courseName"));
            }

        } catch (SQLException ex) {
            registrationerr.setText("Some Error Happen In Course Combo Box..");
        }

        courseCombobox.setItems(faculty);
    }

    public void addAllLabels() {
        try {
            String query = "SELECT count(courseCode) FROM student_registration where studentID='" + studentid.getText() + "';";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                totalCourseLabel.setText(resultSet.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student_UIController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void SetMainNotice() {

        try {
            String notice = "Registration";

            String query = "select * from notice where noticeName = '" + notice + "';";
            System.out.println(query);

            statement = connection.createStatement();

            resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                noticeText.setText(resultSet.getString(1).toString() + " Will Close after " + resultSet.getString(2).toString());
            }

        } catch (SQLException ex) {
            Logger.getLogger(Chairman_UIController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private LocalDate getMainNoticeDate() {

        try {
            String notice = "Registration";

            String query = "select * from notice where noticeName = '" + notice + "';";
            System.out.println(query);

            statement = connection.createStatement();

            resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                date = LocalDate.parse(resultSet.getString(2), DateTimeFormatter.ISO_DATE);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Chairman_UIController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return date;
    }

    private void setDashBoardPermissionFileld() {
        try {

            String maniTopic = "";

            String query0 = "select groupname, faculty,topic from student_registration where studentID='"
                    + studentid.getText().trim() + "' and permission='YES';";

            statement = connection.createStatement();
            resultSet = statement.executeQuery(query0);

            while (resultSet.next()) {

                maniTopic = (resultSet.getString(3));
            }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////
            if (!maniTopic.equals("")) {
                String query = "select groupname, faculty,topic from student_registration where studentID='"
                        + studentid.getText().trim() + "' and permission='YES' and topic ='" + maniTopic + "';";

                System.out.println(query);

                resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    groupName.setText(resultSet.getString(1));
                    instructor.setText(resultSet.getString(2));
                    topic.setText(resultSet.getString(3));
                }

                String query1 = "select studentID from student_registration where groupName='"
                        + groupName.getText().trim() + "' and permission='YES';";

                resultSet = statement.executeQuery(query1);
                System.out.println(query1);

                while (resultSet.next()) {
                    othersMember.appendText(resultSet.getString(1) + "\n");
                }

            } else {
                groupName.clear();
                instructor.clear();
                topic.clear();
                othersMember.clear();

            }
        } catch (SQLException ex) {
            Logger.getLogger(Student_UIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
