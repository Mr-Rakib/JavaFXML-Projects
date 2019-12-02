/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj_eid_gift;

import dependencyClass.*;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import dependencyClass.RegisterStudent;
import dependencyClass.RequestedRegisteredStudent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author RAKIB
 */
public class Techers_UIController implements Initializable {

    String facultyName;
    String facultyID;

    @FXML
    private AnchorPane teachersUI;
    @FXML
    private Label teacherName;
    @FXML
    private Label teacherID;

    Connection connection;
    Statement statement;
    ResultSet resultSet;
    @FXML
    private TableView<RegisterStudent> registerStudentTable;
    @FXML
    private TableColumn<RegisterStudent, String> col_id;
    @FXML
    private TableColumn<RegisterStudent, String> col_course;
    @FXML
    private TableColumn<RegisterStudent, String> col_faculty;
    @FXML
    private TableColumn<RegisterStudent, String> col_topic;
    @FXML
    private TableColumn<RegisterStudent, String> col_group;

    private ObservableList<RegisterStudent> data;

    @FXML
    private TextField searchStudentid;
    @FXML
    private TableView<RequestedRegisteredStudent> requestedStudentList;
    @FXML
    private TableColumn<RequestedRegisteredStudent, String> col_req_id;
    @FXML
    private TableColumn<RequestedRegisteredStudent, String> col_req_course;
    @FXML
    private TableColumn<RequestedRegisteredStudent, String> col_req_topic;
    @FXML
    private TableColumn<RequestedRegisteredStudent, String> col_req_group;

    private ObservableList<RequestedRegisteredStudent> data1;
    @FXML
    private TextField requestedStudentSearch;
    @FXML
    private TextField req_studentName;
    @FXML
    private TextField req_studentID;
    @FXML
    private TextField req_studentGroup;
    @FXML
    private TextField req_studentTopic;
    @FXML
    private TextField req_studentCode;
    @FXML
    private TextField req_studentSupervisor;
    @FXML
    private Label acceptlabel;
    @FXML
    private Label accepterr;
    @FXML
    private TextField AcceptedStudentStatusField;
    @FXML
    private TableView<StudentsRegistration> AcceptedStudentStatusTable;
    @FXML
    private TableColumn<StudentsRegistration, String> col_acp_id;
    @FXML
    private TableColumn<StudentsRegistration, String> col_acp_code;
    @FXML
    private TableColumn<StudentsRegistration, String> col_acp_faculty;
    @FXML
    private TableColumn<StudentsRegistration, String> col_acp_topic;
    @FXML
    private TableColumn<StudentsRegistration, String> col_acp_group;
    @FXML
    private TableColumn<StudentsRegistration, String> col_acp_status;

    private ObservableList<StudentsRegistration> data2;

    @FXML
    private TableColumn<StudentsRegistration, String> col_acp_permission;
    @FXML
    private Label totalRegisterStudentLabel;
    @FXML
    private Label totalRequestedStudentLabel;
    @FXML
    private Button acceptButton;
    @FXML
    private Button rejecttButton;
    @FXML
    private Label noticeText;

    LocalDate date;
    @FXML
    private Label availableStudentLabel;

    /**
     * Initializes the controller class.
     */
    void PassData(String username, String employeeName) {

        DBConnection conn = new DBConnection();
        connection = conn.startConnect();

        facultyName = employeeName;
        teacherName.setText(facultyName);

        facultyID = username;
        teacherID.setText(facultyID);

        SetavailableStudentLabel();
        addAllRequestedStudentData();
        AddAllDataFromStudentStatus();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        DBConnection conn = new DBConnection();
        connection = conn.startConnect();

        refreshAllData();
        LocalDate currentDate = LocalDate.now();

        if ((getMainNoticeDate()).isAfter(currentDate)
                || Integer.parseInt(availableStudentLabel.getText()) <= 0) {
            SetMainNotice();
        } else {
            rejecttButton.setDisable(true);
            acceptButton.setDisable(true);
            noticeText.setText("No notice available");
        }

    }

