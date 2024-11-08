
package kelas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class category {

    String category_name;
    int category_id;

    
    private Connection konek;
    private PreparedStatement ps;
    private Statement st;
    private ResultSet rs;
    private String query;
    
    
    public category() throws SQLException{
        koneksi koneksi = new koneksi();
        konek = koneksi.konekDB();
    }

    
    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }
    
    
    public void tambahCategory(){
        query = "INSERT INTO category VALUES(?,?)";          
        try {
            ps = konek.prepareStatement(query);
            ps.setInt(1, category_id);
            ps.setString(2, category_name);
            
            ps.executeUpdate();
            ps.close();
            
            JOptionPane.showMessageDialog(null, "kategori berhasil ditambahkan");
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "kategori gagal ditambahkan");
        }
    }
    
    public ResultSet tampilCategory(){
         query = "SELECT* FROM category";   //sesuaikain nama tabelnya
        try {
            st = konek.createStatement();
            rs = st.executeQuery(query);
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "kategori gagal ditampilkan");
        }
       
        return rs;
    }
    
    public ResultSet tampilCombok(){
        query = "SELECT category_name FROM category";   //sesuaikain nama tabelnya
        try {
            st = konek.createStatement();
            rs = st.executeQuery(query);
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "kategori gagal ditampilkan");
        }
       
        return rs;
    }
     
    public void hapusCategory(){
        query = "DELETE FROM category WHERE category_id = ?";
        try {
            ps = konek.prepareStatement(query);
            ps.setInt(1, category_id);
           
            ps.executeUpdate();
            ps.close();
            
            JOptionPane.showMessageDialog(null, "kategori berhasil dihapus");
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "kategori gagal dihapus");
        }
    }
      
    public void ubahCategory(){
                  
        query = "UPDATE category SET"
                + " category_name=? "
                + " WHERE category_id=?";
        try {
            ps = konek.prepareStatement(query);
            ps.setString(1, category_name);
            ps.setInt(2, category_id);
            
            
            ps.executeUpdate();
            ps.close();
            
            JOptionPane.showMessageDialog(null, "kategori berhasil diubah");
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "kategori gagal diubah");
        } 
           
    }
    public ResultSet konversi(){
        query = "SELECT category_id FROM category WHERE category_name = ?";
        
        try {
            ps = konek.prepareStatement(query);
            ps.setString(1, category_name);
            rs = ps.executeQuery();
            
            System.out.println("data masuk");
        } catch (SQLException sQLException) {
            System.out.println("data tak masuk");
        }
        return rs;
    }
    public ResultSet autoID(){
        query = "SELECT category_id FROM category ORDER BY category_id DESC LIMIT 1";
        
        try {
            st = konek.createStatement();
            rs = st.executeQuery(query);
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "kategory gagal ditampilkan");
        }
        return rs;
    }   

    
}
