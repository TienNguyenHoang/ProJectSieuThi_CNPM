/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.myproject.GUI.QuanLy.QuanLyPanels;

import com.myproject.BUS.CTHD_BanHangBUS;
import com.myproject.BUS.HangHoaBUS;
import com.myproject.BUS.HoaDonBanHangBUS;
import com.myproject.BUS.KhachHangBUS;
import com.myproject.DTO.CTHD_BanHangDTO;
import com.myproject.DTO.HangHoaTongDTO;
import com.myproject.DTO.HoaDonBanHangDTO;
import com.myproject.DTO.KhachHangDTO;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Admin
 */
public class QLKhachHangPanel extends javax.swing.JPanel {

    KhachHangBUS KHBUS = new KhachHangBUS();
    List<KhachHangDTO> khlist = KHBUS.getList();
    HoaDonBanHangBUS HDBHBUS = new HoaDonBanHangBUS();
    List<HoaDonBanHangDTO> hdbhlist = HDBHBUS.getList();
    HangHoaBUS HHBUS = new HangHoaBUS();
    List<HangHoaTongDTO> hhlist = HHBUS.getAllHangHoa();
    CTHD_BanHangBUS CTBHBUS = new CTHD_BanHangBUS();
    List<CTHD_BanHangDTO> cthdbhlist = CTBHBUS.getList();

    public QLKhachHangPanel() {
        initComponents();
        loadKH();
        SearchKh();
        ComboboxKh();
        ComboboxSortDiem();
        ClickShowHH();
    }

    public void loadKH() {
        DefaultTableModel table = (DefaultTableModel) jtbCustomer.getModel();
        for (KhachHangDTO kh : khlist) {
            Object[] rowdata = {kh.getMaKH(), kh.getHoTen(), kh.getSDT(), kh.getNgSinh(), kh.getDiem(), kh.isTinhTrang()};
            table.addRow(rowdata);
        }
    }

