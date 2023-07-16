package view;

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
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Panel atas untuk menampilkan profil pengguna
        JPanel profilePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel profileLabel = new JLabel("Profil Pengguna");
        profileLabel.setFont(new Font("Arial", Font.BOLD, 18));
        profilePanel.add(profileLabel);

        // Panel tengah dengan layout grid 2x2 untuk tombol
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10));

        JButton gojekButton = createIconButton("Gojek",
                "C:\\Users\\hp\\OneDrive\\Desktop\\Jason\\Kuliah\\Semester Pendek 1\\Praktikum PBO\\TubesPBOGojek\\src\\view\\gojek.png",
                100, 40, frame);
        buttonPanel.add(gojekButton);

        JButton gocarButton = createIconButton("Gocar",
                "C:\\Users\\hp\\OneDrive\\Desktop\\Jason\\Kuliah\\Semester Pendek 1\\Praktikum PBO\\TubesPBOGojek\\src\\view\\gojek.png",
                100, 40, frame);
        buttonPanel.add(gocarButton);

        JButton gofoodButton = createIconButton("Gofood",
                "C:\\Users\\hp\\OneDrive\\Desktop\\Jason\\Kuliah\\Semester Pendek 1\\Praktikum PBO\\TubesPBOGojek\\src\\view\\gojek.png",
                100, 40, frame);
        buttonPanel.add(gofoodButton);

        JButton gosendButton = createIconButton("Gosend",
                "C:\\Users\\hp\\OneDrive\\Desktop\\Jason\\Kuliah\\Semester Pendek 1\\Praktikum PBO\\TubesPBOGojek\\src\\view\\gojek.png",
                100, 40, frame);
        buttonPanel.add(gosendButton);

        mainPanel.add(profilePanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        frame.getContentPane().add(mainPanel);
        frame.setVisible(true);
    }

    private static JButton createIconButton(String text, String iconName, int width, int height, JFrame frame) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 10));
        button.setForeground(Color.WHITE);
        button.setBackground(Color.GREEN);
        button.setFocusPainted(false);
        button.setOpaque(true);

        ImageIcon icon = new ImageIcon(iconName);
        Image image = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(image);

        button.setIcon(scaledIcon);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Anda memilih: " + text);
                frame.dispose();
                if (text.equals("Gofood")){
                    new GoFoodScreen();
                }
                
            }
        });

        return button;
    }

    // private static JButton createIconButton(String text, String iconName) {
    //     JButton button = new JButton(text);
    //     button.setFont(new Font("Arial", Font.BOLD, 18));
    //     button.setForeground(Color.WHITE);
    //     button.setBackground(Color.GREEN);
    //     button.setFocusPainted(false);
    //     button.setOpaque(true);

    //     ImageIcon icon = new ImageIcon(iconName);
    //     button.setIcon(icon);

    //     button.addActionListener(new ActionListener() {
    //         @Override
    //         public void actionPerformed(ActionEvent e) {
    //             JOptionPane.showMessageDialog(null, "Anda memilih: " + text);
    //         }
    //     });

    //     return button;
    // }

}
