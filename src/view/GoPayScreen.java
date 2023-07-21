/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;
import controller.GoRideController;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.Gopay;
import model.OrderStatus;
import model.PaymentMethod;
import model.Service;
import model.Transaction;
import model.User;

public class GoPayScreen {
    private User currentUser;
    private JFrame frame;
    private JTextField amountField;
    private JButton topUpButton;
    
    public GoPayScreen(User user) {
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

        JLabel nameLabel = new JLabel("Nama: " + currentUser.getName());
        JLabel balanceLabel = new JLabel("Saldo GoPay: " + currentUser.getTotalBalance());
        JButton topUpButton = new JButton("Top Up");
        
        JButton backButton =  new JButton("Kembali");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new UserScreen();
            }
        });
        
        panel.add(nameLabel);
        panel.add(balanceLabel);
        panel.add(topUpButton);
        panel.add(backButton);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);

        topUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                showTopUpScreen();
            }
        });
    }
    
    private void showTopUpScreen() {
        frame = new JFrame("Top Up");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel amountLabel = new JLabel("Enter Amount:");
        amountField = new JTextField(10);
        inputPanel.add(amountLabel);
        inputPanel.add(amountField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topUpButton = new JButton("Top Up");
        topUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                topUp();
            }
        });
        buttonPanel.add(topUpButton);

        mainPanel.add(inputPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private void topUp() {
        String amountText = amountField.getText();
        try {
            double topUp = Double.parseDouble(amountText);
            double adminFee = topUp * 5 / 100;
            double finalPrice = topUp + adminFee;
            
            String confirmationMessage = "Gopay sukses!\n"
                    + "Total Top Up: " + topUp 
                    + "\nAdmin Fee: " + adminFee
                    + "\nFinal Price: " + finalPrice;
            int confirm = JOptionPane.showConfirmDialog(frame, confirmationMessage, "Confirmation",JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                Date now = new Date();

                Transaction transaction = new Transaction(currentUser.getUserID(), Service.GOPAY, PaymentMethod.GOPAY,null, topUp, 0, finalPrice, now, 0, adminFee, OrderStatus.SUCCESS);
                boolean berhasil = new GoRideController().insertGoRideTransaction(transaction);
                if (berhasil){
                    Gopay gopay = new Gopay();
                    gopay.setTransactionID(new GoRideController().getTransactionID(transaction));
                    gopay.setTopUpBalance(topUp);
                    
                    boolean berhasil2 = new Controller().insertToGoPay(gopay);
                    
                    if (berhasil2) {
                        boolean berhasil3 = new Controller().updateUserTotalBalance(topUp, currentUser);
                        if (berhasil3){
                            String username = currentUser.getUsername();
                            String password = currentUser.getPassword();

                            ArrayList<User> users = Controller.getInstance().getAllUsers();

                            for (User u : users) {
                                if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                                    Controller.getInstance().currentUser = u;
                                    break;
                                }
                            }
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
                JOptionPane.showMessageDialog(frame, "Top-up successful! Amount: " + topUp, "Success", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
                showMainPage();
            } else {
                JOptionPane.showMessageDialog(frame, "Cancel pemesanan");
            }
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Invalid amount. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
