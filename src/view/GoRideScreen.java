package view;

import javax.swing.*;

import controller.GoRideController;
import model.OrderStatus;
import model.User;
import model.PaymentMethod;
import model.Service;
import model.Region;
import model.Transaction;
import model.Voucher;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.ArrayList;

public class GoRideScreen {
    private User currentUser;
    private JFrame frame;
    private JLabel nameLabel;
    private JLabel balanceLabel;
    private ArrayList<Region> regions;
    private JTextField pickupField;
    private JTextField destinationField;

    public GoRideScreen(User user) {
        currentUser = user;
    }

    public void showMainPage() {
        frame = new JFrame("GoJek");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        nameLabel = new JLabel("Nama: " + currentUser.getName());
        balanceLabel = new JLabel("Saldo GoPay: " + currentUser.getTotalBalance());
        JButton goRideButton = new JButton("Pesan GoRide");

        panel.add(nameLabel);
        panel.add(balanceLabel);
        panel.add(goRideButton);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);

        goRideButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                showGoRidePage();
            }
        });
    }

    public void showGoRidePage() {
        frame = new JFrame("GoRide - GoJek");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 600);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gb = new GridBagConstraints();
        gb.fill = GridBagConstraints.HORIZONTAL;
        gb.insets = new Insets(10, 10, 10, 10);

        gb.gridx = 0;
        gb.gridy = 0;
        JLabel listLokasiLabel = new JLabel("List Lokasi: ");
        panel.add(listLokasiLabel, gb);

        gb.gridwidth = 1;
        gb.weightx = 0;
        gb.anchor = GridBagConstraints.WEST;
        int countY = 1;
        regions = new GoRideController().getAllRegions();
        for(Region region : regions){
            gb.gridx = 0;
            gb.gridy = countY;
            gb.anchor = GridBagConstraints.WEST;
            JLabel regionNameLabel = new JLabel(region.getRegionName() + " :");
            panel.add(regionNameLabel, gb);

            gb.gridx = 1;
            gb.anchor = GridBagConstraints.CENTER;
            JLabel regionPositionLabel = new JLabel(String.valueOf(region.getRegionPosition()));
            panel.add(regionPositionLabel, gb);
            countY++;
        }

        gb.gridy = countY;
        panel.add(new JLabel(), gb);
        countY++;

        countY++;
        gb.gridx = 0;
        gb.gridy = countY;
        gb.anchor = GridBagConstraints.WEST;
        JLabel pickupLabel = new JLabel("Lokasi Penjemputan:");
        panel.add(pickupLabel, gb);
        gb.gridx = 1;
        pickupField = new JTextField();
        panel.add(pickupField, gb);

        countY++;
        gb.gridx = 0;
        gb.gridy = countY;
        JLabel destinationLabel = new JLabel("Tujuan:");
        panel.add(destinationLabel, gb);
        gb.gridx = 1;
        destinationField = new JTextField();
        panel.add(destinationField, gb);

        countY++;
        gb.gridx = 0;
        gb.gridy = countY;
        JLabel voucherLabel = new JLabel("Voucher:");
        panel.add(voucherLabel, gb);
        gb.gridx = 1;
        JTextField voucherField = new JTextField();
        panel.add(voucherField, gb);

        countY++;
        gb.gridx = 0;
        gb.gridy = countY;
        JLabel paymentLabel = new JLabel("Metode Pembayaran:");
        panel.add(paymentLabel, gb);
        gb.gridx = 1;
        JRadioButton gopayButton = new JRadioButton("GOPAY");
        gopayButton.setActionCommand("GOPAY");
        JRadioButton cashButton = new JRadioButton("CASH");
        cashButton.setActionCommand("CASH");
        ButtonGroup paymentButtonGroup = new ButtonGroup();
        paymentButtonGroup.add(gopayButton);
        paymentButtonGroup.add(cashButton);
        JPanel paymentPanel = new JPanel();
        paymentPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        paymentPanel.add(gopayButton);
        paymentPanel.add(cashButton);
        panel.add(paymentPanel, gb);

        countY++;
        gb.gridy = countY;
        gb.anchor = GridBagConstraints.EAST;
        JButton orderButton = new JButton("Pesan GoRide");
        orderButton.setPreferredSize(new Dimension(80, orderButton.getPreferredSize().height));
        panel.add(orderButton, gb);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);

        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int pickupLocation = Integer.parseInt(pickupField.getText());
                int destination = Integer.parseInt(destinationField.getText());
                PaymentMethod paymentMethod = PaymentMethod.valueOf((String)paymentButtonGroup.getSelection().getActionCommand());
                Voucher voucher;
                if (voucherField.getText().isEmpty()){
                    voucher = null;
                } else {
                    voucher = new Voucher();
                    voucher.setVoucherName(voucherField.getText());
                }

                double totalPrice = 0;
                if(destination > pickupLocation) {
                    totalPrice = 5000 + (7000 * (destination - pickupLocation));
                } else {
                    totalPrice = 5000 + (7000 * (pickupLocation - destination));
                }
                
                double totalDiscount = 0;
                double priceAfterDiscount = totalPrice - totalDiscount;
                Date now = new Date();

                double adminFee = 2000;

                Transaction transaction = new Transaction(currentUser.getUserID(), Service.GORIDE, paymentMethod, voucher, totalPrice, totalDiscount, priceAfterDiscount, now, 0, adminFee, OrderStatus.PENDING);

                orderGoRide(transaction);
            }
        });
    }

    public void orderGoRide(Transaction transaction) {
        // Logika bisnis untuk memesan GoRide
        String confirmationMessage = "Pemesanan GoRide sukses!\n" +
                "Lokasi Penjemputan: " + regions.get(Integer.parseInt(pickupField.getText()) - 1).getRegionName() + "\n" +
                "Tujuan: " + regions.get(Integer.parseInt(destinationField.getText()) - 1).getRegionName() + "\n" +
                "Total Pembayaran: " + transaction.getPriceAfterDiscount() + "\n" +
                "Metode Pembayaran: " + transaction.getPaymentMethod().name();
        int confirm = JOptionPane.showConfirmDialog(frame, confirmationMessage, "Confirmation", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            boolean berhasil = new GoRideController().insertGoRideTransaction(transaction);
            if (berhasil) {
                if (transaction.getPaymentMethod() == PaymentMethod.GOPAY){
                    if (currentUser.getTotalBalance() >= transaction.getPriceAfterDiscount()){
                        currentUser.setTotalBalance(currentUser.getTotalBalance() - transaction.getPriceAfterDiscount());
                        new GoRideController().updateGoPay(currentUser.getUsername(), currentUser.getTotalBalance());
                        JOptionPane.showMessageDialog(frame, "Pembayaran berhasil \nSisa GOPAY: " + currentUser.getTotalBalance());
                    } else {
                        JOptionPane.showMessageDialog(frame, "Saldo GOPAY tidak cukup");
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Pembayaran berhasil");
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Pembayaran gagal");
            }
            frame.dispose();
            showMainPage();
        } else {
            JOptionPane.showMessageDialog(frame, "Cancel pemesanan");
        }       
    }

    public static void main(String[] args) {
        // Contoh penggunaan
        User user = new GoRideController().getUserByUsername("user1");
        GoRideScreen goRideScreen = new GoRideScreen(user);
        goRideScreen.showMainPage();
    }
}
