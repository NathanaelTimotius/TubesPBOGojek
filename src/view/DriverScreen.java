package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DriverScreen extends JFrame {
    private JLabel notificationLabel;
    private JButton acceptButton;
    private JButton rejectButton;

    public DriverScreen() {
        setTitle("Gojek Driver App");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        notificationLabel = new JLabel("New order from user: ");
        mainPanel.add(notificationLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        acceptButton = new JButton("Accept");
        rejectButton = new JButton("Reject");

        buttonPanel.add(acceptButton);
        buttonPanel.add(rejectButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        // Add action listeners to the buttons
        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Notify the controller that the driver accepts the order
                // Controller method should be called here
                displayOrderProcessingConfirmation();
            }
        });

        rejectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Notify the controller that the driver rejects the order
                // Controller method should be called here
                // ...
            }
        });
    }

    public void displayOrderNotification(String user, String pickupLocation, String destination) {
        notificationLabel.setText("<html>New order from user: " + user + "<br>Pickup location: " + pickupLocation + "<br>Destination: " + destination + "</html>");
    }

    public void displayOrderProcessingConfirmation() {
        // You can customize a dialog box instead of System.out.println if needed
        System.out.println("Order accepted. The order is being processed.");
    }

    public void displayOrderCompletionConfirmation() {
        // You can customize a dialog box instead of System.out.println if needed
        System.out.println("Order completed successfully.");
    }

    // You can add more methods to update the GUI, e.g., driver location, availability, etc.
    public static void main(String[] args) {
        new DriverScreen();
    }
}
