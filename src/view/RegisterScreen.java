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

    public RegisterScreen() {
        setTitle("Registrasi");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 600);
        setLocationRelativeTo(null);

        initComponents();
        addComponentsToPanel();
        pack(); 
    }

    private void initComponents() {
        nameField = new JTextField(20);
        addressField = new JTextField(20);
        phoneNumberField = new JTextField(20);
        usernameField = new JTextField(20);
        emailField = new JTextField(20);
        passwordField = new JPasswordField(20);

        maleRadioButton = new JRadioButton("Laki-laki");
        femaleRadioButton = new JRadioButton("Perempuan");
        ButtonGroup genderButtonGroup = new ButtonGroup();
        genderButtonGroup.add(maleRadioButton);
        genderButtonGroup.add(femaleRadioButton);

        birthdayDatePicker = new JDateChooser();
        idRegionField = new JTextField(20);
    }

    private void addComponentsToPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        addField(panel, gbc, "Nama:", nameField, 0);
        addField(panel, gbc, "Alamat:", addressField, 1);
        addField(panel, gbc, "Nomor Telepon:", phoneNumberField, 2);
        addField(panel, gbc, "Username:", usernameField, 3);
        addField(panel, gbc, "Email:", emailField, 4);
        addField(panel, gbc, "Password:", passwordField, 5);

        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(new JLabel("Jenis Kelamin:"), gbc);

        gbc.gridx = 1;
        panel.add(maleRadioButton, gbc);

        gbc.gridx = 2;
        panel.add(femaleRadioButton, gbc);

        addField(panel, gbc, "Tanggal Ulang Tahun:", birthdayDatePicker, 7);
        addField(panel, gbc, "Region:", idRegionField, 8);

        JButton registerButton = new JButton("Registrasi");
        registerButton.addActionListener(e -> registerUser());

        JButton backButton = new JButton("Kembali");
        backButton.addActionListener(e -> goBackToMainMenu());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(registerButton);
        buttonPanel.add(backButton);

        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(buttonPanel, gbc);

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setPreferredSize(new Dimension(480, 250));
        add(scrollPane);
    }

    private void addField(JPanel panel, GridBagConstraints gbc, String labelName, JComponent component, int yPos) {
        gbc.gridx = 0;
        gbc.gridy = yPos;
        panel.add(new JLabel(labelName), gbc);

        gbc.gridx = 1;
        panel.add(component, gbc);
    }

    private void registerUser() {
        String name = nameField.getText();
        String address = addressField.getText();
        String phoneNumber = phoneNumberField.getText();
        String username = usernameField.getText();
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());
        String gender = maleRadioButton.isSelected() ? "Male" : "Female";
        Date birthday = new Date(birthdayDatePicker.getDate().getTime());
        int idRegion = Integer.parseInt(idRegionField.getText());

        if (password.length() < 8) {
            JOptionPane.showMessageDialog(this,
                    "Password harus terdiri dari minimal 8 karakter.",
                    "Registrasi Gagal", JOptionPane.ERROR_MESSAGE);
            return;
        }

        User user = new User(name, address, phoneNumber, username, email, password, gender, birthday);
        user.setRegion(new Controller().getRegion(idRegion));
        user.setTotalBalance(0);

        Controller controller = new Controller();
        boolean registrationSuccess = controller.insertToUser(user);

        if (registrationSuccess) {
            JOptionPane.showMessageDialog(this,
                    "Registrasi berhasil. Data berhasil disimpan.",
                    "Registrasi Berhasil", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            new LoginScreen();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Registrasi gagal. Terjadi kesalahan.",
                    "Registrasi Gagal", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void goBackToMainMenu() {
        dispose();
        new MainMenuScreen();
    }
}
