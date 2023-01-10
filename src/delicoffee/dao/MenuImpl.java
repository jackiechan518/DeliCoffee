package delicoffee.dao;

import delicoffee.model.Menu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Long
 */
public class MenuImpl implements MenuDAO {

    @Override
    public List<Menu> getList() {
        try {
            Connection cons = DBConnect.getConnection();
            String sql = "SELECT * FROM thuc_don";
            List<Menu> list = new ArrayList<>();
            PreparedStatement ps = cons.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Menu menu = new Menu();
                menu.setMaMon(rs.getInt("maMon"));
                menu.setTenMon(rs.getString("tenMon"));
                menu.setSoLuongMon(rs.getInt("soLuongMon"));
                menu.setGiaMon(rs.getInt("giaMon"));
                list.add(menu);
            }
            ps.close();
            rs.close();
            cons.close();
            return list;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static void removeList(int id) {
        try {
            Connection cons = DBConnect.getConnection();
            String sql = "DELETE FROM thuc_don where maMon = ?";
            PreparedStatement ps = cons.prepareCall(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertList(Menu menu) {
        try {
            Connection cons = DBConnect.getConnection();
            String sql = "INSERT INTO thuc_don(maMon, tenMon, soLuongMon, giaMon) VALUES(?, ?, ?, ?)";
            PreparedStatement ps = cons.prepareStatement(sql);
            ps.setInt(1, menu.getMaMon());
            ps.setString(2, menu.getTenMon());
            ps.setInt(3, menu.getSoLuongMon());
            ps.setInt(4, menu.getGiaMon());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int createOrUpdate(Menu menu) {
        try {
            Connection cons = DBConnect.getConnection();
            String sql = "INSERT INTO thuc_don(maMon, tenMon, soLuongMon, giaMon) VALUES(?, ?, ?, ?) ON DUPLICATE KEY UPDATE maMon = VALUES(maMon), tenMon = VALUES(tenMon), soLuongMon = VALUES(soLuongMon), giaMon = VALUES(giaMon)";
            PreparedStatement ps = cons.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, menu.getMaMon());
            ps.setString(2, menu.getTenMon());
            ps.setInt(3, menu.getSoLuongMon());
            ps.setInt(4, menu.getGiaMon());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            int generatedKey = 0;
            if (rs.next()) {
                generatedKey = rs.getInt(1);
            }
            ps.close();
            cons.close();
            return generatedKey;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public static void main(String[] args) {
        MenuDAO MenuDAO = new MenuImpl();
        System.out.println(MenuDAO.getList());
    }

}
