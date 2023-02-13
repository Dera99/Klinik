/*
 Navicat Premium Data Transfer

 Source Server         : connection
 Source Server Type    : MySQL
 Source Server Version : 100424
 Source Host           : localhost:3306
 Source Schema         : klinik

 Target Server Type    : MySQL
 Target Server Version : 100424
 File Encoding         : 65001

 Date: 13/02/2023 19:52:46
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
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of accounts
-- ----------------------------
INSERT INTO `accounts` VALUES (1, 'admin', 'admin', 'Admin');
INSERT INTO `accounts` VALUES (2, 'dokter', 'dokter', 'Dokter');
INSERT INTO `accounts` VALUES (17, 'bidan', 'bidan', 'Bidan');
INSERT INTO `accounts` VALUES (18, 'Ning', 'Ning', 'Bidan');
INSERT INTO `accounts` VALUES (19, 'Kiki', 'Kiki', 'Dokter');
INSERT INTO `accounts` VALUES (20, 'Risti', 'Risti', 'Dokter');
INSERT INTO `accounts` VALUES (21, 'wahyu', 'wahyu', 'Dokter');

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
) ENGINE = InnoDB AUTO_INCREMENT = 45 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of detail_resep
-- ----------------------------
INSERT INTO `detail_resep` VALUES (30, 25, '', NULL, NULL);
INSERT INTO `detail_resep` VALUES (31, 26, '', NULL, NULL);
INSERT INTO `detail_resep` VALUES (32, 27, '', NULL, NULL);
INSERT INTO `detail_resep` VALUES (33, 28, 'AMX-004', '2x3', 1);
INSERT INTO `detail_resep` VALUES (34, 29, '', NULL, NULL);
INSERT INTO `detail_resep` VALUES (35, 30, 'PCT-003', '3x1', 5);
INSERT INTO `detail_resep` VALUES (36, 31, '', NULL, NULL);
INSERT INTO `detail_resep` VALUES (37, 32, '', NULL, NULL);
INSERT INTO `detail_resep` VALUES (38, 33, '', NULL, NULL);
INSERT INTO `detail_resep` VALUES (39, 34, '', NULL, NULL);
INSERT INTO `detail_resep` VALUES (40, 30, 'CTZ-009', '1x1', 5);
INSERT INTO `detail_resep` VALUES (41, 35, 'CTZ-009', '1x1', 5);
INSERT INTO `detail_resep` VALUES (42, 36, '', NULL, NULL);
INSERT INTO `detail_resep` VALUES (43, 37, 'CTZ-009', '1x1', 5);
INSERT INTO `detail_resep` VALUES (44, 38, 'PCT-003', '2x3', 5);

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
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of jadwal_pelayanan
-- ----------------------------
INSERT INTO `jadwal_pelayanan` VALUES (1, 'DR001', 'Senin-Kamis', '04:00:00', '09:00:00');
INSERT INTO `jadwal_pelayanan` VALUES (2, 'DR002', 'Senin-Kamis', '04:00:00', '14:00:00');
INSERT INTO `jadwal_pelayanan` VALUES (4, 'DR003', 'Senin-Jumat', '09:00:00', '15:00:00');
INSERT INTO `jadwal_pelayanan` VALUES (5, 'BD001', 'Senin-Jumat', '09:00:00', '15:00:00');
INSERT INTO `jadwal_pelayanan` VALUES (6, 'BD002', '', '00:00:00', '00:00:00');
INSERT INTO `jadwal_pelayanan` VALUES (7, 'BD003', '22', '00:00:00', '00:00:00');
INSERT INTO `jadwal_pelayanan` VALUES (8, '', '', '00:00:00', '00:00:00');
INSERT INTO `jadwal_pelayanan` VALUES (9, 'DR0004', 'senin dan kamis', '08:00:00', '20:00:00');
INSERT INTO `jadwal_pelayanan` VALUES (10, 'DR0003', 'Rabu dan Sabtu', '08:00:00', '20:00:00');
INSERT INTO `jadwal_pelayanan` VALUES (11, 'BDN0004', 'Selasa, Kamis', '07:00:00', '20:00:00');
INSERT INTO `jadwal_pelayanan` VALUES (12, 'BDN0005', 'Senin, Rabu, Jum\'at', '07:00:00', '20:00:00');
INSERT INTO `jadwal_pelayanan` VALUES (13, 'DR0003', 'Selasa Dan Rabu', '08:00:00', '20:00:00');
INSERT INTO `jadwal_pelayanan` VALUES (14, 'BDN0005', 'Senin-Rabu', '08:00:00', '20:00:00');
INSERT INTO `jadwal_pelayanan` VALUES (15, 'BDN0004', 'Selasa dan Jum\'at', '07:00:00', '20:00:00');
INSERT INTO `jadwal_pelayanan` VALUES (16, 'DR0006', 'Rabu', '08:00:00', '20:00:00');
INSERT INTO `jadwal_pelayanan` VALUES (17, 'BDN0005', 'Senin', '08:00:00', '20:00:00');

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
INSERT INTO `obat` VALUES ('ABX-001', 'Ambroxol', 15000, 20, '180ml', '2023-01-23');
INSERT INTO `obat` VALUES ('AMX-004', 'AmoxCyilin', 50000, 44, '100mg', '2023-01-22');
INSERT INTO `obat` VALUES ('CTZ-009', 'Cetirizine', 15000, 15, '120mg', '2024-01-21');
INSERT INTO `obat` VALUES ('PCT-003', 'Paracetamol', 7000, 40, '100mg', '2023-01-23');
INSERT INTO `obat` VALUES ('PD-004', 'Panadol', 5000, 20, '100mg', '2024-01-13');
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
) ENGINE = InnoDB AUTO_INCREMENT = 63 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of pasien
-- ----------------------------
INSERT INTO `pasien` VALUES (52, 'test', '2231', '2223', 'Laki-Laki', '2023-02-11', NULL, '2023-02-11');
INSERT INTO `pasien` VALUES (54, 'test11', '2231', '2223', 'Laki-Laki', '2023-02-11', '224123123', '2023-02-11');
INSERT INTO `pasien` VALUES (55, 'Surya Jakasukma', 'Perumahan Duta Mekar Asri Blok D No.79', '089876547792', 'Laki-Laki', '2002-08-15', NULL, '2023-02-12');
INSERT INTO `pasien` VALUES (56, 'Sherly Laurina', 'Perumahan Coco Garden Klapanunggal, Kab. Bogor', '089876547792', 'Perempuan', '2002-08-15', '776459023', '2023-02-12');
INSERT INTO `pasien` VALUES (57, 'Wisandri', 'Perumahan Harmoni 10 Klapanunggal Blok B.28', '089518790032', 'Perempuan', '2002-08-15', '13498732', '2023-02-12');
INSERT INTO `pasien` VALUES (58, 'Safitry', 'Perumahan Cileungsi Indah, Blok D 49', '089518098726', 'Perempuan', '2000-09-08', '77394168', '2023-02-12');
INSERT INTO `pasien` VALUES (59, 'Didik Yuda Perwira', 'Perumahan Cileungsi Indah, Blok A No.51', '081377394851', 'Laki-Laki', '2000-09-08', NULL, '2023-02-12');
INSERT INTO `pasien` VALUES (60, 'Rina Widowati', 'Jl. H. Jamrud RT 01 RW 06 Kec. Bekasi', '087899640311', 'Laki-Laki', '2023-02-13', NULL, '2023-02-13');
INSERT INTO `pasien` VALUES (61, 'Septi Mawarni', 'Perumahan Cileungsi Indah', '089508549987', 'Perempuan', '2020-05-07', '998034', '2023-02-13');
INSERT INTO `pasien` VALUES (62, 'Dera Abdul Gani', 'Perumahan BRI Jakasampurna', '089577629013', 'Laki-Laki', '2000-10-12', '9903411', '2023-02-13');

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
) ENGINE = InnoDB AUTO_INCREMENT = 59 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of pemeriksaan
-- ----------------------------
INSERT INTO `pemeriksaan` VALUES (47, 'Umum', 52, NULL, '2023-02-11 14:58:44', NULL);
INSERT INTO `pemeriksaan` VALUES (48, 'Umum', 53, 'DR0004', '2023-02-11 14:59:06', 'tset');
INSERT INTO `pemeriksaan` VALUES (49, 'Umum', 54, NULL, '2023-02-11 14:58:52', NULL);
INSERT INTO `pemeriksaan` VALUES (50, 'Umum', 55, 'DR0004', '2023-02-12 01:58:10', 'Demam dan alergi');
INSERT INTO `pemeriksaan` VALUES (51, 'Umum', 56, NULL, '2023-02-12 00:54:24', NULL);
INSERT INTO `pemeriksaan` VALUES (52, 'Bidan', 57, NULL, '2023-02-12 00:55:48', NULL);
INSERT INTO `pemeriksaan` VALUES (53, 'Bidan', 58, NULL, '2023-02-12 01:49:12', NULL);
INSERT INTO `pemeriksaan` VALUES (54, 'Umum', 59, NULL, '2023-02-12 01:50:51', NULL);
INSERT INTO `pemeriksaan` VALUES (55, 'Umum', 60, 'DR0004', '2023-02-13 02:11:50', 'Alergi ringan');
INSERT INTO `pemeriksaan` VALUES (56, 'Umum', 61, NULL, '2023-02-13 18:57:00', NULL);
INSERT INTO `pemeriksaan` VALUES (57, 'Bidan', 61, 'BDN0004', '2023-02-13 19:26:49', 'Alergi');
INSERT INTO `pemeriksaan` VALUES (58, 'Umum', 62, 'DR0004', '2023-02-13 19:42:59', 'Flu');

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
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

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
INSERT INTO `resep` VALUES (30, 50);
INSERT INTO `resep` VALUES (31, 51);
INSERT INTO `resep` VALUES (32, 52);
INSERT INTO `resep` VALUES (33, 53);
INSERT INTO `resep` VALUES (34, 54);
INSERT INTO `resep` VALUES (35, 55);
INSERT INTO `resep` VALUES (36, 56);
INSERT INTO `resep` VALUES (37, 57);
INSERT INTO `resep` VALUES (38, 58);

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
INSERT INTO `tenaga_medis` VALUES ('BDN0004', 'Riska', 'Perumahan Duta Mekar Asri Cileungsi', '089677984319', 'riskaaulia71@gmail.com', 'Bidan', 17);
INSERT INTO `tenaga_medis` VALUES ('BDN0005', 'Sri Ningsing', 'Perumahan Cileungsi Indah Blok C.69', '08137964319', 'sriningsing1@gmail.com', 'Bidan', 18);
INSERT INTO `tenaga_medis` VALUES ('DR0003', 'dr. Okta', 'Jakasampurna Bekasi', '089577935149', 'droktavian@gmail.com', 'Dokter Umum', NULL);
INSERT INTO `tenaga_medis` VALUES ('DR0004', 'dr. Fayrus', 'Klapanunggal, Bogor Regency', '0859-2006-8006', 'fayrusbasalamah@gmail.com', 'Dokter Umum', 2);
INSERT INTO `tenaga_medis` VALUES ('DR0006', 'Kiki', 'Perumahan Bulak Kapal Bekasi, Jawa Barat', '087933146901', 'kikikurniawan@gmail.com', 'Dokter Umum', 19);
INSERT INTO `tenaga_medis` VALUES ('DR0007', 'Risti', 'Jl. Raya Narogong Cileungsi, Kec. Cileungsi', '08958879431', 'Ristianawati31@gmail.com', 'Dokter Umum', 20);
INSERT INTO `tenaga_medis` VALUES ('DR0008', 'Wahyu', 'Perumahan Duta Mekar Asri', '089577390021', 'wahyuardd@gmail.com', 'Dokter Umum', 21);

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
