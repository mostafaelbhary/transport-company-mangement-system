import java.util.ArrayList;
import java.util.List;

public class Trip {
    private String source;
    private String destination;
    private int numberOfStops;
    private double price;
    private Vehicle vehicle;
    private List<Trip> availableTrips;

    public Trip(String source, String destination, int numberOfStops, double price, Vehicle vehicle) {
        this.source = source;
        this.destination = destination;
        this.numberOfStops = numberOfStops;
        this.price = price;
        this.vehicle = vehicle;
        this.availableTrips = new ArrayList<>();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getNumberOfStops() {
        return numberOfStops;
    }

    public void setNumberOfStops(int numberOfStops) {
        this.numberOfStops = numberOfStops;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public List<Trip> getAvailableTrips() {
        return availableTrips;
    }

    public void setAvailableTrips(List<Trip> availableTrips) {
        this.availableTrips = availableTrips;
    }

    public void addAvailableTrip(Trip trip) {
        this.availableTrips.add(trip);
    }
}
