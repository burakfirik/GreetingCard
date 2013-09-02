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
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTabbedPane;
/**
 *
 * @author burak
 */
public class TemplateCanvas extends JPanel  implements Serializable{
    
    int flag=0;
    
    GreetImage permenantTemp;
    
    private Image image;
    private int x;
    private int y;
   
    private GreetPose pose;
    
    
    
    
    public TemplateCanvas(String name){
        //Default Size
        this.setSize(TEMP_PANEL_WIDTH,TEMP_PANEL_HEIGHT);
            try{
                image=ImageIO.read(new File(name));
                x=(this.getWidth()-((BufferedImage)image).getWidth())/2;
                y=(this.getHeight()-((BufferedImage)image).getHeight())/2;
            }catch(IOException ex){
                System.out.println("Did not read the image");
            }
           
    }
    
    
    
    public TemplateCanvas(GreetPose poses){
        pose=poses;
        
        this.loadImage("img/9.jpg");
        //BufferedImage imageTest=ImageIO.read(new File("img/t1.jpg"));
        //GreetImage test=new GreetImage(imageTest);
        //pose.addShape(test);
           
    }
   
    
    public TemplateCanvas() {
        //this.loadImage("img/6.jpg");
        BufferedImage imageTest = null;
        try {
            imageTest = ImageIO.read(new File("img/blank.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(TemplateCanvas.class.getName()).log(Level.SEVERE, null, ex);
        }
        GreetImage test=new GreetImage(imageTest);
        pose=new GreetPose();
        pose.addShape(test);
        
    }
    
    public void loadImage(String name){
        try{
                image=ImageIO.read(new File(name));
                x=(this.getWidth()-((BufferedImage)image).getWidth())/2;
                y=(this.getHeight()-((BufferedImage)image).getHeight())/2;
            }catch(IOException ex){
                System.out.println("Did not read the image");
            }
       
            permenantTemp=new GreetImage(((BufferedImage)image));
            pose.reset();
            pose.addShape(permenantTemp);
        
        
       // repaint();
    }
    
    
    public void paintComponent(Graphics g){
       // super.paintComponent(g);
        //g.drawImage(image, x, y, this);
       
            Graphics2D g2=(Graphics2D)g;
            renderShapes(g2);
       
    }
    
    
     private void renderShapes(Graphics2D g2)
    {
          
        // RENDER THE ENTIRE POSE
        if(pose==null){
            return;     
        }
        Iterator<GreetImage> shapesIt = pose.getShapesIterator();
        
        while (shapesIt.hasNext())
        {
            GreetShape shape = shapesIt.next();
            shape.render(g2);
        }        
    }
    
    
    
   public GreetPose getPose(){
   
       return this.pose;
   }
   
   
   public void setPose(GreetPose p){
       this.pose=p;
   }
    
}
