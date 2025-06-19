# Laptop Rental Management System

A comprehensive Java-based system for managing laptop rentals in educational institutions. This system provides functionality for both administrators and students to manage laptop inventory, bookings, and returns.

## Project Structure

```
com.laptop.rental
├── model
│   ├── User.java          # Base user class with authentication
│   ├── Student.java       # Student-specific model
│   ├── Admin.java         # Admin-specific model
│   ├── Laptop.java        # Laptop inventory model
│   └── Booking.java       # Booking/rental model
├── repository
│   ├── UserRepository.java    # User data persistence
│   ├── StudentRepository.java # Student data persistence
│   ├── LaptopRepository.java  # Laptop data persistence
│   └── BookingRepository.java # Booking data persistence
├── service
│   ├── AuthService.java       # Authentication and authorization
│   ├── StudentService.java    # Student management business logic
│   ├── LaptopService.java     # Laptop management business logic
│   └── BookingService.java    # Booking management business logic
└── Main.java                  # Main application entry point
```

## Features

### Admin Features
- **Student Management**: Add, view, update, and delete student records
- **Laptop Management**: Add, view, update, and delete laptop inventory
- **Booking Overview**: View all active bookings and system statistics
- **Overdue Tracking**: Monitor overdue laptop returns
- **System Statistics**: View total revenue, laptop availability, etc.

### Student Features
- **Browse Available Laptops**: View all available laptops with specifications
- **Book Laptops**: Reserve laptops for specific durations
- **Return Laptops**: Return borrowed laptops
- **View Bookings**: Check personal booking history
- **Extend Bookings**: Extend current booking duration

## Technical Implementation

### Design Patterns Used
1. **Repository Pattern**: Separates data access logic from business logic
2. **Service Layer Pattern**: Encapsulates business logic and provides clean API
3. **Model-View-Controller (MVC)**: Separates data models from user interface
4. **Object-Oriented Design**: Proper encapsulation, inheritance, and polymorphism

### Data Persistence
- **File-based Storage**: Uses text files for data persistence
- **Structured Data Format**: Consistent formatting for easy parsing
- **Automatic File Creation**: Creates data files on first use

### Key Classes and Their Responsibilities

#### Model Classes
- **User**: Base class for authentication and role management
- **Student**: Extends user functionality with student-specific attributes
- **Admin**: Extends user functionality with admin-specific attributes
- **Laptop**: Represents laptop inventory with specifications and availability
- **Booking**: Manages rental transactions with timing and billing

#### Repository Classes
- **UserRepository**: Handles user account data persistence
- **StudentRepository**: Manages student profile data
- **LaptopRepository**: Handles laptop inventory data
- **BookingRepository**: Manages booking and rental records

#### Service Classes
- **AuthService**: Handles user authentication and registration
- **StudentService**: Provides student management business logic
- **LaptopService**: Manages laptop inventory operations
- **BookingService**: Handles booking and rental business logic

## Setup and Installation

### Prerequisites
- Java 8 or higher
- Any Java IDE (Eclipse, IntelliJ IDEA, NetBeans, etc.)

### Running the Application
1. **Compile the Project**:
   ```bash
   javac -d . com/laptop/rental/*.java com/laptop/rental/*/*.java
   ```

2. **Run the Application**:
   ```bash
   java com.laptop.rental.Main
   ```

### Default Admin Account
- **Username**: admin
- **Password**: admin123

## Data Files

The system automatically creates the following data files:

- `users.txt`: User accounts and authentication data
- `students.txt`: Student profiles and information
- `laptops.txt`: Laptop inventory and specifications
- `bookings.txt`: Booking and rental records

## Usage Examples

### Adding a New Student (Admin)
1. Login as admin
2. Select "Manage Students" → "Add New Student"
3. Enter student details (ID, name, email, phone, department)

### Booking a Laptop (Student)
1. Login as student
2. Select "View Available Laptops"
3. Select "Book a Laptop"
4. Enter student ID, laptop ID, and duration

### Viewing System Statistics (Admin)
1. Login as admin
2. Select "View System Statistics"
3. View total laptops, available laptops, and revenue

## Error Handling

The system includes comprehensive error handling for:
- **File I/O Errors**: Graceful handling of file operations
- **Invalid Input**: Input validation and user-friendly error messages
- **Data Consistency**: Checks for duplicate IDs and invalid references
- **Business Logic Errors**: Proper validation of booking rules

## Extensibility

The system is designed to be easily extensible:
- **New User Types**: Can add new user roles by extending the User class
- **Additional Features**: Service layer makes it easy to add new functionality
- **Database Integration**: Repository pattern allows easy migration to database storage
- **GUI Interface**: Current console interface can be replaced with GUI

## Testing

### Sample Data
Use the provided sample data in `sample_data.txt` to test the system:
- Sample students with different departments
- Various laptop models with different specifications
- Different pricing tiers and conditions

### Test Scenarios
1. **Admin Operations**: Add students, manage laptops, view statistics
2. **Student Operations**: Book laptops, view availability, return laptops
3. **Edge Cases**: Overdue bookings, duplicate IDs, invalid inputs
4. **Data Persistence**: Verify data is saved and loaded correctly

## Future Enhancements

Potential improvements for future versions:
- **Database Integration**: Replace file storage with SQL database
- **GUI Interface**: Develop graphical user interface
- **Email Notifications**: Send reminders for overdue laptops
- **Payment Integration**: Online payment processing
- **Reporting**: Advanced reporting and analytics
- **Mobile App**: Develop mobile application for students

## Contributing

This project demonstrates Object-Oriented Programming principles and can be extended for educational purposes or real-world implementation.

## License

This project is created for educational purposes as part of an OOP lab assignment. 