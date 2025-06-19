@echo off
echo ========================================
echo Laptop Rental Management System
echo ========================================
echo.

echo Compiling Java files...
javac -cp . com/laptop/rental/Main.java com/laptop/rental/model/*.java com/laptop/rental/repository/*.java com/laptop/rental/service/*.java

if %errorlevel% neq 0 (
    echo Compilation failed! Please check your Java installation.
    pause
    exit /b 1
)

echo Compilation successful!
echo.
echo Starting the application...
echo.

java -cp . com.laptop.rental.Main

echo.
echo Application terminated.
pause 