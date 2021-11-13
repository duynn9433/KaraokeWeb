/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import static DAO.DAO.con;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Client;
import model.Service;

/**
 *
 * @author xxxx9
 */
public class ServiceDao extends DAO {

    public ServiceDao() {
        super();
    }

    public List<Service> findService(String name) throws SQLException {
        List<Service> res = new ArrayList<>();

        String sql = "select * from tblservice where name like concat( '%',?,'%')";
        PreparedStatement ps;
        ps = con.prepareStatement(sql);
        ps.setString(1, name);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Service service = new Service();
            service.setID(rs.getInt("id"));
            service.setName(rs.getString("name"));
            service.setUnity(rs.getString("unity"));
            service.setPricePerUnit(rs.getFloat("pricePerUnit"));
            service.setDescription(rs.getString("description"));

            res.add(service);
        }

        return res;
    }

}
