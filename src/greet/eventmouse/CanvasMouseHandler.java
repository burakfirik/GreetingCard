/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package greet.eventmouse;

import greet.Greet;
import greet.GreetStateManager;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author burak
 */
public class CanvasMouseHandler implements MouseListener, MouseMotionListener
{
    /**
     * This method responds to when the user presses the mouse
     * on the pose editing canvas. Note that it forwards the
     * response to the poseur state manager, since that object
     * manages the data it intends to change.
     * 
     * @param e The event object.
     */
    @Override
    public void mousePressed(MouseEvent e) 
    {
        Greet singleton = Greet.getGreet();
        GreetStateManager state = singleton.getStateManager();
        state.processMousePress(e.getX(), e.getY(),e);
    }

    /**
     * This method responds to when the user releases the mouse
     * on the pose editing canvas. Note that it forwards the
     * response to the poseur state manager, since that object
     * manages the data it intends to change.
     * 
     * @param e The event object.
     */
    @Override
    public void mouseReleased(MouseEvent e) 
    {
        Greet singleton = Greet.getGreet();
        GreetStateManager state = singleton.getStateManager();
        state.processMouseReleased(e.getX(), e.getY());
    }
    
    /**
     * This method responds to when the user drags the mouse
     * on the pose editing canvas. Note that it forwards the
     * response to the poseur state manager, since that object
     * manages the data it intends to change.
     * 
     * @param e The event object.
     */
    @Override
    public void mouseDragged(MouseEvent e) 
    {
        Greet singleton = Greet.getGreet();
        GreetStateManager poseurStateManager = singleton.getStateManager();
        poseurStateManager.processMouseDragged(e.getX(), e.getY(),e);
    }

    // WE ARE NOT USING THE REST OF THESE INTERACTIONS
    
    @Override
    public void mouseMoved(MouseEvent e)    {}    
    @Override
    public void mouseClicked(MouseEvent e)  {}
    @Override
    public void mouseEntered(MouseEvent e)  {}
    @Override
    public void mouseExited(MouseEvent e)   {}
}
