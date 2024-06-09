package unibo.citylife.model.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unibo.citysimulation.model.map.impl.ImageHandler;

import java.awt.image.BufferedImage;

class ImageHandlerTest {

    private ImageHandler imageHandler;
    private transient BufferedImage originalImage;

    @BeforeEach
    void setUp() {
        originalImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        imageHandler = new ImageHandler("/unibo/citysimulation/images/mapImage.png");
        imageHandler.setImage(originalImage);
    }

    @Test
    void testSetImage() {
        final BufferedImage storedImage = imageHandler.getImage();
        assertNotSame(originalImage, storedImage);
    }

    // getting an image should not expose internal references and
    // after serialization and deserialization should return the same image
    @Test
    void testGetImageNotExpose() {
        final BufferedImage retrievedImageOpt = imageHandler.getImage();

        final BufferedImage retrievedImage = retrievedImageOpt;
        assertNotSame(originalImage, retrievedImage);
        assertEquals(originalImage.getWidth(), retrievedImage.getWidth());
        assertEquals(originalImage.getHeight(), retrievedImage.getHeight());
        assertEquals(originalImage.getType(), retrievedImage.getType());
    }
}
