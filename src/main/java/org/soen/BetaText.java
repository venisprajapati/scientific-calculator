package org.soen;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Command-line interface for Beta(x, y) calculation.
 *
 * <p>Uses FactorialUtil and BetaCalculator. All inputs must be
 * positive integers.</p>
 */
public final class BetaText {

    /**
     * Prompt text for x input.
     */
    private static final String PROMPT_X = "Enter positive integer x (> 0): ";

    /**
     * Prompt text for y input.
     */
    private static final String PROMPT_Y = "Enter positive integer y (> 0): ";

    /**
     * Decimal format used to display Beta results.
     */
    private static final DecimalFormat DECIMAL_FORMAT =
            new DecimalFormat("#.################");

    private BetaText() {
        // Utility class
    }

    /**
     * CLI entry point.
     *
     * @param args unused
     */
    public static void main(final String[] args) {

        try {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        } catch (Exception ignored) {
            // Non-fatal: some terminals do not support ANSI clear.
        }

        System.out.println("=== Beta Function Calculator: B(x, y) ===");
        final Scanner scanner = new Scanner(System.in);
        try {
            System.out.print(PROMPT_X);
            final int x = scanner.nextInt();

            System.out.print(PROMPT_Y);
            final int y = scanner.nextInt();

            final BigDecimal beta = BetaCalculator.beta(x, y);
            System.out.printf("✅ B(%d, %d) = %s%n", x, y,
                    DECIMAL_FORMAT.format(beta));

        } catch (InputMismatchException ime) {
            System.out.println("❌ Error: Invalid input. Please enter "
                    + "integer values only.");
        } catch (BetaException be) {
            System.out.println("❌ Error: " + be.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Unexpected error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
