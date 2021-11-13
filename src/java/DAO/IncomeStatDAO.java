/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import static DAO.DAO.con;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import model.IncomeStat;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 *
 * @author duynn
 */
public class IncomeStatDAO extends DAO {

    public IncomeStatDAO() {
        super();
    }

    /**
     * @author nguyen ngoc duy
     */
    public List<IncomeStat> getIncomeStat(LocalDateTime startDate, LocalDateTime endDate) throws SQLException {
        List<IncomeStat> res = new ArrayList<>();
        //sinh doi tuong IncomeStat voi startDay - endDay la dau cuoi thang

        //lay du lieu income trong database
        String sql = "{call getIncomeStat(?,?)}";

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, dtf.format(startDate));
        ps.setString(2, dtf.format(endDate));

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            IncomeStat incomeStat = new IncomeStat();
            incomeStat.setIncome(Float.parseFloat(rs.getString("income")));
            incomeStat.setThang(rs.getString("Thoigian"));
            res.add(incomeStat);
        }

        return res;
    }
}
