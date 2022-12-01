/*
 Navicat Premium Data Transfer

 Source Server         : connection
 Source Server Type    : MySQL
 Source Server Version : 100424
 Source Host           : localhost:3306
 Source Schema         : klinik_drfayrus

 Target Server Type    : MySQL
 Target Server Version : 100424
 File Encoding         : 65001

 Date: 01/12/2022 18:00:20
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for data_pasien
-- ----------------------------
DROP TABLE IF EXISTS `data_pasien`;
CREATE TABLE `data_pasien`  (
  `IdPasien` int NOT NULL AUTO_INCREMENT,
  `Nama` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `Tanggal_Lahir` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `Alamat` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `Telp` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `Kode_Asuransi` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`IdPasien`) USING BTREE,
  UNIQUE INDEX `Kode_Asuransi`(`Kode_Asuransi`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of data_pasien
-- ----------------------------

-- ----------------------------
-- Table structure for dokter
-- ----------------------------
DROP TABLE IF EXISTS `dokter`;
CREATE TABLE `dokter`  (
  `IdDokter` int NOT NULL,
  `Nama` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`IdDokter`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dokter
-- ----------------------------

-- ----------------------------
-- Table structure for jadwal
-- ----------------------------
DROP TABLE IF EXISTS `jadwal`;
CREATE TABLE `jadwal`  (
  `IdJadwal` int NOT NULL,
  `IdDokter` int NULL DEFAULT NULL,
  `Hari` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `JadwalMulai` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `JadwalSelesai` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`IdJadwal`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of jadwal
-- ----------------------------

-- ----------------------------
-- Table structure for obat
-- ----------------------------
DROP TABLE IF EXISTS `obat`;
CREATE TABLE `obat`  (
  `IdObat` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `nama_obat` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`IdObat`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of obat
-- ----------------------------

-- ----------------------------
-- Table structure for rekam_medis
-- ----------------------------
DROP TABLE IF EXISTS `rekam_medis`;
CREATE TABLE `rekam_medis`  (
  `IdRekam` int NOT NULL,
  `IdResep` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `IdPasien` int NULL DEFAULT NULL,
  `IdDokter` int NULL DEFAULT NULL,
  `tgl_berobat` date NULL DEFAULT NULL,
  `Keluhan` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `diagnosa` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`IdRekam`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rekam_medis
-- ----------------------------

-- ----------------------------
-- Table structure for resep
-- ----------------------------
DROP TABLE IF EXISTS `resep`;
CREATE TABLE `resep`  (
  `IdResep` int NOT NULL,
  `IdObat` int NULL DEFAULT NULL,
  `IdRekamMedis` int NULL DEFAULT NULL,
  PRIMARY KEY (`IdResep`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of resep
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
