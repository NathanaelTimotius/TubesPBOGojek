package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import java.awt.event.ActionListener;

import controller.Controller;

public class LocationFormScreen extends JFrame {

    private JTextField nameField;
    private JTextField positionField;

    public LocationFormScreen() {
        super("Add Location");
        initializeUI();
    }

    private void initializeUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 160);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel(new GridLayout(3, 2, 10, 10));
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));

        nameField = new JTextField(20);
        positionField = new JTextField(20);

        addFormField(contentPane, "Region Name:", nameField);
        addFormField(contentPane, "Region Position:", positionField);

        JButton backButton = createButton("Kembali", e -> disposeAndOpenAdminScreen());
        JButton addButton = createButton("Add Location", e -> addLocation());

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

    private void addLocation() {
        String name = nameField.getText().trim();
        String posStr = positionField.getText().trim();

        if (name.isEmpty() || posStr.isEmpty()) {
            showMessage("Please fill in all fields.");
            return;
        }

        int pos;
        try {
            pos = Integer.parseInt(posStr);
        } catch (NumberFormatException ex) {
            showMessage("Invalid input for position");
            return;
        }

        boolean addRegionSuccess = new Controller().addRegion(name, pos);
        if (addRegionSuccess) {
            showMessage("Location added successfully!");
            disposeAndOpenAdminScreen();
        } else {
            showMessage("Error adding Location");
        }
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
