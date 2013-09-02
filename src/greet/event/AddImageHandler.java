/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package greet.event;

import greet.Greet;
import greet.gui.GreetGUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author burak
 */
public class AddImageHandler implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
       Greet singleton = Greet.getGreet();
       GreetGUI greetgui=singleton.getGUI();
       greetgui.addImage();
    }
    
}
