package org.soen;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for BetaCalculator and FactorialUtil.
 */
public final class BetaCalculatorTest {

    @Test
    @DisplayName("Factorial of 0 is 1")
    void factorialZero() {
        assertEquals("1", FactorialUtil.factorial(0).toString());
    }

    @Test
    @DisplayName("Factorial of 5 is 120")
    void factorialFive() {
        assertEquals("120", FactorialUtil.factorial(5).toString());
    }

    @Test
    @DisplayName("Factorial negative throws")
    void factorialNegative() {
        assertThrows(BetaException.class, () -> FactorialUtil.factorial(-1));
    }

    @Test
    @DisplayName("Beta(3,4) matches expected value (approx)")
    void beta3and4() {
        final BigDecimal beta = BetaCalculator.beta(3, 4);
        // Expected: (2! * 3!) / 6! = (2 * 6) / 720 = 12/720 = 1/60 = 0.016666...
        final BigDecimal expected = new BigDecimal("0.016666666666666666");
        // Compare using double precision tolerance
        assertEquals(expected.doubleValue(), beta.doubleValue(), 1e-12);
    }

    @Test
    @DisplayName("Beta symmetry: B(a,b) == B(b,a)")
    void betaSymmetry() {
        final BigDecimal b1 = BetaCalculator.beta(4, 7);
        final BigDecimal b2 = BetaCalculator.beta(7, 4);
        assertEquals(b1.doubleValue(), b2.doubleValue(), 1e-12);
    }

    @Test
    @DisplayName("Beta invalid inputs throw")
    void betaInvalidInputs() {
        assertThrows(BetaException.class, () -> BetaCalculator.beta(0, 3));
        assertThrows(BetaException.class, () -> BetaCalculator.beta(3, 0));
        assertThrows(BetaException.class, () -> BetaCalculator.beta(-1, 2));
    }
}
