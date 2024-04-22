package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import java.awt.event.ActionListener;

import controller.Controller;

public class RestoranFormScreen extends JFrame {

    private JTextField regionField;
    private JTextField nameField;
    private JTextField phoneNumberField;
    private JTextField usernameField;
    private JTextField passwordField;

    public RestoranFormScreen() {
        super("Add Restoran");
        initializeUI();
    }

    private void initializeUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel(new GridLayout(6, 2, 10, 10));
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Create form fields
        regionField = new JTextField(20);
        nameField = new JTextField(20);
        phoneNumberField = new JTextField(20);
        usernameField = new JTextField(20);
        passwordField = new JTextField(20);

        // Add labels and fields to the form
        addFormField(contentPane, "Region:", regionField);
        addFormField(contentPane, "Name:", nameField);
        addFormField(contentPane, "Phone Number:", phoneNumberField);
        addFormField(contentPane, "Username:", usernameField);
        addFormField(contentPane, "Password:", passwordField);

        JButton backButton = createButton("Kembali", e -> disposeAndOpenAdminScreen());
        JButton addButton = createButton("Add Restoran", e -> addRestoran());

        contentPane.add(backButton);
        contentPane.add(addButton);

        setContentPane(contentPane);
        setVisible(true);
    }

    private void addFormField(JPanel container, String labelName, JTextField textField) {
        container.add(new JLabel(labelName));
        container.add(textField);
    }

    private JButton createButton(String label, ActionListener actionListener) {
        JButton button = new JButton(label);
        button.addActionListener(actionListener);
        return button;
    }

    private void disposeAndOpenAdminScreen() {
        dispose();
        new AdminScreen();
    }

    private void addRestoran() {
        String regionStr = regionField.getText().trim();
        String name = nameField.getText().trim();
        String phoneNumber = phoneNumberField.getText().trim();
        String password = passwordField.getText();
        String username = usernameField.getText().trim();

        if (isEmptyField(regionStr) || isEmptyField(name) || isEmptyField(phoneNumber) || isEmptyField(password) || isEmptyField(username)) {
            showMessage("Please fill in all fields.");
            return;
        }

        int region;
        try {
            region = Integer.parseInt(regionStr);
        } catch (NumberFormatException ex) {
            showMessage("Invalid input for region");
            return;
        }

        boolean addRestoranSuccess = new Controller().addRestoran(region, name, phoneNumber, password, username);
        if (addRestoranSuccess) {
            showMessage("Restoran added successfully!");
            disposeAndOpenAdminScreen();
        } else {
            showMessage("Error adding Restoran");
        }
    }

    private boolean isEmptyField(String value) {
        return value.isEmpty();
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
