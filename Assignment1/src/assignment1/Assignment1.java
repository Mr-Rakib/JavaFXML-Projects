package assignment1;

import assignment1.DependencyClasses.Student;

/**
 *
 * @author RAKIB
 *
 *
 */
public class Assignment1 {

    Assignment1() {

        CallStudent();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        new Assignment1();
    }

    private void CallStudent() {
        Student array[] = {
            new Student("2016000000009", "Md Rakibul Hasan", "Dhaka"),
            new Student("2016000000009", "Md Rakibul Hasan", "Dhaka"),
            new Student("2016000000009", "Md Rakibul Hasan", "Dhaka"),
            new Student("2016000000009", "Md Rakibul Hasan", "Dhaka"),
            new Student("2016000000009", "Md Rakibul Hasan", "Dhaka")
        };

        for (Student student : array) {

            System.out.println(student);

        }
        Student student = new Student();
        System.out.println(array.length);
    }

}
