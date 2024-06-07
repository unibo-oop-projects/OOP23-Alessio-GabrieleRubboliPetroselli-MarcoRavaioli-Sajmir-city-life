package unibo.citysimulation.model.map.impl;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import javax.imageio.ImageIO;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Utility class to support MapModel and MapPanel to manage and handle the map image.
 * This class encapsulates the logic for loading, retrieving, and setting the map image.
 */
public final class ImageHandler implements Serializable {
    private static final long serialVersionUID = 1L;
    private transient BufferedImage image;
    private final String imagePath;

    /**
     * Constructor to create a new ImageHandler and load the map image from the specified path.
     *
     * @param imagePath The path to the map image.
     */
    public ImageHandler(final String imagePath) {
        this.imagePath = imagePath;
        this.image = loadImage();
    }

    /**

     * Loads an image from the specified path.

     *

     * @return The loaded BufferedImage.

     */

     private BufferedImage loadImage() {
        try {
            BufferedImage img = ImageIO.read(getClass().getResource(imagePath));
            if (img == null) {
                throw new IOException("Image could not be read, possibly due to invalid path: " + imagePath);
            }
            return img;
        } catch (IOException e) {
            throw new RuntimeException("Failed to load map image", e);
        }
    }

    /**
     * Gets a defensive copy of the current image.
     * This ensures the original image cannot be modified externally.
     *
     * @return A defensive copy of the current image.
     */
    public BufferedImage getImage() {
        return createImageDefensiveCopy(image);
    }

    /**
     * Sets the image with a defensive copy.
     *
     * @param image the image to set
     */
    public void setImage(final BufferedImage image) {
        this.image = createImageDefensiveCopy(image);
    }

    /**
     * Creates a defensive copy of the provided image.
     * This prevents the original image from being altered.
     *
     * @param original The original image.
     * @return A defensive copy of the original image.
     */
    private BufferedImage createImageDefensiveCopy(final BufferedImage original) {
        final BufferedImage copy = new BufferedImage(
            original.getWidth(),
            original.getHeight(),
            original.getType()
        );
        final Graphics2D g = copy.createGraphics();
        g.drawImage(original, 0, 0, null);
        g.dispose();
        return copy;
    }

    /**
     * Custom deserialization logic to handle transient fields.
     *
     * @param ois the ObjectInputStream
     * @throws IOException            if an I/O error occurs
     * @throws ClassNotFoundException if the class of a serialized object cannot be found
     */
    private void readObject(final ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        this.image = loadImage();
    }

    /**
     * Custom serialization logic to handle transient fields.
     *
     * @param oos the ObjectOutputStream
     * @throws IOException if an I/O error occurs
     */
    private void writeObject(final ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
    }
}
