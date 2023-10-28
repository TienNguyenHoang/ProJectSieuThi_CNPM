package com.myproject.GUI.QuanLy.QuanLyPanels;

import com.myproject.BUS.CTHD_BanHangBUS;
import com.myproject.BUS.CT_HangHoaBUS;
import com.myproject.BUS.CT_PhieuNhapBUS;
import com.myproject.BUS.HangHoaBUS;
import com.myproject.BUS.HoaDonBanHangBUS;
import com.myproject.BUS.KhachHangBUS;
import com.myproject.BUS.LoaiHangBUS;
import com.myproject.BUS.PhieuNhapBUS;
import com.myproject.DTO.CTHD_BanHangDTO;
import com.myproject.DTO.CT_HangHoaDTO;
import com.myproject.DTO.CT_PhieuNhapDTO;
import com.myproject.DTO.HangHoaDTO;
import com.myproject.DTO.HangHoaTongDTO;
import com.myproject.DTO.HoaDonBanHangDTO;
import com.myproject.DTO.KhachHangDTO;
import com.myproject.DTO.LoaiHangDTO;
import com.myproject.DTO.PhieuNhapDTO;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.security.Timestamp;
import java.util.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import javax.swing.table.TableModel;
// Import các thư viện cần thiết

public class ThongKePanel extends javax.swing.JPanel {

    PhieuNhapBUS PNBUS = new PhieuNhapBUS();
    List<PhieuNhapDTO> listpn = PNBUS.getList();
    HoaDonBanHangBUS HDBHBUS = new HoaDonBanHangBUS();
    List<HoaDonBanHangDTO> listhdbh = HDBHBUS.getList();
    KhachHangBUS KHBUS = new KhachHangBUS();
    List<KhachHangDTO> listkh = KHBUS.getList();
    List<HoaDonBanHangDTO> hdbhlist = HDBHBUS.getList();
    HangHoaBUS HHBUS = new HangHoaBUS();
    List<HangHoaTongDTO> hht_list = HHBUS.getAllHangHoa();
    List<HangHoaDTO> hhlist = HHBUS.getList();
    CTHD_BanHangBUS CTBHBUS = new CTHD_BanHangBUS();
    List<CTHD_BanHangDTO> cthdbhlist = CTBHBUS.getList();
    CT_HangHoaBUS CTHHBUS = new CT_HangHoaBUS();
    List<CT_HangHoaDTO> cthhlist = CTHHBUS.getList();
    CT_PhieuNhapBUS CTPNBUS = new CT_PhieuNhapBUS();
    List<CT_PhieuNhapDTO> ctpnlist = CTPNBUS.getList();
    LoaiHangBUS LHBUS = new LoaiHangBUS();
    List<LoaiHangDTO> lhlist = LHBUS.getList();

    public ThongKePanel() {
        initComponents();
        loadTKBanner();
        chooserdateyear();
        chooseryear();
        choosermonth();
        datechoose_start();
        datechoose_end();
        Search();
        ComboboxSortThanhTien();
        ComboboxLH();
    }

    public void loadTKBanner() {
        jcbboxDTLH.addItem("Tất cả");
        jcbboxKCLH.addItem("Tất cả");
        for (LoaiHangDTO lh : lhlist) {
            jcbboxDTLH.addItem(lh.getTenLH());
            jcbboxKCLH.addItem(lh.getTenLH());
        }
        int soLuongHoaDon = listhdbh.size();
        jlbDH.setText(soLuongHoaDon + "  Đơn Hàng");
        int soLuongKhachHang = 0;
        Set<String> uniqueCustomerIds = new HashSet<>();
        for (HoaDonBanHangDTO hdbh : listhdbh) {
            String maKH = hdbh.getMaKH();
            if (!maKH.equals("KH000")) {
                uniqueCustomerIds.add(maKH);
            }
        }
        soLuongKhachHang = uniqueCustomerIds.size();
        jlbCustomer.setText(soLuongKhachHang + "  Khách Hàng");
        float TongDoanhThu = 0;
        float TongKhoangChi = 0;
        for (CTHD_BanHangDTO cthd : cthdbhlist) {
            TongDoanhThu = TongDoanhThu + cthd.getSLBan() * cthd.getDonGia();
        }
        jlbDT.setText(TongDoanhThu + " VNÐ");
        for (PhieuNhapDTO pn : listpn) {
            if (pn.isTinhTrang() == true) {
                TongKhoangChi = TongKhoangChi + pn.getThanhTien();
            }
        }
        jlbKC.setText(TongKhoangChi + " VNÐ");
        //for hien san pham ban ra
        DefaultTableModel tableSpBr = (DefaultTableModel) jtbDoanhThu.getModel();
        tableSpBr.setRowCount(0);
        for (HangHoaDTO hh : hhlist) {
            float sl_hhbr = 0;
            float thanhtien = 0;
            for (CT_HangHoaDTO cthh : cthhlist) {
                if (hh.getMaHH().trim().equals(cthh.getMaHH().trim())) {
                    for (CTHD_BanHangDTO cthd : cthdbhlist) {
                        if (cthh.getMaCT_HH().trim().equals(cthd.getMaCT_HH().trim())) {
                            sl_hhbr += cthd.getSLBan();
                            thanhtien += cthd.getDonGia() * cthd.getSLBan();
                        }
                    }
                }
            }
            if (sl_hhbr == 0 && thanhtien == 0) {
                continue; // Bỏ qua hàng hóa có sl_hh và thanhtien bằng 0
            }
            Object[] rowSpBr = {hh.getMaHH(), hh.getTenHH(), hh.getMaLH(), sl_hhbr, thanhtien};
            tableSpBr.addRow(rowSpBr);
        }
        //for hien san pham nhap vao
        DefaultTableModel tableSpN = (DefaultTableModel) jtbKhoangChi.getModel();
        tableSpN.setRowCount(0);
        for (HangHoaDTO hh : hhlist) {
            float sl_hhn = 0;
            float thanhtien = 0;
            for (CT_HangHoaDTO cthh : cthhlist) {
                if (hh.getMaHH().trim().equals(cthh.getMaHH().trim())) {
                    for (CT_PhieuNhapDTO ctpn : ctpnlist) {
                        for (PhieuNhapDTO hdn : listpn) {
                            if (cthh.getMaCT_HH().trim().equals(ctpn.getMaCT_HH().trim()) && hdn.isTinhTrang() == true) {
                                sl_hhn += ctpn.getSLNhap();
                                thanhtien += ctpn.getDonGiaNhap() * ctpn.getSLNhap();
                            }
                        }
                    }
                }
            }
            if (sl_hhn == 0 && thanhtien == 0) {
                continue; // Bỏ qua hàng hóa có sl_hh và thanhtien bằng 0
            }
            Object[] rowSpN = {hh.getMaHH(), hh.getTenHH(), hh.getMaLH(), sl_hhn, thanhtien};
            tableSpN.addRow(rowSpN);
        }
    }

    //************************************************ham tim kiem DT va KC*****************************************
    private TableRowSorter<DefaultTableModel> rowSorterDT; // rowSorter cho jtbDoanhThu
    private TableRowSorter<DefaultTableModel> rowSorterKC;

    public void Search() {
        jtfDTSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateFilter();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateFilter();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateFilter();
            }

