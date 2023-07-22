package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import com.toedter.calendar.JDateChooser;
import controller.Controller;
import model.User;

public class RegisterScreen extends JFrame {
    private JTextField nameField;
    private JTextField addressField;
    private JTextField phoneNumberField;
    private JTextField usernameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;
    private JDateChooser birthdayDatePicker;
    private JTextField idRegionField;
    private JFrame frame; 
    
    public RegisterScreen() {
        setTitle("Registrasi");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        
        frame = this;
        
        initUI();
    }

    private void initUI() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Name Label and Field
        JLabel nameLabel = new JLabel("Nama:");
        nameField = new JTextField(20);

        // Address Label and Field
        JLabel addressLabel = new JLabel("Alamat:");
        addressField = new JTextField(20);

        // Phone Number Label and Field
        JLabel phoneNumberLabel = new JLabel("Nomor Telepon:");
        phoneNumberField = new JTextField(20);

        // Username Label and Field
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(20);

        // Email Label and Field
        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField(20);

        // Password Label and Field
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);

        // Gender Label and Radio Buttons
        JLabel genderLabel = new JLabel("Jenis Kelamin:");
        maleRadioButton = new JRadioButton("Laki-laki");
        femaleRadioButton = new JRadioButton("Perempuan");
        ButtonGroup genderButtonGroup = new ButtonGroup();
        genderButtonGroup.add(maleRadioButton);
        genderButtonGroup.add(femaleRadioButton);

        // Birthday DatePicker
        JLabel birthdayLabel = new JLabel("Tanggal Ulang Tahun:");
        birthdayDatePicker = new JDateChooser();

        // Photo FileChooser
        JLabel idRegion = new JLabel("Region:");
        idRegionField = new JTextField(20);
        
        JPanel backPanel = new JPanel();
        JButton backButton =  new JButton("Kembali");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new MainMenuScreen();
            }
        });
        backPanel.add(backButton);

        // Register Button
        JButton registerButton = new JButton("Registrasi");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String address = addressField.getText();
                String phoneNumber = phoneNumberField.getText();
                String username = usernameField.getText();
                String email = emailField.getText();
                char[] password = passwordField.getPassword();
                String passwordString = new String(password);
                String passwordStr = new String(password);
                String gender = maleRadioButton.isSelected() ? "Male" : "Female";
                Date birthday = new java.sql.Date(birthdayDatePicker.getDate().getTime());
                int idRegion = Integer.parseInt(idRegionField.getText());

                if (passwordStr.length() < 8) {
                    JOptionPane.showMessageDialog(RegisterScreen.this,
                            "Password harus terdiri dari minimal 8 karakter.",
                            "Registrasi Gagal", JOptionPane.ERROR_MESSAGE);
                } else {
                    User user = new User();
                    user.setRegion(new Controller().getRegion(idRegion));
                    user.setName(name);
                    user.setAddress(address);
                    user.setPhoneNumber(phoneNumber);
                    user.setBirthDate(birthday);
                    user.setUsername(username);
                    user.setEmail(email);
                    user.setPassword(passwordString);
                    user.setGender(gender);
                    user.setTotalBalance(0);
                    
                    boolean berhasil = new Controller().insertToUser(user);
                    if (berhasil){
                        frame.dispose();
                        JOptionPane.showMessageDialog(RegisterScreen.this,
                            "Registrasi berhasil. Data berhasil disimpan.",
                            "Registrasi Berhasil", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Registration Error", "Error", JOptionPane.ERROR_MESSAGE);

                    }
                    
                }
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(nameLabel, gbc);

        gbc.gridx = 1;
        panel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(addressLabel, gbc);

        gbc.gridx = 1;
        panel.add(addressField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(phoneNumberLabel, gbc);

        gbc.gridx = 1;
        panel.add(phoneNumberField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        panel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(emailLabel, gbc);

        gbc.gridx = 1;
        panel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        panel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(genderLabel, gbc);

        gbc.gridx = 1;
        panel.add(maleRadioButton, gbc);

        gbc.gridx = 2;
        panel.add(femaleRadioButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        panel.add(birthdayLabel, gbc);

        gbc.gridx = 1;
        panel.add(birthdayDatePicker, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        panel.add(idRegion, gbc);

        gbc.gridx = 1;
        panel.add(idRegionField, gbc);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(registerButton);
        buttonPanel.add(backPanel);
        
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(buttonPanel, gbc);

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setPreferredSize(new Dimension(480, 250));
        add(scrollPane);
    }
}
