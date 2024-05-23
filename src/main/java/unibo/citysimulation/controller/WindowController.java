package unibo.citysimulation.controller;

import unibo.citysimulation.model.CityModel;
import unibo.citysimulation.utilities.Pair;
import unibo.citysimulation.view.WindowView;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * Controller class responsible for managing the main window.
 */
public class WindowController {
    private WindowView windowView;
    private CityModel cityModel;

    public WindowController(WindowView windowView, CityModel cityModel) {
        this.windowView = windowView;
        this.cityModel = cityModel;

        this.windowView.addResizeListener(new ResizeListener());

        cityModel.setFrameSize(cityModel.getFrameSize()); // questo comando potrebbe essere modificato, così funziona

        new MapController(cityModel, windowView.getInfoPanel(), windowView.getMapPanel());

        new ClockController(cityModel.getClockModel(), windowView.getClockPanel());

        new InputController(cityModel, cityModel.getInputModel(), windowView.getInputPanel(),
                windowView.getClockPanel());

        new GraphicsController(cityModel, windowView.getGraphicsPanel());

        Pair<Integer, Integer> frameSize = cityModel.getFrameSize();
        windowView.updateFrame(frameSize.getFirst(), frameSize.getSecond());
    }

    /**
     * Inner class responsible for handling component resize events.
     */
    private class ResizeListener extends ComponentAdapter {
        @Override
        public void componentResized(ComponentEvent e) {
            super.componentResized(e);

            // Ottieni le dimensioni del frame dopo il ridimensionamento
            int newWidth = e.getComponent().getWidth();
            int newHeight = e.getComponent().getHeight();

            // Se la modifica è stata apportata all'altezza
            if (newHeight != cityModel.getFrameHeight()) {
                newWidth = newHeight * 2;
            } else { // Altrimenti, la modifica è stata apportata alla larghezza
                newHeight = newWidth / 2;
            }


            // Aggiorna le coordinate massime nel modello della mappa con le nuove dimensioni della mapPanel
            cityModel.getMapModel().setMaxCoordinates(windowView.getMapPanel().getWidth(), windowView.getMapPanel().getHeight());   
            // Aggiorna le informazioni sui trasporti nel modello della mappa                                                          
            cityModel.getMapModel().setTransportInfo(cityModel.getTransportLines());                                                   
            // Imposta le nuove dimensioni del frame nel modello della città
            cityModel.setFrameSize(new Pair<>(newWidth, newHeight));

            // Imposta le nuove coordinate delle linee nella mapPanel
            windowView.getMapPanel().setLinesInfo(cityModel.getMapModel().getLinesPointsCoordinates(), cityModel.getMapModel().getTransportNames());

            // Se sono presenti persone, imposta le nuove informazioni delle persone nella mapPanel
            if (cityModel.isPeoplePresent() && cityModel.isBusinessesPresent()){
                windowView.getMapPanel().setEntities(cityModel.getMapModel().getPersonInfos(cityModel.getAllPeople()), cityModel.getMapModel().getBusinessInfos(cityModel.getBusinesses()));
            }

            windowView.updateFrame(newWidth, newHeight);
        }
    }
}
