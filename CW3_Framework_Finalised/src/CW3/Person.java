// NAME: XINXIN FAN 
// ID: 2039125
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CW3;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import javax.imageio.ImageIO;

/**
 *
 * @author andrew.abel
 */
public class Person {

    // Instance variables
    private String firstName;
    private String familyName;
    private String nickname;
    private int reward;
    private String nationality;
    private String idCode;
    private String crimes;
    private LocalDate dob;
    private Image photo;

    public Person(String[] info) {
        // Constructor to take a String array and assign it to instance variables

        // Complete this method
        this.firstName = info[0];
        this.familyName = info[1];
        this.nickname = info[2];
        this.reward = Integer.parseInt(info[3]);
        this.nationality = info[4];
        this.idCode = info[5];
        this.crimes = info[6];
        this.dob = convertDateOfBirth(info[7]);
        this.photo = readImage(info[8]);

    }

    public Person(String line) throws ArrayIndexOutOfBoundsException, IllegalArgumentException {
        // Constructor to take a single string, the entire line of a file, and assign
        // it to instance variables
        String[] info = line.split(",");
        
        this.firstName = info[0];
        this.familyName = info[1];
        this.nickname = info[2];
        this.reward = Integer.parseInt(info[3]);
        this.nationality = info[4];
        this.idCode = info[5];
        this.crimes = info[6];
        this.dob = convertDateOfBirth(info[7]);
        this.photo = readImage(info[8]);
        // Complete this method
    }

    public BufferedImage readImage(String filename) {
        // Read an image from a file and return a Buffered image

        // Complete this method
        BufferedImage image = null;
        try{
            image = ImageIO.read(new File(filename));
        }
        catch(IOException e){
            System.out.println("read image error");
        }
        
        // Return temporary value, change this
        return image;
    }

    public LocalDate convertDateOfBirth(String inputDate) {
        // Take a date input String and convert to a local date
        // If the date is an incorrect value, assign a default value of 1/1/1991

        // Complete this method
        LocalDate DoB = null; 
        try{
        String[]dates = inputDate.split("/");
        int day = Integer.parseInt(dates[0]);
        int month = Integer.parseInt(dates[1]);
        int year = Integer.parseInt(dates[2]);
        DoB = LocalDate.of(year, month, day);
        }
        catch(DateTimeException e){
            DoB = LocalDate.of(1991, 1, 1);
        }
        // Return temporary value, change this
        return DoB;
    }

    public int getAgeinYears() {
        // return the age of the person in years

        // Complete this method
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public String toString() {
        // Output method as String

        // Do not change!
        String out = "***\n" + getIdCode() + ", " + getFirstName() + " "
                + getFamilyName() + ", Reward: " + getReward() + ", " + getNationality()
                + ", " + getDob().toString() + ", " + getNickname() + ", " + getCrimes();

        return out;
    }

    // All getters are completed, do not change
    public String getFirstName() {

        return firstName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public int getReward() {
        return reward;
    }

    public String getNationality() {
        return nationality;
    }

    public String getIdCode() {
        return idCode;
    }

    public LocalDate getDob() {
        return dob;
    }

    public Image getPhoto() {
        return photo;
    }

    public String getNickname() {
        return nickname;
    }

    public String getCrimes() {
        return crimes;
    }

}
