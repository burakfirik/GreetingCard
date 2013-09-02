/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package greet.gui;

import greet.Greet;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import static greet.GreetSettings.*;
import greet.image.GreetImage;
import greet.image.GreetShape;
import greet.state.GreetPose;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;
/**
 *
 * @author burak
 */
public class TemplateLeftCanvas extends JPanel {
    
    int flag=0;
    
    GreetImage permenantTemp;
    
    private BufferedImage image;
    private int x;
    private int y;
   
    private GreetPose pose;
    
    
    public TemplateLeftCanvas(String name){
        //Default Size
        this.setSize(TEMP_PANEL_WIDTH,TEMP_PANEL_HEIGHT);
            try{
                image=ImageIO.read(new File(name));
                x=(this.getWidth()-image.getWidth())/2;
                y=(this.getHeight()-image.getHeight())/2;
            }catch(IOException ex){
                System.out.println("Did not read the image");
            }
           
    }
    
    /*
    public void loadImage(String name,int flag){
        try{
                image=ImageIO.read(new File(name));
                x=(this.getWidth()-image.getWidth())/2;
                y=(this.getHeight()-image.getHeight())/2;
            }catch(IOException ex){
                System.out.println("Did not read the image");
            }
        repaint();
    }
    
    */
    
    
   
   
    
    public TemplateLeftCanvas(){
        
    }
    
    public void loadImage(String name){
        try{
                image=ImageIO.read(new File(name));
               // x=(this.getWidth()-image.getWidth())/2;
               // y=(this.getHeight()-image.getHeight())/2;
            }catch(IOException ex){
                System.out.println("Did not read the image");
            }
        
        
        repaint();
    }
    
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        //g.drawImage(image, x, y, this);
       
            g.drawImage(image, 0, y, this);
            
        
    }
    
     
   
    
}
