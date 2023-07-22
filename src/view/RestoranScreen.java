package view;

import controller.Controller;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import model.Menu;
import model.MenuType;
import model.Restaurant;

public class RestoranScreen {
    private JFrame frame;
    private Restaurant restoran;
    
    public RestoranScreen(Restaurant restoran){
        this.restoran = restoran;
        showMainPage();
    }
    
    public void showMainPage() {
        frame = new JFrame("Restoran Driver App");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        mainPanel.setLayout(new GridLayout(4, 1, 10, 10));

        JLabel nameLabel = new JLabel("Nama Driver : " + restoran.getRestaurantName());
        mainPanel.add(nameLabel);

        JLabel platLabel = new JLabel("Alamat : " + restoran.getRegion().getRegionName());
        mainPanel.add(platLabel);

        JPanel buttonPanel = new JPanel();
        JButton addMenuButton = new JButton("Add Menu");
        JButton pendapatanButton = new JButton("Cek Total Pendapatan");
        buttonPanel.add(addMenuButton);
        buttonPanel.add(pendapatanButton);
        
        JPanel backPanel = new JPanel();
        JButton backButton =  new JButton("Kembali");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new MainMenuScreen();
            }
        });
        backPanel.add(backButton);
        
        
        addMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                showAddMenuScreen();
            }
        });

        pendapatanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Total pendapatan: " + restoran.getIncome());
            }
        });

        mainPanel.add(buttonPanel);
        mainPanel.add(backPanel);

        frame.add(mainPanel);
        frame.setVisible(true);
    }
    
    private void showAddMenuScreen(){
        JFrame frame = new JFrame("Add Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));

        JLabel nameLabel = new JLabel("Menu Name:");
        JTextField nameField = new JTextField();

        JLabel typeLabel = new JLabel("Menu Type:");
        String[] types = {"Food", "Drink"};
        JComboBox<String> typeComboBox = new JComboBox<>(types);

        JLabel priceLabel = new JLabel("Price:");
        JTextField priceField = new JTextField();

        JButton addButton = new JButton("Add Menu");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String menuName = nameField.getText();
                String menuType = (String) typeComboBox.getSelectedItem();
                String priceText = priceField.getText();

                if (menuName.isEmpty() || menuType.isEmpty() || priceText.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please fill in all the fields.", "Error", JOptionPane.ERROR_MESSAGE);
                    return; 
                }
                int price;
                try {
                    price = Integer.parseInt(priceText);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid price format", "Error", JOptionPane.ERROR_MESSAGE);
                    return; 
                }
                
                Menu menu = new Menu();
                menu.setMenuName(menuName);
                if (menuType.equals(MenuType.FOOD.toString())){
                    menu.setMenuType(MenuType.FOOD);
                } else {
                    menu.setMenuType(MenuType.DRINK);
                }
                menu.setPrice(price);
                
                boolean berhasil = new Controller().insertToMenu(menu);
                if (berhasil) {
                    boolean berhasil2 = new Controller().insertToRestoranMenu(restoran, menu);
                    if (berhasil2){
                        JOptionPane.showMessageDialog(frame, "Menu Added:\nName: " + menuName + "\nType: " + menuType + "\nPrice: " + price);
                        nameField.setText("");
                        priceField.setText("");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Adding Menu Error", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Adding Menu Error", "Error", JOptionPane.ERROR_MESSAGE);
                }
                
            }
        });
        JButton backButton =  new JButton("Kembali");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new MainMenuScreen();
            }
        });        

        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(typeLabel);
        inputPanel.add(typeComboBox);
        inputPanel.add(priceLabel);
        inputPanel.add(priceField);

        contentPanel.add(inputPanel, BorderLayout.NORTH);
        
        JPanel bottomRightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomRightPanel.add(addButton);
        bottomRightPanel.add(backButton);

        // Add containers to main frame
        frame.add(contentPanel, BorderLayout.CENTER);
        frame.add(bottomRightPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}
