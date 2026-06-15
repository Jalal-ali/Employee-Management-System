package ui;

import fileHandler.FileHandler;
import javax.swing.*;
import java.awt.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class RegisterScreen {

    JFrame registerFrame = new JFrame("Employee Management System");
    FileHandler auth = new FileHandler();

    public RegisterScreen() {

        registerFrame.setSize(500, 650);
        registerFrame.setLocationRelativeTo(null);
        registerFrame.setLayout(null);
        registerFrame.setResizable(false);
        registerFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(50, 100, 380, 400);

        JLabel title = new JLabel("REGISTER");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBounds(0, 10, 380, 40);

        JLabel subtitle = new JLabel("Employee Management System");
        subtitle.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitle.setHorizontalAlignment(SwingConstants.CENTER);
        subtitle.setBounds(0, 50, 380, 25);

//        inputs
        JLabel userLabel = new JLabel("Username");
        userLabel.setFont(new Font("Arial", Font.BOLD, 14));
        userLabel.setBounds(40, 100, 100, 25);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(40, 125, 300, 35);

        JLabel passLabel = new JLabel("Password");
        passLabel.setFont(new Font("Arial", Font.BOLD, 14));
        passLabel.setBounds(40, 180, 100, 25);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(40, 205, 300, 35);

        char defaultEchoChar = passwordField.getEchoChar();

        JCheckBox showPassword = new JCheckBox("Show Password");
        showPassword.setBounds(40, 245, 150, 20);


//        forgot
        JLabel forgotPassword = new JLabel("<html><u>Forgot Password?</u></html>");
        forgotPassword.setFont(new Font("Arial", Font.PLAIN, 12));
        forgotPassword.setForeground(Color.BLUE);
        forgotPassword.setCursor(new Cursor(Cursor.HAND_CURSOR));
        forgotPassword.setBounds(220, 245, 120, 20);

        JButton registerBtn = new JButton("Register");
        registerBtn.setFont(new Font("Arial", Font.BOLD, 14));
        registerBtn.setBounds(40, 285, 140, 40);

        JButton clearBtn = new JButton("Clear");
        clearBtn.setFont(new Font("Arial", Font.BOLD, 14));
        clearBtn.setBounds(200, 285, 140, 40);

        JLabel accountLabel = new JLabel("Already have an account?");
        accountLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        accountLabel.setBounds(70, 350, 150, 20);
//        login link
        JLabel loginLink = new JLabel("<html><u>Login</u></html>");
        loginLink.setFont(new Font("Arial", Font.BOLD, 12));
        loginLink.setForeground(Color.BLUE);
        loginLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginLink.setBounds(220, 350, 60, 20);

        panel.add(title);
        panel.add(subtitle);
        panel.add(userLabel);
        panel.add(usernameField);
        panel.add(passLabel);
        panel.add(passwordField);
        panel.add(showPassword);
        panel.add(registerBtn);
        panel.add(accountLabel);
        panel.add(loginLink);
        panel.add(clearBtn);
        panel.add(forgotPassword);

        registerFrame.add(panel);
        registerFrame.setVisible(true);

//        funcs ------
        registerBtn.addActionListener(e -> {
            String user = usernameField.getText();
            String pass = new String(passwordField.getPassword());

//            if inputs are empty
            if (user.length() < 1 || pass.length() < 1) {
                JOptionPane.showMessageDialog(
                        registerFrame,
                        "Username and password cannot be empty!",
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            boolean success = auth.register(user, pass);
//            if reg is succeeded
            if (success) {
                JOptionPane.showMessageDialog(registerFrame,
                        "Registration successful!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);

                new LoginScreen();
                registerFrame.dispose();
            } else {
                JOptionPane.showMessageDialog(
                        registerFrame,
                        "Registration Failed, Try again!",
                        "Failed",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        loginLink.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                new LoginScreen();
                registerFrame.dispose();
            }
        });
        showPassword.addActionListener(e -> {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar(defaultEchoChar);
            }
        });

        clearBtn.addActionListener(e -> {
            usernameField.setText("");
            passwordField.setText("");
            showPassword.setSelected(false);
            passwordField.setEchoChar(defaultEchoChar);
            usernameField.requestFocus();
        });

        forgotPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {

                String username = JOptionPane.showInputDialog(
                        registerFrame,
                        "Enter your username:"
                );

                if (username == null || username.trim().isEmpty()) {
                    return;
                }

                String password = auth.getPassword(username);

                if (password != null) {
                    JOptionPane.showMessageDialog(
                            registerFrame,
                            "Your password is: " + password,
                            "Password Recovery",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                } else {
                    JOptionPane.showMessageDialog(
                            registerFrame,
                            "Username not found!",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });
    }
}