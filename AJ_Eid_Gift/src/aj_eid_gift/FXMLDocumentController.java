/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aj_eid_gift;

import dependencyClass.DBConnection;
import java.net.URL;
import java.sql.*;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author RAKIB
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private AnchorPane loginUI;
    @FXML
    private TextField loginUserName;
    @FXML
    private PasswordField loginPassword;
    @FXML
    private Label loginerr;
    Connection connection;
    Statement statement;
    ResultSet resultSet;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        DBConnection conn = new DBConnection();
        connection = conn.startConnect();

        // TODO
    }

    @FXML
    private void handleLoginAction(ActionEvent event) throws SQLException {

        String userID = "";
        String userPassword = "";
        String userType = "";

        String username = loginUserName.getText().trim();
        String password = loginPassword.getText().trim();
        

        String query = "select * from user_login where username ='" + username + "';";

        System.out.println(query);

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {

                userID = resultSet.getString("username");
                userPassword = resultSet.getString("password");
                userType = resultSet.getString("designation");
            }

            System.out.println(userID + userPassword + userType);

            if (username.equals(userID) && password.equals(userPassword) && !username.equals(null)
                    && userType.toLowerCase().equals("teacher")) {
                
                String employeeName = "";

                String query1 = "select * from employee where employeeID ='" + username + "';";
                

                statement = connection.createStatement();
                resultSet = statement.executeQuery(query1);

                while (resultSet.next()) {

                    employeeName = resultSet.getString("employeeName") ;
                }


                /*  AnchorPane pane = FXMLLoader.load(getClass().getResource("FXML/techers_UI.fxml"));
                loginUI.getChildren().setAll(pane);*/
                FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/techers_UI.fxml"));
                Parent parent = loader.load();
                Scene scene = new Scene(parent);

                Techers_UIController teacher = loader.getController();

                teacher.PassData(username, employeeName);

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.hide();
                stage.setScene(scene);
                stage.show();

            } else if (username.equals(userID) && password.equals(userPassword) && !username.equals(null)
                    && userType.toLowerCase().equals("chairman")) {
                
                 String employeeName = "";

                String query1 = "select * from employee where employeeID ='" + username + "';";
                

                statement = connection.createStatement();
                resultSet = statement.executeQuery(query1);

                while (resultSet.next()) {

                    employeeName = resultSet.getString("employeeName") ;
                }


                /*  AnchorPane pane = FXMLLoader.load(getClass().getResource("FXML/techers_UI.fxml"));
                loginUI.getChildren().setAll(pane);*/
                FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/Chairman_UI.fxml"));
                Parent parent = loader.load();
                Scene scene = new Scene(parent);

                Chairman_UIController chairman = loader.getController();

                chairman.PassData(username, employeeName);

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.hide();
                stage.setScene(scene);
                stage.show();

               
               
            } else if (username.equals(userID) && password.equals(userPassword) && !username.equals(null)
                    && userType.toLowerCase().equals("student")) {

                String studentName = "";

                {

                    String query1 = "select * from student where studentID ='" + username + "';";
                    {
                        statement = connection.createStatement();
                        resultSet = statement.executeQuery(query1);

                        while (resultSet.next()) {
                            studentName = resultSet.getString("studentName");
                        }
                    }

                }

                FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/student_UI.fxml"));
                Parent parent = loader.load();
                Scene scene = new Scene(parent);

                Student_UIController student = loader.getController();
                student.PassData(username, studentName);

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.hide();
                stage.setScene(scene);
                stage.show();

            } else {
                loginerr.setText("Please insert valid Username and Password");
            }

        } catch (Exception ex) {

            System.err.print(ex);

        }

    }

}
