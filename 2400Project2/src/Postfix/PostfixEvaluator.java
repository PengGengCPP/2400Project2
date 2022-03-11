package Postfix;

import Stacks.ResizableArrayStack;

/**
 * Object that can evaluate RPN expressions with 5 variables. Variables are assigned a value when object is instantiated or though a method. Supports variables a to e.
 */

public class PostfixEvaluator {
    
        private int a = 0;
        private int b = 0;
        private int c = 0;
        private int d = 0;
        private int e = 0;
    
        public PostfixEvaluator(int a, int b, int c, int d, int e) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
            this.e = e;
        }
    
        /**
         * method to set the values of variables a to e
         * @param a
         * @param b
         * @param c
         * @param d
         * @param e
         */
        public void setVariableValues(int a, int b, int c, int d, int e) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
            this.e = e;
        }
    
        /**
         * method to get the values of the variables a to e.
         * @param var the character variable that you want to get the value of
         * @return the value of the variable
         */
        public int getVariableValue(char var) {
            int ret = 0;
            switch (var) {
                case 'a':
                    ret = this.a;
                    break;
                case 'b':
                    ret = this.b;
                    break;
                case 'c':
                    ret = this.c;
                    break;
                case 'd':
                    ret = this.d;
                    break;
                case 'e':
                    ret = this.e;
                    break;
            
                default:
                    break;
            }
            return ret;
        }
    
        /**
         * evaluates an expression (in terms of infix notations) using operandTwo as the first part, the operand as the middle part, and operandOne as the last part.
         * @param operandOne
         * @param operandTwo
         * @param operand
         * @return
         */
        private int evaluateOperator(int operandOne, int operandTwo, char operand) {
            int ret = 0;
            switch (operand) {
                case '+':
                    ret = operandTwo + operandOne;
                    break;
                case '-':
                    ret = operandTwo - operandOne;
                    break;
                case '*':
                    ret = operandTwo * operandOne;
                    break;
                case '/':
                    ret = operandTwo / operandOne;
                    break;
                case '^':
                    ret = (int) Math.pow(operandTwo, operandOne);
                    break;
            
                default:
                    break;
            }
            return ret;
        }
    
        /**
         * Evaluates a postfix expression using +, -, *, /, or ^. Numbers are represented by the lowercase letters a to e.
         * @param postfix expression
         * @return evaluation of the expression using the variables.
         */
        public int evaluate(String postfix) {
            //reference the previous two items in the stack for the operands
            ResizableArrayStack<Integer> mathStack = new ResizableArrayStack<>();
        
            for (int i = 0; i < postfix.length(); i++) {
                char currentChar = postfix.charAt(i);
    
                if (Character.isAlphabetic(currentChar)) {
                    mathStack.push(getVariableValue(currentChar));
                } else if (currentChar == '+' || currentChar == '-' || currentChar == '*' || currentChar == '/') {
                    int operandOne = mathStack.pop();
                    int operandTwo = mathStack.pop();
                    int result = evaluateOperator(operandOne, operandTwo, currentChar);
                    mathStack.push(result);
                }
            }
            return mathStack.peek();
        }
    
        public static void main(String[] args) {
            PostfixEvaluator eval = new PostfixEvaluator(2, 3, 4, 5, 6);
            System.out.println(eval.evaluate("ae+bd-/"));
        }
}
