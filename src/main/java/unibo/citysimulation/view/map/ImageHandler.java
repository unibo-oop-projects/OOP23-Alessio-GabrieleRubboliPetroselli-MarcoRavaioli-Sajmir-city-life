package unibo.citysimulation.view.map;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;

public class ImageHandler implements Serializable {
    private static final long serialVersionUID = 1L;

    private transient BufferedImage image;

    public BufferedImage getImage() {
        return createImageDefensiveCopy(image);
    }

    public void setImage(BufferedImage image) {
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

    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }
}
