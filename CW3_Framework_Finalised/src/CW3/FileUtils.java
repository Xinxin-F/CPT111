// NAME: XINXIN FAN 
// ID: 2039125
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CW3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author andrew.abel
 */
public class FileUtils {
       
    public static List<Person> readFile(String filename) {

        // Receive a filename String, and read a file, storing all person 
        // information in an arraylist, handling any file reading errors and
        // returning an array list
        
        // Complete this method
        List<Person> PeopleList = new ArrayList<>();
        
        Path path = Paths.get(filename);
        //int i = 0; 
        try {
            BufferedReader reader = Files.newBufferedReader(path);
            String lineContent = reader.readLine();
            while (lineContent != null) {
                // there is space in 'Erick' row, so replace space with nothing
                lineContent = lineContent.replaceAll(", ", ",");
                System.out.println(lineContent);
                Person p = new Person(lineContent);
                
            // Add to arraylist
                PeopleList.add(p);
                //i++; 
                lineContent = reader.readLine();
            }
            //reader.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
        
        // temporary return line, change this
        return PeopleList;
    }
}
