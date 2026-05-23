import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MainGUI extends JFrame {
    private static final String PASSENGER_FILE = "passenger_data.txt";
    private static final String EMPLOYEE_FILE = "employee_data.txt";
    private static List<Passenger> passengers = new ArrayList<>();
    private static List<Employee> employees = new ArrayList<>();
    private static List<Trip> availableTrips = new ArrayList<>();
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;

    public MainGUI() {
        setTitle("Transport Company Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(20);
        JPanel usernamePanel = new JPanel();
        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);
        JPanel passwordPanel = new JPanel();
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);

        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                if (login(username, password)) {
                    JOptionPane.showMessageDialog(MainGUI.this, "Login Successful");
                    showDashboard(username);
                } else {
                    JOptionPane.showMessageDialog(MainGUI.this, "Invalid Username or Password");
                }
            }
        });

        registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                register(username, password);
                JOptionPane.showMessageDialog(MainGUI.this, "Registration Successful");
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);

        mainPanel.add(usernamePanel);
        mainPanel.add(passwordPanel);
        mainPanel.add(buttonPanel);

        add(mainPanel);
        setVisible(true);

        loadData(); // Load existing data from files
    }

    private boolean login(String username, String password) {
        for (Passenger passenger : passengers) {
            if (passenger.getUsername().equals(username) && passenger.getPassword().equals(password)) {
                return true;
            }
        }
        for (Employee employee : employees) {
            if (employee.getUsername().equals(username) && employee.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }


    private void showDashboard(String username) {
        for (Passenger passenger : passengers) {
            if (passenger.getUsername().equals(username)) {
                // Show passenger dashboard
                showPassengerDashboard(passenger);
                return;
            }
        }
        for (Employee employee : employees) {
            if (employee.getUsername().equals(username)) {
                // Show employee dashboard
                showEmployeeDashboard(employee);
                return;
            }
        }
    }

    private void showPassengerDashboard(Passenger passenger) {
        JFrame passengerDashboard = new JFrame("Passenger Dashboard");
        passengerDashboard.setSize(600, 400);
        passengerDashboard.setLocationRelativeTo(null);

        JLabel welcomeLabel = new JLabel("Welcome, " + passenger.getName() + "!");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel passengerPanel = new JPanel();
        passengerPanel.setLayout(new BoxLayout(passengerPanel, BoxLayout.Y_AXIS));
        passengerPanel.add(welcomeLabel);

        passengerDashboard.add(passengerPanel);
        passengerDashboard.setVisible(true);
    }

    private void showEmployeeDashboard(Employee employee) {
        // Example: Open a new window to display employee dashboard based on type
        if (employee.getType() == EmployeeType.DRIVER) {
            JFrame driverDashboard = new JFrame("Driver Dashboard");
            driverDashboard.setSize(600, 400);
            driverDashboard.setLocationRelativeTo(null);

            JLabel welcomeLabel = new JLabel("Welcome, Driver " + employee.getName() + "!");
            welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);

            JPanel driverPanel = new JPanel();
            driverPanel.setLayout(new BoxLayout(driverPanel, BoxLayout.Y_AXIS));
            driverPanel.add(welcomeLabel);

            driverDashboard.add(driverPanel);
            driverDashboard.setVisible(true);
        } else if (employee.getType() == EmployeeType.MANAGER) {
            // Handle manager dashboard
            JOptionPane.showMessageDialog(MainGUI.this, "Welcome, Manager " + employee.getName() + "!");
        }
    }

    private void register(String username, String password) {
        // Check if the username is already taken
        for (Passenger existingPassenger : passengers) {
            if (existingPassenger.getUsername().equals(username)) {
                JOptionPane.showMessageDialog(this, "Username already taken. Please choose a different one.", "Registration Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        // Check if the password meets security criteria (e.g., minimum length)
        if (password.length() < 8) {
            JOptionPane.showMessageDialog(this, "Password must be at least 8 characters long.", "Registration Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // If checks pass, create a new passenger and add to the list
        Passenger newPassenger = new Passenger(username, password, "New Passenger");
        passengers.add(newPassenger);

        // Save the updated passengers list to the file
        saveData();

        JOptionPane.showMessageDialog(this, "Registration Successful", "Success", JOptionPane.INFORMATION_MESSAGE);
    }


    private void loadData() {
        // Load existing data from files
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
                    EmployeeType type = EmployeeType.valueOf(parts[3].toUpperCase());
                    Employee employee = new Employee(username, password, name, type);
                    employees.add(employee);
                }
            }

        } catch (IOException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }
    private void saveData() {
        // Save data to files
        try {
            savePassengerData();
            saveEmployeeData();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void savePassengerData() throws IOException {
        try (BufferedWriter passengerWriter = new BufferedWriter(new FileWriter(PASSENGER_FILE))) {
            for (Passenger passenger : passengers) {
                passengerWriter.write(passenger.getUsername() + "," + passenger.getPassword() + "," + passenger.getName());
                passengerWriter.newLine();
            }
        }
    }

    private void saveEmployeeData() throws IOException {
        try (BufferedWriter employeeWriter = new BufferedWriter(new FileWriter(EMPLOYEE_FILE))) {
            for (Employee employee : employees) {
                employeeWriter.write(employee.getUsername() + "," + employee.getPassword() + "," + employee.getName() + "," + employee.getType());
                employeeWriter.newLine();
            }
        }
    }
    private void loadPassengerData() throws IOException {
        try (BufferedReader passengerReader = new BufferedReader(new FileReader(PASSENGER_FILE))) {
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
        }
    }

    private void loadEmployeeData() throws IOException {
        try (BufferedReader employeeReader = new BufferedReader(new FileReader(EMPLOYEE_FILE))) {
            String line;
            while ((line = employeeReader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    String username = parts[0];
                    String password = parts[1];
                    String name = parts[2];
                    EmployeeType type = EmployeeType.valueOf(parts[3].toUpperCase());
                    Employee employee = new Employee(username, password, name, type);
                    employees.add(employee);
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainGUI();
            }
        });
    }
}
