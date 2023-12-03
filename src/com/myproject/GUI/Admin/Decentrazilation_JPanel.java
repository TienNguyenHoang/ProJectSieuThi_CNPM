package com.myproject.GUI.Admin;

import com.myproject.BUS.NhanVienBUS;
import com.myproject.BUS.NhomQuyenBUS;
import com.myproject.DTO.NhanVienDTO;
import com.myproject.DTO.NhomQuyenDTO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.table.DefaultTableModel;

public class Decentrazilation_JPanel extends javax.swing.JPanel {
    NhanVienBUS nhanVienBUS = new NhanVienBUS();
    // liên quan đến bảng
    private DefaultTableModel tableModelDecentrazilation;
    private int seclectRowDecentrazilationTable;
    
    public Decentrazilation_JPanel() {
        initComponents();
        // đẩy dữ liệu lên bảng
        this.tableInitialization();
        this.loadDataToTable(nhanVienBUS.getStaffArrayList());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        decentrazilation_JTable = new javax.swing.JTable();
        authorize_JBTN = new javax.swing.JButton();
        manager_JRB = new javax.swing.JRadioButton();
        warehouseStaff_JRB = new javax.swing.JRadioButton();
        salesClerk_JRB = new javax.swing.JRadioButton();
        admin_JRB = new javax.swing.JRadioButton();
        refesh_JBTN = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(916, 650));

