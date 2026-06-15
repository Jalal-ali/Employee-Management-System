package ui;

import fileHandler.FileHandler;
import models.Employee;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Dashboard {
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

        JLabel title = new JLabel("Employee Management System");
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));

        JButton logoutBtn = new JButton("Logout");

        header.add(title, BorderLayout.WEST);
        header.add(logoutBtn, BorderLayout.EAST);

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

        dashboardBtn.addActionListener(e ->
                cardLayout.show(contentPanel, "dashboard"));

        addBtn.addActionListener(e ->
                cardLayout.show(contentPanel, "add"));

        searchBtn.addActionListener(e ->
                cardLayout.show(contentPanel, "search"));

        updateBtn.addActionListener(e ->
                cardLayout.show(contentPanel, "update"));

        deleteBtn.addActionListener(e ->
                cardLayout.show(contentPanel, "delete"));

        viewBtn.addActionListener(e ->
                cardLayout.show(contentPanel, "view"));

        sidebar.add(dashboardBtn);
        sidebar.add(addBtn);
        sidebar.add(searchBtn);
        sidebar.add(updateBtn);
        sidebar.add(deleteBtn);
        sidebar.add(viewBtn);

        return sidebar;
    }

    private JButton createMenuButton(String text) {

        JButton btn = new JButton(text);

        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setBackground(new Color(33, 37, 41));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 15));

        return btn;
    }

    private void createPages() {

        contentPanel.add(createDashboardPage(), "dashboard");
        contentPanel.add(createAddEmployeePage(), "add");
        contentPanel.add(createSearchPage(), "search");
        contentPanel.add(createUpdatePage(), "update");
        contentPanel.add(createDeletePage(), "delete");
        contentPanel.add(createViewEmployeesPage(), "view");
    }

    private JPanel createDashboardPage() {

        JPanel panel = new JPanel(new BorderLayout(20, 20));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel cardsPanel = new JPanel(new GridLayout(1, 3, 20, 20));
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
//    add
private JPanel createAddEmployeePage() {

    JPanel panel = new JPanel(new GridBagLayout());

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(10, 10, 10, 10);
    gbc.fill = GridBagConstraints.HORIZONTAL;

    JTextField idField = new JTextField(20);
    JTextField nameField = new JTextField(20);
    JTextField deptField = new JTextField(20);

    JButton saveBtn = new JButton("Save Employee (Ctrl+S)");

    gbc.gridx = 0;
    gbc.gridy = 0;
    panel.add(new JLabel("Employee ID"), gbc);

    gbc.gridx = 1;
    panel.add(idField, gbc);

    gbc.gridx = 0;
    gbc.gridy = 1;
    panel.add(new JLabel("Name"), gbc);

    gbc.gridx = 1;
    panel.add(nameField, gbc);

    gbc.gridx = 0;
    gbc.gridy = 2;
    panel.add(new JLabel("Department"), gbc);

    gbc.gridx = 1;
    panel.add(deptField, gbc);

    gbc.gridx = 1;
    gbc.gridy = 4;
    panel.add(saveBtn, gbc);

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
}//    search emp
    private JPanel createSearchPage() {

        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField idField = new JTextField(20);

        JButton searchBtn = new JButton("Search Employee");

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Employee ID"), gbc);

        gbc.gridx = 1;
        panel.add(idField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(searchBtn, gbc);
        searchBtn.addActionListener(e ->{
            try{
                int id = Integer.parseInt(idField.getText()) ;
                if(id <= 0){
                    JOptionPane.showMessageDialog(
                            frame,
                            "Enter a valid ID!",
                            "Invalid Input",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }
                String employee = fileHandler.searchEmployee(id);
                String[] data = employee.split(",");

                JPanel panel1 = new JPanel(new GridLayout(4, 2, 10, 10));

                panel1.add(new JLabel("ID:"));
                panel1.add(new JLabel(data[0]));

                panel1.add(new JLabel("Name:"));
                panel1.add(new JLabel(data[1]));

                panel1.add(new JLabel("Department:"));
                panel1.add(new JLabel(data[2]));

                panel1.add(new JLabel("Role:"));
                panel1.add(new JLabel(data[3]));


                if (employee.length() > 0) {
                    JOptionPane.showMessageDialog(
                            null,
                            panel1,
                            "Employee Details",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }else{
                    JOptionPane.showMessageDialog(panel1,
                            "Unable to find!",
                            "Failed",
                            JOptionPane.ERROR_MESSAGE );
                }
            }
            catch(NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid numeric ID");
                }
        });

        return panel;
    }
//    delete
private JPanel createDeletePage() {

    JPanel panel = new JPanel(new GridBagLayout());

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(10,10,10,10);
    gbc.fill = GridBagConstraints.HORIZONTAL;

    JTextField idField = new JTextField(20);

    JButton deleteBtn = new JButton("Delete Employee");

    gbc.gridx = 0;
    gbc.gridy = 0;
    panel.add(new JLabel("Employee ID"), gbc);

    gbc.gridx = 1;
    panel.add(idField, gbc);

    gbc.gridx = 1;
    gbc.gridy = 4;
    panel.add(deleteBtn, gbc);
    deleteBtn.addActionListener(e ->{
        try{
            int id = Integer.parseInt(idField.getText()) ;
            if(id <= 0){
                JOptionPane.showMessageDialog(
                        frame,
                        "Enter a valid ID!",
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }
            fileHandler.delete(id);
            JOptionPane.showMessageDialog(frame,"Deleted Successfully!");
        }
        catch(NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Please enter a valid numeric ID");
        }
    });

    return panel;
}
//update
private JPanel createUpdatePage() {

    JPanel panel = new JPanel(new GridBagLayout());

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(10, 10, 10, 10);
    gbc.fill = GridBagConstraints.HORIZONTAL;

    JTextField idField = new JTextField(20);
    JTextField nameField = new JTextField(20);
    JTextField deptField = new JTextField(20);

    JButton updateBtn = new JButton("Update Employee (Ctrl+S)");

    gbc.gridx = 0;
    gbc.gridy = 0;
    panel.add(new JLabel("Employee ID"), gbc);

    gbc.gridx = 1;
    panel.add(idField, gbc);

    gbc.gridx = 0;
    gbc.gridy = 1;
    panel.add(new JLabel("Name"), gbc);

    gbc.gridx = 1;
    panel.add(nameField, gbc);

    gbc.gridx = 0;
    gbc.gridy = 2;
    panel.add(new JLabel("Department"), gbc);

    gbc.gridx = 1;
    panel.add(deptField, gbc);

    gbc.gridx = 1;
    gbc.gridy = 4;
    panel.add(updateBtn, gbc);

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

            String newName = nameField.getText();
            String newDept = deptField.getText();

            fileHandler.update(id, newName, newDept);

            JOptionPane.showMessageDialog(
                    frame,
                    "Updated Successfully!",
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
    private JPanel createViewEmployeesPage() {

        JPanel panel = new JPanel(new BorderLayout());

        String[] columns = {
                "ID",
                "Name",
                "Department",
                "Role"
        };



        Object[][] data = fileHandler.displayEmployees();

        JTable table = new JTable(data, columns);

        panel.add(new JScrollPane(table), BorderLayout.CENTER);

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