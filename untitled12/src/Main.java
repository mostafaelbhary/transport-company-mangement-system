import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String PASSENGER_FILE = "passenger_data.txt";
    private static final String EMPLOYEE_FILE = "employee_data.txt";
    private static List<Passenger> passengers = new ArrayList<>();
    private static List<Employee> employees = new ArrayList<>();
    private static List<Trip> availableTrips = new ArrayList<>();

    public static void main(String[] args) {
        loadData(); // Load existing data from files

        Scanner sc = new Scanner(System.in);
        Employee Manager=new Employee("Boss","12355","Manager",EmployeeType.MANAGER);
        Employee e1=new Employee("Ekwa","12355","aHMED Ekwa",EmployeeType.DRIVER);
        Vehicle v1 = new Vehicle(5,"Lemozin",2010,"A",e1);
        Trip t1 = new Trip("Alex","Cairo",1,90,v1);
        availableTrips.add(t1);
        saveData();
        System.out.println("Welcome to the Transportation System!");

        while (true) {
            System.out.println("\nAre you a PASSENGER or an EMPLOYEE?");
            System.out.println("1. PASSENGER\n2. EMPLOYEE\n3. EXIT");

            int userType = sc.nextInt();

            switch (userType) {
                case 1:
                    handlePassenger(sc);
                    break;
                case 2:
                    handleEmployee(sc);
                    break;
                case 3:
                    System.out.println("Exiting the program...");
                    saveData(); // Save updated data to files before exiting
                    sc.close(); // Close the scanner before exiting
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static void handlePassenger(Scanner sc) {
        System.out.println("\nWelcome, PASSENGER");
        System.out.println("1. REGISTER\n2. LOGIN");

        int passengerChoice = sc.nextInt();

        switch (passengerChoice) {
            case 1:
                registerPassenger(sc);
                break;
            case 2:
                loginPassenger(sc);
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    private static void registerPassenger(Scanner sc) {
        System.out.println("\nEnter Username:");
        String username = sc.next();
        System.out.println("Enter Password:");
        String password = sc.next();
        System.out.println("Enter Full Name:");
        String name = sc.next();

        Passenger newPassenger = new Passenger(username, password, name);
        passengers.add(newPassenger);
        System.out.println("Registration Successful.");
    }

    private static void loginPassenger(Scanner sc) {
        System.out.println("\nEnter Username:");
        String username = sc.next();
        System.out.println("Enter Password:");
        String password = sc.next();

        Passenger currentPassenger = findPassenger(username, password);

        if (currentPassenger != null) {
            System.out.println("Login Successful.");
            handlePassengerActions(sc, currentPassenger);
        } else {
            System.out.println("Invalid Username or Password. Please try again.");
        }
    }

    private static Passenger findPassenger(String username, String password) {
        for (Passenger passenger : passengers) {
            if (passenger.getUsername().equals(username) && passenger.getPassword().equals(password)) {
                return passenger;
            }
        }
        return null;
    }

    private static void handlePassengerActions(Scanner sc, Passenger passenger) {
        while (true) {
            System.out.println("\nWhat would you like to do?");
            System.out.println("1. View Available Trips\n2. Book a Trip\n3. Logout");

            int actionChoice = sc.nextInt();

            switch (actionChoice) {
                case 1:
                    displayAvailableTrips();
                    break;
                case 2:
                    passenger.selectTrip(availableTrips);

                    break;
                case 3:
                    System.out.println("Logging out...");

                    return;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
    }

    private static void displayAvailableTrips() {
        System.out.println("Available Trips:");
        for (int i = 0; i < availableTrips.size(); i++) {
            System.out.println((i + 1) + ". " + availableTrips.get(i).getDestination() + " PRICE = " + availableTrips.get(i).getPrice());
        }
    }
    private static void handleEmployee(Scanner sc) {
        System.out.println("\nWelcome, EMPLOYEE");
        System.out.println("1. REGISTER\n2. LOGIN");

        int employeeChoice = sc.nextInt();

        switch (employeeChoice) {
            case 1:
                registerEmployee(sc);
                break;
            case 2:
                loginEmployee(sc);
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }


    private static void registerEmployee(Scanner sc) {
        System.out.println("\nEnter Username:");
        String username = sc.next();
        System.out.println("Enter Password:");
        String password = sc.next();
        System.out.println("Enter Full Name:");
        String name = sc.next();
        System.out.println("Enter Employee Type (DRIVER or MANAGER):");
        String typeInput = sc.next();

        EmployeeType type;
        try {
            type = EmployeeType.valueOf(typeInput.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid employee type. Defaulting to DRIVER.");
            type = EmployeeType.DRIVER;
        }

        Employee newEmployee = new Employee(username, password, name, type);
        employees.add(newEmployee);
        System.out.println("Registration Successful.");
    }

    private static void loginEmployee(Scanner sc) {
        System.out.println("\nEnter Username:");
        String username = sc.next();
        System.out.println("Enter Password:");
        String password = sc.next();

        Employee currentEmployee = findEmployee(username, password);

        if (currentEmployee != null) {
            System.out.println("Login Successful.");
            currentEmployee.displayProfile();
            // Handle employee actions based on type (not implemented in this example)
        } else {
            System.out.println("Invalid Username or Password. Please try again.");
        }
    }

    private static Employee findEmployee(String username, String password) {
        for (Employee employee : employees) {
            if (employee.getUsername().equals(username) && employee.getPassword().equals(password)) {
                return employee;
            }
        }
        return null;
    }


    private static void loadData() {
        try (BufferedReader passengerReader = new BufferedReader(new FileReader(PASSENGER_FILE));
             BufferedReader employeeReader = new BufferedReader(new FileReader(EMPLOYEE_FILE))) {

            String line;
            while ((line = passengerReader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    String username = parts[0];
                    String password = parts[1];
                    String name = parts[2];
                    Passenger passenger = new Passenger(username, password, name);
                    passengers.add(passenger);
                }
            }

            while ((line = employeeReader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    String username = parts[0];
                    String password = parts[1];
                    String name = parts[2];
                    EmployeeType type;
                    try {
                        type = EmployeeType.valueOf(parts[3].toUpperCase());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid employee type found in data.");
                        continue; // Skip this invalid entry
                    }
                    Employee employee = new Employee(username, password, name, type);
                    employees.add(employee);
                }
            }

        } catch (IOException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }

    private static void saveData() {
        try (BufferedWriter passengerWriter = new BufferedWriter(new FileWriter(PASSENGER_FILE));
             BufferedWriter employeeWriter = new BufferedWriter(new FileWriter(EMPLOYEE_FILE))) {

            for (Passenger passenger : passengers) {
                String line = String.join(",", passenger.getUsername(), passenger.getPassword(), passenger.getName());
                passengerWriter.write(line);
                passengerWriter.newLine();
            }

            for (Employee employee : employees) {
                String line = String.join(",", employee.getUsername(), employee.getPassword(),
                        employee.getName(), employee.getType().toString());
                employeeWriter.write(line);
                employeeWriter.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }
}
