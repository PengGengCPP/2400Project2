package Postfix;

public class InfixToPostfix
{
    public String convertToPostfix(String infix)
    {
        LinkedStack operatorStack = 0;
        String postfix = "";
        int i = 0;
        final String operators = "(+-*/"; // higher index = higher predecence

        while(i < infix.length())
        {
            char nextCharacter = postfix.charAt(i);
            switch(nextCharacter)
            {
                // variable
                case 'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
                'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z':
                    postfix.concat(nextCharacter);
                    break;
                
                // ^ (exponent)
                case '^':
                    operatorStack.push(nextCharacter);
                    break;
                
                // +, -, *, / (addition, subtraction, multiplication, division)
                case '+', '-', '*', '/':
                    while (!operatorStack.isEmpty() && operators.indexOf(nextCharacter) >= operators.indexOf(operatorStack.peek()))
                    {
                        postfix.concat(operatorStack.pop());
                    }
                    operatorStack.push(nextCharacter);
                    break;

                // ( [open parenthesis]
                case '(':
                    operatorStack.push(nextCharacter);
                
                // ) [close parenthesis]
                case ')':
                    topOerator = operatorStack.pop();
                    while (topOperator != '(')
                    {
                        postfix.concat(topOperator);
                        topOperator = operatorStack.pop();
                    }
                    break;
                    
            }
        }
    }
}