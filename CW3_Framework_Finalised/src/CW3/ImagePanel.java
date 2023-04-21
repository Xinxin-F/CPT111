// XINXIN FAN 
// ID: 2039125
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CW3;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author andrew.abel
 */
public class ImagePanel extends JPanel {

    private final Image myImage;
    private final int myX, myY;
    private final int myWidth, myHeight;
    private final Person id;
    
    public ImagePanel(Person dispID,
                        int myX, 
                        int myY, 
                        int myWidth, 
                        int myHeight) {
        
        // Constructor for image panel
        
        // Complete this method
        super();
        this.myX = myX;
        this.myY = myY;
        this.myWidth = myWidth;
        this.myHeight = myHeight;
        this.id = dispID;
        this.myImage = id.getPhoto();
        //Color c2 = new Color(127, 97, 29);  
        setBackground(Color.RED);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Draw the wanted poster
        
        // Complete this method
        //g.setColor(Color.black);
        //another layer
        //Color c1 = new Color(203, 178, 128);  
        //g.setColor(c1);

        g.setColor(Color.BLACK);
        g.fillRect(myX +15, myY +15, myWidth-30, myHeight-45);
        
        //background for image
        g.setColor(Color.WHITE);
        g.fillRect(myWidth/3, myHeight/4, myWidth/3, myHeight/3);
        
        // need to put image above the background
        // image
        g.drawImage(myImage, myWidth/3, myHeight/4, myWidth/3, myHeight/3, this);
        
        // title
        Font title = new Font ("Dialog", Font.BOLD, 95);
        g.setFont(title);
        g.setColor(Color.RED);
        FontMetrics tt = g.getFontMetrics();
        String Title = "WANTED";
        // String input, position
        g.drawString(Title, (myWidth-tt.stringWidth(Title))/2, myHeight/7);
        
        // offense
        
        int titleX = myX +20;
        int titleY = myHeight/8; 
        String[]crime = id.getCrimes().split(" ");
        if (crime.length < 5) {
            Font offense = new Font("Courier 10 Pitch", Font.BOLD, 30);
            g.setFont(offense);
            g.setColor(Color.WHITE);
            FontMetrics fm = g.getFontMetrics();
            String crimeName= id.getCrimes(); 
            g.drawString(crimeName, myWidth/2-(fm.stringWidth(crimeName)/2), titleY + 45);
            //g.drawString(id.getCrimes(), titleX + 30 , titleY + 45);
        }
        else{
            String before = "";
            String after = ""; 
            for (int i = 0; i<3; i++){
                before = before + crime[i] + " "; 
            }
            for (int j = 3; j<crime.length; j++){
                after = after + crime[j]+ " ";
            }
            Font offense = new Font("Courier 10 Pitch", Font.BOLD, 25);
            g.setFont(offense);
            g.setColor(Color.WHITE);
            FontMetrics fm = g.getFontMetrics();
            g.drawString(before, myWidth/2-(fm.stringWidth(before)/2) , titleY + 45);
            g.drawString(after, myWidth/2-(fm.stringWidth(after)/2) + 5, titleY + 75);
       }
        // name
        Font name = new Font ("Serif", Font.BOLD, 30);
        g.setFont(name);
        g.setColor(Color.WHITE);
        String fullName = id.getFirstName() + " "+ id.getFamilyName(); 
        //FontMetrics fm = g.getFontMetrics();
        FontMetrics fm = g.getFontMetrics();
        int textwidth = fm.stringWidth(fullName);
        g.drawString(fullName, (myWidth-textwidth)/2, 3*myHeight/5 +15);
        
        /*
        Font name = new Font ("Serif", Font.BOLD, 30);
        g.setFont(name);
        g.setColor(Color.WHITE);
        g.drawString(id.getFirstName() + " "+ id.getFamilyName(), myWidth/3, 3*myHeight/5 +15);
*/
        //int nameX = myWidth/3;
        int nameY = 3*myHeight/5 +15;
        
        
        // nickname
        Font nickname = new Font ("Serif", Font.BOLD, 25);
        g.setFont(nickname);
        String nick = "\"" + id.getNickname() + "\""; 
        FontMetrics nk = g.getFontMetrics();
        g.drawString(nick, (myWidth-nk.stringWidth(nick))/2, nameY + 30);
        //g.drawString("\"" + id.getNickname() + "\"" , nameX + 20, nameY + 30);
        
        
        // reward
        Font reward = new Font ("Century Schoolbook L", Font.BOLD | Font.ITALIC, 30);
        g.setFont(reward);
        g.setColor(Color.RED);
        FontMetrics rw = g.getFontMetrics();
        String REWARD = "REWARD: $"+ String.valueOf(id.getReward()); 
        g.drawString(REWARD, (myWidth-rw.stringWidth(REWARD))/2, nameY + 80);
        
        // age & nationality
        Font age = new Font ("DejaVu Sans", Font.BOLD, 25);
        g.setFont(age);
        g.setColor(Color.WHITE);
        FontMetrics ag = g.getFontMetrics();
        String AGE = "Age: "+ String.valueOf(id.getAgeinYears()) + "  "
                + "Nationality : " + id.getNationality(); 
        g.drawString(AGE, (myWidth-ag.stringWidth(AGE))/2, nameY + 120);
        
        // idCode
        Font idCode = new Font ("DejaVu Sans", Font.BOLD, 25);
        g.setFont(idCode);
        g.setColor(Color.WHITE);
        FontMetrics cd = g.getFontMetrics();
        String IDCODE = "Id Code: "+ id.getIdCode();
        g.drawString(IDCODE, (myWidth-cd.stringWidth(IDCODE))/2 , nameY + 160); 
        //g.drawString("Id Code: "+ id.getIdCode(), nameX - 30, nameY + 160);
        
        // background for caution
        g.setColor(Color.RED);
        //g.fillRect(myWidth/3 - 105, nameY + 175, 4*myWidth/5 - 15, myHeight/20);
        g.fillRect(myWidth/2-(9*myWidth/10-10)/2, nameY + 175, 9*myWidth/10-10, myHeight/20);
        
        // caution
        Font caution = new Font ("Serif", Font.BOLD, 26);
        g.setFont(caution);
        g.setColor(Color.WHITE);
        FontMetrics ct = g.getFontMetrics();
        String CAUTION = "APPROACH WITH CAUTION"; 
        g.drawString(CAUTION, (myWidth-ct.stringWidth(CAUTION))/2, nameY + 200);
        //g.drawString("APPROACH WITH CAUTION", nameX - 100, nameY + 200);
    }
    
    // Getters, do not need to change
     @Override
    public int getX() {
        return myX;
    }

    @Override
    public int getY() {
        return myY;
    }

    @Override
    public int getWidth() {
        return myWidth;
    }

    @Override
    public int getHeight() {
        return myHeight;

    }
}
