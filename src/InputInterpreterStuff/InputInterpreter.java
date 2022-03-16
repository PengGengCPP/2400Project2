package InputInterpreterStuff;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class InputInterpreter {
    
    public InputInterpreter() {

    }

    public String[] interpretInput(String input) {
        if (input == null || input.equals("")) {
            return new String[0];
        }
        
        //remove whitespace from input
        String cleanInput = removeWhitespace(input);
        
        //ensure non-alphabetical, numerical, and contains only allowed operators
        //TODO: lmao

        //brace checking
        if(!checkBalance(cleanInput)) {
            throw new IllegalArgumentException("Braces are not balanced.");
        }

        //checking no seqeuential operators
        if (!ensureNoSequentialOperators(cleanInput)) {
            throw new IllegalArgumentException("Sequential characters are illegal.");
        }

        //part 1: get the numbers
        List<String> nums = new ArrayList<>();
        nums.addAll(Arrays.asList(cleanInput.split("[\\(\\)^+*/-]")));
        //remove empty items
        Iterator<String> temp = nums.iterator();
        while (temp.hasNext()) {
            String curr = temp.next();
            if (curr == "") {
                temp.remove();
            }
        }

        //part 2: get the operators
        List<String> operators = new ArrayList<>();
        cleanInput.chars().filter(c -> c == '+' || c == '-' || c == '*' || c == '/' || c == '^' || c == '(' || c == ')').forEach(o -> operators.add(Character.toString((char) o)));

        //part 3: get the order of where to pull from to construct return array: 1 indicates from nums, 2 from operators
        Deque<Integer> order = new LinkedList<>();
        for (int i = 0; i < cleanInput.length(); i++) {
            char currentChar = cleanInput.charAt(i);

            if (order.isEmpty()) {
                order.addFirst(1);
                continue;
            }

            if (currentChar == '+' || currentChar == '-' || currentChar == '*' || currentChar == '/' || currentChar == '^' || currentChar == '(' || currentChar == ')') {
                order.addFirst(2);
            } else if(order.peekFirst() == 2) {
                order.addFirst(1);
            }
        }

        //part 4: reassemble Strings into string array with respect to order.
        List<String> ret = new ArrayList<>();
        Iterator<String> numIterator = nums.iterator();
        Iterator<String> opeIterator = operators.iterator();
        while (!order.isEmpty()) {
            int currentString = order.removeLast();
            if (currentString == 1) {
                ret.add(numIterator.next());
            } else if (currentString == 2) {
                ret.add(opeIterator.next());
            }
        }
        return ret.toArray(new String[0]);

    }

    private String removeWhitespace(String string) {
        StringBuilder build = new StringBuilder();
        string.chars().filter(c -> c != ' ').forEach(c -> build.append(Character.toString((char) c)));
        return build.toString();
    }

    private boolean areAPair(char c1, char c2) {
        boolean ret = false;
        if (c1 == '(' && c2 == ')') {
            ret = true;
        }
        if (c1 == '{' && c2 == '}') {
            ret = true;
        }
        if (c1 == '[' && c2 == ']') {
            ret = true;
        }
        return ret;
    }

    private boolean checkBalance(String exp) {
        Stack<Character> stack = new Stack<>();
        boolean isBalanced = true;
        int position = 0;
        char nextCharacter;

        while (isBalanced == true && !(position == exp.length())) {
            nextCharacter = exp.charAt(position);

            switch (nextCharacter) {
                case '(': case '[': case '{':
                    stack.push(nextCharacter);
                    break;
            
                case ')': case ']': case '}':
                    //fail case: there are no open left delimiters.
                    if (stack.isEmpty()) {
                        isBalanced = false;
                    } else {
                        char openDelimiterFromStack = stack.pop();
                        isBalanced = areAPair(openDelimiterFromStack, nextCharacter);
                    }
                default:
                    break;
            }

            position++;
        }
        if (!stack.isEmpty()) {
            isBalanced = false;
        }
        return isBalanced;
    }

    private boolean ensureNoSequentialOperators(String input) {
        boolean isValid = true; //assume that the expression is proper
        char currentChar;
        for (int i = 0; i < input.length(); i++) {
            currentChar = input.charAt(i);
            if (i == 0 && (currentChar == '+' || currentChar == '-' || currentChar == '*' || currentChar == '/' || currentChar == '^')) {
                isValid = false;
                break;
            } else if (i < input.length() - 1  && (currentChar == '+' || currentChar == '-' || currentChar == '*' || currentChar == '/' || currentChar == '^')) {
                char tempChar = input.charAt(i + 1);
                if (tempChar == '+' || tempChar == '-' || tempChar == '*' || tempChar == '/' || tempChar == '^') {
                    isValid = false;
                    break;
                }
            } else if (i == input.length() - 1 && (currentChar == '+' || currentChar == '-' || currentChar == '*' || currentChar == '/' || currentChar == '^')) {
                isValid = false;
                break;
            }
        }

        return isValid;
    }

    //main for testing
    public static void main(String[] args) {
        InputInterpreter inp = new InputInterpreter();
        String input = "100+243-44*300^34*(42-100)";
        System.out.println(Arrays.toString(inp.interpretInput(input)));
        
        System.out.println(inp.removeWhitespace("1 + 2 + 4 * 5"));
    }
}
