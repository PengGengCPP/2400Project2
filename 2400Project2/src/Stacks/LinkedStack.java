package Stacks;

import java.util.EmptyStackException;

public class LinkedStack<T> implements StackInterface<T>
{
    private Node topNode; // references the first node in the chain

    public LinkedStack()
    {
        topNode = null;
    }

    public void push(T newEntry)
    {
        Node newNode = new Node(newEntry, topNode);
        topNode = newNode;
    }

    public T pop()
    {
        T top = peek();  // Might throw EmptyStackException
        topNode = topNode.getNextNode();
        return top;
    }

    public T peek()
    {   
        if (isEmpty())
            throw new EmptyStackException("Stack is empty!");
        else
            return topNode.getData();
    }

    public boolean isEmpty()
    {
        return topNode == null;
    }

    public void clear()
    {
        topNode = null;
    }

    private class Node
    {
        private T data;
        private Node next;

        public Node(T inputData, Node inputNextNode)
        {
            data = inputData;
            next = inputNextNode;
        }

        public T getData()
        {
            return data;
        }

        public void setData(T inputData) // never used
        {
            data = inputData;
        }

        public Node getNextNode()
        {
            return next;
        }

        public void setNextNode(Node inputNextNode) // never used
        {
            next = inputNextNode;
        }
    } // end Node
} // end LinkedStack