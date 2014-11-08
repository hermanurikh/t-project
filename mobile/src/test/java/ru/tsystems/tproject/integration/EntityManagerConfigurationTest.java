package ru.tsystems.tproject.integration;

import static org.junit.Assert.*;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * A test of entity manager injection.
 */
@ContextConfiguration(locations = "/spring.xml")
public class EntityManagerConfigurationTest extends AbstractJUnit4SpringContextTests {

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void testConfiguration() {
        assertNotNull(entityManager);
    }
}
