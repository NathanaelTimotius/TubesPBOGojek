package view;

import javax.swing.*;

import controller.Controller;
import controller.GoCarController;
import controller.GoController;
import model.Gocar;
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

public class GoCarScreen {
    private User currentUser;
    private JFrame frame;
    private JLabel nameLabel;
    private JLabel balanceLabel;
    private ArrayList<Region> regions;
    private JTextField pickupField;
    private JTextField destinationField;

    public GoCarScreen(User user) {
        currentUser = user;
    }

    public void showMainPage() {
        frame = new JFrame("GoJek");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        nameLabel = new JLabel("Nama: " + currentUser.getName());
        balanceLabel = new JLabel("Saldo GoPay: " + currentUser.getTotalBalance());
        JButton goCarButton = new JButton("Pesan GoCar");

        JButton backButton =  new JButton("Kembali");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new UserScreen();
            }
        });
        
        panel.add(nameLabel);
        panel.add(balanceLabel);
        panel.add(goCarButton);
        panel.add(backButton);
        
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);

        goCarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                showGoCarPage();
            }
        });
    }

    public void showGoCarPage() {
        frame = new JFrame("GoCar - GoJek");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 600);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

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
        regions = new Controller().getAllRegion();
        for (Region region : regions) {
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
        JButton orderButton = new JButton("Pesan GoCar");
        orderButton.setPreferredSize(new Dimension(80, orderButton.getPreferredSize().height));
        panel.add(orderButton, gb);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);

        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int pickupLocation = Integer.parseInt(pickupField.getText());
                int destination = Integer.parseInt(destinationField.getText());
                PaymentMethod paymentMethod = PaymentMethod
                        .valueOf((String) paymentButtonGroup.getSelection().getActionCommand());

                double totalPrice = 0;
                if (destination > pickupLocation) {
                    totalPrice = 5000 + (7000 * (destination - pickupLocation));
                } else {
                    totalPrice = 5000 + (7000 * (pickupLocation - destination));
                }

                Voucher voucher;
                double totalDiscount = 0;
                boolean isValid = true;
                if (voucherField.getText().isEmpty()) {
                    voucher = null;
                } else {
                    voucher = new GoController().getVoucher(voucherField.getText());
                    if (voucher != null) {
                        totalDiscount = totalPrice * voucher.getDiscountPercentage() / 100;
                    } else {
                        isValid = false;
                        JOptionPane.showMessageDialog(frame, "Voucher tidak ada");
                    }
                }

                double priceAfterDiscount = totalPrice - totalDiscount;
                Date now = new Date();

                double adminFee = 2000;

                Transaction transaction = new Transaction(currentUser.getUserID(), Service.GOCAR, paymentMethod,
                        voucher, totalPrice, totalDiscount, priceAfterDiscount, now, 0, adminFee, OrderStatus.PENDING);

                if (isValid) {
                    orderGoCar(transaction);
                }
            }
        });
    }

    public void orderGoCar(Transaction transaction) {
        // Logika bisnis untuk memesan GoCar
        String confirmationMessage = "Pemesanan GoCar sukses!\n"
                + "Lokasi Penjemputan: " + regions.get(Integer.parseInt(pickupField.getText()) - 1).getRegionName()
                + "\nTujuan: " + regions.get(Integer.parseInt(destinationField.getText()) - 1).getRegionName()
                + "\nTotal Pembayaran: " + transaction.getPriceAfterDiscount()
                + "\nMetode Pembayaran: " + transaction.getPaymentMethod().name();
        int confirm = JOptionPane.showConfirmDialog(frame, confirmationMessage, "Confirmation",
                JOptionPane.YES_NO_OPTION);

        boolean lanjut = true;
        if (confirm == JOptionPane.YES_OPTION) { // CONFIRM
            if (transaction.getPaymentMethod() == PaymentMethod.GOPAY) { // GOPAY ??
                if (currentUser.getTotalBalance() >= transaction.getPriceAfterDiscount()) { // CEK SALDO
                    currentUser.setTotalBalance(currentUser.getTotalBalance() - transaction.getPriceAfterDiscount());
                    new GoController().updateGoPay(currentUser.getUsername(), currentUser.getTotalBalance());
                } else {
                    JOptionPane.showMessageDialog(frame, "Saldo GOPAY tidak cukup");
                    lanjut = false; // GA BISA LANJUT
                }
            }
            if (lanjut) {
                boolean berhasil = new GoCarController().insertGoCarTransaction(transaction);
                if (berhasil) {
                    Gocar gocar = new Gocar();
                    gocar.setTransactionID(new GoController().getTransactionID(transaction));

                    int pickUp = Integer.parseInt(pickupField.getText());
                    int destination = Integer.parseInt(destinationField.getText());
                    gocar.setTitikJemput(pickUp);
                    gocar.setTitikAntar(destination);

                    if (destination >= pickUp) {
                        gocar.setDistance(destination - pickUp);
                    } else {
                        gocar.setDistance(pickUp - destination);
                    }

                    boolean berhasil2 = new GoCarController().insertToGoCar(gocar);
                    if (berhasil2){
                        JOptionPane.showMessageDialog(frame, "Pembayaran berhasil");
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
