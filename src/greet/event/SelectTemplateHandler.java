/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package greet.event;

import greet.Greet;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author burak
 */
public class SelectTemplateHandler implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        Greet singleton=Greet.getGreet();
        int i=singleton.getGUI().getIndex();
        if(i!=0){
            i--;
        }else{
            i=9;
        }
        
        //singleton.getGUI().getRightCanvas().loadImage(singleton.getGUI().getNames()[i]);
        singleton.getGUI().getSelectedTabPane().loadImage(singleton.getGUI().getNames()[i]);
        singleton.getGUI().repaint();
    }
    
}
