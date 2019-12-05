/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment1.DependencyClasses;

import java.util.Date;

/**
 *
 * @author RAKIB
 */
public class StudentRegistration {
    String studentID;
    String facultyID;
    String courseCode;
    int courseCredit;
    Date time;
    int courseSection;
    int studentLimit;
    String roomNumber;
    String Day;

    public StudentRegistration() {
    }

    public StudentRegistration(String studentID, String facultyID, String courseCode, int courseCredit, Date time, int courseSection, int studentLimit, String roomNumber, String Day) {
        this.studentID = studentID;
        this.facultyID = facultyID;
        this.courseCode = courseCode;
        this.courseCredit = courseCredit;
        this.time = time;
        this.courseSection = courseSection;
        this.studentLimit = studentLimit;
        this.roomNumber = roomNumber;
        this.Day = Day;
    }
    
    
    
}
