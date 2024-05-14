package unibo.citysimulation.model.clock;

import java.time.LocalTime;
import java.util.List;

import unibo.citysimulation.model.transport.TransportLine;
import unibo.citysimulation.view.map.MapPanel;

public class ClockObserverTransport implements ClockObserver {
    private List<TransportLine> transportLines;
    private MapPanel mapPanel;

    public ClockObserverTransport(List<TransportLine> transportLines,MapPanel mapPanel) {
        this.transportLines = transportLines;
        this.mapPanel=mapPanel;
    }

    @Override
    public void onTimeUpdate(LocalTime currentTime, int currentDay) {
        //mapPanel.updateTransportLines(transportLines);
    }
    
}
