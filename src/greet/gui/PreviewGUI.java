/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package greet.gui;

import javax.swing.JFrame;
import static greet.GreetSettings.*;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author burak
 */
public class PreviewGUI extends JFrame{
    
    
    public PreviewGUI(){
        super("Preview of Greeting Card");
       
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
      
        // CONSTRUCT AND LAYOUT THE COMPONENTS
        initGUI();
        
         // AND SETUP THE HANDLERS
        initHandlers();
        
        
        this.setSize(FRAME_WIDTH,FRAME_HEIGHT);
        //this.setResizable(false);
        this.setVisible(true);
    
    }
    
    

    private void initGUI() {
       
    }

    private void initHandlers() {
        
    }
    
    public static void main(String []args ){
        //PreviewGUI preview=new PreviewGUI();
        //preview.setSize(300,300);
        //preview.setVisible(true);
           
    
    }
    
    public void paintComponent(Graphics g){
        paintComponent(g);
      
           
       
    }
    
}
