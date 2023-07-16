package controller;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;

public class ControllerView {
    public JButton CreateRestoranButton(String namaRestoran){
        JButton button = new JButton(namaRestoran);
        button.setPreferredSize(new Dimension(300, 50)); 
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(300, 50)); 
        button.setFont(new Font("Arial", Font.BOLD, 18));
        
        return button;
    }
}
