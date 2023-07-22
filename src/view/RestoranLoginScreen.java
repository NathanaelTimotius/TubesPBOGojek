package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.Controller;
import java.util.ArrayList;
import model.*;

public class RestoranLoginScreen {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public RestoranLoginScreen() {
        JFrame frame = new JFrame();
        frame.setTitle("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel nikLabel = new JLabel("Username:");
        usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");
        JButton backButton =  new JButton("Kembali");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new MainMenuScreen();
            }
        });
        
        
        mainPanel.add(nikLabel);
        mainPanel.add(usernameField);
        mainPanel.add(passwordLabel);
        mainPanel.add(passwordField);
        mainPanel.add(loginButton);
        mainPanel.add(backButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = String.valueOf(passwordField.getPassword());

                ArrayList<Restaurant> restorans = Controller.getInstance().getAllRestoran();
                for (Restaurant r : restorans)
                {
                    if (r.getUsername().equals(username) && r.getPassword().equals(password))
                    {
                        Controller.getInstance().currentRestoran = r;
                        frame.setVisible(false);
                        JOptionPane.showMessageDialog(frame, "Login Success as " + r.getRestaurantName());
                        new RestoranScreen(Controller.getInstance().currentRestoran);
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