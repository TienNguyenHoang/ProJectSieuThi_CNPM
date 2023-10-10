USE [SIEUTHIMINI]
GO
/****** Object:  Table [dbo].[CT_PHIEUNHAP]    Script Date: 10/10/2023 11:51:35 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CT_PHIEUNHAP](
	[MaPhieuNhap] [char](6) NOT NULL,
	[MaHH] [char](6) NOT NULL,
	[MaNCC] [char](6) NOT NULL,
	[SoLuongNhap] [float] NOT NULL,
	[DonGiaNhap] [float] NOT NULL,
 CONSTRAINT [PK_CT_PHIEUNHAP] PRIMARY KEY CLUSTERED 
(
	[MaPhieuNhap] ASC,
	[MaHH] ASC,
	[MaNCC] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CTHD_BANHANG]    Script Date: 10/10/2023 11:51:35 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CTHD_BANHANG](
	[MaHD] [char](6) NOT NULL,
	[MaHH] [char](6) NOT NULL,
	[SoLuongBan] [float] NOT NULL,
 CONSTRAINT [PK_CTHD_BANHANG] PRIMARY KEY CLUSTERED 
(
	[MaHH] ASC,
	[MaHD] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HANGHOA]    Script Date: 10/10/2023 11:51:35 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HANGHOA](
	[MaHH] [char](6) NOT NULL,
	[TenHH] [nvarchar](30) NOT NULL,
	[MaKM] [char](6) NULL,
	[NgSanXuat] [date] NOT NULL,
	[HanSuDung] [date] NOT NULL,
	[DonGiaBan] [float] NOT NULL,
	[DonVi] [nvarchar](10) NOT NULL,
	[MaLH] [char](6) NOT NULL,
	[SoLuong] [float] NOT NULL,
	[TinhTrang] [bit] NOT NULL,
 CONSTRAINT [PK_HH] PRIMARY KEY CLUSTERED 
(
	[MaHH] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HOADONBANHANG]    Script Date: 10/10/2023 11:51:35 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HOADONBANHANG](
	[MaHD] [char](6) NOT NULL,
	[NgLapHD] [smalldatetime] NOT NULL,
	[ThanhTien] [float] NOT NULL,
	[MaKH] [char](6) NOT NULL,
	[MaNV] [char](6) NOT NULL,
 CONSTRAINT [PK_HDBN] PRIMARY KEY CLUSTERED 
(
	[MaHD] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KHACHHANG]    Script Date: 10/10/2023 11:51:35 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KHACHHANG](
	[MaKH] [char](6) NOT NULL,
	[HoTen] [nvarchar](30) NOT NULL,
	[Diem] [int] NOT NULL,
	[SDT] [char](10) NOT NULL,
	[NgSinh] [date] NOT NULL,
	[TinhTrang] [bit] NOT NULL,
 CONSTRAINT [PK_KH] PRIMARY KEY CLUSTERED 
(
	[MaKH] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KHUYENMAI]    Script Date: 10/10/2023 11:51:35 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KHUYENMAI](
	[MaKM] [char](6) NOT NULL,
	[NgBatDauKM] [date] NOT NULL,
	[NgKetThucKM] [date] NOT NULL,
	[TiLeGiam] [float] NOT NULL,
	[TinhTrang] [bit] NOT NULL,
	[MoTa] [ntext] NOT NULL,
 CONSTRAINT [PK_HDN] PRIMARY KEY CLUSTERED 
(
	[MaKM] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LOAIHANG]    Script Date: 10/10/2023 11:51:35 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LOAIHANG](
	[MaLH] [char](6) NOT NULL,
	[TenLH] [nvarchar](30) NOT NULL,
	[TinhTrang] [bit] NOT NULL,
 CONSTRAINT [PK_LH] PRIMARY KEY CLUSTERED 
(
	[MaLH] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NHACUNGCAP]    Script Date: 10/10/2023 11:51:35 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NHACUNGCAP](
	[MaNCC] [char](6) NOT NULL,
	[TenNCC] [nvarchar](30) NOT NULL,
	[DiaChi] [nvarchar](100) NOT NULL,
	[Email] [char](30) NOT NULL,
	[SDT] [char](10) NOT NULL,
	[TinhTrang] [bit] NOT NULL,
 CONSTRAINT [PK_NCC] PRIMARY KEY CLUSTERED 
(
	[MaNCC] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NHANVIEN]    Script Date: 10/10/2023 11:51:35 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NHANVIEN](
	[MaNV] [char](6) NOT NULL,
	[TenNV] [nvarchar](30) NOT NULL,
	[NgSinh] [date] NOT NULL,
	[GioiTinh] [char](3) NOT NULL,
	[SDT] [char](10) NOT NULL,
	[Email] [char](30) NOT NULL,
	[MatKhau] [char](30) NULL,
	[MaQuyen] [char](6) NULL,
	[TinhTrang] [bit] NOT NULL,
 CONSTRAINT [PK_NV] PRIMARY KEY CLUSTERED 
(
	[MaNV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NHOMQUYEN]    Script Date: 10/10/2023 11:51:35 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NHOMQUYEN](
	[MaQuyen] [char](6) NOT NULL,
	[TenQuyen] [nvarchar](30) NOT NULL,
	[MoTa] [nvarchar](100) NOT NULL,
 CONSTRAINT [PK_NQ] PRIMARY KEY CLUSTERED 
(
	[MaQuyen] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PHIEUNHAP]    Script Date: 10/10/2023 11:51:35 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PHIEUNHAP](
	[MaPhieuNhap] [char](6) NOT NULL,
	[NgLapPhieu] [smalldatetime] NOT NULL,
	[TinhTrang] [bit] NOT NULL,
	[MaNV] [char](6) NOT NULL,
 CONSTRAINT [PK_PN] PRIMARY KEY CLUSTERED 
(
	[MaPhieuNhap] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[CT_PHIEUNHAP] ([MaPhieuNhap], [MaHH], [MaNCC], [SoLuongNhap], [DonGiaNhap]) VALUES (N'PN01  ', N'HH001 ', N'NCC01 ', 100, 4000)
INSERT [dbo].[CT_PHIEUNHAP] ([MaPhieuNhap], [MaHH], [MaNCC], [SoLuongNhap], [DonGiaNhap]) VALUES (N'PN01  ', N'HH003 ', N'NCC01 ', 100, 4000)
INSERT [dbo].[CT_PHIEUNHAP] ([MaPhieuNhap], [MaHH], [MaNCC], [SoLuongNhap], [DonGiaNhap]) VALUES (N'PN01  ', N'HH004 ', N'NCC01 ', 100, 4000)
INSERT [dbo].[CT_PHIEUNHAP] ([MaPhieuNhap], [MaHH], [MaNCC], [SoLuongNhap], [DonGiaNhap]) VALUES (N'PN01  ', N'HH005 ', N'NCC03 ', 30, 100000)
INSERT [dbo].[CT_PHIEUNHAP] ([MaPhieuNhap], [MaHH], [MaNCC], [SoLuongNhap], [DonGiaNhap]) VALUES (N'PN02  ', N'HH001 ', N'NCC01 ', 50, 4000)
INSERT [dbo].[CT_PHIEUNHAP] ([MaPhieuNhap], [MaHH], [MaNCC], [SoLuongNhap], [DonGiaNhap]) VALUES (N'PN02  ', N'HH002 ', N'NCC01 ', 50, 4000)
INSERT [dbo].[CT_PHIEUNHAP] ([MaPhieuNhap], [MaHH], [MaNCC], [SoLuongNhap], [DonGiaNhap]) VALUES (N'PN02  ', N'HH011 ', N'NCC02 ', 100, 6000)
INSERT [dbo].[CT_PHIEUNHAP] ([MaPhieuNhap], [MaHH], [MaNCC], [SoLuongNhap], [DonGiaNhap]) VALUES (N'PN02  ', N'HH016 ', N'NCC04 ', 100, 20000)
INSERT [dbo].[CT_PHIEUNHAP] ([MaPhieuNhap], [MaHH], [MaNCC], [SoLuongNhap], [DonGiaNhap]) VALUES (N'PN03  ', N'HH017 ', N'NCC03 ', 30, 13000)
INSERT [dbo].[CT_PHIEUNHAP] ([MaPhieuNhap], [MaHH], [MaNCC], [SoLuongNhap], [DonGiaNhap]) VALUES (N'PN03  ', N'HH018 ', N'NCC03 ', 30, 5000)
INSERT [dbo].[CT_PHIEUNHAP] ([MaPhieuNhap], [MaHH], [MaNCC], [SoLuongNhap], [DonGiaNhap]) VALUES (N'PN03  ', N'HH019 ', N'NCC03 ', 30, 25000)
INSERT [dbo].[CT_PHIEUNHAP] ([MaPhieuNhap], [MaHH], [MaNCC], [SoLuongNhap], [DonGiaNhap]) VALUES (N'PN03  ', N'HH020 ', N'NCC03 ', 30, 10000)
INSERT [dbo].[CT_PHIEUNHAP] ([MaPhieuNhap], [MaHH], [MaNCC], [SoLuongNhap], [DonGiaNhap]) VALUES (N'PN04  ', N'HH005 ', N'NCC04 ', 20, 150000)
INSERT [dbo].[CT_PHIEUNHAP] ([MaPhieuNhap], [MaHH], [MaNCC], [SoLuongNhap], [DonGiaNhap]) VALUES (N'PN04  ', N'HH011 ', N'NCC02 ', 50, 6000)
INSERT [dbo].[CT_PHIEUNHAP] ([MaPhieuNhap], [MaHH], [MaNCC], [SoLuongNhap], [DonGiaNhap]) VALUES (N'PN04  ', N'HH012 ', N'NCC02 ', 50, 10000)
INSERT [dbo].[CT_PHIEUNHAP] ([MaPhieuNhap], [MaHH], [MaNCC], [SoLuongNhap], [DonGiaNhap]) VALUES (N'PN04  ', N'HH013 ', N'NCC04 ', 50, 4000)
GO
INSERT [dbo].[CTHD_BANHANG] ([MaHD], [MaHH], [SoLuongBan]) VALUES (N'HD01  ', N'HH001 ', 2)
INSERT [dbo].[CTHD_BANHANG] ([MaHD], [MaHH], [SoLuongBan]) VALUES (N'HD01  ', N'HH002 ', 2)
INSERT [dbo].[CTHD_BANHANG] ([MaHD], [MaHH], [SoLuongBan]) VALUES (N'HD01  ', N'HH003 ', 2)
INSERT [dbo].[CTHD_BANHANG] ([MaHD], [MaHH], [SoLuongBan]) VALUES (N'HD02  ', N'HH009 ', 5)
INSERT [dbo].[CTHD_BANHANG] ([MaHD], [MaHH], [SoLuongBan]) VALUES (N'HD02  ', N'HH010 ', 10)
INSERT [dbo].[CTHD_BANHANG] ([MaHD], [MaHH], [SoLuongBan]) VALUES (N'HD03  ', N'HH017 ', 5)
INSERT [dbo].[CTHD_BANHANG] ([MaHD], [MaHH], [SoLuongBan]) VALUES (N'HD03  ', N'HH018 ', 2)
GO
INSERT [dbo].[HANGHOA] ([MaHH], [TenHH], [MaKM], [NgSanXuat], [HanSuDung], [DonGiaBan], [DonVi], [MaLH], [SoLuong], [TinhTrang]) VALUES (N'HH001 ', N'Pepsi', N'KM01  ', CAST(N'2023-10-05' AS Date), CAST(N'2024-10-05' AS Date), 10000, N'chai', N'LH01  ', 20, 1)
INSERT [dbo].[HANGHOA] ([MaHH], [TenHH], [MaKM], [NgSanXuat], [HanSuDung], [DonGiaBan], [DonVi], [MaLH], [SoLuong], [TinhTrang]) VALUES (N'HH002 ', N'Coca Cola', N'KM02  ', CAST(N'2023-10-05' AS Date), CAST(N'2024-10-05' AS Date), 10000, N'chai', N'LH01  ', 20, 1)
INSERT [dbo].[HANGHOA] ([MaHH], [TenHH], [MaKM], [NgSanXuat], [HanSuDung], [DonGiaBan], [DonVi], [MaLH], [SoLuong], [TinhTrang]) VALUES (N'HH003 ', N'Mirinda soda kem', N'KM01  ', CAST(N'2023-10-05' AS Date), CAST(N'2024-10-05' AS Date), 10000, N'lon', N'LH01  ', 20, 1)
INSERT [dbo].[HANGHOA] ([MaHH], [TenHH], [MaKM], [NgSanXuat], [HanSuDung], [DonGiaBan], [DonVi], [MaLH], [SoLuong], [TinhTrang]) VALUES (N'HH004 ', N'7Up', N'KM02  ', CAST(N'2023-10-05' AS Date), CAST(N'2024-10-05' AS Date), 10000, N'lon', N'LH01  ', 20, 1)
INSERT [dbo].[HANGHOA] ([MaHH], [TenHH], [MaKM], [NgSanXuat], [HanSuDung], [DonGiaBan], [DonVi], [MaLH], [SoLuong], [TinhTrang]) VALUES (N'HH005 ', N'Thịt Bò ', N'KM01  ', CAST(N'2023-10-05' AS Date), CAST(N'2023-10-12' AS Date), 250000, N'Kg', N'LH02  ', 20, 1)
INSERT [dbo].[HANGHOA] ([MaHH], [TenHH], [MaKM], [NgSanXuat], [HanSuDung], [DonGiaBan], [DonVi], [MaLH], [SoLuong], [TinhTrang]) VALUES (N'HH006 ', N'Thịt Gà ', NULL, CAST(N'2023-10-05' AS Date), CAST(N'2023-10-12' AS Date), 120000, N'Kg', N'LH02  ', 20, 1)
INSERT [dbo].[HANGHOA] ([MaHH], [TenHH], [MaKM], [NgSanXuat], [HanSuDung], [DonGiaBan], [DonVi], [MaLH], [SoLuong], [TinhTrang]) VALUES (N'HH007 ', N'Thịt Heo Ba rọi', N'KM01  ', CAST(N'2023-10-05' AS Date), CAST(N'2023-10-12' AS Date), 159000, N'Kg', N'LH02  ', 20, 1)
INSERT [dbo].[HANGHOA] ([MaHH], [TenHH], [MaKM], [NgSanXuat], [HanSuDung], [DonGiaBan], [DonVi], [MaLH], [SoLuong], [TinhTrang]) VALUES (N'HH008 ', N'Thịt Cá Diêu Hồng', NULL, CAST(N'2023-10-05' AS Date), CAST(N'2023-10-12' AS Date), 45000, N'Kg', N'LH02  ', 20, 1)
INSERT [dbo].[HANGHOA] ([MaHH], [TenHH], [MaKM], [NgSanXuat], [HanSuDung], [DonGiaBan], [DonVi], [MaLH], [SoLuong], [TinhTrang]) VALUES (N'HH009 ', N'Mì Hảo Hảo', N'KM02  ', CAST(N'2023-10-05' AS Date), CAST(N'2024-04-05' AS Date), 3500, N'gói', N'LH03  ', 100, 1)
INSERT [dbo].[HANGHOA] ([MaHH], [TenHH], [MaKM], [NgSanXuat], [HanSuDung], [DonGiaBan], [DonVi], [MaLH], [SoLuong], [TinhTrang]) VALUES (N'HH010 ', N'Phở Bò Đệ Nhất', NULL, CAST(N'2023-10-05' AS Date), CAST(N'2024-04-05' AS Date), 8400, N'gói', N'LH03  ', 100, 1)
INSERT [dbo].[HANGHOA] ([MaHH], [TenHH], [MaKM], [NgSanXuat], [HanSuDung], [DonGiaBan], [DonVi], [MaLH], [SoLuong], [TinhTrang]) VALUES (N'HH011 ', N'Hủ Tiếu Nam Vang', N'KM01  ', CAST(N'2023-10-05' AS Date), CAST(N'2024-04-05' AS Date), 9400, N'gói', N'LH03  ', 100, 1)
INSERT [dbo].[HANGHOA] ([MaHH], [TenHH], [MaKM], [NgSanXuat], [HanSuDung], [DonGiaBan], [DonVi], [MaLH], [SoLuong], [TinhTrang]) VALUES (N'HH012 ', N'Miến Khô', NULL, CAST(N'2023-10-05' AS Date), CAST(N'2024-04-05' AS Date), 20000, N'gói', N'LH03  ', 100, 1)
INSERT [dbo].[HANGHOA] ([MaHH], [TenHH], [MaKM], [NgSanXuat], [HanSuDung], [DonGiaBan], [DonVi], [MaLH], [SoLuong], [TinhTrang]) VALUES (N'HH013 ', N'Snack Lay''s sườn', N'KM01  ', CAST(N'2023-10-05' AS Date), CAST(N'2024-04-05' AS Date), 10000, N'gói', N'LH04  ', 100, 1)
INSERT [dbo].[HANGHOA] ([MaHH], [TenHH], [MaKM], [NgSanXuat], [HanSuDung], [DonGiaBan], [DonVi], [MaLH], [SoLuong], [TinhTrang]) VALUES (N'HH014 ', N'Bánh Gạo One One', NULL, CAST(N'2023-10-05' AS Date), CAST(N'2024-04-05' AS Date), 22000, N'gói', N'LH04  ', 50, 1)
INSERT [dbo].[HANGHOA] ([MaHH], [TenHH], [MaKM], [NgSanXuat], [HanSuDung], [DonGiaBan], [DonVi], [MaLH], [SoLuong], [TinhTrang]) VALUES (N'HH015 ', N'Bánh Quy Oreo', NULL, CAST(N'2023-10-05' AS Date), CAST(N'2024-04-05' AS Date), 17000, N'gói', N'LH04  ', 100, 1)
INSERT [dbo].[HANGHOA] ([MaHH], [TenHH], [MaKM], [NgSanXuat], [HanSuDung], [DonGiaBan], [DonVi], [MaLH], [SoLuong], [TinhTrang]) VALUES (N'HH016 ', N'KitKat', N'KM02  ', CAST(N'2023-10-05' AS Date), CAST(N'2024-04-05' AS Date), 35000, N'gói', N'LH04  ', 50, 1)
INSERT [dbo].[HANGHOA] ([MaHH], [TenHH], [MaKM], [NgSanXuat], [HanSuDung], [DonGiaBan], [DonVi], [MaLH], [SoLuong], [TinhTrang]) VALUES (N'HH017 ', N'Cam Sành', NULL, CAST(N'2023-10-05' AS Date), CAST(N'2023-10-12' AS Date), 25000, N'Kg', N'LH05  ', 20, 1)
INSERT [dbo].[HANGHOA] ([MaHH], [TenHH], [MaKM], [NgSanXuat], [HanSuDung], [DonGiaBan], [DonVi], [MaLH], [SoLuong], [TinhTrang]) VALUES (N'HH018 ', N'Dưa Hấu', N'KM01  ', CAST(N'2023-10-05' AS Date), CAST(N'2023-10-12' AS Date), 10000, N'Kg', N'LH05  ', 20, 1)
INSERT [dbo].[HANGHOA] ([MaHH], [TenHH], [MaKM], [NgSanXuat], [HanSuDung], [DonGiaBan], [DonVi], [MaLH], [SoLuong], [TinhTrang]) VALUES (N'HH019 ', N'Táo', NULL, CAST(N'2023-10-05' AS Date), CAST(N'2023-10-12' AS Date), 40000, N'Kg', N'LH05  ', 20, 1)
INSERT [dbo].[HANGHOA] ([MaHH], [TenHH], [MaKM], [NgSanXuat], [HanSuDung], [DonGiaBan], [DonVi], [MaLH], [SoLuong], [TinhTrang]) VALUES (N'HH020 ', N'chuối', N'KM02  ', CAST(N'2023-10-05' AS Date), CAST(N'2023-10-20' AS Date), 20000, N'Kg', N'LH05  ', 20, 1)
GO
INSERT [dbo].[HOADONBANHANG] ([MaHD], [NgLapHD], [ThanhTien], [MaKH], [MaNV]) VALUES (N'HD01  ', CAST(N'2023-10-06T00:00:00' AS SmallDateTime), 100000, N'KH001 ', N'NV02  ')
INSERT [dbo].[HOADONBANHANG] ([MaHD], [NgLapHD], [ThanhTien], [MaKH], [MaNV]) VALUES (N'HD02  ', CAST(N'2023-10-06T00:00:00' AS SmallDateTime), 100000, N'KH002 ', N'NV02  ')
INSERT [dbo].[HOADONBANHANG] ([MaHD], [NgLapHD], [ThanhTien], [MaKH], [MaNV]) VALUES (N'HD03  ', CAST(N'2023-10-06T00:00:00' AS SmallDateTime), 100000, N'KH003 ', N'NV02  ')
GO
INSERT [dbo].[KHACHHANG] ([MaKH], [HoTen], [Diem], [SDT], [NgSinh], [TinhTrang]) VALUES (N'KH001 ', N'Bùi Thành Tài', 100, N'0393515608', CAST(N'2003-05-06' AS Date), 1)
INSERT [dbo].[KHACHHANG] ([MaKH], [HoTen], [Diem], [SDT], [NgSinh], [TinhTrang]) VALUES (N'KH002 ', N'Võ Lê Kim Tiễn', 200, N'0123512452', CAST(N'2003-02-05' AS Date), 1)
INSERT [dbo].[KHACHHANG] ([MaKH], [HoTen], [Diem], [SDT], [NgSinh], [TinhTrang]) VALUES (N'KH003 ', N'Hồ Đông Huy', 300, N'0123345435', CAST(N'2020-12-31' AS Date), 1)
INSERT [dbo].[KHACHHANG] ([MaKH], [HoTen], [Diem], [SDT], [NgSinh], [TinhTrang]) VALUES (N'KH004 ', N'Nguyễn Hoàng Tiến', 100, N'0234657961', CAST(N'2003-09-07' AS Date), 1)
INSERT [dbo].[KHACHHANG] ([MaKH], [HoTen], [Diem], [SDT], [NgSinh], [TinhTrang]) VALUES (N'KH005 ', N'Nguyễn Vũ Tiến Đạt', 250, N'0123873544', CAST(N'2003-07-03' AS Date), 1)
INSERT [dbo].[KHACHHANG] ([MaKH], [HoTen], [Diem], [SDT], [NgSinh], [TinhTrang]) VALUES (N'KH006 ', N'Trần Nhật Duy', 400, N'0345876122', CAST(N'2003-02-21' AS Date), 1)
GO
INSERT [dbo].[KHUYENMAI] ([MaKM], [NgBatDauKM], [NgKetThucKM], [TiLeGiam], [TinhTrang], [MoTa]) VALUES (N'KM01  ', CAST(N'2023-10-01' AS Date), CAST(N'2023-10-10' AS Date), 0.05, 1, N'Khuyến mãi cho sự kiện a')
INSERT [dbo].[KHUYENMAI] ([MaKM], [NgBatDauKM], [NgKetThucKM], [TiLeGiam], [TinhTrang], [MoTa]) VALUES (N'KM02  ', CAST(N'2023-10-15' AS Date), CAST(N'2023-10-18' AS Date), 0.1, 1, N'Khuyến mãi cho sự kiện a')
GO
INSERT [dbo].[LOAIHANG] ([MaLH], [TenLH], [TinhTrang]) VALUES (N'LH01  ', N'Nước Ngọt', 1)
INSERT [dbo].[LOAIHANG] ([MaLH], [TenLH], [TinhTrang]) VALUES (N'LH02  ', N'Thịt, cá', 1)
INSERT [dbo].[LOAIHANG] ([MaLH], [TenLH], [TinhTrang]) VALUES (N'LH03  ', N'Mì,Phở,Hủ Tiếu gói', 1)
INSERT [dbo].[LOAIHANG] ([MaLH], [TenLH], [TinhTrang]) VALUES (N'LH04  ', N'Bánh Kẹo', 1)
INSERT [dbo].[LOAIHANG] ([MaLH], [TenLH], [TinhTrang]) VALUES (N'LH05  ', N'Trái Cây', 1)
GO
INSERT [dbo].[NHACUNGCAP] ([MaNCC], [TenNCC], [DiaChi], [Email], [SDT], [TinhTrang]) VALUES (N'NCC01 ', N'Vạn Hưng', N'702/1H Sư Vạn Hạnh, Phường 12, Quận 10, Thành phố Hồ Chí Minh', N'bull@gmail.com                ', N'0817246582', 1)
INSERT [dbo].[NHACUNGCAP] ([MaNCC], [TenNCC], [DiaChi], [Email], [SDT], [TinhTrang]) VALUES (N'NCC02 ', N'Acecook', N'22 Đống Đa,Hà Nội', N'acecook@gmail.com             ', N'0456712312', 1)
INSERT [dbo].[NHACUNGCAP] ([MaNCC], [TenNCC], [DiaChi], [Email], [SDT], [TinhTrang]) VALUES (N'NCC03 ', N'Chợ lớn', N'phường 11, quận 5, TPHCM', N'cholon@gmail.com              ', N'0657234234', 1)
INSERT [dbo].[NHACUNGCAP] ([MaNCC], [TenNCC], [DiaChi], [Email], [SDT], [TinhTrang]) VALUES (N'NCC04 ', N'Hải Hà', N'NULLSố 25 Trương Định, phường Trương Định, quận Hai Bà Trưng, Hà Nội', N'haiha@gmail.com               ', N'0128723453', 1)
GO
INSERT [dbo].[NHANVIEN] ([MaNV], [TenNV], [NgSinh], [GioiTinh], [SDT], [Email], [MatKhau], [MaQuyen], [TinhTrang]) VALUES (N'NV01  ', N'Nguyễn Thị Nị', CAST(N'2005-09-04' AS Date), N'Nu ', N'0123587345', N'Ni@gmail.com                  ', N'Ni123                         ', N'MQ02  ', 1)
INSERT [dbo].[NHANVIEN] ([MaNV], [TenNV], [NgSinh], [GioiTinh], [SDT], [Email], [MatKhau], [MaQuyen], [TinhTrang]) VALUES (N'NV02  ', N'Admin', CAST(N'1999-09-05' AS Date), N'Nam', N'0123458724', N'admin@gmail.com               ', N'admin123                      ', N'MQ04  ', 1)
INSERT [dbo].[NHANVIEN] ([MaNV], [TenNV], [NgSinh], [GioiTinh], [SDT], [Email], [MatKhau], [MaQuyen], [TinhTrang]) VALUES (N'NV03  ', N'Nguyễn Văn Tèo', CAST(N'2003-05-02' AS Date), N'Nu ', N'0123876842', N'Tien@gmail.com                ', N'tien123                       ', N'MQ03  ', 1)
INSERT [dbo].[NHANVIEN] ([MaNV], [TenNV], [NgSinh], [GioiTinh], [SDT], [Email], [MatKhau], [MaQuyen], [TinhTrang]) VALUES (N'NV04  ', N'Hồ Khứa Huy', CAST(N'2003-05-09' AS Date), N'Nu ', N'0878623423', N'huy@gmail.com                 ', N'huy456                        ', N'MQ01  ', 1)
GO
INSERT [dbo].[NHOMQUYEN] ([MaQuyen], [TenQuyen], [MoTa]) VALUES (N'MQ01  ', N'Quản Lý', N'Quản lý toàn bộ chức năng')
INSERT [dbo].[NHOMQUYEN] ([MaQuyen], [TenQuyen], [MoTa]) VALUES (N'MQ02  ', N'Nhân Viên Bán Hàng', N'Quản lý chức năng bán hàng')
INSERT [dbo].[NHOMQUYEN] ([MaQuyen], [TenQuyen], [MoTa]) VALUES (N'MQ03  ', N'Nhân Viên Kho', N'Quản lý chức năng tồn kho và nhập hàng')
INSERT [dbo].[NHOMQUYEN] ([MaQuyen], [TenQuyen], [MoTa]) VALUES (N'MQ04  ', N'admin', N'Quản lý tài khoản và phân quyền người dùng')
GO
INSERT [dbo].[PHIEUNHAP] ([MaPhieuNhap], [NgLapPhieu], [TinhTrang], [MaNV]) VALUES (N'PN01  ', CAST(N'2023-10-02T00:00:00' AS SmallDateTime), 1, N'NV03  ')
INSERT [dbo].[PHIEUNHAP] ([MaPhieuNhap], [NgLapPhieu], [TinhTrang], [MaNV]) VALUES (N'PN02  ', CAST(N'2023-10-03T00:00:00' AS SmallDateTime), 1, N'NV03  ')
INSERT [dbo].[PHIEUNHAP] ([MaPhieuNhap], [NgLapPhieu], [TinhTrang], [MaNV]) VALUES (N'PN03  ', CAST(N'2023-10-05T00:00:00' AS SmallDateTime), 1, N'NV03  ')
INSERT [dbo].[PHIEUNHAP] ([MaPhieuNhap], [NgLapPhieu], [TinhTrang], [MaNV]) VALUES (N'PN04  ', CAST(N'2023-10-06T00:00:00' AS SmallDateTime), 1, N'NV03  ')
GO
ALTER TABLE [dbo].[CT_PHIEUNHAP]  WITH CHECK ADD  CONSTRAINT [FK_CTPN_HH] FOREIGN KEY([MaHH])
REFERENCES [dbo].[HANGHOA] ([MaHH])
GO
ALTER TABLE [dbo].[CT_PHIEUNHAP] CHECK CONSTRAINT [FK_CTPN_HH]
GO
ALTER TABLE [dbo].[CT_PHIEUNHAP]  WITH CHECK ADD  CONSTRAINT [FK_CTPN_NCC] FOREIGN KEY([MaNCC])
REFERENCES [dbo].[NHACUNGCAP] ([MaNCC])
GO
ALTER TABLE [dbo].[CT_PHIEUNHAP] CHECK CONSTRAINT [FK_CTPN_NCC]
GO
ALTER TABLE [dbo].[CT_PHIEUNHAP]  WITH CHECK ADD  CONSTRAINT [FK_CTPN_PN] FOREIGN KEY([MaPhieuNhap])
REFERENCES [dbo].[PHIEUNHAP] ([MaPhieuNhap])
GO
ALTER TABLE [dbo].[CT_PHIEUNHAP] CHECK CONSTRAINT [FK_CTPN_PN]
GO
ALTER TABLE [dbo].[CTHD_BANHANG]  WITH CHECK ADD  CONSTRAINT [FK_CTHDBH_HD] FOREIGN KEY([MaHD])
REFERENCES [dbo].[HOADONBANHANG] ([MaHD])
GO
ALTER TABLE [dbo].[CTHD_BANHANG] CHECK CONSTRAINT [FK_CTHDBH_HD]
GO
ALTER TABLE [dbo].[CTHD_BANHANG]  WITH CHECK ADD  CONSTRAINT [FK_CTHDBH_HH] FOREIGN KEY([MaHH])
REFERENCES [dbo].[HANGHOA] ([MaHH])
GO
ALTER TABLE [dbo].[CTHD_BANHANG] CHECK CONSTRAINT [FK_CTHDBH_HH]
GO
ALTER TABLE [dbo].[HANGHOA]  WITH CHECK ADD  CONSTRAINT [FK_HH_KM] FOREIGN KEY([MaKM])
REFERENCES [dbo].[KHUYENMAI] ([MaKM])
GO
ALTER TABLE [dbo].[HANGHOA] CHECK CONSTRAINT [FK_HH_KM]
GO
ALTER TABLE [dbo].[HANGHOA]  WITH CHECK ADD  CONSTRAINT [FK_HH_LH] FOREIGN KEY([MaLH])
REFERENCES [dbo].[LOAIHANG] ([MaLH])
GO
ALTER TABLE [dbo].[HANGHOA] CHECK CONSTRAINT [FK_HH_LH]
GO
ALTER TABLE [dbo].[HOADONBANHANG]  WITH CHECK ADD  CONSTRAINT [FK_HDBH_KH] FOREIGN KEY([MaKH])
REFERENCES [dbo].[KHACHHANG] ([MaKH])
GO
ALTER TABLE [dbo].[HOADONBANHANG] CHECK CONSTRAINT [FK_HDBH_KH]
GO
ALTER TABLE [dbo].[HOADONBANHANG]  WITH CHECK ADD  CONSTRAINT [FK_HDBH_NV] FOREIGN KEY([MaNV])
REFERENCES [dbo].[NHANVIEN] ([MaNV])
GO
ALTER TABLE [dbo].[HOADONBANHANG] CHECK CONSTRAINT [FK_HDBH_NV]
GO
ALTER TABLE [dbo].[NHANVIEN]  WITH CHECK ADD  CONSTRAINT [FK_NV_NQ] FOREIGN KEY([MaQuyen])
REFERENCES [dbo].[NHOMQUYEN] ([MaQuyen])
GO
ALTER TABLE [dbo].[NHANVIEN] CHECK CONSTRAINT [FK_NV_NQ]
GO
ALTER TABLE [dbo].[PHIEUNHAP]  WITH CHECK ADD  CONSTRAINT [FK_PN_NV] FOREIGN KEY([MaNV])
REFERENCES [dbo].[NHANVIEN] ([MaNV])
GO
ALTER TABLE [dbo].[PHIEUNHAP] CHECK CONSTRAINT [FK_PN_NV]
GO
