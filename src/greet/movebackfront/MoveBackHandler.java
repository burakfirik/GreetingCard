/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package greet.movebackfront;

import greet.Greet;
import greet.GreetStateManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author burak
 */
public class MoveBackHandler implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        Greet singleton=Greet.getGreet();
        GreetStateManager statemanager=singleton.getStateManager();
        statemanager.moveBackSelectedImage();
    }
    
}
