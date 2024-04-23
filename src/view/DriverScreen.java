package view;

import javax.swing.*;

import controller.DriverController;
import model.*;

import java.awt.*;

public class DriverScreen {
    private JFrame frame;
    private Driver driver;
    private Transaction pendingTransaction;
    private Goride pendingGoride;
    private Gocar pendingGocar;
    private Gofood pendingGofood;
    private Gosend pendingGosend;
    private Transaction currentTransaction;
    private Goride currentGoride;
    private Gocar currentGocar;
    private Gofood currentGofood;
    private Gosend currentGosend;

    private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT = 200;

    public DriverScreen(Driver driver) {
        this.driver = driver;
    }

    public void showMainPage() {
        initializeFrame("Gojek Driver App");

        JPanel mainPanel = createMainPanel();

        JLabel nameLabel = new JLabel("Nama Driver : " + driver.getName());
        mainPanel.add(nameLabel);

        JLabel platLabel = new JLabel("Plat Nomor Kendaraan : " + driver.getVehicle().getNumberPlate());
        mainPanel.add(platLabel);

        JPanel buttonPanel = createButtonPanel();
        mainPanel.add(buttonPanel);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private void initializeFrame(String title) {
        frame = new JFrame(title);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }

    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 1, 10, 10));
        return mainPanel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        JButton orderButton = createOrderButton();
        JButton pendapatanButton = createPendapatanButton();
        JButton backButton = createBackButton();

        buttonPanel.add(orderButton);
        buttonPanel.add(pendapatanButton);
        buttonPanel.add(backButton);

        return buttonPanel;
    }
    private JButton createOrderButton() {
        JButton orderButton = new JButton(driver.getAvailable() ? "Ambil Orderan" : "Selesaikan Orderan");
        orderButton.addActionListener(e -> {
            frame.dispose();
            showOrderanPage();
        });
        return orderButton;
    }

    private JButton createPendapatanButton() {
        JButton pendapatanButton = new JButton("Cek Total Pendapatan");
        pendapatanButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Total pendapatan: " + driver.getIncome()));
        return pendapatanButton;
    }

    private JButton createBackButton() {
        JButton backButton = new JButton("Kembali");
        backButton.addActionListener(e -> {
            frame.dispose();
            new MainMenuScreen();
        });
        return backButton;
    }

    private void showOrderanPage() {
        initializeFrame("Gojek Driver App");

        pendingTransaction = driver.getAvailable() ? new DriverController().getPendingTransaction() : currentTransaction;

        if (pendingTransaction != null) {
            switch (pendingTransaction.getServiceID()) {
                case Service.GORIDE:
                    showGoRidePage();
                    break;
                case Service.GOCAR:
                    showGoCarPage();
                    break;
                case Service.GOFOOD:
                    showGoFoodPage();
                    break;
                case Service.GOSEND:
                    showGoSendPage();
                    break;
                default:
                    System.out.println(pendingTransaction.getServiceID());
                    JOptionPane.showMessageDialog(frame, "Input Invalid", "Peringatan", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Belum ada orderan");
            showMainPage();
        }
    }

    private void showGoRidePage() {
        pendingGoride = driver.getAvailable() ? new DriverController().getPendingGoRide(pendingTransaction.getTransactionID()) : currentGoride;

        JPanel mainPanel = createMainPanel();
        mainPanel.setLayout(new GridLayout(4, 2, 10, 10));

        addLabelsToPanel(mainPanel, "Transaction ID:", Integer.toString(pendingTransaction.getTransactionID()));
        addLabelsToPanel(mainPanel, "Pickup Location:", Integer.toString(pendingGoride.getTitikJemput()));
        addLabelsToPanel(mainPanel, "Destination:", Integer.toString(pendingGoride.getTitikAntar()));
        addLabelsToPanel(mainPanel, "Total Price:", Double.toString(pendingTransaction.getPriceAfterDiscount()));

        JButton backButton = createBackButton();
        JButton updateButton = createUpdateButton(driver.getAvailable() ? "Accept Order" : "Finish Order");

        backButton.addActionListener(e -> {
            frame.dispose();
            showMainPage();
        });

        updateButton.addActionListener(e -> {
            new DriverController().changeDriverState(driver);
            if (driver.getAvailable()) {
                currentTransaction = pendingTransaction;
                currentGoride = pendingGoride;
                new DriverController().changeTransactionState(pendingTransaction);
                currentTransaction.setOrderStatus(OrderStatus.IN_PROGRESS);
                currentGoride.setTransactionID(pendingTransaction.getTransactionID());
                driver.setAvailable(false);
            } else {
                new DriverController().updateIncomeDriver(driver, currentTransaction.getTotalPrice());
                System.out.println(currentGoride.getTransactionID());
                new DriverController().setDriverGoride(currentGoride, driver, currentTransaction.getTotalPrice());
                new DriverController().changeTransactionState(currentTransaction);
                driver.setAvailable(true);
            }

            JOptionPane.showMessageDialog(frame, "Berhasil proses");
            frame.dispose();
            showMainPage();
        });

        addButtonsToPanel(frame, mainPanel, backButton, updateButton);
    }

    private void showGoCarPage() {
        pendingGocar = driver.getAvailable() ? new DriverController().getPendingGoCar(pendingTransaction.getTransactionID()) : currentGocar;

        JPanel mainPanel = createMainPanel();
        mainPanel.setLayout(new GridLayout(4, 2, 10, 10));

        addLabelsToPanel(mainPanel, "Transaction ID:", Integer.toString(pendingTransaction.getTransactionID()));
        addLabelsToPanel(mainPanel, "Pickup Location:", Integer.toString(pendingGocar.getTitikJemput()));
        addLabelsToPanel(mainPanel, "Destination:", Integer.toString(pendingGocar.getTitikAntar()));
        addLabelsToPanel(mainPanel, "Total Price:", Double.toString(pendingTransaction.getPriceAfterDiscount()));

        JButton backButton = createBackButton();
        JButton updateButton = createUpdateButton(driver.getAvailable() ? "Accept Order" : "Finish Order");

        backButton.addActionListener(e -> {
            frame.dispose();
            showMainPage();
        });

        updateButton.addActionListener(e -> {
            new DriverController().changeDriverState(driver);
            if (driver.getAvailable()) {
                currentTransaction = pendingTransaction;
                currentGocar = pendingGocar;
                new DriverController().changeTransactionState(pendingTransaction);
                currentTransaction.setOrderStatus(OrderStatus.IN_PROGRESS);
                currentGocar.setTransactionID(pendingTransaction.getTransactionID());
                driver.setAvailable(false);
            } else {
                new DriverController().updateIncomeDriver(driver, currentTransaction.getTotalPrice());
                System.out.println(currentGocar.getTransactionID());
                new DriverController().setDriverGocar(currentGocar, driver, currentTransaction.getTotalPrice());
                new DriverController().changeTransactionState(currentTransaction);
                driver.setAvailable(true);
            }

            JOptionPane.showMessageDialog(frame, "Berhasil proses");
            frame.dispose();
            showMainPage();
        });

        addButtonsToPanel(frame, mainPanel, backButton, updateButton);
    }

    private void showGoFoodPage() {
        pendingGofood = new DriverController().getPendingGoFood(pendingTransaction.getTransactionID());

        JPanel mainPanel = createMainPanel();
        mainPanel.setLayout(new GridLayout(4, 2, 10, 10));


        addLabelsToPanel(mainPanel, "Transaction ID:", Integer.toString(pendingTransaction.getTransactionID()));
        addLabelsToPanel(mainPanel, "Restaurant Name:", pendingGofood.getRestaurantName());
        addLabelsToPanel(mainPanel, "Destination:", new DriverController().getRegionName(pendingGofood.getTitikAntar()));
        addLabelsToPanel(mainPanel, "Total Price:", Double.toString(pendingTransaction.getPriceAfterDiscount()));

        JButton backButton = createBackButton();
        JButton updateButton = createUpdateButton(driver.getAvailable() ? "Accept Order" : "Finish Order");

        backButton.addActionListener(e -> {
            frame.dispose();
            showMainPage();
        });

        updateButton.addActionListener(e -> {
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
                new DriverController().updateIncomeDriver(driver, currentGofood.getDeliveryFee());
                new DriverController().changeTransactionState(currentTransaction);
                new DriverController().setDriverGofood(currentGofood, driver);
                driver.setAvailable(true);
            }

            JOptionPane.showMessageDialog(frame, "Berhasil proses");
            frame.dispose();
            showMainPage();
        });

        addButtonsToPanel(frame, mainPanel, backButton, updateButton);
    }

    private void showGoSendPage() {
        pendingGosend = new DriverController().getPendingGoSend(pendingTransaction.getTransactionID());

        JPanel mainPanel = createMainPanel();
        mainPanel.setLayout(new GridLayout(5, 2, 10, 10));


        addLabelsToPanel(mainPanel, "Transaction ID:", Integer.toString(pendingTransaction.getTransactionID()));
        addLabelsToPanel(mainPanel, "Destination:", new DriverController().getRegionName(pendingGosend.getDestination()));
        addLabelsToPanel(mainPanel, "Item Name:", pendingGosend.getItem());
        addLabelsToPanel(mainPanel, "Receiver Name:", pendingGosend.getReceiverName());
        addLabelsToPanel(mainPanel, "Total Price:", Double.toString(pendingTransaction.getPriceAfterDiscount()));

        JButton backButton = createBackButton();
        JButton updateButton = createUpdateButton(driver.getAvailable() ? "Accept Order" : "Finish Order");

        backButton.addActionListener(e -> {
            frame.dispose();
            showMainPage();
        });

        updateButton.addActionListener(e -> {
            new DriverController().changeDriverState(driver);
            if (driver.getAvailable()) {
                currentTransaction = pendingTransaction;
                currentGosend = pendingGosend;
                new DriverController().changeTransactionState(pendingTransaction);
                currentTransaction.setOrderStatus(OrderStatus.IN_PROGRESS);
                currentGosend.setTransactionID(pendingTransaction.getTransactionID());
                new DriverController().setDriverGosend(currentGosend, driver);
                driver.setAvailable(false);
            } else {
                new DriverController().updateIncomeDriver(driver, currentTransaction.getTotalPrice());
                new DriverController().changeTransactionState(currentTransaction);
                new DriverController().setDriverGosend(currentGosend, driver);
                driver.setAvailable(true);
            }

            JOptionPane.showMessageDialog(frame, "Berhasil proses");
            frame.dispose();
            showMainPage();
        });

        addButtonsToPanel(frame, mainPanel, backButton, updateButton);
    }

    private void addLabelsToPanel(JPanel panel, String labelText, String valueText) {
        JLabel label = new JLabel(labelText);
        JLabel value = new JLabel(valueText);
        panel.add(label);
        panel.add(value);
    }

    private JButton createUpdateButton(String text) {
        return new JButton(text);
    }

    private void addButtonsToPanel(JFrame frame, JPanel panel, JButton... buttons) {
        JPanel buttonPanel = new JPanel();
        for (JButton button : buttons) {
            buttonPanel.add(button);
        }
        frame.add(panel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}