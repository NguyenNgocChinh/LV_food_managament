package QuanLyNhaHang.DAO;

import QuanLyNhaHang.DTO.SanPham;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SanPhamDAO {

    public ArrayList<SanPham> getListSanPham() {
        try {
            String sql = "SELECT * FROM SanPham";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            ArrayList<SanPham> dssp = new ArrayList<>();
            while (rs.next()) {
                SanPham sp = new SanPham();

                sp.setMaSP(rs.getInt(1));
                sp.setTenSP(rs.getString(2));
                sp.setHinhAnh(rs.getString(3));
                sp.setDonGia(rs.getInt(4));

                dssp.add(sp);
            }
            return dssp;
        } catch (SQLException e) {
        }

        return null;
    }

    public SanPham getSanPham(int ma) {
        try {
            String sql = "SELECT *FROM SanPham WHERE MaSP=?";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1, ma);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                SanPham sp = new SanPham();

                sp.setMaSP(rs.getInt(1));
                sp.setTenSP(rs.getString(2));
                sp.setHinhAnh(rs.getString(3));
                sp.setDonGia(rs.getInt(4));

                return sp;
            }
        } catch (SQLException e) {
        }

        return null;
    }

    public String getAnh(int ma) {
        try {
            String sql = "SELECT HinhAnh FROM SanPham WHERE MaSP=?";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1, ma);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                return rs.getString("HinhAnh");
            }
        } catch (SQLException e) {
        }
        return "";
    }

    public boolean themSanPham(SanPham sp) {
        try {
            String sql = "INSERT INTO SanPham(TenSP, HinhAnh, DonGia) "
                    + "VALUES (?, ?, ?)";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setString(1, sp.getTenSP());
            pre.setString(2, sp.getHinhAnh());
            pre.setInt(3, sp.getDonGia());

            pre.execute();
            return true;
        } catch (SQLException e) {
        }
        return false;
    }

    public boolean xoaSanPham(int maSP) {
        try {
            String sql = "DELETE FROM SanPham WHERE MaSP=" + maSP;
            Statement st = MyConnect.conn.createStatement();
            st.execute(sql);
            return true;
        } catch (SQLException e) {
        }
        return false;
    }

    public boolean suaSanPham(SanPham sp) {
        try {
            String sql = "UPDATE SanPham SET "
                    + "TenSP=?, "
                    + "HinhAnh=?, DonGia=? "
                    + "WHERE MaSP=?";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setString(1, sp.getTenSP());
            pre.setString(2, sp.getHinhAnh());
            pre.setInt(3, sp.getDonGia());
            pre.setInt(4, sp.getMaSP());

            pre.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
