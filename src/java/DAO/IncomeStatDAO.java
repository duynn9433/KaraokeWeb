/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import static DAO.DAO.con;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import model.IncomeStat;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 *
 * @author duynn
 */
public class IncomeStatDAO extends DAO{
    public IncomeStatDAO(){
        super();
    }
    
    public ArrayList<IncomeStat> getIncomeStat(LocalDateTime startDate, LocalDateTime endDate) {
        ArrayList<IncomeStat> res = new ArrayList<>();
        //sinh doi tuong IncomeStat voi startDay - endDay la dau cuoi thang
        LocalDate date;
        for (date = startDate.toLocalDate(); date.isBefore(endDate.toLocalDate());) {
            IncomeStat is = new IncomeStat();
            is.setStartDate(date.atTime(0, 0, 0));
            int endDayOfMonth ;
            int month = date.getMonthValue();
            if (month == 1 || month == 3 || month == 5 || month == 7
                    || month == 8 || month == 10 || month == 12) {
                endDayOfMonth = 31;
            } else if (month == 4 || month == 6 || month == 9 || month == 11) {
                endDayOfMonth = 30;
            } else {
                endDayOfMonth = date.isLeapYear() ? 29 : 28;
            }
            
            if (date.plusDays(endDayOfMonth - date.getDayOfMonth()).isBefore(endDate.toLocalDate())) {
                date = date.plusDays(endDayOfMonth - date.getDayOfMonth());
                is.setEndDate(date.atTime(23, 59, 59));
                res.add(is);
                date = date.plusDays(1);
            } else {
                break;
            }        
        }
        //System.out.println(date);
        if(date.isBefore(endDate.toLocalDate())){
            IncomeStat is = new IncomeStat();
            is.setStartDate(date.atTime(0, 0, 0));
            is.setEndDate(endDate);
            res.add(is);
        }
        
        //lay du lieu income trong database
        String sql = "SELECT b.id, min(br.checkin) as minin, max(br.checkout) as maxout, \n" +
"(coalesce((SELECT sum(timestampdiff(hour,br.checkin, br.checkout)*pricePerHour) + \n" +
"((SELECT sum(us.amount*us.pricePerUnit) \n" +
"FROM tblbookedroom br, tblusedservice us \n" +
"WHERE br.id = us.bookedRoomID and b.id = br.billID GROUP BY br.billID))),\n" +
"\n" +
"(SELECT sum(timestampdiff(hour,br.checkin, br.checkout)*pricePerHour) \n" +
"FROM tblbookedroom br WHERE b.id = br.billID  GROUP BY br.billID))) as income \n" +
"FROM tblbill b, tblbookedroom br\n" +
"WHERE b.id = br.billID  \n" +
"group by b.id\n" +
"having minin > ?  and maxout < ?  ";
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        
        for(int i=0; i<res.size();i++){
            try{
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, dtf.format(res.get(i).getStartDate()));
                ps.setString(2, dtf.format(res.get(i).getEndDate()));
                
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    res.get(i).setIncome(res.get(i).getIncome()+rs.getFloat("income"));
                }
            }catch(SQLException e){
            }
        }
        //xoa cac phan tu co income = 0
        for(int i = 0; i<res.size();i++){
            if(res.get(i).getIncome() == 0){
                res.remove(i);
                i--;
            }
        }
        
        return res;
    }
}
