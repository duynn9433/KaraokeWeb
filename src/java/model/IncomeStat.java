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
    private String thang;
    
    public IncomeStat() {
        super();
        income = 0;
    }

    public IncomeStat(float income, String thang) {
        this.income = income;
        this.thang = thang;
    }

    public float getIncome() {
        return income;
    }

    public void setIncome(float income) {
        this.income = income;
    }

    public String getThang() {
        return thang;
    }

    public void setThang(String thang) {
        this.thang = thang;
    }

    @Override
    public String toString() {
        return "IncomeStat{" + "income=" + income + ", thang=" + thang + '}';
    }
    
}
