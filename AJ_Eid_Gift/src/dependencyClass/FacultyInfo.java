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
public class FacultyInfo {

    String id;
    String name;

    String department;
    String limit;

    public FacultyInfo(String id, String name, String department, String limit) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.limit = limit;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

   
}
