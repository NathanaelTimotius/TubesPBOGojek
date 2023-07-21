package view;

import javax.swing.*;

import controller.DriverController;
import model.Driver;
import model.Goride;
import model.OrderStatus;
import model.Transaction;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DriverScreen {
    private JFrame frame;
    private Driver driver;
    private Transaction pendingTransaction;
    private Goride pendingGoride;
    private Transaction currentTransaction;
    private Goride currentGoride;

    public DriverScreen(Driver driver) {
        this.driver = driver;
    }

    public void showMainPage() {
        frame = new JFrame("Gojek Driver App");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        mainPanel.setLayout(new GridLayout(3, 1, 10, 10));

        // Nama Driver Label
        JLabel nameLabel = new JLabel("Nama Driver : " + driver.getName());
        mainPanel.add(nameLabel);

        // Plat Nomor Kendaraan Label
        JLabel platLabel = new JLabel("Plat Nomor Kendaraan : " + driver.getVehicle().getNumberPlate());
        mainPanel.add(platLabel);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        JButton orderButton;
        if (driver.getAvailable()) {
            orderButton = new JButton("Ambil Orderan");
        } else {
            orderButton = new JButton("Selesaikan Orderan");
        }
        JButton pendapatanButton = new JButton("Cek Total Pendapatan");
        buttonPanel.add(orderButton);
        buttonPanel.add(pendapatanButton);

        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                showOrderanPage();
            }
        });

        pendapatanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Total pendapatan: " + driver.getIncome());
            }
        });

        mainPanel.add(buttonPanel);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    public void showOrderanPage() {
        frame = new JFrame("Gojek Driver App");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        pendingTransaction = null;
        if (driver.getAvailable()) {
            pendingTransaction = new DriverController().getPendingTransaction();
        } else {
            pendingTransaction = currentTransaction;
        }

        pendingGoride = null;
        if (pendingTransaction != null) {
            pendingGoride = new DriverController().getPendingGoRide(pendingTransaction.getTransactionID());
        }

        if (pendingTransaction != null) {
            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new GridLayout(4, 2, 10, 10));

            mainPanel.add(new JLabel("Transaction ID:"));
            mainPanel.add(new JLabel(Integer.toString(pendingTransaction.getTransactionID())));
            mainPanel.add(new JLabel("Pickup Location:"));
            mainPanel.add(new JLabel(Integer.toString(pendingGoride.getTitikJemput())));
            mainPanel.add(new JLabel("Destination:"));
            mainPanel.add(new JLabel(Integer.toString(pendingGoride.getTitikAntar())));
            mainPanel.add(new JLabel("Total Price:"));
            mainPanel.add(new JLabel(Double.toString(pendingTransaction.getPriceAfterDiscount())));

            JButton backButton = new JButton("Back");
            JButton updateButton;
            if (driver.getAvailable()) {
                updateButton = new JButton("Accept Order");
            } else {
                updateButton = new JButton("Finish Order");
            }

            backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                    showMainPage();
                }
            });

            updateButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new DriverController().changeDriverState(driver);
                    if  (driver.getAvailable()) {
                        currentTransaction = pendingTransaction;
                        currentGoride = pendingGoride;
                        new DriverController().changeTransactionState(pendingTransaction);
                        currentTransaction.setOrderStatus(OrderStatus.IN_PROGRESS);
                        currentGoride.setTransactionID(pendingTransaction.getTransactionID());
                        new DriverController().setDriverGoride(currentGoride, driver);
                        driver.setAvailable(false);
                    } else {
                        new DriverController().changeTransactionState(currentTransaction);
                        new DriverController().setDriverGoride(currentGoride, driver);
                        driver.setAvailable(true);
                    }

                    JOptionPane.showMessageDialog(frame, "Berhasil proses");
                    frame.dispose();
                    showMainPage();
                }
            });

            JPanel buttonPanel = new JPanel();
            buttonPanel.add(backButton);
            buttonPanel.add(updateButton);

            frame.add(mainPanel, BorderLayout.CENTER);
            frame.add(buttonPanel, BorderLayout.SOUTH);
            frame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(frame, "Belum ada orderan");
        }
    }

    public static void main(String[] args) {
        Driver driver = new DriverController().getDriverByUsername("1234567890123456");
        DriverScreen driverScreen = new DriverScreen(driver);
        driverScreen.showMainPage();
    }
}
