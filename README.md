## Project 2 - LinkedStack and ResizableArrayStack
Implementation of LinkedStack and ResizableArrayStack as well as code to convert infix notations to postfix notations and evaluate postfix notations using given variables.

## Group Members

- Benjamin Chen - LinkedStack and ResizableArrayStack
- Jimmy Peng - Javadoc and videos
- Kyung Ho Min - ConvertToPostfix and EvaluatePostfix

## Features

- ConvertToPostfix object that you pass an infix expression into to get a postfix expression
- PostfixEvaluator object that you initialize with values for a, b, c, d, and e that evaluates a given postfix expression
## Extra Features

- Unbalanced brace detection
- Basic input filtration to allow only lowercase letters, numbers, and operators as the input infix expression
- Input interpreter that can parse infix expression user input and display a calculated result (see InputInterpreterStuff/Driver.java)
    - Sanitizes input (allows only legal characters, removes whitespace, brace checking, checking for no sequential operators, and parentheses and operator rule checking)
    - Evaluates the expression using integer calculations
    - No decimal support
    - Not exactly tested properly though

## Links
- Index File: See javadoc/index.html
- Video: https://www.youtube.com/watch?v=ywBFBBRxytk
