package ui;

import javax.swing.*;
import java.awt.*;

public class Dashboard {

    JFrame frame = new JFrame("Employee Management System");

    public Dashboard() {

        frame.setSize(900, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setPreferredSize(new Dimension(900, 60));

        JLabel title = new JLabel("Employee Management System");
        title.setFont(new Font("Arial", Font.BOLD, 22));

        JButton logoutBtn = new JButton("Logout");

        header.add(title, BorderLayout.WEST);
        header.add(logoutBtn, BorderLayout.EAST);

        // Sidebar
        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(220, 600));
        sidebar.setLayout(new GridLayout(6, 1, 10, 10));

        JButton addBtn = new JButton("Add Employee");
        JButton searchBtn = new JButton("Search Employee");
        JButton updateBtn = new JButton("Update Employee");
        JButton deleteBtn = new JButton("Delete Employee");
        JButton viewBtn = new JButton("View Employees");

        sidebar.add(addBtn);
        sidebar.add(searchBtn);
        sidebar.add(updateBtn);
        sidebar.add(deleteBtn);
        sidebar.add(viewBtn);

        // Content Panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());

        JLabel welcome = new JLabel(
                "Welcome to Employee Dashboard",
                SwingConstants.CENTER
        );

        welcome.setFont(new Font("Arial", Font.BOLD, 20));

        contentPanel.add(welcome, BorderLayout.CENTER);

        frame.add(header, BorderLayout.NORTH);
        frame.add(sidebar, BorderLayout.WEST);
        frame.add(contentPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}
