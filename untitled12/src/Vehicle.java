public class Vehicle {
    private int numOfSeats;
    public String model;
    private int year;
    private String licensePlate;
    private Employee driver;

    public Vehicle(int numOfSeats, String model, int year, String licensePlate, Employee driver) {
        if (numOfSeats <= 0) {
            throw new IllegalArgumentException("Number of seats must be positive.");
        }
        if (year < 0) {
            throw new IllegalArgumentException("Year must be a non-negative value.");
        }
        if (driver == null) {
            throw new IllegalArgumentException("Driver cannot be null.");
        }

        this.numOfSeats = numOfSeats;
        this.model = model;
        this.year = year;
        this.licensePlate = licensePlate;
        this.driver = driver;
    }
    public Vehicle(int numOfSeats, String model, int year, String licensePlate) {
        if (numOfSeats <= 0) {
            throw new IllegalArgumentException("Number of seats must be positive.");
        }
        if (year < 0) {
            throw new IllegalArgumentException("Year must be a non-negative value.");
        }
        this.numOfSeats = numOfSeats;
        this.model = model;
        this.year = year;
        this.licensePlate = licensePlate;
    }

    public int getNumOfSeats() {
        return numOfSeats;
    }

    public void setNumOfSeats(int numOfSeats) {
        if (numOfSeats <= 0) {
            throw new IllegalArgumentException("Number of seats must be positive.");
        }
        this.numOfSeats = numOfSeats;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        if (year < 0) {
            throw new IllegalArgumentException("Year must be a non-negative value.");
        }
        this.year = year;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Employee getDriver() {
        return driver;
    }

    public void setDriver(Employee driver) {
        if (driver == null) {
            throw new IllegalArgumentException("Driver cannot be null.");
        }
        this.driver = driver;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "numOfSeats=" + numOfSeats +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", licensePlate='" + licensePlate + '\'' +
                ", driver=" + driver.getName() + // Assuming Employee has a getName() method
                '}';
    }

    public void display() {
        System.out.println(this.toString());
    }
}
