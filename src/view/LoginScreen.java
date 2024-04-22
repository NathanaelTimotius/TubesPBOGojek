package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.Controller;
import java.util.ArrayList;
import model.*;

public class LoginScreen {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginScreen() {
        JFrame frame = new JFrame();
        frame.setTitle("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");

        JButton backButton =  new JButton("Kembali");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new MainMenuScreen();
            }
        });

        mainPanel.add(usernameLabel);
        mainPanel.add(usernameField);
        mainPanel.add(passwordLabel);
        mainPanel.add(passwordField);
        mainPanel.add(loginButton);
        mainPanel.add(registerButton);
        mainPanel.add(backButton);
        
        registerButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                RegisterScreen registerScreen = new RegisterScreen();
                registerScreen.setVisible(true);
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = String.valueOf(passwordField.getPassword());

                ArrayList<User> users = Controller.getInstance().getAllUsers();


                for (User u : users)
                {
                    if (u.getUsername().equals(username) && u.getPassword().equals(password))
                    {
                        Controller.getInstance().currentUser = u;
                        JOptionPane.showMessageDialog(frame, "Login Success as " + u.getName());
                        new UserScreen();
                        frame.setVisible(false);
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