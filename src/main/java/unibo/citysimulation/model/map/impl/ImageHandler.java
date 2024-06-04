package unibo.citysimulation.model.map.impl;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.Optional;

/**
 * Class that handles the mapImage with serialization and deserialization.
 * Creates defensive copies of the mapImage when necessary.
 */
public final class ImageHandler implements Serializable {
    private static final long serialVersionUID = 1L;

    private transient BufferedImage image;

    /**
     * Gets a defensive copy of the current image.
     *
     * @return a defensive copy of the current image
     */
    public Optional<BufferedImage> getImage() {
        return Optional.ofNullable(createImageDefensiveCopy(image));
    }

    /**
     * Sets the image with a defensive copy.
     *
     * @param image the image to set
     */
    public void setImage(final BufferedImage image) {
        this.image = createImageDefensiveCopy(image);
    }

    private static BufferedImage createImageDefensiveCopy(final BufferedImage original) {
        if (original == null) {
            throw new IllegalArgumentException("The original image cannot be null");
        }
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
