public class Ticket {
    private Passenger passenger;
    private Trip trip;
    private int price;
    private String destination;

    public Ticket(Passenger passenger, Trip trip, int price) {
        this.passenger = passenger;
        this.trip = trip;
        this.price = price;
        this.destination = trip.getDestination();
    }

    public Trip getTrip() {
        return trip;
    }

    public int getPrice() {
        return price;
    }

    public String getDestination() {
        return destination;
    }

    public String getPassengerId() {
        if (passenger != null) {
            return String.valueOf(passenger.getId());
        }
        return null;
    }

    public void displayInfo() {
        if (passenger != null && trip != null) {
            System.out.println("Passenger: " + passenger.getName());
            System.out.println("Trip: " + trip.getSource() + " to " + destination);
            System.out.println("Price: $" + price);
        } else {
            System.out.println("Ticket information is incomplete.");
        }
    }

    public static class TicketType {
        public static final String ONE_WAY = "OneWay";
        public static final String ROUND_TRIP = "RoundTrip";
    }
}
