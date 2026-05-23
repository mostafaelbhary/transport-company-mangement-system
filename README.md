# Transport Company Management System

![Java](https://img.shields.io/badge/Java-11+-orange?style=flat-square&logo=java)
![License](https://img.shields.io/badge/License-MIT-blue?style=flat-square)
![Status](https://img.shields.io/badge/Status-Active-success?style=flat-square)

## 📋 About The Project

**  Transport Company Management System** is a comprehensive desktop application designed to streamline transportation operations. This system enables efficient management of passengers, employees, vehicles, and trips while providing user-friendly interfaces for both customers and administrative staff.

The application supports role-based access control, allowing passengers to browse and book trips, while employees (drivers and managers) can manage routes, vehicles, and operational details. All data is persistently stored in local files for easy retrieval and updates.

## 🛠️ Built With

- **Java** - Core programming language
- **Swing** - GUI framework for desktop interface
- **File I/O** - Data persistence layer
- **ArrayList** - Data structure for managing collections
- **Object-Oriented Programming** - Clean architecture and design patterns

## ✨ Features

- 🔐 **User Authentication** - Secure login system for passengers and employees
- 👥 **Passenger Management** - Registration, profile management, and booking history
- 👨‍💼 **Employee Management** - Driver and manager role assignments
- 🚗 **Vehicle Management** - Track vehicle details, capacity, and assignments
- 🛣️ **Trip Management** - Create and manage transportation routes with stops and pricing
- 🎫 **Ticket Booking System** - Passengers can browse available trips and book tickets
- 📊 **Data Persistence** - Automatic saving and loading of user and trip data
- 🎨 **Intuitive GUI** - User-friendly graphical interface for all operations
- 📁 **File-Based Database** - Local data storage with easy backup capabilities

## 🚀 Getting Started

### Prerequisites

- **Java Development Kit (JDK)** version 11 or higher
- **IDE** (IntelliJ IDEA, Eclipse, or NetBeans) or command-line compiler

### Installation

1. **Clone or download the repository**
   ```bash
   git clone https://github.com/yourusername/transport-company.git
   cd transport-company
   ```

2. **Navigate to the project directory**
   ```bash
   cd untitled12
   ```

3. **Compile the Java files**
   ```bash
   javac src/*.java -d bin
   ```

4. **Ensure data files are in the project directory**
   ```
   passenger_data.txt
   employee_data.txt
   ```

## 💻 Usage

### Running the Application

**Option 1: Using GUI (Recommended)**
```bash
java -cp bin MainGUI
```

**Option 2: Using Console Interface**
```bash
java -cp bin Main
```

### User Workflows

#### For Passengers:
1. Launch the application
2. Register a new account or login with existing credentials
3. Browse available trips
4. Select a trip and book a ticket
5. View booking history and manage reservations

#### For Employees:
1. Login with employee credentials
2. Access employee dashboard
3. Manage assigned vehicles and routes
4. Update trip information and pricing
5. Monitor operational details

### Sample Data

The application comes with pre-configured sample data:
- **Default Trip**: Alex to Cairo route (1 stop, EGP 90)
- **Default Manager**: "Boss" (ID: 12355)
- **Default Driver**: "Ekwa" (ID: 12355)

## 📁 Project Structure

```
Transport Company/
├── untitled12/
│   ├── src/
│   │   ├── Main.java              # Console-based entry point
│   │   ├── MainGUI.java           # GUI entry point
│   │   ├── MainGui.form           # GUI layout file
│   │   ├── Passenger.java         # Passenger entity
│   │   ├── Employee.java          # Employee entity
│   │   ├── Vehicle.java           # Vehicle entity
│   │   ├── Trip.java              # Trip/Route entity
│   │   ├── Ticket.java            # Ticket entity
│   │   └── User.java              # Base user class
│   ├── employee_data.txt          # Employee data storage
│   ├── passenger_data.txt         # Passenger data storage
│   └── TransportCompany.iml       # IntelliJ project file
└── README.md                       # This file
```

## 🗂️ Core Classes

- **User** - Base class for system users
- **Passenger** - Extends User; handles ticket booking and reservations
- **Employee** - Extends User; manages drivers and managers
- **Vehicle** - Represents transportation vehicles with capacity and driver assignment
- **Trip** - Manages routes with source, destination, stops, and pricing
- **Ticket** - Represents passenger bookings for specific trips
- **Main** - Console-based application controller
- **MainGUI** - Graphical user interface controller

## 🔄 Data Persistence

The application automatically:
- **Loads** passenger and employee data from files on startup
- **Saves** data modifications to files before exiting
- **Maintains** data consistency across sessions

Data files are stored in plain text format for easy debugging and manual edits if needed.

## 🛣️ Roadmap

- [ ] Database integration (MySQL/PostgreSQL)
- [ ] Email notifications for bookings
- [ ] Payment gateway integration
- [ ] Advanced reporting and analytics
- [ ] Mobile application
- [ ] Real-time GPS tracking
- [ ] Multi-language support
- [ ] User ratings and reviews system

## 🤝 Contributing

Contributions are welcome! To contribute:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

Please ensure your code follows the project's coding standards and includes appropriate documentation.

## 📝 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 👨‍💻 Author

**Mostafa Elbhary**

- GitHub: [@mostafa-elbhary](https://github.com/mostafa-elbhary)
- Email: contact@example.com

## 📞 Support

For support, questions, or bug reports, please open an issue on GitHub or contact the project author.

---

**Made with ❤️ by Mostafa Elbhary**
