package unibo.citysimulation.model.zone;

import unibo.citysimulation.model.transport.TransportLineImpl;

public class Main {
    public static void main(String[] args) {
        // Create instances of TransportLine
        TransportLineImpl Line_Central_TopRight = new TransportLineImpl("line central-topright",100,30);
        TransportLineImpl Line_Central_TopLeft = new TransportLineImpl("line central-topleft ",200,35);
        TransportLineImpl Line_Central_BottomLeft = new TransportLineImpl("line central-bottomleft",300,25);
        TransportLineImpl Line_Central_BottomRight = new TransportLineImpl("line central-bottomright",400,20);

        // Create instances of Zone
        ZoneImpl centralZone = new ZoneImpl("Central Zone");
        ZoneImpl topRightZone = new ZoneImpl("Top Right Zone");
        ZoneImpl topLeftZone = new ZoneImpl("Top Left Zone");
        ZoneImpl bottomRightZone = new ZoneImpl("Bottom Right Zone");
        ZoneImpl bottomLeftZone = new ZoneImpl("Bottom Left Zone");

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
