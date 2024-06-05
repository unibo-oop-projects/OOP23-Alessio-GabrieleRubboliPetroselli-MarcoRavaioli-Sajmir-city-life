package unibo.citysimulation.model.map.impl;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import javax.imageio.ImageIO;

/**
 * Utility class to support MapModel and MapPanel to manage and handle the map image.
 */
public class ImageHandler implements Serializable {
    private static final long serialVersionUID = 1L;
    private transient BufferedImage image;

    /**
     * Contructor to create a new ImageHandler and load the map image from Url.
     * 
     * @param imagePath The path to the map image.
     */
    public ImageHandler(final String imagePath) {
        this.image = loadImageWithExceptionHandling(imagePath);
    }

    private BufferedImage loadImageWithExceptionHandling(final String imagePath) {
        try {
            return loadImage(imagePath);
        } catch (IOException e) {
            throw new ImageLoadingException("Failed to load map image", e);
        }
    }

    private BufferedImage loadImage(final String imagePath) throws IOException {
        final BufferedImage img = ImageIO.read(getClass().getResource(imagePath));
        if (img == null) {
            throw new IOException("Image could not be read, possibly due to invalid path: " + imagePath);
        }
        return img;
    }

    /**
     * Gets a defensive copy of the current image.
     *
     * @return an optional containing a defensive copy of the current image
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
}
