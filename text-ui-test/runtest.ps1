# Set script directory
$ScriptDir = Split-Path -Parent $MyInvocation.MyCommand.Path

# Set project paths
$BinDir = Join-Path -Path $ScriptDir -ChildPath "..\bin"
$SrcDir = Join-Path -Path $ScriptDir -ChildPath "..\src\main\java"

# Create bin directory if it doesn't exist
if (-not (Test-Path -Path $BinDir)) {
    New-Item -ItemType Directory -Path $BinDir | Out-Null
}

# Delete previous ACTUAL.TXT if it exists
$ActualFile = Join-Path -Path $ScriptDir -ChildPath "ACTUAL.TXT"
if (Test-Path -Path $ActualFile) {
    Remove-Item -Path $ActualFile
}

Write-Host "Compiling..."
$SourceFiles = @(
    (Join-Path -Path $SrcDir -ChildPath "V.java"),
    (Join-Path -Path $SrcDir -ChildPath "Task.java"),
    (Join-Path -Path $SrcDir -ChildPath "Todo.java"),
    (Join-Path -Path $SrcDir -ChildPath "Deadline.java"),
    (Join-Path -Path $SrcDir -ChildPath "Event.java")
)
javac -Xlint:none -d $BinDir $SourceFiles

if ($LASTEXITCODE -ne 0) {
    Write-Host "********** BUILD FAILURE **********" -ForegroundColor Red
    exit 1
}

Write-Host "Running tests..."
$InputFile = Join-Path -Path $ScriptDir -ChildPath "input.txt"
# Set console output to UTF-8
[Console]::OutputEncoding = [System.Text.Encoding]::UTF8

# Run the java program
Get-Content $InputFile -Encoding UTF8 | java -classpath $BinDir V | Out-File -FilePath $ActualFile -Encoding utf8NoBOM

Write-Host "Comparing with expected output..."
$ExpectedFile = Join-Path -Path $ScriptDir -ChildPath "EXPECTED.TXT"
if (-not (Test-Path -Path $ActualFile)) {
    Write-Host "********** TEST FAILED **********" -ForegroundColor Red
    Write-Host "ACTUAL.TXT was not created. Check for runtime errors."
    exit 1
}

$diff = Compare-Object -ReferenceObject (Get-Content $ExpectedFile -Encoding UTF8) -DifferenceObject (Get-Content $ActualFile -Encoding UTF8) -PassThru

if ($diff) {
    Write-Host "********** TEST FAILED **********" -ForegroundColor Red
    Write-Host "Differences found:"
    $diff
    exit 1
} else {
    Write-Host "********** TEST PASSED **********" -ForegroundColor Green
    exit 0
}