    public void ClickShowHH() {
        jtbCustomer.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedRow = jtbCustomer.getSelectedRow();
                DefaultTableModel tablehdbh = (DefaultTableModel) jtbDHKH.getModel();
                tablehdbh.setRowCount(0);
                if (selectedRow != -1) {
                    String MaKHSelect = (String) jtbCustomer.getValueAt(selectedRow, 0);
                    boolean hasHoaDon = false;
                    for (HoaDonBanHangDTO hdbh : hdbhlist) {
                        if (MaKHSelect.trim().equals(hdbh.getMaKH().trim())) {
                            java.util.Date yourDate = hdbh.getNgLap(); // Lấy ngày giờ từ đối tượng HDBanHang
                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); // Định dạng ngày giờ
                            String formattedDate = dateFormat.format(yourDate);
                            Object[] rowdata = {hdbh.getMaHD(), formattedDate, hdbh.getThanhTien(), hdbh.getMaNV()};
                            tablehdbh.addRow(rowdata);
                            hasHoaDon = true;
                        }
                    }
                    if (!hasHoaDon) {
                        Object[] noDataMessage = {"Khách hàng không có hóa đơn."};
                        tablehdbh.addRow(noDataMessage);
                    }
                }

            }
        });
    }

    public void SearchKh() {
        jtfSearch.getDocument().addDocumentListener(new DocumentListener() {
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
                String text = jtfSearch.getText().toLowerCase(); // Lấy nội dung tìm kiếm và chuyển thành chữ thường
                DefaultTableModel tableModel = (DefaultTableModel) jtbCustomer.getModel();
                TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(tableModel);
                jtbCustomer.setRowSorter(rowSorter);
                // Chỉ tìm kiếm trên các cột 0, 1, và 2,3
                List<RowFilter<Object, Object>> filters = new ArrayList<>();
                filters.add(RowFilter.regexFilter("(?i)" + Pattern.quote(text), 0)); // Tìm kiếm trên cột 0
                filters.add(RowFilter.regexFilter("(?i)" + Pattern.quote(text), 1)); // Tìm kiếm trên cột 1
                filters.add(RowFilter.regexFilter("(?i)" + Pattern.quote(text), 2)); // Tìm kiếm trên cột 2
                RowFilter<Object, Object> combinedFilter = RowFilter.orFilter(filters);
                rowSorter.setRowFilter(combinedFilter);
            }
        });
    }
    //loc diem va hoat dong
    // Biến lưu trạng thái tình trạng và điểm được chọn
    private String selectedTrangThai = "";
    private String selectedDiem = "";

    public void resetFix() {
        selectedTrangThai = (String) FilterCbb.getSelectedItem();
        selectedDiem = (String) MathCbb.getSelectedItem();
        filterAndSortData();
    }

    public void ComboboxKh() {
        FilterCbb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedTrangThai = (String) FilterCbb.getSelectedItem();
                filterAndSortData();
            }
        });
    }

    public void ComboboxSortDiem() {
        MathCbb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedDiem = (String) MathCbb.getSelectedItem();
                filterAndSortData();
            }
        });
    }

    private void filterAndSortData() {
        DefaultTableModel tableModel = (DefaultTableModel) jtbCustomer.getModel();
        tableModel.setRowCount(0);
        List<KhachHangDTO> filteredList = new ArrayList<>(khlist);
        // Áp dụng lọc dựa trên trạng thái
        if (selectedTrangThai.equals("Đang hoạt động")) {
            filteredList.removeIf(kh -> !kh.isTinhTrang());
        } else if (selectedTrangThai.equals("Ngừng hoạt động")) {
            filteredList.removeIf(Kh -> Kh.isTinhTrang());
        }
        // Áp dụng sắp xếp dựa trên điểm
        if (selectedDiem.equals("Điểm tăng dần")) {
            Collections.sort(filteredList, Comparator.comparingDouble(KhachHangDTO::getDiem));
        } else if (selectedDiem.equals("Điểm giảm dần")) {
            Collections.sort(filteredList, Comparator.comparingDouble(KhachHangDTO::getDiem).reversed());
        }
        // Hiển thị dữ liệu đã lọc và sắp xếp
        for (KhachHangDTO kh : filteredList) {
            Object[] rowdata = {kh.getMaKH(), kh.getHoTen(), kh.getSDT(), kh.getNgSinh(), kh.getDiem(), kh.isTinhTrang()};
            tableModel.addRow(rowdata);
        }
    }

    private String generateNewMaKH() {
        int rowCount = khlist.size();
        int newSequence = rowCount + 1;
        return "KH" + String.format("%03d", newSequence);
    }
    // Hàm kiểm tra định dạng số điện thoại
    public static boolean isValidPhoneNumber(String phoneNumber) {
        String regex = "^0[97]\\d{8}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }
    // Hàm kiểm tra định dạng họ tên
    public boolean validateName(String name) {
        String regex = "^[\\p{L} .'-]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(name);
        return name.length() <= 30 && matcher.matches();
    }
// Hàm kiểm tra định dạng email
    private boolean isValidEmail(String email) {
        // Sử dụng biểu thức chính quy để kiểm tra email (ví dụ)
        String emailPattern = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(emailPattern);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        MaCtHd = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        NgLCtHd = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        ThTCtHd = new javax.swing.JTextField();
        MaCtKh = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        MaCtNv = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jDialog4 = new javax.swing.JDialog();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        MaKHText = new javax.swing.JTextField();
        TenKHtext = new javax.swing.JTextField();
        SdtKhText = new javax.swing.JTextField();
        NgSChoose = new com.toedter.calendar.JDateChooser();
        btnKh = new javax.swing.JButton();
        jDialog5 = new javax.swing.JDialog();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        MaKhFix = new javax.swing.JTextField();
        TenKhFix = new javax.swing.JTextField();
        SdtKhFix = new javax.swing.JTextField();
        NgSFix = new com.toedter.calendar.JDateChooser();
        jLabel19 = new javax.swing.JLabel();
        MathKhFix = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        TTCbb = new javax.swing.JComboBox<>();
        btnfix = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbDHKH = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtbCustomer = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jtfSearch = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        MathCbb = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        FilterCbb = new javax.swing.JComboBox<>();
        jPanel10 = new javax.swing.JPanel();
        jToolBar3 = new javax.swing.JToolBar();
        btnAddKh = new javax.swing.JButton();
        jSeparator10 = new javax.swing.JToolBar.Separator();
        btnFixKh = new javax.swing.JButton();
        jSeparator11 = new javax.swing.JToolBar.Separator();
        jbttnDetail = new javax.swing.JButton();
        jSeparator12 = new javax.swing.JToolBar.Separator();
        jbttnRefresh = new javax.swing.JButton();

        jDialog1.setMinimumSize(new java.awt.Dimension(792, 577));
        jDialog1.setModal(true);

        jPanel5.setBackground(new java.awt.Color(3, 169, 244));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/info.png"))); // NOI18N
        jLabel2.setText("Chi Tiết Đơn Hàng");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
        );

        jPanel6.setBackground(new java.awt.Color(250, 245, 242));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Hóa Đơn"));

        jLabel3.setText("Mã Hóa Đơn");

        MaCtHd.setBackground(new java.awt.Color(242, 239, 238));
        MaCtHd.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        MaCtHd.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        MaCtHd.setEnabled(false);

        jLabel4.setText("Ngày Lập");

        NgLCtHd.setBackground(new java.awt.Color(242, 239, 238));
        NgLCtHd.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        NgLCtHd.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        NgLCtHd.setEnabled(false);

        jLabel5.setText("Thành Tiền");

        ThTCtHd.setBackground(new java.awt.Color(242, 239, 238));
        ThTCtHd.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        ThTCtHd.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        ThTCtHd.setEnabled(false);

        MaCtKh.setBackground(new java.awt.Color(242, 239, 238));
        MaCtKh.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        MaCtKh.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        MaCtKh.setEnabled(false);

        jLabel6.setText("Mã Khách Hàng");

        MaCtNv.setBackground(new java.awt.Color(242, 239, 238));
        MaCtNv.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        MaCtNv.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        MaCtNv.setEnabled(false);

        jLabel7.setText("Mã Nhân Viên");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(MaCtKh))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(30, 30, 30)
                        .addComponent(MaCtHd, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(35, 35, 35)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(NgLCtHd, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                    .addComponent(MaCtNv))
                .addGap(34, 34, 34)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ThTCtHd, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(100, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(MaCtHd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(NgLCtHd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(ThTCtHd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(MaCtKh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(MaCtNv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Chi Tiết Hóa Đơn"));

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Hàng Hóa", "Tên Hàng Hóa", "Số Lượng", "Đơn Giá"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable3.setEnabled(false);
        jScrollPane3.setViewportView(jTable3);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jDialog4.setMinimumSize(new java.awt.Dimension(761, 340));
        jDialog4.setModal(true);

        jPanel9.setBackground(new java.awt.Color(3, 169, 244));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/customer.png"))); // NOI18N
        jLabel22.setText("Thêm Khách Hàng");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 274, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin"));

        jLabel10.setText("Mã Khách Hàng");

        jLabel11.setText("Số Điện Thoại");

        jLabel12.setText("Ngày Sinh");

        jLabel13.setText("Họ Tên");

        MaKHText.setEnabled(false);

        NgSChoose.setDateFormatString("dd-MM-yyyy");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(65, 65, 65)
                        .addComponent(TenKHtext))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(MaKHText, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(SdtKhText)
                    .addComponent(NgSChoose, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(MaKHText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SdtKhText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(jLabel12)
                        .addComponent(TenKHtext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(NgSChoose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        btnKh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/plus.png"))); // NOI18N
        btnKh.setText("Thêm");
        btnKh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnKh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnKh)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnKh)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialog4Layout = new javax.swing.GroupLayout(jDialog4.getContentPane());
        jDialog4.getContentPane().setLayout(jDialog4Layout);
        jDialog4Layout.setHorizontalGroup(
            jDialog4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialog4Layout.setVerticalGroup(
            jDialog4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jDialog5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jDialog5.setMinimumSize(new java.awt.Dimension(698, 360));
        jDialog5.setModal(true);

        jPanel13.setBackground(new java.awt.Color(3, 169, 244));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/customer.png"))); // NOI18N
        jLabel23.setText("Sửa Khách Hàng");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 211, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin"));

        jLabel14.setText("Mã Khách Hàng");

        jLabel16.setText("Số Điện Thoại");

        jLabel17.setText("Ngày Sinh");

        jLabel18.setText("Họ Tên");

        MaKhFix.setEnabled(false);

        NgSFix.setDateFormatString("dd-MM-yyyy");

        jLabel19.setText("Điểm tích lũy");

        MathKhFix.setEnabled(false);

        jLabel20.setText("Tình trạng");

        TTCbb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đang hoạt động", "Ngừng hoạt động" }));

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(18, 18, 18)
                        .addComponent(MaKhFix, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TenKhFix)
                            .addComponent(MathKhFix))))
                .addGap(27, 27, 27)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17)
                    .addComponent(jLabel20))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(SdtKhFix)
                    .addComponent(NgSFix, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addComponent(TTCbb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel16)
                    .addComponent(MaKhFix, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SdtKhFix, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel18)
                        .addComponent(jLabel17)
                        .addComponent(TenKhFix, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(NgSFix, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel19)
                        .addComponent(MathKhFix, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel20))
                    .addComponent(TTCbb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnfix.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/fix.png"))); // NOI18N
        btnfix.setText("Sửa");
        btnfix.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnfixActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnfix)))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnfix)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialog5Layout = new javax.swing.GroupLayout(jDialog5.getContentPane());
        jDialog5.getContentPane().setLayout(jDialog5Layout);
        jDialog5Layout.setHorizontalGroup(
            jDialog5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialog5Layout.setVerticalGroup(
            jDialog5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(980, 640));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Đơn Hàng Của Khách"));

        jtbDHKH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Hóa Đơn", "Ngày Lập", "Thành Tiền", "Mã Nhân Viên"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtbDHKH.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtbDHKH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtbDHKHMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtbDHKH);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Khách Hàng"));

        jtbCustomer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Khách Hàng", "Họ Tên", "Số điện thoại", "Ngày Sinh", "Điểm tích lũy", "Tình trạng"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtbCustomer.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtbCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtbCustomerMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jtbCustomer);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/search.png"))); // NOI18N

        jtfSearch.setToolTipText("Search here...");
        jtfSearch.setBorder(null);
        jtfSearch.setName(""); // NOI18N
        jtfSearch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfSearchFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfSearchFocusLost(evt);
            }
        });

        jLabel8.setText("Sắp Xếp");

        MathCbb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chưa sắp xếp", "Điểm tăng dần", "Điểm giảm dần" }));

        jLabel9.setText("Lọc");

        FilterCbb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Đang hoạt động", "Ngừng hoạt động" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(FilterCbb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 370, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(MathCbb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfSearch)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jtfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(MathCbb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(FilterCbb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jtfSearch.getAccessibleContext().setAccessibleName("");

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Thao tác"));

        jToolBar3.setRollover(true);

        btnAddKh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/plus.png"))); // NOI18N
        btnAddKh.setText("Thêm");
        btnAddKh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAddKh.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAddKh.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAddKh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddKhActionPerformed(evt);
            }
        });
        jToolBar3.add(btnAddKh);
        jToolBar3.add(jSeparator10);

        btnFixKh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/fix.png"))); // NOI18N
        btnFixKh.setText("Sửa");
        btnFixKh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFixKh.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnFixKh.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnFixKh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFixKhActionPerformed(evt);
            }
        });
        jToolBar3.add(btnFixKh);
        jToolBar3.add(jSeparator11);

        jbttnDetail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/detail.png"))); // NOI18N
        jbttnDetail.setText("Xem");
        jbttnDetail.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbttnDetail.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbttnDetail.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbttnDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbttnDetailActionPerformed(evt);
            }
        });
        jToolBar3.add(jbttnDetail);
        jToolBar3.add(jSeparator12);

        jbttnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/reload.png"))); // NOI18N
        jbttnRefresh.setText("Mới");
        jbttnRefresh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbttnRefresh.setFocusable(false);
        jbttnRefresh.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbttnRefresh.setPreferredSize(new java.awt.Dimension(42, 60));
        jbttnRefresh.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbttnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbttnRefreshActionPerformed(evt);
            }
        });
        jToolBar3.add(jbttnRefresh);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jToolBar3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jToolBar3, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jtbDHKHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtbDHKHMouseClicked
        jtbDHKH.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = jtbDHKH.getSelectedRow();
                if (e.getClickCount() == 2 && row != -1) {
                    // Đã thực hiện hai lần click, hủy chọn
                    jtbDHKH.clearSelection();
                    jbttnDetail.setEnabled(true);
                }
            }
        });
        DefaultTableModel table = (DefaultTableModel) jtbDHKH.getModel();
        if (table.getValueAt(0, 0).equals("Khách hàng không có hóa đơn.")) {
            jbttnDetail.setEnabled(false);
        } else {
            jbttnDetail.setEnabled(true);
        }
    }//GEN-LAST:event_jtbDHKHMouseClicked

    private void btnAddKhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddKhActionPerformed
        String newMaNV = generateNewMaKH();
        MaKHText.setText(newMaNV);
        SdtKhText.setText("");
        TenKHtext.setText("");
        JTextField dateTextField = (JTextField) NgSChoose.getDateEditor().getUiComponent();
        dateTextField.setEditable(false);
        dateTextField.setText("");
        jDialog4.setLocationRelativeTo(null);
        jDialog4.setVisible(true);

    }//GEN-LAST:event_btnAddKhActionPerformed

    private void btnFixKhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFixKhActionPerformed
        if (jtbCustomer.getSelectedRow() != -1) {
            int selectedRow = jtbCustomer.getSelectedRow();
            // Lấy thông tin từ dòng đã chọn
            String maNV = (String) jtbCustomer.getValueAt(selectedRow, 0);
            String tenNV = (String) jtbCustomer.getValueAt(selectedRow, 1);
            String sdt = (String) jtbCustomer.getValueAt(selectedRow, 2);
            Date Ngsinh = (Date) jtbCustomer.getValueAt(selectedRow, 3);
            int diem = (int) jtbCustomer.getValueAt(selectedRow, 4);
            int tinhTrang = (boolean) jtbCustomer.getValueAt(selectedRow, 5) ? 1 : 0;
            if (tinhTrang == 1) {
                TTCbb.setSelectedItem("Đang hoạt động");
            } else {
                TTCbb.setSelectedItem("Ngừng hoạt động");
            }
            MaKhFix.setText(maNV);
            TenKhFix.setText(tenNV);
            SdtKhFix.setText(sdt);
            NgSFix.setDate(Ngsinh);
            MathKhFix.setText(String.valueOf(diem));
            JTextField dateTextField = (JTextField) NgSFix.getDateEditor().getUiComponent();
            dateTextField.setEditable(false);
            jDialog5.setLocationRelativeTo(null);
            jDialog5.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một khách hàng để sửa!");
        }

    }//GEN-LAST:event_btnFixKhActionPerformed

    private void jbttnDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbttnDetailActionPerformed
        if (jtbDHKH.getSelectedRow() != -1) {
            int selectedRow = jtbDHKH.getSelectedRow();
            int selectedRowKh = jtbCustomer.getSelectedRow();
            String mahd = (String) jtbDHKH.getValueAt(selectedRow, 0);
            DefaultTableModel tabelcthd = (DefaultTableModel) jTable3.getModel();
            tabelcthd.setRowCount(0);
            String regexPattern = "HD0[0-9]";
            if (mahd.trim().matches(regexPattern)) {
                String manv = (String) jtbDHKH.getValueAt(selectedRow, 3);
                String makh = (String) jtbCustomer.getValueAt(selectedRowKh, 0);
                String NgLap = (String) jtbDHKH.getValueAt(selectedRow, 1);
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//                String formattedDate = dateFormat.format(NgLap);
                float ThanhTien = (float) jtbDHKH.getValueAt(selectedRow, 2);
                String formattedThanhTien = String.format("%.2f", ThanhTien);
                MaCtHd.setText(mahd);
                MaCtKh.setText(makh);
                MaCtNv.setText(manv);
                NgLCtHd.setText(NgLap);
                ThTCtHd.setText(formattedThanhTien);
                for (CTHD_BanHangDTO cthd : cthdbhlist) {
                    if (cthd.getMaHD().trim().equals(mahd.trim())) {
                        for (HangHoaTongDTO hht : hhlist) {
                            if (cthd.getMaCT_HH().trim().equals(hht.getMaCT_HH().trim())) {
                                Object[] rowcthd = {hht.getMaHH(), hht.getTenHH(), cthd.getSLBan(), cthd.getDonGia()};
                                tabelcthd.addRow(rowcthd);
                            }
                        }
                    }
                }
                jDialog1.setLocationRelativeTo(null);
                jDialog1.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Không Có Hóa Ðon !");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một đơn hàng để xem chi tiết!");
        }
    }//GEN-LAST:event_jbttnDetailActionPerformed

    private void btnKhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhActionPerformed
        if (TenKHtext.getText().isEmpty() || SdtKhText.getText().isEmpty() || NgSChoose.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đủ thông tin!");
        } else {
            String sdt = SdtKhText.getText();
            String hoten = TenKHtext.getText();
            boolean isDataValid = true;
            // Kiểm tra định dạng họ tên
            if (!validateName(hoten)) {
                JOptionPane.showMessageDialog(null, "Họ tên không hợp lệ!");
                isDataValid = false;
            }
            // Kiểm tra định dạng số điện thoại
            if (!isValidPhoneNumber(sdt)) {
                JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ!");
                isDataValid = false;
            }
            Date ngaySinh = NgSChoose.getDate();
            if (ngaySinh != null) {
                Calendar calNgaySinh = Calendar.getInstance();
                calNgaySinh.setTime(ngaySinh);
                Calendar calHienTai = Calendar.getInstance();
                // Kiểm tra xem ngày sinh có hợp lệ
                if (calNgaySinh.after(calHienTai)) {
                    JOptionPane.showMessageDialog(null, "Ngày sinh không hợp lệ (lớn hơn ngày hiện tại)!");
                    isDataValid = false;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày sinh!");
                isDataValid = false;
            }
            if (isDataValid) {
                KhachHangDTO newkh = new KhachHangDTO();
                newkh.setMaKH(MaKHText.getText());
                newkh.setHoTen(TenKHtext.getText());
                newkh.setSDT(SdtKhText.getText());
                newkh.setNgSinh(ngaySinh);
                newkh.setDiem(0);
                newkh.setTinhTrang(true);
                if (KHBUS.AddKhNew(newkh)) {
                    JOptionPane.showMessageDialog(null, "Thêm Thành Công!");
                    // Xóa toàn bộ dữ liệu từ bảng
                    DefaultTableModel tablefilter = (DefaultTableModel) jtbCustomer.getModel();
                    tablefilter.setRowCount(0);
                    khlist = KHBUS.getList(); // Đảm bảo datanv là danh sách mới sau khi thêm
                    loadKH(); // Load lại dữ liệu vào bảng
                    MathCbb.setSelectedIndex(0);
                    FilterCbb.setSelectedIndex(0);
                    jtfSearch.setText("");
                    jDialog4.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Thêm Không Thành Công!");
                }
            }
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_btnKhActionPerformed

    private void btnfixActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnfixActionPerformed
        if (NgSFix.getDate() == null || TenKhFix.getText().isEmpty() || SdtKhFix.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đủ thông tin!");
        } else {
            String sdt = SdtKhFix.getText();
            String hoten = TenKhFix.getText();
            boolean isDataValid = true;
            // Kiểm tra định dạng họ tên
            if (!validateName(hoten)) {
                JOptionPane.showMessageDialog(null, "Họ tên không hợp lệ!");
                isDataValid = false;
            }
            // Kiểm tra định dạng số điện thoại
            if (!isValidPhoneNumber(sdt)) {
                JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ!");
                isDataValid = false;
            }
            Date ngaySinhfix = NgSFix.getDate();
            if (ngaySinhfix != null) {
                Calendar calNgaySinh = Calendar.getInstance();
                calNgaySinh.setTime(ngaySinhfix);
                Calendar calHienTai = Calendar.getInstance();
                // Kiểm tra xem ngày sinh có hợp lệ
                if (calNgaySinh.after(calHienTai)) {
                    JOptionPane.showMessageDialog(null, "Ngày sinh không hợp lệ (lớn hơn ngày hiện tại)!");
                    isDataValid = false;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày sinh!");
                isDataValid = false;
            }
            if (isDataValid) {
                int choice = JOptionPane.showConfirmDialog(null, "Bạn có muốn sửa khách hàng không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    KhachHangDTO khfix = new KhachHangDTO();
                    khfix.setMaKH(MaKhFix.getText());
                    khfix.setHoTen(TenKhFix.getText());
                    khfix.setDiem(Integer.parseInt(MathKhFix.getText()));
                    khfix.setSDT(SdtKhFix.getText());
                    khfix.setNgSinh(NgSFix.getDate());
                    if ("Đang hoạt động".equals(TTCbb.getSelectedItem())) {
                        khfix.setTinhTrang(true);
                    } else if ("Ngừng hoạt động".equals(TTCbb.getSelectedItem())) {
                        khfix.setTinhTrang(false);
                    }
                    if (KHBUS.UpdatekhNew(khfix)) {
                        JOptionPane.showMessageDialog(null, "Sửa Thành Công!"); // Hiển thị thông báo thành công
                        // Xóa toàn bộ dữ liệu từ bảng
                        DefaultTableModel tablefilter = (DefaultTableModel) jtbCustomer.getModel();
                        tablefilter.setRowCount(0);
                        // Cập nhật dữ liệu trong datanv (nếu cần)
                        khlist = KHBUS.getList(); // Đảm bảo datanv là danh sách mới sau khi thêm
//                        loadKH(); // Load lại dữ liệu vào bảng
                        resetFix();
                        jDialog5.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Sửa Không Thành Công!");
                    }

                }

            }

        }

        // TODO add your handling code here:
    }//GEN-LAST:event_btnfixActionPerformed

    private void jtbCustomerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtbCustomerMouseClicked
        jbttnDetail.setEnabled(true);
        jtbCustomer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = jtbCustomer.getSelectedRow();
                if (e.getClickCount() == 2 && row != -1) {
                    // Đã thực hiện hai lần click, hủy chọn
                    jtbCustomer.clearSelection();
                }
            }
        });
    }//GEN-LAST:event_jtbCustomerMouseClicked

    private void jtfSearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfSearchFocusGained

        // TODO add your handling code here:
    }//GEN-LAST:event_jtfSearchFocusGained

    private void jtfSearchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfSearchFocusLost

        // TODO add your handling code here:
    }//GEN-LAST:event_jtfSearchFocusLost

    private void jbttnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbttnRefreshActionPerformed
        jtfSearch.setText("");
        FilterCbb.setSelectedIndex(0);
        MathCbb.setSelectedIndex(0);
        jbttnDetail.setEnabled(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_jbttnRefreshActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> FilterCbb;
    private javax.swing.JTextField MaCtHd;
    private javax.swing.JTextField MaCtKh;
    private javax.swing.JTextField MaCtNv;
    private javax.swing.JTextField MaKHText;
    private javax.swing.JTextField MaKhFix;
    private javax.swing.JComboBox<String> MathCbb;
    private javax.swing.JTextField MathKhFix;
    private javax.swing.JTextField NgLCtHd;
    private com.toedter.calendar.JDateChooser NgSChoose;
    private com.toedter.calendar.JDateChooser NgSFix;
    private javax.swing.JTextField SdtKhFix;
    private javax.swing.JTextField SdtKhText;
    private javax.swing.JComboBox<String> TTCbb;
    private javax.swing.JTextField TenKHtext;
    private javax.swing.JTextField TenKhFix;
    private javax.swing.JTextField ThTCtHd;
    private javax.swing.JButton btnAddKh;
    private javax.swing.JButton btnFixKh;
    private javax.swing.JButton btnKh;
    private javax.swing.JButton btnfix;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog4;
    private javax.swing.JDialog jDialog5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JToolBar.Separator jSeparator10;
    private javax.swing.JToolBar.Separator jSeparator11;
    private javax.swing.JToolBar.Separator jSeparator12;
    private javax.swing.JTable jTable3;
    private javax.swing.JToolBar jToolBar3;
    private javax.swing.JButton jbttnDetail;
    private javax.swing.JButton jbttnRefresh;
    private javax.swing.JTable jtbCustomer;
    private javax.swing.JTable jtbDHKH;
    private javax.swing.JTextField jtfSearch;
    // End of variables declaration//GEN-END:variables
}
