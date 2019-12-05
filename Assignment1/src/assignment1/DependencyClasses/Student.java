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
public class Student {
    String id;
    String name;
    String address;
    int totalStudent=0;

    public Student(String id, String name,  String address) {
        totalStudent =totalStudent++;
        this.id = id;
        this.name = name;
        this.address = address;
        
    }

    public Student() {
    }

    

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public  String getAddress() {
        return address;
    }

    public void setAddress( String address) {
        this.address = address;
    }

    public int getTotalStudent() {
        return totalStudent;
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", name=" + name + ", address=" + address + '}';
    }
    

    
    
    
}
