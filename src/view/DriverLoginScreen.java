package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.Controller;
import java.util.ArrayList;
import model.*;

public class DriverLoginScreen {

    private static final int FRAME_WIDTH = 300;
    private static final int FRAME_HEIGHT = 200;

    private JTextField usernameField;
    private JPasswordField passwordField;

    public DriverLoginScreen() {
        JFrame frame = createFrame("Login");
        JPanel mainPanel = createMainPanel();

        JLabel nikLabel = new JLabel("Username:");
        usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        JButton backButton = createBackButton(frame);
        JButton loginButton = createLoginButton(frame);

        addComponentsToPanel(mainPanel, nikLabel, usernameField, passwordLabel, passwordField, loginButton, backButton);

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
        JPanel mainPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        return mainPanel;
    }

    private JButton createLoginButton(JFrame frame) {
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = String.valueOf(passwordField.getPassword());

                ArrayList<Driver> drivers = Controller.getInstance().getAllDrivers();
                for (Driver d : drivers)
                {
                    if (d.getUsername().equals(username) && d.getPassword().equals(password))
                    {
                        Controller.getInstance().currentDriver = d;
                        JOptionPane.showMessageDialog(frame, "Login Success as " + d.getName());
                        frame.setVisible(false);
                        DriverScreen driverScreen = new DriverScreen(Controller.getInstance().currentDriver);
                        driverScreen.showMainPage();
                        return;
                    }
                }

                JOptionPane.showMessageDialog(frame, "Login failed!");

            }
        });
        return loginButton;
    }

    private void addComponentsToPanel(JPanel panel, Component... components) {
        for (Component component : components) {
            panel.add(component);
        }
    }

    private JButton createBackButton(JFrame frame) {
        JButton backButton =  new JButton("Kembali");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new MainMenuScreen();
            }
        });
        return backButton;
    }
}