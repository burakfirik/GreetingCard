/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package greet.eventmouse;

import greet.Greet;
import greet.GreetState;
import greet.GreetStateManager;
import greet.gui.GreetGUI;
import greet.gui.TemplateCanvas;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author burak
 */
public class MoveTextHandler implements MouseListener {



    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Greet singleton = Greet.getGreet();
        GreetStateManager greetmanager=singleton.getStateManager();
        greetmanager.setState(GreetState.MOVE_LABEL_STATE);
        
        System.out.println("x = " + e.getX() + ", y = " +  e.getY()+ "\n");
        
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Greet singleton = Greet.getGreet();
        GreetGUI gui=singleton.getGUI();
        //gui.setLocale(e.getX(), e.getY());
        //System.out.println("x = " + e.getX() + ", y = " +  e.getY()+ "\n");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
       
    }
    
}
