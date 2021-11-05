/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author duynn
 */
public class Bill implements Serializable{
    private int ID;
    private LocalDate paymentDate;
    private float paymentAmount;
    private String paymentType;
    private String note;
    private List<BookedRoom> listBookedRoom; 
    private User user;
    private Client client;

    public Bill() {
        super();
    }

    public Bill(LocalDate paymentDate, float paymentAmount, String paymentType, 
            String note, ArrayList<BookedRoom> listBookedRoom, User user, 
            Client client) {
        super();
        this.paymentDate = paymentDate;
        this.paymentAmount = paymentAmount;
        this.paymentType = paymentType;
        this.note = note;
        this.listBookedRoom = listBookedRoom;
        this.user = user;
        this.client = client;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public float getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(float paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<BookedRoom> getListBookedRoom() {
        return listBookedRoom;
    }

    public void setListBookedRoom(ArrayList<BookedRoom> listBookedRoom) {
        this.listBookedRoom = listBookedRoom;
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

    @Override
    public String toString() {
        return "Bill{" + "ID=" + ID + ", paymentDate=" + paymentDate + 
                ", paymentAmount=" + paymentAmount + ", paymentType=" + paymentType 
                + ", note=" + note + ", listBookedRoom=" + listBookedRoom 
                + ", user=" + user + ", client=" + client + '}';
    }
    
    
    
    public Object[] toObject(int stt, int indexBookedRoom){
        int i = indexBookedRoom;
        BookedRoom br = listBookedRoom.get(indexBookedRoom);
        
        LocalDateTime sd = br.getCheckout();
        LocalDateTime ed = br.getCheckin();
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        
        return new Object[]{
            stt, this.ID , this.client.getName(),
            dtf.format(br.getCheckin()),dtf.format(br.getCheckout()),
            //paymentAmount
            br.getTotalPrice()
        };
    }
    
}
