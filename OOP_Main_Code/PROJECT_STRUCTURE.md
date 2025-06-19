# Complete Project Structure - Laptop Rental Management System

## Directory Structure
```
OOP_Main_Code/
├── model/
│   ├── User.java              # Base user class with authentication
│   ├── Student.java           # Student model with extended attributes
│   ├── Admin.java             # Admin model extending User
│   ├── Laptop.java            # Laptop inventory model
│   └── Booking.java           # Booking/rental transaction model
├── repository/
│   ├── UserRepository.java    # User data persistence layer
│   ├── StudentRepository.java # Student data persistence layer
│   ├── LaptopRepository.java  # Laptop data persistence layer
│   └── BookingRepository.java # Booking data persistence layer
├── service/
│   ├── AuthService.java       # Authentication and authorization service
│   ├── StudentService.java    # Student management business logic
│   ├── LaptopService.java     # Laptop management business logic
│   └── BookingService.java    # Booking management business logic
├── Main.java                  # Main application entry point
├── README.md                  # Project documentation
├── sample_data.txt            # Sample data for testing
├── run.bat                    # Windows execution script
├── run.sh                     # Unix/Linux execution script
└── PROJECT_STRUCTURE.md       # This file
```

## Class Details

### Model Classes (5 files)

#### 1. User.java
- **Purpose**: Base class for all users in the system
- **Key Features**:
  - Username and password authentication
  - Role-based access control (0=Admin, 1=Student)
  - Helper methods for role checking
  - Proper encapsulation with getters/setters

#### 2. Student.java
- **Purpose**: Represents student users with extended attributes
- **Key Features**:
  - Student ID, name, email, phone, department
  - Multiple constructors for flexibility
  - Complete getter/setter methods
  - toString() method for display

#### 3. Admin.java
- **Purpose**: Represents admin users with admin-specific attributes
- **Key Features**:
  - Extends User class
  - Admin ID and email fields
  - Admin-specific functionality

#### 4. Laptop.java
- **Purpose**: Represents laptop inventory items
- **Key Features**:
  - ID, brand, model, specifications
  - Availability status
  - Hourly rental rate
  - Condition tracking (New/Good/Fair/Poor)
  - Multiple constructors

#### 5. Booking.java
- **Purpose**: Represents rental transactions
- **Key Features**:
  - Student and laptop references
  - Booking and return dates
  - Duration and pricing
  - Status tracking (Active/Returned/Overdue)
  - Automatic total calculation

### Repository Classes (4 files)

#### 1. UserRepository.java
- **Purpose**: Handles user account data persistence
- **Key Features**:
  - File-based storage (users.txt)
  - Save and retrieve user accounts
  - Structured data format

#### 2. StudentRepository.java
- **Purpose**: Manages student profile data persistence
- **Key Features**:
  - File-based storage (students.txt)
  - CRUD operations for students
  - Search by ID and name
  - Data validation

#### 3. LaptopRepository.java
- **Purpose**: Handles laptop inventory data persistence
- **Key Features**:
  - File-based storage (laptops.txt)
  - Find available laptops
  - Search by brand
  - Update availability status

#### 4. BookingRepository.java
- **Purpose**: Manages booking and rental records
- **Key Features**:
  - File-based storage (bookings.txt)
  - Date formatting and parsing
  - Find bookings by student/laptop
  - Mark bookings as returned
  - Track active bookings

### Service Classes (4 files)

#### 1. AuthService.java
- **Purpose**: Handles user authentication and registration
- **Key Features**:
  - Login validation
  - User registration
  - Role-based access

#### 2. StudentService.java
- **Purpose**: Provides student management business logic
- **Key Features**:
  - Student registration
  - Profile updates
  - Password changes
  - Student authentication

#### 3. LaptopService.java
- **Purpose**: Manages laptop inventory operations
- **Key Features**:
  - Add/update/delete laptops
  - Availability management
  - Rate and condition updates
  - Inventory statistics

#### 4. BookingService.java
- **Purpose**: Handles booking and rental business logic
- **Key Features**:
  - Book and return laptops
  - Booking extensions
  - Revenue calculation
  - Overdue tracking
  - Booking cancellation

### Main Application (1 file)

#### Main.java
- **Purpose**: Main application entry point and user interface
- **Key Features**:
  - Console-based menu system
  - Admin and student interfaces
  - Complete CRUD operations
  - System statistics
  - Error handling
  - User-friendly prompts

## Data Files (Auto-generated)

The system automatically creates these data files when first used:

1. **users.txt** - User accounts and authentication
2. **students.txt** - Student profiles and information
3. **laptops.txt** - Laptop inventory and specifications
4. **bookings.txt** - Booking and rental records

## Design Patterns Implemented

1. **Repository Pattern**: Separates data access from business logic
2. **Service Layer Pattern**: Encapsulates business logic
3. **MVC Pattern**: Separates models, views (console), and controllers
4. **Object-Oriented Design**: Proper inheritance, encapsulation, polymorphism

## Key Features

### Admin Features
- Student management (CRUD operations)
- Laptop inventory management
- View all bookings and statistics
- Monitor overdue returns
- System revenue tracking

### Student Features
- Browse available laptops
- Book laptops for specific durations
- Return borrowed laptops
- View personal booking history
- Extend booking duration

### System Features
- File-based data persistence
- Comprehensive error handling
- Input validation
- Automatic data file creation
- Role-based access control

## How to Run

### Windows
```bash
run.bat
```

### Unix/Linux
```bash
chmod +x run.sh
./run.sh
```

### Manual Compilation
```bash
javac -d . com/laptop/rental/*.java com/laptop/rental/*/*.java
java com.laptop.rental.Main
```

## Default Login
- **Username**: admin
- **Password**: admin123

## Testing
Use the sample data provided in `sample_data.txt` to test all system features.

This project demonstrates comprehensive Object-Oriented Programming principles with a complete, functional laptop rental management system. 