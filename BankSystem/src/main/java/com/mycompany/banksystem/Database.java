/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.banksystem;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
/**
 *
 * @author Acer
 */
public class Database {
    
    private String DB_FILE = "database.txt";  //creates/finds a file named database.txt.
    private HashMap<String, Account> userMap = new HashMap<>(); //creates a HashMap for automation of username:password:balance.
    
    
    public void loadData() {
    File file = new File(DB_FILE);  //Makes an instance of the file named DB_FILE which is, in this context, database.txt.
    if (!file.exists()) return; // Skip if first time running, which means the file is already made.

    try (Scanner input = new Scanner(file)) {     //We use new Scanner(file) here instead of Scanner(System.in) because we are reading the file, not the user's keyboard.
            while (input.hasNextLine()) {         //So basically, if the scanner finds that there is another line of text, it runs the loop.
                String line = input.nextLine();   //Gets all the text within that line of text and stores it in the variable called "line".
                String[] parts = line.split(":");   //This breaks the string whenever the Scanner finds that there is a colon(:) and stores that in the array called "parts".
                if (parts.length == 3) {            //This line checks if the file is in the correct format (username:password:balance), which are exactly 3 parts.
                    String user = parts[0];         //Stores the first string read in the line of text and assigns it to a variable called user, which will be our username.
                    String pass = parts[1];         //Stores the second string read in the line of text and assigns it to a variable called pass, which will be our password.
                    double bal = Double.parseDouble(parts[2]);  //For here, since the balance is initially a String when read, we convert it to a double using Double.parseDouble.
                    userMap.put(user, new Account(user, pass, bal));    //We create a new Account object with the user, pass and bal respectively. We then proceed to insert it into the HashMap called userMap, which was initialized in line 21 of this java file.
                }
            }
        } catch (IOException | NumberFormatException e) {   //IOException basically means "if the file is missing", and NuberFormatException means the balance is not a valid number.
            System.out.println("Error reading database: " + e.getMessage());
        }
    }
    
    public Account authenticate(String username, String password) { //This will be our authenticator that reads the current username and password.
        if (userMap.containsKey(username)) {   // If the username inserted into the text pane is present in the HashMap, we will proceed to line 46.
            Account acc = userMap.get(username);    //Takes the username given and finds the account of that username.
            if (acc.getPassword().equals(password)) { // Takes the password of the account and, if it matches, returns the username, pass, and bal of the account. Else it returns null.
                return acc;
            }
        }
        return null; //Runs if userMap did not contain the username or if password did not match.
    }
    
    public void saveAll() {
        
        try (PrintWriter labas = new PrintWriter(new BufferedWriter(new FileWriter(DB_FILE, false )))) {    //This is where I took a lot of time to do. So basically the FileWriter opens the DB_FILE, which is our database.txt.
                    //BufferedWriter is basically our modern-age "Flash the Speedster". It collects all the data in RAM and transfers it to our disk in one chunk, so basically BIG write speed. Currently, this is not necessary but soon, there will be more accounts and we will have to overwrite everything when updating an account so this is future-proof.
                    //Lastly, PrintWriter transforms our String and doubles (objects) into plain text strings so it can be input into the file.
            for (Account acc : userMap.values() ) {     //This line looks at the HashMap and grabs all the accounts inside. This is necessary so everything is overwritten.
                labas.println( acc.getUsername() + ":" + acc.getPassword() + ":" + acc.getBalance() );  //labas.println() is used because we edit what is inside the file.
            }                   //The format is username : password : balance.
            
        } catch (IOException e) {
            System.err.println("Error updating: " + e.getMessage());
        }
        
    }
    
    
    
}