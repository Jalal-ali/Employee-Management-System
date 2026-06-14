package ui;

import javax.swing.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class LoginScreen {
    JFrame LoginFrame = new JFrame("Login");
    public LoginScreen() {

//        setTitle("Login");
        LoginFrame.setSize(300, 200);
        LoginFrame.setLayout(null);
        LoginFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(50, 30, 180, 25);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(50, 70, 180, 25);

        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(90, 110, 100, 30);

        LoginFrame.add(usernameField);
        LoginFrame.add(passwordField);
        LoginFrame.add(loginBtn);

        LoginFrame.setVisible(true);
    }
}