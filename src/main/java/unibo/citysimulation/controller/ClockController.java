package unibo.citysimulation.controller;

import unibo.citysimulation.view.sidePanels.ClockPanel;

public class ClockController {
    private ClockPanel clockPanel;

    public ClockController(ClockPanel clockPanel) {
        this.clockPanel = clockPanel;
    }

    // Metodo per aggiornare l'orario nella vista
    public void updateTime(String currentTime, int currentDay) {

        clockPanel.setClockText("Giorno: " + currentDay + " ora: " + currentTime);

    }
}
