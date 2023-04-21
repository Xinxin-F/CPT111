// NAME: XINXIN FAN 
// ID: 2039125

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CW3;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JFrame;

/**
 *
 * @author ANDREW.ABEL
 */
public class Menu {

    // ArrayList for id
    List<Person> ids = new ArrayList<>();
    
    public void mainMenu(String fileName) {
        
        // Display a welcome message, get option and input
        
        // Do not Change
        
        System.out.println("\nCriminal Database Menu \n");
        System.out.println("Choose an option: ");
        System.out.println("1. Load Crime File");
        System.out.println("2. List all Criminals");
        System.out.println("3. Search for a Criminal");
        System.out.println("4. Generate a Wanted Poster");
        System.out.println("5. Generate Stats");
        System.out.println("6. Check Criminal for ID Fraud");
        System.out.println("7. Exit");

        // Get input from method
        int command = getIntInput();
        System.out.println("You entered " + command);
        switch (command) {
            case 1:
                System.out.println("Load Database");
                ids = loadFiles(fileName, ids);
                break;
            case 2:
                System.out.println("Listing all Criminals");
                listPeople(ids);
                break;
            case 3:
                System.out.println("Search for a Criminal with partial match");
                String input = getStringInput("Please input a partial match for the ID file");      
                searchIDs(ids, input);
                break;
            case 4:
                System.out.println("Generate a Wanted Poster");
                input = getStringInput("Please input a exact match for the ID code"); 
                
                generatePoster(ids, input);
                break;
            case 5:
                System.out.println("\nGenerate Stats");
                generateStats(ids);
                break;
            case 6:
                System.out.println("Check for Fraud");
                input = getStringInput("Please input a exact match for the ID code");                
                // Get a boolean of whether valid
                boolean valid = checkFraud(ids, input);
                if(valid){
                    System.out.println("Valid ID");
                } else {
                    System.out.println("Invalid ID or not found in system");
                }
                
                break;
            case 7:

                System.exit(0);
                break;
            default:
                System.out.println("Invalid Choice, please re-enter between 1 and 6");
            //  showMenu();
        }
        // Loop through menus again
        mainMenu(fileName);
    }
    
    
    private List<Person> loadFiles(String fileName, List<Person> ids) {
        // Method to handle loading of file and writing into array
        // Completed method, do not change
        
        System.out.println("Loading file " + fileName);
        ids = FileUtils.readFile(fileName);
        return ids;
    }
    
    
    // Generate Stats
    // This method is pre-completed
    private void generateStats(List<Person> ids) {
        // Method to generate stats
        // Completed method, do not change
        
        System.out.println("Number of criminals in System: " + statsIdNumber(ids));
        System.out.println("Number of Nationalities in System: " + statsNatNumber(ids));
        System.out.println("Average age of people: " + statsAvgAge(ids));
        System.out.println("Average reward level: " + avgReward(ids));
    }
    

    
    public static int getIntInput() {
        // Method to check for input
        // Completed method, do not change
        
        Scanner kb = new Scanner(System.in);
        String input = kb.nextLine();

        int cmd = 6;
        try {
            cmd = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            // If not a number
            System.out.println("Invalid number option chosen");
            cmd = getIntInput();
        }

        return cmd;
    }
    
    public static String getStringInput(String inputMessage) {
        // Method to receive a message to display, and get a string input
        // from keyboard
        // Completed method, do not change
        
        Scanner kb = new Scanner(System.in);
        
        System.out.println(inputMessage);
        String input = kb.nextLine();
        return input;
    }
   
    

    
    private void listPeople(List<Person> ids) {
        // Display list of Peoople using toString method
        
        // Complete this method
        //System.out.println("test");--test the object is working
        if (ids.isEmpty()) {
            System.out.println("\nPlease load the file before listing");
        } 
        else {
            for (int i = 0; i < ids.size(); i++) {
                //only prints the values when the content is not empty
                //if (stocks[i]!=null){--nothing wrong, just another method
                try {
                    System.out.println(ids.get(i).toString());
                } catch (NullPointerException e) {
                    //do nothing 
                }
            }
            //to tell the user the list is fully printed
            //helper
            //System.out.println("List Completed");
        }

    }

    
    private void searchIDs(List<Person> ids, String input) {
        // Method to search for people and display using toString
        // Search by full or partial match of first name, family name, nationality,
        // id or nickname, not case sensitive
        
        // Complete this method
        // convert to lower case
        String lowInput = input.toLowerCase();
        List<Person> found = new ArrayList<>();
        
        if (ids.isEmpty()){
            System.out.println("\nPlease load the file before searching");
        }
        else {
             for (int i = 0; i<ids.size(); i++){
            // need to apply to String to convert Person to String 
            // so that contains can be used
            // convert to lower case
            if ((ids.get(i).toString()).toLowerCase().contains(lowInput)){
               found.add(ids.get(i));
            }
        }
        for (int i = 0; i<found.size(); i++){
            System.out.println(found.get(i).toString());
        }
        }
       
    }

