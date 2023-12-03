package com.myproject.BUS;

import com.myproject.DAO.KhuyenMaiDAO;
import com.myproject.DTO.HH_LH_DTO;
import com.myproject.DTO.KhuyenMaiDTO;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class KhuyenMaiBUS {
    KhuyenMaiDAO km = new KhuyenMaiDAO();
    
    public ArrayList<KhuyenMaiDTO> getAllKM() {
        return km.getAllKM();
    }

    public ArrayList<HH_LH_DTO> getAllHHOfKM(String MaKM) {
        return km.getAllHHOfKM(MaKM);
    }

    public boolean TatKM(String MaKM, Date NKTDate) {
        return km.TatKM(MaKM,NKTDate);
    }
    public String getLastestNum() {
        String MaKM = km.getLastestNum().trim();
        if (MaKM.isEmpty()) {
            return "KM01";
        }
        int index = Integer.parseInt(MaKM.substring(2));
        return "KM" + String.format("%02d", index+1);
    }

    public ArrayList<HH_LH_DTO> getAllHH_MAKM_NULL() {
        return km.getAllHH_MAKM_NULL();
    }

    public boolean addKM(String MaKM, Date NBDDate, Date NKTDate, float tlgFloat, boolean tinhtrang, String MotaString, ArrayList<String> arrHH) {
        return km.addKM(MaKM,NBDDate,NKTDate,tlgFloat,tinhtrang,MotaString,arrHH);
    }

    public ArrayList<HH_LH_DTO> getALLHHFormFix(String MaKM) {
        return km.getALLHHFormFix(MaKM);
    }

    public boolean updateKM(String MaKM, Date NKTDate, String moTa, ArrayList<HH_LH_DTO> arrHH) {
        return km.updateKM(MaKM,NKTDate,moTa,arrHH);
    }

    public void AutoUpdateKM(Date date) {
        km.AutoUpdateKM(date);
    }
    
    
    //TIENDAT
    // lấy khuyễn mãi theo mã khuyến mãi
    public KhuyenMaiDTO getPromtionByID(String MaKM) {
        return km.getPromotion(MaKM);
    }
}
