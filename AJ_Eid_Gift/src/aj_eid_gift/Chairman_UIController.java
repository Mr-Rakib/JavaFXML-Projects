/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj_eid_gift;

import dependencyClass.DBConnection;
import dependencyClass.FacultyInfo;

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author RAKIB
 */
public class Chairman_UIController implements Initializable {

    @FXML
    private AnchorPane chairmanUI;
    @FXML
    private Label chairmanName;
    @FXML
    private Label chairmanID;
    @FXML
    private TableView<StudentsRegistration> acceptedStudentTable;
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

    Connection connection;
    Statement statement;
    ResultSet resultSet;
    @FXML
    private TextField searchAcceptedStudentField;
    @FXML
    private TextField studentName;
    @FXML
    private TextField studentID;
    @FXML
    private TextField groupName;
    @FXML
    private TextField supervisor;
    @FXML
    private TextField topic;
    @FXML
    private TextField courseCode;
    @FXML
    private Label permissionlabel;
    @FXML
    private Label permissionlabelerr;
    @FXML
    private TableView<StudentsRegistration> RegisterStudentTable;
    @FXML
    private TableColumn<StudentsRegistration, String> col_reg_id;
    @FXML
    private TableColumn<StudentsRegistration, String> col_reg_code;
    @FXML
    private TableColumn<StudentsRegistration, String> col_reg_topic;
    @FXML
    private TableColumn<StudentsRegistration, String> col_reg_group;
    @FXML
    private TableColumn<StudentsRegistration, String> col_reg_status;
    @FXML
    private TableColumn<StudentsRegistration, String> col_reg_faculty;
    @FXML
    private TextField req_studentGroup;
    @FXML
    private ComboBox<?> req_studentCourse;
    @FXML
    private TextField req_studentTopic;
    @FXML
    private ComboBox<?> req_studentFaculty;
    @FXML
    private TextField req_studentName;
    @FXML
    private TextField req_studentID;
    @FXML
    private Label permissionLabel;
    @FXML
    private Label permissionLabelerr;
    @FXML
    private TextField serchRegisteredStudentField;
    @FXML
    private Label totalStudent;
    @FXML
    private Label totalregisteredStudent;
    @FXML
    private Label totalFaculty;
    @FXML
    private TableView<StudentsRegistration> permissionTable;
    @FXML
    private TableColumn<StudentsRegistration, String> col_per_id;
    @FXML
    private TableColumn<StudentsRegistration, String> col_per_code;
    @FXML
    private TableColumn<StudentsRegistration, String> col_per_faculty;
    @FXML
    private TableColumn<StudentsRegistration, String> col_per_topic;
    @FXML
    private TableColumn<StudentsRegistration, String> col_per_group;
    @FXML
    private TableColumn<StudentsRegistration, String> col_per_status;
    @FXML
    private TableColumn<StudentsRegistration, String> col_per_permission;
    @FXML
    private TextField permissionTableSearchField;
    @FXML
    private TableView<FacultyInfo> facultyListTable;
    @FXML
    private TableColumn<FacultyInfo, String> col_facultyID;
    @FXML
    private TableColumn<FacultyInfo, String> col_facultyName;
    @FXML
    private TableColumn<FacultyInfo, String> col_facultyDepartment;
    @FXML
    private TableColumn<FacultyInfo, String> col_facultyLimit;
    private ObservableList<FacultyInfo> dataf;
    @FXML
    private TextField searchFaculty;
    @FXML
    private TextField facultyName;
    @FXML
    private TextField facultyID;
    @FXML
    private TextField facultyStudentLimit;
    @FXML
    private Label facultyLabel;
    @FXML
    private Label facultyLabelerr;
    @FXML
    private DatePicker registrationDatePiker;
    @FXML
    private Label mainNoticText;

    LocalDate date;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        DBConnection conn = new DBConnection();
        connection = conn.startConnect();

        SetMainNotice();

        ///set the combobox
        AddCourseCombobox();
        AddFacultyCombobox();

        AddAllDataFromStudentStatus();
        AddAllDataintoRegisterStudentTable();

        AddAllFacultyInformation();

        LocalDate currentDate = LocalDate.now();

