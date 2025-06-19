Write-Host "========================================" -ForegroundColor Green
Write-Host "Laptop Rental Management System" -ForegroundColor Green
Write-Host "========================================" -ForegroundColor Green
Write-Host ""

Write-Host "Compiling Java files..." -ForegroundColor Yellow

# Get all Java files recursively
$javaFiles = Get-ChildItem -Path "com" -Filter "*.java" -Recurse | ForEach-Object { $_.FullName }

# Compile all Java files
javac -d . $javaFiles

if ($LASTEXITCODE -ne 0) {
    Write-Host "Compilation failed! Please check your Java installation." -ForegroundColor Red
    Read-Host "Press Enter to continue"
    exit 1
}

Write-Host "Compilation successful!" -ForegroundColor Green
Write-Host ""
Write-Host "Starting the application..." -ForegroundColor Yellow
Write-Host ""

java com.laptop.rental.Main

Write-Host ""
Write-Host "Application terminated." -ForegroundColor Yellow
Read-Host "Press Enter to continue" 