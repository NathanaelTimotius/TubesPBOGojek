package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.Controller;
import java.util.ArrayList;
import model.*;

public class DriverLoginScreen {

    private JTextField nikField;
    private JPasswordField passwordField;

    public DriverLoginScreen() {
        JFrame frame = new JFrame();
        frame.setTitle("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel nikLabel = new JLabel("NIK:");
        nikField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");

        mainPanel.add(nikLabel);
        mainPanel.add(nikField);
        mainPanel.add(passwordLabel);
        mainPanel.add(passwordField);
        mainPanel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nik = nikField.getText();
                String password = String.valueOf(passwordField.getPassword());

                ArrayList<Driver> drivers = Controller.getInstance().getAllDrivers();
                for (Driver d : drivers)
                {
                    if (d.getNIK().equals(nik) && d.getPassword().equals(password))
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

        frame.getContentPane().add(mainPanel);
        frame.setVisible(true);
    }
}