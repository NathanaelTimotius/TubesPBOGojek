/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javax.swing.*;

import model.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author Asus
 */
public class GoSendScreen{
    private User currentUser;
    private JFrame frame;
    private JLabel nameLabel,nameLabel2;
    private JLabel balanceLabel;

    public GoSendScreen(User user) {
        currentUser = user;
    }

    public void showMainPage() {

        frame = new JFrame("GoSend");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        nameLabel = new JLabel("Nama: " + currentUser.getName());
        balanceLabel = new JLabel("Saldo GoPay: " + currentUser.getTotalBalance());
        JButton pesanGosend = new JButton("Pesan GoSend");

        panel.add(nameLabel);
        panel.add(nameLabel2);
        panel.add(balanceLabel);
        panel.add(pesanGosend);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);

        pesanGosend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showGoSendPage();
            }
        });
    }

    public void showGoSendPage() {
        frame = new JFrame("GoSend");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel nameLabel = new JLabel("Nama Pengirim :");
        JTextField nameField = new JTextField();
        JLabel nameLabel2 = new JLabel("Nama Tujuan:");
        JTextField nameField2 = new JTextField();
        JLabel pickupLabel = new JLabel("Lokasi Penjemputan:");
        JTextField pickupField = new JTextField();
        JLabel destinationLabel = new JLabel("Lokasi Tujuan:");
        JTextField destinationField = new JTextField();
        JButton orderButton = new JButton("Pesan GoSend");

        panel.add(nameLabel);
        panel.add(nameLabel2);
        panel.add(nameField);
        panel.add(nameField2);
        panel.add(pickupLabel);
        panel.add(pickupField);
        panel.add(destinationLabel);
        panel.add(destinationField);
        panel.add(orderButton);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);

        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String name2 = nameField.getText();
                String pickupLocation = pickupField.getText();
                String destination = destinationField.getText();

                // Panggil metode untuk memesan GoSend dengan data yang diinput pengguna
                orderGoSend(name, name2, pickupLocation, destination);
            }
        });
    }

    public void orderGoSend(String name, String name2, String pickupLocation, String destination) {
        // Logika bisnis untuk memesan GoSend

        // Contoh: Tampilkan pesan konfirmasi dengan informasi pemesanan
        String confirmationMessage = "Pemesanan GoSend sukses!\n" +
                "Nama Pengirim: " + name + "\n" +
                "Nama Terkirim: " + name2 + "\n" +
                "Lokasi Penjemputan: " + pickupLocation + "\n" +
                "Tujuan: " + destination;
        JOptionPane.showMessageDialog(frame, confirmationMessage);

        // Hapus tampilan GoRide setelah pesanan selesai
        frame.dispose();
    }

    public static void main(String[] args) {
        // Contoh penggunaan
        User user = new User();
        GoSendScreen goSendScreen = new GoSendScreen(user);
        goSendScreen.showMainPage();
    }
}
