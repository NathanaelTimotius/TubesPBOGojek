package view;

import javax.swing.*;

import controller.GoRideController;
import model.User;
import model.Region;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
        frame.setSize(350, 500);
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
        ArrayList<Region> regions = new GoRideController().getAllRegions();
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

        gb.gridx = 0;
        gb.gridy = countY;
        gb.anchor = GridBagConstraints.WEST;
        JLabel nameLabel = new JLabel("Nama:");
        panel.add(nameLabel, gb);
        gb.gridx = 1;
        JTextField nameField = new JTextField();
        panel.add(nameField, gb);

        countY++;
        gb.gridx = 0;
        gb.gridy = countY;
        JLabel pickupLabel = new JLabel("Lokasi Penjemputan:");
        panel.add(pickupLabel, gb);
        gb.gridx = 1;
        JTextField pickupField = new JTextField();
        panel.add(pickupField, gb);

        countY++;
        gb.gridx = 0;
        gb.gridy = countY;
        JLabel destinationLabel = new JLabel("Tujuan:");
        panel.add(destinationLabel, gb);
        gb.gridx = 1;
        JTextField destinationField = new JTextField();
        panel.add(destinationField, gb);

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
        User user = new GoRideController().getUserByUsername("user1");
        GoRideScreen goRideScreen = new GoRideScreen(user);
        goRideScreen.showMainPage();
    }
}
