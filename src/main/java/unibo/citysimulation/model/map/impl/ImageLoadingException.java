package unibo.citysimulation.model.map.impl;

/**
 * Custom exception class to handle errors related to image loading.
 * This exception is thrown when an image cannot be loaded, providing
 * a specific message and the underlying cause.
 */
public class ImageLoadingException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new ImageLoadingException with the specified detail message and cause.
     *
     * @param message The detail message explaining the reason for the exception.
     * @param cause   The underlying cause of the exception.
     */
    public ImageLoadingException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
