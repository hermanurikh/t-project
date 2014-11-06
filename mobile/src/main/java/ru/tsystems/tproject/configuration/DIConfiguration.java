package ru.tsystems.tproject.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import ru.tsystems.tproject.DAO.API.*;
import ru.tsystems.tproject.DAO.implementation.*;
import org.springframework.context.annotation.Configuration;

/**
 * Created by german on 05.11.14.
 */
@Configuration
@ComponentScan(value={"ru.tsystems.tproject.services.implementation"})
public class DIConfiguration {
    @Bean
    public ContractDAO getContractDAO() { return new ContractDAOImplementation(); }
    @Bean
    public OptionDAO getOptionDAO() { return new OptionDAOImplementation(); }
    @Bean
    public RoleDAO getRoleDAO() { return new RoleDAOImplementation(); }
    @Bean
    public TariffDAO getTariffDAO() { return new TariffDAOImplementation(); }
    //@Bean
    //public UserDAO getUserDAO() { return new UserDAOImplementation(); }
}
