package ru.tsystems.tproject.DAO.implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class Marina {
    public static void main(String[] args) throws Exception{

        System.out.println("Please enter a number");
        boolean inputCorrect = false;
        System.out.println("The number must be less than " + Integer.MAX_VALUE);
        System.out.println("The number must be more than " + Integer.MIN_VALUE);
        System.out.println("Please enter \"ok\" when done");
        String a = null;
        List<Integer> list = new ArrayList<>();
        while (!inputCorrect) {
            try {
                a = new BufferedReader(new InputStreamReader(System.in)).readLine();
                if (a.equals("ok")) inputCorrect = true;
                else {
                    int b = Integer.parseInt(a);
                    list.add(b);
                }
            } catch (NumberFormatException ex) {
                System.out.println("You didn't enter a number :( Please don't cheat on me!");
            }
        }
        int[] array = new int[list.size()];
        for (int i = 0; i < list.size(); i++) array[i] = list.get(i);
        Arrays.sort(array);
        for (int x : array) System.out.print(x + " ");
        System.out.println("");





        System.out.println("Have a nice day!");

    }
}
