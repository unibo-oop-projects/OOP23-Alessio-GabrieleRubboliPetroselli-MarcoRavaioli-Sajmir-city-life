package unibo.citylife.model.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unibo.citysimulation.model.map.impl.ImageHandler;

import java.awt.image.BufferedImage;
import java.util.Optional;

class ImageHandlerTest {

    private ImageHandler imageHandler;
    private transient BufferedImage originalImage;

    @BeforeEach
    void setUp() {
        originalImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        imageHandler = new ImageHandler();
        imageHandler.setImage(originalImage);
    }

    @Test
    void testSetImage() {
        final Optional<BufferedImage> storedImage = imageHandler.getImage();
        assertTrue(storedImage.isPresent());
        assertNotSame(originalImage, storedImage.get());
    }

    // getting an image should not expose internal references and
    // after serialization and deserialization should return the same image
    @Test
    void testGetImageNotExpose() {
        final Optional<BufferedImage> retrievedImageOpt = imageHandler.getImage();
        assertTrue(retrievedImageOpt.isPresent());

        final BufferedImage retrievedImage = retrievedImageOpt.get();
        assertNotSame(originalImage, retrievedImage);
        assertEquals(originalImage.getWidth(), retrievedImage.getWidth());
        assertEquals(originalImage.getHeight(), retrievedImage.getHeight());
        assertEquals(originalImage.getType(), retrievedImage.getType());
    }
}