        decentrazilation_JTable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        decentrazilation_JTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã nhân viên", "Họ tên", "Chức vụ", "SĐT", "Mã nhóm quyền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        decentrazilation_JTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        decentrazilation_JTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clickedOnTable(evt);
            }
        });
        jScrollPane1.setViewportView(decentrazilation_JTable);

        authorize_JBTN.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        authorize_JBTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/authorize.png"))); // NOI18N
        authorize_JBTN.setText("Gán quyền");
        authorize_JBTN.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        authorize_JBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                assignPermissions(evt);
            }
        });

        buttonGroup1.add(manager_JRB);
        manager_JRB.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        manager_JRB.setText("Quản lý");
        manager_JRB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                choosenPermission(evt);
            }
        });

        buttonGroup1.add(warehouseStaff_JRB);
        warehouseStaff_JRB.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        warehouseStaff_JRB.setText("Nhân viên kho");

        buttonGroup1.add(salesClerk_JRB);
        salesClerk_JRB.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        salesClerk_JRB.setText("Nhân viên bán hàng");

        buttonGroup1.add(admin_JRB);
        admin_JRB.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        admin_JRB.setText("Quản trị viên");

        refesh_JBTN.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        refesh_JBTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/reload.png"))); // NOI18N
        refesh_JBTN.setText("Làm mới");
        refesh_JBTN.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        refesh_JBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refeshPage(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 904, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(refesh_JBTN)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(authorize_JBTN))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(manager_JRB)
                        .addGap(138, 138, 138)
                        .addComponent(warehouseStaff_JRB)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(salesClerk_JRB)
                        .addGap(119, 119, 119)
                        .addComponent(admin_JRB)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(manager_JRB)
                    .addComponent(warehouseStaff_JRB)
                    .addComponent(salesClerk_JRB)
                    .addComponent(admin_JRB))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(authorize_JBTN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(refesh_JBTN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        getAccessibleContext().setAccessibleName("");
    }// </editor-fold>//GEN-END:initComponents

    private void assignPermissions(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_assignPermissions
        int selectedRow = this.decentrazilation_JTable.getSelectedRow();
        if (selectedRow != -1) {
            String MaNV = (String) this.tableModelDecentrazilation.getValueAt(selectedRow, 0);
            NhanVienDTO staff = nhanVienBUS.getStaffInfoBYID(MaNV);
            if(this.buttonGroup1.getSelection() != null) {
                if(checkJRadioButton(staff)) {
                    JOptionPane.showMessageDialog(null, "QUYỀN ĐÃ ĐƯỢC GÁN CHO NHÂN VIÊN!");
                } else {
                    if(this.manager_JRB.isSelected() && staff.getChucVu().equalsIgnoreCase("Quản Lý")) {
                        staff.setMaQuyen("MQ01");
                    } else if(this.salesClerk_JRB.isSelected() && staff.getChucVu().equalsIgnoreCase("Nhân Viên Bán Hàng")) {
                        staff.setMaQuyen("MQ02");
                    } else if(this.warehouseStaff_JRB.isSelected() && staff.getChucVu().equalsIgnoreCase("Nhân Viên Kho")) {
                        staff.setMaQuyen("MQ03");
                    } else if(this.admin_JRB.isSelected() && staff.getChucVu().equalsIgnoreCase("Quản Trị Viên")) {
                        staff.setMaQuyen("MQ04");
                    } else {
                        JOptionPane.showMessageDialog(null, "Cần Phân Quyền Đúng Với Chức Vụ Của Nhân Viên!");
                        return;
                    }
                    nhanVienBUS.updateStaffInfo(staff);
                    JOptionPane.showMessageDialog(null, "PHÂN QUYỀN THÀNH CÔNG!");
                    loadDataToTable(nhanVienBUS.getStaffArrayList());
                }
            } else {
                JOptionPane.showMessageDialog(null, "VUI LÒNG CHỌN QUYỀN MÀ BẠN MUỐN GÁN!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "VUI LÒNG CHỌN"
                    + " NHÂN VIÊN MUỐN PHÂN QUYỀN!");
        }
    }//GEN-LAST:event_assignPermissions

    private void clickedOnTable(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clickedOnTable
        int selectedRow = this.decentrazilation_JTable.getSelectedRow();
        if (selectedRow != -1) {
            String position = (String) this.tableModelDecentrazilation.getValueAt(selectedRow, 4);
            if(position.equals("MQ0002")) {
                this.salesClerk_JRB.setSelected(true);
            } else if(position.equals("MQ0004")) {
                this.admin_JRB.setSelected(true);
            } else if(position.equals("MQ0003")) {
                this.warehouseStaff_JRB.setSelected(true);
            } else if(position.equals("MQ0001")) {
                this.manager_JRB.setSelected(true);
            } else {
                this.buttonGroup1.clearSelection();
            }
        }
    }//GEN-LAST:event_clickedOnTable

    private void choosenPermission(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_choosenPermission
        // TODO add your handling code here:
    }//GEN-LAST:event_choosenPermission

    private void refeshPage(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refeshPage
        loadDataToTable(nhanVienBUS.getStaffArrayList());
        this.buttonGroup1.clearSelection();
    }//GEN-LAST:event_refeshPage
 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton admin_JRB;
    private javax.swing.JButton authorize_JBTN;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTable decentrazilation_JTable;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton manager_JRB;
    private javax.swing.JButton refesh_JBTN;
    private javax.swing.JRadioButton salesClerk_JRB;
    private javax.swing.JRadioButton warehouseStaff_JRB;
    // End of variables declaration//GEN-END:variables

// PHƯƠNG THỨC
    // Xử lý đẩy dữ liệu lên bảng
    public void tableInitialization() {
        this.tableModelDecentrazilation = (DefaultTableModel) this.decentrazilation_JTable.getModel();
        this.seclectRowDecentrazilationTable = -1;
    }
    
    public Object loadStaffInfoToRow(NhanVienDTO staffItem) {
        Object[] row = new Object[] {
            staffItem.getMaNV(), staffItem.getTenNV(), staffItem.getChucVu(), 
            staffItem.getSDT(), staffItem.getMaQuyen()
        };
        return row;
    }
    
    public void loadDataToTable(ArrayList<NhanVienDTO> staffArrayList) {
        this.tableModelDecentrazilation.setRowCount(0);
        for (NhanVienDTO staffItem : nhanVienBUS.getStaffArrayList()) {
            if(staffItem.getMaQuyen().equals("")) {
                Object[] row = (Object[]) loadStaffInfoToRow(staffItem);
                this.tableModelDecentrazilation.addRow(row);
            }
        }
        for (NhanVienDTO staffItem : nhanVienBUS.getStaffArrayList()) {
            if(!staffItem.getMaQuyen().equals("")) {
                Object[] row = (Object[]) loadStaffInfoToRow(staffItem);
                this.tableModelDecentrazilation.addRow(row);
            }
        }
    }
    
    public boolean checkJRadioButton(NhanVienDTO staff) {
        if(staff.getMaQuyen().trim().equals("MQ01") && this.manager_JRB.isSelected()) {
            return true;
        } else if(staff.getMaQuyen().trim().equals("MQ02") && this.salesClerk_JRB.isSelected()) {
            return true;
        } else if(staff.getMaQuyen().trim().equals("MQ03") && this.warehouseStaff_JRB.isSelected()) {
            return true;
        } else if(staff.getMaQuyen().trim().equals("MQ04") && this.admin_JRB.isSelected()) {
            return true;
        }
        return false;
    }
}
