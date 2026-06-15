package ui;

import fileHandler.FileHandler;
import models.Employee;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class Dashboard {
    private final Color NORMAL_COLOR = new Color(52, 58, 64);
    private final Color ACTIVE_COLOR = new Color(0, 123, 255);
    private JButton activeButton;

    FileHandler fileHandler = new FileHandler();
    JFrame frame = new JFrame("Employee Management System");

    CardLayout cardLayout = new CardLayout();
    JPanel contentPanel = new JPanel(cardLayout);

    public Dashboard() {

        frame.setSize(1200, 750);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel header = createHeader();

        JPanel sidebar = createSidebar();

        createPages();

        frame.add(header, BorderLayout.NORTH);
        frame.add(sidebar, BorderLayout.WEST);
        frame.add(contentPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private JPanel createHeader() {

        JPanel header = new JPanel(new BorderLayout());
        header.setBorder(new EmptyBorder(10, 20, 10, 20));
        header.setBackground(Color.WHITE);

        JLabel title = new JLabel("Employee Management System - Dashboard");
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));

        JButton logoutBtn = new JButton("Logout");

        header.add(title, BorderLayout.WEST);
        header.add(logoutBtn, BorderLayout.EAST);

        logoutBtn.addActionListener(e -> {
            new LoginScreen();
            frame.dispose();
        });

        return header;
    }

    private JPanel createSidebar() {

        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(220, 0));
        sidebar.setLayout(new GridLayout(7, 1, 0, 10));
        sidebar.setBorder(new EmptyBorder(20, 10, 20, 10));
        sidebar.setBackground(new Color(33, 37, 41));

        JButton dashboardBtn = createMenuButton("Dashboard");
        JButton addBtn = createMenuButton("Add Employee");
        JButton searchBtn = createMenuButton("Search Employee");
        JButton updateBtn = createMenuButton("Update Employee");
        JButton deleteBtn = createMenuButton("Delete Employee");
        JButton viewBtn = createMenuButton("View Employees");

        dashboardBtn.addActionListener(e -> {
            cardLayout.show(contentPanel, "dashboard");
            setActiveButton(dashboardBtn);
        });

        addBtn.addActionListener(e -> {
            cardLayout.show(contentPanel, "add");
            setActiveButton(addBtn);
        });

        searchBtn.addActionListener(e -> {
            cardLayout.show(contentPanel, "search");
            setActiveButton(searchBtn);
        });

        updateBtn.addActionListener(e -> {
            cardLayout.show(contentPanel, "update");
            setActiveButton(updateBtn);

        });

        deleteBtn.addActionListener(e -> {
            cardLayout.show(contentPanel, "delete");
            setActiveButton(deleteBtn);

        });

        viewBtn.addActionListener(e -> {
            cardLayout.show(contentPanel, "view");
            setActiveButton(viewBtn);
        });

        sidebar.add(dashboardBtn);
        sidebar.add(addBtn);
        sidebar.add(searchBtn);
        sidebar.add(updateBtn);
        sidebar.add(deleteBtn);
        sidebar.add(viewBtn);
        setActiveButton(dashboardBtn);
        return sidebar;
    }

    //    menu btns
    private JButton createMenuButton(String text) {
        JButton btn = new JButton(text);

        btn.setFocusPainted(false);
        btn.setBackground(NORMAL_COLOR);
        btn.setForeground(Color.WHITE);
        btn.setBorderPainted(false);
        btn.setOpaque(true);

        return btn;
    }

    private void setActiveButton(JButton button) {

        if (activeButton != null) {
            activeButton.setBackground(NORMAL_COLOR);
            activeButton.setForeground(Color.WHITE);
        }

        button.setBackground(ACTIVE_COLOR);
        button.setForeground(Color.WHITE);

        activeButton = button;
    }

    private void createPages() {

        contentPanel.add(createDashboardPage(), "dashboard");
        contentPanel.add(createAddEmployeePage(), "add");
        contentPanel.add(createSearchPage(), "search");
        contentPanel.add(createUpdatePage(), "update");
        contentPanel.add(createDeletePage(), "delete");
        contentPanel.add(createViewEmployeesPage(), "view");
    }

    //    dashboard
    private JPanel createDashboardPage() {

        JPanel panel = new JPanel(new BorderLayout(25, 25));
        panel.setBorder(new EmptyBorder(25, 25, 25, 25));
        panel.setBackground(new Color(245, 247, 250));

        JPanel cardsPanel = new JPanel(new GridLayout(1, 2, 25, 0));
        cardsPanel.setOpaque(false);

        String totalEmp = fileHandler.employeesCount();

        cardsPanel.add(createCard("Total Employees", totalEmp));
        cardsPanel.add(createCard("Departments", "5"));

        panel.add(cardsPanel, BorderLayout.NORTH);

        JLabel welcome = new JLabel("Welcome to Employee Dashboard");
        welcome.setFont(new Font("Segoe UI", Font.BOLD, 24));
        welcome.setHorizontalAlignment(SwingConstants.CENTER);

        panel.add(welcome, BorderLayout.CENTER);

        return panel;
    }

    //    card
    private JPanel createCard(String title, String value) {

        JPanel card = new JPanel(new BorderLayout());

        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                new EmptyBorder(15, 15, 15, 15)
        ));

        JLabel titleLabel = new JLabel(title);
        JLabel valueLabel = new JLabel(value);

        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));

        card.add(titleLabel, BorderLayout.NORTH);
        card.add(valueLabel, BorderLayout.CENTER);

        return card;
    }

    //   add
    private JPanel createAddEmployeePage() {

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(245, 247, 250));
        panel.setBorder(new EmptyBorder(25, 25, 25, 25));

        JLabel heading = new JLabel("Add New Employee");
        heading.setFont(new Font("Segoe UI", Font.BOLD, 28));
        heading.setForeground(new Color(33, 37, 41));
        heading.setBorder(new EmptyBorder(0, 0, 20, 0));

        panel.add(heading, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                new EmptyBorder(25, 25, 25, 25)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel idLabel = new JLabel("Employee ID");
        JLabel nameLabel = new JLabel("Employee Name");
        JLabel deptLabel = new JLabel("Department");

        Font labelFont = new Font("Segoe UI", Font.BOLD, 14);

        idLabel.setFont(labelFont);
        nameLabel.setFont(labelFont);
        deptLabel.setFont(labelFont);

        JTextField idField = new JTextField(20);
        JTextField nameField = new JTextField(20);
        JTextField deptField = new JTextField(20);

        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 14);

        idField.setFont(fieldFont);
        nameField.setFont(fieldFont);
        deptField.setFont(fieldFont);

        JButton saveBtn = new JButton("Save Employee (Ctrl+S)");
        saveBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        saveBtn.setBackground(new Color(0, 123, 255));
        saveBtn.setForeground(Color.WHITE);
        saveBtn.setFocusPainted(false);
        saveBtn.setBorderPainted(false);
        saveBtn.setPreferredSize(new Dimension(220, 40));

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(idLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(idField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(nameLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(deptLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(deptField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(saveBtn, gbc);

        panel.add(formPanel, BorderLayout.CENTER);

        saveBtn.addActionListener(e -> {
            try {
                Employee emp = new Employee();
                emp.id = Integer.parseInt(idField.getText());
                emp.name = nameField.getText();
                emp.department = deptField.getText();

                if (emp.id <= 0 || emp.name.length() <= 0 || emp.department.length() <= 0) {
                    JOptionPane.showMessageDialog(
                            frame,
                            "Any input field cannot be empty!",
                            "Invalid Input",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }

                boolean success = fileHandler.saveEmployee(emp);

                if (success) {
                    JOptionPane.showMessageDialog(
                            frame,
                            "Employee added successfully!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    idField.setText("");
                    nameField.setText("");
                    deptField.setText("");
                    idField.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(
                            frame,
                            "Operation Failed, Try again!",
                            "Failed",
                            JOptionPane.ERROR_MESSAGE
                    );
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                        frame,
                        "Please enter a valid numeric ID",
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        KeyStroke ctrlS = KeyStroke.getKeyStroke("control S");

        panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(ctrlS, "saveEmployee");

        panel.getActionMap().put("saveEmployee", new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                saveBtn.doClick();
            }
        });

        return panel;
    }

    //search
    private JPanel createSearchPage() {

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(245, 247, 250));
        panel.setBorder(new EmptyBorder(25, 25, 25, 25));

        JLabel heading = new JLabel("Search Employee");
        heading.setFont(new Font("Segoe UI", Font.BOLD, 28));
        heading.setForeground(new Color(33, 37, 41));
        heading.setBorder(new EmptyBorder(0, 0, 20, 0));

        panel.add(heading, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                new EmptyBorder(25, 25, 25, 25)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel idLabel = new JLabel("Employee ID");
        idLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));

        JTextField idField = new JTextField(20);
        idField.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JButton searchBtn = new JButton("Search Employee");
        searchBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        searchBtn.setBackground(new Color(0, 123, 255));
        searchBtn.setForeground(Color.WHITE);
        searchBtn.setFocusPainted(false);
        searchBtn.setBorderPainted(false);
        searchBtn.setPreferredSize(new Dimension(180, 40));

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(idLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(idField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(searchBtn, gbc);

        panel.add(formPanel, BorderLayout.CENTER);

        searchBtn.addActionListener(e -> {
            try {

                int id = Integer.parseInt(idField.getText());

                if (id <= 0) {
                    JOptionPane.showMessageDialog(
                            frame,
                            "Enter a valid ID!",
                            "Invalid Input",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }

                String employee = fileHandler.searchEmployee(id);

                if (employee == null || employee.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(
                            frame,
                            "Employee not found!",
                            "Search Result",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }

                String[] data = employee.split(",");

                JPanel detailsPanel = new JPanel(new GridLayout(4, 2, 10, 10));
                detailsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

                detailsPanel.add(new JLabel("ID:"));
                detailsPanel.add(new JLabel(data[0]));

                detailsPanel.add(new JLabel("Name:"));
                detailsPanel.add(new JLabel(data[1]));

                detailsPanel.add(new JLabel("Department:"));
                detailsPanel.add(new JLabel(data[2]));

                detailsPanel.add(new JLabel("Role:"));
                detailsPanel.add(new JLabel(data[3]));

                JOptionPane.showMessageDialog(
                        frame,
                        detailsPanel,
                        "Employee Details",
                        JOptionPane.INFORMATION_MESSAGE
                );

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                        frame,
                        "Please enter a valid numeric ID",
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        return panel;
    }

    //    delete
    private JPanel createDeletePage() {

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(245, 247, 250));
        panel.setBorder(new EmptyBorder(25, 25, 25, 25));

        JLabel heading = new JLabel("Delete Employee");
        heading.setFont(new Font("Segoe UI", Font.BOLD, 28));
        heading.setForeground(new Color(33, 37, 41));
        heading.setBorder(new EmptyBorder(0, 0, 20, 0));

        panel.add(heading, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                new EmptyBorder(25, 25, 25, 25)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel idLabel = new JLabel("Employee ID");
        idLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));

        JTextField idField = new JTextField(20);
        idField.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JButton deleteBtn = new JButton("Delete Employee");
        deleteBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        deleteBtn.setBackground(new Color(220, 53, 69));
        deleteBtn.setForeground(Color.WHITE);
        deleteBtn.setFocusPainted(false);
        deleteBtn.setBorderPainted(false);
        deleteBtn.setPreferredSize(new Dimension(180, 40));

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(idLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(idField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(deleteBtn, gbc);

        panel.add(formPanel, BorderLayout.CENTER);

        deleteBtn.addActionListener(e -> {
            try {

                int id = Integer.parseInt(idField.getText());

                if (id <= 0) {
                    JOptionPane.showMessageDialog(
                            frame,
                            "Enter a valid ID!",
                            "Invalid Input",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }

                String employee = fileHandler.searchEmployee(id);

                if (employee == null || employee.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(
                            frame,
                            "Employee not found!",
                            "Delete Failed",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }

                String[] data = employee.split(",");

                int confirm = JOptionPane.showConfirmDialog(
                        frame,
                        "Delete Employee?\n\n" +
                                "ID: " + data[0] + "\n" +
                                "Name: " + data[1] + "\n" +
                                "Department: " + data[2] + "\n" +
                                "Role: " + data[3],
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );

                if (confirm == JOptionPane.YES_OPTION) {

                    fileHandler.delete(id);

                    JOptionPane.showMessageDialog(
                            frame,
                            "Employee Deleted Successfully!\n\n" +
                                    "ID: " + data[0] + "\n" +
                                    "Name: " + data[1],
                            "Deleted",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    idField.setText("");
                    idField.requestFocus();
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                        frame,
                        "Please enter a valid numeric ID",
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        return panel;
    }

    //update
    private JPanel createUpdatePage() {

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(245, 247, 250));
        panel.setBorder(new EmptyBorder(25, 25, 25, 25));

        JLabel heading = new JLabel("Update Employee");
        heading.setFont(new Font("Segoe UI", Font.BOLD, 28));
        heading.setForeground(new Color(33, 37, 41));
        heading.setBorder(new EmptyBorder(0, 0, 20, 0));

        panel.add(heading, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                new EmptyBorder(25, 25, 25, 25)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel idLabel = new JLabel("Employee ID");
        JLabel nameLabel = new JLabel("New Name");
        JLabel deptLabel = new JLabel("New Department");

        Font labelFont = new Font("Segoe UI", Font.BOLD, 14);

        idLabel.setFont(labelFont);
        nameLabel.setFont(labelFont);
        deptLabel.setFont(labelFont);

        JTextField idField = new JTextField(20);
        JTextField nameField = new JTextField(20);
        JTextField deptField = new JTextField(20);

        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 14);

        idField.setFont(fieldFont);
        nameField.setFont(fieldFont);
        deptField.setFont(fieldFont);

        JButton updateBtn = new JButton("Update Employee (Ctrl+S)");
        updateBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        updateBtn.setBackground(new Color(255, 193, 7));
        updateBtn.setForeground(Color.BLACK);
        updateBtn.setFocusPainted(false);
        updateBtn.setBorderPainted(false);
        updateBtn.setPreferredSize(new Dimension(220, 40));

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(idLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(idField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(nameLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(deptLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(deptField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(updateBtn, gbc);

        panel.add(formPanel, BorderLayout.CENTER);

        updateBtn.addActionListener(e -> {
            try {

                int id = Integer.parseInt(idField.getText());

                if (id <= 0) {
                    JOptionPane.showMessageDialog(
                            frame,
                            "Enter a valid ID!",
                            "Invalid Input",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }

                String employee = fileHandler.searchEmployee(id);

                if (employee == null || employee.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(
                            frame,
                            "Employee not found!",
                            "Update Failed",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }

                String newName = nameField.getText().trim();
                String newDept = deptField.getText().trim();

                if (newName.isEmpty() || newDept.isEmpty()) {
                    JOptionPane.showMessageDialog(
                            frame,
                            "All fields are required!",
                            "Invalid Input",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }

                fileHandler.update(id, newName, newDept);

                JOptionPane.showMessageDialog(
                        frame,
                        "Employee Updated Successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                idField.setText("");
                nameField.setText("");
                deptField.setText("");
                idField.requestFocus();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                        frame,
                        "Please enter a valid numeric ID",
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        KeyStroke ctrlS = KeyStroke.getKeyStroke("control S");

        panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(ctrlS, "updateEmployee");

        panel.getActionMap().put("updateEmployee", new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                updateBtn.doClick();
            }
        });

        return panel;
    }

    //show
    private JPanel createViewEmployeesPage() {

        JPanel panel = new JPanel(new BorderLayout(20, 20));
        panel.setBackground(new Color(245, 247, 250));
        panel.setBorder(new EmptyBorder(25, 25, 25, 25));

        JLabel heading = new JLabel("All Employees");
        heading.setFont(new Font("Segoe UI", Font.BOLD, 28));
        heading.setForeground(new Color(33, 37, 41));

        panel.add(heading, BorderLayout.NORTH);

        String[] columns = {
                "ID",
                "Name",
                "Department",
                "Role"
        };

        Object[][] data = fileHandler.displayEmployees();

        JTable table = new JTable(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        ((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(SwingConstants.CENTER);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(35);
        table.setSelectionBackground(new Color(230, 240, 255));
        table.setSelectionForeground(Color.BLACK);
//        table.setShowGrid(false);
        table.setShowGrid(true);
        table.setGridColor(new Color(220, 220, 220));
        table.setIntercellSpacing(new Dimension(1, 1));
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setFillsViewportHeight(true);

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
        header.setBackground(new Color(33, 37, 41));
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(header.getWidth(), 40));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                new EmptyBorder(10, 10, 10, 10)
        ));

        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(Color.WHITE);
        tablePanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                new EmptyBorder(10, 10, 10, 10)
        ));

        tablePanel.add(scrollPane, BorderLayout.CENTER);

        panel.add(tablePanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createSimplePage(String title) {

        JPanel panel = new JPanel(new BorderLayout());

        JLabel label = new JLabel(title, SwingConstants.CENTER);
        label.setFont(new Font("Segoe UI", Font.BOLD, 24));

        panel.add(label, BorderLayout.CENTER);

        return panel;
    }
}