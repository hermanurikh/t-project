package ru.tsystems.tproject.utils;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 * A class to test the Parser class.
 */
public class ParserTest {
    private static final String NUMBER = "(981) 771-00-04";
    private static final String NUMBER_PARSED = "9817710004";

    @Test
    public void testDoParse() {
        assertEquals(NUMBER_PARSED, Parser.doParse(NUMBER));
    }
}
