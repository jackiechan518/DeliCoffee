package delicoffee.controller;

import delicoffee.dao.MenuImpl;
import delicoffee.model.Menu;
import delicoffee.service.MenuService;
import delicoffee.service.MenuServiceImpl;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Long
 */
public class MenuController {

    private JButton btnSubmit;
    private JButton btnSubmit1;
    private JTextField jtfMaMon;
    private JTextField jtfTenMon;
    private JTextField jtfSoLuongMon;
    private JTextField jtfGiaMon;
    private JLabel jlbMsg;

    private Menu menu = null;
    private MenuService menuService = null;

    public MenuController(JButton btnSubmit, JTextField jtfMaMon, JTextField jtfTenMon, JTextField jtfSoLuongMon, JTextField jtfGiaMon, JLabel jlbMsg, JButton btnSubmit1) {
        this.btnSubmit = btnSubmit;
        this.btnSubmit1 = btnSubmit1;
        this.jtfMaMon = jtfMaMon;
        this.jtfTenMon = jtfTenMon;
        this.jtfSoLuongMon = jtfSoLuongMon;
        this.jtfGiaMon = jtfGiaMon;
        this.jlbMsg = jlbMsg;
        
        this.menuService = new MenuServiceImpl();

    }

    public void setView(Menu menu) {
        this.menu = menu;
        jtfMaMon.setText("" + menu.getMaMon());
        jtfTenMon.setText(menu.getTenMon());
        jtfSoLuongMon.setText(menu.getSoLuongMon() + "");
        jtfGiaMon.setText(menu.getGiaMon() + "");
    }

    public void setEvent() {
        btnSubmit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (jtfTenMon.getText().length() == 0 || jtfTenMon.getText() == null) {
                    jlbMsg.setText("Vui lòng nhập đầy đủ các thông tin dưới đây");
                } else {
                    menu.setTenMon(jtfTenMon.getText());
                    menu.setSoLuongMon(Integer.parseInt(jtfSoLuongMon.getText()));
                    menu.setGiaMon(Integer.parseInt(jtfGiaMon.getText()));

                    int lastId = menuService.createOrUpdate(menu);
                    if (lastId > 0) {
                        menu.setMaMon(lastId);
                        jtfMaMon.setText("" + lastId);
                        jlbMsg.setText("Cập nhật thành công!");
                    }
                }

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnSubmit.setBackground(Color.red);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnSubmit.setBackground(Color.red);
            }

        });

        btnSubmit1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Menu menu = new Menu();

                try {
                    menu.setMaMon(Integer.parseInt(jtfMaMon.getText()));
                    menu.setTenMon(jtfTenMon.getText());
                    menu.setSoLuongMon(Integer.parseInt(jtfSoLuongMon.getText()));
                    menu.setGiaMon(Integer.parseInt(jtfGiaMon.getText()));

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Mã món phải là số");
                }
                MenuImpl.insertList(menu);
            }

        });

    }

}
