package view;

import controller.Controller;
import controller.ControllerView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import model.Menu;
import model.Restaurant;

public class GoFoodScreen {
    
    public GoFoodScreen(){
        showGoFoodScreen();
    }
    
    public void showGoFoodScreen(){
        ControllerView cv = new ControllerView();
        Controller con = new Controller();
        
        JFrame frame = new JFrame("Go Food");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JLabel title = new JLabel("List Restoran");
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel restaurantPanel = new JPanel(); 
        restaurantPanel.setLayout(new BoxLayout(restaurantPanel, BoxLayout.Y_AXIS));
        restaurantPanel.setAlignmentX(Component.CENTER_ALIGNMENT); 
        restaurantPanel.add(Box.createVerticalGlue());
        
        
        ArrayList<Restaurant> resto = con.getAllRestoran();
        JButton buttons[] = new JButton[resto.size()];
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = cv.CreateRestoranButton(resto.get(i).getRestaurantName());
            ArrayList<Menu> listMenu = resto.get(i).getListMenu();
            
            buttons[i].addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    frame.dispose();
                    new MenuScreen(listMenu);
                }
            });
            
            restaurantPanel.add(buttons[i]);
            restaurantPanel.add(Box.createVerticalStrut(10));
        }

        restaurantPanel.add(Box.createVerticalGlue());

        JScrollPane scrollPane = new JScrollPane(restaurantPanel); 
        
        mainPanel.add(title, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        frame.add(mainPanel);
        frame.setVisible(true);

    }
    
    
}
