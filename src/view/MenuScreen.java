/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import model.Menu;

public class MenuScreen {

    public MenuScreen(ArrayList<Menu> listMenu) {
        showMenuScreen(listMenu);
    }
    
    public void showMenuScreen(ArrayList<Menu> listMenu){ 
        JFrame frame = new JFrame("Go Food");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JLabel title = new JLabel("List Menu");
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel menuPanel = new JPanel(); 
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setAlignmentX(Component.CENTER_ALIGNMENT); 
        menuPanel.add(Box.createVerticalGlue());
        
        for (int i = 0; i < listMenu.size(); i++) {
            JPanel menuRow = new JPanel();
            menuRow.setLayout(new FlowLayout(FlowLayout.LEFT));
            
            JLabel label = new JLabel((i+1) +". "+ listMenu.get(i).getMenuName() + " : ");
            label.setFont(new Font("Arial", Font.BOLD, 18));
            
            JSpinner input = new JSpinner();
            input.setPreferredSize(new Dimension(50, 20));
            
            menuRow.add(label);
            menuRow.add(input);
            
            menuPanel.add(menuRow);
            menuPanel.add(Box.createVerticalStrut(10));
        }
        
        menuPanel.add(Box.createVerticalGlue());
        
        JScrollPane scrollPane = new JScrollPane(menuPanel); 
        
        mainPanel.add(title, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        
        
        frame.add(mainPanel);
        frame.setVisible(true);
    }
}
