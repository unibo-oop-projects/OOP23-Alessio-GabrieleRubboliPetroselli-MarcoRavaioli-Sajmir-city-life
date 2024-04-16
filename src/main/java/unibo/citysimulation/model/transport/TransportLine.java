package unibo.citysimulation.model.transport;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a transport line within the city simulation.
 */
public class TransportLine {
    private String name;
    private List<Transport> vehicles;
    private int serviceFrequency; // Frequency of service in minutes
    private int startHour; // Starting hour of service
    private int endHour; // Ending hour of service

    public TransportLine(String name, int serviceFrequency, int startHour, int endHour) {
        this.name = name;
        this.serviceFrequency = serviceFrequency;
        this.startHour = startHour;
        this.endHour = endHour;
        this.vehicles = new ArrayList<>();
    }

    // Getter and setter methods for other fields, if needed

    /**
     * Adds a vehicle to the transport line.
     * 
     * @param vehicle The vehicle to add.
     */
    public void addVehicle(Transport vehicle) {
        vehicles.add(vehicle);
    }

    /**
     * Removes a vehicle from the transport line.
     * 
     * @param vehicle The vehicle to remove.
     * @return true if the vehicle was successfully removed, false otherwise.
     */
    public boolean removeVehicle(Transport vehicle) {
        return vehicles.remove(vehicle);
    }

    /**
     * Calculates the average congestion level of the vehicles in the transport line.
     * 
     * @return The average congestion level.
     */
    public double calculateAverageCongestion() {
        if (vehicles.isEmpty()) {
            return 0.0; // Return 0 if there are no vehicles in the line
        }

        int totalCongestion = 0;
        for (Transport vehicle : vehicles) {
            totalCongestion += vehicle.getCongestion();
        }

        return (double) totalCongestion / vehicles.size();
    }
}
