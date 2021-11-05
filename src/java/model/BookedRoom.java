/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author duynn
 */
public class BookedRoom implements Serializable{
    private int ID;
    private LocalDateTime checkin;
    private LocalDateTime checkout;
    private float amount;
    private float pricePerHour;
    private float totalPrice;
    private String note;
    private Room room;
    private List<UsedService> listUsedService;
    private List<BookedStaff> listHiredStaff;
    private boolean isCheckin;

    public boolean isIsCheckin() {
        return isCheckin;
    }

    public void setIsCheckin(boolean isCheckin) {
        this.isCheckin = isCheckin;
    }

    public BookedRoom() {
        super();
    }

    public BookedRoom(LocalDateTime checkin, LocalDateTime checkout, float amount, 
            float pricePerHour, float totalPrice, String note, Room room,
            List<UsedService> listUsedService, 
            List<BookedStaff> listHiredStaff) {
        super();
        this.checkin = checkin;
        this.checkout = checkout;
        this.amount = amount;
        this.pricePerHour = pricePerHour;
        this.totalPrice = totalPrice;
        this.note = note;
        this.room = room;
        this.listUsedService = listUsedService;
        this.listHiredStaff = listHiredStaff;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public LocalDateTime getCheckin() {
        return checkin;
    }

    public void setCheckin(LocalDateTime checkin) {
        this.checkin = checkin;
    }

    public LocalDateTime getCheckout() {
        return checkout;
    }

    public void setCheckout(LocalDateTime checkout) {
        this.checkout = checkout;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(float pricePerHour) {
        this.pricePerHour = pricePerHour;
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

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public List<UsedService> getListUsedService() {
        return listUsedService;
    }

    public void setListUsedService(List<UsedService> listUsedService) {
        this.listUsedService = listUsedService;
    }

    public List<BookedStaff> getListHiredStaff() {
        return listHiredStaff;
    }

    public void setListHiredStaff(List<BookedStaff> listHiredStaff) {
        this.listHiredStaff = listHiredStaff;
    }
    
    
    
    
}
