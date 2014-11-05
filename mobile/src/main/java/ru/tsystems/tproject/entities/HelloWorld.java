package ru.tsystems.tproject.entities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.tsystems.tproject.DAO.API.ContractDAO;
import ru.tsystems.tproject.services.API.ContractService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by german on 29.10.14.
 */
public class HelloWorld {


    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring4.xml");
        ContractService contractService = context.getBean(ContractService.class);
        List<Contract> contracts = contractService.getAllContracts();
        for (Contract x : contracts) System.out.println(x);

    }
}