        if ((getMainNoticeDate()).isAfter(currentDate)) {
            SetMainNotice();
        } else {

            mainNoticText.setText("No notice available");
        }

        SetAllDashboardLabel();

        SetAllDashboardTabel();

        // TODO
    }

    @FXML
    private void handleLogoutAction(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("FXML/FXMLDocument.fxml"));
        chairmanUI.getChildren().setAll(pane);
    }

    void PassData(String username, String employeeName) {

        chairmanID.setText(username);
        chairmanName.setText(employeeName);

    }

    ////
    public void setCellStatus() {
        col_acp_id.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        col_acp_code.setCellValueFactory(new PropertyValueFactory<>("courseCode"));
        col_acp_topic.setCellValueFactory(new PropertyValueFactory<>("topic"));
        col_acp_group.setCellValueFactory(new PropertyValueFactory<>("groupName"));
        col_acp_faculty.setCellValueFactory(new PropertyValueFactory<>("faculty"));
        col_acp_status.setCellValueFactory(new PropertyValueFactory<>("status"));

    }

    private void AddAllDataFromStudentStatus() {
        data2 = FXCollections.observableArrayList();
        setCellStatus();
        String query = "select * from student_registration where status = 'Accepted' and permission ='NO';";

        showAllDatainTable(query);

    }

    @FXML
    private void searchAcceptedStudentFieldTypeAction(KeyEvent event) {

        data2 = FXCollections.observableArrayList();
        setCellStatus();

        if (!searchAcceptedStudentField.getText().trim().equals(null)) {

            String query = "select * from student_registration where studentID like '" + searchAcceptedStudentField.getText().trim()
                    + "%' and  status = 'Accepted' and permission ='NO';";

            showAllDatainTable(query);
        } else {
            String query = "select * from student_registration where status = 'Accepted' and permission ='NO';";

            showAllDatainTable(query);

        }

    }

