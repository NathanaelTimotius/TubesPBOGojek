package view;

import controller.Controller;
import controller.GoRideController;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.*;

import model.Cart;
import model.Gofood;
import model.Menu;
import model.OrderStatus;
import model.PaymentMethod;
import model.Region;
import model.Restaurant;
import model.Service;
import model.Transaction;
import model.User;
import model.Voucher;

public class GoFoodScreen {
    private JFrame frame;
    private JLabel nameLabel;
    private JLabel balanceLabel;
    private User currentUser;
    private JTextField destinationField;
    private ArrayList<Region> cart;
    private ArrayList<Cart> listCart;
    private int pickUp;
    private int idRestoran;
    private double incomeRestoran;
    private double deliveryFee;
    private String restoranName;
    
    public GoFoodScreen(User user){
        currentUser = user;
        showMainPage();
    }
    
    public void showMainPage() {
        frame = new JFrame("GoFood");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        nameLabel = new JLabel("Nama: " + currentUser.getName());
        balanceLabel = new JLabel("Saldo GoPay: " + currentUser.getTotalBalance());
        JButton goRideButton = new JButton("Pesan GoFood");
        
        JButton backButton =  new JButton("Kembali");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new UserScreen();
            }
        });
        
        panel.add(nameLabel);
        panel.add(balanceLabel);
        panel.add(goRideButton);
        panel.add(backButton);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);

        goRideButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                showGoFoodScreen();
            }
        });
    }
    
    public void showGoFoodScreen(){
        Controller con = new Controller();
        
        frame = new JFrame("Go Food");
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
            int idRegionRestoran = resto.get(i).getRegion().getRegionPosition();
            int tempID = resto.get(i).getRestaurantID();
            String tempName = resto.get(i).getRestaurantName();
            
            buttons[i].addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    frame.dispose();
                    pickUp = idRegionRestoran;
                    idRestoran = tempID;
                    restoranName = tempName;
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
                showMainPage();
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
        frame = new JFrame("Go Food");
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
        listCart = new ArrayList<Cart>();
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
                    boolean AllInputValid = true;
                    for (int i = 0; i < input.length; i++) {
                        int inputValue = (int) input[i].getValue();
                        if (inputValue < 0) {
                            AllInputValid = false;
                            break;
                        }
                    }
                    if (AllInputValid){
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
                new GoFoodScreen(currentUser);
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
        cart = con.getAllRegion();
        String text = "Pilih alamat anda : \n";
        for (int i = 0; i < cart.size(); i++) {
            text +=  (i+1) + ". " + cart.get(i).getRegionName() + "\n";
        }
        String temp = JOptionPane.showInputDialog(null, text, "Region", JOptionPane.QUESTION_MESSAGE);
        if (temp == null) {
            showMenuScreen(listMenu, idRegionRestoran);
        } else {
            if (temp.equals("")){
                JOptionPane.showMessageDialog(frame, "Please fill the region", "Peringatan", JOptionPane.WARNING_MESSAGE);
                showFormPengirimanGoFood(listMenu, listCart, idRegionRestoran);
                return;
            } 
            destinationField = new JTextField(temp);
            int tempRegion = Integer.parseInt(temp);
            if (tempRegion > cart.size() || tempRegion <= 0){
                JOptionPane.showMessageDialog(frame, "Region Invalid", "Peringatan", JOptionPane.WARNING_MESSAGE);
                showFormPengirimanGoFood(listMenu, listCart, idRegionRestoran);
                return;
            }
            frame = new JFrame("Go Food");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 400);
            frame.setLocationRelativeTo(null);
            
            deliveryFee =  con.getBiayaOngkir(tempRegion, idRegionRestoran);
            int biayaMenu = con.getBiayaMenu(listCart);
            
            JPanel mainPanel = new JPanel(new BorderLayout());
            
            JLabel title = new JLabel("Pembayaran");
            title.setFont(new Font("Arial", Font.BOLD, 18));
            title.setHorizontalAlignment(SwingConstants.CENTER);
            
            JPanel paymentPanel = new JPanel();
            paymentPanel.add(Box.createVerticalGlue());


            JLabel biayaOngkirLabel = new JLabel("Biaya Ongkir: " + deliveryFee);
            JLabel biayaMenuLabel = new JLabel("Biaya Menu: " + biayaMenu);
            
            
            JPanel inputPanel = new JPanel();

            JLabel voucherLabel = new JLabel("Voucher: ");
            JTextField voucherField = new JTextField(10);
            
            double totalBiaya = con.getTotalBiaya(deliveryFee, biayaMenu, "");
            JLabel totalBiayaLabel = new JLabel("Total Biaya: " + con.getTotalBiaya(deliveryFee, biayaMenu, ""));
            
            JButton calculateButton = new JButton("Calculate");
            calculateButton.addActionListener(e -> {
                String voucher = voucherField.getText();
                double newTotalBiaya = con.getTotalBiaya(deliveryFee, biayaMenu, voucher);
                if (totalBiaya == newTotalBiaya){
                    JOptionPane.showMessageDialog(frame, "Voucher Invalid", "Peringatan", JOptionPane.WARNING_MESSAGE);
                } 
                totalBiayaLabel.setText("Total Biaya: " + newTotalBiaya);
               
            });
            JRadioButton gopayButton = new JRadioButton("GOPAY");
            gopayButton.setActionCommand("GOPAY");
            JRadioButton cashButton = new JRadioButton("CASH");
            cashButton.setActionCommand("CASH");
            ButtonGroup paymentButtonGroup = new ButtonGroup();
            paymentButtonGroup.add(gopayButton);
            paymentButtonGroup.add(cashButton);
            
            JButton orderButton = new JButton("Order");
            orderButton.setPreferredSize(new Dimension(300, 50)); 
            orderButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            orderButton.setMaximumSize(new Dimension(300, 50)); 
            orderButton.setFont(new Font("Arial", Font.BOLD, 18));
            orderButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String labelText = totalBiayaLabel.getText();
                    String numericPart = labelText.substring(labelText.lastIndexOf(":") + 2);
                    double priceAfterDiscount = Double.parseDouble(numericPart);
                    if (paymentButtonGroup.getSelection() == null){
                        JOptionPane.showMessageDialog(frame, "Metode Pembayaran Kosong !!!", "Warning", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    PaymentMethod paymentMethod = PaymentMethod
                        .valueOf((String) paymentButtonGroup.getSelection().getActionCommand());
                    
                    Voucher voucher;
                    double totalDiscount = 0;
                    boolean isValid = true;
                    if (voucherField.getText().isEmpty()) {
                        voucher = null;
                    } else {
                        voucher = new GoRideController().getVoucher(voucherField.getText());
                        if (voucher != null) {
                            totalDiscount = totalBiaya * voucher.getDiscountPercentage() / 100;
                        } else {
                            isValid = false;
                            JOptionPane.showMessageDialog(frame, "Voucher tidak ada");
                        }
                    }
                    Date now = new Date();
                    incomeRestoran = biayaMenu - (biayaMenu * 5 / 100);
                    double adminFee = (deliveryFee * 5 / 100 )+ (biayaMenu * 5 / 100);
                    Transaction transaction = new Transaction(currentUser.getUserID(), Service.GOFOOD, paymentMethod,
                            voucher, totalBiaya, totalDiscount, priceAfterDiscount, now, 0, adminFee, OrderStatus.PENDING);

                     if (isValid) {
                        orderGoFood(transaction);
                    }
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
            paymentPanel.add(gopayButton);
            paymentPanel.add(cashButton);
            paymentPanel.add(orderButton);
            paymentPanel.add(backButton);
            paymentPanel.add(Box.createVerticalGlue());

            mainPanel.add(title, BorderLayout.NORTH);
            mainPanel.add(paymentPanel, BorderLayout.CENTER);

            frame.add(mainPanel);
            frame.setVisible(true);
        }
        
    }
    public void orderGoFood(Transaction transaction) {
        String confirmationMessage = "Pemesanan GoFood sukses!\n"
                + "Restoran: " + restoranName + "\n";
        for (Cart cart : listCart) {
            confirmationMessage += cart.getMenu().getMenuName() + ": ";
            confirmationMessage += cart.getQuantity() + "\n";
        }
        confirmationMessage += "\nTujuan: " + cart.get(Integer.parseInt(destinationField.getText()) - 1).getRegionName()
                + "\nTotal Pembayaran: " + transaction.getPriceAfterDiscount()
                + "\nMetode Pembayaran: " + transaction.getPaymentMethod().name();
        int confirm = JOptionPane.showConfirmDialog(frame, confirmationMessage, "Confirmation",
                JOptionPane.YES_NO_OPTION);

        boolean lanjut = true;
        if (confirm == JOptionPane.YES_OPTION) { // CONFIRM
            if (transaction.getPaymentMethod() == PaymentMethod.GOPAY) { // GOPAY ??
                if (currentUser.getTotalBalance() >= transaction.getPriceAfterDiscount()) { // CEK SALDO
                    currentUser.setTotalBalance(currentUser.getTotalBalance() - transaction.getPriceAfterDiscount());
                    new GoRideController().updateGoPay(currentUser.getUsername(), currentUser.getTotalBalance());
                } else {
                    JOptionPane.showMessageDialog(frame, "Saldo GOPAY tidak cukup");
                    lanjut = false; // GA BISA LANJUT
                }
            }
            if (lanjut) {
                boolean berhasil = new GoRideController().insertGoRideTransaction(transaction);
                if (berhasil) {
                    boolean berhasil2 = new Controller().insertToCart(listCart);
                    
                    if (berhasil2){
                        Gofood gofood = new Gofood();
                        gofood.setTransactionID(new GoRideController().getTransactionID(transaction));

                        int destination = Integer.parseInt(destinationField.getText());
                        gofood.setTitikAntar(destination);

                        if (pickUp >= destination) {
                            gofood.setDistance(pickUp - destination);
                        } else {
                            gofood.setDistance(destination - pickUp);
                        }
                        gofood.setRestaurantID(idRestoran);
                        gofood.setRestaurantName(restoranName);
                        gofood.setDeliveryFee(deliveryFee);

                        boolean berhasil3 = new Controller().insertToGoFood(gofood);
                        if (berhasil3) {
                            boolean berhasil4 = new Controller().updateIncomeRestoran(gofood.getRestaurantID(), incomeRestoran);
                            JOptionPane.showMessageDialog(frame, "Pembayaran berhasil");
                        } else {
                            JOptionPane.showMessageDialog(frame, "Pembayaran gagal");
                        }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Pembayaran gagal");
                    }
                    
                } else {
                    JOptionPane.showMessageDialog(frame, "Pembayaran gagal");
                }
            }
            frame.dispose();
            showMainPage();
        } else {
            JOptionPane.showMessageDialog(frame, "Cancel pemesanan");
        }
    }
}
