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

 Date: 11/02/2023 15:02:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for accounts
-- ----------------------------
DROP TABLE IF EXISTS `accounts`;
CREATE TABLE `accounts`  (
  `id_user` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `level` enum('Admin','Dokter','Bidan') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id_user`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of accounts
-- ----------------------------
INSERT INTO `accounts` VALUES (1, 'admin', 'admin', 'Admin');
INSERT INTO `accounts` VALUES (2, 'dokter', 'dokter', 'Dokter');
INSERT INTO `accounts` VALUES (4, 'bidan', 'bidan', 'Bidan');

-- ----------------------------
-- Table structure for detail_resep
-- ----------------------------
DROP TABLE IF EXISTS `detail_resep`;
CREATE TABLE `detail_resep`  (
  `id_detail` int NOT NULL AUTO_INCREMENT,
  `id_resep` int NULL DEFAULT NULL,
  `id_obat` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `aturan` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `jumlah` int NULL DEFAULT NULL,
  PRIMARY KEY (`id_detail`) USING BTREE,
  INDEX `detail_resep_ibfk_2`(`id_obat`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of detail_resep
-- ----------------------------
INSERT INTO `detail_resep` VALUES (30, 25, '', NULL, NULL);
INSERT INTO `detail_resep` VALUES (31, 26, '', NULL, NULL);
INSERT INTO `detail_resep` VALUES (32, 27, '', NULL, NULL);
INSERT INTO `detail_resep` VALUES (33, 28, 'AMX-004', '2x3', 1);
INSERT INTO `detail_resep` VALUES (34, 29, '', NULL, NULL);

-- ----------------------------
-- Table structure for jadwal_pelayanan
-- ----------------------------
DROP TABLE IF EXISTS `jadwal_pelayanan`;
CREATE TABLE `jadwal_pelayanan`  (
  `id_jadwal` int NOT NULL AUTO_INCREMENT,
  `id_medis` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `hari` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `jam_mulai` time NULL DEFAULT NULL,
  `jam_selesai` time NULL DEFAULT NULL,
  PRIMARY KEY (`id_jadwal`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of jadwal_pelayanan
-- ----------------------------
INSERT INTO `jadwal_pelayanan` VALUES (1, 'DR0003', 'Senin-Kamis', '04:00:00', '09:00:00');
INSERT INTO `jadwal_pelayanan` VALUES (2, 'DR003', 'Senin-Kamis', '04:00:00', '14:00:00');
INSERT INTO `jadwal_pelayanan` VALUES (4, 'BDN0004', 'Senin-Jumat', '09:00:00', '15:00:00');
INSERT INTO `jadwal_pelayanan` VALUES (5, 'DR0004', 'Senin-Jumat', '09:00:00', '15:00:00');
INSERT INTO `jadwal_pelayanan` VALUES (6, 'DR0004', '', '00:00:00', '00:00:00');
INSERT INTO `jadwal_pelayanan` VALUES (7, 'DR0004', '22', '00:00:00', '00:00:00');
INSERT INTO `jadwal_pelayanan` VALUES (8, 'DR0004', '', '00:00:00', '00:00:00');

-- ----------------------------
-- Table structure for obat
-- ----------------------------
DROP TABLE IF EXISTS `obat`;
CREATE TABLE `obat`  (
  `id_obat` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `nama_obat` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `harga` float NULL DEFAULT NULL,
  `jumlah` int NULL DEFAULT NULL,
  `dosis` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `expired` date NULL DEFAULT NULL,
  PRIMARY KEY (`id_obat`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of obat
-- ----------------------------
INSERT INTO `obat` VALUES ('AMX-004', 'AmoxCyilin', 50000, 44, '100mg', '2023-02-22');
INSERT INTO `obat` VALUES ('AMX-005', 'Amoxcyilin', 50, 4, '100mg', '2023-01-12');
INSERT INTO `obat` VALUES ('PST-0021', 'Antasida', 50, 73, '100mg', '2023-02-28');

-- ----------------------------
-- Table structure for pasien
-- ----------------------------
DROP TABLE IF EXISTS `pasien`;
CREATE TABLE `pasien`  (
  `id_pasien` int NOT NULL AUTO_INCREMENT,
  `nama_pasien` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `alamat` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `no_telp` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `jenis_kelamin` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `ttl` date NULL DEFAULT NULL,
  `kode_asuransi` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `tanggal_daftar` date NULL DEFAULT current_timestamp,
  PRIMARY KEY (`id_pasien`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 55 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of pasien
-- ----------------------------
INSERT INTO `pasien` VALUES (52, 'test', '2231', '2223', 'Laki-Laki', '2023-02-11', NULL, '2023-02-11');
INSERT INTO `pasien` VALUES (53, 'test11', '2231', '2223', 'Laki-Laki', '2023-02-11', '', '2023-02-11');
INSERT INTO `pasien` VALUES (54, 'test11', '2231', '2223', 'Laki-Laki', '2023-02-11', '224123123', '2023-02-11');

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
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of pemeriksaan
-- ----------------------------
INSERT INTO `pemeriksaan` VALUES (47, 'Umum', 52, NULL, '2023-02-11 14:58:44', NULL);
INSERT INTO `pemeriksaan` VALUES (48, 'Umum', 53, 'DR0004', '2023-02-11 14:59:06', 'tset');
INSERT INTO `pemeriksaan` VALUES (49, 'Umum', 54, NULL, '2023-02-11 14:58:52', NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of resep
-- ----------------------------
INSERT INTO `resep` VALUES (20, NULL);
INSERT INTO `resep` VALUES (21, NULL);
INSERT INTO `resep` VALUES (22, NULL);
INSERT INTO `resep` VALUES (23, NULL);
INSERT INTO `resep` VALUES (24, NULL);
INSERT INTO `resep` VALUES (25, NULL);
INSERT INTO `resep` VALUES (26, NULL);
INSERT INTO `resep` VALUES (27, 47);
INSERT INTO `resep` VALUES (28, 48);
INSERT INTO `resep` VALUES (29, 49);

-- ----------------------------
-- Table structure for tenaga_medis
-- ----------------------------
DROP TABLE IF EXISTS `tenaga_medis`;
CREATE TABLE `tenaga_medis`  (
  `id_medis` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `nama` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `alamat` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `nomor_telepon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `profesi` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `id_user` int NULL DEFAULT NULL,
  PRIMARY KEY (`id_medis`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tenaga_medis
-- ----------------------------
INSERT INTO `tenaga_medis` VALUES ('ADM0004', 'Sarah', 'Perumahan CI blok A', '08789792', 'sarah@gmail.com', 'Admin', 1);
INSERT INTO `tenaga_medis` VALUES ('BDN0004', 'Eti', 'Perumahan CI blok A', '08878', 'Eti@gmail.com', 'Bidan', 4);
INSERT INTO `tenaga_medis` VALUES ('DR0004', 'Dera', 'Perumahan BRI No.6', '0877959', 'dera@gmail.com', 'Dokter Umum', 2);

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

-- ----------------------------
-- Triggers structure for table tenaga_medis
-- ----------------------------
DROP TRIGGER IF EXISTS `tenaga_medis_before_delete`;
delimiter ;;
CREATE TRIGGER `tenaga_medis_before_delete` BEFORE DELETE ON `tenaga_medis` FOR EACH ROW BEGIN
  DELETE FROM accounts WHERE id_user = OLD.id_user;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
