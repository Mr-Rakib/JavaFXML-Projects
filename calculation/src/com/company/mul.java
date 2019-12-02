package com.company;

import java.util.Scanner;

public class mul {


    public void Multiplication( Double x) {

        Scanner sc = new Scanner(System.in);
        Double  y, z;


        y = sc.nextDouble();
        z = x * y;
        System.out.println(x+" * "+y +" = "+ z);
    }
}
