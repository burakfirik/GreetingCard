/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package greet.event;

import greet.Greet;
import greet.gui.GreetGUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 *
 * @author burak
 */
public class BrowseTemplateHandler implements ActionListener {

    
    @Override
    public void actionPerformed(ActionEvent e) {
       Greet singleton = Greet.getGreet();
       GreetGUI greetgui=singleton.getGUI();
       String t[]=greetgui.getNames();
       greetgui.getLeftCanvas().loadImage(t[greetgui.getIndex()]);
       greetgui.incrementIndex();
       greetgui.repaint();
       
    }
    
}
