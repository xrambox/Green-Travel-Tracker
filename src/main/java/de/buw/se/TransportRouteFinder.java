package de.buw.se;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TransportRouteFinder {

    private JFrame frame;
    private JPanel panel;
    private JTextField txtStartingLocation;
    private JTextField txtDestination;
    private JTextArea routeInfoArea;

    public static void run() {
        EventQueue.invokeLater(() -> {
            try {
                TransportRouteFinder window = new TransportRouteFinder();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public TransportRouteFinder() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Transport Route Finder");
        frame.setBounds(100, 100, 800, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.NORTH);
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        JLabel lblStartingLocation = new JLabel("Starting Location:");
        panel.add(lblStartingLocation);

        txtStartingLocation = new JTextField();
        txtStartingLocation.setColumns(20);
        panel.add(txtStartingLocation);

        JLabel lblDestination = new JLabel("Destination:");
        panel.add(lblDestination);

        txtDestination = new JTextField();
        txtDestination.setColumns(20);
        panel.add(txtDestination);

        JButton btnFindRoute = new JButton("Find Route");
        btnFindRoute.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String startingLocation = txtStartingLocation.getText();
                String destination = txtDestination.getText();
                findRoute(startingLocation, destination);
            }
        });
        panel.add(btnFindRoute);

        routeInfoArea = new JTextArea();
        routeInfoArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(routeInfoArea);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    private void findRoute(String startingLocation, String destination) {
        // Reading the CSV file
        File file = new File("src/main/resources/TransportRouteFinder.csv");
        if (!file.exists()) {
            JOptionPane.showMessageDialog(frame, "CSV file not found.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Searching for the routes in the CSV file
        boolean routeFound = false;
        String intermediateStop = "";
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            // Skip the header row
            br.readLine();

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String start = parts[0];
                String end = parts[1];
                intermediateStop = parts[2];

                if (start.equalsIgnoreCase(startingLocation) && end.equalsIgnoreCase(destination)) {
                    routeFound = true;
                    break;
                }
            }
            if (routeFound) {
                routeInfoArea.setText("Route found!\nIntermediate Stop: " + intermediateStop);
            } else {
                routeInfoArea.setText("Route not found for the given locations.");
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "Error reading CSV file: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
