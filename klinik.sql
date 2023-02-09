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

 Date: 10/02/2023 00:24:43
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
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

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
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of detail_resep
-- ----------------------------
INSERT INTO `detail_resep` VALUES (7, 7, 'AMX-004', '2x1', 1);
INSERT INTO `detail_resep` VALUES (8, 8, 'AMX-004', '2x3', 1);
INSERT INTO `detail_resep` VALUES (10, 9, 'AMX-004', '2x3', 1);
INSERT INTO `detail_resep` VALUES (11, 9, 'PST-0021', '2x3', 1);
INSERT INTO `detail_resep` VALUES (12, 9, 'PST-0021', '23x2', 1);
INSERT INTO `detail_resep` VALUES (13, 9, 'PST-0021', '3x3', 6);
INSERT INTO `detail_resep` VALUES (14, 10, 'AMX-004', '2x2', 1);
INSERT INTO `detail_resep` VALUES (15, 11, '', NULL, NULL);
INSERT INTO `detail_resep` VALUES (16, 12, 'PST-0021', '2x2', 1);
INSERT INTO `detail_resep` VALUES (17, 13, '', NULL, NULL);
INSERT INTO `detail_resep` VALUES (18, 14, 'PST-0021', '2x2', 1);
INSERT INTO `detail_resep` VALUES (19, 15, 'PST-0021', '2x2', 1);
INSERT INTO `detail_resep` VALUES (20, 16, 'PST-0021', '2x3', 1);
INSERT INTO `detail_resep` VALUES (21, 17, 'AMX-004', '2x3', 1);
INSERT INTO `detail_resep` VALUES (22, 18, 'PST-0021', '3x1', 1);

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
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of jadwal_pelayanan
-- ----------------------------
INSERT INTO `jadwal_pelayanan` VALUES (1, 'DR0003', 'Senin-Kamis', '04:00:00', '09:00:00');
INSERT INTO `jadwal_pelayanan` VALUES (2, 'DR003', 'Senin-Kamis', '04:00:00', '14:00:00');
INSERT INTO `jadwal_pelayanan` VALUES (4, 'BDN0004', 'Senin-Jumat', '09:00:00', '15:00:00');
INSERT INTO `jadwal_pelayanan` VALUES (5, 'DR0004', 'Senin-Jumat', '09:00:00', '15:00:00');

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
INSERT INTO `obat` VALUES ('AMX-004', 'AmoxCyilin', 50000, 44, '100mg', '2023-02-22');
INSERT INTO `obat` VALUES ('AMX-005', 'Amoxcyilin', 50, 4, '100mg', '2023-01-12');
INSERT INTO `obat` VALUES ('PST-0021', 'Antasida', 50, 74, '100mg', '2023-02-28');

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
) ENGINE = InnoDB AUTO_INCREMENT = 44 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

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
INSERT INTO `pasien` VALUES (32, 'Dera', 'Jl. H Aip. 1', '08787485', 'Laki-Laki', '2023-02-05', '3221323123', '2023-02-05');
INSERT INTO `pasien` VALUES (33, 'ASUU', 'XX!@#', '223123', 'Laki-Laki', '2023-02-05', '23333', '2023-02-05');
INSERT INTO `pasien` VALUES (34, 'tae', 'xx32', '22312', 'Laki-Laki', '2023-02-05', '2244', '2023-02-05');
INSERT INTO `pasien` VALUES (35, 'Dera', '2231', '2323232', 'Laki-Laki', '2023-02-09', '2312323', '2023-02-09');
INSERT INTO `pasien` VALUES (36, '123', '223', '222', 'Laki-Laki', '2023-02-09', '22', '2023-02-09');
INSERT INTO `pasien` VALUES (37, 'test', 'tt23', '2231', 'Laki-Laki', '2023-02-09', '', '2023-02-09');
INSERT INTO `pasien` VALUES (38, 'assssssss', '321', '223', 'Laki-Laki', '2023-02-09', '', '2023-02-09');
INSERT INTO `pasien` VALUES (39, 'kucing', '23', '22', 'Laki-Laki', '2023-02-09', NULL, '2023-02-09');
INSERT INTO `pasien` VALUES (40, 'kudanil', 'ttse', '232', 'Laki-Laki', '2023-02-10', '2221421', '2023-02-10');
INSERT INTO `pasien` VALUES (41, 'eti', 'tasea', '223', 'Laki-Laki', '2023-02-10', '2232', '2023-02-10');
INSERT INTO `pasien` VALUES (42, 'tteses', 'tt232', '1232', 'Laki-Laki', '2023-02-10', NULL, '2023-02-10');
INSERT INTO `pasien` VALUES (43, 'asss', '333', '222', 'Laki-Laki', '2023-02-10', NULL, '2023-02-10');

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
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of pemeriksaan
-- ----------------------------
INSERT INTO `pemeriksaan` VALUES (27, 'Umum', 32, 'DR0001', '2023-02-05 21:17:34', 'Ganteng');
INSERT INTO `pemeriksaan` VALUES (28, 'Umum', 33, NULL, '2023-02-05 21:33:15', NULL);
INSERT INTO `pemeriksaan` VALUES (29, 'Umum', 34, NULL, '2023-02-05 21:59:47', NULL);
INSERT INTO `pemeriksaan` VALUES (30, 'Umum', 35, 'DR0004', '2023-02-09 14:30:11', 'Kanker');
INSERT INTO `pemeriksaan` VALUES (31, 'Umum', 36, NULL, '2023-02-09 15:22:45', NULL);
INSERT INTO `pemeriksaan` VALUES (32, 'Umum', 37, 'DR0004', '2023-02-09 23:19:00', 'USUS BUNTU');
INSERT INTO `pemeriksaan` VALUES (33, 'Umum', 38, NULL, '2023-02-09 23:24:08', NULL);
INSERT INTO `pemeriksaan` VALUES (34, 'Umum', 39, 'DR0004', '2023-02-09 23:29:48', 'Kutil');
INSERT INTO `pemeriksaan` VALUES (35, 'Umum', 40, 'DR0004', '2023-02-10 00:06:07', 'diabetes');
INSERT INTO `pemeriksaan` VALUES (36, 'Bidan', 41, 'BDN0004', '2023-02-10 00:07:04', 'auu');
INSERT INTO `pemeriksaan` VALUES (37, 'Umum', 42, 'DR0004', '2023-02-10 00:07:53', 'kutil');
INSERT INTO `pemeriksaan` VALUES (38, 'Bidan', 43, 'BDN0004', '2023-02-10 00:08:33', '223');

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
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of resep
-- ----------------------------
INSERT INTO `resep` VALUES (7, 27);
INSERT INTO `resep` VALUES (8, 28);
INSERT INTO `resep` VALUES (9, 29);
INSERT INTO `resep` VALUES (10, 30);
INSERT INTO `resep` VALUES (11, 31);
INSERT INTO `resep` VALUES (12, 32);
INSERT INTO `resep` VALUES (13, 33);
INSERT INTO `resep` VALUES (14, 34);
INSERT INTO `resep` VALUES (15, 35);
INSERT INTO `resep` VALUES (16, 36);
INSERT INTO `resep` VALUES (17, 37);
INSERT INTO `resep` VALUES (18, 38);

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
