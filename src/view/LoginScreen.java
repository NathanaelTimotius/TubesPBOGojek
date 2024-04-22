package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import controller.Controller;
import model.*;

public class LoginScreen {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JFrame frame;

    public LoginScreen() {
        frame = createFrame("Login");
        JPanel mainPanel = createMainPanel();

        frame.getContentPane().add(mainPanel);
        frame.setVisible(true);
    }

    private JFrame createFrame(String title) {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null);
        return frame;
    }

    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        usernameField = new JTextField();
        passwordField = new JPasswordField();

        mainPanel.add(new JLabel("Username:"));
        mainPanel.add(usernameField);
        mainPanel.add(new JLabel("Password:"));
        mainPanel.add(passwordField);
        mainPanel.add(createButton("Login", e -> loginUser()));
        mainPanel.add(createButton("Register", e -> openRegisterScreen()));
        mainPanel.add(createButton("Kembali", e -> openMainMenuScreen()));

        return mainPanel;
    }

    private JButton createButton(String label, ActionListener actionListener) {
        JButton button = new JButton(label);
        button.addActionListener(actionListener);
        return button;
    }

    private void openMainMenuScreen() {
        frame.dispose();
        new MainMenuScreen();
    }

    private void openRegisterScreen() {
        frame.dispose();
        RegisterScreen registerScreen = new RegisterScreen();
        registerScreen.setVisible(true);
    }

    private void loginUser() {
        String username = usernameField.getText();
        String password = String.valueOf(passwordField.getPassword());

        ArrayList<User> users = Controller.getInstance().getAllUsers();

        for (User u : users) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                Controller.getInstance().currentUser = u;
                JOptionPane.showMessageDialog(frame, "Login Success as " + u.getName());
                new UserScreen();
                frame.setVisible(false);
                return;
            }
        }

        JOptionPane.showMessageDialog(frame, "Login failed!");
    }
}