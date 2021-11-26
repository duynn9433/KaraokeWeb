/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import model.*;
/**
 *
 * @author Truong
 */
public class TimeFrameStatDAO extends DAO{

    public TimeFrameStatDAO() {
        super();
    }
    public List<TimeFrameStat> getListTimeFrame(LocalDateTime startDay, LocalDateTime endDay){
       List<TimeFrameStat> rs = new ArrayList<>();
    /*    endtime.setDate(endtime.getDate()+1);
  

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String sql1 = "Drop view if exists tbltemp;";
        String sql2 = " create view tbltemp as (select a.id,a.price from tblbookedroom as a union select a.id, sum(b.price ) as stf from tblbookedroom as a, tblusedstaff as b where a.id= b.idbookedroom group by id union select a.id, sum(b.price*b.count ) as sv from tblbookedroom as a, tblusedservice as b where a.id= b.idbookedroom group by id);";
        String sql3  = "select a.id, a.checkin as checkin,a.checkout as checkout,b.doanhthu as doanhthu from tblbookedroom as a, (select id, sum(price) as doanhthu from tbltemp group by id) as b where a.id=b.id and a.checkin>=? and a.checkout<=?;";
    
        try {
            PreparedStatement ps1 = con.prepareStatement(sql1);
            ps1.execute();
            PreparedStatement ps2 = con.prepareStatement(sql2);
            ps2.execute();
            PreparedStatement ps3 = con.prepareStatement(sql3);
            ps3.setString(1,sdf.format(starttime));
            ps3.setString(2,sdf.format(endtime));
            ResultSet kq= ps3.executeQuery();
            
            while(kq.next()){
                
                String in = kq.getTimestamp("checkin").toString();
                
                String out = kq.getTimestamp("checkout").toString();
                
                int doanhthu = kq.getInt("doanhthu");
            // pack
               in = in.substring(11,19);
               out = out.substring(11,19);
               int k=1;
               String in2 = in;
               String out2 = out;
               if(in.compareTo(out)>0){
                    in2="00:00:00";
                    out2="24:00:00";
                   k=2;
               }
               while(k-->0){
                   String min = in;
                   String mout = out2;
                   for(int i=0;i<24;i++){
                       String timeframe = rs.get(i).getTime().toString();
                       if(timeframe.compareTo(min)>=0 && timeframe.compareTo(mout)<0){
                           TimeFrameStat tfs = rs.get(i);
                           tfs.setClientcount(tfs.getClientcount()+1);
                           tfs.setTotalamount(tfs.getTotalamount()+doanhthu);
                           rs.set(i, tfs);
                        }
                    }
                   in=in2;
                   out2 = out;
               }
            }
            Collections.sort(rs,new cmp_stat());
        } catch (SQLException ex) {
            Logger.getLogger(TimeFrameStatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        return rs;
    }
}
