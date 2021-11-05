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
public class BookedStaff implements Serializable{
    private int ID;
    private int rating;
    private User user;

    public BookedStaff() {
        super();
    }

    public BookedStaff(float tip, int rating, float salaryPerHour, 
            float totalSalary, User user) {
        super();
        this.rating = rating;
        this.user = user;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
}
