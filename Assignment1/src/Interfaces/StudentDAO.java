/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import assignment1.DependencyClasses.Courses;
import assignment1.DependencyClasses.Student;
import java.util.List;

/**
 *
 * @author RAKIB
 */
public interface StudentDAO {
    Student createStudent();
    List <Courses>  retriveCourses();
    List <?> allReisterCourses();
    
     
    
}
