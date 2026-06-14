package ui;

import javax.swing.*;
import java.awt.*;

public class HomeScreen {

    public JFrame homeFrame = new JFrame("Employee Management System - Home");

    public HomeScreen() {

        homeFrame.setSize(700, 500);
        homeFrame.setLocationRelativeTo(null);
        homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homeFrame.setLayout(null);
        homeFrame.setResizable(false);

        JLabel title = new JLabel("EMPLOYEE MANAGEMENT SYSTEM");
        title.setFont(new Font("Arial", Font.BOLD, 26));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBounds(50, 40, 600, 40);

        JLabel subtitle = new JLabel("Object Oriented Programming Project");
        subtitle.setFont(new Font("Arial", Font.PLAIN, 16));
        subtitle.setHorizontalAlignment(SwingConstants.CENTER);
        subtitle.setBounds(50, 85, 600, 25);

        JButton loginBtn = new JButton("Login");
        loginBtn.setFont(new Font("Arial", Font.BOLD, 14));
        loginBtn.setBounds(275, 170, 150, 45);

        JButton signupBtn = new JButton("Sign Up");
        signupBtn.setFont(new Font("Arial", Font.BOLD, 14));
        signupBtn.setBounds(275, 240, 150, 45);

        JSeparator divider = new JSeparator();
        divider.setBounds(50, 360, 600, 2);

        JLabel groupTitle = new JLabel("Group Members:");
        groupTitle.setFont(new Font("Arial", Font.BOLD, 13));
        groupTitle.setBounds(50, 375, 150, 20);

        JTextArea members = new JTextArea(
                """
                        • Jalal Ali  (IU02-0325-1543)
                        • Yabish Ali (IU02-0325-1432)
                        • Shah Nawaz (IU02-0325-1198)"""
        );

        members.setEditable(false);
        members.setOpaque(false);
        members.setFont(new Font("Arial", Font.PLAIN, 13));
        members.setBounds(70, 400, 200, 70);


        homeFrame.add(title);
        homeFrame.add(subtitle);
        homeFrame.add(loginBtn);
        homeFrame.add(signupBtn);
        homeFrame.add(divider);
        homeFrame.add(groupTitle);
        homeFrame.add(members);

        homeFrame.setVisible(true);

//        functionalities
        loginBtn.addActionListener(e -> {
            new LoginScreen();
            homeFrame.dispose();
        });
        signupBtn.addActionListener(e -> {
            new RegisterScreen();
            homeFrame.dispose();
        });
    }
}