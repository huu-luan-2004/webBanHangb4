@echo off
echo Downloading Maven...

:: Create tools directory
if not exist "tools" mkdir tools
cd tools

:: Download Maven 3.9.6
if not exist "apache-maven-3.9.6" (
    echo Downloading Maven from Apache...
    curl -L -o maven.zip "https://archive.apache.org/dist/maven/maven-3/3.9.6/binaries/apache-maven-3.9.6-bin.zip"
    
    :: Extract Maven
    echo Extracting Maven...
    tar -xf maven.zip
    del maven.zip
)

cd ..

:: Set Maven home and add to PATH
set MAVEN_HOME=%CD%\tools\apache-maven-3.9.6
set PATH=%MAVEN_HOME%\bin;%PATH%

echo Maven setup complete!
echo Running Spring Boot application...

:: Run the application
mvn spring-boot:run

pause