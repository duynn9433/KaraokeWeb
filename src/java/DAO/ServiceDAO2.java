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
import model.BookedRoom;
import model.Booking;
import model.Client;
import model.Service;
import model.UsedService;

/**
 *
 * @author xxxx9
 */
public class ServiceDAO extends DAO {

    public ServiceDAO() {
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

    public void addUsedService(Booking booking) throws SQLException {
        con.setAutoCommit(false);

        String sql = "INSERT INTO tblusedservice (amount, pricePerUnit, note, tblserviceID, tblbookedroomID) VALUES (?,?,?,?,?)";
        PreparedStatement ps;
        ps = con.prepareStatement(sql);

        for (BookedRoom bookedRoom : booking.getListBookedRoom()) {
            for (UsedService us : bookedRoom.getListUsedService()) {

                PreparedStatement ps1 = con.prepareStatement("SELECT COUNT(ID) from tblusedservice where ID=?");
                ps1.setInt(1, us.getID());
                ResultSet rs1 = ps1.executeQuery();
                rs1.next();
                int count = rs1.getInt(1);

                if (count == 0) {
                    ps.setInt(1, us.getAmount());
                    ps.setFloat(2, us.getPricePerUnit());
                    ps.setString(3, us.getNote());
                    ps.setInt(4, us.getService().getID());
                    ps.setInt(5, bookedRoom.getID());

                    ps.executeUpdate();
                }
                else 
                {
                    //update here
                }

            }
        }

        con.commit();
        con.setAutoCommit(true);
    }

    public void addService(Service s)throws SQLException{
        int maxID;
        PreparedStatement psinit = null;
        ResultSet rsinit = null;

        psinit = con.prepareStatement("select max(s.id) as max from tblservice s");
        rsinit = psinit.executeQuery();
        rsinit.next();
        maxID = rsinit.getInt("max");

        s.setID(++maxID);
        psinit.close();
        rsinit.close();
        //luu
        
        String sql = "insert into tblservice values (?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1,s.getID());
        ps.setString(2, s.getName());
        ps.setString(3, s.getUnity());
        ps.setFloat(4, s.getPricePerUnit());
        ps.setString(5, s.getDescription());
        ps.setString(6, "1");
        
        ps.executeUpdate();
        
        ps.close();
    }
    
    public void editService(Service s) throws SQLException{
        
        String sql="UPDATE tblservice set ID=?, name=?, unity=?,pricePerUnit = ?, description=?, isActive = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1,s.getID());
        ps.setString(2, s.getName());
        ps.setString(3, s.getUnity());
        ps.setFloat(4, s.getPricePerUnit());
        ps.setString(5, s.getDescription());
        ps.setString(6,"1");
        
        ps.executeUpdate();
        
        ps.close();
    }
    
    public void deleteService(Service s) throws SQLException{
        String sql = "DELETE FROM tblservice WHERE ID = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1,s.getID());
        ps.executeUpdate();
        ps.close();
    }

}
