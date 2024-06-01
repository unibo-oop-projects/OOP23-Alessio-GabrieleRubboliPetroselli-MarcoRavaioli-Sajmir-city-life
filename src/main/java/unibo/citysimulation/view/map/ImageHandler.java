package unibo.citysimulation.view.map;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

public class ImageHandler implements Serializable{
    private static final long serialVersionUID = 1L;

    private transient BufferedImage image;
    private String imagePath;

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = createImageDefensiveCopy(image);
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String path) throws IOException {
        this.imagePath = path;
        this.image = ImageIO.read(new File(path));
    }

    private static BufferedImage createImageDefensiveCopy(final BufferedImage original) {
        if (original == null) {
            throw new IllegalArgumentException("The original image cannot be null");
        }
        // Create a new BufferedImage with the same dimensions and type as the original
        final BufferedImage copy = new BufferedImage(
            original.getWidth(),
            original.getHeight(),
            original.getType()
        );
        // Draw the original image onto the copy
        final Graphics2D g = copy.createGraphics();
        g.drawImage(original, 0, 0, null);
        g.dispose();
        return copy;
    }

    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        if (imagePath != null) {
            image = ImageIO.read(new File(imagePath));
        }
    }
}
