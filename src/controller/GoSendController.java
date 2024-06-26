package controller;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import model.Gosend;
import model.Transaction;

public class GoSendController {
    private DatabaseHandler conn;
    
    public GoSendController(){
        this.conn = new DatabaseHandler();
    }

    public boolean insertGoSendTransaction(Transaction transaction){
        try {
            conn.connect();
            String query = "INSERT INTO transaksi(serviceId, paymentMethod, totalPrice, adminFee, totalDiscount, finalPrice, transactionDate, status, id_user, id_voucher) VALUES(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, transaction.getServiceID());
            stmt.setString(2, transaction.getPaymentMethod().name());
            stmt.setDouble(3, transaction.getTotalPrice());
            stmt.setDouble(4, transaction.getAdminFee());
            stmt.setDouble(5, transaction.getTotalDiscount());
            stmt.setDouble(6, transaction.getPriceAfterDiscount());
            Date transactionDate = new Date(transaction.getTransactionDate().getTime());
            stmt.setDate(7, transactionDate);
            stmt.setString(8, transaction.getOrderStatus().name());
            stmt.setInt(9, transaction.getUserID());

            if (transaction.getVoucher() == null) {
                stmt.setNull(10, Types.INTEGER);
            } else {
                stmt.setInt(10, transaction.getVoucher().getVoucherID());
            }

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            conn.disconnect();
        }
    }
    
    public boolean insertToGoSend(Gosend gosend){
        try {
            conn.connect();
            String query = "INSERT INTO gosend(id_transaksi, id_region_antar, item_name, nama_penerima, distance) VALUES(?,?,?,?,?)";
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, gosend.getTransactionID());
            stmt.setInt(2, gosend.getDestination());
            stmt.setString(3, gosend.getItem());
            stmt.setString(4, gosend.getReceiverName());
            stmt.setDouble(5, gosend.getDistance());

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            conn.disconnect();
        }
    }
}
