/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dependencyClass;

/**
 *
 * @author RAKIB
 */
public class RegisterStudent {
    
    String studentID;
    String courseCode;
    String faculty;
    String topic;
    String groupName;

    public RegisterStudent(String studentID, String courseCode, String faculty, String topic, String groupName) {
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
    
    
    
}
