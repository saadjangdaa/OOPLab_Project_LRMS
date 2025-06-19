#!/bin/bash

echo "========================================"
echo "Laptop Rental Management System"
echo "========================================"
echo

echo "Compiling Java files..."
javac -d . com/laptop/rental/*.java com/laptop/rental/*/*.java

if [ $? -ne 0 ]; then
    echo "Compilation failed! Please check your Java installation."
    exit 1
fi

echo "Compilation successful!"
echo
echo "Starting the application..."
echo

java com.laptop.rental.Main

echo
echo "Application terminated." 