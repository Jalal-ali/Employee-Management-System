package ui;

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
        panel.setBounds(50, 100, 380, 400);

        JLabel title = new JLabel("LOGIN");
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
        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel passLabel = new JLabel("Password");
        passLabel.setFont(new Font("Arial", Font.BOLD, 14));
        passLabel.setBounds(40, 180, 100, 25);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(40, 205, 300, 35);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        char defaultEchoChar = passwordField.getEchoChar();

        JCheckBox showPassword = new JCheckBox("Show Password");
        showPassword.setBounds(40, 245, 150, 20);


        JButton loginBtn = new JButton("Login");
        loginBtn.setFont(new Font("Arial", Font.BOLD, 14));
        loginBtn.setBounds(40, 285, 140, 40);

        JButton clearBtn = new JButton("Clear");
        clearBtn.setFont(new Font("Arial", Font.BOLD, 14));
        clearBtn.setBounds(200, 285, 140, 40);

//        forgot
        JLabel forgotPassword = new JLabel("<html><u>Forgot Password?</u></html>");
        forgotPassword.setFont(new Font("Arial", Font.PLAIN, 12));
        forgotPassword.setForeground(Color.BLUE);
        forgotPassword.setCursor(new Cursor(Cursor.HAND_CURSOR));
        forgotPassword.setBounds(220, 245, 120, 20);

//        register link
        JLabel accountLabel = new JLabel("Don't have an account?");
        accountLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        accountLabel.setBounds(80, 350, 130, 20);
        JLabel registerLink = new JLabel("<html><u>Register</u></html>");
        registerLink.setFont(new Font("Arial", Font.BOLD, 12));
        registerLink.setForeground(Color.BLUE);
        registerLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        registerLink.setBounds(210, 350, 70, 20);

        panel.add(title);
        panel.add(subtitle);
        panel.add(userLabel);
        panel.add(usernameField);
        panel.add(passLabel);
        panel.add(passwordField);
        panel.add(showPassword);
        panel.add(loginBtn);
        panel.add(accountLabel);
        panel.add(registerLink);
        panel.add(clearBtn);
        panel.add(forgotPassword);

        loginFrame.add(panel);
        loginFrame.setVisible(true);

//        funcs
        loginBtn.addActionListener(e -> {
            String user = usernameField.getText();
            String pass = new String(passwordField.getPassword());

            if (user.length() < 1 || pass.length() < 1) {
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

                new Dashboard();
                loginFrame.dispose();
            } else {
                JOptionPane.showMessageDialog(
                        loginFrame,
                        "Login Failed, Try again!",
                        "Failed",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        registerLink.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                new RegisterScreen();
                loginFrame.dispose();
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
                try {


                    String username = JOptionPane.showInputDialog(
                            loginFrame,
                            "Enter your username:"
                    );
                    String pass = JOptionPane.showInputDialog(
                            loginFrame,
                            "Enter your new password:"
                    );

                    if (username == null || username.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(loginFrame,
                                "Username is required."
                        );
                        return;
                    }
                    if (pass == null || pass.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(loginFrame,
                                "Password is required."
                        );
                        return;
                    }

                    String cred = auth.updatePass(username, pass);
//                String[] data = cred.split(",");
//                String user = data[0];
//                String password = data[1];

                    if (cred.length() > 0) {
                        JOptionPane.showMessageDialog(
                                loginFrame,
                                "Credentials updated ",
                                "Password Recovery",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                    } else {
                        JOptionPane.showMessageDialog(
                                loginFrame,
                                "Username not found!",
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(
                            loginFrame,
                            "Failed to update, Try again!",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });
    }
}