    @FXML
    private void handleLogoutAction(ActionEvent event) throws IOException {

        AnchorPane pane = FXMLLoader.load(getClass().getResource("FXML/FXMLDocument.fxml"));
        teachersUI.getChildren().setAll(pane);
    }

    @FXML
    private void handelTypeStudentSearchAction(KeyEvent event) throws SQLException {

        if ((searchStudentid.getText()).trim().equals("")) {
            refreshAllData();
        } else {
            addAllData();
        }

    }

    @FXML
    private void handelSearchStudentAction(ActionEvent event) throws SQLException {
        if ((searchStudentid.getText()).trim().equals("")) {
            refreshAllData();
        } else {
            addAllData();
        }

    }

    ///Tab 1
    public void setCell() {
        col_id.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        col_course.setCellValueFactory(new PropertyValueFactory<>("courseCode"));
        col_faculty.setCellValueFactory(new PropertyValueFactory<>("faculty"));
        col_topic.setCellValueFactory(new PropertyValueFactory<>("topic"));
        col_group.setCellValueFactory(new PropertyValueFactory<>("groupName"));

    }

    public void addAllData() throws SQLException {

        data = FXCollections.observableArrayList();
        setCell();
        try {
            String query = "select * from student_registration where studentID like '" + (searchStudentid
                    .getText().trim()) + "%';";

            System.out.println(query);

            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {

                data.add(new RegisterStudent(resultSet.getString(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4),
                        resultSet.getString(5)));

            }
            registerStudentTable.setItems(data);
        } catch (SQLException ex) {
            Logger.getLogger(Techers_UIController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    ///Tab 2
    public void setCellRequested() {
        col_req_id.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        col_req_course.setCellValueFactory(new PropertyValueFactory<>("courseCode"));
        col_req_topic.setCellValueFactory(new PropertyValueFactory<>("topic"));
        col_req_group.setCellValueFactory(new PropertyValueFactory<>("groupName"));

    }

    public void addAllRequestedStudentData() {

        data1 = FXCollections.observableArrayList();
        setCellRequested();
        try {
            String query = "select * from student_registration where status = '' and faculty='" + facultyName + "';";
            String query1 = "select count(studentID) from student_registration where status = '' and faculty='" + facultyName + "';";

            System.out.println(query1);

            statement = connection.createStatement();
            resultSet = statement.executeQuery(query1);
            while (resultSet.next()) {

                totalRequestedStudentLabel.setText(resultSet.getString(1));

            }

            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {

                data1.add(new RequestedRegisteredStudent(resultSet.getString(1), resultSet.getString(2),
                        resultSet.getString(4), resultSet.getString(5)));

            }
            requestedStudentList.setItems(data1);
        } catch (SQLException ex) {
            Logger.getLogger(Techers_UIController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void refreshAllData() {

        data = FXCollections.observableArrayList();
        setCell();
        try {
            String query = "select * from student_registration;";
            String query1 = "select count(studentID) from student_registration;";

            System.out.println(query);

            statement = connection.createStatement();

            resultSet = statement.executeQuery(query1);

            while (resultSet.next()) {

                totalRegisterStudentLabel.setText(resultSet.getString(1));

            }

            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {

                data.add(new RegisterStudent(resultSet.getString(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4),
                        resultSet.getString(5)));

            }
            registerStudentTable.setItems(data);
        } catch (SQLException ex) {
            Logger.getLogger(Techers_UIController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void requestedStudentSearchAction(ActionEvent event) {

        if ((requestedStudentSearch.getText()).trim().equals("")) {
            addAllRequestedStudentData();
        } else {

            refreshAllrequestedData();
        }

        acceptlabel.setText(null);
        accepterr.setText(null);

    }

    @FXML
    private void requestedStudentSearchTypeAction(KeyEvent event) {
        if ((requestedStudentSearch.getText()).trim().equals("")) {
            addAllRequestedStudentData();
        } else {

            refreshAllrequestedData();
        }
    }

    private void refreshAllrequestedData() {

        data1 = FXCollections.observableArrayList();
        setCellRequested();
        try {
            String query = "select * from student_registration where status = '' and studentID like '" + requestedStudentSearch
                    .getText().trim() + "%' and faculty='" + facultyName + "';";

            System.out.println(query);

            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {

                data1.add(new RequestedRegisteredStudent(resultSet.getString(1), resultSet.getString(2),
                        resultSet.getString(4), resultSet.getString(5)));

            }
            requestedStudentList.setItems(data1);
        } catch (SQLException ex) {
            Logger.getLogger(Techers_UIController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void handelClickAction(MouseEvent event) {

        ObservableList<RegisterStudent> select;

        select = registerStudentTable.getSelectionModel().getSelectedItems();
        String id = select.get(0).getStudentID();
        searchStudentid.setText(id);

    }

    @FXML
    private void AcceptRequestAction(ActionEvent event) throws SQLException {

        String id = req_studentID.getText();
        String code = req_studentCode.getText();
        String group = req_studentGroup.getText();
        String suppervisor = req_studentSupervisor.getText();
        String topic = req_studentTopic.getText();
        String status = "Accepted";

        String query = "UPDATE student_registration SET status = '" + status + "' where studentID ='" + id + "';";

        System.out.println(query);

        statement = connection.createStatement();

        if (statement.executeUpdate(query) > 0) {
            accepterr.setText(null);
            acceptlabel.setText("Request Accepted ...!");
            System.out.print("Data selected");
        } else {
            acceptlabel.setText(null);
            accepterr.setText("Please Select Any Item first");
            System.out.print("No data selected");
        }

        clearAllReqField();
        addAllRequestedStudentData();
        AddAllDataFromStudentStatus();
        SetavailableStudentLabel();

    }

    @FXML
    private void RejectRequestAction(ActionEvent event) throws SQLException {

        acceptlabel.setText(null);

        String id = req_studentID.getText();
        String code = req_studentCode.getText();
        String group = req_studentGroup.getText();
        String suppervisor = "";
        String topic = req_studentTopic.getText();
        String status = "Rejected";

        String query = "UPDATE student_registration SET status = '" + status + "' where studentID ='" + id + "';";

        System.out.println(query);

        statement = connection.createStatement();

        if (statement.executeUpdate(query) > 0) {
            accepterr.setText("Request Rejected ...!");
            System.out.print("Data selected");
        } else {

            accepterr.setText("Please Select Any Item first");
            System.out.print("No data selected");
        }

        clearAllReqField();
        addAllRequestedStudentData();
        AddAllDataFromStudentStatus();

    }

    @FXML
    private void requestedStudentClickAction(MouseEvent event) {

        ObservableList<RequestedRegisteredStudent> select;

        select = requestedStudentList.getSelectionModel().getSelectedItems();
        String id = select.get(0).getStudentID();
        String name = select.get(0).getName(id);
        String code = select.get(0).getCourseCode();
        String topic = select.get(0).getTopic();
        String supervisor = facultyName;
        String group = select.get(0).getGroupName();

        System.out.println(id + name + code + topic + supervisor + group);

        req_studentName.setText(name);
        req_studentID.setText(id);
        req_studentCode.setText(code);
        req_studentGroup.setText(group);
        req_studentSupervisor.setText(supervisor);
        req_studentTopic.setText(topic);

    }

    private void clearAllReqField() {
        req_studentName.clear();
        req_studentID.clear();
        req_studentCode.clear();
        req_studentGroup.clear();
        req_studentSupervisor.clear();
        req_studentTopic.clear();

    }

    @FXML
    private void AcceptedStudentStatusFieldAction(ActionEvent event) {

        AddAllDataFromStudentStatus();
    }

    @FXML
    private void AcceptedStudentStatusFieldTypeAction(KeyEvent event) {

        if ((AcceptedStudentStatusField.getText()).trim().equals("")) {
            AddAllDataFromStudentStatus();
        } else {

            refreshAddAllDataFromStudentStatus();
        }

    }

    private void AddAllDataFromStudentStatus() {
        data2 = FXCollections.observableArrayList();
        setCellStatus();
        try {
            String query = "select * from student_registration where status = 'Accepted' and faculty='" + facultyName + "';";

            System.out.println(query);

            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {

                data2.add(
                        new StudentsRegistration(
                                resultSet.getString(1), resultSet.getString(2),
                                resultSet.getString(3), resultSet.getString(4), resultSet.getString(5),
                                resultSet.getString(6), resultSet.getString("permission")
                        )
                );

                System.out.print(resultSet.getString(1) + resultSet.getString(2)
                        + resultSet.getString(3) + resultSet.getString(4) + resultSet.getString(5)
                        + resultSet.getString(6) + resultSet.getString("permission"));

            }
            AcceptedStudentStatusTable.setItems(data2);
        } catch (SQLException ex) {
            Logger.getLogger(Techers_UIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setCellStatus() {
        col_acp_id.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        col_acp_code.setCellValueFactory(new PropertyValueFactory<>("courseCode"));
        col_acp_topic.setCellValueFactory(new PropertyValueFactory<>("topic"));
        col_acp_group.setCellValueFactory(new PropertyValueFactory<>("groupName"));
        col_acp_faculty.setCellValueFactory(new PropertyValueFactory<>("faculty"));
        col_acp_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        col_acp_permission.setCellValueFactory(new PropertyValueFactory<>("permission"));

    }

    private void refreshAddAllDataFromStudentStatus() {
        data2 = FXCollections.observableArrayList();
        setCellStatus();
        try {
            String query = "select * from student_registration where studentID like '" + AcceptedStudentStatusField.getText() + "%' and status = 'Accepted' and faculty='" + facultyName + "';";

            System.out.println(query);

            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {

                data2.add(
                        new StudentsRegistration(
                                resultSet.getString(1), resultSet.getString(2),
                                resultSet.getString(3), resultSet.getString(4), resultSet.getString(5),
                                resultSet.getString(6), resultSet.getString("permission")
                        )
                );

                System.out.print(resultSet.getString(1) + resultSet.getString(2)
                        + resultSet.getString(3) + resultSet.getString(4) + resultSet.getString(5)
                        + resultSet.getString(6) + resultSet.getString("permission"));

            }
            AcceptedStudentStatusTable.setItems(data2);
        } catch (SQLException ex) {
            Logger.getLogger(Techers_UIController.class.getName()).log(Level.SEVERE, null, ex);
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
                date = LocalDate.parse(resultSet.getString(2), DateTimeFormatter.ISO_DATE);
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

    private void SetavailableStudentLabel() {
        try {

            int totalreg = 0;
            int max = 0;
            int available = 0;

            String query1 = "SELECT studentLimit FROM student_limit where employeeID='" + facultyID + "';";

            statement = connection.createStatement();
            resultSet = statement.executeQuery(query1);

            while (resultSet.next()) {

                max = Integer.parseInt(resultSet.getString(1));

            }
            
            String query = "select count(studentID) from student_registration where status = 'Accepted' and faculty='" + facultyName + "';";
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {

                totalreg = Integer.parseInt(resultSet.getString(1));

            }
            
            available = max-totalreg;
            
            availableStudentLabel.setText(available+"");
            
        } catch (SQLException ex) {
            Logger.getLogger(Techers_UIController.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (Integer.parseInt(availableStudentLabel.getText()) <= 0) {
            acceptButton.setDisable(true);
        }

    }

}
