@ECHO OFF

REM create bin directory if it doesn't exist
if not exist "..\bin" mkdir "..\bin"

REM create data directory if it doesn't exist
if not exist "..\data" mkdir "..\data"

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

REM compile the code into the bin folder (recursively find all .java files)
dir /s /b "..\src\main\java\*.java" > sources.txt
javac -cp "..\src\main\java" -Xlint:none -d "..\bin" @sources.txt
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)

REM run the program using the fully qualified class name
java -classpath "..\bin" mochi.Mochi < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT
