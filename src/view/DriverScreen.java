package view;

import javax.swing.*;

import controller.DriverController;
import model.Driver;
import model.Goride;
import model.OrderStatus;
import model.Transaction;
import model.Gofood;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class DriverScreen {
    private JFrame frame;
    private Driver driver;
    private Transaction pendingTransaction;
    private Goride pendingGoride;
    private Gofood pendingGofood;
    private Transaction currentTransaction;
    private Goride currentGoride;
    private Gofood currentGofood;

    public DriverScreen(Driver driver) {
        this.driver = driver;
    }

    public void showMainPage() {
        frame = new JFrame("Gojek Driver App");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        mainPanel.setLayout(new GridLayout(4, 1, 10, 10));

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
        
        JPanel backPanel = new JPanel();
        JButton backButton =  new JButton("Kembali");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new MainMenuScreen();
            }
        });
        backPanel.add(backButton);
        
        
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
        mainPanel.add(backPanel);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    public void showOrderanPage() {
        frame = new JFrame("Gojek Driver App");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        
        pendingTransaction = null;
        if (driver.getAvailable()) {
            pendingTransaction = new DriverController().getPendingTransaction();
        } else {
            pendingTransaction = currentTransaction;
        }

        pendingGoride = null;
        if (pendingTransaction != null) {
            switch (pendingTransaction.getServiceID()){
                case 0 :
                    pendingGoride = new DriverController().getPendingGoRide(pendingTransaction.getTransactionID());
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
                            if (driver.getAvailable()) {
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
                    break;
                case 1 :
                    break;
                case 2 :
                    pendingGofood = new DriverController().getPendingGoFood(pendingTransaction.getTransactionID());
                    
                    mainPanel = new JPanel(new GridBagLayout());
                    GridBagConstraints gbc = new GridBagConstraints();
                    gbc.insets = new Insets(5, 5, 5, 5);

                    JLabel transactionIdLabel = new JLabel("Transaction ID:");
                    JLabel transactionIdValue = new JLabel(Integer.toString(pendingTransaction.getTransactionID()));
                    JLabel restaurantNameLabel = new JLabel("Restaurant Name:");
                    JLabel restaurantNameValue = new JLabel(pendingGofood.getRestaurantName());
                    JLabel listCartLabel = new JLabel("List Cart:");
                    JLabel listCartValue = new JLabel(new DriverController().printAllCart(pendingGofood.getCart()));
                    JLabel destinationLabel = new JLabel("Destination:");
                    JLabel destinationValue = new JLabel(new DriverController().getRegionName(pendingGofood.getTitikAntar()));
                    JLabel totalPriceLabel = new JLabel("Total Price:");
                    JLabel totalPriceValue = new JLabel(Double.toString(pendingTransaction.getPriceAfterDiscount()));

                    gbc.gridx = 0;
                    gbc.gridy = 0;
                    mainPanel.add(transactionIdLabel, gbc);

                    gbc.gridx = 1;
                    mainPanel.add(transactionIdValue, gbc);

                    gbc.gridx = 0;
                    gbc.gridy = 1;
                    mainPanel.add(restaurantNameLabel, gbc);

                    gbc.gridx = 1;
                    mainPanel.add(restaurantNameValue, gbc);

                    gbc.gridx = 0;
                    gbc.gridy = 2;
                    mainPanel.add(listCartLabel, gbc);

                    gbc.gridx = 1;
                    mainPanel.add(listCartValue, gbc);

                    gbc.gridx = 0;
                    gbc.gridy = 3;
                    mainPanel.add(destinationLabel, gbc);

                    gbc.gridx = 1;
                    mainPanel.add(destinationValue, gbc);

                    gbc.gridx = 0;
                    gbc.gridy = 4;
                    mainPanel.add(totalPriceLabel, gbc);

                    gbc.gridx = 1;
                    mainPanel.add(totalPriceValue, gbc);

                    backButton = new JButton("Back");
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
                            if (driver.getAvailable()) {
                                currentTransaction = pendingTransaction;
                                currentGofood = pendingGofood;
                                new DriverController().changeTransactionState(pendingTransaction);
                                currentTransaction.setOrderStatus(OrderStatus.IN_PROGRESS);
                                currentGofood.setTransactionID(pendingTransaction.getTransactionID());
                                new DriverController().setDriverGofood(currentGofood, driver);
                                driver.setAvailable(false);
                            } else {
                                new DriverController().changeTransactionState(currentTransaction);
                                new DriverController().setDriverGofood(currentGofood, driver);
                                driver.setAvailable(true);
                            }

                            JOptionPane.showMessageDialog(frame, "Berhasil proses");
                            frame.dispose();
                            showMainPage();
                        }
                    });

                    buttonPanel = new JPanel();
                    buttonPanel.add(backButton);
                    buttonPanel.add(updateButton);

                    frame.add(mainPanel, BorderLayout.CENTER);
                    frame.add(buttonPanel, BorderLayout.SOUTH);
                    frame.setVisible(true);
                    break;
                case 3:
                    break;
                case 4:
                    break;
                default :
                    JOptionPane.showMessageDialog(frame, "Input Invalid", "Peringatan", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Belum ada orderan");
            showMainPage();
        }
    }
    
}
