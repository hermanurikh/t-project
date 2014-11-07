package ru.tsystems.tproject.entities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.tsystems.tproject.DAO.API.ContractDAO;
import ru.tsystems.tproject.configuration.DIConfiguration;
import ru.tsystems.tproject.services.API.ContractService;
import ru.tsystems.tproject.services.implementation.ContractServiceImplementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class HelloWorld {

    @Autowired
    private ContractService contractService;

    public static void main(String[] args) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        ContractService contractService = context.getBean(ContractService.class);
        List<Contract> contracts = contractService.getAllContracts();
        for (Contract x : contracts) System.out.println(x);
    }

    public void test() {

    }
}







