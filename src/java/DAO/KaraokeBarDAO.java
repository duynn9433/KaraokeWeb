/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import static DAO.DAO.con;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.BookedRoom;
import model.KaraokeBar;

/**
 *
 * @author duynn
 */
public class KaraokeBarDAO extends DAO{

    public KaraokeBarDAO() {
        super();
    }
    
    public void addInfoKara(KaraokeBar karaoke) throws SQLException{
         //set id
        int karaMaxID;
        PreparedStatement psinit = null;
        ResultSet rsinit = null;

        psinit = con.prepareStatement("select max(b.id) as max from tblkaraokebar b");
        rsinit = psinit.executeQuery();
        rsinit.next();
        karaMaxID = rsinit.getInt("max");

        karaoke.setID(++karaMaxID);
        psinit.close();
        rsinit.close();
        //luu
        
        String sql = "insert into tblkaraokebar values (?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1,karaoke.getID());
        ps.setString(2, karaoke.getName());
        ps.setString(3, karaoke.getAddress());
        ps.setString(4, karaoke.getDescription());
        
        ps.executeUpdate();
        
        ps.close();
    }
    
    public List<KaraokeBar> getInforKara() throws SQLException{
        List<KaraokeBar> res = new ArrayList<>();
        String sql ="select * from tblkaraokebar";
        
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            KaraokeBar kara = new KaraokeBar();
            kara.setID(rs.getInt("ID"));
            kara.setName(rs.getString("name"));
            kara.setAddress(rs.getString("address"));
            kara.setDescription(rs.getString("description"));
            
            res.add(kara);
        }
        
        return res;
    }
    
    public void editInforKara(KaraokeBar karaoke) throws SQLException{
        String sql="update tblkaraokebar set ID=?, name=?, address=?, description=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1,karaoke.getID());
        ps.setString(2, karaoke.getName());
        ps.setString(3, karaoke.getAddress());
        ps.setString(4, karaoke.getDescription());
        
        ps.executeUpdate();
        
        ps.close();
    }
}
