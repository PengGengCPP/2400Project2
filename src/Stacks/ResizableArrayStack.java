package Stacks;

import java.util.Arrays;
import java.util.EmptyStackException;

public class ResizableArrayStack<T> implements StackInterface<T> {

    private T[] stack;
    private int topIndex;

    public ResizableArrayStack()
    {
        this(64); // the default capacity if none is given
    }
    
    public ResizableArrayStack(int inputInitialCapacity)
    {
        topIndex = -1;
        T[] stack = (T[])new Object[inputInitialCapacity];
    }

    private void ensureCapacity() // double the size if the array becomes full
    {
        if(topIndex >= stack.length-1)
        {
            stack = Arrays.copyOf(stack, stack.length*2);
        }
    }

    @Override
    public void push(T inputEntry)
    {
        ensureCapacity();
        stack[topIndex+1] = inputEntry;
        topIndex++;
    }

    @Override
    public T pop()
    {
        T topEntry = peek();
        stack[topIndex] = null;
        topIndex--;
        return topEntry;
    }

    @Override
    public T peek()
    {
        if(isEmpty())
            throw new EmptyStackException();
        else
            return stack[topIndex];
    }

    @Override
    public boolean isEmpty()
    {
        return topIndex < 0;
    }

    @Override
    public void clear()
    {
        while(topIndex>-1)
        {
            stack[topIndex] = null;
            topIndex--;
        }
    }
}
