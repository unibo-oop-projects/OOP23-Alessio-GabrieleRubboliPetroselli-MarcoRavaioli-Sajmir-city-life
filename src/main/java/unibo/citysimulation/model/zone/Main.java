package unibo.citysimulation.model.zone;

import unibo.citysimulation.model.transport.TransportLine;

public class Main {
    public static void main(String[] args) {
        // Create instances of TransportLine
        TransportLine Line_Central_TopRight = new TransportLine("line central-topright",100,30);
        TransportLine Line_Central_TopLeft = new TransportLine("line central-topleft ",200,35);
        TransportLine Line_Central_BottomLeft = new TransportLine("line central-bottomleft",300,25);
        TransportLine Line_Central_BottomRight = new TransportLine("line central-bottomright",400,20);

        // Create instances of Zone
        Zone centralZone = new Zone("Central Zone", null);
        Zone topRightZone = new Zone("Top Right Zone", null);
        Zone topLeftZone = new Zone("Top Left Zone", null);
        Zone bottomRightZone = new Zone("Bottom Right Zone", null);
        Zone bottomLeftZone = new Zone("Bottom Left Zone", null);

        // Create an instance of ZoneTable
        ZoneTable table = new ZoneTable();

        // Add pairs to the table with their durations
        table.addPair(centralZone, topRightZone, Line_Central_TopRight);
        table.addPair(centralZone, topLeftZone, Line_Central_TopLeft);
        table.addPair(centralZone, bottomRightZone, Line_Central_BottomRight);
        table.addPair(centralZone, bottomLeftZone, Line_Central_BottomLeft);
        

        // Retrieve and print the minutes for each pair
        System.out.println("Minutes from Central to Top Right: " + table.getMinutesForPair(centralZone, topRightZone));
        // Retrieve and print the minutes for each pair
        System.out.println("Minutes from Central to Top Left: " + table.getMinutesForPair(centralZone, topLeftZone));
        System.out.println("Minutes from Central to Bottom Right: " + table.getMinutesForPair(centralZone, bottomRightZone));
        System.out.println("Minutes from Central to Bottom Left: " + table.getMinutesForPair(centralZone, bottomLeftZone));

        // Add more print statements for other pairs
    }
}
