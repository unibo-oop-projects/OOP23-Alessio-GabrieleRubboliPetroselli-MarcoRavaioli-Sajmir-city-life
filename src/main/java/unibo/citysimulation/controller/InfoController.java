package unibo.citysimulation.controller;

import unibo.citysimulation.model.CityModel;
import unibo.citysimulation.view.sidePanels.InfoPanel;

public class InfoController {
    private CityModel cityModel;
    private InfoPanel infoPanel;

    public InfoController(CityModel cityModel, InfoPanel infoPanel) {
        this.infoPanel = infoPanel;
        this.cityModel = cityModel;

        infoPanel.updateNumberOfZones(cityModel.getZones().size());
        infoPanel.updateNumberOfTransportLines(cityModel.getTransportLines().size());
    }


}
