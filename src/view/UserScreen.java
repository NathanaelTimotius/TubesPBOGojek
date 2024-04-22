package view;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class UserScreen {

    public UserScreen() {
        displayUserScreen();
    }

    private void displayUserScreen() {
        JFrame frame = createMainFrame();
        JPanel mainPanel = createMainPanel(frame);

        JPanel profilePanel = createProfilePanel();
        JPanel buttonPanel = createButtonPanel(frame);

        mainPanel.add(profilePanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        frame.getContentPane().add(mainPanel);
        frame.setVisible(true);
    }

    private JFrame createMainFrame() {
        JFrame frame = new JFrame("User Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        return frame;
    }

    private JPanel createMainPanel(JFrame frame) {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(400, 400));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton backButton = createTextButton("Kembali", e -> {
            frame.dispose();
            new MainMenuScreen();
        });

        JPanel backPanel = new JPanel();
        backPanel.add(backButton);
        mainPanel.add(backPanel, BorderLayout.SOUTH);

        return mainPanel;
    }

    private JPanel createProfilePanel() {
        JPanel profilePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel profileLabel = new JLabel("Profil Pengguna");
        profileLabel.setFont(new Font("Arial", Font.BOLD, 18));
        profilePanel.add(profileLabel);
        return profilePanel;
    }

    private JPanel createButtonPanel(JFrame frame) {
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10));

        addButtonToPanel(buttonPanel, "Gojek", "gojek.png", e -> showGojekScreen(frame));
        addButtonToPanel(buttonPanel, "Gocar", "gojek.png", e -> showGocarScreen(frame));
        addButtonToPanel(buttonPanel, "Gofood", "gojek.png", e -> showGofoodScreen(frame));
        addButtonToPanel(buttonPanel, "Gosend", "gojek.png", e -> showGosendScreen(frame));
        addButtonToPanel(buttonPanel, "Gopay", "gojek.png", e -> showGopayScreen(frame));

        return buttonPanel;
    }

    private JButton createTextButton(String text, ActionListener listener) {
        JButton button = new JButton(text);
        button.addActionListener(listener);
        return button;
    }

    private void addButtonToPanel(JPanel panel, String buttonText, String iconName, ActionListener listener) {
        JButton button = createIconButton(buttonText, iconName);
        button.addActionListener(listener);
        panel.add(button);
    }

    private JButton createIconButton(String text, String iconName) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setForeground(Color.BLACK);
        button.setBackground(new Color(0, 255, 51));
        button.setFocusPainted(false);
        button.setOpaque(true);

        ImageIcon icon = new ImageIcon(iconName);
        Image scaledImage = icon.getImage().getScaledInstance(100, 40, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        button.setIcon(scaledIcon);
        return button;
    }

    private void showGojekScreen(JFrame frame) {
        frame.dispose();
        GoRideScreen goRideScreen = new GoRideScreen(Controller.getInstance().currentUser);
        goRideScreen.showMainPage();
    }

    private void showGocarScreen(JFrame frame) {
        frame.dispose();
        GoCarScreen goCarScreen = new GoCarScreen(Controller.getInstance().currentUser);
        goCarScreen.showMainPage();
    }

    private void showGofoodScreen(JFrame frame) {
        frame.dispose();
        new GoFoodScreen(Controller.getInstance().currentUser);
    }

    private void showGosendScreen(JFrame frame) {
        frame.dispose();
        GoSendScreen goSendScreen = new GoSendScreen(Controller.getInstance().currentUser);
        goSendScreen.showMainPage();
    }

    private void showGopayScreen(JFrame frame) {
        frame.dispose();
        new GoPayScreen(Controller.getInstance().currentUser);
    }
}
