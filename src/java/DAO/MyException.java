/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author duynn
 */
public class MyException extends Exception{
    private String msg;

    public MyException() {
        super();
    }
    

    public MyException(String msg) {
        super(msg);
        this.msg = msg;
    }
    
}
