package ui;

import javax.swing.*;
import java.awt.*;

public class SplashScreen  {
    public JFrame splashFrame = new JFrame("Employee Management System");
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
        title.setBounds(120, 70, 500, 40);



        loadingLabel = new JLabel("Loading...");
        loadingLabel.setBounds(310, 150, 100, 25);

        progressBar = new JProgressBar(0, 100);
        progressBar.setBounds(100, 190, 500, 25);
        progressBar.setStringPainted(true);

        panel.add(title);
//        panel.add(group);
//        panel.add(course);
        panel.add(loadingLabel);
        panel.add(progressBar);

        splashFrame.add(panel);
        startLoading();
    }

    private void startLoading() {

        Timer timer = new Timer(10, null);

        timer.addActionListener(e -> {
            int value = progressBar.getValue();
            progressBar.setValue(value + 1);
            if (value >= 99) {
                timer.stop();
                splashFrame.dispose();
                new HomeScreen();
            }
        });

        timer.start();
    }
}