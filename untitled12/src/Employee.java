import java.util.List;
import java.util.Scanner;

public class Employee extends User {
    private EmployeeType type;
    private static int counter = 1;
    private String id;

    public Employee(String username, String password, String name, EmployeeType type) {
        super(username, password, name);
        this.type = type;
        this.id = generateEmployeeId();
    }
    public Employee(String username, String password){
        super(username,password);
    }

    public Employee(String username, String password, String name) {
        super(username, password, name);
        this.type = EmployeeType.DRIVER;
        this.id = generateEmployeeId();
    }

    private String generateEmployeeId() {
        String prefix = "EMP";
        String paddedCounter = String.format("%03d", counter);
        counter++;
        return prefix + paddedCounter;
    }

    public EmployeeType getType() {
        return type;
    }

    public void setType(EmployeeType type) {
        this.type = type;
    }

    public void displayProfile() {
        System.out.println("Employee Profile:");
        System.out.println("Name: " + getName());
        System.out.println("ID: " + getId());
        System.out.println("Type: " + getType());
    }

    public void viewAssignedTrips(List<Trip> assignedTrips) {
        System.out.println("Assigned Trips:");
        for (Trip trip : assignedTrips) {
            System.out.println(trip.getSource() + " to " + trip.getDestination() + ", Price: $" + trip.getPrice());
        }
    }

    public void manageTrips(Trip tripToUpdate, Employee newDriver) {
        if (tripToUpdate == null || newDriver == null) {
            System.out.println("Invalid trip or driver. Please provide valid inputs.");
            return;
        }

        tripToUpdate.getVehicle().setDriver(newDriver);
        System.out.println("Trip managed successfully. New driver assigned: " + newDriver.getName());
    }

    public void addVehicle(int numOfSeats, String model, int year, String licensePlate) {
        try {
            Vehicle newVehicle = new Vehicle(numOfSeats, model, year, licensePlate, this);
            System.out.println("New vehicle added successfully: " + newVehicle.getModel());
        } catch (IllegalArgumentException e) {
            System.out.println("Failed to add vehicle: " + e.getMessage());
        }
    }

    public void addEmployee(String username, String password, String name, EmployeeType type) {
        try {
            Employee newEmployee = new Employee(username, password, name, type);
            System.out.println("New employee added successfully: " + newEmployee.getName());
        } catch (IllegalArgumentException e) {
            System.out.println("Failed to add employee: " + e.getMessage());
        }
    }

    public void generateReport(List<Trip> trips) {
        System.out.println("Trip Report:");
        for (Trip trip : trips) {
            System.out.println("Source: " + trip.getSource() + ", Destination: " + trip.getDestination() +
                    ", Price: $" + trip.getPrice() + ", Number of Stops: " + trip.getNumberOfStops());
        }
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + getName() + '\'' +
                ", id='" + getId() + '\'' +
                ", type=" + type +
                '}';
    }

    public String getId() {
        return id;
    }
}

enum EmployeeType {
    DRIVER, MANAGER
}
