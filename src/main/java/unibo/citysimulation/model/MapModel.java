package unibo.citysimulation.model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 * Model class representing the map.
 */
public class MapModel {
    private BufferedImage image;
    private int normClickedX = -1;
    private int normClickedY = -1;
    private int maxX = -1;
    private int maxY = -1;

    /**
     * Constructs a MapModel object and loads the map image.
     */
    public MapModel() {
        loadMapImage();
    }

    /**
     * Sets the last clicked coordinates after normalization.
     *
     * @param x The x-coordinate of the click.
     * @param y The y-coordinate of the click.
     */
    public void setLastClickedCoordinates(int x, int y) {
        normClickedX = normalizeCoordinate(x, maxX);
        normClickedY = normalizeCoordinate(y, maxY);
    }
    
    /**
     * Sets the maximum coordinates of the map.
     *
     * @param x The maximum x-coordinate.
     * @param y The maximum y-coordinate.
     */
    public void setMaxCoordinates(int x, int y) {
        maxX = x;
        maxY = y;
    }

    /**
     * Normalizes a coordinate based on the maximum value.
     *
     * @param c   The coordinate to normalize.
     * @param max The maximum value of the coordinate.
     * @return The normalized coordinate.
     */
    private int normalizeCoordinate(int c, int max) {
        return (int) (c / (double) maxX * 1000);
    }

    public int getNormX() {
        return normClickedX;
    }

    public int getNormY() {
        return normClickedY;
    }

    /**
     * Retrieves the image of the map.
     *
     * @return The BufferedImage object representing the map image.
     */
    public BufferedImage getImage(){
        return image;
    }

    /**
     * Loads the map image from the resources.
     */
    private void loadMapImage() {
        try {
            // Load the image using a relative path within the classpath
            URL imageUrl = getClass().getResource("/unibo/citysimulation/mapImage.jpeg");
            if (imageUrl != null) {
                image = ImageIO.read(imageUrl);
            } else {
                throw new IOException("Image file not found");
            }
        } catch (IOException e) {
            handleImageLoadError(e);
        }
    }

    /**
     * Handles errors that occur during image loading.
     *
     * @param e The IOException instance representing the error.
     */
    private void handleImageLoadError(IOException e) {
        // Handle the error in a meaningful way, such as showing an error message to the user
        JOptionPane.showMessageDialog(null, "Error loading map image: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
