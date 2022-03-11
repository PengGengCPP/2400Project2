
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.EmptyStackException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Stacks.LinkedStack;

public class LinkedStackTest {

    private LinkedStack<Integer> stack;

    @BeforeEach
    void init() {
        stack = new LinkedStack<>();

        stack.push(1);
        stack.push(2);
        stack.push(3);
    }

    @Test
    void testClear() {
        stack.clear();
        assertThrows(EmptyStackException.class, () -> stack.peek());
    }

    @Test
    void testIsEmpty() {
        assertEquals(false, stack.isEmpty());
        stack.clear();
        assertEquals(true, stack.isEmpty());
    }

    @Test
    void testPeek() {
        assertEquals(3, stack.peek());
        stack.pop();
        assertEquals(2, stack.peek());
        stack.pop();
        assertEquals(1, stack.peek());
    }

    @Test
    void testPop() {
        assertEquals(3, stack.pop());
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
    }

    @Test
    void testPush() {
        stack.push(1);
        assertEquals(1, stack.peek());
    }
}
