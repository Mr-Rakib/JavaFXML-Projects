package com.company;

import java.util.Scanner;

public class add {

    public void addition(Double x)
    {
        Scanner sc = new Scanner(System.in);
        Double  y,z;


        y =sc.nextDouble();
        z = x+y;
        System.out.println(x+" + "+y +" = "+ z);


    }
}
