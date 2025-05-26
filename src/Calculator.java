import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class Calculator {

    private static final Pattern DIFFERENTIATION_PATTERN = Pattern.compile("([+-])?(\\d*)(x)?(?:\\^(\\d+))?");
    private static final String MINUS = "-";
    private static final Pattern QUADRATIC_PATTERN = Pattern.compile("([+-])(\\d*)x\\^2|([+-])(\\d*)x(?!\\^)|([+-])(\\d+)(?!x)");

    public double calculate(double x, double y, String operator) {
        return switch (operator) {
            case "+" -> add(x, y);
            case "-" -> subtract(x, y);
            case "*" -> multiply(x, y);
            case "/" -> divide(x, y);
            case "exponent" -> exponent(x, y);
            default -> throw new RuntimeException("Please provide a valid operator (+,-,*,/,exponent).");
        };
    }


    // Method overloading
    public double calculate(double x, String operator) {
        return switch (operator) {
            case "sqrt" -> sqrt(x);
            case "areaOfCircle" -> areaOfCircle(x);
            default -> throw new RuntimeException("Please provide a valid operator(+,-,*,/).");
        };
    }

    public double[] calculate(String s) {
        return getRootsOfQuadraticEquation(s);
    }

    public String calculateDifferential(String s) {
        return differentiate(s);
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
    private double exponent(final double base, final double power) {
        return Math.pow(base, power);
    }


    private double sqrt(double x) {
        return Math.sqrt(x);
    }

    private double[] getRootsOfQuadraticEquation(String s) {
        s = s.replaceAll(" ", "");

        if (s.charAt(0) != '+' && s.charAt(0) != '-') {
            s = "+" + s;
        }

        Matcher matcher = QUADRATIC_PATTERN.matcher(s);

        int a = 0;
        int b = 0;
        int c = 0;

        while (matcher.find()) {
            if (matcher.group(1) != null) {
                int coeff = matcher.group(2).isEmpty() ? 1 : Integer.parseInt(matcher.group(2));
                a = MINUS.equals(matcher.group(1)) ? -coeff : coeff;
            } else if (matcher.group(3) != null) {
                int coeff = matcher.group(4).isEmpty() ? 1 : Integer.parseInt(matcher.group(4));
                b = MINUS.equals(matcher.group(3)) ? -coeff : coeff;
            } else if (matcher.group(5) != null) {
                int coeff = Integer.parseInt(matcher.group(6));
                c = MINUS.equals(matcher.group(5)) ? -coeff : coeff;
            }
        }

        double discriminant = b * b - 4 * a * c;

        if (discriminant < 0) {
            throw new ArithmeticException("Discriminant is negative.");
        }

        double sqrtD = Math.sqrt(discriminant);
        double x = (-b + sqrtD) / (2 * a);
        double y = (-b - sqrtD) / (2 * a);

        return new double[]{x, y};
    }

    private double areaOfCircle(double radius) {
        return Math.PI * radius * radius;
    }


    // Differentiates a polynomial expression like "3x^3 + 2x^2 + x + 7"
    private String differentiate(String expression) {
        expression = expression.replaceAll(" ", "");

        if (expression.charAt(0) != '-') {
            expression = "+" + expression;
        }

        Matcher matcher = DIFFERENTIATION_PATTERN.matcher(expression);
        

        StringBuilder result = new StringBuilder();

        while (matcher.find()) {
            String sign = matcher.group(1);
            String coeffStr = matcher.group(2);
            String x = matcher.group(3);
            String powerStr = matcher.group(4);

            if (matcher.group().isEmpty()) {
                continue;
            }

            int coeff = coeffStr.isEmpty() ? 1 : Integer.parseInt(coeffStr);
            if (MINUS.equals(sign)) coeff *= -1;
            if (x == null) continue;

            int power = powerStr == null ? 1 : Integer.parseInt(powerStr);

            int newCoeff = coeff * power;
            int newPower = power - 1;

            if (!result.isEmpty() && newCoeff > 0) {
                result.append("+");
            }

            if (newCoeff == -1 && newPower != 0) {
                result.append(MINUS);
            } else if (!(newCoeff == 1 && newPower != 0)) {
                result.append(newCoeff);
            }

            if (newPower > 0) {
                result.append("x");
                if (newPower > 1) {
                    result.append("^").append(newPower);
                }
            }
        }

        return result.isEmpty() ? "0" : result.toString();
    }


}



