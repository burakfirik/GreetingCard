/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package greet;

import greet.image.GreetShape;
import greet.state.GreetPose;
import static greet.GreetSettings.*;
import greet.gui.GreetGUI;
import greet.image.GreetImage;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import sun.misc.BASE64Decoder;

/**
 *
 * @author burak
 */
public class GreetStateManager {
    // THIS KEEPS TRACK OF WHAT MODE OUR APPLICATION
    // IS IN. IN DIFFERENT MODES IT BEHAVES DIFFERENTLY
    // AND DIFFERENT CONTROLS ARE ENABLED OR DISABLED
    private GreetState state;
    
    // THIS IS THE POSE WE ARE ACTUALLY EDITING
    private GreetPose pose;

    // THESE STORE THE STATE INFORMATION
    // FOR THE TWO CANVASES
    //private PoseCanvasState trueCanvasState;
   // private PoseCanvasState zoomableCanvasState;
       
    // THIS WILL SERVE AS OUR CLIPBOARD. NOTE THAT WE DO NOT
    // HAVE AN UNDO FEATURE, SO OUR CLIPBOARD CAN ONLY STORE
    // A SINGLE SHAPE
    private GreetShape clipboard;
    
    // THIS WILL SERVE AS OUR CURRENTLY SELECTED SHAPE,
    // WHICH CAN BE MOVED AND CHANGED
    private GreetImage selectedShape;
    
    

    // THIS IS A SHAPE THAT IS IN THE PROGRESS OF BEING
    // CREATED BY THE USER
    private GreetShape shapeInProgress;
    //private PoseurShapeType shapeInProgressType;

    // WE'LL USE THESE VALUES TO CALCULATE HOW FAR THE
    // MOUSE HAS BEEN DRAGGED BETWEEN CALLS TO
    // OUR mouseDragged METHOD CALLS
    private int lastMouseDraggedX;
    private int lastMouseDraggedY;
    
    //This is a flag to check if we are in dragging mode
    private boolean dragging;
    private int fixedX;
    private int fixedY;
    
    
    /**
     * This constructor sets up all the state information regarding
     * the shapes that are to be rendered for the pose. Note that 
     * at this point the user has not done anything, all our
     * pose data structures are essentially empty.
     */
    public GreetStateManager()
    {
        
        Greet singleton = Greet.getGreet();
        GreetGUI greetgui=singleton.getGUI();
        
        // WE ALWAYS START IN SELECT_SHAPE_MODE
        state = GreetState.STARTUP_STATE;
        
        // CONSTRUCT THE POSE, WHICH WILL BE USED
        // TO RENDER OUR STUFF
        //pose = new GreetPose(TEMP_PANEL_WIDTH, TEMP_PANEL_HEIGHT);
        pose=greetgui.getRightCanvas().getPose();
        // NOW INITIALIZE THE CANVAS STATES. NOTE, DON'T CONFUSE
        // THE WORD "true" WITH THE PARAMETER "true". THEY HAVE
        // TWO SEPARATE MEANINGS
       // trueCanvasState = new PoseCanvasState(
        //        false, pose, INIT_ZOOM_LEVEL, INIT_ZOOM_LEVEL, INIT_ZOOM_LEVEL);
      //  zoomableCanvasState = new PoseCanvasState(
       //         true, pose, INIT_ZOOM_LEVEL, ZOOM_FACTOR, MIN_ZOOM_LEVEL);

        // NOTHING IS ON THE CLIPBOARD TO START
        clipboard = null;
        
        // THERE IS NO SELECTED SHAPE YET
        selectedShape = null;
    }
    
    
     /**
     * Gets the current mode of the application.
     * 
     * @return The mode currently being used by the application. 
     */
    public GreetState getMode() { return state; }
    
    
    
