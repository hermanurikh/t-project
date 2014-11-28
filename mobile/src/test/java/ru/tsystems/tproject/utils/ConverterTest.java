package ru.tsystems.tproject.utils;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * A class to test the Converter class.
 */
public class ConverterTest {
    private static final Converter CONVERTER = new Converter();
    private static final String PASSWORD = "password";
    private static final String PASSWORD_ENCODED = "3ad53f64e2412e40125a3f83199d5358";

    @Test
    public void testGetMD5() throws Exception {
        assertEquals(PASSWORD_ENCODED, Converter.getMD5(PASSWORD));
        assertNotEquals(PASSWORD, Converter.getMD5(PASSWORD));
    }
    @Test
    public void testIsPasswordValid() throws Exception {
        assertTrue(CONVERTER.isPasswordValid(PASSWORD_ENCODED, PASSWORD, null));
        assertFalse(CONVERTER.isPasswordValid(PASSWORD, PASSWORD, null));
    }
    @Test
    public void testEncodePassword() throws Exception {
        assertTrue(CONVERTER.encodePassword(PASSWORD, null).equals(PASSWORD_ENCODED));
        assertFalse(CONVERTER.encodePassword(PASSWORD, null).equals(PASSWORD));
    }

}
