package view;

import controller.Controller;
import model.Menu;
import model.MenuBuilder;
import model.MenuType;
import model.Restaurant;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RestoranScreen {
    private static final int FRAME_WIDTH = 400;
    private static final int FRAME_HEIGHT = 220;
    private static final String[] MENU_TYPES = {"Food", "Drink"};

    private JFrame frame;
    private Restaurant restaurant;

    private Controller controller;

    public RestoranScreen(Restaurant restaurant) {
        this.restaurant = restaurant;
        this.controller = new Controller();
        initialize();
    }

    private void initialize() {
        frame = createFrame("Restoran App");
        showMainPage();
    }

    public void showMainPage() {
        JPanel mainPanel = createMainPanel();

        JLabel nameLabel = new JLabel("Nama Restoran : " + restaurant.getRestaurantName());
        mainPanel.add(nameLabel);

        JLabel platLabel = new JLabel("Alamat : " + restaurant.getRegion().getRegionName());
        mainPanel.add(platLabel);

        JPanel buttonPanel = createButtonPanel();
        mainPanel.add(buttonPanel);

        JPanel backPanel = createBackPanel();
        mainPanel.add(backPanel);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private JFrame createFrame(String title) {
        JFrame frame = new JFrame(title);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        return frame;
    }

    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        mainPanel.setLayout(new GridLayout(4, 1, 10, 10)); // Adjusted grid layout rows
        return mainPanel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        JButton addMenuButton = new JButton("Add Menu");
        addMenuButton.addActionListener(e -> showAddMenuScreen());
        JButton pendapatanButton = new JButton("Cek Total Pendapatan");
        pendapatanButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Total pendapatan: " + restaurant.getIncome()));
        buttonPanel.add(addMenuButton);
        buttonPanel.add(pendapatanButton);
        return buttonPanel;
    }

    private JPanel createBackPanel() {
        JPanel backPanel = new JPanel();
        JButton backButton = new JButton("Kembali");
        backButton.addActionListener(e -> {
            frame.dispose(); // Dispose current frame
            new MainMenuScreen(); // Show main menu
        });
        backPanel.add(backButton);
        return backPanel;
    }

    private void showAddMenuScreen() {
        JFrame addMenuFrame = createFrame("Add Menu");
        JPanel contentPanel = createContentPanel();
        JPanel inputPanel = createInputPanel(addMenuFrame);
        contentPanel.add(inputPanel, BorderLayout.NORTH);
        JPanel bottomRightPanel = createBottomRightPanel(addMenuFrame);
        addMenuFrame.add(contentPanel, BorderLayout.CENTER);
        addMenuFrame.add(bottomRightPanel, BorderLayout.SOUTH);
        addMenuFrame.setVisible(true);
    }

    private JPanel createContentPanel() {
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        return contentPanel;
    }

    private JPanel createInputPanel(JFrame addMenuFrame) {
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        JLabel nameLabel = new JLabel("Menu Name:");
        JTextField nameField = new JTextField();
        JLabel typeLabel = new JLabel("Menu Type:");
        JComboBox<String> typeComboBox = new JComboBox<>(MENU_TYPES);
        JLabel priceLabel = new JLabel("Price:");
        JTextField priceField = new JTextField();
        JButton addButton = createAddButton(addMenuFrame, nameField, typeComboBox, priceField);
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(typeLabel);
        inputPanel.add(typeComboBox);
        inputPanel.add(priceLabel);
        inputPanel.add(priceField);
        inputPanel.add(addButton);
        return inputPanel;
    }

    private JButton createAddButton(JFrame addMenuFrame, JTextField nameField, JComboBox<String> typeComboBox, JTextField priceField) {
        JButton addButton = new JButton("Add Menu");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String menuName = nameField.getText();
                String menuType = (String) typeComboBox.getSelectedItem();
                String priceText = priceField.getText();
                if (menuName.isEmpty() || menuType.isEmpty() || priceText.isEmpty()) {
                    JOptionPane.showMessageDialog(addMenuFrame, "Please fill in all the fields.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int price;
                try {
                    price = Integer.parseInt(priceText);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(addMenuFrame, "Invalid price format", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Menu menu = createMenu(menuName, menuType, price);
                boolean success = controller.insertToMenu(menu);
                if (success) {
                    boolean success2 = controller.insertToRestoranMenu(restaurant, menu);
                    if (success2) {
                        JOptionPane.showMessageDialog(addMenuFrame, "Menu Added:\nName: " + menuName + "\nType: " + menuType + "\nPrice: " + price);
                        nameField.setText("");
                        priceField.setText("");
                        addMenuFrame.dispose(); // Dispose addMenuFrame after successful addition
                    } else {
                        JOptionPane.showMessageDialog(addMenuFrame, "Adding Menu Error", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(addMenuFrame, "Adding Menu Error", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        return addButton;
    }

    private Menu createMenu(String menuName, String menuType, int price) {
        MenuBuilder menuBuilder = new MenuBuilder();
        if (menuType.equalsIgnoreCase(MenuType.FOOD.toString())) {
            return menuBuilder.withMenuname(menuName).withMenuType(MenuType.FOOD).withPrice(price).build();
        } else {
            return menuBuilder.withMenuname(menuName).withMenuType(MenuType.DRINK).withPrice(price).build();
        }
    }

    private JPanel createBottomRightPanel(JFrame addMenuFrame) {
        JPanel bottomRightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton backButton = new JButton("Kembali");
        backButton.addActionListener(e -> {
            addMenuFrame.dispose();
            showMainPage();
        });
        bottomRightPanel.add(backButton);
        return bottomRightPanel;
    }
}
