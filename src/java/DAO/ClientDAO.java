/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.*;

/**
 *
 * @author duynn
 */
public class ClientDAO extends DAO {

    public ClientDAO() {
        super();
    }
    /**
     * @author nguyen ngoc duy
     */
    public List<Client> searchClient(String name, String phoneNumber) throws SQLException {
        List<Client> res = new ArrayList<>();
        String sql = "select * from tblclient c where c.name = ? and c.phoneNumber = ?";
        PreparedStatement ps;

        ps = con.prepareStatement(sql);
        ps.setString(1, name);
        ps.setString(2, phoneNumber);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            if (rs.getBoolean("isActive")) {
                Client c = new Client();
                c.setID(rs.getInt("ID"));
                c.setName(rs.getString("name"));
                c.setPhoneNumber(rs.getString("phoneNumber"));
                c.setAddress(rs.getString("address"));
                c.setMail(rs.getString("mail"));
                c.setNote(rs.getString("note"));
                c.setIsActive(true);
                res.add(c);
            }
        }

        return res;
    }
    /**
     * @author nguyen ngoc duy
     */
    public void addClient(Client c) throws SQLException {
        //set id
        int clientMaxID;
        PreparedStatement psinit = null;
        ResultSet rsinit = null;

        psinit = con.prepareStatement("select max(c.id) as max from tblclient c");
        rsinit = psinit.executeQuery();
        rsinit.next();
        clientMaxID = rsinit.getInt("max");
        c.setID(++clientMaxID);

        psinit.close();
        rsinit.close();
        System.out.println("ClientDAO " + c.toString());
        //
        String sql = "insert into tblclient values (?,?,?,?,?,?,?)";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, c.getID());
        ps.setString(2, c.getName());
        ps.setString(3, c.getAddress());
        ps.setString(4, c.getMail());
        ps.setString(5, c.getPhoneNumber());
        ps.setString(6, c.getNote());
        ps.setBoolean(7, true);
        ps.executeUpdate();

    }
    /**
     * @author nguyen ngoc duy
     */
    public int getNextID() throws SQLException {
        String sql = "select max(c.id) as max from tblclient b";
        PreparedStatement ps = null;
        ResultSet rs = null;

        ps = con.prepareStatement(sql);

        rs = ps.executeQuery();
        return rs.getInt("max") + 1;

    }
}
