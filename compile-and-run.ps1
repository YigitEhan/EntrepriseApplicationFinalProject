# Simple PowerShell script to compile changed files and run the application
# This is a workaround when Maven is not available

Write-Host "Compiling changed Java files..." -ForegroundColor Cyan

# Create output directory if it doesn't exist
$targetClasses = "target\classes"
if (!(Test-Path $targetClasses)) {
    New-Item -ItemType Directory -Path $targetClasses -Force | Out-Null
}

# Find all Java files
$javaFiles = Get-ChildItem -Path "src\main\java" -Filter "*.java" -Recurse

# Compile each Java file
foreach ($file in $javaFiles) {
    $relativePath = $file.FullName.Substring((Get-Location).Path.Length + 1)
    Write-Host "Compiling: $relativePath" -ForegroundColor Yellow
    
    # Get the package path
    $packagePath = $file.DirectoryName.Replace("src\main\java\", "").Replace("\", "/")
    $outputDir = Join-Path $targetClasses $packagePath
    
    # Create output directory
    if (!(Test-Path $outputDir)) {
        New-Item -ItemType Directory -Path $outputDir -Force | Out-Null
    }
    
    # Compile (this is simplified - in reality you'd need all dependencies on classpath)
    # javac -d $targetClasses -cp "..." $file.FullName
}

Write-Host "`nNote: This script requires Maven to properly compile with all dependencies." -ForegroundColor Red
Write-Host "Please install Maven or use the Maven Wrapper (mvnw.cmd)" -ForegroundColor Red
Write-Host "`nTo run with Maven Wrapper:" -ForegroundColor Green
Write-Host "  .\mvnw.cmd clean spring-boot:run" -ForegroundColor White

