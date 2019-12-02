/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dependencyClass;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author RAKIB
 */
public class StudentsRegistration {
    String studentID;
    String courseCode;
    String faculty;
    String topic;
    String groupName;
    String status;
    String permission;

    public StudentsRegistration(String studentID, String courseCode, String faculty, String topic, String groupName, String status, String permission) {
        this.studentID = studentID;
        this.courseCode = courseCode;
        this.faculty = faculty;
        this.topic = topic;
        this.groupName = groupName;
        this.status = status;
        this.permission = permission;
    }

    public StudentsRegistration(String studentID, String courseCode, String faculty, String topic, String groupName, String status) {
        this.studentID = studentID;
        this.courseCode = courseCode;
        this.faculty = faculty;
        this.topic = topic;
        this.groupName = groupName;
        this.status = status;
    }
    
    

    public StudentsRegistration(String studentID, String courseCode, String faculty, String topic, String groupName) {
        this.studentID = studentID;
        this.courseCode = courseCode;
        this.faculty = faculty;
        this.topic = topic;
        this.groupName = groupName;
    }  

   
    

    

    public String getStudentID() {
        return studentID;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getTopic() {
        return topic;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getStatus() {
        return status;
    }

    public String getPermission() {
        return permission;
    }
    
    
    public String getName(String  id )
    {
        String Name ="";
        DBConnection conn = new DBConnection();
        Connection connection = conn.startConnect();
        
        
        String query = "select studentName from student where studentID ='"+id+"';";

        try {
           Statement statement = connection.createStatement();
            ResultSet  resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
              Name =  resultSet.getString("studentName");
            }
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(RequestedRegisteredStudent.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        return Name;
    
    
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
    
    
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    

    public void AllData()
    {
        System.out.println(studentID+""+courseCode+""+faculty+""+topic+""+groupName);
    }
    
    
}
