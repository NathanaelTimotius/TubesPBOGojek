package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.*;


public class Controller {
    private static Controller single_instance = null;

    public static synchronized Controller getInstance()
    {
        if (single_instance == null)
            single_instance = new Controller();
  
        return single_instance;
    }

    public User currentUser = null;
    public Driver currentDriver = null;
    public Restaurant currentRestoran = null;
    public Admin currentAdmin = null;
    
    DatabaseHandler conn = new DatabaseHandler();
    
    public ArrayList<Admin> getAllAdmin(){
        ArrayList<Admin> admins = new ArrayList<>();
        conn.connect();
        String query = "SELECT * FROM admin";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Admin admin = new Admin();
                admin.setName(rs.getString("Name"));
                admin.setPhoneNumber(rs.getString("phoneNumber"));
                admin.setBirthDate(rs.getDate("birthDate"));
                admin.setUsername(rs.getString("username"));
                admin.setPassword(rs.getString("password"));
                admin.setIncome(rs.getDouble("income"));
                admins.add(admin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (admins);
    }
    
    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        conn.connect();
        String query = "SELECT * FROM users";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                User user = new User();
                user.setUserID(rs.getInt("id_user"));
                user.setTransaction(getAllTransactionPerUser(rs.getInt("id_transaksi")));
                user.setRegion(getRegion(rs.getInt("id_Region")));
                user.setName(rs.getString("name"));
                user.setAddress(rs.getString("address"));
                user.setPhoneNumber(rs.getString("phoneNumber"));
                user.setBirthDate(rs.getDate("birthDate"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setGender(rs.getString("gender"));
                user.setTotalBalance(rs.getDouble("totalBalance"));
                
                
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (users);
    }
    
    public ArrayList<Driver> getAllDrivers() {
        ArrayList<Driver> drivers = new ArrayList<>();
        conn.connect();
        String query = "SELECT * FROM driver";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Driver driver = new Driver();
                driver.setVehicle(getKendaraanDriver(rs.getInt("id_kendaraan")));
                driver.setName(rs.getString("name"));
                driver.setPhoneNumber(rs.getString("phoneNumber"));
                driver.setBirthDate(rs.getDate("birthDate"));
                driver.setNIK(rs.getString("NIK"));
                driver.setPassword(rs.getString("password"));
                driver.setIncome(rs.getDouble("income"));
                driver.setAvailable(rs.getBoolean("available"));
                drivers.add(driver);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (drivers);
    }
    
    public Vehicle getKendaraanDriver(int idKendaraan){
        conn.connect();
        String query = "SELECT * FROM kendaraan WHERE id_kendaraan='" + idKendaraan + "'";
        Vehicle kendaraan = new Vehicle();
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                if (rs.getString("jenisKendaraan").equals(VehicleType.MOTOR.toString())){
                    kendaraan.setVehicleType(VehicleType.MOTOR);
                } else {
                    kendaraan.setVehicleType(VehicleType.MOBIL);
                }
                kendaraan.setModel(rs.getString("modelKendaraan"));
                kendaraan.setNumberPlate(rs.getString("numberPlate"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (kendaraan);
    }
    
    public ArrayList<Transaction> getAllTransactionPerUser(int Id_User) {
        ArrayList<Transaction> transactions = new ArrayList<>();
        conn.connect();
        String query = "SELECT * FROM transaksi WHERE id_user='" + Id_User + "'";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Transaction transaction = new Transaction();
                transaction.setTransactionID(rs.getInt("id_transaksi"));
                transaction.setVoucher(getVoucher(rs.getInt("id_voucher")));
//                transaction.setServiceID(rs.getInt("id_transaksi"));
                transaction.setTotalPrice(rs.getDouble("totalPrice"));
                transaction.setAdminFee(rs.getDouble("adminFee"));
                transaction.setTotalDiscount(rs.getDouble("totalDiscount"));
                transaction.setPriceAfterDiscount(rs.getDouble("finalPrice"));
                transaction.setTransactionDate(rs.getDate("transactionDate"));
                if(rs.getString("paymentMethod").equals(PaymentMethod.CASH)){
                    transaction.setPaymentMethod(PaymentMethod.CASH);
                } else {
                    transaction.setPaymentMethod(PaymentMethod.GOPAY);
                }
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (transactions);
    }
    
    public ArrayList<Region> getAllRegion(){
        conn.connect();
        String query = "SELECT * FROM region";
        ArrayList<Region> regions = new ArrayList<>();
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Region region = new Region();
                region.setRegionName(rs.getString("regionName"));
                region.setRegionPosition(rs.getInt("regionPosition"));
                regions.add(region);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (regions);
    }
    
    public ArrayList<Voucher> getAllVoucher(){
        conn.connect();
        String query = "SELECT * FROM voucher";
        ArrayList<Voucher> vouchers = new ArrayList<>();
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Voucher voucher = new Voucher();
                voucher.setVoucherID(rs.getInt("id_voucher"));
                voucher.setVoucherName(rs.getString("voucherName"));
                voucher.setDiscountPercentage(rs.getDouble("discountPercentage"));
                vouchers.add(voucher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (vouchers);
    }
    
    public Voucher getVoucher(int id_voucher){
        conn.connect();
        String query = "SELECT * FROM voucher WHERE id_voucher='" + id_voucher + "'";
        Voucher voucher = new Voucher();
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                voucher.setVoucherID(rs.getInt("id_voucher"));
                voucher.setVoucherName(rs.getString("voucherName"));
                voucher.setDiscountPercentage(rs.getDouble("discountPercentage"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (voucher);
    }
    
    public User getUser(String name, String address) {
        conn.connect();
        String query = "SELECT * FROM users WHERE Name='" + name + "'&&Address='" + address + "'";
        User user = new User();
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                user.setUserID(rs.getInt("Id_User"));
                user.setName(rs.getString("Name"));
                user.setAddress(rs.getString("Address"));
                user.setPhoneNumber(rs.getString("Phone_number"));
                user.setBirthDate(rs.getDate("Birth_date"));
                user.setUsername(rs.getString("Username"));
                user.setEmail(rs.getString("Email"));
                user.setPassword(rs.getString("Password"));
                user.setGender(rs.getString("Gender"));
                user.setTotalBalance(rs.getDouble("Total_balance"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (user);
    }
    
    public Driver getDriver(String name, String NIK) {
        conn.connect();
        String query = "SELECT * FROM users WHERE Name='" + name + "'&&NIK='" + NIK + "'";
        Driver driver = new Driver();
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                driver.setDriverID(rs.getInt("Id_driver"));
                driver.setNIK(rs.getString("NIK"));
                driver.setPassword(rs.getString("Password"));
                driver.setIncome(rs.getDouble("Income"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (driver);
    }
    
    public ArrayList<Restaurant> getAllRestoran() {
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        conn.connect();
        String query = "SELECT * FROM restoran";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Restaurant resto = new Restaurant();
                resto.setRestaurantID(rs.getInt("id_restoran"));
                resto.setRestaurantName(rs.getString("restaurantName"));
                resto.setPassword(rs.getString("password"));
                resto.setPhoneNumber(rs.getString("phoneNumber"));
                resto.setRegion(getRegion(rs.getInt("id_region")));
                resto.setListMenu(getMenuRestoran(rs.getInt("id_restoran")));
                resto.setIncome(rs.getDouble("income"));
                resto.setUsername(rs.getString("username"));
                restaurants.add(resto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (restaurants);
    }
    
    public ArrayList<Menu> getMenuRestoran(int idRestoran){
        ArrayList<Menu> menus = new ArrayList<>();
        conn.connect();
        String query = "SELECT * FROM restoran_menu";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                
                if (rs.getInt("id_restoran") == idRestoran){
                    Menu menu = getMenu(rs.getInt("id_menu"));
                    menus.add(menu);
                }
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (menus);
    }
    
    public Menu getMenu(int idMenu){
        conn.connect();
        String query = "SELECT * FROM menu WHERE id_menu ='" + idMenu + "'";
        Menu menu = new Menu();
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                menu.setMenuName(rs.getString("menuName"));
                if (rs.getString("menuType").equals(MenuType.FOOD.toString())){
                    menu.setMenuType(MenuType.FOOD);
                } else {
                    menu.setMenuType(MenuType.DRINK);
                }
                menu.setPrice(rs.getInt("price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (menu);
    }
    
    public Region getRegion(int Id_region){
        conn.connect();
        String query = "SELECT * FROM region WHERE id_region ='" + Id_region + "'";
        Region region = new Region();
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                region.setRegionName(rs.getString("regionName"));
                region.setRegionPosition(rs.getInt("regionPosition"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (region);
    }
    
    public boolean insertToCart(ArrayList<Cart> listCart){
        int cartGroup = getLastCartGroup() + 1;
        try {
            conn.connect();
            String query = "INSERT INTO cart(cart_group, id_menu, quantity) VALUES(?,?,?)";
            PreparedStatement stmt = conn.con.prepareStatement(query);
            for (Cart cart : listCart) {
                stmt.setInt(1, cartGroup);
                stmt.setInt(2, getMenuId(cart.getMenu()));
                stmt.setInt(3, cart.getQuantity());
                stmt.executeUpdate();
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            conn.disconnect();
        }
    }
    
    public boolean insertToGoFood(Gofood gofood){
        try {
            conn.connect();
            String query = "INSERT INTO gofood(id_transaksi, id_restoran, id_cart, distance, restoran_name, delivery_fee, id_region_antar) VALUES(?,?,?,?,?,?,?)";
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, gofood.getTransactionID());
            stmt.setInt(2, gofood.getRestaurantID());
            stmt.setInt(3, getLastCartGroup());
            stmt.setDouble(4, gofood.getDistance());
            stmt.setString(5, gofood.getRestaurantName());
            stmt.setDouble(6, gofood.getDeliveryFee());
            stmt.setInt(7, gofood.getTitikAntar());
            
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            conn.disconnect();
        }
    }
    
    public boolean insertToGoPay(Gopay gopay){
        try {
            conn.connect();
            String query = "INSERT INTO gopay(top_up_balance, id_transaksi) VALUES(?,?)";
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setDouble(1, gopay.getTopUpBalance());
            stmt.setInt(2, gopay.getTransactionID());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            conn.disconnect();
        }
    }
    
    public boolean insertToMenu(Menu menu){
        try {
            conn.connect();
            String query = "INSERT INTO menu(menuName, menuType, price) VALUES(?,?,?)";
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setString(1, menu.getMenuName());
            if (menu.getMenuType().equals(MenuType.FOOD)){
                stmt.setString(2, "FOOD");
            } else {
                stmt.setString(2, "DRINK");
            }
            stmt.setInt(3, menu.getPrice());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            conn.disconnect();
        }
    }
    
    public boolean insertToRestoranMenu(Restaurant restoran, Menu menu){
         try {
            conn.connect();
            String query = "INSERT INTO restoran_menu(id_restoran, id_menu) VALUES(?,?)";
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, restoran.getRestaurantID());
            stmt.setInt(2, getMenuId(menu));
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            conn.disconnect();
        }
    }
    
    public boolean insertToUser(User user){
         try {
            conn.connect();
            String query = "INSERT INTO users(id_region, name, address, phoneNumber, birthDate, username, email, password, gender, totalBalance) VALUES(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, getRegionId(user.getRegion()));
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getAddress());
            stmt.setString(4, user.getPhoneNumber());
            stmt.setDate(5, user.getBirthDate());
            stmt.setString(6, user.getUsername());
            stmt.setString(7, user.getEmail());
            stmt.setString(8, user.getPassword());
            stmt.setString(9, user.getGender());
            stmt.setDouble(10, user.getTotalBalance());
            
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            conn.disconnect();
        }
    }
    
    public boolean updateUserTotalBalance(double topUp, User user){
        try {
            conn.connect();
            String query = "UPDATE users SET totalBalance = totalBalance + " + topUp + " WHERE id_user =" + user.getUserID();

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
    
    public boolean updateIncomeRestoran(int idRestoran, double incomeRestoran){
        try {
            conn.connect();
            String query = "UPDATE restoran SET income = income + " + incomeRestoran + " WHERE id_restoran =" + idRestoran;

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
    
    public int getMenuId(Menu menu) {
        try {
            conn.connect();
            String query = "SELECT id_menu FROM menu WHERE menuName = ? AND menuType= ? AND price = ?";
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setString(1, menu.getMenuName());
            if (menu.getMenuType().equals(MenuType.FOOD)){
                stmt.setString(2, "FOOD");
            } else {
                stmt.setString(2, "DRINK");
            }
            stmt.setDouble(3, menu.getPrice());

            ResultSet rs = stmt.executeQuery();
            
             if (rs.next()) {
                return rs.getInt("id_menu");
            }
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            conn.disconnect();
        }
    }
    
    public int getRegionId(Region region){
        try {
            conn.connect();
            String query = "SELECT id_region FROM region WHERE regionName = ? AND regionPosition = ?";
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setString(1, region.getRegionName());
            stmt.setInt(2, region.getRegionPosition());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("id_region");
            }
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            conn.disconnect();
        }
    }
    
    public int getLastCartGroup() {
        try {
            conn.connect();
            String query = "SELECT * FROM cart ORDER BY id_cart DESC LIMIT 1;";
            PreparedStatement stmt = conn.con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("cart_group");
            }
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            conn.disconnect();
        }
    }

    public int getBiayaOngkir(int tempRegion, int idRegionRestoran){
        int biayaOngkir = 5000;
        if (tempRegion > idRegionRestoran){
           biayaOngkir += 10000 * (tempRegion - idRegionRestoran);
        } else {
            biayaOngkir += 1000 * (idRegionRestoran - tempRegion);
        }
        return biayaOngkir;
    }
    
    public int getBiayaMenu(ArrayList<Cart> listCart){
        int biayaMenu = 0;
        
        for (Cart cart : listCart) {
            biayaMenu += cart.getMenu().getPrice() * cart.getQuantity();
        }
        
        return biayaMenu;
    }
    
    public double getTotalBiaya(double biayaOngkir, int biayaMenu, String voucherName){
        double totalBiaya = biayaOngkir + biayaMenu;
        if (voucherName.equals("")){
            return totalBiaya;
        } else {
            ArrayList<Voucher> listVoucher = getAllVoucher();
            for (Voucher voucher : listVoucher) {
                if (voucher.getVoucherName().equals(voucherName)) {
                    totalBiaya -= (totalBiaya * voucher.getDiscountPercentage() / 100.0);
                }
            }
            return totalBiaya;
        }
    }
}