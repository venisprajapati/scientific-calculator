package org.soen;

import java.math.BigInteger;

/**
 * Utility class providing factorial computation.
 * Implemented iteratively to avoid recursion and stack overflow.
 */
public final class FactorialUtil {

    private FactorialUtil() {
        // Utility class - prevent instantiation
    }

    /**
     * Computes factorial of a non-negative integer n (n!).
     *
     * @param n non-negative integer
     * @return n! as BigInteger
     * @throws BetaException if n is negative
     */
    public static BigInteger factorial(final int n) {
        if (n < 0) {
            throw new BetaException("Factorial requires n >= 0");
        }

        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }
}