    public GreetShape getShapeInProgress()
    {
        return shapeInProgress;
    } 
    
    
     /**
     * Accessor method for getting the pose object that
     * is being edited.
     * 
     * @return The Pose object currently being edited by the app.
     */
    public GreetPose getPose() { return pose; }
    
    
    /**
     * Accessor method for getting the shape the user has
     * selected. Note that if no shape is currently selected,
     * null is returned.
     * 
     * @return The shape the use has selected, null if nothing
     * is currently selected.
     */
    public GreetShape getSelectedShape() { return selectedShape; }
     
    /**
     * Accessor method that tests to see if a shape is currently
     * being created by the user. If so, true is returned, else
     * false.
     * 
     * @return true if the user is in the process of creating a
     * shape, false otherwise.0
     */
    public boolean isShapeInProgress()
    {
        return shapeInProgress != null;
    }
    
     /**
     * Accessor method to test and see if a shape is currently
     * on the clipboard.
     * 
     * @return true if a shape is currently on the clipboard,
     * false otherwise.
     */
    public boolean isShapeOnClipboard()
    {
        return clipboard != null;
    }
    
    
    /**
     * Accessor method to test and see if a shape is currently
     * selected.
     * 
     * @return true if a shape is currently selected, false otherwise.
     */
    public boolean isShapeSelected()
    {
        return selectedShape != null;
    }
    
    /**
     * Accessor method that tests if the testSelectedShape
     * argument is the currently selected shape. If it is,
     * true is returned, else false.
     * 
     * @param testSelectedShape The shape to test to see if it's
     * the same object as the currently selected shape.
     * 
     * @return true if testSelectedShape is the currently 
     * selected shape. false otherwise.
     */
    public boolean isSelectedShape(GreetShape testSelectedShape)
    {
        return testSelectedShape == selectedShape;
    }
    
     // MUTATOR METHODS
    
    /**
     * This mutator method changes the mode of the application,
     * which may result in a cursor change and the enabling and
     * disabling of various controls.
     * 
     * @param newMode The mode to set as the current mode.
     */
    public void setState(GreetState newMode)
    {
        // KEEP THE MODE
        state = newMode;
        
        // AND UPDATE THE GUI
        Greet singleton = Greet.getGreet();
        GreetGUI gui = singleton.getGUI();
        
    }
    
    // METHODS MADE AVAILBLE TO OTHER CLASSES

    /**
     * This method is called when the user selects a shape type
     * to render. 
     * 
     * @param shapeType The shape type the user wishes to render.
     */
    public void selectShapeToDraw()
    {
        // CLEAR THE SELECTED SHAPE
        selectedShape = null;
        
        // CHANGE THE MODE
        setState(GreetState.CREATE_SHAPE_STATE);
        
        // THIS WILL UNDO ANY SHAPE IN PROGRESS ALREADY
        shapeInProgress = null;    
        //shapeInProgressType = shapeType;
        
        // REPAINT THE CANVASES
        repaintCanvases();
    }
    
