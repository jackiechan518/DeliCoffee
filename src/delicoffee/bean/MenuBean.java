
package delicoffee.bean;

import java.util.Date;

/**
 *
 * @author Long
 */
public class MenuBean {
    private String maMon;
    private String tenMon;
    private int soLuongMon ;
    private int giaMon;

    public MenuBean() {
    }

    public MenuBean(String maMon, String tenMon, int soLuongMon, int giaMon) {
        this.maMon = maMon;
        this.tenMon = tenMon;
        this.soLuongMon = soLuongMon;
        this.giaMon = giaMon;
    }

    public String getMaMon() {
        return maMon;
    }

    public void setMaMon(String maMon) {
        this.maMon = maMon;
    }

    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    public int getSoLuongMon() {
        return soLuongMon;
    }

    public void setSoLuongMon(int soLuongMon) {
        this.soLuongMon = soLuongMon;
    }

    public int getGiaMon() {
        return giaMon;
    }

    public void setGiaMon(int giaMon) {
        this.giaMon = giaMon;
    }
        
    
}
