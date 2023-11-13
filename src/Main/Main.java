package Main;

import QuanLyNhaHang.DAO.MyConnect;
import QuanLyNhaHang.GUI.MainQuanLyGUI;

public class Main {

    public static void main(String[] args) {
        new MyConnect();

        changLNF("Nimbus");
        MainQuanLyGUI gui = new MainQuanLyGUI();
        gui.showWindow();
        
    }

    public static void changLNF(String nameLNF) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if (nameLNF.equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
        }
    }
}
