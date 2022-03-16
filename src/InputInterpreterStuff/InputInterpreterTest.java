package InputInterpreterStuff;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class InputInterpreterTest {
    InputInterpreter inp = new InputInterpreter();
    
    @Test
    void testCheckBalance() {

    }

    @Test
    void testEnsureNoSequentialOperators() {
        String t1 = "1+22++3-4";
        String t2 = "1*2*3+4/5";
        String t3 = "-*3*4*5";
        String t4 = "3*4*1*4/";

        assertThrows(IllegalArgumentException.class, () -> inp.interpretInput(t1));
        assertEquals(true, Arrays.equals(inp.interpretInput(t2), new Object[]{"1" , "*", "2", "*", "3", "+", "4", "/", "5"}));
        assertThrows(IllegalArgumentException.class, () -> inp.interpretInput(t3));
        assertThrows(IllegalArgumentException.class, () -> inp.interpretInput(t4));
    }

    @Test
    void testInterpretInput() {

    }

    @Test
    void testCleanWhitespace() {
        
    }
}
