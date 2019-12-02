package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
       Double  x;
        Scanner sc = new Scanner(System.in);
        add a = new add();
        sub s = new sub();
        mul m = new mul();
        div d = new div();

        System.out.println("For exit press x");
        while (true) {
            x = sc.nextDouble();
            String n = sc.next();
            switch (n) {

                case "+":
                    a.addition(x);
                    break;

                case "-":
                    s.substraction(x);
                    break;

                case "*":
                    m.Multiplication(x);
                    break;

                case "/":
                    d.Division(x);
                    break;

                case "x":
                    System.exit(0);
            }
        }

    }
}
