package de.buw.se;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CarbonFootPrintCalculator {
    private static final Map<String, Double> emissionFactors = new HashMap<>();
    static {
        emissionFactors.put("gasoline", 2.31); // kgCO2e per liter
        emissionFactors.put("diesel", 2.68); // kgCO2e per liter
        emissionFactors.put("electric", 0.12); // kgCO2e per kWh
    }

    public static void run() {
        EventQueue.invokeLater(() -> {
            try {
                CarbonFootPrintCalculator window = new CarbonFootPrintCalculator();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private JFrame frame;
    private JTextField distanceField;
    private JComboBox<String> transportChoiceBox;
    private JCheckBox roundTripCheckBox;
    private JTextField fuelEfficiencyField;
    private JComboBox<String> fuelTypeBox;
    private JComboBox<String> trafficConditionBox;
    private JTextField passengersField;
    private JComboBox<String> travelClassBox;
    private JTextArea resultArea;

    public CarbonFootPrintCalculator() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Carbon Emission Calculator");
        frame.setBounds(100, 100, 600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(new GridLayout(0, 2, 10, 10));

        JLabel lblDistance = new JLabel("Distance (km):");
        panel.add(lblDistance);

        distanceField = new JTextField();
        panel.add(distanceField);
        distanceField.setColumns(10);

        JLabel lblTransportChoice = new JLabel("Transport Mode:");
        panel.add(lblTransportChoice);

        String[] transportChoices = {"Car", "Bike", "Train", "Bus", "Electric Car", "Plane", "Walking"};
        transportChoiceBox = new JComboBox<>(transportChoices);
        panel.add(transportChoiceBox);

        JLabel lblRoundTrip = new JLabel("Round Trip:");
        panel.add(lblRoundTrip);

        roundTripCheckBox = new JCheckBox();
        panel.add(roundTripCheckBox);

        JLabel lblFuelEfficiency = new JLabel("Fuel Efficiency (km per liter or kWh):");
        panel.add(lblFuelEfficiency);

        fuelEfficiencyField = new JTextField();
        panel.add(fuelEfficiencyField);
        fuelEfficiencyField.setColumns(10);

        JLabel lblFuelType = new JLabel("Fuel Type:");
        panel.add(lblFuelType);

        String[] fuelTypes = {"Gasoline", "Diesel", "Electric"};
        fuelTypeBox = new JComboBox<>(fuelTypes);
        panel.add(fuelTypeBox);

        JLabel lblTrafficCondition = new JLabel("Traffic Condition:");
        panel.add(lblTrafficCondition);

        String[] trafficConditions = {"Light", "Moderate", "Heavy"};
        trafficConditionBox = new JComboBox<>(trafficConditions);
        panel.add(trafficConditionBox);

        JLabel lblPassengers = new JLabel("Number of Passengers:");
        panel.add(lblPassengers);

        passengersField = new JTextField();
        panel.add(passengersField);
        passengersField.setColumns(10);

        JLabel lblTravelClass = new JLabel("Travel Class:");
        panel.add(lblTravelClass);

        String[] travelClasses = {"Economy", "Business", "First"};
        travelClassBox = new JComboBox<>(travelClasses);
        panel.add(travelClassBox);

        JButton btnCalculate = new JButton("Calculate Emission");
        btnCalculate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateEmission();
            }
        });
        panel.add(btnCalculate);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        frame.getContentPane().add(new JScrollPane(resultArea), BorderLayout.SOUTH);
    }

    private void calculateEmission() {
        try {
            double distance = Double.parseDouble(distanceField.getText());
            int transportChoice = transportChoiceBox.getSelectedIndex() + 1;
            boolean isRoundTrip = roundTripCheckBox.isSelected();

            if (isRoundTrip) {
                distance *= 2;
            }

            double emission = 0;
            if (transportChoice == 1) {
                String fuelType = (String) fuelTypeBox.getSelectedItem();
                double fuelEfficiency = Double.parseDouble(fuelEfficiencyField.getText());
                String trafficCondition = (String) trafficConditionBox.getSelectedItem();
                emission = calculateCarEmission(distance, fuelType.toLowerCase(), fuelEfficiency, trafficCondition.toLowerCase());
            } else if (transportChoice == 6) {
                String travelClass = (String) travelClassBox.getSelectedItem();
                emission = calculateAirplaneEmission(distance, travelClass.toLowerCase());
            } else {
                int passengers = Integer.parseInt(passengersField.getText());
                emission = calculateEmission(distance, transportChoice, passengers);
            }

            printSummary(distance, transportChoice, emission);

        } catch (NumberFormatException e) {
            resultArea.setText("Please enter valid inputs!");
        }
    }

    private static double calculateCarEmission(double distance, String fuelType, double fuelEfficiency, String trafficCondition) {
        double emissionFactor = emissionFactors.getOrDefault(fuelType, 0.2); // Default to gasoline
        double trafficMultiplier = 1.0;

        switch (trafficCondition) {
            case "light":
                trafficMultiplier = 1.0;
                break;
            case "moderate":
                trafficMultiplier = 1.2;
                break;
            case "heavy":
                trafficMultiplier = 1.5;
                break;
        }

        return (distance / fuelEfficiency) * emissionFactor * trafficMultiplier;
    }

    private static double calculateAirplaneEmission(double distance, String travelClass) {
        double emissionFactor = 0.15; // Default for economy

        switch (travelClass) {
            case "economy":
                emissionFactor = 0.15;
                break;
            case "business":
                emissionFactor = 0.3;
                break;
            case "first":
                emissionFactor = 0.45;
                break;
        }

        return distance * emissionFactor;
    }

    private static double calculateEmission(double distance, int choice, int passengers) {
        double emissionFactor = 0;

        switch (choice) {
            case 2:
                emissionFactor = 0.01; // kgCO2e/km for bike
                break;
            case 3:
                emissionFactor = 0.03; // kgCO2e/km for train
                break;
            case 4:
                emissionFactor = 0.05; // kgCO2e/km for bus
                break;
            case 5:
                emissionFactor = 0.01; // kgCO2e/km for electric car
                break;
            case 7:
                emissionFactor = 0; // kgCO2e/km for walking
                break;
            default:
                System.out.println("Invalid choice!");
        }

        return (distance * emissionFactor) / passengers;
    }

    private void printSummary(double distance, int transportChoice, double emission) {
        String transportMode = "";
        switch (transportChoice) {
            case 1:
                transportMode = "Car";
                break;
            case 2:
                transportMode = "Bike";
                break;
            case 3:
                transportMode = "Train";
                break;
            case 4:
                transportMode = "Bus";
                break;
            case 5:
                transportMode = "Electric Car";
                break;
            case 6:
                transportMode = "Plane";
                break;
            case 7:
                transportMode = "Walking";
                break;
            default:
                transportMode = "Unknown";
        }
        resultArea.setText("\n--- Trip Summary ---\n");
        resultArea.append("Mode of Transport: " + transportMode + "\n");
        resultArea.append("Total Distance: " + distance + " km\n");
        resultArea.append("Carbon Emission: " + emission + " kgCO2e\n");
    }
}
