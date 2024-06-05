package unibo.citysimulation.model.map.impl;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
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
     */
    public ImageHandler(String imagePath) {
        try {
            loadImage(imagePath);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load map image", e); // Throw a runtime exception
        }
    }

    private void loadImage(String imagePath) throws IOException {
        final URL imageUrl = ImageHandler.class.getResource(imagePath);
        if (imageUrl != null) {
            image = ImageIO.read(imageUrl);
            if (image == null) {
                throw new IOException("Image is corrupted or unsupported");
            }
        } else {
            throw new IOException("Image file not found");
        }
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
