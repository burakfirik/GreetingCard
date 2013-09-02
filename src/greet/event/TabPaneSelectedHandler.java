/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package greet.event;

import greet.Greet;
import greet.gui.GreetGUI;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author burak
 */
public class TabPaneSelectedHandler implements ChangeListener{
    

    @Override
    public void stateChanged(ChangeEvent e) {
        
        JTabbedPane pane = (JTabbedPane)e.getSource();

        // Get current tab
        int s = pane.getSelectedIndex();
        Greet singleton = Greet.getGreet();
        GreetGUI greetgui=singleton.getGUI();
        greetgui.selectedTabPane(s);
        singleton.getStateManager().setPosePanel(s);
    }
}
