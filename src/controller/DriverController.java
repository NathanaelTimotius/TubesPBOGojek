package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Cart;

import model.Driver;
import model.Gofood;
import model.Goride;
import model.OrderStatus;
import model.PaymentMethod;
import model.Region;
import model.Transaction;
import model.Vehicle;
import model.VehicleType;
import model.Voucher;

public class DriverController {
    private DatabaseHandler conn;

    public DriverController(){
        this.conn = new DatabaseHandler();
    }

    public Driver getDriverByUsername(String NIK) {
        Driver driver = null;
        try {
            conn.connect();
            String query = "SELECT * FROM driver WHERE NIK = '" + NIK + "'";
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                driver = new Driver();
                driver.setDriverID(rs.getInt("id_driver"));
                
                Vehicle vehicle = getVehicle(rs.getInt("id_kendaraan"));
                driver.setVehicle(vehicle);

                driver.setName(rs.getString("name"));
                driver.setPhoneNumber(rs.getString("phoneNumber"));
                driver.setBirthDate(rs.getDate("birthDate"));
                driver.setNIK(rs.getString("NIK"));
                driver.setPassword(rs.getString("password"));
                driver.setIncome(rs.getDouble("income"));
                driver.setAvailable(rs.getBoolean("available"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return driver;
    }

    public Vehicle getVehicle(int id) {
        Vehicle vehicle = null;
        try {
            conn.connect();
            String query = "SELECT * FROM kendaraan WHERE id_kendaraan = " + id;
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                vehicle = new Vehicle();
                vehicle.setVehicleType(VehicleType.valueOf(rs.getString("jenisKendaraan").toUpperCase()));
                vehicle.setModel(rs.getString("modelKendaraan"));
                vehicle.setNumberPlate(rs.getString("numberPlate"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return vehicle;
    }

    public Transaction getPendingTransaction() {
        Transaction transaction = null;
        try {
            conn.connect();
            String query = "SELECT * FROM transaksi WHERE status = 'Pending' ORDER BY id_transaksi ASC";
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                transaction = new Transaction();
                transaction.setTransactionID(rs.getInt("id_transaksi"));
                Voucher voucher = getVoucher(rs.getInt("id_voucher"));
                if (voucher != null) {
                    transaction.setVoucher(voucher);
                }
                transaction.setServiceID(rs.getInt("serviceId"));
                transaction.setPaymentMethod(PaymentMethod.valueOf(rs.getString("paymentMethod").toUpperCase()));
                transaction.setTotalPrice(rs.getDouble("totalPrice"));
                transaction.setAdminFee(rs.getDouble("adminFee"));
                transaction.setTotalDiscount(rs.getDouble("totalDiscount"));
                transaction.setPriceAfterDiscount(rs.getDouble("finalPrice"));
                transaction.setTransactionDate(rs.getDate("transactionDate"));
                transaction.setOrderStatus(OrderStatus.valueOf(rs.getString("status").toUpperCase()));
                transaction.setUserID(rs.getInt("id_user"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return transaction;
    }

    public Goride getPendingGoRide(int transactionID) {
       Goride goride = null;
        try {
            conn.connect();
            String query = "SELECT * FROM goride WHERE id_transaksi = " + transactionID;
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                goride = new Goride();
                goride.setTransactionID(rs.getInt("id_transaksi"));
                goride.setTitikAntar(rs.getInt("id_region_antar"));
                goride.setTitikJemput(rs.getInt("id_region_jemput"));
                goride.setDistance(rs.getDouble("distance"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return goride;
    }
    
    public Gofood getPendingGoFood(int transactionID) {
       Gofood gofood = null;
        try {
            conn.connect();
            String query = "SELECT * FROM gofood WHERE id_transaksi = " + transactionID;
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                gofood = new Gofood();
                gofood.setTransactionID(rs.getInt("id_transaksi"));
                gofood.setRestaurantName(rs.getString("restoran_name"));
                gofood.setCart(getAllCartPerGroup(rs.getInt("id_cart")));
                gofood.setTitikAntar(rs.getInt("id_region_antar"));
                gofood.setDistance(rs.getDouble("distance"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return gofood;
    }
    
    public ArrayList<Cart> getAllCartPerGroup(int cartGroup){
        conn.connect();
        String query = "SELECT * FROM cart WHERE cart_group = '" + cartGroup + "'";
        ArrayList<Cart> carts = new ArrayList<>();
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Cart cart = new Cart();
                cart.setQuantity(rs.getInt("quantity"));
                cart.setMenu(new Controller().getMenu(rs.getInt("id_menu")));
                carts.add(cart);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (carts);
    }
    
    public Voucher getVoucher(int id){
        Voucher voucher = null;
        try {
            conn.connect();
            String query = "SELECT * FROM voucher WHERE id_voucher = " + id;
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                voucher = new Voucher();
                voucher.setVoucherName(rs.getString("voucherName"));
                voucher.setVoucherID(rs.getInt("id_voucher"));
                voucher.setDiscountPercentage(rs.getDouble("discountPercentage"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return voucher;
    }

    public boolean changeDriverState(Driver driver) {
        try {
            conn.connect();
            String query; 
            if (driver.getAvailable()) {
                query = "UPDATE driver SET available=0 WHERE id_driver=" + driver.getDriverID();
            } else {
                query = "UPDATE driver SET available=1 WHERE id_driver=" + driver.getDriverID();
            }
            Statement stmt = conn.con.createStatement();
            stmt.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            conn.disconnect();
        }
    }

    public boolean changeTransactionState(Transaction transaction) {
        try {
            conn.connect();
            String query = "";
            if (transaction.getOrderStatus() == OrderStatus.PENDING) {
                query = "UPDATE transaksi SET status='IN_PROGRESS' WHERE id_transaksi=" + transaction.getTransactionID();
            } else if (transaction.getOrderStatus() == OrderStatus.IN_PROGRESS) {
                query = "UPDATE transaksi SET status='SUCCESS'  WHERE id_transaksi=" + transaction.getTransactionID();
            }
            Statement stmt = conn.con.createStatement();
            stmt.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            conn.disconnect();
        }
    }

    public boolean setDriverGoride(Goride goride, Driver driver){
        try {
            conn.connect();
            String query = "UPDATE goride SET id_driver=" + driver.getDriverID() + " WHERE id_gojek=" + goride.getTransactionID();
            Statement stmt = conn.con.createStatement();
            stmt.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            conn.disconnect();
        }
    }
    
    public boolean setDriverGofood(Gofood gofood, Driver driver){
        try {
            conn.connect();
            String query = "UPDATE gofood SET id_driver=" + driver.getDriverID() + " WHERE id_gofood=" + gofood.getTransactionID();
            Statement stmt = conn.con.createStatement();
            stmt.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            conn.disconnect();
        }
    }
    
    public String getRegionName(int regionPosition){
        String region = "Not Found";
        try {
            conn.connect();
            String query = "SELECT * FROM region WHERE regionPosition = " + regionPosition;
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                region = rs.getString("regionName");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return region;
    }
    
    public String printAllCart(ArrayList<Cart> listCart){
        String text = "";
        for (Cart cart : listCart) {
            text += cart.getMenu().getMenuName() + ": ";
            text += cart.getQuantity()+ "  \n";
        }
        return text;
    }
}