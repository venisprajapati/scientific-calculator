package org.soen;

/**
 * Domain-specific exception for Beta-related validation errors.
 */
public final class BetaException extends IllegalArgumentException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new BetaException with the specified detail message.
     *
     * @param message the detail message
     */
    public BetaException(final String message) {
        super(message);
    }
}
