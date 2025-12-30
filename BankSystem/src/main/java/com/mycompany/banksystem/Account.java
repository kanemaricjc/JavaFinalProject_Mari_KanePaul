/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.banksystem;

/**
 *
 * @author Acer
 */
public class Account {
    
    private String username;
    private String password;
    private double balance;
    
    public Account(String username, String password, double balance) {
        
        this.username = username;
        this.password = password;
        this.balance = balance;
        
    }
    
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }
    
    public boolean withdraw (double amount) {
        if (amount <= balance ) {
            balance -= amount;
            return true;
        }
        return false;
    }
    
    public String getPassword() {
        return password;
    }
    
    public String getUsername() {
        return username;
    }
    
    public double getBalance () {
        return balance;
    }
    
}
