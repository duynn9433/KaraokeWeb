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
public class KaraokeBar implements Serializable{
    private int ID;
    private String name;
    private String address;
    private String description;

    public KaraokeBar() {
    }

    public KaraokeBar(int ID, String name, String address, String description) {
        this.ID = ID;
        this.name = name;
        this.address = address;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "KaraokeBar{" + "ID=" + ID + ", name=" + name + ", address=" + address + ", description=" + description + '}';
    }
    
}
