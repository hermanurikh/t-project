package ru.tsystems.tproject.services.API;

import ru.tsystems.tproject.entities.Role;
import ru.tsystems.tproject.entities.User;
import ru.tsystems.tproject.exceptions.CustomDAOException;
import ru.tsystems.tproject.services.implementation.RoleServiceImplementation;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by german on 19.10.14.
 */
public class HelloWorld {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd.mm.yyyy");
    public static void main(String[] args) throws Exception{
        Date date = dateFormat.parse("14.04.1992");
        System.out.println(date);



    }
}
