/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.banksystem;
import java.util.*;
import javax.swing.*;
/**
 *
 * @author Acer
 */
public class BankSystem {

    public static void main(String[] args) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);     //initialization of initial GUI for login
            }
        });
        
    }
}