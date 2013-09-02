/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package greet.event;

import greet.Greet;
import greet.GreetState;
import greet.GreetStateManager;
import greet.gui.GreetGUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author burak
 */
public class SelectImageHandler implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        Greet singleton = Greet.getGreet();
        GreetStateManager greetmanager=singleton.getStateManager();
        greetmanager.setState(GreetState.SELECT_SHAPE_STATE);
    }
    
}
