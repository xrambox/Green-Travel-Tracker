# Green Travel Tracker

Green Travel Tracker is a desktop application designed to promote eco-friendly travel by providing tools to calculate carbon emissions, find transport routes, and display travel tips. The application is built using Java Swing for the GUI and aims to help users make more environmentally conscious travel decisions.

## Features

1. **Carbon Emission Calculator**: Calculate the carbon footprint of your trips based on the mode of transportation, distance, fuel efficiency, and other factors.
2. **Transport Route Finder**: Find transport routes between two locations and display any intermediate stops.
3. **Travel Tips**: Display random travel tips to promote safe and eco-friendly travel practices.

## Installation

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/yourusername/green-travel-tracker.git
   cd green-travel-tracker
   ```

2. **Compile the Code**:
   ```bash
   javac -d bin src/de/buw/se/*.java
   ```

3. **Run the Application**:
   ```bash
   java -cp bin de.buw.se.AppCLI
   ```

## Usage

### Carbon Emission Calculator

1. Launch the application and click on the "Carbon Emission Calculator" button.
2. Enter the required details such as distance, transport mode, fuel efficiency, fuel type, traffic conditions, number of passengers, and travel class.
3. Click on the "Calculate Emission" button to view the carbon footprint of your trip.

### Transport Route Finder

1. Launch the application and click on the "Transport Route Finder" button.
2. Enter the starting location and destination in the provided text fields.
3. Click on the "Find Route" button to see if a route exists and view any intermediate stops.

### Travel Tips

1. Launch the application and click on the "Show Travel Tips" button to display random travel tips in the text area.

## Code Structure

### Main Application (AppCLI)

The main application class sets up the JFrame and adds buttons for accessing different features of the application. It also includes a text area to display random travel tips.

```java
'''public class AppCLI {
    private JFrame frame;
    private JTextArea tipsArea;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                AppCLI window = new AppCLI();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public AppCLI() {
        initialize();
    }

    private void initialize() {
        // Initialization code
    }

    private void displayRandomTips() {
        // Code to display random travel tips
    }
}'''
```

### Carbon Emission Calculator

This class provides the functionality to calculate the carbon emissions for different modes of transport. It uses a JFrame to collect user input and display the results.

```java
'''public class CarbonFootPrintCalculator {
    // Constants, fields, and initialization methods

    public static void run() {
        // Code to run the calculator
    }

    private void calculateEmission() {
        // Code to calculate carbon emissions
    }

    private static double calculateCarEmission(...) {
        // Calculation logic for car emissions
    }

    private static double calculateAirplaneEmission(...) {
        // Calculation logic for airplane emissions
    }

    private static double calculateEmission(...) {
        // Calculation logic for other transport modes
    }

    private void printSummary(...) {
        // Code to print the summary of the emissions
    }
}'''
```

### Transport Route Finder

This class finds transport routes between two locations by reading a CSV file. It displays any intermediate stops if a route is found.

```java
'''public class TransportRouteFinder {
    // Fields and initialization methods

    public static void run() {
        // Code to run the route finder
    }

    private void findRoute(...) {
        // Code to find and display the route
    }
}'''
```

## CSV File Format

The `TransportRouteFinder.csv` file should be placed in the `src/main/resources` directory and should have the following format:

```
StartingLocation,Destination,IntermediateStop
...
```

## Contributing

1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Commit your changes (`git commit -am 'Add new feature'`).
4. Push to the branch (`git push origin feature-branch`).
5. Create a new Pull Request.

Author: Ankit Anand