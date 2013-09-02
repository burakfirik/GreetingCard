/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package greet.image;

import greet.Greet;
import greet.gui.GreetGUI;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import org.w3c.dom.Element;

/**
 *
 * @author burak
 */
public abstract class GreetShape {
    
    private BufferedImage image;
    
  
  
    /**
     * Default constructor for this abstract class, it initializes
     */
    public GreetShape()
    {
        // LET'S FILL IN THIS CLASS' DATA WITH SETTINGS
        // FROM THE GUI CONTROLS
        Greet singleton = Greet.getGreet();
        GreetGUI gui = singleton.getGUI();
        
        // ASK THE GUI FOR ALL THESE VALUES
       
     }

    public void buildImage(BufferedImage img){
        image=img;
        
    }
   
    
    public void renderImage(Graphics2D g, int x, int y){
        
        g.drawImage(image, x, y, null);
        
    }
    
   
   
    public BufferedImage getImage(){
        
        return this.image;
    }
    
    public void setImage(BufferedImage img){
        this.image=img;
    }

    
   
    
    
    /***** ABSTRACT METHODS *****/
    
    //it will look down
    public abstract void render(Graphics2D g2);
    public abstract void reSizeImage(int nWidth, int nHeight);    
  
    public abstract int getWidth();
    public abstract int getHeight();
    public abstract boolean containsPoint(Point2D testPoint);
    
    /**
     * This method renders this shape to whatever context the g2 argument
     * comes from. 
     * 
     * @param g2 The graphics context for rendering. It may refer to that
     * of a canvas or an image.
     * 
     * @param poseOriginX The x coordinate location of the pose box.
     * 
     * @param poseOriginY The y coordinate location of the pose box.
     * 
     * @param zoomLevel Used for scaling all that gets rendered.
     * 
     * @param isSelected Selected items are highlighted.
     */       
   // public abstract void    render( Graphics2D g2, 
                                   //int x,int y);    
    
    /**
     * This method makes a clone, i.e. a duplicate, of this shape. This
     * is useful for cut/copy/paste types of operations in applications.
     * 
     * @return A constructed object that is identical to this one.
     */    
    @Override
    public abstract GreetShape clone();

    public abstract BufferedImage deepCopy(BufferedImage bi);
    
    /**
     * This method moves this shape to the x, y location without doing
     * any error checking on whether it's a good location or not.
     * 
     * @param x The x coordinate of where to move this shape.
     * 
     * @param y The y coordinate of where to move this shape.
     */    
    public abstract void move(int x, int y);
    
    /**
     * This is a smarter method for moving this shape, it considers
     * the pose area and prevents it from being moved off the pose area
     * by clamping at the edges.
     * 
     * @param incX The amount to move this shape in the x-axis.
     * 
     * @param incY The amount to move this shape in the y-axis.
     * 
     * @param poseArea The box in the middle of the rendering canvas
     * where the shapes are being rendered.
     */    
    public abstract void    moveShape(  int incX, int incY, 
                                        Rectangle2D.Double poseArea);
    
    /**
     * This method tests to see if the x,y arguments would be valid
     * next geometric valuee for a shape in progress.
     * 
     * @param x The x-axis coordinate for the test point.
     * 
     * @param y The y-axis coordinate for the test point.
     * 
     * @return true if (x,y) would be a valid second coordinate
     * for the shape in progress based on where it is currentyl
     * located.
     */     
    public abstract boolean completesValidShape(int x, int y);
    
    /**
     * This method helps to update the shape that's being
     * sized, testing to make sure it doesn't draw in illegal 
     * coordinates.
     * 
     * @param updateX The x-axis coordinate for the update point.
     * 
     * @param updateY The y-axis coordinate for the update point.
     */  
    public abstract void updateShapeInProgress(int updateX, int updateY);

    /**
     * This method helps to build a .pose file. Shapes know what data
     * they have, so this fills in the geometryNode argument DOC element
     * with the shape data that would be needed to recreate it when
     * it's loaded back from the .pose (xml) file.
     * 
     * @param geometryNode The node where we'll put attributes regarding
     * the geometry of this shape.
     */       
   
}
