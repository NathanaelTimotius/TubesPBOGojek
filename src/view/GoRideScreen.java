package view;

import javax.swing.*;

import model.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoRideScreen {
    private User currentUser;
    private JFrame frame;
    private JLabel nameLabel;
    private JLabel balanceLabel;

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
                showGoRidePage();
            }
        });
    }

    public void showGoRidePage() {
        frame = new JFrame("GoRide - GoJek");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel nameLabel = new JLabel("Nama:");
        JTextField nameField = new JTextField();
        JLabel pickupLabel = new JLabel("Lokasi Penjemputan:");
        JTextField pickupField = new JTextField();
        JLabel destinationLabel = new JLabel("Tujuan:");
        JTextField destinationField = new JTextField();
        JButton orderButton = new JButton("Pesan GoRide");

        panel.add(nameLabel);
        panel.add(nameField);
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
                String pickupLocation = pickupField.getText();
                String destination = destinationField.getText();

                // Panggil metode untuk memesan GoRide dengan data yang diinput pengguna
                orderGoRide(name, pickupLocation, destination);
            }
        });
    }

    public void orderGoRide(String name, String pickupLocation, String destination) {
        // Logika bisnis untuk memesan GoRide

        // Contoh: Tampilkan pesan konfirmasi dengan informasi pemesanan
        String confirmationMessage = "Pemesanan GoRide sukses!\n" +
                "Nama: " + name + "\n" +
                "Lokasi Penjemputan: " + pickupLocation + "\n" +
                "Tujuan: " + destination;
        JOptionPane.showMessageDialog(frame, confirmationMessage);

        // Hapus tampilan GoRide setelah pesanan selesai
        frame.dispose();
    }

    public static void main(String[] args) {
        // Contoh penggunaan
        User user = new User();
        GoRideScreen goRideScreen = new GoRideScreen(user);
        goRideScreen.showMainPage();
    }
}
