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
public class Room implements Serializable {

    private int ID;
    private String size;
    private String type;
    private float pricePerHour;
    private String description;
    private boolean isActive;

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public Room() {
        super();
    }

    public Room(String size, String type, float pricePerHour, String description) {
        super();
        this.size = size;
        this.type = type;
        this.pricePerHour = pricePerHour;
        this.description = description;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(float pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object[] toObject() {
        return new Object[]{
                ID, size, type,pricePerHour,description
        };
    }

    @Override
    public String toString() {
        return "Room{" + "ID=" + ID + ", size=" + size + ", type=" + type + ", pricePerHour=" + pricePerHour + ", description=" + description + ", isActive=" + isActive + '}';
    }
    
}


