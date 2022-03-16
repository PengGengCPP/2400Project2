package InputInterpreterStuff;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import Stacks.LinkedStack;

public class ArrayPostfixConverter {
    private static InputInterpreter interpreter = new InputInterpreter();

    private static int getPrecedence(String operator) {
        int ret = 0;
        switch (operator) {
            case "^":
                ret = 3;
                break;

            case "*":
            case "/":
                ret = 2;
                break;

            case "+":
            case "-":
                ret = 1;
                break;

            default:
                break;
        }
        return ret;
    }

    public String infixToPostfix(String exp) {
        String[] input = interpreter.interpretInput(exp);


        List<String> postfixExp = new ArrayList<>();
        LinkedStack<String> stack = new LinkedStack<>(); // stack to store operators

        String currentString;
        // for each character in the expression
        // (ignoring characters that aren't alphabetical, operands, or numbers)
        for (int i = 0; i < input.length; i++) {
            currentString = input[i];

            if (currentString.matches("[a-zA-Z]+") || currentString.matches("-?\\d+(\\.\\d+)?")) {
                // operands in postfix expressions appear in the same order as infix expressons.
                postfixExp.add(currentString);
                continue;
            } else if (currentString.equals("(")) {
                // push opening parentheses to the stack to mark a mini expression.
                stack.push(currentString);
                continue;
            } else if (currentString.equals(")")) {
                // upon encountering the closing parentheses, collapse the mini expression into
                // its RPN form.
                // make sure that the parentheses has a pair though.
                // sorta like a mini expression that needs to be evaluated when the end is met.
                while (true) {
                    if (!stack.isEmpty()) {
                        String fromStack = stack.pop();
                        if (fromStack.equals("(")) {
                            break;
                        }
                        postfixExp.add(fromStack);
                    } else {
                        throw new InputMismatchException("Invalid expression! Not properly balanced.");
                    }
                }
                continue;
            } else if (currentString.equals("^")) {
                // push exponentials to the stack. These will be added to the postfix expression
                // when collapsing with order of operation precedence or at the end.
                // implicitly, the precedence of any ^ compared to ^ is that the second one encountered is higher. So it must always be pushed to the stack.
                stack.push(currentString);
            } else if (currentString.equals("+") || currentString.equals("-") || currentString.equals("*") || currentString.equals("/")) {
                // if precedence of current operator is less than the precedence of the operator at the top of the stack,
                // then the current operator has to wait for the
                // stack operator's calculation. thus, append the operator from the stack.
                while (!stack.isEmpty() && getPrecedence(currentString) <= getPrecedence(stack.peek())) {
                    String fromStack = stack.pop();
                    postfixExp.add(fromStack);
                }
                // finally, push operator to stack.
                stack.push(currentString);
            }
        }
        // empty out the stack
        while (!stack.isEmpty()) {
            String temp = stack.pop();
            // if there are parentheses left, then it was not properly balanced.
            if (temp.equals("(") || temp.equals(")")) {
                throw new InputMismatchException("Invalid expression! Not properly balanced.");
            }
            postfixExp.add(temp);
        }

        //rebuilding expression into string
        return String.join(" ", postfixExp);
    }

    //main for testing
    public static void main(String[] args) {
        ArrayPostfixConverter translator = new ArrayPostfixConverter();
        String input = "100+243-44*300^34*(42-100)";
        System.out.println(translator.infixToPostfix(input));

    }
}
