package ui;

import javax.swing.*;
import java.awt.*;

public class SplashScreen  {
    JFrame splashFrame = new JFrame("Employee Management System");
    private JProgressBar progressBar;
    private JLabel loadingLabel;

    public SplashScreen() {

        splashFrame.setSize(700, 400);
        splashFrame.setLocationRelativeTo(null);
        splashFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel title = new JLabel("EMPLOYEE MANAGEMENT SYSTEM");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBounds(120, 50, 500, 40);

        JLabel group = new JLabel("Group Members: Jalal Ali, Member 2, Member 3");
        group.setFont(new Font("Arial", Font.PLAIN, 16));
        group.setBounds(150, 120, 400, 30);

        JLabel course = new JLabel("Object Oriented Programming");
        course.setBounds(230, 150, 250, 25);

        loadingLabel = new JLabel("Loading...");
        loadingLabel.setBounds(310, 250, 100, 25);

        progressBar = new JProgressBar(0, 100);
        progressBar.setBounds(100, 290, 500, 25);
        progressBar.setStringPainted(true);

        panel.add(title);
        panel.add(group);
        panel.add(course);
        panel.add(loadingLabel);
        panel.add(progressBar);

        splashFrame.add(panel);
        startLoading();
    }

    private void startLoading() {

        Timer timer = new Timer(40, null);

        timer.addActionListener(e -> {

            int value = progressBar.getValue();

            progressBar.setValue(value + 1);

            if (value >= 99) {
                timer.stop();
                splashFrame.dispose();
                new LoginScreen();
            }
        });

        timer.start();
    }
}