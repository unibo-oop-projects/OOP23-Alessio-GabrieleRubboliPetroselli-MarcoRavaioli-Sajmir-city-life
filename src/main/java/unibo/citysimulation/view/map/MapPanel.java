package unibo.citysimulation.view.map;

import unibo.citysimulation.view.StyledPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class MapPanel extends StyledPanel {
    private BufferedImage mapImage;

    public MapPanel() {
        super(); // Chiama il costruttore di StyledPanel per applicare lo stile al pannello

        try {
            // Carica l'immagine usando un percorso relativo al classpath
            URL imageUrl = getClass().getResource("/unibo/citysimulation/mapImage.jpeg");
            if (imageUrl != null) {
                mapImage = ImageIO.read(imageUrl);
            } else {
                throw new IOException("Image file not found");
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Puoi gestire l'eccezione qui, ad esempio mostrando un messaggio di errore
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Disegna l'immagine sulla JPanel
        if (mapImage != null) {
            g.drawImage(mapImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        // Imposta le dimensioni preferite della JPanel in base alle dimensioni dell'immagine
        return new Dimension(mapImage.getWidth(), mapImage.getHeight());
    }
}