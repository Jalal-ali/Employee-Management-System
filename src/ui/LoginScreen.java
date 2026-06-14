package ui;

import controllers.AuthController;
import fileHandler.FileHandler;

import javax.swing.*;
import java.awt.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class LoginScreen {

    FileHandler auth = new FileHandler();
    JFrame loginFrame = new JFrame("Employee Management System");

    public LoginScreen() {

        loginFrame.setSize(500, 650);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setLayout(null);
        loginFrame.setResizable(false);
        loginFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(50, 100, 380, 380);

        JLabel title = new JLabel("LOGIN");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBounds(0, 10, 380, 40);

        JLabel subtitle = new JLabel("Employee Management System");
        subtitle.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitle.setHorizontalAlignment(SwingConstants.CENTER);
        subtitle.setBounds(0, 50, 380, 25);

        JLabel userLabel = new JLabel("Username");
        userLabel.setFont(new Font("Arial", Font.BOLD, 14));
        userLabel.setBounds(40, 100, 100, 25);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(40, 125, 300, 35);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel passLabel = new JLabel("Password");
        passLabel.setFont(new Font("Arial", Font.BOLD, 14));
        passLabel.setBounds(40, 180, 100, 25);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(40, 205, 300, 35);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));

        JButton loginBtn = new JButton("Login");
        loginBtn.setFont(new Font("Arial", Font.BOLD, 14));
        loginBtn.setBounds(120, 270, 140, 40);

        JLabel accountLabel = new JLabel("Don't have an account?");
        accountLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        accountLabel.setBounds(80, 340, 130, 20);

        JLabel registerLink = new JLabel("<html><u>Register</u></html>");
        registerLink.setFont(new Font("Arial", Font.BOLD, 12));
        registerLink.setForeground(Color.BLUE);
        registerLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        registerLink.setBounds(210, 340, 70, 20);

        panel.add(title);
        panel.add(subtitle);
        panel.add(userLabel);
        panel.add(usernameField);
        panel.add(passLabel);
        panel.add(passwordField);
        panel.add(loginBtn);
        panel.add(accountLabel);
        panel.add(registerLink);

        loginFrame.add(panel);
        loginFrame.setVisible(true);

        loginBtn.addActionListener(e -> {
            String user = usernameField.getText();
            String pass = new String(passwordField.getPassword());
            if(user.length() <= 0 || pass.length() <= 0){
                JOptionPane.showMessageDialog(
                        loginFrame,
                        "Username and password cannot be empty!",
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }
            boolean success = auth.login(user, pass);

            if (success) {
                JOptionPane.showMessageDialog(
                        loginFrame,
                        "Login successful!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                new LoginScreen();
                loginFrame.dispose();
            }else{
                JOptionPane.showMessageDialog(loginFrame,
                        "Login Failed, Try again!",
                        "Failed",
                        JOptionPane.INFORMATION_MESSAGE );
            }
        });

        registerLink.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {

                new RegisterScreen();

                loginFrame.dispose();
            }
        });
    }
}