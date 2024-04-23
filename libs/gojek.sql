-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 23, 2024 at 09:25 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gojek`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id_admin` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phoneNumber` varchar(20) DEFAULT NULL,
  `birthDate` date DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `income` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id_admin`, `name`, `phoneNumber`, `birthDate`, `username`, `password`, `income`) VALUES
(1, 'Jason Enrico', '1234567890', '2004-01-01', 'admin1', 'adminpass1', 5000),
(2, 'Nathanael Timotius', '0987654321', '2003-05-10', 'admin2', 'adminpass2', 8000),
(3, 'Ricardo Koswara', '081236491234', '2000-04-20', 'admin3', 'adminpass3', 8000);

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `id_cart` int(11) NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  `id_menu` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `cart`
--

INSERT INTO `cart` (`id_cart`, `quantity`, `id_menu`) VALUES
(1, 3, 1),
(2, 2, 1),
(3, 1, 2),
(4, 3, 2),
(5, 2, 3),
(6, 1, 3),
(7, 3, 3),
(8, 2, 4),
(9, 1, 5),
(10, 1, 1),
(11, 1, 1),
(12, 1, 1),
(13, 1, 1),
(14, 2, 1),
(15, 1, 3),
(16, 1, 5),
(17, 1, 1),
(18, 1, 1),
(19, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `cart_menu`
--

CREATE TABLE `cart_menu` (
  `id_cart` int(11) NOT NULL,
  `id_menu` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `cart_menu`
--

INSERT INTO `cart_menu` (`id_cart`, `id_menu`) VALUES
(1, 1),
(1, 2),
(2, 2),
(2, 6),
(3, 4),
(3, 8);

-- --------------------------------------------------------

--
-- Table structure for table `driver`
--

CREATE TABLE `driver` (
  `id_driver` int(11) NOT NULL,
  `id_kendaraan` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phoneNumber` varchar(20) DEFAULT NULL,
  `birthDate` date DEFAULT NULL,
  `NIK` varchar(20) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `income` double DEFAULT 0,
  `available` tinyint(1) DEFAULT 1,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `driver`
--

INSERT INTO `driver` (`id_driver`, `id_kendaraan`, `name`, `phoneNumber`, `birthDate`, `NIK`, `password`, `income`, `available`, `username`) VALUES
(1, 1, 'Driver1', '080101010101', '1987-01-01', '1234567890123456', 'driverpass1', 72000, 0, 'driver1'),
(2, 2, 'Driver2', '081313131313', '1992-05-10', '9876543210987654', 'driverpass2', 8000, 1, 'driver2'),
(3, 3, 'Driver3', '081212121212', '1979-04-15', '6543210987654321', 'driverpass3', 6000, 1, 'driver3'),
(4, 6, 'nathan', '081', '2024-04-01', '01823', 'nathan1', NULL, 1, 'nathan'),
(5, 7, 'nathan', '081', '2024-04-01', '01823', 'nathan1', NULL, 1, 'nathan'),
(6, 8, 'nathan', '082', '2024-04-05', '123', 'nathan22', NULL, 1, 'nathan'),
(7, 9, 'nathan', '1028301', '2024-04-09', '10310', 'natha', NULL, 1, 'nathan');

-- --------------------------------------------------------

--
-- Table structure for table `gocar`
--

CREATE TABLE `gocar` (
  `id_gocar` int(11) NOT NULL,
  `id_transaksi` int(11) DEFAULT NULL,
  `id_driver` int(11) DEFAULT NULL,
  `id_region_antar` int(11) DEFAULT NULL,
  `id_region_jemput` int(11) DEFAULT NULL,
  `distance` double DEFAULT NULL,
  `car_type` varchar(50) DEFAULT NULL,
  `delivery_fee` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `gocar`
--

INSERT INTO `gocar` (`id_gocar`, `id_transaksi`, `id_driver`, `id_region_antar`, `id_region_jemput`, `distance`, `car_type`, `delivery_fee`) VALUES
(4, 60, NULL, 3, 1, 2, NULL, NULL),
(5, 68, NULL, 3, 1, 2, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `gofood`
--

CREATE TABLE `gofood` (
  `id_gofood` int(11) NOT NULL,
  `id_transaksi` int(11) DEFAULT NULL,
  `id_driver` int(11) DEFAULT NULL,
  `id_restoran` int(11) DEFAULT NULL,
  `id_cart` int(11) DEFAULT NULL,
  `distance` double DEFAULT NULL,
  `restoran_name` varchar(255) DEFAULT NULL,
  `delivery_fee` double DEFAULT NULL,
  `id_region_antar` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `gofood`
--

INSERT INTO `gofood` (`id_gofood`, `id_transaksi`, `id_driver`, `id_restoran`, `id_cart`, `distance`, `restoran_name`, `delivery_fee`, `id_region_antar`) VALUES
(4, 51, NULL, 2, NULL, 0, 'Restoran B', 5000, 2),
(5, 52, NULL, 3, NULL, 2, 'Restoran C', 7000, 1),
(6, 54, NULL, 1, NULL, 0, 'Restoran A', 5000, 1),
(7, 63, NULL, 1, NULL, 0, 'Restoran A', 5000, 1),
(8, 65, NULL, 1, NULL, 1, 'Restoran A', 15000, 2);

-- --------------------------------------------------------

--
-- Table structure for table `gopay`
--

CREATE TABLE `gopay` (
  `id_gopay` int(11) NOT NULL,
  `top_up_balance` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `goride`
--

CREATE TABLE `goride` (
  `id_gojek` int(11) NOT NULL,
  `id_transaksi` int(11) DEFAULT NULL,
  `id_driver` int(11) DEFAULT NULL,
  `id_region_antar` int(11) DEFAULT NULL,
  `id_region_jemput` int(11) DEFAULT NULL,
  `distance` double DEFAULT NULL,
  `delivery_fee` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `goride`
--

INSERT INTO `goride` (`id_gojek`, `id_transaksi`, `id_driver`, `id_region_antar`, `id_region_jemput`, `distance`, `delivery_fee`) VALUES
(16, 50, NULL, 3, 1, 2, NULL),
(17, 53, 1, 3, 1, 2, NULL),
(18, 55, 2, 2, 1, 1, NULL),
(19, 62, NULL, 3, 1, 2, NULL),
(20, 64, NULL, 3, 1, 2, NULL),
(21, 66, NULL, 3, 1, 2, NULL),
(22, 67, NULL, 3, 1, 2, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `gosend`
--

CREATE TABLE `gosend` (
  `id_gosend` int(11) NOT NULL,
  `id_transaksi` int(11) DEFAULT NULL,
  `id_driver` int(11) DEFAULT NULL,
  `id_region_antar` int(11) DEFAULT NULL,
  `item_name` varchar(100) DEFAULT NULL,
  `nama_penerima` varchar(50) DEFAULT NULL,
  `distance` double DEFAULT NULL,
  `delivery_fee` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `gosend`
--

INSERT INTO `gosend` (`id_gosend`, `id_transaksi`, `id_driver`, `id_region_antar`, `item_name`, `nama_penerima`, `distance`, `delivery_fee`) VALUES
(2, 59, 1, 3, 'mouse', 'nathan', 2, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `kendaraan`
--

CREATE TABLE `kendaraan` (
  `id_kendaraan` int(11) NOT NULL,
  `jenisKendaraan` varchar(50) DEFAULT NULL,
  `modelKendaraan` varchar(50) DEFAULT NULL,
  `numberPlate` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `kendaraan`
--

INSERT INTO `kendaraan` (`id_kendaraan`, `jenisKendaraan`, `modelKendaraan`, `numberPlate`) VALUES
(1, 'Motor', 'Honda Vario', 'B 1234 XYZ'),
(2, 'Mobil', 'Toyota Avanza', 'D 5678 ABC'),
(3, 'Motor', 'Yamaha NMAX', 'F 9012 DEF'),
(4, 'Mobil', 'Honda Brio', 'D 1238 ZCY'),
(5, 'Motor', 'Honda', 'A 123'),
(6, 'Motor', 'Honda', 'A 131'),
(7, 'Motor', 'Honda', 'A 131'),
(8, 'Motor', 'Honda', 'A 123'),
(9, 'Motor', 'Honda', '012301');

-- --------------------------------------------------------

--
-- Table structure for table `menu`
--

CREATE TABLE `menu` (
  `id_menu` int(11) NOT NULL,
  `menuName` varchar(255) DEFAULT NULL,
  `menuType` varchar(255) DEFAULT NULL,
  `price` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `menu`
--

INSERT INTO `menu` (`id_menu`, `menuName`, `menuType`, `price`) VALUES
(1, 'Nasi Goreng', 'FOOD', 25000),
(2, 'Mie Goreng', 'FOOD', 22000),
(3, 'Soto Ayam', 'FOOD', 18000),
(4, 'Gado-Gado', 'FOOD', 15000),
(5, 'Martabak', 'FOOD', 35000),
(6, 'Es Teh', 'DRINK', 8000),
(7, 'Ayam Bakar', 'FOOD', 30000),
(8, 'Pisang Goreng', 'FOOD', 10000),
(9, 'Sate Ayam', 'FOOD', 25000),
(10, 'Kue Lapis', 'FOOD', 12000),
(11, 'Nasi Goreng', 'DRINK', 1000),
(13, 'Martabak Manis', 'FOOD', 15000);

-- --------------------------------------------------------

--
-- Table structure for table `region`
--

CREATE TABLE `region` (
  `id_region` int(11) NOT NULL,
  `regionName` varchar(255) DEFAULT NULL,
  `regionPosition` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `region`
--

INSERT INTO `region` (`id_region`, `regionName`, `regionPosition`) VALUES
(1, 'Dipatiukur', 1),
(2, 'Dago', 2),
(3, 'Astana Anyar', 3),
(4, 'Cimahi', 4),
(5, 'Kopo', 5);

-- --------------------------------------------------------

--
-- Table structure for table `restoran`
--

CREATE TABLE `restoran` (
  `id_restoran` int(11) NOT NULL,
  `id_region` int(11) DEFAULT NULL,
  `restaurantName` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phoneNumber` varchar(15) DEFAULT NULL,
  `income` double DEFAULT 0,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `restoran`
--

INSERT INTO `restoran` (`id_restoran`, `id_region`, `restaurantName`, `password`, `phoneNumber`, `income`, `username`) VALUES
(1, 1, 'Restoran A', 'respass1', '081234567890', 171250, 'res1'),
(2, 2, 'Restoran B', 'respass2', '081298765432', 17100, 'res2'),
(3, 3, 'Restoran C', 'respass3', '081212358724', 43250, 'res3'),
(4, 4, 'Restoran D', 'respass4', '8203812191', 0, 'res4');

-- --------------------------------------------------------

--
-- Table structure for table `restoran_menu`
--

CREATE TABLE `restoran_menu` (
  `id_restoran` int(11) NOT NULL,
  `id_menu` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `restoran_menu`
--

INSERT INTO `restoran_menu` (`id_restoran`, `id_menu`) VALUES
(1, 1),
(1, 2),
(1, 7),
(1, 8),
(1, 9),
(1, 10),
(1, 11),
(1, 13),
(2, 3),
(2, 4),
(3, 5),
(3, 6);

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `id_transaksi` int(11) NOT NULL,
  `id_voucher` int(11) DEFAULT 0,
  `serviceId` int(11) DEFAULT NULL,
  `paymentMethod` varchar(50) DEFAULT NULL,
  `totalPrice` double DEFAULT NULL,
  `adminFee` double DEFAULT NULL,
  `totalDiscount` double DEFAULT NULL,
  `finalPrice` double DEFAULT NULL,
  `transactionDate` date DEFAULT NULL,
  `status` enum('Pending','In_Progress','Success') DEFAULT NULL,
  `id_user` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`id_transaksi`, `id_voucher`, `serviceId`, `paymentMethod`, `totalPrice`, `adminFee`, `totalDiscount`, `finalPrice`, `transactionDate`, `status`, `id_user`) VALUES
(50, 1, 0, 'GOPAY', 19000, 2000, 1900, 17100, '2024-04-22', 'Success', 1),
(51, NULL, 2, 'GOPAY', 23000, 1150, 0, 23000, '2024-04-22', 'Success', 1),
(52, NULL, 2, 'GOPAY', 42000, 2100, 0, 42000, '2024-04-22', 'Success', 1),
(53, NULL, 0, 'GOPAY', 19000, 2000, 0, 19000, '2024-04-22', 'Success', 1),
(54, NULL, 2, 'GOPAY', 30000, 1500, 0, 30000, '2024-04-22', 'Success', 1),
(55, NULL, 0, 'GOPAY', 12000, 2000, 0, 12000, '2024-04-22', 'Success', 1),
(59, NULL, 3, 'GOPAY', 19000, 2000, 0, 19000, '2024-04-22', 'Success', 1),
(60, NULL, 1, 'GOPAY', 19000, 2000, 0, 19000, '2024-04-22', 'Success', 1),
(61, NULL, 4, 'GOPAY', 1000, 50, 0, 1050, '2024-04-22', 'Success', 1),
(62, 1, 0, 'GOPAY', 19000, 2000, 1900, 17100, '2024-04-23', 'Success', 1),
(63, NULL, 2, 'GOPAY', 30000, 1500, 0, 30000, '2024-04-23', 'Success', 1),
(64, NULL, 0, 'GOPAY', 19000, 2000, 0, 19000, '2024-04-23', 'Success', 1),
(65, 1, 2, 'GOPAY', 40000, 2000, 4000, 36000, '2024-04-23', 'Success', 1),
(66, NULL, 0, 'GOPAY', 19000, 2000, 0, 19000, '2024-04-23', 'Success', 1),
(67, NULL, 0, 'GOPAY', 19000, 2000, 0, 19000, '2024-04-23', 'In_Progress', 1),
(68, NULL, 0, 'GOPAY', 19000, 2000, 0, 19000, '2024-04-23', 'Pending', 1);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id_user` int(11) NOT NULL,
  `id_transaksi` int(11) DEFAULT NULL,
  `id_region` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `phoneNumber` varchar(20) DEFAULT NULL,
  `birthDate` date DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `totalBalance` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id_user`, `id_transaksi`, `id_region`, `name`, `address`, `phoneNumber`, `birthDate`, `username`, `email`, `password`, `gender`, `totalBalance`) VALUES
(1, NULL, 1, 'User1', 'Jl. Dipatiukur', '081234567890', '1995-01-01', 'user1', 'user1@gmail.com', 'userpass1', 'Male', 270670),
(2, NULL, 5, 'User2', 'Jl. Kopo', '082345678901', '2004-03-15', 'user2', 'user1@gmail.com', 'userpass2', 'Female', 75000),
(4, NULL, 4, 'Nathan', 'Sereh', '1028310', '2003-03-07', 'nathan', 'nathan@gmail.com', 'nathan123', 'Male', 0);

-- --------------------------------------------------------

--
-- Table structure for table `voucher`
--

CREATE TABLE `voucher` (
  `id_voucher` int(11) NOT NULL,
  `voucherName` varchar(255) DEFAULT NULL,
  `discountPercentage` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `voucher`
--

INSERT INTO `voucher` (`id_voucher`, `voucherName`, `discountPercentage`) VALUES
(1, 'VOUCHER1', 10),
(2, 'VOUCHER2', 20);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id_admin`);

--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`id_cart`);

--
-- Indexes for table `cart_menu`
--
ALTER TABLE `cart_menu`
  ADD PRIMARY KEY (`id_cart`,`id_menu`),
  ADD KEY `id_menu` (`id_menu`);

--
-- Indexes for table `driver`
--
ALTER TABLE `driver`
  ADD PRIMARY KEY (`id_driver`),
  ADD KEY `id_kendaraan` (`id_kendaraan`);

--
-- Indexes for table `gocar`
--
ALTER TABLE `gocar`
  ADD PRIMARY KEY (`id_gocar`),
  ADD KEY `id_transaksi` (`id_transaksi`),
  ADD KEY `id_driver` (`id_driver`),
  ADD KEY `id_region_antar` (`id_region_antar`),
  ADD KEY `id_region_jemput` (`id_region_jemput`);

--
-- Indexes for table `gofood`
--
ALTER TABLE `gofood`
  ADD PRIMARY KEY (`id_gofood`),
  ADD KEY `id_transaksi` (`id_transaksi`),
  ADD KEY `id_driver` (`id_driver`),
  ADD KEY `id_restoran` (`id_restoran`),
  ADD KEY `id_cart` (`id_cart`),
  ADD KEY `id_region_antar` (`id_region_antar`);

--
-- Indexes for table `gopay`
--
ALTER TABLE `gopay`
  ADD PRIMARY KEY (`id_gopay`);

--
-- Indexes for table `goride`
--
ALTER TABLE `goride`
  ADD PRIMARY KEY (`id_gojek`),
  ADD KEY `id_transaksi` (`id_transaksi`),
  ADD KEY `id_driver` (`id_driver`),
  ADD KEY `id_region_antar` (`id_region_antar`),
  ADD KEY `id_region_jemput` (`id_region_jemput`);

--
-- Indexes for table `gosend`
--
ALTER TABLE `gosend`
  ADD PRIMARY KEY (`id_gosend`),
  ADD KEY `id_transaksi` (`id_transaksi`),
  ADD KEY `id_driver` (`id_driver`),
  ADD KEY `id_region_antar` (`id_region_antar`);

--
-- Indexes for table `kendaraan`
--
ALTER TABLE `kendaraan`
  ADD PRIMARY KEY (`id_kendaraan`);

--
-- Indexes for table `menu`
--
ALTER TABLE `menu`
  ADD PRIMARY KEY (`id_menu`);

--
-- Indexes for table `region`
--
ALTER TABLE `region`
  ADD PRIMARY KEY (`id_region`);

--
-- Indexes for table `restoran`
--
ALTER TABLE `restoran`
  ADD PRIMARY KEY (`id_restoran`);

--
-- Indexes for table `restoran_menu`
--
ALTER TABLE `restoran_menu`
  ADD PRIMARY KEY (`id_restoran`,`id_menu`),
  ADD KEY `id_menu` (`id_menu`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id_transaksi`),
  ADD KEY `id_voucher` (`id_voucher`),
  ADD KEY `fk_user` (`id_user`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id_user`),
  ADD KEY `id_region` (`id_region`),
  ADD KEY `users_ibfk_1` (`id_transaksi`);

--
-- Indexes for table `voucher`
--
ALTER TABLE `voucher`
  ADD PRIMARY KEY (`id_voucher`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id_admin` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `cart`
--
ALTER TABLE `cart`
  MODIFY `id_cart` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `driver`
--
ALTER TABLE `driver`
  MODIFY `id_driver` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `gocar`
--
ALTER TABLE `gocar`
  MODIFY `id_gocar` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `gofood`
--
ALTER TABLE `gofood`
  MODIFY `id_gofood` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `gopay`
--
ALTER TABLE `gopay`
  MODIFY `id_gopay` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `goride`
--
ALTER TABLE `goride`
  MODIFY `id_gojek` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `gosend`
--
ALTER TABLE `gosend`
  MODIFY `id_gosend` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `kendaraan`
--
ALTER TABLE `kendaraan`
  MODIFY `id_kendaraan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `menu`
--
ALTER TABLE `menu`
  MODIFY `id_menu` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `region`
--
ALTER TABLE `region`
  MODIFY `id_region` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `restoran`
--
ALTER TABLE `restoran`
  MODIFY `id_restoran` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `transaksi`
--
ALTER TABLE `transaksi`
  MODIFY `id_transaksi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=69;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `voucher`
--
ALTER TABLE `voucher`
  MODIFY `id_voucher` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cart_menu`
--
ALTER TABLE `cart_menu`
  ADD CONSTRAINT `cart_menu_ibfk_1` FOREIGN KEY (`id_cart`) REFERENCES `cart` (`id_cart`),
  ADD CONSTRAINT `cart_menu_ibfk_2` FOREIGN KEY (`id_menu`) REFERENCES `menu` (`id_menu`);

--
-- Constraints for table `driver`
--
ALTER TABLE `driver`
  ADD CONSTRAINT `driver_ibfk_1` FOREIGN KEY (`id_kendaraan`) REFERENCES `kendaraan` (`id_kendaraan`);

--
-- Constraints for table `gocar`
--
ALTER TABLE `gocar`
  ADD CONSTRAINT `gocar_ibfk_1` FOREIGN KEY (`id_transaksi`) REFERENCES `transaksi` (`id_transaksi`),
  ADD CONSTRAINT `gocar_ibfk_2` FOREIGN KEY (`id_driver`) REFERENCES `driver` (`id_driver`),
  ADD CONSTRAINT `gocar_ibfk_3` FOREIGN KEY (`id_region_antar`) REFERENCES `region` (`id_region`),
  ADD CONSTRAINT `gocar_ibfk_4` FOREIGN KEY (`id_region_jemput`) REFERENCES `region` (`id_region`);

--
-- Constraints for table `gofood`
--
ALTER TABLE `gofood`
  ADD CONSTRAINT `gofood_ibfk_1` FOREIGN KEY (`id_transaksi`) REFERENCES `transaksi` (`id_transaksi`),
  ADD CONSTRAINT `gofood_ibfk_2` FOREIGN KEY (`id_driver`) REFERENCES `driver` (`id_driver`),
  ADD CONSTRAINT `gofood_ibfk_3` FOREIGN KEY (`id_restoran`) REFERENCES `restoran` (`id_restoran`),
  ADD CONSTRAINT `gofood_ibfk_4` FOREIGN KEY (`id_cart`) REFERENCES `cart` (`id_cart`),
  ADD CONSTRAINT `gofood_ibfk_5` FOREIGN KEY (`id_region_antar`) REFERENCES `region` (`id_region`);

--
-- Constraints for table `goride`
--
ALTER TABLE `goride`
  ADD CONSTRAINT `goride_ibfk_1` FOREIGN KEY (`id_transaksi`) REFERENCES `transaksi` (`id_transaksi`),
  ADD CONSTRAINT `goride_ibfk_2` FOREIGN KEY (`id_driver`) REFERENCES `driver` (`id_driver`),
  ADD CONSTRAINT `goride_ibfk_3` FOREIGN KEY (`id_region_antar`) REFERENCES `region` (`id_region`),
  ADD CONSTRAINT `goride_ibfk_4` FOREIGN KEY (`id_region_jemput`) REFERENCES `region` (`id_region`);

--
-- Constraints for table `gosend`
--
ALTER TABLE `gosend`
  ADD CONSTRAINT `gosend_ibfk_1` FOREIGN KEY (`id_transaksi`) REFERENCES `transaksi` (`id_transaksi`),
  ADD CONSTRAINT `gosend_ibfk_2` FOREIGN KEY (`id_driver`) REFERENCES `driver` (`id_driver`),
  ADD CONSTRAINT `gosend_ibfk_3` FOREIGN KEY (`id_region_antar`) REFERENCES `region` (`id_region`);

--
-- Constraints for table `restoran_menu`
--
ALTER TABLE `restoran_menu`
  ADD CONSTRAINT `restoran_menu_ibfk_1` FOREIGN KEY (`id_restoran`) REFERENCES `restoran` (`id_restoran`),
  ADD CONSTRAINT `restoran_menu_ibfk_2` FOREIGN KEY (`id_menu`) REFERENCES `menu` (`id_menu`);

--
-- Constraints for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD CONSTRAINT `fk_user` FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`),
  ADD CONSTRAINT `transaksi_ibfk_1` FOREIGN KEY (`id_voucher`) REFERENCES `voucher` (`id_voucher`);

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`id_transaksi`) REFERENCES `transaksi` (`id_transaksi`),
  ADD CONSTRAINT `users_ibfk_2` FOREIGN KEY (`id_region`) REFERENCES `region` (`id_region`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
