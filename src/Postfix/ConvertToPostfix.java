package Postfix;

import java.util.HashSet;
import java.util.InputMismatchException;

import Stacks.LinkedStack;

public class ConvertToPostfix {

    private HashSet<Character> allowedCharacters;

    public ConvertToPostfix() {
        allowedCharacters = new HashSet<>();
        "01234567890abcdefghijklmnopqrstuvwxyz()+-/*^".chars().forEach(c -> allowedCharacters.add((char) c));
    }

    private static int getPrecedence(char operator) {
        int ret = 0;
        switch (operator) {
            case '^':
                ret = 3;
                break;

            case '*':
            case '/':
                ret = 2;
                break;

            case '+':
            case '-':
                ret = 1;
                break;

            default:
                break;
        }
        return ret;
    }

    public String infixToPostfix(String exp) {
        // ensure that expression is made only out of valid characters
        for (int i = 0; i < exp.length(); i++) {
            if (!allowedCharacters.contains(exp.charAt(i))) {
                throw new IllegalArgumentException("unsupported character");
            }
        }


        String postfixExp = ""; // string to build the expression on
        LinkedStack<Character> stack = new LinkedStack<>(); // stack to store operators

        char currentChar;
        // for each character in the expression
        // (ignoring characters that aren't alphabetical, operands, or numbers)
        for (int i = 0; i < exp.length(); i++) {
            currentChar = exp.charAt(i);

            if (Character.isAlphabetic(currentChar) || Character.isDigit(currentChar)) {
                // operands in postfix expressions appear in the same order as infix expressons.
                postfixExp += currentChar;
                continue;
            } else if (currentChar == '(') {
                // push opening parentheses to the stack to mark a mini expression.
                stack.push(currentChar);
                continue;
            } else if (currentChar == ')') {
                // upon encountering the closing parentheses, collapse the mini expression into
                // its RPN form.
                // make sure that the parentheses has a pair though.
                // sorta like a mini expression that needs to be evaluated when the end is met.
                while (true) {
                    if (!stack.isEmpty()) {
                        char fromStack = stack.pop();
                        if (fromStack == '(') {
                            break;
                        }
                        postfixExp += fromStack;
                    } else {
                        throw new InputMismatchException("Invalid expression! Not properly balanced.");
                    }
                }
                continue;
            } else if (currentChar == '^') {
                // push exponentials to the stack. These will be added to the postfix expression
                // when collapsing with order of operation precedence or at the end.
                // implicitly, the precedence of any ^ compared to ^ is that the second one encountered is higher. So it must always be pushed to the stack.
                stack.push(currentChar);
            } else if (currentChar == '+' || currentChar == '-' || currentChar == '*' || currentChar == '/') {
                // if precedence of current operator is less than the precedence of the operator at the top of the stack,
                // then the current operator has to wait for the
                // stack operator's calculation. thus, append the operator from the stack.
                while (!stack.isEmpty() && getPrecedence(currentChar) <= getPrecedence(stack.peek())) {
                    char fromStack = stack.pop();
                    postfixExp += fromStack;
                }
                // finally, push operator to stack.
                stack.push(currentChar);
            }
        }
        // empty out the stack
        while (!stack.isEmpty()) {
            char temp = stack.pop();
            // if there are parentheses left, then it was not properly balanced.
            if (temp == '(' || temp == ')') {
                throw new InputMismatchException("Invalid expression! Not properly balanced.");
            }
            postfixExp += temp;
        }

        return postfixExp;
    }
}
