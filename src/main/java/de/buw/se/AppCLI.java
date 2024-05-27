package de.buw.se;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class AppCLI {

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
        frame = new JFrame();
        frame.setTitle("Green Travel Tracker");
        frame.setBounds(100, 100, 500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.NORTH);

        JButton btnCarbonCalculator = new JButton("Carbon Emission Calculator");
        btnCarbonCalculator.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CarbonFootPrintCalculator.run();
            }
        });
        panel.add(btnCarbonCalculator);

        JButton btnRouteFinder = new JButton("Transport Route Finder");
        btnRouteFinder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TransportRouteFinder.run();
            }
        });
        panel.add(btnRouteFinder);

        tipsArea = new JTextArea();
        tipsArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(tipsArea);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        JButton btnShowTips = new JButton("Show Travel Tips");
        btnShowTips.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayRandomTips();
            }
        });
        frame.getContentPane().add(btnShowTips, BorderLayout.SOUTH);
    }

    private void displayRandomTips() {
        String[] sentences = {
            "Plan your route: Before you embark on your journey, plan your route thoroughly. Familiarize yourself with the roads, potential stops, and alternative routes in case of unexpected situations.",
            "Check your vehicle: Ensure your vehicle is in good condition before hitting the road. Check the tires, brakes, lights, and fluid levels. Regular maintenance can prevent breakdowns during your journey.",
            "Wear protective gear: Whether you're riding a motorcycle or driving a car, always wear appropriate protective gear. This includes helmets, gloves, jackets, and sturdy footwear. Safety gear can significantly reduce the risk of injury in case of accidents.",
            "Stay alert: Pay attention to your surroundings while traveling. Avoid distractions such as texting or using your phone while driving. Stay vigilant for road hazards, changing weather conditions, and other vehicles.",
            "Take regular breaks: Long journeys can be exhausting, both physically and mentally. Take regular breaks to rest, stretch your legs, and hydrate. Fatigue can impair your reaction time and decision-making abilities, so it's essential to stay fresh and alert.",
            "Obey traffic laws: Follow traffic rules and regulations at all times. Respect speed limits, yield signs, and traffic signals. Reckless driving not only endangers yourself but also others on the road.",
            "Stay hydrated and nourished: Maintain proper hydration and nutrition throughout your journey. Pack enough water and healthy snacks to keep your energy levels up. Avoid excessive caffeine or sugary drinks, as they can lead to dehydration and fatigue.",
            "Keep emergency supplies: Prepare an emergency kit with essentials such as a first-aid kit, flashlight, batteries, blankets, and tools for minor repairs. Having these supplies on hand can help you handle unexpected situations more effectively.",
            "Stay updated on weather conditions: Check the weather forecast before you travel, especially if you're embarking on a long journey. Be prepared for changing weather conditions and adjust your plans accordingly. Avoid traveling in severe weather if possible.",
            "Inform someone of your itinerary: Let a trusted friend or family member know about your travel plans, including your route, expected arrival time, and contact information. In case of emergencies, they can provide assistance or alert authorities if needed."
        };

        Random random = new Random();
        tipsArea.setText("*****************TIPS WHILE TRAVELLING****************************\n");

        for (int i = 0; i < 3; i++) {
            int index = random.nextInt(sentences.length);
            tipsArea.append("********************************************\n");
            tipsArea.append(sentences[index] + "\n");
        }
    }
}
