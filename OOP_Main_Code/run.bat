@echo off
echo ========================================
echo Laptop Rental Management System
echo ========================================
echo.

echo Compiling Java files...
javac -d . ^
com\laptop\rental\Main.java ^
com\laptop\rental\model\User.java ^
com\laptop\rental\model\Student.java ^
com\laptop\rental\model\Admin.java ^
com\laptop\rental\model\Laptop.java ^
com\laptop\rental\model\Booking.java ^
com\laptop\rental\repository\UserRepository.java ^
com\laptop\rental\repository\StudentRepository.java ^
com\laptop\rental\repository\LaptopRepository.java ^
com\laptop\rental\repository\BookingRepository.java ^
com\laptop\rental\service\AuthService.java ^
com\laptop\rental\service\StudentService.java ^
com\laptop\rental\service\LaptopService.java ^
com\laptop\rental\service\BookingService.java

if %errorlevel% neq 0 (
    echo Compilation failed! Please check your Java installation.
    pause
    exit /b 1
)

echo Compilation successful!
echo.
echo Starting the application...
echo.

java com.laptop.rental.Main

echo.
echo Application terminated.
pause 