package InputInterpreterStuff;

import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        ArrayPostfixConverter translator = new ArrayPostfixConverter();
        
        @SuppressWarnings("resource")
        Scanner scan = new Scanner(System.in);
        String infix = scan.nextLine();

        String postfix = translator.infixToPostfix(infix);

        System.out.println("Infix Expression: " + infix);
        System.out.println("Postfix Expression: " + postfix);

        PostfixEvalulatorFMT evaluator = new PostfixEvalulatorFMT();
        System.out.println("Evaluation of Postfix Expression using given values: " + evaluator.evaluate(postfix));
    }
}
