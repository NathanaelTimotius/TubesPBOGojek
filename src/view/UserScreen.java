package view;

import controller.Controller;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserScreen {

    public UserScreen() {
        viewUserScreen();
    }

    private static void viewUserScreen() {
        JFrame frame = new JFrame("User Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(400, 400));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Panel atas untuk menampilkan profil pengguna
        JPanel profilePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel profileLabel = new JLabel("Profil Pengguna");
        profileLabel.setFont(new Font("Arial", Font.BOLD, 18));
        profilePanel.add(profileLabel);

        // Panel tengah dengan layout grid 2x2 untuk tombol
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10));

        JButton gojekButton = createIconButton("Gojek","gojek.png", 100, 40, frame);
        buttonPanel.add(gojekButton);

        JButton gocarButton = createIconButton("Gocar", "gojek.png", 100, 40, frame);
        buttonPanel.add(gocarButton);

        JButton gofoodButton = createIconButton("Gofood", "gojek.png", 100, 40, frame);
        buttonPanel.add(gofoodButton);

        JButton gosendButton = createIconButton("Gosend","gojek.png", 100, 40, frame);
        buttonPanel.add(gosendButton);
        
        JButton gopayButton = createIconButton("Gopay","gojek.png", 100, 40, frame);
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

    private static JButton createIconButton(String text, String iconName, int width, int height, JFrame frame) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setForeground(Color.BLACK);
        button.setBackground(new Color(0, 255, 51));
        button.setFocusPainted(false);
        button.setOpaque(true);

        ImageIcon icon = new ImageIcon(iconName);
        Image image = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(image);

        button.setIcon(scaledIcon);


        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Controller();
                frame.dispose();
                if (text.equals("Gofood")){
                    new GoFoodScreen(Controller.getInstance().currentUser);
                } else if (text.equals("Gojek")){
                    GoRideScreen goRideScreen = new GoRideScreen(Controller.getInstance().currentUser);
                    goRideScreen.showMainPage();
                } else if (text.equals("Gopay")){
                    new GoPayScreen(Controller.getInstance().currentUser);
                } else if (text.equals("Gocar")){
                    GoCarScreen goCarScreen = new GoCarScreen(Controller.getInstance().currentUser);
                    goCarScreen.showMainPage();
                } else if (text.equals("Gosend")) {
                    GoSendScreen goSendScreen = new GoSendScreen(Controller.getInstance().currentUser);
                    goSendScreen.showMainPage();
                }
                
            }
        });

        return button;
    }

}
