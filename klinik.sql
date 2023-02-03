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

 Date: 03/02/2023 17:12:41
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
  `level` enum('Admin','Dokter','Bidan') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id_user`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of accounts
-- ----------------------------
INSERT INTO `accounts` VALUES (1, 'admin', 'admin', 'Dokter');
INSERT INTO `accounts` VALUES (2, 'bidan', 'bidan', 'Bidan');

-- ----------------------------
-- Table structure for detail_resep
-- ----------------------------
DROP TABLE IF EXISTS `detail_resep`;
CREATE TABLE `detail_resep`  (
  `id_detail` int NOT NULL AUTO_INCREMENT,
  `id_resep` int NULL DEFAULT NULL,
  `id_obat` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `aturan` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `jumlah` int NULL DEFAULT NULL,
  PRIMARY KEY (`id_detail`) USING BTREE,
  INDEX `detail_resep_ibfk_2`(`id_obat`) USING BTREE,
  INDEX `detail_resep_ibfk_1`(`id_resep`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of detail_resep
-- ----------------------------
INSERT INTO `detail_resep` VALUES (1, 1, '1', '3x1', 2);
INSERT INTO `detail_resep` VALUES (2, 1, '2', '3x2', 2);
INSERT INTO `detail_resep` VALUES (3, 1, NULL, NULL, NULL);
INSERT INTO `detail_resep` VALUES (4, 6, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for jadwal_pelayanan
-- ----------------------------
DROP TABLE IF EXISTS `jadwal_pelayanan`;
CREATE TABLE `jadwal_pelayanan`  (
  `id_jadwal` int NOT NULL,
  `id_medis` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `hari` enum('Senin','Selasa','Rabu','Kamis','Jumat','Sabtu','Minggu') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `jam_mulai` time NULL DEFAULT NULL,
  `jam_selesai` time NULL DEFAULT NULL,
  PRIMARY KEY (`id_jadwal`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of obat
-- ----------------------------
INSERT INTO `obat` VALUES ('AMX-005', 'Amoxcyilin', 50, 5, '100mg', '2023-01-12');

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
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

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
INSERT INTO `pasien` VALUES (23, 'Asuteru', 'Jl H Aip', '0877677', 'Laki-Laki', '2023-01-31', '', '2023-01-31');
INSERT INTO `pasien` VALUES (24, 'ASutenan', 'ddd', 'ff', 'Laki-Laki', '2023-02-01', 'aa', '2023-02-01');
INSERT INTO `pasien` VALUES (25, 'aaauu', '32', '55', 'Laki-Laki', '2023-02-02', '3232', '2023-02-02');
INSERT INTO `pasien` VALUES (26, 'Asuu', '3322', '1224', 'Laki-Laki', '2023-02-03', '20877997', '2023-02-03');
INSERT INTO `pasien` VALUES (27, 'asuu', 'Asalamm', '08797984', 'Laki-Laki', '2023-02-03', '233221221', '2023-02-03');
INSERT INTO `pasien` VALUES (28, 'dera', '223', '1123', 'Laki-Laki', '2023-02-03', '222', '2023-02-03');
INSERT INTO `pasien` VALUES (29, 'ass33', '2331234', '55213', 'Laki-Laki', '2023-02-03', '13223', '2023-02-03');
INSERT INTO `pasien` VALUES (30, 'taikucing', '22312', '442', 'Laki-Laki', '2023-02-03', '1123', '2023-02-03');
INSERT INTO `pasien` VALUES (31, 'eeqKucing', '223', '441', 'Laki-Laki', '2023-02-03', '2223', '2023-02-03');

-- ----------------------------
-- Table structure for pemeriksaan
-- ----------------------------
DROP TABLE IF EXISTS `pemeriksaan`;
CREATE TABLE `pemeriksaan`  (
  `id_pemeriksaan` int NOT NULL AUTO_INCREMENT,
  `pelayanan` enum('Umum','Bidan') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `id_pasien` int NULL DEFAULT NULL,
  `id_medis` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `tanggal` datetime NULL DEFAULT current_timestamp ON UPDATE CURRENT_TIMESTAMP,
  `diagnosa` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id_pemeriksaan`) USING BTREE,
  INDEX `pemeriksaan_ibfk_2`(`id_medis`) USING BTREE,
  INDEX `pemeriksaan_ibfk_3`(`id_pasien`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of pemeriksaan
-- ----------------------------
INSERT INTO `pemeriksaan` VALUES (1, 'Umum', 6, NULL, '2023-02-02 00:00:00', 'Kanker');
INSERT INTO `pemeriksaan` VALUES (16, 'Umum', 23, 'DR0001', '2023-02-01 16:52:15', 'Geger Otak');
INSERT INTO `pemeriksaan` VALUES (17, 'Umum', 24, 'DR0001', '2023-02-02 16:41:07', 'Hepatitis');
INSERT INTO `pemeriksaan` VALUES (18, 'Umum', 25, 'DR0001', '2023-02-01 16:41:08', 'Otak miring');
INSERT INTO `pemeriksaan` VALUES (19, 'Umum', 25, 'DR0001', '2023-02-02 16:41:10', 'Keseleo');
INSERT INTO `pemeriksaan` VALUES (20, 'Umum', 23, NULL, '2023-02-02 16:52:59', NULL);
INSERT INTO `pemeriksaan` VALUES (22, 'Umum', 27, 'DR0001', '2023-02-03 10:04:11', 'asuu');
INSERT INTO `pemeriksaan` VALUES (23, 'Umum', 28, NULL, '2023-02-03 10:43:51', NULL);
INSERT INTO `pemeriksaan` VALUES (24, 'Umum', 29, NULL, '2023-02-03 10:44:05', NULL);
INSERT INTO `pemeriksaan` VALUES (26, 'Umum', 31, NULL, '2023-02-03 10:48:17', NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of resep
-- ----------------------------
INSERT INTO `resep` VALUES (1, 16);
INSERT INTO `resep` VALUES (2, 22);
INSERT INTO `resep` VALUES (3, 23);
INSERT INTO `resep` VALUES (4, 24);
INSERT INTO `resep` VALUES (6, 26);

-- ----------------------------
-- Table structure for tenaga_medis
-- ----------------------------
DROP TABLE IF EXISTS `tenaga_medis`;
CREATE TABLE `tenaga_medis`  (
  `id_medis` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `nama` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `profesi` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `nomor_telepon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `id_user` int NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tenaga_medis
-- ----------------------------
INSERT INTO `tenaga_medis` VALUES ('DR0001', 'Dera', 'Dokter Umum', '087997565', 1);
INSERT INTO `tenaga_medis` VALUES ('BDN001', 'Astuti', 'Bidan', '0875767', 2);

-- ----------------------------
-- Triggers structure for table detail_resep
-- ----------------------------
DROP TRIGGER IF EXISTS `obat_keluar`;
delimiter ;;
CREATE TRIGGER `obat_keluar` AFTER INSERT ON `detail_resep` FOR EACH ROW UPDATE obat SET jumlah = jumlah - NEW.jumlah WHERE id_obat = NEW.id_obat
;
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table pemeriksaan
-- ----------------------------
DROP TRIGGER IF EXISTS `tr_insert_pemeriksaan`;
delimiter ;;
CREATE TRIGGER `tr_insert_pemeriksaan` AFTER INSERT ON `pemeriksaan` FOR EACH ROW BEGIN
  INSERT INTO resep (id_resep, id_pemeriksaan) 
  VALUES (NULL, NEW.id_pemeriksaan);
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table resep
-- ----------------------------
DROP TRIGGER IF EXISTS `tr_insert_resep`;
delimiter ;;
CREATE TRIGGER `tr_insert_resep` AFTER INSERT ON `resep` FOR EACH ROW BEGIN
  INSERT INTO detail_resep (id_detail, id_resep) 
  VALUES (NULL, NEW.id_resep);
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
