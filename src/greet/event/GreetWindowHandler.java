/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package greet.event;

import greet.Greet;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 *
 * @author burak
 */
public class GreetWindowHandler implements WindowListener{
     /**
     * This method responds when the user attempts to close
     * the window. We'll relay this request to the file manager,
     * which will make sure any unsaved work is not lost
     * before exiting the application.
     * 
     * @param we The Event Object.
     */
    @Override
    public void windowClosing(WindowEvent we) 
    {
        // RELAY THE REQUEST TO THE FILE MANAGER
        Greet singleton = Greet.getGreet();
        singleton.requestExit();
    }

    // WE WILL NOT BE USING THE REST OF THESE METHODS,
    // BUT I'LL PROVIDE EMPTY METHOD DEFINITIONS TO MAKE
    // THE COMPILER HAPPY, SINCE THIS CLASS implements
    // WindowListener. NOTE THAT AN ALTERNATIVE WOULD
    // BE extends WindowsAdapter AND THEN ONLY PROVIDE
    // THE METHODS WE'LL BE USING.
    
    @Override
    public void windowOpened(WindowEvent we)         {}

    @Override
    public void windowClosed(WindowEvent we)         {}

    @Override
    public void windowIconified(WindowEvent we)      {}

    @Override
    public void windowDeiconified(WindowEvent we)    {}

    @Override
    public void windowActivated(WindowEvent we)      {}

    @Override
    public void windowDeactivated(WindowEvent we)    {}   
}
