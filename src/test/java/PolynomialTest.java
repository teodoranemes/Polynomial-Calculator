import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import Model.Polynomial;
import Model.DivisionResult;

public class PolynomialTest {

    @Test
    public void testAddition() {
        Polynomial p1 = new Polynomial();
        p1.setCoefficient(2, 3); // 3x^2
        p1.setCoefficient(1, 4); // 4x

        Polynomial p2 = new Polynomial();
        p2.setCoefficient(2, 2); // 2x^2
        p2.setCoefficient(0, 5); // 5

        Polynomial result = p1.addition(p2);
        assertEquals("5.0x^2 + 4.0x + 5.0", result.toString());
    }

    @Test
    public void testSubtraction() {
        Polynomial p1 = new Polynomial();
        p1.setCoefficient(2, 3); // 3x^2
        p1.setCoefficient(1, 4); // 4x

        Polynomial p2 = new Polynomial();
        p2.setCoefficient(2, 2); // 2x^2
        p2.setCoefficient(0, 5); // 5

        Polynomial result = p1.subtraction(p2);
        assertEquals("x^2 + 4.0x - 5.0", result.toString());
    }

    @Test
    public void testMultiplication() {
        Polynomial p1 = new Polynomial();
        p1.setCoefficient(2, 3); // 3x^2
        p1.setCoefficient(1, 4); // 4x

        Polynomial p2 = new Polynomial();
        p2.setCoefficient(2, 2); // 2x^2
        p2.setCoefficient(0, 5); // 5

        Polynomial result = p1.multiplication(p2);
        assertEquals("6.0x^4 + 8.0x^3 + 15.0x^2 + 20.0x", result.toString());
    }

    @Test
    public void testDerivative() {
        Polynomial p = new Polynomial();
        p.setCoefficient(3, 3); // 3x^3
        p.setCoefficient(2, 2); // 2x^2
        p.setCoefficient(1, 1); // x

        Polynomial result = p.derivative();
        assertEquals("9.0x^2 + 4.0x + 1.0", result.toString());
    }

    @Test
    public void testIntegration() {
        Polynomial p = new Polynomial();
        p.setCoefficient(3, 3); // 3x^3
        p.setCoefficient(2, 2); // 2x^2
        p.setCoefficient(1, 1); // x

        Polynomial result = p.integration();
        assertEquals("0.75x^4 + 0.6666666666666666x^3 + 0.5x^2", result.toString());
    }

    @Test
    public void testDivision() {

        Polynomial dividend = new Polynomial();
        dividend.setCoefficient(2, 5);

        Polynomial divisor = new Polynomial();
        divisor.setCoefficient(1, 1);

        DivisionResult divisionResult = dividend.division(divisor);
        Polynomial quotient = divisionResult.getQuotient();
        Polynomial remainder = divisionResult.getRemainder();

        Polynomial expectedQuotient = new Polynomial();
        expectedQuotient.setCoefficient(1, 5);

        Polynomial expectedRemainder = new Polynomial();

        assertEquals(expectedQuotient.toString(), quotient.toString());
        assertEquals(expectedRemainder.toString(), remainder.toString());
    }

}
