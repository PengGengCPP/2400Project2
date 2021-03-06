package Postfix;

public class DriverPostfix {
    public static void main(String[] args) {
        ConvertToPostfix translator = new ConvertToPostfix();
        String infix = "a*b/(c-a)+d*e";
        String postfix = translator.infixToPostfix(infix);

        System.out.println("Infix Expression: " + infix);
        System.out.println("Postfix Expression: " + postfix);

        PostfixEvaluator evaluator = new PostfixEvaluator(2, 3, 4, 5, 6);
        System.out.println("Evaluation of Postfix Expression using given values: " + evaluator.evaluate(postfix));
    }
}
