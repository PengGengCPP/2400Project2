package App;

public class FibRecursive {
    
    public static void printFibTo(int n) {
        for (int i = 1; i <= n; i++) {
            System.out.println(getFib(i));
        }
    }

    private static int getFib(int n) {
        //base cases
        if (n <= 0) {
            throw new IllegalArgumentException("lmao");
        }

        if (n == 1 || n == 2) {
            return 1;
        }

        return getFib(n - 1) + getFib(n - 2);
    }

    public static void main(String[] args) {
        printFibTo(10);
    }

}
