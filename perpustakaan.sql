-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 03, 2025 at 02:29 PM
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
-- Database: `perpustakaan`
--

-- --------------------------------------------------------

--
-- Table structure for table `anggota`
--

CREATE TABLE `anggota` (
  `fakultas` varchar(255) NOT NULL,
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `anggota`
--

INSERT INTO `anggota` (`fakultas`, `id`) VALUES
('Hukum', 3),
('Teknologi Informasi', 4),
('Ilmu Komunikasi', 5);

-- --------------------------------------------------------

--
-- Table structure for table `buku`
--

CREATE TABLE `buku` (
  `genre` varchar(255) NOT NULL,
  `isbn` varchar(255) NOT NULL,
  `item_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `buku`
--

INSERT INTO `buku` (`genre`, `isbn`, `item_id`) VALUES
('Fiksi', '9786024246947', 1),
('Fiksi', '9786024246948', 3);

-- --------------------------------------------------------

--
-- Table structure for table `dosen`
--

CREATE TABLE `dosen` (
  `nidn` int(11) NOT NULL,
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `dosen`
--

INSERT INTO `dosen` (`nidn`, `id`) VALUES
(825240098, 3);

-- --------------------------------------------------------

--
-- Table structure for table `item`
--

CREATE TABLE `item` (
  `item_id` bigint(20) NOT NULL,
  `judul` varchar(100) NOT NULL,
  `penulis` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `item`
--

INSERT INTO `item` (`item_id`, `judul`, `penulis`) VALUES
(1, 'Cantik Itu Luka', 'Eka Kurniawan'),
(2, 'Sagara', 'Leila S. Chudori'),
(3, 'Laut Bercerita', 'Leila S. Chudori'),
(4, 'Cinta Alam', 'Eka Kurniawan');

-- --------------------------------------------------------

--
-- Table structure for table `jurnal`
--

CREATE TABLE `jurnal` (
  `edisi` int(11) NOT NULL,
  `volume` int(11) NOT NULL,
  `item_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `jurnal`
--

INSERT INTO `jurnal` (`edisi`, `volume`, `item_id`) VALUES
(1, 12, 4);

-- --------------------------------------------------------

--
-- Table structure for table `mahasiswa`
--

CREATE TABLE `mahasiswa` (
  `jurusan` varchar(255) NOT NULL,
  `nim` int(11) NOT NULL,
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `mahasiswa`
--

INSERT INTO `mahasiswa` (`jurusan`, `nim`, `id`) VALUES
('Sistem Informasi', 825240099, 4),
('Ilmu Komunikasi', 825240069, 5);

-- --------------------------------------------------------

--
-- Table structure for table `majalah`
--

CREATE TABLE `majalah` (
  `nomor_edisi` int(11) NOT NULL,
  `penerbit` varchar(255) NOT NULL,
  `item_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `majalah`
--

INSERT INTO `majalah` (`nomor_edisi`, `penerbit`, `item_id`) VALUES
(12, 'Gramedia', 2);

-- --------------------------------------------------------

--
-- Table structure for table `peminjaman`
--

CREATE TABLE `peminjaman` (
  `id_peminjaman` int(11) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `tgl_kembali` datetime(6) DEFAULT NULL,
  `tgl_pinjam` datetime(6) DEFAULT NULL,
  `tgl_tempo` datetime(6) DEFAULT NULL,
  `obj_dosen_id` int(11) DEFAULT NULL,
  `obj_item_item_id` bigint(20) DEFAULT NULL,
  `obj_mahasiswa_id` int(11) DEFAULT NULL,
  `obj_staff_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `peminjaman`
--

INSERT INTO `peminjaman` (`id_peminjaman`, `status`, `tgl_kembali`, `tgl_pinjam`, `tgl_tempo`, `obj_dosen_id`, `obj_item_item_id`, `obj_mahasiswa_id`, `obj_staff_id`) VALUES
(1, 'Terlambat', '2025-12-27 00:00:00.000000', '2025-12-03 00:00:00.000000', '2025-12-10 00:00:00.000000', NULL, 2, 5, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `staff`
--

CREATE TABLE `staff` (
  `jabatan` varchar(255) NOT NULL,
  `shift` varchar(255) NOT NULL,
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `staff`
--

INSERT INTO `staff` (`jabatan`, `shift`, `id`) VALUES
('Manager', 'Siang', 1),
('Librarian', 'Siang', 2);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_type` varchar(31) NOT NULL,
  `id` int(11) NOT NULL,
  `alamat` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `no_telp` varchar(12) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_type`, `id`, `alamat`, `email`, `nama`, `no_telp`, `password`) VALUES
('staff', 1, 'Cengkareng Barat 5', 'carmenjessalyn@gmail.com', 'Carmen', '081236547890', 'Carmen_12'),
('staff', 2, 'Grogol 1, Petamburan Jakarta', 'cacamarica@gmail.com', 'Caca', '08152365478', 'Cacicu_12'),
('dosen', 3, 'Grogol 2, Jakarta Barat', 'Bryan@gmail.com', 'Bryan', '081236547825', 'Brayan_12'),
('mahasiswa', 4, 'Grogol 2, Jakarta Barat', 'Albert@gmail.com', 'Albert', '081321453547', 'Albert_12'),
('mahasiswa', 5, 'Grogol 1, Petamburan Jakarta', 'soreya@gmail.com', 'Sore', '08152365472', 'soreya_12');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `anggota`
--
ALTER TABLE `anggota`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `buku`
--
ALTER TABLE `buku`
  ADD PRIMARY KEY (`item_id`);

--
-- Indexes for table `dosen`
--
ALTER TABLE `dosen`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `item`
--
ALTER TABLE `item`
  ADD PRIMARY KEY (`item_id`);

--
-- Indexes for table `jurnal`
--
ALTER TABLE `jurnal`
  ADD PRIMARY KEY (`item_id`);

--
-- Indexes for table `mahasiswa`
--
ALTER TABLE `mahasiswa`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `majalah`
--
ALTER TABLE `majalah`
  ADD PRIMARY KEY (`item_id`);

--
-- Indexes for table `peminjaman`
--
ALTER TABLE `peminjaman`
  ADD PRIMARY KEY (`id_peminjaman`),
  ADD KEY `FKt1yyt60mcvwjyh7vkvfqm0oyp` (`obj_dosen_id`),
  ADD KEY `FKaw7biagpdg7at9ga606gh4yif` (`obj_item_item_id`),
  ADD KEY `FKi6hiqtu04ge0bq6pfwgib0r4l` (`obj_mahasiswa_id`),
  ADD KEY `FKr04mf4t52823bv9u51964j3ed` (`obj_staff_id`);

--
-- Indexes for table `staff`
--
ALTER TABLE `staff`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `item`
--
ALTER TABLE `item`
  MODIFY `item_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `peminjaman`
--
ALTER TABLE `peminjaman`
  MODIFY `id_peminjaman` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `anggota`
--
ALTER TABLE `anggota`
  ADD CONSTRAINT `FKjts2jyc25epohh259g75hpu8x` FOREIGN KEY (`id`) REFERENCES `user` (`id`);

--
-- Constraints for table `buku`
--
ALTER TABLE `buku`
  ADD CONSTRAINT `FKcfc3fcy6mbagumsjh78e9ii0y` FOREIGN KEY (`item_id`) REFERENCES `item` (`item_id`);

--
-- Constraints for table `dosen`
--
ALTER TABLE `dosen`
  ADD CONSTRAINT `FKhs01uujvaq70btyglhrv7xh27` FOREIGN KEY (`id`) REFERENCES `anggota` (`id`);

--
-- Constraints for table `jurnal`
--
ALTER TABLE `jurnal`
  ADD CONSTRAINT `FKhuwxhfe0e6rhq58dqr84qwm67` FOREIGN KEY (`item_id`) REFERENCES `item` (`item_id`);

--
-- Constraints for table `mahasiswa`
--
ALTER TABLE `mahasiswa`
  ADD CONSTRAINT `FK4tnf8l8xh734e51pmrw3vkgrv` FOREIGN KEY (`id`) REFERENCES `anggota` (`id`);

--
-- Constraints for table `majalah`
--
ALTER TABLE `majalah`
  ADD CONSTRAINT `FKt97t2tcv0rfd6poppaec9hkjp` FOREIGN KEY (`item_id`) REFERENCES `item` (`item_id`);

--
-- Constraints for table `peminjaman`
--
ALTER TABLE `peminjaman`
  ADD CONSTRAINT `FKaw7biagpdg7at9ga606gh4yif` FOREIGN KEY (`obj_item_item_id`) REFERENCES `item` (`item_id`),
  ADD CONSTRAINT `FKi6hiqtu04ge0bq6pfwgib0r4l` FOREIGN KEY (`obj_mahasiswa_id`) REFERENCES `mahasiswa` (`id`),
  ADD CONSTRAINT `FKr04mf4t52823bv9u51964j3ed` FOREIGN KEY (`obj_staff_id`) REFERENCES `staff` (`id`),
  ADD CONSTRAINT `FKt1yyt60mcvwjyh7vkvfqm0oyp` FOREIGN KEY (`obj_dosen_id`) REFERENCES `dosen` (`id`);

--
-- Constraints for table `staff`
--
ALTER TABLE `staff`
  ADD CONSTRAINT `FKbexrkamkp6kq5mi23v5dgodnw` FOREIGN KEY (`id`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
