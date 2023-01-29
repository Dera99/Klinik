/*
 Navicat Premium Data Transfer

 Source Server         : db
 Source Server Type    : MySQL
 Source Server Version : 100424
 Source Host           : localhost:3306
 Source Schema         : klinik

 Target Server Type    : MySQL
 Target Server Version : 100424
 File Encoding         : 65001

 Date: 29/01/2023 23:02:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for accounts
-- ----------------------------
DROP TABLE IF EXISTS `accounts`;
CREATE TABLE `accounts`  (
  `id_user` int NOT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `level` enum('admin','dokter') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id_user`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of accounts
-- ----------------------------
INSERT INTO `accounts` VALUES (1, 'admin', 'admin', 'admin');

-- ----------------------------
-- Table structure for bidan
-- ----------------------------
DROP TABLE IF EXISTS `bidan`;
CREATE TABLE `bidan`  (
  `id_bidan` int NOT NULL AUTO_INCREMENT,
  `nama_bidan` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `nomor_telepon` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id_bidan`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bidan
-- ----------------------------

-- ----------------------------
-- Table structure for detail_resep
-- ----------------------------
DROP TABLE IF EXISTS `detail_resep`;
CREATE TABLE `detail_resep`  (
  `id_detail` int NOT NULL,
  `id_resep` int NULL DEFAULT NULL,
  `id_obat` int NULL DEFAULT NULL,
  `dosis` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `jumlah` int NULL DEFAULT NULL,
  PRIMARY KEY (`id_detail`) USING BTREE,
  INDEX `detail_resep_ibfk_2`(`id_obat`) USING BTREE,
  INDEX `detail_resep_ibfk_1`(`id_resep`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of detail_resep
-- ----------------------------
INSERT INTO `detail_resep` VALUES (1, 1, 1, '3x1', 2);
INSERT INTO `detail_resep` VALUES (2, 1, 2, '3x2', 2);
INSERT INTO `detail_resep` VALUES (3, 1, 2, '3x3', 8);

-- ----------------------------
-- Table structure for dokter
-- ----------------------------
DROP TABLE IF EXISTS `dokter`;
CREATE TABLE `dokter`  (
  `id_dokter` int NOT NULL AUTO_INCREMENT,
  `nama` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `spesialis` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `no_telp` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id_dokter`) USING BTREE,
  INDEX `nama`(`nama`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dokter
-- ----------------------------
INSERT INTO `dokter` VALUES (1, 'dede', 'umum', '0828249');

-- ----------------------------
-- Table structure for jadwal_pelayanan
-- ----------------------------
DROP TABLE IF EXISTS `jadwal_pelayanan`;
CREATE TABLE `jadwal_pelayanan`  (
  `id_jadwal` int NOT NULL,
  `id_dokter` int NULL DEFAULT NULL,
  `id_bidan` int NULL DEFAULT NULL,
  `hari` enum('Senin','Selasa','Rabu','Kamis','Jumat','Sabtu','Minggu') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `jam_mulai` time NULL DEFAULT NULL,
  `jam_selesai` time NULL DEFAULT NULL,
  PRIMARY KEY (`id_jadwal`) USING BTREE,
  INDEX `jadwal_pelayanan_ibfk_2`(`id_bidan`) USING BTREE,
  INDEX `jadwal_pelayanan_ibfk_1`(`id_dokter`) USING BTREE,
  CONSTRAINT `jadwal_pelayanan_ibfk_1` FOREIGN KEY (`id_dokter`) REFERENCES `dokter` (`id_dokter`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `jadwal_pelayanan_ibfk_2` FOREIGN KEY (`id_bidan`) REFERENCES `bidan` (`id_bidan`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of jadwal_pelayanan
-- ----------------------------

-- ----------------------------
-- Table structure for obat
-- ----------------------------
DROP TABLE IF EXISTS `obat`;
CREATE TABLE `obat`  (
  `id_obat` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `nama` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `harga` float NULL DEFAULT NULL,
  `jumlah` int NULL DEFAULT NULL,
  `dosis` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `expired` date NULL DEFAULT NULL,
  PRIMARY KEY (`id_obat`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of obat
-- ----------------------------
INSERT INTO `obat` VALUES ('AMX-005', 'Amoxcyilin', 50, 3, '100mg', '2023-01-12');

-- ----------------------------
-- Table structure for pasien
-- ----------------------------
DROP TABLE IF EXISTS `pasien`;
CREATE TABLE `pasien`  (
  `id_pasien` int NOT NULL AUTO_INCREMENT,
  `nama` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `alamat` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `no_telp` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `jenis_kelamin` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `ttl` date NULL DEFAULT NULL,
  `kode_asuransi` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `tanggal_daftar` date NULL DEFAULT current_timestamp,
  PRIMARY KEY (`id_pasien`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pasien
-- ----------------------------
INSERT INTO `pasien` VALUES (1, 'dera', 'aa', '08487', 'Laki-Laki', '2023-01-19', '221331', NULL);
INSERT INTO `pasien` VALUES (2, 'c', 'asd213', '223', 'Laki-laki', '0025-07-15', NULL, NULL);
INSERT INTO `pasien` VALUES (3, 'c', 'asd213', '223', 'Laki-laki', '0025-07-15', NULL, NULL);
INSERT INTO `pasien` VALUES (4, 'ccas', 'd3123', '2', 'Laki-laki', '0025-07-15', '', '2023-01-20');
INSERT INTO `pasien` VALUES (5, '123', 's312', '223', 'Laki-laki', '0025-07-15', '12233', '2023-01-20');
INSERT INTO `pasien` VALUES (6, 'xcc', 'asd3213', '4412', 'Laki-laki', '0025-07-15', '3222', '2023-01-20');
INSERT INTO `pasien` VALUES (7, '3231', '12244', '222', 'Laki-laki', '2023-01-20', '-', '2023-01-20');
INSERT INTO `pasien` VALUES (8, 'dda', '21312', '44', 'Laki-laki', '2023-01-20', '-', '2023-01-20');
INSERT INTO `pasien` VALUES (9, 'Dera Abdul Gani', 'sd', 'a21312', 'Laki-Laki', '2021-02-02', '-', '2023-01-20');
INSERT INTO `pasien` VALUES (10, 'xxc2', '223', '123', 'Laki-Laki', '2022-12-06', '-', '2023-01-20');
INSERT INTO `pasien` VALUES (11, 'xxc', 'dssd', '', 'Laki-Laki', '2023-01-20', '-', '2023-01-20');
INSERT INTO `pasien` VALUES (12, 'xxx', '31232', '44125', 'Laki-Laki', '2023-01-20', '-', '2023-01-20');
INSERT INTO `pasien` VALUES (13, 'czxcz1231', '22', '121', 'Laki-Laki', '2023-01-20', '-', '2023-01-20');
INSERT INTO `pasien` VALUES (14, 'czxcz1231xxx', '22', '121', 'Laki-Laki', '2023-01-20', '-', '2023-01-20');
INSERT INTO `pasien` VALUES (15, 'cczxcz1231xxxcc', '22', '121', 'Laki-Laki', '2023-01-20', '-', '2023-01-20');
INSERT INTO `pasien` VALUES (16, 'zxcx', '2231', '1242', 'Laki-Laki', '2023-01-20', '-', '2023-01-20');
INSERT INTO `pasien` VALUES (17, '23123', 'ss', '312312', 'Laki-Laki', '2023-01-20', '', '2023-01-20');
INSERT INTO `pasien` VALUES (18, '23123', 'ss', '312312', 'Laki-Laki', '2023-01-20', '2232', '2023-01-20');
INSERT INTO `pasien` VALUES (19, '23123ccc', 'ss', '312312', 'Laki-Laki', '2023-01-20', '2232', '2023-01-20');
INSERT INTO `pasien` VALUES (20, 'dera', 'aa', 'aa', 'Laki-Laki', '2023-01-20', 'aa', '2023-01-20');

-- ----------------------------
-- Table structure for pemeriksaan
-- ----------------------------
DROP TABLE IF EXISTS `pemeriksaan`;
CREATE TABLE `pemeriksaan`  (
  `id_pemeriksaan` int NOT NULL AUTO_INCREMENT,
  `pelayanan` enum('Umum','Bidan') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `id_pasien` int NULL DEFAULT NULL,
  `id_bidan` int NULL DEFAULT NULL,
  `id_dokter` int NULL DEFAULT NULL,
  `tanggal` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `diagnosa` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id_pemeriksaan`) USING BTREE,
  INDEX `pemeriksaan_ibfk_1`(`id_dokter`) USING BTREE,
  INDEX `pemeriksaan_ibfk_2`(`id_bidan`) USING BTREE,
  INDEX `pemeriksaan_ibfk_3`(`id_pasien`) USING BTREE,
  CONSTRAINT `pemeriksaan_ibfk_1` FOREIGN KEY (`id_dokter`) REFERENCES `dokter` (`id_dokter`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `pemeriksaan_ibfk_2` FOREIGN KEY (`id_bidan`) REFERENCES `bidan` (`id_bidan`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `pemeriksaan_ibfk_3` FOREIGN KEY (`id_pasien`) REFERENCES `pasien` (`id_pasien`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pemeriksaan
-- ----------------------------
INSERT INTO `pemeriksaan` VALUES (1, 'Umum', 6, NULL, 1, '2023-01-24 00:00:00', 'Kanker');

-- ----------------------------
-- Table structure for resep
-- ----------------------------
DROP TABLE IF EXISTS `resep`;
CREATE TABLE `resep`  (
  `id_resep` int NOT NULL AUTO_INCREMENT,
  `id_pemeriksaan` int NULL DEFAULT NULL,
  PRIMARY KEY (`id_resep`) USING BTREE,
  INDEX `id_pemeriksaan_ibkf1`(`id_pemeriksaan`) USING BTREE,
  CONSTRAINT `id_pemeriksaan_ibkf1` FOREIGN KEY (`id_pemeriksaan`) REFERENCES `pemeriksaan` (`id_pemeriksaan`) ON DELETE SET NULL ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of resep
-- ----------------------------
INSERT INTO `resep` VALUES (1, NULL);

-- ----------------------------
-- Table structure for transaksi
-- ----------------------------
DROP TABLE IF EXISTS `transaksi`;
CREATE TABLE `transaksi`  (
  `id_transaksi` int NOT NULL,
  `id_pemeriksaan` int NULL DEFAULT NULL,
  `total` int NULL DEFAULT NULL,
  PRIMARY KEY (`id_transaksi`) USING BTREE,
  INDEX `transaksi_ibkf1`(`id_pemeriksaan`) USING BTREE,
  CONSTRAINT `transaksi_ibkf1` FOREIGN KEY (`id_pemeriksaan`) REFERENCES `pemeriksaan` (`id_pemeriksaan`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of transaksi
-- ----------------------------

-- ----------------------------
-- Triggers structure for table detail_resep
-- ----------------------------
DROP TRIGGER IF EXISTS `obat_keluar`;
delimiter ;;
CREATE TRIGGER `obat_keluar` AFTER INSERT ON `detail_resep` FOR EACH ROW UPDATE obat SET jumlah = jumlah - NEW.jumlah WHERE id_obat = NEW.id_obat
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
