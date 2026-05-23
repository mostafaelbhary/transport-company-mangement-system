import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Passenger extends User {
    private TicketType ticketType;
    private Trip trip;
    private static int counter = 1;
    private String id;
    private ArrayList<Trip> bookedTrips;

    public Passenger(String username, String password, String name, TicketType ticketType) {
        super(username, password, name);
        this.ticketType = ticketType;
        this.id = "24" + counter++;
        this.bookedTrips = new ArrayList<>();
    }


    public Passenger(String username, String password, String name) {
        super(username, password, name);
    }


    public TicketType getTicketType() {
        return ticketType;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    public String getId() {
        return id;
    }

    public void displayProfile() {
        System.out.println("Passenger Profile:");
        System.out.println("Name: " + getName());
        System.out.println("ID: " + getId());
        System.out.println("Ticket Type: " + getTicketType());
    }


    public void selectTrip(List<Trip> availableTrips) {
        System.out.println("Available Trips:");
        for (int i = 0; i < availableTrips.size(); i++) {
            System.out.println((i + 1) + ". " + availableTrips.get(i).getDestination()+" PRICE = "+availableTrips.get(i).getPrice());
        }

        System.out.println("Enter the trip number you want to select:");
        try {
            int tripNumber = readIntegerInput();
            if (tripNumber >= 1 && tripNumber <= availableTrips.size()) {
                this.trip = availableTrips.get(tripNumber - 1);
                System.out.println("Trip selected successfully. Your destination is: " + trip.getDestination());
            } else {
                throw new IllegalArgumentException("Invalid trip selection. Please try again.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void bookTicket() {
        if (trip == null) {
            System.out.println("Please select a trip first.");
            return;
        }

        try {
            int ticketPrice = calculateTicketPrice(trip);
            Ticket newTicket = new Ticket(this, trip, ticketPrice);
            bookedTrips.add(trip);
            System.out.println("Ticket booked successfully.");
        } catch (Exception e) {
            System.out.println("Failed to book ticket: " + e.getMessage());
        }
    }

    public void reviewTickets() {
        System.out.println("Booked Tickets:");
        for (int i = 0; i < bookedTrips.size(); i++) {
            System.out.println((i + 1) + ". " + bookedTrips.get(i).getDestination());
        }
    }

    public void cancelTicket() {
        reviewTickets();

        System.out.println("Enter the ticket number you want to cancel:");
        try {
            int ticketNumber = readIntegerInput();
            if (ticketNumber >= 1 && ticketNumber <= bookedTrips.size()) {
                bookedTrips.remove(ticketNumber - 1);
                System.out.println("Ticket cancelled successfully.");
            } else {
                throw new IllegalArgumentException("Invalid ticket number. Please try again.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private int calculateTicketPrice(Trip trip) throws Exception {
        int basePrice = (int) trip.getPrice();
        if (ticketType == TicketType.ROUND_TRIP) {
            return basePrice * 2;
        } else if (ticketType == TicketType.ONE_WAY) {
            return basePrice;
        } else {
            throw new Exception("Invalid ticket type.");
        }
    }

    private int readIntegerInput() throws NumberFormatException {
        Scanner sc = new Scanner(System.in);
        return Integer.parseInt(sc.nextLine());
    }

    public enum TicketType {
        ONE_WAY, ROUND_TRIP
    }
}
