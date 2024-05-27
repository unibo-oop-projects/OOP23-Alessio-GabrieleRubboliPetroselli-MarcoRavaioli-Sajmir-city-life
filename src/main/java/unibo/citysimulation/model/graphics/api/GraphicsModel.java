package unibo.citysimulation.model.graphics.api;

import org.jfree.data.xy.XYSeriesCollection;
import unibo.citysimulation.model.business.impl.Business;
import unibo.citysimulation.model.person.api.DynamicPerson;
import unibo.citysimulation.model.transport.TransportLine;

import java.awt.Color;
import java.util.List;

public interface GraphicsModel {
    void clearDatasets();
    void updateDataset(List<DynamicPerson> people, List<TransportLine> lines, List<Business> businesses);
    List<XYSeriesCollection> getDatasets();
    List<String> getNames();
    List<Color> getColors();
}
