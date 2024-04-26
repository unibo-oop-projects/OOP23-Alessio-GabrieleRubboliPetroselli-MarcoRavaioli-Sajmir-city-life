package unibo.citysimulation.model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

// Import necessari

public class MapModel {
    private BufferedImage image;
    private int normClickedX = -1;
    private int normClickedY = -1;
    private int maxX = -1;
    private int maxY = -1;

    public MapModel() {
        loadMapImage();
        
    }

    public void setLastClickedCoordinates(int x, int y) {
        normClickedX = normalizeCoordinate(x);
        normClickedY = normalizeCoordinate(y);
    }
    
    public void setMaxCoordinates(int x, int y) {
        maxX = x;
        maxY = y;
        System.out.println(x);
        System.out.println(y);
    }

    private int normalizeCoordinate(int c) {
        return (int) (c / (double) maxX * 1000);
    }

    public int getImageWidth() {
        return image.getWidth();
    }

    public BufferedImage getImage(){
        return image;
    }

        private void loadMapImage() {
        try {
            // Carica l'immagine usando un percorso relativo al classpath
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

        private void handleImageLoadError(IOException e) {
        // Gestisci l'errore in modo significativo, ad esempio mostrando un messaggio di errore all'utente
        JOptionPane.showMessageDialog(null, "Error loading map image: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

}
