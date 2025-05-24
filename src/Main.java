import java.util.Arrays;


public class Main {

    public static void main(String[] args) {
        Calculator c = new Calculator();
        double result1 = c.calculate(5,"sqrt");
        double result2 = c.calculate(4,5,"+");
        double[] result3 = c.calculate("2x² + 5x - 3");

        System.out.println(Arrays.toString(result3));
    }
}