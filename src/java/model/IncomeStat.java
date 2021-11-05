/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;


/**
 *
 * @author duynn
 */
public class IncomeStat implements Serializable{
    private float income ;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String mode;
    
    public IncomeStat() {
        super();
        income = 0;
        mode ="Thang";
    }

    public IncomeStat(float income, LocalDateTime startDate, LocalDateTime endDate, String mode ) {
        this.income = income;
        this.startDate = startDate;
        this.endDate = endDate;
        this.mode = mode;
    }

    public float getIncome() {
        return income;
    }

    public void setIncome(float income) {
        this.income = income;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    @Override
    public String toString() {
        return "IncomeStat{" + "income=" + income + ", startDate=" + startDate + ", endDate=" + endDate + ", mode=" + mode + '}';
    }
    
    public Object[] toObject(int stt){
        String tenThang = startDate.getMonthValue() + "/" + startDate.getYear();
        return new Object[]{
            stt, tenThang, income
        };
    }
    public static Comparator<IncomeStat> MonthComp = new Comparator<IncomeStat>() {
        @Override
        public int compare(IncomeStat o1, IncomeStat o2) {
            int t = o1.getStartDate().getYear() - o2.getStartDate().getYear();
            if(t==0)
                return o1.getStartDate().getMonthValue() - o2.getStartDate().getMonthValue();
            else
                return t;
        };
    };
}
