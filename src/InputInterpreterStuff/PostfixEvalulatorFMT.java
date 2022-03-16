package InputInterpreterStuff;

import Stacks.ResizableArrayStack;

public class PostfixEvalulatorFMT {
    
    /**
     * Evaluates a postfix expression using +, -, *, /, or ^. Numbers are
     * represented by the lowercase letters a to e.
     * 
     * @param postfix expression
     * @return evaluation of the expression using the variables.
     */
    public int evaluate(String postfix) {
        String[] input = postfix.split(" ");

        // reference the previous two items in the stack for the operands
        ResizableArrayStack<Integer> mathStack = new ResizableArrayStack<>();

        for (int i = 0; i < input.length; i++) {
            String currentString = input[i];

            if (currentString.matches("\\d+")) {
                mathStack.push(Integer.parseInt(currentString));
            } else if (currentString.matches("[\\/*^+-]")) {
                int operandTwo = mathStack.pop();
                int operandOne = mathStack.pop();
                int result = evaluateOperator(operandOne, operandTwo, currentString);
                mathStack.push(result);
            }
        }
        return mathStack.peek();
    }
    
    private int evaluateOperator(int operandOne, int operandTwo, String operator) {
        int ret = 0;
        switch (operator) {
            case "+":
                ret = operandOne + operandTwo;
                break;
            case "-":
                ret = operandOne - operandTwo;
                break;
            case "*":
                ret = operandOne * operandTwo;
                break;
            case "/":
                ret = operandOne / operandTwo;
                break;
            case "^":
                ret = (int) Math.pow(operandOne, operandTwo);
                break;

            default:
                break;
        }
        return ret;
    }
}
