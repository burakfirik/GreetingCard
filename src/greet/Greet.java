/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author burak firik
 * cse333
 * 108428236
 */
package greet;

import greet.gui.GreetGUI;
import greet.gui.TemplateCanvas;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author burak firik
 * cse333
 * 108428236
 */
public class Greet {

    
    private static Greet singleton = null;
    
    private GreetGUI gui;
    
    private GreetStateManager stateManager;
    
    //private constructor for singleton design pattern
    private Greet(){}
    
    
    
    public static Greet getGreet()
    {
        // ONLY CONSTRUCT THE SINGLETON THE FIRST TIME
        if (singleton == null)
        {
            singleton = new Greet();
        }
        
        // GET THE SINGLETON NO MATTER WHAT
        return singleton;
    }
    
    
    public GreetGUI getGUI() { return gui; }
    
     public GreetStateManager getStateManager() { return stateManager; }
   
    
    
    public void init(){
       
            
        // INITALIZE THE GUI
        gui = new GreetGUI();
        
        stateManager=new GreetStateManager();
      
        //to initialize the panels inside the gui
       // gui.initGUI();
        
    }
    
    public static void main(String[] args) {
      
       Greet greetcard=Greet.getGreet();
       greetcard.init();
       
      
    }
    
    public void requestExit()
    {
        // WE MAY HAVE TO SAVE CURRENT WORK
        boolean continueToExit = true;
       
        
        // IF THE USER REALLY WANTS TO EXIT THE APP
        if (continueToExit)
        {
            // EXIT THE APPLICATION
            System.exit(0);
        }
    }
}
