package unibo.citysimulation.model.transport;

public class Main {
    public static void main(String[] args) {
        // Create instances of TransportLine
        TransportLine centralLine = new TransportLine("Central Line", 5, 8, 20, 100);
        TransportLine topRightLine = new TransportLine("Top Right Line", 10, 9, 18, 200);
        TransportLine topLeftLine = new TransportLine("Top Left Line", 15, 7, 22, 200);
        TransportLine bottomRightLine = new TransportLine("Bottom Right Line", 5, 6, 21, 200);
        TransportLine bottomLeftLine = new TransportLine("Bottom Left Line", 5, 4, 22, 300);

        // Create instances of Zone
        Zone centralZone = new Zone("Central Zone", centralLine);
        Zone topRightZone = new Zone("Top Right Zone", topRightLine);
        Zone topLeftZone = new Zone("Top Left Zone", topLeftLine);
        Zone bottomRightZone = new Zone("Bottom Right Zone", bottomRightLine);
        Zone bottomLeftZone = new Zone("Bottom Left Zone", bottomLeftLine);

        // Create an instance of ZoneTable
        ZoneTable table = new ZoneTable();

        // Add pairs to the table with their durations
        table.addPair(centralZone, topRightZone, 30);
        table.addPair(centralZone, topLeftZone, 35);
        table.addPair(centralZone, bottomRightZone, 40);
        table.addPair(centralZone, bottomLeftZone, 45);
        table.addPair(topRightZone, topLeftZone, 50);
        table.addPair(topRightZone, bottomRightZone, 55);
        table.addPair(topRightZone, bottomLeftZone, 60);
        table.addPair(topLeftZone, bottomRightZone, 65);
        table.addPair(topLeftZone, bottomLeftZone, 70);
        table.addPair(bottomRightZone, bottomLeftZone, 75);

        // Retrieve and print the minutes for each pair
        System.out.println("Minutes from Central to Top Right: " + table.getMinutesForPair(centralZone, topRightZone));
        // Retrieve and print the minutes for each pair
        System.out.println("Minutes from Central to Top Left: " + table.getMinutesForPair(centralZone, topLeftZone));
        System.out.println("Minutes from Central to Bottom Right: " + table.getMinutesForPair(centralZone, bottomRightZone));
        System.out.println("Minutes from Central to Bottom Left: " + table.getMinutesForPair(centralZone, bottomLeftZone));
        System.out.println("Minutes from Top Right to Top Left: " + table.getMinutesForPair(topRightZone, topLeftZone));
        System.out.println("Minutes from Top Right to Bottom Right: " + table.getMinutesForPair(topRightZone, bottomRightZone));
        System.out.println("Minutes from Top Right to Bottom Left: " + table.getMinutesForPair(topRightZone, bottomLeftZone));
        System.out.println("Minutes from Top Left to Bottom Right: " + table.getMinutesForPair(topLeftZone, bottomRightZone));
        System.out.println("Minutes from Top Left to Bottom Left: " + table.getMinutesForPair(topLeftZone, bottomLeftZone));
        System.out.println("Minutes from Bottom Right to Bottom Left: " + table.getMinutesForPair(bottomRightZone, bottomLeftZone));

        // Add more print statements for other pairs
    }
}
