package org.soen;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * BetaText.java
 *
 * Final implementation for D1 – SOEN 6011
 * Function: B(x, y) = (x-1)! * (y-1)! / (x + y - 1)!
 *
 * Requirements:
 * - R1: Accepts two real-number inputs > 0 (using integers for simplicity)     - ✅
 * - R2: Computes B(x, y) via factorial approximation                           - ✅
 * - R3: Uses a textual user interface (console I/O)                            - ✅
 * - R4: Handles invalid input with clear error messages                        - ✅
 * - R5: Separates computation and I/O                                          - ✅
 * - R6: GUI design in D2 will ensure accessibility (screen reader-friendly)    - D2
 *
 * Author: Venis Prajapati
 * References: [1][2][4][6]
 */
public class BetaText {

    public static void main(String[] args) {

        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.println("=== Beta Function Calculator: B(x, y) ===");
        Scanner scanner = new Scanner(System.in);

        try {
            // --- R1: Input Handling ---
            System.out.print("Enter positive integer x (> 0): ");
            int x = scanner.nextInt();

            System.out.print("Enter positive integer y (> 0): ");
            int y = scanner.nextInt();

            // --- R4: Input Validation ---
            if (x <= 0 || y <= 0) {
                System.out.println("❌ Error: Both x and y must be positive integers.");
                return;
            }

            // --- R2: Beta Computation Logic ---
            long numerator = factorial(x - 1) * factorial(y - 1);
            long denominator = factorial(x + y - 1);
            double beta = (double) numerator / denominator;

            // --- R3: Output ---
            System.out.printf("✅ B(%d, %d) = %.6f%n", x, y, beta);

        } catch (InputMismatchException e) {
            System.out.println("❌ Error: Invalid input. Please enter integer values only.");
        } catch (ArithmeticException e) {
            System.out.println("❌ Math error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Unexpected error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    /**
     * Computes factorial of a non-negative integer n
     * @param n number to compute factorial of
     * @return n! as a long
     */
    public static long factorial(int n) {
        if (n < 0) throw new IllegalArgumentException("Negative input to factorial");
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}
