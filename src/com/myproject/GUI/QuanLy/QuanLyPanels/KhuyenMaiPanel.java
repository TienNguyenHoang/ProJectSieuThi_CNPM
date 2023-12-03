package com.myproject.GUI.QuanLy.QuanLyPanels;

import com.myproject.BUS.KhuyenMaiBUS;
import com.myproject.DTO.HH_LH_DTO;
import com.myproject.DTO.KhuyenMaiDTO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class KhuyenMaiPanel extends javax.swing.JPanel {
    DefaultTableModel modelTableKM;
    KhuyenMaiBUS km = new KhuyenMaiBUS();
    ArrayList<KhuyenMaiDTO> arrKM = new ArrayList<>();
    public KhuyenMaiPanel() {
        initComponents();
        modelTableKM = (DefaultTableModel) jtbKhuyenMai.getModel();
        loadAllKM();
    }
    public void loadAllKM() {
        modelTableKM.setRowCount(0);
        arrKM = km.getAllKM();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (KhuyenMaiDTO p : arrKM) {
            String NBD = dateFormat.format(p.getNgBD());
            String NKT = dateFormat.format(p.getNgKT());
            Object[] row = {p.getMaKM(),NBD,NKT,p.getTiLeGiam(),p.getMoTa(),p.isTinhTrang()};
            modelTableKM.addRow(row);
        }
        jtbKhuyenMai.setModel(modelTableKM);
    }
    public void FilterKM() {
        modelTableKM.setRowCount(0);
        arrKM = km.getAllKM();
        FilterStatus();
        FilterSearch();
        if(arrKM.size() !=0) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            for (KhuyenMaiDTO p : arrKM) {
                String NBD = dateFormat.format(p.getNgBD());
                String NKT = dateFormat.format(p.getNgKT());
                Object[] row = {p.getMaKM(),NBD,NKT,p.getTiLeGiam(),p.getMoTa(),p.isTinhTrang()};
                modelTableKM.addRow(row);
            }
        }
        jtbKhuyenMai.setModel(modelTableKM);
    }
    public void FilterSearch(){
        String search = jtfSearch.getText();
        if(!search.trim().equalsIgnoreCase("")) {
            int i=0;
            while(i<arrKM.size()) {
                String str = "";
                if(!arrKM.get(i).getMaKM().toLowerCase().contains(search.toLowerCase()))
                {
                    arrKM.remove(arrKM.get(i));
                } else {
                    i++;
                }
            }
        }
    }
    public void FilterStatus() {
        if(jcbboxFilter.getSelectedIndex() != 0) {
            int intdex = jcbboxFilter.getSelectedIndex();
            boolean trangThai = false;
            if(intdex == 1) {
                trangThai = true;
            }
            int i=0;
            while(i<arrKM.size()) {
                if(arrKM.get(i).isTinhTrang()!= trangThai)
                {
                    arrKM.remove(arrKM.get(i));
                } else {
                    i++;
                }
            }
        }
    }
    public void FilterTiLeGiam() {
        if(jcbboxFilter.getSelectedIndex() != 0) {
            int intdex = jcbboxFilter.getSelectedIndex();
            boolean trangThai = false;
            if(intdex == 1) {
                trangThai = true;
            }
            int i=0;
            while(i<arrKM.size()) {
                if(arrKM.get(i).isTinhTrang()!= trangThai)
                {
                    arrKM.remove(arrKM.get(i));
                } else {
                    i++;
                }
            }
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jdlFormAddKM = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jbttnAddFormAddKM = new javax.swing.JButton();
        jtfMaKMFormAddKM = new javax.swing.JTextField();
        jdcNKTFormAddKM = new com.toedter.calendar.JDateChooser();
        jdcNBDFormAddKM = new com.toedter.calendar.JDateChooser();
        jtfTLGFormAddKM = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jtfMTFormAddKM = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtbKMFormAddKM = new javax.swing.JTable();
        jdlFormDetailKM = new javax.swing.JDialog();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jtfMaKMFormDetailKM = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jtfTLGFormDetailKM = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jcbboxTTFormDetailKM = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        jtfNBDFormDetailKM = new javax.swing.JTextField();
        jtfNKTFormDetailKM = new javax.swing.JTextField();
        jtfMTFormDetailKM = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtbKMFormDetailKM = new javax.swing.JTable();
        jdlFormFixKM = new javax.swing.JDialog();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jbttnFixFormFixKM = new javax.swing.JButton();
        jtfMaKMFormFixKM = new javax.swing.JTextField();
        jdcNKTFormFixKM = new com.toedter.calendar.JDateChooser();
        jtfTLGFormFixKM = new javax.swing.JTextField();
        jdcNBDFormFixKM = new com.toedter.calendar.JDateChooser();
        jLabel24 = new javax.swing.JLabel();
        jtfMTFormFixKM = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        jtbKMFormFixKM = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        jbttnOpenFormAddKM = new javax.swing.JButton();
        jbttnOpenFormDetailKM = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jbttnOffKM = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        jbttnOpenFormFixKM = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jtfSearch = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jcbboxFilter = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbKhuyenMai = new javax.swing.JTable();

        jdlFormAddKM.setMinimumSize(new java.awt.Dimension(752, 585));

        jPanel4.setBackground(new java.awt.Color(3, 169, 244));
        jPanel4.setPreferredSize(new java.awt.Dimension(752, 80));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/promotion.png"))); // NOI18N
        jLabel5.setText("Thêm Khuyến Mãi");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 260, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông Tin"));

        jLabel6.setText("Mã Khuyến Mãi");

        jLabel7.setText("Tỉ Lệ Giảm");

        jLabel10.setText("Ngày Bắt Đầu");

        jLabel11.setText("Ngày Kết Thúc");

        jbttnAddFormAddKM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/plus.png"))); // NOI18N
        jbttnAddFormAddKM.setText("Thêm");
        jbttnAddFormAddKM.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbttnAddFormAddKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbttnAddFormAddKMActionPerformed(evt);
            }
        });

        jtfMaKMFormAddKM.setEnabled(false);

        jdcNKTFormAddKM.setDateFormatString("yyyy-MM-dd");

        jdcNBDFormAddKM.setDateFormatString("yyyy-MM-dd");
        jdcNBDFormAddKM.setEnabled(false);

        jLabel22.setText("Mô tả");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel22)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jtfMaKMFormAddKM, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addComponent(jtfTLGFormAddKM)
                    .addComponent(jtfMTFormAddKM))
                .addGap(38, 38, 38)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jdcNBDFormAddKM, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(jdcNKTFormAddKM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbttnAddFormAddKM)
                .addGap(34, 34, 34))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6)
                                .addComponent(jtfMaKMFormAddKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel10)
                            .addComponent(jdcNBDFormAddKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jdcNKTFormAddKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel7)
                                .addComponent(jtfTLGFormAddKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jbttnAddFormAddKM))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22)
                    .addComponent(jtfMTFormAddKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jtbKMFormAddKM.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Hàng Hóa", "Tên Hàng Hóa", "Loại Hàng", "Khuyến Mãi"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jtbKMFormAddKM);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jdlFormAddKMLayout = new javax.swing.GroupLayout(jdlFormAddKM.getContentPane());
        jdlFormAddKM.getContentPane().setLayout(jdlFormAddKMLayout);
        jdlFormAddKMLayout.setHorizontalGroup(
            jdlFormAddKMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jdlFormAddKMLayout.setVerticalGroup(
            jdlFormAddKMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jdlFormDetailKM.setMinimumSize(new java.awt.Dimension(698, 629));
        jdlFormDetailKM.setModal(true);

        jPanel7.setBackground(new java.awt.Color(3, 169, 244));
        jPanel7.setPreferredSize(new java.awt.Dimension(752, 80));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/promotion.png"))); // NOI18N
        jLabel12.setText("Chi Tiết Khuyến Mãi");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 594, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 114, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông Tin"));

        jLabel13.setText("Mã Khuyến Mãi");

        jtfMaKMFormDetailKM.setBorder(null);

        jLabel14.setText("Tỉ Lệ Giảm");

        jtfTLGFormDetailKM.setBorder(null);

        jLabel15.setText("Ngày Bắt Đầu");

        jLabel16.setText("Ngày Kết Thúc");

        jLabel2.setText("Tình trạng");

        jcbboxTTFormDetailKM.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bật", "Tắt" }));

        jLabel23.setText("Mô tả");

        jtfNBDFormDetailKM.setBorder(null);

        jtfNKTFormDetailKM.setBorder(null);

        jtfMTFormDetailKM.setBorder(null);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jcbboxTTFormDetailKM, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jtfTLGFormDetailKM)
                    .addComponent(jtfMaKMFormDetailKM))
                .addGap(26, 26, 26)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addComponent(jLabel15)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jtfNBDFormDetailKM, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addComponent(jLabel16)
                            .addGap(18, 18, 18)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jtfMTFormDetailKM, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jtfNKTFormDetailKM, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jLabel23))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jtfMaKMFormDetailKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(jtfNBDFormDetailKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jtfTLGFormDetailKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(jtfNKTFormDetailKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jtfMTFormDetailKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel23))
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jcbboxTTFormDetailKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jtbKMFormDetailKM.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Hàng Hóa", "Tên Hàng Hóa", "Loại Hàng", "Tình Trạng"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
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
        jScrollPane3.setViewportView(jtbKMFormDetailKM);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jdlFormDetailKMLayout = new javax.swing.GroupLayout(jdlFormDetailKM.getContentPane());
        jdlFormDetailKM.getContentPane().setLayout(jdlFormDetailKMLayout);
        jdlFormDetailKMLayout.setHorizontalGroup(
            jdlFormDetailKMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jdlFormDetailKMLayout.setVerticalGroup(
            jdlFormDetailKMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jdlFormFixKM.setMinimumSize(new java.awt.Dimension(715, 680));
        jdlFormFixKM.setModal(true);

        jPanel10.setBackground(new java.awt.Color(3, 169, 244));
        jPanel10.setPreferredSize(new java.awt.Dimension(752, 80));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/promotion.png"))); // NOI18N
        jLabel17.setText("Sửa Khuyến Mãi");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 594, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 148, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông Tin"));

        jLabel18.setText("Mã Khuyến Mãi");

        jLabel19.setText("Tỉ Lệ Giảm");

        jLabel20.setText("Ngày Bắt Đầu");

        jLabel21.setText("Ngày Kết Thúc");

        jbttnFixFormFixKM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/fix.png"))); // NOI18N
        jbttnFixFormFixKM.setText("Sửa");
        jbttnFixFormFixKM.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbttnFixFormFixKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbttnFixFormFixKMActionPerformed(evt);
            }
        });

        jtfMaKMFormFixKM.setEnabled(false);

        jdcNKTFormFixKM.setDateFormatString("yyyy-MM-dd");

        jtfTLGFormFixKM.setEnabled(false);

        jdcNBDFormFixKM.setDateFormatString("yyyy-MM-dd");
        jdcNBDFormFixKM.setEnabled(false);

        jLabel24.setText("Mô tả");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(jLabel20)
                    .addComponent(jLabel24))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jtfMTFormFixKM)
                    .addComponent(jtfMaKMFormFixKM)
                    .addComponent(jdcNBDFormFixKM, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
                .addGap(53, 53, 53)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addComponent(jLabel19))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jtfTLGFormFixKM)
                    .addComponent(jdcNKTFormFixKM, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
                .addComponent(jbttnFixFormFixKM)
                .addGap(26, 26, 26))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jtfTLGFormFixKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jdcNKTFormFixKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(jtfMaKMFormFixKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jdcNBDFormFixKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addComponent(jbttnFixFormFixKM))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(jtfMTFormFixKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jtbKMFormFixKM.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Hàng Hóa", "Tên Hàng Hóa", "Loại Hàng", "Khuyến Mãi"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jtbKMFormFixKM);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, 742, Short.MAX_VALUE)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jdlFormFixKMLayout = new javax.swing.GroupLayout(jdlFormFixKM.getContentPane());
        jdlFormFixKM.getContentPane().setLayout(jdlFormFixKMLayout);
        jdlFormFixKMLayout.setHorizontalGroup(
            jdlFormFixKMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jdlFormFixKMLayout.setVerticalGroup(
            jdlFormFixKMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Thao tác"));

        jToolBar1.setRollover(true);

        jbttnOpenFormAddKM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/plus.png"))); // NOI18N
        jbttnOpenFormAddKM.setText("Thêm");
        jbttnOpenFormAddKM.setFocusable(false);
        jbttnOpenFormAddKM.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbttnOpenFormAddKM.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbttnOpenFormAddKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbttnOpenFormAddKMActionPerformed(evt);
            }
        });
        jToolBar1.add(jbttnOpenFormAddKM);

        jbttnOpenFormDetailKM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/detail.png"))); // NOI18N
        jbttnOpenFormDetailKM.setText("Xem");
        jbttnOpenFormDetailKM.setFocusable(false);
        jbttnOpenFormDetailKM.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbttnOpenFormDetailKM.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbttnOpenFormDetailKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbttnOpenFormDetailKMActionPerformed(evt);
            }
        });
        jToolBar1.add(jbttnOpenFormDetailKM);
        jToolBar1.add(jSeparator1);

        jbttnOffKM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/remove.png"))); // NOI18N
        jbttnOffKM.setText("Tắt");
        jbttnOffKM.setFocusable(false);
        jbttnOffKM.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbttnOffKM.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbttnOffKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbttnOffKMActionPerformed(evt);
            }
        });
        jToolBar1.add(jbttnOffKM);
        jToolBar1.add(jSeparator2);

        jbttnOpenFormFixKM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/fix.png"))); // NOI18N
        jbttnOpenFormFixKM.setText("Sửa");
        jbttnOpenFormFixKM.setFocusable(false);
        jbttnOpenFormFixKM.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbttnOpenFormFixKM.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbttnOpenFormFixKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbttnOpenFormFixKMActionPerformed(evt);
            }
        });
        jToolBar1.add(jbttnOpenFormFixKM);
        jToolBar1.add(jSeparator3);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/search.png"))); // NOI18N

        jtfSearch.setToolTipText("Search here...");
        jtfSearch.setBorder(null);
        jtfSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfSearchKeyReleased(evt);
            }
        });

        jLabel9.setText("Lọc");

        jcbboxFilter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất Cả", "Đang Bật", "Đang Tắt" }));
        jcbboxFilter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jcbboxFilterMouseClicked(evt);
            }
        });
        jcbboxFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbboxFilterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(jcbboxFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 635, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jtfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jcbboxFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseReleased(evt);
            }
        });

        jtbKhuyenMai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Khuyến Mãi", "Ngày Bắt Đầu", "Ngày Kết Thúc", "Tỉ Lệ Giảm", "Mô Tả", "Tình Trạng"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.String.class, java.lang.Boolean.class
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
        jtbKhuyenMai.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtbKhuyenMai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtbKhuyenMaiMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jtbKhuyenMaiMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jtbKhuyenMaiMouseReleased(evt);
            }
        });
        jtbKhuyenMai.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtbKhuyenMaiKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtbKhuyenMaiKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jtbKhuyenMai);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jbttnOpenFormAddKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbttnOpenFormAddKMActionPerformed
        // clean các field
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate currentDate = LocalDate.now();
        String NKTString = currentDate.format(formatter);
        JTextField dateTextField = (JTextField) jdcNBDFormAddKM.getDateEditor().getUiComponent();
        dateTextField.setText(NKTString);
        dateTextField = (JTextField) jdcNKTFormAddKM.getDateEditor().getUiComponent();
        dateTextField.setEditable(false);
        dateTextField.setText("");
        jtfMaKMFormAddKM.setText(km.getLastestNum());
        jtfTLGFormAddKM.setText("");
        jtfMTFormAddKM.setText("");
        // load table km form addkm
        ArrayList<HH_LH_DTO> arrHH = km.getAllHH_MAKM_NULL();
        DefaultTableModel modelTableAddKM = (DefaultTableModel) jtbKMFormAddKM.getModel();
        modelTableAddKM.setRowCount(0);
        for (HH_LH_DTO p : arrHH) {
            modelTableAddKM.addRow(new Object[] {p.getMaHH(),p.getTenHH(),p.getTenLH(),false});
        }
        jtbKMFormAddKM.setModel(modelTableAddKM);
        jdlFormAddKM.setLocationRelativeTo(null);
        jdlFormAddKM.setVisible(true);
        
    }//GEN-LAST:event_jbttnOpenFormAddKMActionPerformed

    private void jScrollPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseClicked

    }//GEN-LAST:event_jScrollPane1MouseClicked

    private void jtbKhuyenMaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtbKhuyenMaiMouseClicked

    }//GEN-LAST:event_jtbKhuyenMaiMouseClicked

    private void jbttnOpenFormFixKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbttnOpenFormFixKMActionPerformed
        if(jtbKhuyenMai.getSelectedRow()!=-1) {
            int row = jtbKhuyenMai.getSelectedRow();
            String MaKM = (String) jtbKhuyenMai.getValueAt(row, 0);
            String NBDString = (String) jtbKhuyenMai.getValueAt(row, 1);
            String NKTString = (String) jtbKhuyenMai.getValueAt(row, 2);
            float tiLeGiam = (float) jtbKhuyenMai.getValueAt(row, 3);
            String moTa = (String) jtbKhuyenMai.getValueAt(row, 4);
            jtfMaKMFormFixKM.setText(MaKM);
            jtfTLGFormFixKM.setText(Float.toString(tiLeGiam));
            JTextField dateTextField = (JTextField) jdcNBDFormFixKM.getDateEditor().getUiComponent();
            dateTextField.setText(NBDString);
            dateTextField = (JTextField) jdcNKTFormFixKM.getDateEditor().getUiComponent();
            dateTextField.setText(NKTString);
            dateTextField.setEditable(false);
            jtfMTFormFixKM.setText(moTa);
            // load vao table các hh có mkm = ... || makm = null
            ArrayList<HH_LH_DTO> arrHH = km.getALLHHFormFix(MaKM);
            DefaultTableModel modelTableFixKM = (DefaultTableModel) jtbKMFormFixKM.getModel();
            modelTableFixKM.setRowCount(0);
            for (HH_LH_DTO p : arrHH) {
                boolean km = true;
                if(p.getMaKM() == null) km = false;
                modelTableFixKM.addRow(new Object[] {p.getMaHH(),p.getTenHH(),p.getTenLH(),km});
            }
            jtbKMFormFixKM.setModel(modelTableFixKM);
            jdlFormFixKM.setLocationRelativeTo(null);
            jdlFormFixKM.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một khuyến mãi để sửa!");
        }
    }//GEN-LAST:event_jbttnOpenFormFixKMActionPerformed

    private void jbttnOffKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbttnOffKMActionPerformed
        if(jtbKhuyenMai.getSelectedRow()!=-1) {
            int choose = JOptionPane.showConfirmDialog(null,"Bạn có chắc muốn tắt?","Tắt Khuyến Mãi", JOptionPane.OK_CANCEL_OPTION);
            if (choose == JOptionPane.OK_OPTION) {
                int row = jtbKhuyenMai.getSelectedRow();
                String MaKM = (String) jtbKhuyenMai.getValueAt(row, 0);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate currentDate = LocalDate.now();
                String NKTString = currentDate.format(formatter);
                Date NKTDate = Date.valueOf(NKTString);
                boolean result = km.TatKM(MaKM.trim(),NKTDate);
                if (result) {
                    JOptionPane.showMessageDialog(null, "Tắt thành công!");
                    modelTableKM.setValueAt(false, row, 5);
                    modelTableKM.setValueAt(NKTString, row, 2);
                    jtbKhuyenMai.setModel(modelTableKM);
                    jbttnOffKM.setEnabled(false);
                    jbttnOpenFormFixKM.setEnabled(false);
                    jbttnOpenFormDetailKM.setEnabled(false);
                    
                } else {
                    JOptionPane.showMessageDialog(null, "Tắt thất bại!");
                }
            }
            
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một khuyến mãi để tắt!");
        }
    }//GEN-LAST:event_jbttnOffKMActionPerformed

    private void jbttnOpenFormDetailKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbttnOpenFormDetailKMActionPerformed
        if(jtbKhuyenMai.getSelectedRow()!=-1) {
            // load thông tin khuyến mãi
            jtfMaKMFormDetailKM.setText((String) jtbKhuyenMai.getValueAt(jtbKhuyenMai.getSelectedRow(), 0));
            jtfNBDFormDetailKM.setText((String) jtbKhuyenMai.getValueAt(jtbKhuyenMai.getSelectedRow(), 1));
            jtfNKTFormDetailKM.setText((String) jtbKhuyenMai.getValueAt(jtbKhuyenMai.getSelectedRow(), 2));
            jtfTLGFormDetailKM.setText(Float.toString((float)jtbKhuyenMai.getValueAt(jtbKhuyenMai.getSelectedRow(), 3)));
            jtfMTFormDetailKM.setText((String) jtbKhuyenMai.getValueAt(jtbKhuyenMai.getSelectedRow(), 4));
            if ((boolean) jtbKhuyenMai.getValueAt(jtbKhuyenMai.getSelectedRow(), 5)) jcbboxTTFormDetailKM.setSelectedIndex(0);
            else jcbboxTTFormDetailKM.setSelectedIndex(1);
            // load table khuyến mãi
            ArrayList<HH_LH_DTO> arrHH = km.getAllHHOfKM((String) jtbKhuyenMai.getValueAt(jtbKhuyenMai.getSelectedRow(), 0));
            DefaultTableModel modelTableDetailKM = (DefaultTableModel) jtbKMFormDetailKM.getModel();
            modelTableDetailKM.setRowCount(0);
            for (HH_LH_DTO p : arrHH) {
                modelTableDetailKM.addRow(new Object[] {p.getMaHH(),p.getTenHH(),p.getTenLH(),p.isTinhTrang()});
            }
            jtbKMFormDetailKM.setModel(modelTableDetailKM);
            jdlFormDetailKM.setLocationRelativeTo(null);
            jdlFormDetailKM.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một khuyến mãi để xem chi tiết!");
        }
    }//GEN-LAST:event_jbttnOpenFormDetailKMActionPerformed

    private void jScrollPane1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane1MouseReleased

    private void jtbKhuyenMaiMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtbKhuyenMaiMouseReleased
        if ((boolean)jtbKhuyenMai.getValueAt(jtbKhuyenMai.getSelectedRow(), jtbKhuyenMai.getColumnCount() - 1)) {
            jbttnOffKM.setEnabled(true);
            jbttnOpenFormFixKM.setEnabled(true);
            jbttnOpenFormDetailKM.setEnabled(true);
        } else {
            jbttnOffKM.setEnabled(false);
            jbttnOpenFormFixKM.setEnabled(false);
            jbttnOpenFormDetailKM.setEnabled(false);
        }
    }//GEN-LAST:event_jtbKhuyenMaiMouseReleased

    private void jtbKhuyenMaiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtbKhuyenMaiKeyPressed
        if ((boolean)jtbKhuyenMai.getValueAt(jtbKhuyenMai.getSelectedRow(), jtbKhuyenMai.getColumnCount() - 1)) {
            jbttnOffKM.setEnabled(true);
            jbttnOpenFormFixKM.setEnabled(true);
            jbttnOpenFormDetailKM.setEnabled(true);
        } else {
            jbttnOffKM.setEnabled(false);
            jbttnOpenFormFixKM.setEnabled(false);
            jbttnOpenFormDetailKM.setEnabled(false);
        }
    }//GEN-LAST:event_jtbKhuyenMaiKeyPressed

    private void jtbKhuyenMaiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtbKhuyenMaiKeyReleased
        if ((boolean)jtbKhuyenMai.getValueAt(jtbKhuyenMai.getSelectedRow(), jtbKhuyenMai.getColumnCount() - 1)) {
            jbttnOffKM.setEnabled(true);
            jbttnOpenFormFixKM.setEnabled(true);
            jbttnOpenFormDetailKM.setEnabled(true);
        } else {
            jbttnOffKM.setEnabled(false);
            jbttnOpenFormFixKM.setEnabled(false);
            jbttnOpenFormDetailKM.setEnabled(false);
        }
    }//GEN-LAST:event_jtbKhuyenMaiKeyReleased

    private void jtbKhuyenMaiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtbKhuyenMaiMousePressed
        if ((boolean)jtbKhuyenMai.getValueAt(jtbKhuyenMai.getSelectedRow(), jtbKhuyenMai.getColumnCount() - 1)) {
            jbttnOffKM.setEnabled(true);
            jbttnOpenFormFixKM.setEnabled(true);
            jbttnOpenFormDetailKM.setEnabled(true);
        } else {
            jbttnOffKM.setEnabled(false);
            jbttnOpenFormFixKM.setEnabled(false);
            jbttnOpenFormDetailKM.setEnabled(false);
        }
    }//GEN-LAST:event_jtbKhuyenMaiMousePressed

    private void jtfSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfSearchKeyReleased
        FilterKM();
    }//GEN-LAST:event_jtfSearchKeyReleased

    private void jcbboxFilterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jcbboxFilterMouseClicked
        
    }//GEN-LAST:event_jcbboxFilterMouseClicked

    private void jcbboxFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbboxFilterActionPerformed
        FilterKM();
    }//GEN-LAST:event_jcbboxFilterActionPerformed

    public boolean ValidateTLG(String num) {
        String regex = "^\\d+(\\.\\d+)?$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(num);
        return matcher.matches() && Float.parseFloat(num) > 0 && Float.parseFloat(num) < 1;
    }
    public boolean ValidateNBD_NKT(String NBDString,String NKTString) {
        boolean flag = true;
        Date NSXDate = Date.valueOf(NBDString);
        Date HSDDate = Date.valueOf(NKTString);
        int comparison = NSXDate.compareTo(HSDDate);
        if (comparison >= 0) {
            flag = false;
        }
        return flag;
    }
    public boolean ValidateChonHH(JTable jTable) {
        boolean flag = false;
        DefaultTableModel modelTable = (DefaultTableModel) jTable.getModel();
        for (int i=0;i<modelTable.getRowCount();i++) {
            if ((boolean)modelTable.getValueAt(i, 3)) {
                flag = true;
                break;
            }
        }
        return flag;
    }
    private void jbttnAddFormAddKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbttnAddFormAddKMActionPerformed
        String tlgString = jtfTLGFormAddKM.getText();
        String MotaString = jtfMTFormAddKM.getText();
        String MaKM = jtfMaKMFormAddKM.getText();
        JTextField dateTextField = (JTextField) jdcNBDFormAddKM.getDateEditor().getUiComponent();
        String NBDString = dateTextField.getText();
        dateTextField = (JTextField) jdcNKTFormAddKM.getDateEditor().getUiComponent();
        String NKTString = dateTextField.getText();
        if (!ValidateTLG(tlgString)) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng định dạng tỉ lệ giá!");
            return;
        }
        if (MotaString.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập mô tả!");
            return;
        }
        if (NKTString.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập ngày kết thúc!");
            return;
        }
        if (!ValidateNBD_NKT(NBDString,NKTString)) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập ngày kết thúc lớn hơn ngày bắt đầu!");
            return;
        }
        if (!ValidateChonHH(jtbKMFormAddKM)) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn ít nhất 1 hàng hóa!");
            return;
        }
        // them vao csdl
        Date NBDDate = Date.valueOf(NBDString);
        Date NKTDate = Date.valueOf(NKTString);
        float tlgFloat = Float.parseFloat(tlgString);
        ArrayList<String> arrHH = new ArrayList<>();
        DefaultTableModel modelTableDetailKM = (DefaultTableModel) jtbKMFormAddKM.getModel();
        for (int i=0;i<modelTableDetailKM.getRowCount();i++) {
            if ((boolean)modelTableDetailKM.getValueAt(i, 3)) {
                String maHH = (String)modelTableDetailKM.getValueAt(i, 0);
                arrHH.add(maHH.trim());
            }
        }
        boolean result = km.addKM(MaKM,NBDDate,NKTDate,tlgFloat,true,MotaString,arrHH);
        if (result) {
            JOptionPane.showMessageDialog(null, "Thêm thành công");
            modelTableKM.addRow(new Object[] {MaKM,NBDString,NKTString,tlgFloat,MotaString,true});
            jtbKhuyenMai.setModel(modelTableKM);
            jdlFormAddKM.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Thêm thất bại!");
        }
        
    }//GEN-LAST:event_jbttnAddFormAddKMActionPerformed

    private void jbttnFixFormFixKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbttnFixFormFixKMActionPerformed
        String MaKM = jtfMaKMFormFixKM.getText().trim();
        JTextField dateTextField = (JTextField) jdcNBDFormFixKM.getDateEditor().getUiComponent();
        String NBDString = dateTextField.getText();
        dateTextField = (JTextField) jdcNKTFormFixKM.getDateEditor().getUiComponent();
        String NKTString = dateTextField.getText();
        String tlgString = jtfTLGFormFixKM.getText();
        String moTa = jtfMTFormFixKM.getText();
        java.util.Date currentDate = new java.util.Date();
        Date date = new Date(currentDate.getTime());
        Date NKTDate = Date.valueOf(NKTString);
        int comparison = NKTDate.compareTo(date);
        if (moTa.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập mô tả!");
            return;
        }
        if (!ValidateNBD_NKT(NBDString,NKTString)) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập ngày kết thúc lớn hơn ngày bắt đầu!");
            return;
        }
        if (comparison <= 0) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập ngày kết thúc phải lớn hơn ngày hiện tại!");
            return;
        }
        if (!ValidateChonHH(jtbKMFormFixKM)) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn ít nhất 1 hàng hóa!");
            return;
        }
        // them vao csdl
        ArrayList<HH_LH_DTO> arrHH = new ArrayList<>();
        DefaultTableModel modelTableFixKM = (DefaultTableModel) jtbKMFormFixKM.getModel();
        for (int i=0;i<modelTableFixKM.getRowCount();i++) {
            String maHH = (String) modelTableFixKM.getValueAt(i, 0);
            String tenHH = (String) modelTableFixKM.getValueAt(i, 1);
            String tenLH = (String) modelTableFixKM.getValueAt(i, 2);
            boolean maKMBoolean = (boolean) modelTableFixKM.getValueAt(i, 3);
            String maKMString=null;
            if (maKMBoolean) maKMString = MaKM;
            HH_LH_DTO p = new HH_LH_DTO(maHH.trim(),tenHH,tenLH,maKMString,true);
            arrHH.add(p);
        }
        int row = jtbKhuyenMai.getSelectedRow();
        boolean result = km.updateKM(MaKM,NKTDate,moTa,arrHH);
        if (result) {
            JOptionPane.showMessageDialog(null, "Sửa thành công");
            modelTableKM.setValueAt(NKTString, row, 2);
            modelTableKM.setValueAt(moTa, row, 4);
            jtbKhuyenMai.setModel(modelTableKM);
            jdlFormFixKM.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Sửa thất bại!");
        }
    }//GEN-LAST:event_jbttnFixFormFixKMActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
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
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JButton jbttnAddFormAddKM;
    private javax.swing.JButton jbttnFixFormFixKM;
    private javax.swing.JButton jbttnOffKM;
    private javax.swing.JButton jbttnOpenFormAddKM;
    private javax.swing.JButton jbttnOpenFormDetailKM;
    private javax.swing.JButton jbttnOpenFormFixKM;
    private javax.swing.JComboBox<String> jcbboxFilter;
    private javax.swing.JComboBox<String> jcbboxTTFormDetailKM;
    private com.toedter.calendar.JDateChooser jdcNBDFormAddKM;
    private com.toedter.calendar.JDateChooser jdcNBDFormFixKM;
    private com.toedter.calendar.JDateChooser jdcNKTFormAddKM;
    private com.toedter.calendar.JDateChooser jdcNKTFormFixKM;
    private javax.swing.JDialog jdlFormAddKM;
    private javax.swing.JDialog jdlFormDetailKM;
    private javax.swing.JDialog jdlFormFixKM;
    private javax.swing.JTable jtbKMFormAddKM;
    private javax.swing.JTable jtbKMFormDetailKM;
    private javax.swing.JTable jtbKMFormFixKM;
    private javax.swing.JTable jtbKhuyenMai;
    private javax.swing.JTextField jtfMTFormAddKM;
    private javax.swing.JTextField jtfMTFormDetailKM;
    private javax.swing.JTextField jtfMTFormFixKM;
    private javax.swing.JTextField jtfMaKMFormAddKM;
    private javax.swing.JTextField jtfMaKMFormDetailKM;
    private javax.swing.JTextField jtfMaKMFormFixKM;
    private javax.swing.JTextField jtfNBDFormDetailKM;
    private javax.swing.JTextField jtfNKTFormDetailKM;
    private javax.swing.JTextField jtfSearch;
    private javax.swing.JTextField jtfTLGFormAddKM;
    private javax.swing.JTextField jtfTLGFormDetailKM;
    private javax.swing.JTextField jtfTLGFormFixKM;
    // End of variables declaration//GEN-END:variables
}
