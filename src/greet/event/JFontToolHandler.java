/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package greet.event;

import de.schimikowski.jfontchooser.JFontChooserListener;
import greet.Greet;
import greet.GreetState;
import greet.GreetStateManager;
import java.awt.Font;

/**
 *
 * @author burak
 */
public class JFontToolHandler implements JFontChooserListener{

    @Override
    public void fontChanged(Font font) {
        Greet singleton = Greet.getGreet();
        GreetStateManager greetmanager=singleton.getStateManager();
        greetmanager.setNewFont(font);
    }
    
}
