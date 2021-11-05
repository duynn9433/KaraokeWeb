/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author duynn
 */
public class UsedService implements Serializable{
    private int ID;
    private int amount;
    private float pricePerUnit;
    private float totalPrice;
    private String note;
    private Service service;

    public UsedService() {
        super();
    }

    public UsedService(int amount, float pricePerUnit, float totalPrice, String note, Service service) {
        super();
        this.amount = amount;
        this.pricePerUnit = pricePerUnit;
        this.totalPrice = totalPrice;
        this.note = note;
        this.service = service;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public float getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(float pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
    
    
    
    
}
