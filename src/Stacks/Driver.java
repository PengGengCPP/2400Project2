package Stacks;
public class Driver {
    public static void main(String[] args) throws Exception {
        //operations with LinkedStack and ResizableArrayStack are the same. 

        //declaring a new stack
        LinkedStack<Integer> stack = new LinkedStack<>();

        //adding items to the stack
        stack.push(1);
        stack.push(2);
        stack.push(3);

        //removing the first item from the stack and printing it
        //will throw EmptyStackException() if the stack is empty.
        int temp = stack.pop();
        System.out.println(temp);

        //checking out what the top item is on the stack and printing it without modifying anything in the stack
        //will throw EmptyStackException() if the stack is empty.
        temp = stack.peek();
        System.out.println(temp);

        //clearing stack and then checking if it is empty
        stack.clear();
        System.out.println(stack.isEmpty());
        
    }
}
