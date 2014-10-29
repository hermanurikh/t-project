package ru.tsystems.tproject.entities;

import ru.tsystems.tproject.services.API.ContractService;
import ru.tsystems.tproject.services.API.TariffService;
import ru.tsystems.tproject.services.API.UserService;
import ru.tsystems.tproject.services.implementation.ContractServiceImplementation;
import ru.tsystems.tproject.services.implementation.TariffServiceImplementation;
import ru.tsystems.tproject.services.implementation.UserServiceImplementation;
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







