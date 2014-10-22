package ru.tsystems.tproject.services.API;

import org.apache.log4j.Logger;
import ru.tsystems.tproject.entities.Role;
import ru.tsystems.tproject.entities.User;
import ru.tsystems.tproject.exceptions.CustomDAOException;
import ru.tsystems.tproject.services.implementation.RoleServiceImplementation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by german on 19.10.14.
 */
public class HelloWorld {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd.mm.yyyy");
    private static Logger logger = Logger.getLogger(HelloWorld.class);
    public static void main(String[] args) throws Exception{
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        Collections.addAll(list1, 10, 9, 8, 7, 5, 4, 6, 3, 2, 1);
        Collections.addAll(list2, 9, 4, 6, 3);
        list1.removeAll(list2);
        for (int x : list1) System.out.println(x);



    }
}
