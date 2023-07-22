package view;

import controller.Controller;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AdminScreen {
    public AdminScreen() {
        viewAdminScreen();
    }

    private static void viewAdminScreen() {
        JFrame frame = new JFrame("Admin Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(400, 400));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Panel atas untuk menampilkan profil pengguna
        JPanel profilePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel profileLabel = new JLabel("Profil Admin");
        profileLabel.setFont(new Font("Arial", Font.BOLD, 18));
        profilePanel.add(profileLabel);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10));

        JButton gojekButton = createIconButton("Add Driver", 100, 40, frame);
        buttonPanel.add(gojekButton);

        JButton gocarButton = createIconButton("Add Location", 100, 40, frame);
        buttonPanel.add(gocarButton);

        JButton gofoodButton = createIconButton("Add Restoran", 100, 40, frame);
        buttonPanel.add(gofoodButton);

        JButton gosendButton = createIconButton("Edit Voucher", 100, 40, frame);
        buttonPanel.add(gosendButton);
        
        JButton gopayButton = createIconButton("Delete Account", 100, 40, frame);
        buttonPanel.add(gopayButton);
        
        JPanel backPanel = new JPanel();
        JButton backButton =  new JButton("Kembali");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new MainMenuScreen();
            }
        });
        backPanel.add(backButton);
        
        mainPanel.add(profilePanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(backPanel, BorderLayout.SOUTH);
        
        frame.getContentPane().add(mainPanel);
        frame.setVisible(true);
    }

    private static JButton createIconButton(String text, int width, int height, JFrame frame) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setForeground(Color.WHITE);
        button.setBackground(Color.GREEN);
        button.setFocusPainted(false);
        button.setOpaque(true);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                if (text.equals("Gofood")){
                    new GoFoodScreen(new Controller().getInstance().currentUser);
                } else if (text.equals("Gojek")){
                    GoRideScreen goRideScreen = new GoRideScreen(new Controller().getInstance().currentUser);
                    goRideScreen.showMainPage();
                } else if (text.equals("Gopay")){
                    new GoPayScreen(new Controller().getInstance().currentUser);
                } else if (text.equals("Gocar")){
                    
                } else if (text.equals("Gosend")) {
                    GoSendScreen goSendScreen = new GoSendScreen(new Controller().getInstance().currentUser);
                    goSendScreen.showMainPage();
                }
                
            }
        });

        return button;
    }
}
