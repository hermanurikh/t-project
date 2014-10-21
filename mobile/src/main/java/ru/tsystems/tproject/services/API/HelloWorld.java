package ru.tsystems.tproject.services.API;

import ru.tsystems.tproject.entities.Role;
import ru.tsystems.tproject.entities.User;
import ru.tsystems.tproject.exceptions.CustomDAOException;
import ru.tsystems.tproject.services.implementation.RoleServiceImplementation;

/**
 * Created by german on 19.10.14.
 */
public class HelloWorld {
    public static void main(String[] args) throws CustomDAOException{
        RoleService roleService = new RoleServiceImplementation();
        Role role = roleService.getRoleById(1);
        roleService.deleteRole(role);
        System.out.println(roleService.getAllRoles());
        ;


    }
}
