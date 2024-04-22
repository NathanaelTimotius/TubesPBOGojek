package view;

import javax.swing.*;
import java.awt.*;
import controller.Controller;
import model.*;

public class RestoranLoginScreen {

    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public RestoranLoginScreen() {
        initializeUI();
    }

    private void initializeUI() {
        frame = createFrame();
        JPanel mainPanel = createMainPanel();

        frame.getContentPane().add(mainPanel);
        frame.setVisible(true);
    }

    private JFrame createFrame() {
        JFrame frame = new JFrame();
        frame.setTitle("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null);
        return frame;
    }

    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        usernameField = new JTextField();
        passwordField = new JPasswordField();

        mainPanel.add(createLabel("Username:"));
        mainPanel.add(usernameField);
        mainPanel.add(createLabel("Password:"));
        mainPanel.add(passwordField);
        mainPanel.add(createLoginButton());
        mainPanel.add(createBackButton());

        return mainPanel;
    }

    private JLabel createLabel(String text) {
        return new JLabel(text);
    }

    private JButton createLoginButton() {
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> login());
        return loginButton;
    }

    private JButton createBackButton() {
        JButton backButton = new JButton("Kembali");
        backButton.addActionListener(e -> back());
        return backButton;
    }

    private void login() {
        String username = usernameField.getText();
        String password = String.valueOf(passwordField.getPassword());

        if (isValidLogin(username, password)) {
            JOptionPane.showMessageDialog(frame, "Login Success as " + Controller.getInstance().currentRestoran.getRestaurantName());
            frame.setVisible(false);
            new RestoranScreen(Controller.getInstance().currentRestoran);
        } else {
            JOptionPane.showMessageDialog(frame, "Login failed!");
        }
    }

    private boolean isValidLogin(String username, String password) {
        for (Restaurant r : Controller.getInstance().getAllRestoran()) {
            if (r.getUsername().equals(username) && r.getPassword().equals(password)) {
                Controller.getInstance().currentRestoran = r;
                return true;
            }
        }
        return false;
    }

    private void back() {
        frame.dispose();
        new MainMenuScreen();
    }
}