/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Vector;

/**
 *
 * @author duynn
 */
public class Client implements Serializable{
    private int ID;
    private String name;
    private String address;
    private String mail;
    private String phoneNumber;
    private String note;
    private boolean isActive;

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public Client() {
        super();
    }

    public Client(String name, String address, String mail, 
            String phoneNumber, String note) {
        super();
        this.name = name;
        this.address = address;
        this.mail = mail;
        this.phoneNumber = phoneNumber;
        this.note = note;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Object[] toObject() {
        return new Object[]{
            ID, name, phoneNumber,address,mail,note
        };
    }

    @Override
    public String toString() {
        return "Client{" + "ID=" + ID + ", name=" + name + ", address=" + address + ", mail=" + mail + ", phoneNumber=" + phoneNumber + ", note=" + note + ", isActive=" + isActive + '}';
    }
    
    
    
    
}
