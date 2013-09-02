/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package greet.event.serialize;

import greet.Greet;
import greet.gui.GreetGUI;
import greet.gui.TemplateCanvas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 *
 * @author burak
 */
public class SaveAsHandler implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        Greet singleton=Greet.getGreet();
        GreetGUI gui=singleton.getGUI();
        //gui.seriablizePanels();
        gui.getGreetIO().saveGreet();
        
        
    }
    
    
     
    
    
}
