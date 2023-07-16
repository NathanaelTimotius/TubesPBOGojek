package view;

import controller.ControllerView;
import java.awt.*;
import javax.swing.*;

public class GoFoodScreen {
    
    public GoFoodScreen(){
        showGoFoodScreen();
    }
    
    public void showGoFoodScreen(){
        ControllerView cv = new ControllerView();
        
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
        

        // Create buttons for each restaurant
        JButton button1 = cv.CreateRestoranButton("Restaurant 1");
        JButton button2 = cv.CreateRestoranButton("Restaurant 2");
        JButton button3 = cv.CreateRestoranButton("Restaurant 3");
        
        
        restaurantPanel.add(button1);
        restaurantPanel.add(Box.createVerticalStrut(10)); // Add vertical gap between buttons
        restaurantPanel.add(button2);
        restaurantPanel.add(Box.createVerticalStrut(10)); // Add vertical gap between buttons
        restaurantPanel.add(button3);
        restaurantPanel.add(Box.createVerticalGlue());

        JScrollPane scrollPane = new JScrollPane(restaurantPanel); // Wrap the restaurant panel in a scroll pane

        mainPanel.add(title, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        frame.add(mainPanel);
        frame.setVisible(true);

    }
    
    
}
