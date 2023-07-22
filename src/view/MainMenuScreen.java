package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuScreen {

    public MainMenuScreen() {
        JFrame frame = new JFrame();
        frame.setTitle("GOJEK");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel welcomePanel = new JPanel();
        welcomePanel.setBackground(new Color(102, 255, 51));
        JLabel welcomeLabel = new JLabel("Welcome to GOJEK!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomePanel.add(welcomeLabel);

        JLabel loginLabel = new JLabel("Anda ingin login sebagai:");
        loginLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JPanel optionsPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        optionsPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        JButton adminButton = createStyledButton("Admin", new Color(0, 0 ,0));
        JButton userButton = createStyledButton("User", new Color(0, 0, 0));
        JButton driverButton = createStyledButton("Driver", new Color(0, 0, 0));
        JButton restaurantButton = createStyledButton("Restaurant", new Color(0, 0, 0));
        adminButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new AdminLoginScreen();
            }
        });
        userButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new LoginScreen();
            }
        });
        driverButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new DriverLoginScreen();
            }
        });
        restaurantButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new RestoranLoginScreen();
            }
        });

        optionsPanel.add(adminButton);
        optionsPanel.add(userButton);
        optionsPanel.add(driverButton);
        optionsPanel.add(restaurantButton);

        mainPanel.add(welcomePanel, BorderLayout.NORTH);
        mainPanel.add(optionsPanel, BorderLayout.CENTER);

        
        

        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);

        frame.setVisible(true);
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

}
