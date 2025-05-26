import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    public final Calculator calculator = new Calculator();


    @Test
    public void when_add_withValidParameters_then_returnsSum() {
        double result = calculator.calculate(5, 3, "+");
        assertEquals(8.0, result, 0.0001);
    }

    @Test
    public void when_subtract_withValidParameters_then_returnsDifference() {
        double result = calculator.calculate(10, 4, "-");
        assertEquals(6.0, result, 0.0001);
    }

    @Test
    public void when_multiply_withValidParameters_then_returnsProduct() {
        double result = calculator.calculate(2, 3, "*");
        assertEquals(6.0, result, 0.0001);
    }

    @Test
    public void when_divide_withValidParameters_then_returnsQuotient() {
        double result = calculator.calculate(10, 2, "/");
        assertEquals(5.0, result, 0.0001);
    }

    @Test
    public void when_divideByZero_then_throwsException() {
        assertThrows(RuntimeException.class, () -> calculator.calculate(5, 0, "/"));
    }

    @Test
    public void when_invalidBinaryOperator_then_throwsException() {
        assertThrows(RuntimeException.class, () -> calculator.calculate(5, 3, "invalid"));
    }


    @Test
    public void when_exponent_withValidParameters_then_returnsPower() {
        double result = calculator.calculate(2, 3, "exponent");
        assertEquals(8.0, result, 0.0001);
    }



    @Test
    public void when_sqrt_withValidParameters_then_returnsSquareRoot() {
        double result = calculator.calculate(9, "sqrt");
        assertEquals(3.0, result, 0.0001);
    }

    @Test
    public void when_areaOfCircle_withValidRadius_then_returnsArea() {
        double result = calculator.calculate(2, "areaOfCircle");
        assertEquals(Math.PI * 4, result, 0.0001);
    }

    @Test
    public void when_invalidUnaryOperator_then_throwsException() {
        assertThrows(RuntimeException.class, () -> calculator.calculate(10, "cubeRoot"));
    }


    @Test
    public void when_quadratic_withRealRoots_then_returnsRoots() {
        double[] roots = calculator.calculate("x^2 - 5x + 6");
        assertEquals(3.0, roots[0], 0.0001);
        assertEquals(2.0, roots[1], 0.0001);
    }

    @Test
    public void when_quadratic_withNegativeDiscriminant_then_throwsException() {
        assertThrows(ArithmeticException.class, () -> calculator.calculate("x^2 + 2x + 5"));
    }


    @Test
    public void when_differentiate_validPolynomial_then_returnsDerivative() {
        String result = calculator.calculateDifferential("3x^3 + 2x^2 + x + 7");
        assertEquals("9x^2+4x+1", result);
    }

    @Test
    public void when_differentiate_constantOnly_then_returnsZero() {
        String result = calculator.calculateDifferential("7");
        assertEquals("0", result);
    }

    @Test
    public void when_differentiate_singleVariable_then_returnsOne() {
        String result = calculator.calculateDifferential("x");
        assertEquals("1", result);
    }
}
