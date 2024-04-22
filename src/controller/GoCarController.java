package controller;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import model.Gocar;
import model.Transaction;


public class GoCarController {
    private DatabaseHandler conn;

    public GoCarController(){
        this.conn = new DatabaseHandler();
    }
    
    public boolean insertGoCarTransaction(Transaction transaction){
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
            System.out.println(transaction.getOrderStatus().name());
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

    public boolean insertToGoCar(Gocar gocar){
        try {
            conn.connect();
            String query = "INSERT INTO gocar(id_transaksi, id_region_antar, id_region_jemput, distance) VALUES(?,?,?,?)";
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, gocar.getTransactionID());
            stmt.setInt(2, gocar.getTitikAntar());
            stmt.setInt(3, gocar.getTitikJemput());
            stmt.setDouble(4, gocar.getDistance());

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