///Click The Accept Button 
    @FXML
    private void AcceptPermissionAction(ActionEvent event) {

        String id = studentID.getText();

        String code = courseCode.getText();

        String topicname = topic.getText();
        String faculty = supervisor.getText();
        String group = groupName.getText();
        String permission = "YES";

        String query = "Update student_registration set permission='" + permission + "' where studentID = '"
                + id + "';";

        System.out.println(query);

        try {
            statement = connection.createStatement();
            if (statement.executeUpdate(query) > 0) {
                permissionlabel.setText("Permission Granted");
                permissionlabelerr.setText("");
            } else {
                permissionlabel.setText("");
                permissionlabelerr.setText("Please Select A Student First");
            }
        } catch (SQLException ex) {
            permissionlabel.setText("");
            permissionlabelerr.setText("Please Select A Student First");
        }

        AddAllDataFromStudentStatus();
        SetAllDashboardTabel();
        clearAll();

    }

    @FXML
    private void clearAction(ActionEvent event) {
        clearAll();
        permissionlabel.setText("");
        permissionlabelerr.setText("");

    }

    @FXML
    private void selectAcceptedStudentSelectAction(MouseEvent event) {

        ObservableList<StudentsRegistration> select;

        select = acceptedStudentTable.getSelectionModel().getSelectedItems();
        String id = select.get(0).getStudentID();
        String name = select.get(0).getName(id);
        String code = select.get(0).getCourseCode();
        String topicname = select.get(0).getTopic();
        String faculty = select.get(0).getFaculty();
        String group = select.get(0).getGroupName();

        System.out.println(id + name + code + topic + supervisor + group);

        studentName.setText(name);
        studentID.setText(id);
        courseCode.setText(code);
        groupName.setText(group);
        supervisor.setText(faculty);
        topic.setText(topicname);

    }

    private void clearAll() {
        studentName.clear();
        studentID.clear();
        courseCode.clear();
        groupName.clear();
        supervisor.clear();
        topic.clear();

    }

    private void showAllDatainTable(String query) {

        try {
            System.out.println(query);

            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {

                data2.add(
                        new StudentsRegistration(
                                resultSet.getString(1), resultSet.getString(2),
                                resultSet.getString(3), resultSet.getString(4), resultSet.getString(5),
                                resultSet.getString(6)
                        )
                );

                System.out.print(resultSet.getString(1) + resultSet.getString(2)
                        + resultSet.getString(3) + resultSet.getString(4) + resultSet.getString(5)
                        + resultSet.getString(6) + resultSet.getString("permission"));

            }
            acceptedStudentTable.setItems(data2);
        } catch (SQLException ex) {
            Logger.getLogger(Chairman_UIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void RegisterStudentTableClickAction(MouseEvent event) {

        ObservableList<StudentsRegistration> select;

        select = RegisterStudentTable.getSelectionModel().getSelectedItems();
        String id = select.get(0).getStudentID().trim();
        String name = select.get(0).getName(id).trim();
        String code = select.get(0).getCourseCode().trim();
        String topicname = select.get(0).getTopic().trim();
        String faculty = select.get(0).getFaculty().trim();
        String group = select.get(0).getGroupName().trim();

        System.out.println(id + name + code + topic + supervisor + group);

        req_studentName.setText(name);
        req_studentID.setText(id);
        req_studentGroup.setText(group);
        req_studentTopic.setText(topicname);

    }

    private void AddAllDataintoRegisterStudentTable() {
        data2 = FXCollections.observableArrayList();
        setCellRegisterStudent();
        String query = "select * from student_registration where status = '' or status = 'Rejected' and permission ='NO';";

        AddAllRegisterStudentData(query);

    }

    private void setCellRegisterStudent() {
        col_reg_id.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        col_reg_code.setCellValueFactory(new PropertyValueFactory<>("courseCode"));
        col_reg_topic.setCellValueFactory(new PropertyValueFactory<>("topic"));
        col_reg_group.setCellValueFactory(new PropertyValueFactory<>("groupName"));
        col_reg_faculty.setCellValueFactory(new PropertyValueFactory<>("faculty"));
        col_reg_status.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    private void AddAllRegisterStudentData(String query) {
        try {

            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {

                data2.add(
                        new StudentsRegistration(
                                resultSet.getString(1), resultSet.getString(2),
                                resultSet.getString(3), resultSet.getString(4), resultSet.getString(5),
                                resultSet.getString(6)
                        )
                );

                System.out.print(resultSet.getString(1) + resultSet.getString(2)
                        + resultSet.getString(3) + resultSet.getString(4) + resultSet.getString(5)
                        + resultSet.getString(6) + resultSet.getString("permission"));

            }
            RegisterStudentTable.setItems(data2);
        } catch (SQLException ex) {
            Logger.getLogger(Chairman_UIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handelApplyandConfirmAction(ActionEvent event) {

        try {
            String id = req_studentID.getText();
            String name = req_studentName.getText();
            String topicname = req_studentTopic.getText();
            String group = req_studentGroup.getText();

            String code = req_studentCourse.getValue().toString();

            String faculty = req_studentFaculty.getValue().toString();

            String status = "Accepted".trim();
            String permission = "YES".trim();

            String query = "UPDATE student_registration SET status = '" + status + "',permission='" + permission
                    + "' where studentID ='" + id + "';";

            System.out.println(query);

            statement = connection.createStatement();
            if (statement.executeUpdate(query) > 0) {
                permissionLabelerr.setText(null);
                permissionLabel.setText("Permission Granted");
                System.out.print("Data selected");
            } else {
                permissionLabel.setText(null);
                permissionLabelerr.setText("Please Select an Item First");
                System.out.print("No data selected");
            }
        } catch (SQLException ex) {

            permissionLabelerr.setText("Please Select an Item First");
        }

        AddAllDataintoRegisterStudentTable();
        resetAllField();
        SetAllDashboardTabel();

    }

    @FXML
    private void handelResetAction(ActionEvent event) {

        resetAllField();
        permissionLabel.setText(null);
        permissionLabelerr.setText(null);
    }

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
            permissionLabelerr.setText("Some Error Happen In Faculty Combo Box..");

        }

        req_studentFaculty.setItems(faculty);

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
            permissionLabelerr.setText("Some Error Happen In Course Combo Box..");
        }

        req_studentCourse.setItems(faculty);
    }

    private void resetAllField() {
        req_studentID.clear();
        req_studentName.clear();
        req_studentCourse.getSelectionModel().clearSelection();
        req_studentFaculty.getSelectionModel().clearSelection();
        req_studentTopic.clear();
        req_studentGroup.clear();
    }

    @FXML
    private void serchRegisteredStudentTypeFieldAction(KeyEvent event) {

        data2 = FXCollections.observableArrayList();
        setCellRegisterStudent();
        if (serchRegisteredStudentField.getText().trim().equals("")) {

            String query = "select * from student_registration where status = '' or status = 'Rejected' and permission ='NO';";

            System.out.println(query);
            AddAllRegisterStudentData(query);

        } else {
            String query = "select * from student_registration where studentID like '" + serchRegisteredStudentField.getText().trim()
                    + "%' and status = '' or status = 'Rejected' and permission ='NO';";

            System.out.println(query);

            AddAllRegisterStudentData(query);
        }
    }

    private void SetAllDashboardLabel() {
        try {
            String query = "SELECT count(studentID) from student";
            String query1 = "SELECT count(employeeName) from employee where designation ='teacher';";
            String query2 = "SELECT COUNT(studentID) from student_registration";

            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                totalStudent.setText(resultSet.getString(1));
            }
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query1);

            while (resultSet.next()) {

                totalFaculty.setText(resultSet.getString(1));
            }
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query2);

            while (resultSet.next()) {
                totalregisteredStudent.setText(resultSet.getString(1));
            }

        } catch (SQLException ex) {
            Logger.getLogger(Chairman_UIController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void SetAllDashboardTabel() {
        data2 = FXCollections.observableArrayList();
        setCellPermission();
        String query = "select * from student_registration where permission ='YES';";

        showAllPemitedDatainTable(query);
    }

    private void setCellPermission() {
        col_per_id.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        col_per_code.setCellValueFactory(new PropertyValueFactory<>("courseCode"));
        col_per_topic.setCellValueFactory(new PropertyValueFactory<>("topic"));
        col_per_group.setCellValueFactory(new PropertyValueFactory<>("groupName"));
        col_per_faculty.setCellValueFactory(new PropertyValueFactory<>("faculty"));
        col_per_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        col_per_permission.setCellValueFactory(new PropertyValueFactory<>("permission"));
    }

    private void showAllPemitedDatainTable(String query) {
        try {

            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {

                data2.add(
                        new StudentsRegistration(
                                resultSet.getString(1), resultSet.getString(2),
                                resultSet.getString(3), resultSet.getString(4), resultSet.getString(5),
                                resultSet.getString(6), resultSet.getString(7)
                        )
                );

                System.out.print(resultSet.getString(1) + resultSet.getString(2)
                        + resultSet.getString(3) + resultSet.getString(4) + resultSet.getString(5)
                        + resultSet.getString(6) + resultSet.getString("permission"));

            }
            permissionTable.setItems(data2);
        } catch (SQLException ex) {
            Logger.getLogger(Chairman_UIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void permissionTableSearchTypeAction(KeyEvent event) {

        if (permissionTableSearchField.getText().trim().equals("")) {
            SetAllDashboardTabel();
        } else {

            data2 = FXCollections.observableArrayList();
            setCellPermission();
            String query = "select * from student_registration where studentID like '"
                    + permissionTableSearchField.getText().trim() + "%' and permission ='YES';";

            showAllPemitedDatainTable(query);

        }

    }

    private void AddAllFacultyInformation() {
        dataf = FXCollections.observableArrayList();

        setAllFacultyColumn();

        String query = "SELECT employee.employeeID,employee.employeeName,employee.department, student_limit.studentLimit "
                + "from employee,student_limit where employee.employeeID= student_limit.employeeID ;";

        showAllFacultyDatainTable(query);

    }

    private void showAllFacultyDatainTable(String query) {

        try {

            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {

                dataf.add(
                        new FacultyInfo(
                                resultSet.getString(1), resultSet.getString(2),
                                resultSet.getString(3), resultSet.getString(4)
                        )
                );

                System.out.print(resultSet.getString(1) + resultSet.getString(2)
                        + resultSet.getString(3) + resultSet.getString(4));

            }
            facultyListTable.setItems(dataf);
        } catch (SQLException ex) {
            Logger.getLogger(Chairman_UIController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void setAllFacultyColumn() {
        col_facultyID.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_facultyName.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_facultyDepartment.setCellValueFactory(new PropertyValueFactory<>("department"));
        col_facultyLimit.setCellValueFactory(new PropertyValueFactory<>("limit"));
    }

    @FXML
    private void searchFacultyTypeAcion(KeyEvent event) {

        if (searchFaculty.getText().trim().equals("")) {
            AddAllFacultyInformation();
        } else {
            dataf = FXCollections.observableArrayList();

            setAllFacultyColumn();

            String query = "SELECT employee.employeeID,employee.employeeName,employee.department, student_limit.studentLimit "
                    + "from employee,student_limit where employee.employeeName like '%" + searchFaculty.getText().trim() + "%' and employee.employeeID= student_limit.employeeID ;";

            showAllFacultyDatainTable(query);

        }
    }

    @FXML
    private void selectFacultyAction(MouseEvent event) {

        ObservableList<FacultyInfo> select;

        select = facultyListTable.getSelectionModel().getSelectedItems();
        String id = select.get(0).getId();
        String name = select.get(0).getName();
        String limit = select.get(0).getLimit();

        System.out.println(id + name + limit);

        facultyID.setText(id);
        facultyName.setText(name);
        facultyStudentLimit.setText(limit);
    }

    @FXML
    private void handelSaveFcultyAction(ActionEvent event) {
        try {
            String id = facultyID.getText();
            String name = facultyName.getText();
            int limit = Integer.parseInt(facultyStudentLimit.getText());

            String query = "update student_limit set studentLimit= " + limit + " where employeeID = '" + id + "';";
            System.out.println(query);

            statement = connection.createStatement();

            if (statement.executeUpdate(query) > 0) {
                facultyLabelerr.setText(null);
                facultyLabel.setText("Successfully increse The limit");
            } else {
                facultyLabel.setText(null);
                facultyLabelerr.setText("Please Select A Faculty First");
            }

        } catch (RuntimeException ex) {
            facultyLabel.setText(null);
            facultyLabelerr.setText("Please Select A Faculty First");
        } catch (SQLException ex) {

            facultyLabelerr.setText("Please Select A Faculty First");
            Logger.getLogger(Chairman_UIController.class.getName()).log(Level.SEVERE, null, ex);
        }

        ClearAllFacultyField();
        AddAllFacultyInformation();

    }

    @FXML
    private void ResetAllFacultyInfoAction(ActionEvent event) {

        ClearAllFacultyField();
        facultyLabel.setText(null);
        facultyLabelerr.setText(null);
        searchFaculty.clear();

    }

    private void ClearAllFacultyField() {

        facultyID.clear();
        facultyName.clear();
        facultyStudentLimit.clear();
    }

    @FXML
    private void handelRegistrationLastDateAction(ActionEvent event) {

        try {
            System.out.println(registrationDatePiker.getValue());
            String notice = "Registration";

            String query = "update notice set lastDate='" + registrationDatePiker.getValue()
                    + "' where noticeName = '" + notice + "';";
            System.out.println(query);

            statement = connection.createStatement();

            if (statement.executeUpdate(query) > 0) {
                facultyLabelerr.setText(null);
                facultyLabel.setText("Date Has Changed Succesfully");
            } else {
                facultyLabel.setText(null);
                facultyLabelerr.setText("Please Select The Date First");
            }
        } catch (SQLException ex) {

            facultyLabel.setText(null);
            facultyLabelerr.setText("Please Select The Date First");
            Logger.getLogger(Chairman_UIController.class.getName()).log(Level.SEVERE, null, ex);
        }

        SetMainNotice();

    }

    private void SetMainNotice() {

        try {
            String notice = "Registration";

            String query = "select * from notice where noticeName = '" + notice + "';";
            System.out.println(query);

            statement = connection.createStatement();

            resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                mainNoticText.setText(resultSet.getString(1).toString() + " Will Close after " + resultSet.getString(2).toString());
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

}
