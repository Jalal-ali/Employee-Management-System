package ui;

import controllers.AuthController;

import javax.swing.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class RegisterScreen {
    JFrame RegisterScreen = new JFrame("Login");
    AuthController auth = new AuthController();
    public RegisterScreen() {

//        setTitle("Login");
        RegisterScreen.setSize(300, 200);
        RegisterScreen.setLayout(null);
        RegisterScreen.setDefaultCloseOperation(EXIT_ON_CLOSE);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(50, 30, 180, 25);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(50, 70, 180, 25);

        JButton RegisterBtn = new JButton("Register");
        RegisterBtn.setBounds(90, 110, 100, 30);

        RegisterScreen.add(usernameField);
        RegisterScreen.add(passwordField);
        RegisterScreen.add(RegisterBtn);

        RegisterScreen.setVisible(true);
    }
}