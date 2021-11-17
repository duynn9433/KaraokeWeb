/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.KaraokeBar;

/**
 *
 * @author Truong
 */
public class KaraokeBarDAO extends DAO{
    public void addKaraBar(KaraokeBar kara) throws SQLException{
        String sql = "insert into tblkaraokebar(ID,name,address,description) "
                + "values('1001',?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, kara.getName());
        ps.setString(2, kara.getAddress());
        ps.setString(3, kara.getDescription());
        ps.execute();
    }
    public void editKaraBar(KaraokeBar kara) throws SQLException{
        String sql = "update tblkaraokebar set name=?,address=?,description=? where ID='1001';";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, kara.getName());
        ps.setString(2, kara.getAddress());
        ps.setString(3, kara.getDescription());
        ps.execute();
    }
    public KaraokeBar getInforKaraBar() throws SQLException{
        String sql = "select* from tblkaraokebar where ID='1001';";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        KaraokeBar kara = new KaraokeBar();
        while(rs.next()){
            kara.setID(1001);
            kara.setName(rs.getString("name"));
             kara.setAddress(rs.getString("address"));
             kara.setDescription(rs.getString("description"));
        }
        return kara;
    }
}
