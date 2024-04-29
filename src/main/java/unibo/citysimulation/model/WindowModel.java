package unibo.citysimulation.model;

/**
 * Represents the model for the main window of the application.
 */
public class WindowModel {
    private int width;
    private int height;

    /**
     * Constructs a WindowModel with the specified width and height.
     *
     * @param width  The width of the window.
     * @param height The height of the window.
     */
    public WindowModel(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Gets the width of the window.
     *
     * @return The width of the window.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets the height of the window.
     *
     * @return The height of the window.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets the width of the window.
     *
     * @param width The width of the window.
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Sets the height of the window.
     *
     * @param height The height of the window.
     */
    public void setHeight(int height) {
        this.height = height;
    }
}
