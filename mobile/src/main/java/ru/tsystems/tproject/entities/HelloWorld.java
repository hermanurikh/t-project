package ru.tsystems.tproject.entities;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by german on 29.10.14.
 */
public class HelloWorld {


    public static void main(String[] args) throws Exception {
        BufferedReader rr = new BufferedReader(new InputStreamReader(System.in));
        String n1 = rr.readLine();
        String n2 = rr.readLine();

        if (n1.equals(n2)) {
            System.out.println("Имена идентичны");
        } else if (n1.length() == n2.length()) {
            System.out.println("Длины имён равны");
        } else {
            System.out.println("Нет совпадений");
        }
    }
}







