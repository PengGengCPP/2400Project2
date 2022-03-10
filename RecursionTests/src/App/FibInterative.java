package App;

public class FibInterative {
    
    public static void printFibTo(int n) {
        for (int i = 1; i <= n; i++) {
            System.out.println(getFib(i));
        }
    }

    private static int getFib(int n) {
        int previous = 0;
        int current = 1; 
        int temp;

        for (int i = 1; i <= n; i++) {
            if (i == 1) {
                current = 1;
                previous = 0;
                continue;
            }
            
            temp = current;
            current = current + previous;
            previous = temp;
        }

        return current;
    }

    public static void main(String[] args) {
        printFibTo(10);
    }
}
