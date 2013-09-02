/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package greet.eventzoom;

import greet.Greet;
import greet.GreetStateManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author burak
 */
public class ZoomInHandler implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        Greet singleton=Greet.getGreet();
        GreetStateManager statemanager=singleton.getStateManager();
        statemanager.enlargeSelectedImage();
        
    }
    
}
