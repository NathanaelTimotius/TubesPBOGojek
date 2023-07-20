package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class RegisterForm extends JFrame {
    private JTextField idRegionField, nameField, addressField, phoneNumberField, usernameField, emailField;
    private JDatePickerImpl birthDatePicker;
    private JButton registerButton;

    public RegisterForm() {
        setTitle("Registration Form");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create components
        idRegionField = new JTextField(10);
        nameField = new JTextField(20);
        addressField = new JTextField(30);
        phoneNumberField = new JTextField(15);
        usernameField = new JTextField(20);
        emailField = new JTextField(30);

        // Date picker
        UtilDateModel model = new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model);
        birthDatePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

        registerButton = new JButton("Register");

        // Register button click listener
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idRegion = Integer.parseInt(idRegionField.getText());
                String name = nameField.getText();
                String address = addressField.getText();
                String phoneNumber = phoneNumberField.getText();
                String username = usernameField.getText();
                String email = emailField.getText();
                Date birthDate = (Date) birthDatePicker.getModel().getValue();

                // TODO: Add code here to handle the registration process
                // For simplicity, we will just display the entered data in a dialog box
                String registrationData = "ID Region: " + idRegion +
                        "\nName: " + name +
                        "\nAddress: " + address +
                        "\nPhone Number: " + phoneNumber +
                        "\nUsername: " + username +
                        "\nEmail: " + email +
                        "\nBirth Date: " + new SimpleDateFormat("yyyy-MM-dd").format(birthDate);
                JOptionPane.showMessageDialog(RegisterForm.this, registrationData);
            }
        });

        // Create panel and add components to it
        JPanel panel = new JPanel(new GridLayout(7, 2));
        panel.add(new JLabel("ID Region (int):"));
        panel.add(idRegionField);
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Address:"));
        panel.add(addressField);
        panel.add(new JLabel("Phone Number:"));
        panel.add(phoneNumberField);
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Birth Date:"));
        panel.add(birthDatePicker);
        panel.add(registerButton);

        // Add panel to the frame
        add(panel);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RegisterForm();
            }
        });
    }
}
