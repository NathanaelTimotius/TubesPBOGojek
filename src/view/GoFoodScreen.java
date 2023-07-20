package view;

import controller.Controller;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import model.Cart;
import model.Menu;
import model.Region;
import model.Restaurant;

public class GoFoodScreen {
    
    public GoFoodScreen(){
        showGoFoodScreen();
    }
    
    public void showGoFoodScreen(){
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
            buttons[i] = new JButton(resto.get(i).getRestaurantName());
            buttons[i].setPreferredSize(new Dimension(300, 50)); 
            buttons[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            buttons[i].setMaximumSize(new Dimension(300, 50)); 
            buttons[i].setFont(new Font("Arial", Font.BOLD, 18));
            
            ArrayList<Menu> listMenu = resto.get(i).getListMenu();
            int idRegionRestoran = resto.get(i).getRestaurantID();
            
            buttons[i].addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    frame.dispose();
                    showMenuScreen(listMenu, idRegionRestoran);
                }
            });
            
            restaurantPanel.add(buttons[i]);
            restaurantPanel.add(Box.createVerticalStrut(10));
        }
        JPanel backPanel = new JPanel();
        
        JButton backButton =  new JButton("Kembali");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new UserScreen();
            }
        });
        backPanel.add(backButton);
        
        restaurantPanel.add(backPanel);
        restaurantPanel.add(Box.createVerticalGlue());

        JScrollPane scrollPane = new JScrollPane(restaurantPanel); 
        
        mainPanel.add(title, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        frame.add(mainPanel);
        frame.setVisible(true);

    }
    
    public void showMenuScreen(ArrayList<Menu> listMenu, int idRegionRestoran){ 
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
        
        JLabel label[] = new JLabel[listMenu.size()];
        JSpinner input[] = new JSpinner[listMenu.size()];
        
        for (int i = 0; i < listMenu.size(); i++) {
            JPanel menuRow = new JPanel();
            menuRow.setLayout(new FlowLayout(FlowLayout.LEFT));
            
            label[i] = new JLabel((i+1) +". "+ listMenu.get(i).getMenuName() + " : ");
            label[i].setFont(new Font("Arial", Font.BOLD, 18));
            
            input[i] = new JSpinner();
            input[i].setPreferredSize(new Dimension(50, 20));
            
            menuRow.add(label[i]);
            menuRow.add(input[i]);
            
            menuPanel.add(menuRow);
            menuPanel.add(Box.createVerticalStrut(10));
        }
        ArrayList<Cart> listCart = new ArrayList<Cart>();
        JButton submit = new JButton("Submit");
        submit.setPreferredSize(new Dimension(300, 50)); 
        submit.setAlignmentX(Component.CENTER_ALIGNMENT);
        submit.setMaximumSize(new Dimension(300, 50)); 
        submit.setFont(new Font("Arial", Font.BOLD, 18));
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean inputValid = false;
                for (int i = 0; i < input.length; i++) {
                    int inputValue = (int) input[i].getValue();
                    if (inputValue > 0) {
                        inputValid = true;
                        break;
                    }
                }

                if (inputValid) {
                    for (int i = 0; i < input.length; i++) {
                        int inputValue = (int) input[i].getValue();
                        if (inputValue > 0) {
                            Menu menu = new Menu(listMenu.get(i).getMenuName(), listMenu.get(i).getMenuType(), listMenu.get(i).getPrice());
                            listCart.add(new Cart(menu, inputValue));
                        }
                    }
                    frame.dispose();
                    showFormPengirimanGoFood(listMenu, listCart, idRegionRestoran);
                } else {
                    JOptionPane.showMessageDialog(frame, "Input Invalid", "Peringatan", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        
        menuPanel.add(submit);
        menuPanel.add(Box.createVerticalStrut(10));

        
        
        JPanel backPanel = new JPanel();
        JButton backButton =  new JButton("Kembali");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new GoFoodScreen();
            }
        });
        backPanel.add(backButton);
        
        menuPanel.add(backPanel);
        menuPanel.add(Box.createVerticalGlue());
        
        
        
        JScrollPane scrollPane = new JScrollPane(menuPanel); 
        
        mainPanel.add(title, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        
        frame.add(mainPanel);
        frame.setVisible(true);
    }
    
    public void showFormPengirimanGoFood(ArrayList<Menu> listMenu, ArrayList<Cart> listCart, int idRegionRestoran){
        Controller con = new Controller();
        ArrayList<Region> allRegion = con.getAllRegion();
        String text = "Pilih alamat anda : \n";
        for (int i = 0; i < allRegion.size(); i++) {
            text +=  (i+1) + ". " + allRegion.get(i).getRegionName() + "\n";
        }
        String temp = JOptionPane.showInputDialog(null, text, "Region", JOptionPane.QUESTION_MESSAGE);
        if (temp == null) {
            showMenuScreen(listMenu, idRegionRestoran);
        } else {
            int tempRegion = Integer.parseInt(temp);
            JFrame frame = new JFrame("Go Food");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 400);
            frame.setLocationRelativeTo(null);
            
            int biayaOngkir =  con.getBiayaOngkir(tempRegion, idRegionRestoran);
            int biayaMenu = con.getBiayaMenu(listCart);
            
            JPanel mainPanel = new JPanel(new BorderLayout());
            
            JLabel title = new JLabel("Pembayaran");
            title.setFont(new Font("Arial", Font.BOLD, 18));
            title.setHorizontalAlignment(SwingConstants.CENTER);
            
            JPanel paymentPanel = new JPanel();
            paymentPanel.add(Box.createVerticalGlue());


            JLabel biayaOngkirLabel = new JLabel("Biaya Ongkir: " + biayaOngkir);
            JLabel biayaMenuLabel = new JLabel("Biaya Menu: " + biayaMenu);
            
            
            JPanel inputPanel = new JPanel();

            JLabel voucherLabel = new JLabel("Voucher: ");
            JTextField voucherField = new JTextField(10);
            
            double totalBiaya = con.getTotalBiaya(biayaOngkir, biayaMenu, "");
            JLabel totalBiayaLabel = new JLabel("Total Biaya: " + con.getTotalBiaya(biayaOngkir, biayaMenu, ""));
            
            JButton calculateButton = new JButton("Calculate");
            calculateButton.addActionListener(e -> {
                try {
                    String voucher = voucherField.getText();
                    double newTotalBiaya = con.getTotalBiaya(biayaOngkir, biayaMenu, voucher);
                    totalBiayaLabel.setText("Total Biaya: " + newTotalBiaya);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Voucher Invalid", "Peringatan", JOptionPane.WARNING_MESSAGE);
                }
            });
            
            JButton orderButton = new JButton("Order");
            orderButton.setPreferredSize(new Dimension(300, 50)); 
            orderButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            orderButton.setMaximumSize(new Dimension(300, 50)); 
            orderButton.setFont(new Font("Arial", Font.BOLD, 18));
            orderButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String labelText = totalBiayaLabel.getText();
                    String numericPart = labelText.substring(labelText.lastIndexOf(":") + 2);
                    double totalBiayaValue = Double.parseDouble(numericPart);
                    
                    
                }
            });
            JButton backButton =  new JButton("Kembali");
            backButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                    showMenuScreen(listMenu, idRegionRestoran);
                }
            });
            
            paymentPanel.add(biayaOngkirLabel);
            paymentPanel.add(biayaMenuLabel);
            inputPanel.add(voucherLabel);
            inputPanel.add(voucherField);
            inputPanel.add(calculateButton);
            paymentPanel.add(inputPanel);
            paymentPanel.add(totalBiayaLabel);
            paymentPanel.add(orderButton);
            paymentPanel.add(backButton);
            paymentPanel.add(Box.createVerticalGlue());

            mainPanel.add(title, BorderLayout.NORTH);
            mainPanel.add(paymentPanel, BorderLayout.CENTER);

            frame.add(mainPanel);
            frame.setVisible(true);
        }
        
    }
}