    private void generatePoster(List<Person> ids, String input) {

        // Method will receive a String input and a list of persons.  Will
        // Look for an exact match with the id and if a match is found, will
        // Display a wanted poster
        
        // Complete this method
        
        // exception could be not found
        //get the index of found 
        if (ids.isEmpty()) {
            System.out.println("\nPlease load the file before generating poster");
        } 
        else {
            int index = -1;
            try {
                for (int i = 0; i < ids.size(); i++) {
                    if (ids.get(i).getIdCode().equals(input)) {
                        index = i;
                        //System.out.println(index);
                    }
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Please re-enter an exact match for ID: ");
            }

            // create a Panel
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setTitle("Wanted Poster");
            frame.setSize(500, 700);
            //the size of the color
            //try{
            if (index != -1) {
                ImagePanel draw = new ImagePanel(ids.get(index), 0, 0, 500, 700);
                //}
                //catch(RuntimeException e){
                //System.out.println("Invalid ID code, please re-enter a valid id code");
                //generatePoster(ids, input);
                // }
                //add the color to the frame
                frame.add(draw);
                //make the frame visible
                frame.setVisible(true);
            } else {
                System.out.println("Incorrect id input, please try again");
            }
        }
    }
        
        // create a Panel
        /*JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Wanted");
        frame.setSize(600, 600);
        //the size of the color
        ImagePanel draw = new ImagePanel(ids.get(index), 0, 0, 600, 600);
        
        //add the color to the frame
        frame.add(draw);
        //make the frame visible
        frame.setVisible(true);
        */
        

    private int statsIdNumber(List<Person> ids){
        // method to calculate the number of ids in the system
        
        if (ids.isEmpty()){
            System.out.println("Empty lists. Please load the file first");
        }
        // complete this method
        int numPeople = ids.size(); 
        // Return temporary value, change this
        return numPeople;
    }
    
    private int statsNatNumber(List<Person> ids){
        // Method to return the number of nationalities present in the system
        
        // Complete this method
        int number = 0;
        List<String> Nationality = new ArrayList<>();
        //if (ids.size() == 0) {
        //System.out.println("\nPlease load the file before searching");
        //} 
        //else {
        //List<String> Nationality = new ArrayList();
        if (ids.isEmpty()) {
            number = 0;
        } 
        else {
            String nation = ids.get(0).getNationality();
            Nationality.add(nation);
            for (int i = 0; i < ids.size(); i++) {
                // need to use 'equals' for comparison, '==' cannot work
                //if ((ids.get(i).getNationality()).equals(nation)) {
                if (!(Nationality.contains(ids.get(i).getNationality()))){
                    Nationality.add(ids.get(i).getNationality());
                }
            }
            number = Nationality.size();
            
            }
        
        //}
        // Return temporary value, change this
        return number;
    }
    
    private double statsAvgAge(List<Person> criminals){
        // Calculate age of every person in the system and return as double
        double total = 0;
        double avg = 0; 
        
        for (int i = 0; i< criminals.size(); i++){
            total = total + criminals.get(i).getAgeinYears();
        }
        avg = total/criminals.size(); 
        
        // Complete this method
        
        // Return temporary value, change this
        return avg;
    }
    
    private double avgReward(List<Person> ids){
        // Method to calculate average reward amount of all people in system
        
        // Complete this method
        double total = 0;
        double avg = 0; 
        for (int i = 0; i< ids.size(); i++){
            total = total + ids.get(i).getReward();
        }
        avg = total/ids.size(); 
        
        // Return temporary value, change this
        return avg;
    }
    
    private boolean checkFraud(List<Person> ids, String input){
        // Method to check if an ID is valid or invalid.  Receives an ID string 
        // and a list of people.  Valid ID if:
        
        // when the user inputs an ID code, a matching ID is found in the system
        // The ID code is 8 characters in length
        // The code begins with an “A”, “B”, or “C” (case sensitive)
        // The third character matches the last number of their year of birth
        // The final 2 characters are a checksum, and should add up to 7
        
        // Complete this method
        //check the inputs starts with ABC
        
        if (!(input.startsWith("A") || input.startsWith("B") || input.startsWith("C"))){
            return false;
        }
        
        // check exists in the system
        List<String> id = new ArrayList<>();
        for (int i = 0; i< ids.size(); i++){
            String idCode = ids.get(i).getIdCode();
            id.add(idCode);
        }
        if (!id.contains(input)){
            return false; 
        }
        
        // check add up to 7
        // might have error/exception as the last two may not be number
        
        int last = Integer.parseInt(input.substring(input.length()-1));
        int before = Integer.parseInt(input.substring(input.length()-2, input.length()-1));
        //System.out.println(last + " " + before);
        if (last + before != 7){
            return false; 
        }
        
        // check the input string length = 8
        
        if (input.length() != 8){
            return false; 
        }
        
        //check third = last number year
        
        int loc = -1; 
        String dob = "";
        for (int i = 0; i<id.size(); i++){
            loc = id.indexOf(input);
        }
        dob = String.valueOf((ids.get(loc)).getDob()); 
        int third = Integer.parseInt(input.substring(2, 3));
        int yearlast = Integer.parseInt(dob.substring(3, 4));
        //System.out.println(third + " " + yearlast);
        if (third != yearlast){
            return false;
        }
        
        // Return temporary value, change this
        
        return true;   
        
    }

}
