package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import com.toedter.calendar.JDateChooser;
import java.awt.*;

import java.awt.event.ActionListener;
import java.sql.Date;
import controller.Controller;

public class DriverFormScreen extends JFrame {

    private JTextField nameField;
    private JTextField phoneNumberField;
    private JDateChooser birthDateField;
    private JTextField NIKField;
    private JTextField passwordField;
    private JTextField usernameField;
    private JTextField jenisKendaraanField;
    private JTextField modelKendaraanField;
    private JTextField numberPlateField;

    public DriverFormScreen() {
        super("Add Driver");
        initializeUI();
    }

    private void initializeUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel(new GridLayout(10, 2, 10, 10));
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Create form fields
        nameField = new JTextField(20);
        phoneNumberField = new JTextField(20);
        birthDateField = new JDateChooser();
        NIKField = new JTextField(20);
        passwordField = new JTextField(20);
        usernameField = new JTextField(20);
        jenisKendaraanField = new JTextField(20);
        modelKendaraanField = new JTextField(20);
        numberPlateField = new JTextField(20);

        // Add labels and fields to the form
        addFormField(contentPane, "Name:", nameField);
        addFormField(contentPane, "Phone Number:", phoneNumberField);
        addFormField(contentPane, "Birth Date:", birthDateField);
        addFormField(contentPane, "NIK:", NIKField);
        addFormField(contentPane, "Username:", usernameField);
        addFormField(contentPane, "Password:", passwordField);
        addFormField(contentPane, "Jenis Kendaraan:", jenisKendaraanField);
        addFormField(contentPane, "Model Kendaraan:", modelKendaraanField);
        addFormField(contentPane, "Plat Nomor Kendaraan:", numberPlateField);

        JButton backButton = createButton("Kembali", e -> disposeAndOpenAdminScreen());
        JButton addButton = createButton("Add Driver", e -> addDriver());

        contentPane.add(backButton);
        contentPane.add(addButton);

        setContentPane(contentPane);
        setVisible(true);
    }

    private void addFormField(JPanel container, String labelName, JComponent component) {
        container.add(new JLabel(labelName));
        container.add(component);
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

    private void addDriver() {
        String name = nameField.getText().trim();
        String phoneNumber = phoneNumberField.getText().trim();
        Date birthDate = new Date(birthDateField.getDate().getTime());
        String NIK = NIKField.getText().trim();
        String password = passwordField.getText();
        String username = usernameField.getText().trim();
        String jenisKendaraan = jenisKendaraanField.getText().trim();
        String modelKendaraan = modelKendaraanField.getText().trim();
        String numberPlate = numberPlateField.getText().trim();

        if (isEmptyField(name) || isEmptyField(phoneNumber) || isEmptyField(NIK) || isEmptyField(password) ||
                isEmptyField(jenisKendaraan) || isEmptyField(modelKendaraan) || isEmptyField(username) || isEmptyField(numberPlate)) {
            showMessage("Please fill in all fields.");
            return;
        }

        boolean addDriverSuccess = new Controller().addDriver(name, phoneNumber, birthDate, NIK, password, username, jenisKendaraan, modelKendaraan, numberPlate);
        if (addDriverSuccess) {
            showMessage("Driver added successfully!");
            disposeAndOpenAdminScreen();
        } else {
            showMessage("Error adding driver");
        }
    }

    private boolean isEmptyField(String value) {
        return value.isEmpty();
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
