package com.company;

import java.util.Scanner;

public class div {


    public void  Division( Double x) {

        Scanner sc = new Scanner(System.in);
        Double y, z;


        y = sc.nextDouble();
        z = x / y;
        System.out.println(x+" / "+y +" = "+ z);
    }
}
