/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author duynn
 */
public class Booking implements Serializable{
    private int ID;
    private LocalDateTime bookDate;
    private float saleOff;
    private String note;
    private User user;
    private Client client;
    private List<BookedRoom> listBookedRoom;
    private boolean isCheckin;
    private boolean isCheckout;

    public boolean isIsCheckin() {
        return isCheckin;
    }

    public void setIsCheckin(boolean isCheckin) {
        this.isCheckin = isCheckin;
    }

    public boolean isIsCheckout() {
        return isCheckout;
    }

    public void setIsCheckout(boolean isCheckout) {
        this.isCheckout = isCheckout;
    }

    @Override
    public String toString() {
        return "Booking{" + "ID=" + ID + ", bookDate=" + bookDate + ", saleOff=" + saleOff + ", note=" + note + ", user=" + user + ", client=" + client + ", listBookedRoom=" + listBookedRoom + ", isCheckin=" + isCheckin + ", isCheckout=" + isCheckout + '}';
    }

    public Booking(int ID, LocalDateTime bookDate, float saleOff, String note, User user, Client client, List<BookedRoom> listBookedRoom, boolean isCheckin, boolean isCheckout) {
        this.ID = ID;
        this.bookDate = bookDate;
        this.saleOff = saleOff;
        this.note = note;
        this.user = user;
        this.client = client;
        this.listBookedRoom = listBookedRoom;
        this.isCheckin = isCheckin;
        this.isCheckout = isCheckout;
    }
    

    public Booking() {
        super();
        isCheckin=false;
        isCheckout=false;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public LocalDateTime getBookDate() {
        return bookDate;
    }

    public void setBookDate(LocalDateTime bookDate) {
        this.bookDate = bookDate;
    }

    public float getSaleOff() {
        return saleOff;
    }

    public void setSaleOff(float saleOff) {
        this.saleOff = saleOff;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<BookedRoom> getListBookedRoom() {
        return listBookedRoom;
    }

    public void setListBookedRoom(List<BookedRoom> listBookedRoom) {
        this.listBookedRoom = listBookedRoom;
    }


    
    
}
