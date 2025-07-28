package org.soen;

/**
 * BetaCalculator.java

 * Core computation module for the Beta function B(x,y), implemented "from scratch":
 *   B(x,y) = Γ(x)·Γ(y) / Γ(x+y)
 * where Γ(n) = (n-1)! for integer n > 0.

 * Requirements addressed:
 *   - R1: Validates two positive integer inputs (x, y > 0).
 *   - R2: Computes Beta function without using Math.* or external math libraries.
 *   - R3: Throws IllegalArgumentException on invalid inputs.
 *   - R5: Maintains separation of computation logic from UI code.
 *   - R6: Supports potential integration with accessibility features via UI labels.
 */
public class BetaCalculator {

    /**
     * Computes the factorial of a non-negative integer n.
     * @param n non-negative integer
     * @return n! as a long
     */
    private static long factorial(int n) {
        long result = 1L;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    /**
     * Approximates the Gamma function for integer inputs:
     * Γ(z) = (z - 1)! for z > 0.
     * @param z positive integer
     * @return (z - 1)! as a long
     * @throws IllegalArgumentException if z <= 0
     */
    private static long gamma(int z) {
        if (z <= 0) {
            throw new IllegalArgumentException("Gamma(z) requires integer z > 0");
        }
        return factorial(z - 1);
    }

    /**
     * Computes the Beta function B(x, y) = Γ(x)·Γ(y) / Γ(x + y).
     * Only accepts positive integer parameters.
     * @param x positive integer parameter
     * @param y positive integer parameter
     * @return Beta(x, y) as a double value
     * @throws IllegalArgumentException if x <= 0 or y <= 0
     */
    public static double beta(int x, int y) {
        if (x <= 0 || y <= 0) {
            throw new IllegalArgumentException("x and y must be positive integers");
        }
        long numerator = gamma(x) * gamma(y);
        long denominator = gamma(x + y);
        return (double) numerator / denominator;
    }
}