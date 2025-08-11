package org.soen;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

/**
 * Core computation module for the Beta function B(x,y).
 *
 * <p>This integer-based implementation uses:
 * B(x,y) = Gamma(x) * Gamma(y) / Gamma(x+y),
 * where Gamma(n) = (n - 1)! for integer n > 0.</p>
 */
public final class BetaCalculator {

    /**
     * Decimal precision used for BigDecimal division.
     */
    private static final MathContext MATH_CONTEXT = MathContext.DECIMAL128;

    private BetaCalculator() {
        // Utility class - prevent instantiation
    }

    /**
     * Returns Gamma(z) for integer z > 0 using factorial identity.
     *
     * @param z positive integer
     * @return Gamma(z) as BigInteger
     * @throws BetaException if z <= 0
     */
    private static BigInteger gammaAsBigInteger(final int z) {
        if (z <= 0) {
            throw new BetaException("Gamma(z) requires integer z > 0");
        }
        return FactorialUtil.factorial(z - 1);
    }

    /**
     * Computes Beta(x, y) using big-number arithmetic.
     *
     * @param x positive integer
     * @param y positive integer
     * @return Beta(x, y) as BigDecimal
     * @throws BetaException if x <= 0 or y <= 0
     */
    public static BigDecimal beta(final int x, final int y) {
        if (x <= 0 || y <= 0) {
            throw new BetaException("x and y must be positive integers");
        }

        final BigInteger gammaX = gammaAsBigInteger(x);
        final BigInteger gammaY = gammaAsBigInteger(y);
        final BigInteger gammaSum = gammaAsBigInteger(x + y);

        final BigInteger numerator = gammaX.multiply(gammaY);
        final BigInteger denominator = gammaSum;

        final BigDecimal numDecimal = new BigDecimal(numerator);
        final BigDecimal denDecimal = new BigDecimal(denominator);

        return numDecimal.divide(denDecimal, MATH_CONTEXT);
    }
}
