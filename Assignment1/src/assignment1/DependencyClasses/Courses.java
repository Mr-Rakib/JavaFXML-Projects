/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment1.DependencyClasses;

/**
 *
 * @author RAKIB
 */
public class Courses {

    String courseCode;
    String courseTitle;
    float courseCredit;

    public Courses(String courseCode, String courseTitle, float courseCredit) {
        this.courseCode = courseCode;
        this.courseTitle = courseTitle;
        this.courseCredit = courseCredit;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public float getCourseCredit() {
        return courseCredit;
    }

}
