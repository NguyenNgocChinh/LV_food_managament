package QuanLyNhaHang.BUS;

import QuanLyNhaHang.DAO.SanPhamDAO;
import QuanLyNhaHang.DTO.SanPham;
import MyCustom.MyDialog;

import java.util.ArrayList;

public class SanPhamBUS {

    private ArrayList<SanPham> listSanPham = null;
    private SanPhamDAO spDAO = new SanPhamDAO();

    public SanPhamBUS() {
        docListSanPham();
    }

    public void docListSanPham() {
        listSanPham = spDAO.getListSanPham();
    }

    public ArrayList<SanPham> getListSanPham() {
        if (listSanPham == null) {
            docListSanPham();
        }
        return listSanPham;
    }

    public SanPham getSanPham(String ma) {
        if (!ma.trim().equals("")) {
            try {
                int maSP = Integer.parseInt(ma);
                for (SanPham sp : listSanPham) {
                    if (sp.getMaSP() == maSP) {
                        return sp;
                    }
                }
            } catch (Exception e) {
            }
        }
        return null;
    }

    public ArrayList<SanPham> getSanPhamTheoTen(String ten) {
        ArrayList<SanPham> dssp = new ArrayList<>();
        for (SanPham sp : listSanPham) {
            String tenSP = sp.getTenSP().toLowerCase();
            if (tenSP.toLowerCase().contains(ten.toLowerCase())) {
                dssp.add(sp);
            }
        }
        return dssp;
    }

    public String getAnh(String ma) {
        int maSP = Integer.parseInt(ma);
        return spDAO.getAnh(maSP);
    }

    public boolean themSanPham(String ten,
            String anh,
            String donGia) {

        if (ten.trim().equals("")) {
            new MyDialog("Tên SP không được để rỗng!", MyDialog.ERROR_DIALOG);
            return false;
        }

        try {
            donGia = donGia.replace(",", "");
            int donGiaSP = Integer.parseInt(donGia);
            SanPham sp = new SanPham();
            sp.setTenSP(ten);
            sp.setHinhAnh(anh);
            sp.setDonGia(donGiaSP);

            if (spDAO.themSanPham(sp)) {
                new MyDialog("Thêm thành công!", MyDialog.SUCCESS_DIALOG);
                return true;
            } else {
                new MyDialog("Thêm thất bại!", MyDialog.ERROR_DIALOG);
                return false;
            }
        } catch (Exception e) {
            System.out.print(e);
            new MyDialog(e.getMessage(), MyDialog.ERROR_DIALOG);
        }
        return false;
    }

    public boolean xoaSanPham(String ma) {
        if (ma.trim().equals("")) {
            new MyDialog("Chưa chọn sản phẩm để xoá!", MyDialog.ERROR_DIALOG);
            return false;
        }

        int maSP = Integer.parseInt(ma);
        if (spDAO.xoaSanPham(maSP)) {
            new MyDialog("Xoá thành công!", MyDialog.SUCCESS_DIALOG);
            return true;
        }

        new MyDialog("Xoá thất bại!", MyDialog.ERROR_DIALOG);
        return false;
    }

    public boolean suaSanPham(String ma,
            String ten,
            String anh,
            String donGia) {

        try {
            if (ma.trim().equals("")) {
                new MyDialog("Chưa chọn sản phẩm để sửa!", MyDialog.ERROR_DIALOG);
                return false;
            }
            donGia = donGia.replace(",", "");
            int maSP = Integer.parseInt(ma);
            int donGiaSP = Integer.parseInt(donGia);

            if (ten.trim().equals("")) {
                new MyDialog("Tên SP không được để rỗng!", MyDialog.ERROR_DIALOG);
                return false;
            }

            SanPham sp = new SanPham();
            sp.setMaSP(maSP);
            sp.setTenSP(ten);
            sp.setHinhAnh(anh);
            sp.setDonGia(donGiaSP);

            if (spDAO.suaSanPham(sp)) {
                new MyDialog("Sửa thành công!", MyDialog.SUCCESS_DIALOG);
                return true;
            } else {
                new MyDialog("Sửa thất bại!", MyDialog.ERROR_DIALOG);
                return false;
            }
        } catch (Exception e) {
            new MyDialog("Nhập số hợp lệ cho Đơn giá và Số lượng!", MyDialog.ERROR_DIALOG);
        }
        return false;
    }

    public String getTenSP(int maSP) {
        for (SanPham sp : listSanPham) {
            if (sp.getMaSP() == maSP) {
                return sp.getTenSP();
            }
        }
        return "";
    }
}
