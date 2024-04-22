package view;

import java.awt.*;
import javax.swing.*;

public class AdminScreen {

    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 400;
    private static final int BUTTON_WIDTH = 100;
    private static final int BUTTON_HEIGHT = 40;

    public AdminScreen() {
        viewAdminScreen();
    }

    private void viewAdminScreen() {
        JFrame frame = createFrame("Admin Screen");
        JPanel mainPanel = createMainPanel();

        JPanel profilePanel = createProfilePanel();
        JPanel buttonPanel = createButtonPanel(frame);
        JPanel backPanel = createBackPanel(frame);

        mainPanel.add(profilePanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(backPanel, BorderLayout.SOUTH);

        frame.getContentPane().add(mainPanel);
        frame.setVisible(true);
    }

    private JFrame createFrame(String title) {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setLocationRelativeTo(null);
        return frame;
    }

    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        return mainPanel;
    }

    private JPanel createProfilePanel() {
        JPanel profilePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel profileLabel = new JLabel("Profil Admin");
        profileLabel.setFont(new Font("Arial", Font.BOLD, 18));
        profilePanel.add(profileLabel);
        return profilePanel;
    }

    private JPanel createButtonPanel(JFrame frame) {
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        buttonPanel.add(createIconButton("Add Driver", BUTTON_WIDTH, BUTTON_HEIGHT, frame));
        buttonPanel.add(createIconButton("Add Location", BUTTON_WIDTH, BUTTON_HEIGHT, frame));
        buttonPanel.add(createIconButton("Add Restoran", BUTTON_WIDTH, BUTTON_HEIGHT, frame));
        return buttonPanel;
    }

    private JPanel createBackPanel(JFrame frame) {
        JPanel backPanel = new JPanel();
        JButton backButton =  new JButton("Kembali");
        backButton.addActionListener(e -> {
            frame.dispose();
            new MainMenuScreen();
        });
        backPanel.add(backButton);
        return backPanel;
    }

    private JButton createIconButton(String text, int width, int height, JFrame frame) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setForeground(Color.BLACK);
        button.setBackground(new Color(0, 255, 51));
        button.setFocusPainted(false);
        button.setOpaque(true);

        button.addActionListener(e -> {
            frame.dispose();
            switch (text) {
                case "Add Driver":
                    new DriverFormScreen();
                    break;
                case "Add Location":
                    new LocationFormScreen();
                    break;
                case "Add Restoran":
                    new RestoranFormScreen();
                    break;
                default:
                    break;
            }
        });

        return button;
    }
}