            private void updateFilter() {
                String text = jtfDTSearch.getText().toLowerCase(); // Lấy nội dung tìm kiếm và chuyển thành chữ thường
                if (rowSorterDT == null) {
                    DefaultTableModel tableModel = (DefaultTableModel) jtbDoanhThu.getModel();
                    rowSorterDT = new TableRowSorter<>(tableModel);
                    jtbDoanhThu.setRowSorter(rowSorterDT);
                }
                List<RowFilter<Object, Object>> filters = new ArrayList<>();
                filters.add(RowFilter.regexFilter("(?i)" + Pattern.quote(text), 0)); // Tìm kiếm trên cột 0
                filters.add(RowFilter.regexFilter("(?i)" + Pattern.quote(text), 1)); // Tìm kiếm trên cột 1
                RowFilter<Object, Object> combinedFilter = RowFilter.orFilter(filters);
                rowSorterDT.setRowFilter(combinedFilter);
            }
        });
        jtfKCSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateFilter();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateFilter();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateFilter();
            }

            private void updateFilter() {
                String text = jtfKCSearch.getText().toLowerCase(); // Lấy nội dung tìm kiếm và chuyển thành chữ thường
                if (rowSorterKC == null) {
                    DefaultTableModel tableModel = (DefaultTableModel) jtbKhoangChi.getModel();
                    rowSorterKC = new TableRowSorter<>(tableModel);
                    jtbKhoangChi.setRowSorter(rowSorterKC);
                }
                List<RowFilter<Object, Object>> filters = new ArrayList<>();
                filters.add(RowFilter.regexFilter("(?i)" + Pattern.quote(text), 0)); // Tìm kiếm trên cột 0
                filters.add(RowFilter.regexFilter("(?i)" + Pattern.quote(text), 1)); // Tìm kiếm trên cột 1
                RowFilter<Object, Object> combinedFilter = RowFilter.orFilter(filters);
                rowSorterKC.setRowFilter(combinedFilter);
            }
        });
    }

    //************************************************Hàm filter loai hang va don gia******************************
    private String selectedLHDT = "";
    private String selectedThanhTienDT = "";
    private String selectedLHKC = "";
    private String selectedThanhTienKC = "";

    public void ComboboxLH() {
        jcbboxDTLH.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedLHDT = (String) jcbboxDTLH.getSelectedItem();
                filterAndSortDataDT();
            }
        });
        jcbboxKCLH.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedLHKC = (String) jcbboxKCLH.getSelectedItem();
                filterAndSortDataKC();
            }
        });
    }

    public void ComboboxSortThanhTien() {
        jcbboxDTArrange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedThanhTienDT = (String) jcbboxDTArrange.getSelectedItem();
                filterAndSortDataDT();
            }
        });
        jcbboxKCArrange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedThanhTienKC = (String) jcbboxKCArrange.getSelectedItem();
                filterAndSortDataKC();
            }
        });
    }

    private void filterAndSortDataKC() {
        DefaultTableModel model = (DefaultTableModel) jtbKhoangChi.getModel();
        if (rowSorterKC == null) {
            rowSorterKC = new TableRowSorter<>(model);
            jtbKhoangChi.setRowSorter(rowSorterKC);
        }
        List<RowSorter.SortKey> sortKeys = new ArrayList<>();

        // Sắp xếp dữ liệu theo thành tiền (nếu đã chọn kiểu sắp xếp)
        if (!"Chưa sắp xếp".equals(selectedThanhTienKC)) {
            int columnIndex = 4; // 4 là chỉ số cột thành tiền (thay bằng chỉ số thực tế)
            if ("Giá tăng dần".equals(selectedThanhTienKC.trim())) {
                sortKeys.add(new RowSorter.SortKey(columnIndex, SortOrder.ASCENDING));
            } else if ("Giá giảm dần".equals(selectedThanhTienKC.trim())) {
                sortKeys.add(new RowSorter.SortKey(columnIndex, SortOrder.DESCENDING));
            }
        }
        RowFilter<DefaultTableModel, Object> textFilter = RowFilter.regexFilter("(?i)" + Pattern.quote(jtfKCSearch.getText()), 1); // 1 là chỉ số cột cần tìm kiếm (thay bằng chỉ số thực tế)
        String selectedMaLH = "";
        if (!"Tất cả".equals(selectedLHKC.trim())) {
            for (LoaiHangDTO lh : lhlist) {
                if (selectedLHKC.trim().equals(lh.getTenLH().trim())) {
                    selectedMaLH = lh.getMaLH();
                    break;
                }
            }
        }
        // Áp dụng bộ lọc theo loại hàng
        RowFilter<DefaultTableModel, Object> lhFilter = RowFilter.regexFilter(selectedMaLH.trim(), 2); // 2 là chỉ số cột loại hàng (thay bằng chỉ số thực tế)
        // Kết hợp cả ba bộ lọc
        List<RowFilter<DefaultTableModel, Object>> filters = new ArrayList<>();
        filters.add(textFilter);
        filters.add(lhFilter);
        RowFilter<DefaultTableModel, Object> combinedFilter = RowFilter.andFilter(filters);
        rowSorterKC.setRowFilter(combinedFilter);
        rowSorterKC.setSortKeys(sortKeys);
        rowSorterKC.sort();
    }

    private void filterAndSortDataDT() {
        DefaultTableModel model = (DefaultTableModel) jtbDoanhThu.getModel();
        if (rowSorterDT == null) {
            rowSorterDT = new TableRowSorter<>(model);
            jtbDoanhThu.setRowSorter(rowSorterDT);
        }
        List<RowSorter.SortKey> sortKeys = new ArrayList<>();

        // Sắp xếp dữ liệu theo thành tiền (nếu đã chọn kiểu sắp xếp)
        if (!"Chưa sắp xếp".equals(selectedThanhTienDT)) {
            int columnIndex = 4; // 4 là chỉ số cột thành tiền (thay bằng chỉ số thực tế)
            if ("Giá tăng dần".equals(selectedThanhTienDT.trim())) {
                sortKeys.add(new RowSorter.SortKey(columnIndex, SortOrder.ASCENDING));
            } else if ("Giá giảm dần".equals(selectedThanhTienDT.trim())) {
                sortKeys.add(new RowSorter.SortKey(columnIndex, SortOrder.DESCENDING));
            }
        }
        RowFilter<DefaultTableModel, Object> textFilter = RowFilter.regexFilter("(?i)" + Pattern.quote(jtfDTSearch.getText()), 1); // 1 là chỉ số cột cần tìm kiếm (thay bằng chỉ số thực tế)
        String selectedMaLH = "";
        if (!"Tất cả".equals(selectedLHDT.trim())) {
            for (LoaiHangDTO lh : lhlist) {
                if (selectedLHDT.trim().equals(lh.getTenLH().trim())) {
                    selectedMaLH = lh.getMaLH();
                    break;
                }
            }
        }
        // Áp dụng bộ lọc theo loại hàng
        RowFilter<DefaultTableModel, Object> lhFilter = RowFilter.regexFilter(selectedMaLH.trim(), 2); // 2 là chỉ số cột loại hàng (thay bằng chỉ số thực tế)
        // Kết hợp cả ba bộ lọc
        List<RowFilter<DefaultTableModel, Object>> filters = new ArrayList<>();
        filters.add(textFilter);
        filters.add(lhFilter);
        RowFilter<DefaultTableModel, Object> combinedFilter = RowFilter.andFilter(filters);
        rowSorterDT.setRowFilter(combinedFilter);
        rowSorterDT.setSortKeys(sortKeys);
        rowSorterDT.sort();
    }
    //*************************************************hàm loc theo nam(nam)*************************************

    public void chooserdateyear() {
        jYearChooser.addPropertyChangeListener("year", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("year".equals(evt.getPropertyName())) {
                    int selectedYear = (int) evt.getNewValue();
                    int soLuongHoaDon = 0;
                    int soLuongKhachHang = 0;
                    float TongDoanhThu = 0;
                    Set<String> uniqueCustomerIds = new HashSet<>();
                    for (HoaDonBanHangDTO hdbh : listhdbh) {
                        java.sql.Timestamp ngayLap = hdbh.getNgLap();
                        LocalDateTime localDateTime = ngayLap.toLocalDateTime();
                        int year = localDateTime.getYear();
                        if (year == selectedYear) {
                            soLuongHoaDon++;
                            TongDoanhThu += hdbh.getThanhTien();
                        }
                        String maKH = hdbh.getMaKH();
                        if (!maKH.trim().equals("KH000") && year == selectedYear) {
                            uniqueCustomerIds.add(maKH);
                        }
                    }
                    jlbDH.setText(soLuongHoaDon + "  Đơn Hàng");
                    soLuongKhachHang = uniqueCustomerIds.size();
                    jlbCustomer.setText(soLuongKhachHang + "  Khách Hàng");
                    jlbDT.setText(TongDoanhThu + " VNÐ");
                    float TongKhoangChi = 0;
                    for (PhieuNhapDTO pn : listpn) {
                        java.sql.Timestamp ngayLapPN = pn.getNgLapPhieu();
                        LocalDateTime localDateTime = ngayLapPN.toLocalDateTime();
                        int year = localDateTime.getYear();
                        if (pn.isTinhTrang() == true && year == selectedYear) {
                            TongKhoangChi += pn.getThanhTien();
                        }
                    }
                    jlbKC.setText(TongKhoangChi + " VNÐ");
                    //for hien san pham ban ra
                    DefaultTableModel tableSpBr = (DefaultTableModel) jtbDoanhThu.getModel();
                    tableSpBr.setRowCount(0);
                    for (HangHoaDTO hh : hhlist) {
                        float sl_hhbr = 0;
                        float thanhtien = 0;
                        for (CT_HangHoaDTO cthh : cthhlist) {
                            if (hh.getMaHH().trim().equals(cthh.getMaHH().trim())) {
                                for (CTHD_BanHangDTO cthd : cthdbhlist) {
                                    if (cthh.getMaCT_HH().trim().equals(cthd.getMaCT_HH().trim())) {
                                        for (HoaDonBanHangDTO hdbh : listhdbh) {
                                            java.sql.Timestamp ngayLap = hdbh.getNgLap();
                                            LocalDateTime localDateTime = ngayLap.toLocalDateTime();
                                            int year = localDateTime.getYear();
                                            if (hdbh.getMaHD().trim().equals(cthd.getMaHD().trim()) && year == selectedYear) {
                                                sl_hhbr += cthd.getSLBan();
                                                thanhtien += cthd.getDonGia() * cthd.getSLBan();
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (sl_hhbr == 0 && thanhtien == 0) {
                            continue; // Bỏ qua hàng hóa có sl_hh và thanhtien bằng 0
                        }
                        Object[] rowSpBr = {hh.getMaHH(), hh.getTenHH(), hh.getMaLH(), sl_hhbr, thanhtien};
                        tableSpBr.addRow(rowSpBr);
                    }
                    //for hien san pham nhap vao
                    DefaultTableModel tableSpN = (DefaultTableModel) jtbKhoangChi.getModel();
                    tableSpN.setRowCount(0);
                    for (HangHoaDTO hh : hhlist) {
                        float sl_hhn = 0;
                        float thanhtien = 0;
                        for (CT_HangHoaDTO cthh : cthhlist) {
                            if (hh.getMaHH().trim().equals(cthh.getMaHH().trim())) {
                                for (CT_PhieuNhapDTO ctpn : ctpnlist) {
                                    if (cthh.getMaCT_HH().trim().equals(ctpn.getMaCT_HH().trim())) {
                                        for (PhieuNhapDTO hdn : listpn) {
                                            java.sql.Timestamp ngayLapPN = hdn.getNgLapPhieu();
                                            LocalDateTime localDateTime = ngayLapPN.toLocalDateTime();
                                            int year = localDateTime.getYear();
                                            if (hdn.getMaPN().trim().equals(ctpn.getMaPN().trim()) && year == selectedYear && hdn.isTinhTrang() == true) {
                                                sl_hhn += ctpn.getSLNhap();
                                                thanhtien += ctpn.getDonGiaNhap() * ctpn.getSLNhap();
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (sl_hhn == 0 && thanhtien == 0) {
                            continue; // Bỏ qua hàng hóa có sl_hh và thanhtien bằng 0
                        }
                        Object[] rowSpN = {hh.getMaHH(), hh.getTenHH(), hh.getMaLH(), sl_hhn, thanhtien};
                        tableSpN.addRow(rowSpN);
                    }
                }
            }
        });
    }
    //**************************************************ham loc theo thang va nam***************************************
    int selectedYear1 = -1;
    int selectedMonth = -1;

    public void chooseryear() {
        jYearChooser1.addPropertyChangeListener("year", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("year".equals(evt.getPropertyName())) {
                    selectedYear1 = (int) evt.getNewValue();
                    chooserMonthAndYear();
                }
            }
        });
    }

    public void choosermonth() {
        jMonthChooser.addPropertyChangeListener("month", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("month".equals(evt.getPropertyName())) {
                    selectedMonth = (int) evt.getNewValue();
                    chooserMonthAndYear();
                }
            }
        });
    }

    private void chooserMonthAndYear() {
        if (selectedYear1 == -1) {
            selectedYear1 = Calendar.getInstance().get(Calendar.YEAR);
        } else if (selectedMonth == -1) {
            selectedMonth = Calendar.getInstance().get(Calendar.MONTH);
        }
        int soLuongKhachHang = 0;
        Set<String> uniqueCustomerIds = new HashSet<>();
        int soLuongHoaDon = 0;
        float TongDoanhThu = 0;
        for (HoaDonBanHangDTO hdbh : listhdbh) {
            java.sql.Timestamp ngayLap = hdbh.getNgLap();
            LocalDateTime localDateTime = ngayLap.toLocalDateTime();
            int year = localDateTime.getYear();
            Month month = localDateTime.getMonth();
            String maKH = hdbh.getMaKH();
            if (!maKH.trim().equals("KH000") && year == selectedYear1 && month.getValue() == selectedMonth + 1) {
                uniqueCustomerIds.add(maKH);
            }
            if (year == selectedYear1 && month.getValue() == selectedMonth + 1) {
                soLuongHoaDon++;
                TongDoanhThu += hdbh.getThanhTien();
            }
        }
        jlbDH.setText(soLuongHoaDon + "  Đơn Hàng");
        soLuongKhachHang = uniqueCustomerIds.size();
        jlbCustomer.setText(soLuongKhachHang + "  Khách Hàng");
        jlbDT.setText(TongDoanhThu + " VNÐ");

        float TongKhoangChi = 0;
        for (PhieuNhapDTO pn : listpn) {
            java.sql.Timestamp ngayLapPN = pn.getNgLapPhieu();
            LocalDateTime localDateTime = ngayLapPN.toLocalDateTime();
            int year = localDateTime.getYear();
            Month month = localDateTime.getMonth();
            if (pn.isTinhTrang() == true && year == selectedYear1 && month.getValue() == selectedMonth + 1) {
                TongKhoangChi += pn.getThanhTien();
            }
        }
        jlbKC.setText(TongKhoangChi + " VNÐ");
        //for hien san pham ban ra
        DefaultTableModel tableSpBr = (DefaultTableModel) jtbDoanhThu.getModel();
        tableSpBr.setRowCount(0);
        for (HangHoaDTO hh : hhlist) {
            float sl_hhbr = 0;
            float thanhtien = 0;
            for (CT_HangHoaDTO cthh : cthhlist) {
                if (hh.getMaHH().trim().equals(cthh.getMaHH().trim())) {
                    for (CTHD_BanHangDTO cthd : cthdbhlist) {
                        if (cthh.getMaCT_HH().trim().equals(cthd.getMaCT_HH().trim())) {
                            for (HoaDonBanHangDTO hdbh : listhdbh) {
                                java.sql.Timestamp ngayLap = hdbh.getNgLap();
                                LocalDateTime localDateTime = ngayLap.toLocalDateTime();
                                Month month = localDateTime.getMonth();
                                int year = localDateTime.getYear();
                                if (hdbh.getMaHD().trim().equals(cthd.getMaHD().trim()) && year == selectedYear1 && month.getValue() == selectedMonth + 1) {
                                    sl_hhbr += cthd.getSLBan();
                                    thanhtien += cthd.getDonGia() * cthd.getSLBan();
                                }
                            }
                        }
                    }
                }
            }
            if (sl_hhbr == 0 && thanhtien == 0) {
                continue; // Bỏ qua hàng hóa có sl_hh và thanhtien bằng 0
            }
            Object[] rowSpBr = {hh.getMaHH(), hh.getTenHH(), hh.getMaLH(), sl_hhbr, thanhtien};
            tableSpBr.addRow(rowSpBr);
        }
        //for hien san pham nhap vao
        DefaultTableModel tableSpN = (DefaultTableModel) jtbKhoangChi.getModel();
        tableSpN.setRowCount(0);
        for (HangHoaDTO hh : hhlist) {
            float sl_hhn = 0;
            float thanhtien = 0;
            for (CT_HangHoaDTO cthh : cthhlist) {
                if (hh.getMaHH().trim().equals(cthh.getMaHH().trim())) {
                    for (CT_PhieuNhapDTO ctpn : ctpnlist) {
                        if (cthh.getMaCT_HH().trim().equals(ctpn.getMaCT_HH().trim())) {
                            for (PhieuNhapDTO hdn : listpn) {
                                java.sql.Timestamp ngayLapPN = hdn.getNgLapPhieu();
                                LocalDateTime localDateTime = ngayLapPN.toLocalDateTime();
                                int year = localDateTime.getYear();
                                Month month = localDateTime.getMonth();
                                if (hdn.getMaPN().trim().equals(ctpn.getMaPN().trim()) && year == selectedYear1 && (month.getValue() == selectedMonth + 1) && hdn.isTinhTrang() == true) {
                                    sl_hhn += ctpn.getSLNhap();
                                    thanhtien += ctpn.getDonGiaNhap() * ctpn.getSLNhap();
                                }
                            }
                        }
                    }
                }
            }
            if (sl_hhn == 0 && thanhtien == 0) {
                continue; // Bỏ qua hàng hóa có sl_hh và thanhtien bằng 0
            }
            Object[] rowSpN = {hh.getMaHH(), hh.getTenHH(), hh.getMaLH(), sl_hhn, thanhtien};
            tableSpN.addRow(rowSpN);
        }

    }

    //*************************************************hàm loc ngay bat dau va ngay ket thuc********************************
    //ham su kien ngya bat dau
    public void datechoose_start() {
        jdcStart.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("date".equals(evt.getPropertyName())) {
                    // Lấy giá trị ngày bắt đầu sau khi người dùng thay đổi
                    Date startDate = jdcStart.getDate();
                    // Gọi hàm xử lý với startDate
                    handleDateChange(startDate, jdcEnd.getDate());
                }
            }
        });
    }

    // Hàm sự kiện ngày kết thúc
    public void datechoose_end() {
        jdcEnd.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("date".equals(evt.getPropertyName())) {
                    // Lấy giá trị ngày kết thúc sau khi người dùng thay đổi
                    Date endDate = jdcEnd.getDate();
                    // Gọi hàm xử lý với endDate
                    handleDateChange(jdcStart.getDate(), endDate);
                }
            }
        });
    }

    // Hàm xử lý với giá trị ngày bắt đầu và ngày kết thúc
    public void handleDateChange(Date startDate, Date endDate) {
        // Kiểm tra xem cả hai giá trị đều tồn tại
        if (startDate != null && endDate != null) {
            // Kiểm tra xem ngày bắt đầu có bé hơn ngày kết thúc
            if (startDate.before(endDate)) {
                int soLuongHoaDon = 0;
                int soLuongKhachHang = 0;
                float TongDoanhThu = 0;
                Set<String> uniqueCustomerIds = new HashSet<>();
                for (HoaDonBanHangDTO hdbh : listhdbh) {
                    java.util.Date ngayLap = hdbh.getNgLap(); // Sử dụng kiểu java.util.Date
                    String maKH = hdbh.getMaKH();
                    if (ngayLap.after(startDate) && ngayLap.before(endDate)) {
                        soLuongHoaDon++;
                        TongDoanhThu += hdbh.getThanhTien();
                    }
                    if (!maKH.trim().equals("KH000") && ngayLap.after(startDate) && ngayLap.before(endDate)) {
                        uniqueCustomerIds.add(maKH);
                    }

                }
                jlbDH.setText(soLuongHoaDon + " Đơn Hàng");
                soLuongKhachHang = uniqueCustomerIds.size();
                jlbCustomer.setText(soLuongKhachHang + "  Khách Hàng");
                jlbDT.setText(TongDoanhThu + " VNĐ");

                float TongKhoangChi = 0;
                for (PhieuNhapDTO pn : listpn) {
                    java.util.Date ngayLapPN = pn.getNgLapPhieu(); // Sử dụng kiểu java.util.Date
                    if (pn.isTinhTrang() == true && ngayLapPN.after(startDate) && ngayLapPN.before(endDate)) {
                        TongKhoangChi += pn.getThanhTien();
                    }
                }
                jlbKC.setText(TongKhoangChi + " VNĐ");
                //for hien san pham ban ra
                DefaultTableModel tableSpBr = (DefaultTableModel) jtbDoanhThu.getModel();
                tableSpBr.setRowCount(0);
                for (HangHoaDTO hh : hhlist) {
                    float sl_hhbr = 0;
                    float thanhtien = 0;
                    for (CT_HangHoaDTO cthh : cthhlist) {
                        if (hh.getMaHH().trim().equals(cthh.getMaHH().trim())) {
                            for (CTHD_BanHangDTO cthd : cthdbhlist) {
                                if (cthh.getMaCT_HH().trim().equals(cthd.getMaCT_HH().trim())) {
                                    for (HoaDonBanHangDTO hdbh : listhdbh) {
                                        java.util.Date ngayLap = hdbh.getNgLap();
                                        if (hdbh.getMaHD().trim().equals(cthd.getMaHD().trim()) && ngayLap.after(startDate) && ngayLap.before(endDate)) {
                                            sl_hhbr += cthd.getSLBan();
                                            thanhtien += cthd.getDonGia() * cthd.getSLBan();
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (sl_hhbr == 0 && thanhtien == 0) {
                        continue; // Bỏ qua hàng hóa có sl_hh và thanhtien bằng 0
                    }
                    Object[] rowSpBr = {hh.getMaHH(), hh.getTenHH(), hh.getMaLH(), sl_hhbr, thanhtien};
                    tableSpBr.addRow(rowSpBr);
                }
                //for hien san pham nhap vao
                DefaultTableModel tableSpN = (DefaultTableModel) jtbKhoangChi.getModel();
                tableSpN.setRowCount(0);
                for (HangHoaDTO hh : hhlist) {
                    float sl_hhn = 0;
                    float thanhtien = 0;
                    for (CT_HangHoaDTO cthh : cthhlist) {
                        if (hh.getMaHH().trim().equals(cthh.getMaHH().trim())) {
                            for (CT_PhieuNhapDTO ctpn : ctpnlist) {
                                if (cthh.getMaCT_HH().trim().equals(ctpn.getMaCT_HH().trim())) {
                                    for (PhieuNhapDTO hdn : listpn) {
                                        java.util.Date ngayLapPN = hdn.getNgLapPhieu();
                                        if (hdn.getMaPN().trim().equals(ctpn.getMaPN().trim()) && ngayLapPN.after(startDate) && ngayLapPN.before(endDate) && hdn.isTinhTrang() == true) {
                                            sl_hhn += ctpn.getSLNhap();
                                            thanhtien += ctpn.getDonGiaNhap() * ctpn.getSLNhap();
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (sl_hhn == 0 && thanhtien == 0) {
                        continue; // Bỏ qua hàng hóa có sl_hh và thanhtien bằng 0
                    }
                    Object[] rowSpN = {hh.getMaHH(), hh.getTenHH(), hh.getMaLH(), sl_hhn, thanhtien};
                    tableSpN.addRow(rowSpN);
                }
            } else {
                // Ngày bắt đầu lớn hơn hoặc bằng ngày kết thúc, xử lý lỗi hoặc hiển thị thông báo
                JOptionPane.showMessageDialog(null, "Ngày bắt đầu lớn hơn ngày kết thúc. Vui lòng chọn lại!");
            }
        } else if (startDate != null) {
            // Chỉ có giá trị ngày bắt đầu, lọc dữ liệu theo ngày bắt đầu
            int soLuongHoaDon = 0;
            int soLuongKhachHang = 0;
            float TongDoanhThu = 0;
            Set<String> uniqueCustomerIds = new HashSet<>();
            for (HoaDonBanHangDTO hdbh : listhdbh) {
                java.util.Date ngayLap = hdbh.getNgLap(); // Sử dụng kiểu java.util.Date
                if (ngayLap.after(startDate)) {
                    soLuongHoaDon++;
                    TongDoanhThu += hdbh.getThanhTien();
                }
                String maKH = hdbh.getMaKH();
                if (!maKH.trim().equals("KH000") && ngayLap.after(startDate)) {
                    uniqueCustomerIds.add(maKH);
                }
            }
            jlbDH.setText(soLuongHoaDon + " Đơn Hàng");
            soLuongKhachHang = uniqueCustomerIds.size();
            jlbCustomer.setText(soLuongKhachHang + "  Khách Hàng");
            jlbDT.setText(TongDoanhThu + " VNĐ");

            float TongKhoangChi = 0;
            for (PhieuNhapDTO pn : listpn) {
                java.util.Date ngayLapPN = pn.getNgLapPhieu(); // Sử dụng kiểu java.util.Date
                if (pn.isTinhTrang() == true && ngayLapPN.after(startDate)) {
                    TongKhoangChi += pn.getThanhTien();
                }
            }
            jlbKC.setText(TongKhoangChi + " VNĐ");
            //for hien san pham ban ra
            DefaultTableModel tableSpBr = (DefaultTableModel) jtbDoanhThu.getModel();
            tableSpBr.setRowCount(0);
            for (HangHoaDTO hh : hhlist) {
                float sl_hhbr = 0;
                float thanhtien = 0;
                for (CT_HangHoaDTO cthh : cthhlist) {
                    if (hh.getMaHH().trim().equals(cthh.getMaHH().trim())) {
                        for (CTHD_BanHangDTO cthd : cthdbhlist) {
                            if (cthh.getMaCT_HH().trim().equals(cthd.getMaCT_HH().trim())) {
                                for (HoaDonBanHangDTO hdbh : listhdbh) {
                                    java.util.Date ngayLap = hdbh.getNgLap();
                                    if (hdbh.getMaHD().trim().equals(cthd.getMaHD().trim()) && ngayLap.after(startDate)) {
                                        sl_hhbr += cthd.getSLBan();
                                        thanhtien += cthd.getDonGia() * cthd.getSLBan();
                                    }
                                }
                            }
                        }
                    }
                }
                if (sl_hhbr == 0 && thanhtien == 0) {
                    continue; // Bỏ qua hàng hóa có sl_hh và thanhtien bằng 0
                }
                Object[] rowSpBr = {hh.getMaHH(), hh.getTenHH(), hh.getMaLH(), sl_hhbr, thanhtien};
                tableSpBr.addRow(rowSpBr);
            }
            //for hien san pham nhap vao
            DefaultTableModel tableSpN = (DefaultTableModel) jtbKhoangChi.getModel();
            tableSpN.setRowCount(0);
            for (HangHoaDTO hh : hhlist) {
                float sl_hhn = 0;
                float thanhtien = 0;
                for (CT_HangHoaDTO cthh : cthhlist) {
                    if (hh.getMaHH().trim().equals(cthh.getMaHH().trim())) {
                        for (CT_PhieuNhapDTO ctpn : ctpnlist) {
                            if (cthh.getMaCT_HH().trim().equals(ctpn.getMaCT_HH().trim())) {
                                for (PhieuNhapDTO hdn : listpn) {
                                    java.util.Date ngayLapPN = hdn.getNgLapPhieu();
                                    if (hdn.getMaPN().trim().equals(ctpn.getMaPN().trim()) && ngayLapPN.after(startDate) && hdn.isTinhTrang() == true) {
                                        sl_hhn += ctpn.getSLNhap();
                                        thanhtien += ctpn.getDonGiaNhap() * ctpn.getSLNhap();
                                    }
                                }
                            }
                        }
                    }
                }
                if (sl_hhn == 0 && thanhtien == 0) {
                    continue; // Bỏ qua hàng hóa có sl_hh và thanhtien bằng 0
                }
                Object[] rowSpN = {hh.getMaHH(), hh.getTenHH(), hh.getMaLH(), sl_hhn, thanhtien};
                tableSpN.addRow(rowSpN);
            }
        } else if (endDate != null) {
            // Chỉ có giá trị ngày kết thúc, lọc dữ liệu theo ngày kết thúc
            int soLuongHoaDon = 0;
            int soLuongKhachHang = 0;
            float TongDoanhThu = 0;
            Set<String> uniqueCustomerIds = new HashSet<>();
            for (HoaDonBanHangDTO hdbh : listhdbh) {
                java.util.Date ngayLap = hdbh.getNgLap(); // Sử dụng kiểu java.util.Date
                if (ngayLap.before(endDate)) {
                    soLuongHoaDon++;
                    TongDoanhThu += hdbh.getThanhTien();
                }
                String maKH = hdbh.getMaKH();
                if (!maKH.trim().equals("KH000") && ngayLap.before(endDate)) {
                    uniqueCustomerIds.add(maKH);
                }
            }
            jlbDH.setText(soLuongHoaDon + " Đơn Hàng");
            jlbDT.setText(TongDoanhThu + " VNĐ");
            soLuongKhachHang = uniqueCustomerIds.size();
            jlbCustomer.setText(soLuongKhachHang + "  Khách Hàng");

            float TongKhoangChi = 0;
            for (PhieuNhapDTO pn : listpn) {
                java.util.Date ngayLapPN = pn.getNgLapPhieu(); // Sử dụng kiểu java.util.Date
                if (pn.isTinhTrang() == true && ngayLapPN.before(endDate)) {
                    TongKhoangChi += pn.getThanhTien();
                }
            }
            jlbKC.setText(TongKhoangChi + " VNĐ");
            //for hien san pham ban ra
            DefaultTableModel tableSpBr = (DefaultTableModel) jtbDoanhThu.getModel();
            tableSpBr.setRowCount(0);
            for (HangHoaDTO hh : hhlist) {
                float sl_hhbr = 0;
                float thanhtien = 0;
                for (CT_HangHoaDTO cthh : cthhlist) {
                    if (hh.getMaHH().trim().equals(cthh.getMaHH().trim())) {
                        for (CTHD_BanHangDTO cthd : cthdbhlist) {
                            if (cthh.getMaCT_HH().trim().equals(cthd.getMaCT_HH().trim())) {
                                for (HoaDonBanHangDTO hdbh : listhdbh) {
                                    java.util.Date ngayLap = hdbh.getNgLap();
                                    if (hdbh.getMaHD().trim().equals(cthd.getMaHD().trim()) && ngayLap.before(endDate)) {
                                        sl_hhbr += cthd.getSLBan();
                                        thanhtien += cthd.getDonGia() * cthd.getSLBan();
                                    }
                                }
                            }
                        }
                    }
                }
                if (sl_hhbr == 0 && thanhtien == 0) {
                    continue; // Bỏ qua hàng hóa có sl_hh và thanhtien bằng 0
                }
                Object[] rowSpBr = {hh.getMaHH(), hh.getTenHH(), hh.getMaLH(), sl_hhbr, thanhtien};
                tableSpBr.addRow(rowSpBr);
            }
            //for hien san pham nhap vao
            DefaultTableModel tableSpN = (DefaultTableModel) jtbKhoangChi.getModel();
            tableSpN.setRowCount(0);
            for (HangHoaDTO hh : hhlist) {
                float sl_hhn = 0;
                float thanhtien = 0;
                for (CT_HangHoaDTO cthh : cthhlist) {
                    if (hh.getMaHH().trim().equals(cthh.getMaHH().trim())) {
                        for (CT_PhieuNhapDTO ctpn : ctpnlist) {
                            if (cthh.getMaCT_HH().trim().equals(ctpn.getMaCT_HH().trim())) {
                                for (PhieuNhapDTO hdn : listpn) {
                                    java.util.Date ngayLapPN = hdn.getNgLapPhieu();
                                    if (hdn.getMaPN().trim().equals(ctpn.getMaPN().trim()) && ngayLapPN.before(endDate) && hdn.isTinhTrang() == true) {
                                        sl_hhn += ctpn.getSLNhap();
                                        thanhtien += ctpn.getDonGiaNhap() * ctpn.getSLNhap();
                                    }
                                }
                            }
                        }
                    }
                }
                if (sl_hhn == 0 && thanhtien == 0) {
                    continue; // Bỏ qua hàng hóa có sl_hh và thanhtien bằng 0
                }
                Object[] rowSpN = {hh.getMaHH(), hh.getTenHH(), hh.getMaLH(), sl_hhn, thanhtien};
                tableSpN.addRow(rowSpN);
            }
        } else {
            // Không có cả hai giá trị, thực hiện xử lý mặc định hoặc hiển thị thông báo
            loadTKBanner();
        }
    }

    //*******************************************************xuat file excel*********************************************************** 
    public boolean exportToExcel(JTable table, TableRowSorter<DefaultTableModel> rowSorter, String filePath, String title) {
        try {
            File excelFile = new File(filePath);
            Workbook workbook;
            if (excelFile.exists()) {
                FileInputStream inputStream = new FileInputStream(excelFile);
                workbook = WorkbookFactory.create(inputStream);
                inputStream.close();
                String newSheetName = title;
                boolean sheetExists = false;
                Iterator<Sheet> sheetIterator = workbook.sheetIterator();
                while (sheetIterator.hasNext()) {
                    if (sheetIterator.next().getSheetName().equals(newSheetName)) {
                        sheetExists = true;
                        break;
                    }
                }
                if (sheetExists) {
                    workbook.removeSheetAt(workbook.getSheetIndex(newSheetName));
                }
            } else {
                workbook = new XSSFWorkbook();
            }
            Sheet sheet = workbook.createSheet(title);
            if (isExcelFileInUse(new File(filePath))) {
                JOptionPane.showMessageDialog(null, "Tệp Excel đang mở. Hãy đóng tệp Excel trước khi xuất.");
                return false;
            }

            // Tạo dòng cho tiêu đề
            Row titleRow = sheet.createRow(0);
            Cell titleCell = titleRow.createCell(0);
            titleCell.setCellValue(title);

            int selectedIndex = jcbboxTimeLine.getSelectedIndex();
            CardLayout cardLayout = (CardLayout) jpnSetTime.getLayout();
            if (selectedIndex == 0) {
                Row headerRow = sheet.createRow(1);
                String[] columnHeaders = {"Time:", "Full"};
                for (int i = 0; i < columnHeaders.length; i++) {
                    Cell headerCell = headerRow.createCell(i);
                    headerCell.setCellValue(columnHeaders[i]);
                }
            } else if (selectedIndex == 1) {
                Row headerRow = sheet.createRow(1);
                String[] columnHeaders = {"Year:", String.valueOf(jYearChooser.getYear())};
                for (int i = 0; i < columnHeaders.length; i++) {
                    Cell headerCell = headerRow.createCell(i);
                    headerCell.setCellValue(columnHeaders[i]);
                }
            } else if (selectedIndex == 2) {
                Row headerRow = sheet.createRow(1);
                String[] columnHeaders = {"Month:", String.valueOf(jMonthChooser.getMonth()), "Year:", String.valueOf(jYearChooser1.getYear())};
                for (int i = 0; i < columnHeaders.length; i++) {
                    Cell headerCell = headerRow.createCell(i);
                    headerCell.setCellValue(columnHeaders[i]);
                }
            } else {
                Row headerRow = sheet.createRow(1);
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String NgStart = dateFormat.format(jdcStart.getDate());
                String NgEnd = dateFormat.format(jdcEnd.getDate());
                String[] columnHeaders = {"Ngày bắt đầu:", NgStart, "Ngày kết thúc", NgEnd};
                for (int i = 0; i < columnHeaders.length; i++) {
                    Cell headerCell = headerRow.createCell(i);
                    headerCell.setCellValue(columnHeaders[i]);
                }
            }

            // Tạo dòng tiêu đề cho các cột
            Row headerRow1 = sheet.createRow(2);
            String[] columnHeaders1 = {"Mã hàng hóa", "Tên hàng hóa", "Loại hàng", "Số lượng", "Tổng tiền"};
            for (int i = 0; i < columnHeaders1.length; i++) {
                Cell headerCell = headerRow1.createCell(i);
                headerCell.setCellValue(columnHeaders1[i]);
            }

            DefaultTableModel model;
            if (rowSorter == null) {
                model = (DefaultTableModel) table.getModel();
            } else {
                model = (DefaultTableModel) table.getModel();
            }

            DataFormat dataFormat = workbook.createDataFormat();
            CellStyle currencyCellStyle = workbook.createCellStyle();
            currencyCellStyle.setDataFormat(dataFormat.getFormat("###,###,###"));

            int rowCount = model.getRowCount();
            int columnCount = model.getColumnCount();

            for (int row = 0; row < rowCount; row++) {
                Row dataRow = sheet.createRow(row + 3); // Bắt đầu từ dòng 4 
                for (int col = 0; col < columnCount; col++) {
                    Cell cell = dataRow.createCell(col);
                    if (col == 3 || col == 4) {
                        String value = String.valueOf(model.getValueAt(row, col));
                        if (value.endsWith(".0")) {
                            value = value.substring(0, value.length() - 2);
                        }
                        double numericValue = Double.parseDouble(value);
                        cell.setCellValue(numericValue);
                        cell.setCellStyle(currencyCellStyle);
                    } else {
                        cell.setCellValue(String.valueOf(model.getValueAt(row, col)));
                    }
                }
            }

            for (int col = 0; col < columnCount; col++) {
                sheet.autoSizeColumn(col);
            }

            FileOutputStream output = new FileOutputStream(excelFile);
            workbook.write(output);
            output.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isExcelFileInUse(File file) {
        boolean isFileLocked = true;
        try ( RandomAccessFile raf = new RandomAccessFile(file, "rw");  FileChannel channel = raf.getChannel()) {
            FileLock lock = channel.tryLock();
            isFileLocked = lock == null;
        } catch (IOException e) {
            // Xử lý ngoại lệ
        }
        return isFileLocked;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel11 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jlbDH = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jlbCustomer = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jlbKC = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jlbDT = new javax.swing.JLabel();
        jpnSetTime = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jYearChooser = new com.toedter.calendar.JYearChooser();
        jPanel8 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jYearChooser1 = new com.toedter.calendar.JYearChooser();
        jMonthChooser = new com.toedter.calendar.JMonthChooser();
        jPanel9 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jdcStart = new com.toedter.calendar.JDateChooser();
        jLabel20 = new javax.swing.JLabel();
        jdcEnd = new com.toedter.calendar.JDateChooser();
        jcbboxTimeLine = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jpnDoanhThu = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jtfDTSearch = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jcbboxDTArrange = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        jcbboxDTLH = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbDoanhThu = new javax.swing.JTable();
        jbttnBCDT = new javax.swing.JButton();
        jpnKhoangChi = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jtfKCSearch = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jcbboxKCArrange = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        jcbboxKCLH = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtbKhoangChi = new javax.swing.JTable();
        jbttnBCKC = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(980, 640));

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("Thống Kê"));

        jPanel1.setBackground(new java.awt.Color(3, 169, 244));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, new java.awt.Color(127, 140, 141), new java.awt.Color(127, 140, 141)));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(232, 131));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Tổng đơn hàng");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/order.png"))); // NOI18N

        jlbDH.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jlbDH.setForeground(new java.awt.Color(255, 255, 255));
        jlbDH.setText(" ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 91, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jlbDH, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlbDH, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2))
        );

        jPanel3.setBackground(new java.awt.Color(244, 67, 54));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, new java.awt.Color(127, 140, 141), new java.awt.Color(127, 140, 141)));
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(232, 131));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Số Lượng Khách");

        jlbCustomer.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jlbCustomer.setForeground(new java.awt.Color(255, 255, 255));
        jlbCustomer.setText(" ");

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/customer_statistics.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jlbCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                        .addComponent(jLabel14))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 20, Short.MAX_VALUE)
                        .addComponent(jLabel14))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jlbCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        jPanel4.setBackground(new java.awt.Color(46, 204, 113));
        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, new java.awt.Color(127, 140, 141), new java.awt.Color(127, 140, 141)));
        jPanel4.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.setPreferredSize(new java.awt.Dimension(232, 131));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Khoảng Chi");

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/receipt.png"))); // NOI18N

        jlbKC.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jlbKC.setForeground(new java.awt.Color(255, 255, 255));
        jlbKC.setText(" ");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jlbKC, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel16))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addContainerGap(123, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlbKC, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 63, Short.MAX_VALUE)
                .addComponent(jLabel16))
        );

        jPanel2.setBackground(new java.awt.Color(233, 30, 99));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, new java.awt.Color(127, 140, 141), new java.awt.Color(127, 140, 141)));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(232, 131));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Doanh Thu");

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/economy.png"))); // NOI18N

        jlbDT.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jlbDT.setForeground(new java.awt.Color(255, 255, 255));
        jlbDT.setText(" ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jlbDT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 20, Short.MAX_VALUE)
                        .addComponent(jLabel5))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jlbDT, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        jpnSetTime.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 733, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 36, Short.MAX_VALUE)
        );

        jpnSetTime.add(jPanel12, "Toan Bo");

        jLabel9.setText("Theo Năm");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(jYearChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(574, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jYearChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        jpnSetTime.add(jPanel7, "Theo Nam");

        jLabel10.setText("Theo Tháng");

        jLabel13.setText("Theo Năm");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(jMonthChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(jYearChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(356, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(8, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jYearChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jMonthChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jpnSetTime.add(jPanel8, "Theo Thang");

        jLabel19.setText("Ngày Bắt Đầu");

        jdcStart.setDateFormatString("dd-MM-yyyy");

        jLabel20.setText("Ngày Kết Thúc");

        jdcEnd.setDateFormatString("dd-MM-yyyy");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addGap(18, 18, 18)
                .addComponent(jdcStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(jLabel20)
                .addGap(18, 18, 18)
                .addComponent(jdcEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(313, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jdcEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jdcStart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(0, 2, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jpnSetTime.add(jPanel9, "Theo Ngay");

        jcbboxTimeLine.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Toàn Bộ", "Theo Năm", "Theo Tháng", "Theo Ngày" }));
        jcbboxTimeLine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbboxTimeLineActionPerformed(evt);
            }
        });

        jLabel11.setText("Loại Thời Gian");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jcbboxTimeLine, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jpnSetTime, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jcbboxTimeLine, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpnSetTime, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/search.png"))); // NOI18N

        jtfDTSearch.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jtfDTSearch.setToolTipText("Search here...");
        jtfDTSearch.setBorder(null);

        jLabel8.setText("Sắp Xếp");

        jcbboxDTArrange.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chưa sắp xếp", "Giá tăng dần", "Giá giảm dần" }));
        jcbboxDTArrange.setBorder(null);

        jLabel18.setText("Loại Hàng");

        jcbboxDTLH.setBorder(null);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfDTSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel18)
                .addGap(18, 18, 18)
                .addComponent(jcbboxDTLH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 268, Short.MAX_VALUE)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jcbboxDTArrange, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jtfDTSearch)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jcbboxDTArrange, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel18)
                        .addComponent(jcbboxDTLH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jtbDoanhThu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Hàng Hóa", "Tên Hàng Hóa", "Loại Hàng", "Số Lượng", "Tổng Tiền"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtbDoanhThu.setEnabled(false);
        jScrollPane1.setViewportView(jtbDoanhThu);

        jbttnBCDT.setBackground(new java.awt.Color(231, 76, 60));
        jbttnBCDT.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jbttnBCDT.setForeground(new java.awt.Color(255, 255, 255));
        jbttnBCDT.setText("Báo Cáo Doanh Thu");
        jbttnBCDT.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbttnBCDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbttnBCDTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnDoanhThuLayout = new javax.swing.GroupLayout(jpnDoanhThu);
        jpnDoanhThu.setLayout(jpnDoanhThuLayout);
        jpnDoanhThuLayout.setHorizontalGroup(
            jpnDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnDoanhThuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnDoanhThuLayout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(10, 10, 10))
                    .addGroup(jpnDoanhThuLayout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(jpnDoanhThuLayout.createSequentialGroup()
                        .addComponent(jbttnBCDT)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jpnDoanhThuLayout.setVerticalGroup(
            jpnDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnDoanhThuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbttnBCDT, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Sản Phẩm Bán Ra", jpnDoanhThu);

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/search.png"))); // NOI18N

        jtfKCSearch.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jtfKCSearch.setToolTipText("Search here...");
        jtfKCSearch.setBorder(null);

        jLabel22.setText("Sắp Xếp");

        jcbboxKCArrange.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chưa sắp xếp", "Giá tăng dần", "Giá giảm dần" }));
        jcbboxKCArrange.setBorder(null);

        jLabel23.setText("Loại Hàng");

        jcbboxKCLH.setBorder(null);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfKCSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel23)
                .addGap(18, 18, 18)
                .addComponent(jcbboxKCLH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 268, Short.MAX_VALUE)
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jcbboxKCArrange, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jtfKCSearch)
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jcbboxKCArrange, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel23)
                        .addComponent(jcbboxKCLH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jtbKhoangChi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Hàng Hóa", "Tên Hàng Hóa", "Loại Hàng", "Số Lượng", "Tổng Tiền"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtbKhoangChi.setEnabled(false);
        jScrollPane2.setViewportView(jtbKhoangChi);

        jbttnBCKC.setBackground(new java.awt.Color(231, 76, 60));
        jbttnBCKC.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jbttnBCKC.setForeground(new java.awt.Color(255, 255, 255));
        jbttnBCKC.setText("Báo Cáo Khoảng Chi");
        jbttnBCKC.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbttnBCKC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbttnBCKCActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnKhoangChiLayout = new javax.swing.GroupLayout(jpnKhoangChi);
        jpnKhoangChi.setLayout(jpnKhoangChiLayout);
        jpnKhoangChiLayout.setHorizontalGroup(
            jpnKhoangChiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnKhoangChiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnKhoangChiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnKhoangChiLayout.createSequentialGroup()
                        .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(10, 10, 10))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnKhoangChiLayout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addContainerGap())
                    .addGroup(jpnKhoangChiLayout.createSequentialGroup()
                        .addComponent(jbttnBCKC)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jpnKhoangChiLayout.setVerticalGroup(
            jpnKhoangChiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnKhoangChiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbttnBCKC, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Sản Phẩm Nhập Vào", jpnKhoangChi);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jTabbedPane1))
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE))
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("Thu");
    }// </editor-fold>//GEN-END:initComponents

    private void jcbboxTimeLineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbboxTimeLineActionPerformed
        int selectedIndex = jcbboxTimeLine.getSelectedIndex();
        CardLayout cardLayout = (CardLayout) jpnSetTime.getLayout();
        if (selectedIndex == 0) {
            cardLayout.show(jpnSetTime, "Toan Bo");
            loadTKBanner();
        } else if (selectedIndex == 1) {
            cardLayout.show(jpnSetTime, "Theo Nam");
        } else if (selectedIndex == 2) {
            cardLayout.show(jpnSetTime, "Theo Thang");
        } else {
            cardLayout.show(jpnSetTime, "Theo Ngay");
        }
    }//GEN-LAST:event_jcbboxTimeLineActionPerformed

    private void jbttnBCDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbttnBCDTActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        File defaultDirectory = new File("D:\\OneDrive\\Tai Lieu\\CNPM\\ProJectSieuThi_CNPM\\src\\resources\\excel");
        fileChooser.setCurrentDirectory(defaultDirectory);
        // Tạo một FileFilter để chỉ cho phép lựa chọn các tệp có đuôi .xlsx
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel Files (.xlsx)", "xlsx");
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            // Lấy đường dẫn và tên file được chọn
            File selectedFile = fileChooser.getSelectedFile();
            String filePath = selectedFile.getAbsolutePath();
            // Kiểm tra xem tên tệp đã có đuôi .xlsx chưa
            if (!filePath.toLowerCase().endsWith(".xlsx")) {
                filePath += ".xlsx"; // Nếu chưa có, thêm đuôi .xlsx
            }
            // Thực hiện xuất tệp Excel
            if (exportToExcel(jtbDoanhThu, rowSorterDT, filePath, "Danh Sách Doanh Thu")) {
                JOptionPane.showMessageDialog(null, "Xuất Excel thành công!");
            } else {
                JOptionPane.showMessageDialog(null, "Xuất Excel thất bại!");
            }
        }
// TODO add your handling code here:
    }//GEN-LAST:event_jbttnBCDTActionPerformed

    private void jbttnBCKCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbttnBCKCActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        File defaultDirectory = new File("D:\\OneDrive\\Tai Lieu\\CNPM\\ProJectSieuThi_CNPM\\src\\resources\\excel");
        fileChooser.setCurrentDirectory(defaultDirectory);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel Files (.xlsx)", "xlsx");
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String filePath = selectedFile.getAbsolutePath();
            if (!filePath.toLowerCase().endsWith(".xlsx")) {
                filePath += ".xlsx";
            }
            // Thực hiện xuất tệp Excel
            if (exportToExcel(jtbKhoangChi, rowSorterKC, filePath, "Danh Sách Khoản Chi")) {
                if (isExcelFileInUse(new File(filePath))) {
                    JOptionPane.showMessageDialog(null, "Tệp Excel đang mở. Hãy đóng tệp Excel trước khi xuất.");
                } else {
                    JOptionPane.showMessageDialog(null, "Xuất Excel thành công!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Xuất Excel thất bại!");
            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jbttnBCKCActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private com.toedter.calendar.JMonthChooser jMonthChooser;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private com.toedter.calendar.JYearChooser jYearChooser;
    private com.toedter.calendar.JYearChooser jYearChooser1;
    private javax.swing.JButton jbttnBCDT;
    private javax.swing.JButton jbttnBCKC;
    private javax.swing.JComboBox<String> jcbboxDTArrange;
    private javax.swing.JComboBox<String> jcbboxDTLH;
    private javax.swing.JComboBox<String> jcbboxKCArrange;
    private javax.swing.JComboBox<String> jcbboxKCLH;
    private javax.swing.JComboBox<String> jcbboxTimeLine;
    private com.toedter.calendar.JDateChooser jdcEnd;
    private com.toedter.calendar.JDateChooser jdcStart;
    private javax.swing.JLabel jlbCustomer;
    private javax.swing.JLabel jlbDH;
    private javax.swing.JLabel jlbDT;
    private javax.swing.JLabel jlbKC;
    private javax.swing.JPanel jpnDoanhThu;
    private javax.swing.JPanel jpnKhoangChi;
    private javax.swing.JPanel jpnSetTime;
    private javax.swing.JTable jtbDoanhThu;
    private javax.swing.JTable jtbKhoangChi;
    private javax.swing.JTextField jtfDTSearch;
    private javax.swing.JTextField jtfKCSearch;
    // End of variables declaration//GEN-END:variables
}
