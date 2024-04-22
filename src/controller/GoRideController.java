package controller;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import model.Goride;
import model.Transaction;

public class GoRideController {
    private DatabaseHandler conn;

    public GoRideController(){
        this.conn = new DatabaseHandler();
    }

    public boolean insertGoRideTransaction(Transaction transaction){
        try {
            conn.connect();
            Date transactionDate = new Date(transaction.getTransactionDate().getTime());
            String query = "INSERT INTO transaksi(serviceId, paymentMethod, totalPrice, adminFee, totalDiscount, finalPrice, transactionDate, status, id_user, id_voucher) VALUES(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, transaction.getServiceID());
            stmt.setString(2, transaction.getPaymentMethod().name());
            stmt.setDouble(3, transaction.getTotalPrice());
            stmt.setDouble(4, transaction.getAdminFee());
            stmt.setDouble(5, transaction.getTotalDiscount());
            stmt.setDouble(6, transaction.getPriceAfterDiscount());
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

    public boolean insertToGoRide(Goride goride){
        try {
            conn.connect();
            String query = "INSERT INTO goride(id_transaksi, id_region_antar, id_region_jemput, distance) VALUES(?,?,?,?)";
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, goride.getTransactionID());
            stmt.setInt(2, goride.getTitikAntar());
            stmt.setInt(3, goride.getTitikJemput());
            stmt.setDouble(4, goride.getDistance());

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