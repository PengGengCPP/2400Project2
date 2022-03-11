package Postfix;

public class DriverPostfix {
    public static void main(String[] args) {
        ConvertToPostfix translator = new ConvertToPostfix();
        String infix = "a*b/(c-a)+d*e";
        String postfix = translator.infixToPostfix(infix);

        System.out.println("Infix Expression: " + infix);
        System.out.println("Postfix Expression: " + postfix);

        //TODO: postfix evaluator when arraystack is done
    }
}
