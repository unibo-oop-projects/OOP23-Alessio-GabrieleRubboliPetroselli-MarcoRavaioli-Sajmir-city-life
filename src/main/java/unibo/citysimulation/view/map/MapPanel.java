package unibo.citysimulation.view.map;

import unibo.citysimulation.view.StyledPanel;
import unibo.citysimulation.view.sidePanels.InfoPanel;
import unibo.citysimulation.controller.MapController;
import unibo.citysimulation.model.MapModel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.swing.*;

public class MapPanel extends StyledPanel {
    private MapController mapController;
    private BufferedImage mapImage;

    public MapPanel(MapModel mapModel, InfoPanel infoPanel) {
        super(bgColor); // Chiama il costruttore di StyledPanel per applicare lo stile al pannello
        this.mapController = new MapController(mapModel, infoPanel);

        try {
            // Carica l'immagine usando un percorso relativo al classpath
            URL imageUrl = getClass().getResource("/unibo/citysimulation/mapImage.jpeg");
            if (imageUrl != null) {
                mapImage = ImageIO.read(imageUrl);
            } else {
                throw new IOException("Image file not found");
            }
        } catch (IOException e) {
            handleImageLoadError(e);
        }

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                mapController.handleMouseclick(x, y);
            }
        });


    }

    private void handleImageLoadError(IOException e) {
        // Gestisci l'errore in modo significativo, ad esempio mostrando un messaggio di errore all'utente
        JOptionPane.showMessageDialog(this, "Error loading map image: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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