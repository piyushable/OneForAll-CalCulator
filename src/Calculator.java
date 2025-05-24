public class Calculator {

    public double calculate(double x, double y, String operator) {
        return switch (operator) {
            case "+" -> add(x, y);
            case "-" -> subtract(x, y);
            case "*" -> multiply(x, y);
            case "/" -> divide(x, y);
            default -> throw new RuntimeException("Please provide a valid operator(+,-,*,/).");
        };

    }

    // Method overloading
    public double calculate(double x, String operator) {
        return switch (operator) {
            case "sqrt" -> sqrt(x);
            default -> throw new RuntimeException("Please provide a valid operator(+,-,*,/).");
        };
    }

    public double[] calculate(String s) {
        return getRootsOfQuadraticEquation(s);
    }

    private double add ( double x, double y) {
        return x + y;
    }

    private double subtract ( double x, double y) {
        return x - y;
    }

    private double multiply ( double x, double y) {
        return x * y;
    }

    private double divide ( double x, double y) {
        if(y == 0) {
            throw new RuntimeException("Division by zero is not possible.");
        }
        return x / y;
    }

    private double sqrt(double x) {
        return Math.sqrt(x);
    }

    private double[] getRootsOfQuadraticEquation(String s) {
        // ax^2 + bx + c
        // 2x^2 + 3x + 6
        // 01234567891011
        // x,y = (-b +/- sqrt(b^2 - 4ac))/2a

        int a = Character.getNumericValue(s.charAt(0));
        int b = Character.getNumericValue(s.charAt(7));
        int c = Character.getNumericValue(s.charAt(s.length() - 1));

        int discriminant = b * b - 4 * a * c;
        System.out.println("D = " + discriminant);
        if (discriminant < 0) {
            throw new ArithmeticException("Discriminant is negative.");
        }
        double x = (-1*b + Math.sqrt(discriminant))/2*a;
        double y = (-1*b - Math.sqrt(discriminant))/2*a;

        return new double[]{x,y};
    }
}


