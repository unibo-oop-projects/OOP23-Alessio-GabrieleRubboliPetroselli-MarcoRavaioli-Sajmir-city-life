package unibo.citysimulation.controller;

import unibo.citysimulation.view.sidePanels.ClockPanel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ClockController {
    private ClockPanel clockPanel;

    public ClockController(ClockPanel clockPanel) {
        this.clockPanel = clockPanel;
    }

    // Metodo per aggiornare l'orario nella vista
    public void updateTime(LocalDateTime currentTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedDateTime = currentTime.format(formatter);

        // Aggiornamento della vista tramite il pannello
        clockPanel.setClockText(formattedDateTime);
    }
}