     /**
     * This method manages the response to mouse button dragging
     * on the right canvas. 
     * 
     * @param x X-coordinate of the mouse drag.
     * 
     * @param y Y-coordinate of the mouse drag.
     */
    public void processMouseDragged(int x, int y, MouseEvent e)
    {   
        
            Greet singleton = Greet.getGreet();
            GreetGUI gui=singleton.getGUI();
       
     
            int incX = x - lastMouseDraggedX;//-(int)poseArea.getWidth()/2;
            int incY = y - lastMouseDraggedY;//-(int)poseArea.getHeight()/2;
            lastMouseDraggedX = x;
            lastMouseDraggedY = y;
            
            
              if(state==GreetState.SHAPE_SELECTED_STATE){
                  
                    selectedShape.move(incX, incY);
                    repaintCanvases();
                
              }
              
              if(state==GreetState.MOVE_LABEL_STATE){
                  gui.setLocale(x, y);
                  
                  
              }
           
              System.out.println("GRAGGED");
        
    }
    
       
     /**
     * This method manages the response to a mouse button press
     * on the right canvas. 
     * 
     * @param x X-coordinate of the mouse press.
     * 
     * @param y Y-coordinate of the mouse press.
     */    
    public void processMousePress(int x, int y, MouseEvent e)
    {   
        Greet singleton = Greet.getGreet();
        GreetStateManager st=singleton.getStateManager();
        GreetGUI gui=singleton.getGUI();
        this.pose=gui.getSelectedTabPane().getPose();
       
        System.out.println("PRESSED");
        
        fixedX=x;
        fixedY=y;
        lastMouseDraggedX=x;
        lastMouseDraggedY=y;
        
        if(state==GreetState.SELECT_SHAPE_STATE){
            
            selectedShape=pose.findShapeWithPoint(x,y);
            
            
            if(selectedShape!=null){
                //setState(GreetState.DRAG_SHAPE_STATE);
                setState(GreetState.SHAPE_SELECTED_STATE);
            } else {
                return;
            }
            
            
        }
        
        
        if(state==GreetState.SHAPE_SELECTED_STATE){
               
            Point2D.Double testPoint = new Point2D.Double(x, y);
           
            if(selectedShape.containsPoint(testPoint)){
                setState(GreetState.SHAPE_SELECTED_STATE);
                //return;
            }else {
                selectedShape=null;
                setState(GreetState.SELECT_SHAPE_STATE);
            }
          
        }
       
    }
    
    
    
    
    public void processMouseReleased(int x, int y)
    {   
        Greet singleton = Greet.getGreet();
       
        GreetGUI gui=singleton.getGUI();
      
            lastMouseDraggedX = x;
            lastMouseDraggedY = y;
        
           if(state==GreetState.SELECT_SHAPE_STATE){
            //setState(PoseurState.SHAPE_SELECTED_STATE);
           }else if(state==GreetState.SHAPE_SELECTED_STATE){
              
               setState(GreetState.SELECT_SHAPE_STATE);
           }
           
    }
    
    
    
    
    
    
    
    
    
    private void repaintCanvases()
    {
        // THE POSE HAS CHANGED
        //markPoseChanged();        
        
        //PoseCanvas trueCanvas = trueCanvasState.getCanvas();
        
        Greet singleton = Greet.getGreet();
        GreetGUI gui = singleton.getGUI();
        
        gui.repaint();
    }

    public void setPosePanel(int s) {
        Greet singleton = Greet.getGreet();
        GreetStateManager st=singleton.getStateManager();
        GreetGUI gui=singleton.getGUI();
        this.pose=gui.getSelectedTabPane().getPose();
       
        gui.repaint();
    }

    public void enlargeSelectedImage() {
        if(selectedShape!=null){
            int width=selectedShape.getWidth();
            int height=selectedShape.getHeight();
           
         
            width+=5;
            height+=5;
           //selectedShape.scaleImage(selectedShape.getImage(), width, height);
            selectedShape.reSizeImage(width, height);
           
            repaintCanvases();
        }
    }
    
    public void reduceSizeSelectedImage() {
        if(selectedShape!=null){
            int width=selectedShape.getWidth();
            int height=selectedShape.getHeight();
           
            double scale=width/height;
            width-=5;
            height-=5;
           //selectedShape.scaleImage(selectedShape.getImage(), width, height);
            selectedShape.reSizeImage(width, height);
           
            repaintCanvases();
        }
    }

    public void moveBackSelectedImage() {
        if(selectedShape!=null){
        GreetImage copy=(GreetImage) selectedShape.clone();
        pose.removeShape(selectedShape);
        pose.getImagesList().add(1, copy);
        selectedShape=copy;
        }
        repaintCanvases();
    }

    public void moveFrontSelectedImage() {
        
        if(selectedShape!=null){
        GreetImage copy=(GreetImage) selectedShape.clone();
        pose.removeShape(selectedShape);
        pose.getImagesList().add(copy);
        selectedShape=copy;
        }
        
        repaintCanvases();
    }

    public void setNewFont(Font f) {
        
        Greet singleton = Greet.getGreet();
        GreetGUI gui=singleton.getGUI();
        gui.setLabel(f);
        
        repaintCanvases();
        
    }
    
    
    
    
    
    
}
