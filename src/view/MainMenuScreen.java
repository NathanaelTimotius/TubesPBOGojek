package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainMenuScreen {

    public MainMenuScreen() {
        JFrame frame = createMainFrame();
        JPanel mainPanel = createMainPanel();

        addWelcomePanel(mainPanel);
        addOptionsPanel(mainPanel, frame);

        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private JFrame createMainFrame() {
        JFrame frame = new JFrame("GOJEK");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        return frame;
    }

    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        return mainPanel;
    }

    private void addWelcomePanel(JPanel mainPanel) {
        JPanel welcomePanel = new JPanel();
        welcomePanel.setBackground(new Color(102, 255, 51));
        JLabel welcomeLabel = new JLabel("Welcome to GOJEK!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomePanel.add(welcomeLabel);
        mainPanel.add(welcomePanel, BorderLayout.NORTH);
    }

    private void addOptionsPanel(JPanel mainPanel, JFrame frame) {
        JPanel optionsPanel = new JPanel(new GridLayout(4, 1, 10, 10));

        addButtonToPanel(optionsPanel, "Admin", new Color(0, 0, 0), e -> openAdminLoginScreen(frame));
        addButtonToPanel(optionsPanel, "User", new Color(0, 0, 0), e -> openUserLoginScreen(frame));
        addButtonToPanel(optionsPanel, "Driver", new Color(0, 0, 0), e -> openDriverLoginScreen(frame));
        addButtonToPanel(optionsPanel, "Restaurant", new Color(0, 0, 0), e -> openRestaurantLoginScreen(frame));

        mainPanel.add(optionsPanel, BorderLayout.CENTER);
    }

    private void addButtonToPanel(JPanel panel, String buttonText, Color buttonColor, ActionListener listener) {
        JButton button = createStyledButton(buttonText, buttonColor);
        button.addActionListener(listener);
        panel.add(button);
    }

    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setForeground(Color.WHITE);
        button.setBackground(color);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        return button;
    }

    private void openAdminLoginScreen(JFrame frame) {
        frame.dispose();
        new AdminLoginScreen();
    }

    private void openUserLoginScreen(JFrame frame) {
        frame.dispose();
        new LoginScreen();
    }

    private void openDriverLoginScreen(JFrame frame) {
        frame.dispose();
        new DriverLoginScreen();
    }

    private void openRestaurantLoginScreen(JFrame frame) {
        frame.dispose();
        new RestoranLoginScreen();
    }
}
