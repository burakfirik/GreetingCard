/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package greet.state;

import greet.gui.TemplateCanvas;
import greet.image.GreetImage;
import greet.image.GreetShape;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import javax.imageio.ImageIO;

/**
 *
 * @author burak
 */
public class GreetPose {
     // HERE'S THIS POSE'S DIMENSIONS
    private int poseWidth;
    private int poseHeight;
    
    // AND THESE ARE ALL THE SHAPES TO RENDER FOR IT
    private LinkedList<GreetImage> imageList;
    
    
    private LinkedList<byte[]> imageByte;
               
    
    /**
     * This constructor fully sets up a blank pose using the
     * provided dimensions. Note that we only need to construct
     * the shapes list once. From here on out we'll just add
     * things to it and move things around inside.
     * 
     * @param initPoseWidth The width of the image in pixels
     * that the pose is to represent when exported.
     * 
     * @param initPoseHeight The height of the image in pixels
     * that the pose is to represent when exported.
     */
    public GreetPose()
    {
        // KEEP THE DIMENSIONS
        
        
        // AND GET OUR SHAPES LIST READY
        imageList = new LinkedList();
        
        imageByte=new LinkedList();
    }
    
    // ACCESSOR METHODS
    
    /**
     * Accessor method for getting the pose width.
     * 
     * @return The width of this pose, in pixels.
     */
    public int getPoseWidth() { return poseWidth; }
    
    /**
     * Accessor method for getting the pose height.
     * 
     * @return The height of this pose, in pixels.
     */
    public int getPoseHeight() { return poseHeight; }
    
    /**
     * Accesoor method for getting this pose's shape list.
     * 
     * @return The pose list with all the shapes used
     * to render this pose.
     */
    public LinkedList<GreetImage> getImagesList()
    {
        return imageList;
    }
    
    
    public LinkedList<byte []>getByteImageList() throws IOException{
          imageByte.clear();
          //imageList.clear();
          ByteArrayOutputStream bos=new ByteArrayOutputStream();
          byte[] imageInByte;
         
         for(int i=0;i<imageList.size();i++){
             
             BufferedImage img=imageList.get(i).getImage();
             
             ImageIO.write(img, "jpg", bos);
             
             bos.flush();
             imageInByte = bos.toByteArray();
             bos.close();
             //imageByte.add(getBytes(imageList.get(i).getImage()));           
             imageByte.add(imageInByte);      
         }
        return imageByte;
    }
    
    /**
     * Accessor method for getting an iterator for going
     * through the shapes list.
     * 
     * @return An iterator for going through the shapes list.
     */
    public Iterator<GreetImage> getShapesIterator()
    {
        return imageList.iterator();
    }    

    // MUTATOR METHODS
    
    /**
     * Mutator method for setting the width of this pose. Note that changing
     * this value might put some shapes partially or fully outside of the
     * pose, which means they wouldn't be rendered.
     * 
     * @param initWidth The new width for the pose.
     */
    public void setPoseWidth(int initWidth) { poseWidth = initWidth; }
    
    /**
     * Mutator method for setting the height of this pose. Note that changing
     * this value might put some shapes partially or fully outside of the
     * pose, which means they wouldn't be rendered.
     * 
     * @param initHeight The new height for the pose.
     */
    public void setPoseHeight(int initHeight) { poseHeight = initHeight; }
    
    /**
     * This method adds a shape to the list of shapes to 
     * be rendered as part of the pose.
     * 
     * @param shapeToAdd The shape to add to the pose.
     */
    public void addShape(GreetImage shapeToAdd)
    {
        imageList.add(shapeToAdd);
    }  

    /**
     * This method removes a shape from the list of shapes to
     * be rendered as part of this pose.
     * 
     * @param shapeToRemove The shape to remove from the pose.
     */
    public void removeShape(GreetShape shapeToRemove)
    {
        imageList.remove(shapeToRemove);
    }

    /**
     * This method removes all the shapes from the shape
     * list, resetting the pose. It does not change the
     * pose dimensions, however.
     */
    public void reset()
    {
        imageList.clear();
    }
     
    /**
     * This method loads the poseData argument's data
     * into this pose object. We do this because when
     * we load a pose from a .pose file, we don't want
     * to end up with a partially loaded pose when 
     * an error happens. Plus, other classes might have
     * references to this pose object and we don't want
     * to chase them all down if we reassign one of their
     * pose variables.
     * 
     * @param poseData Pose data to load into this pose.
     */
    public void loadPoseData(GreetPose poseData)
    {
        // JUST COPY IT ALL IN, CLEARING OUT ALL OLD SHAPES
        poseWidth = poseData.poseWidth;
        poseHeight = poseData.poseHeight;
        imageList.clear();
        for (GreetImage shape : poseData.imageList)
        {
            imageList.add(shape);
        }
    }   
    
    /**
     * This method looks through the shapes in the pose,
     * starting with the top-most ones, which are at the
     * end of the list, and returns the first one that
     * contains the point x, y. If no shape is found,
     * null is returned.
     * 
     * @param x Coordinate of test point in x-axis.
     * 
     * @param y Coordinate of test point in y-axis.
     * 
     * @return The first shape on top that contains the 
     * test point, null if no shape is found to contain
     * the point.
     */
    public GreetImage findShapeWithPoint(int x, int y)
    {
        // WE'RE LOOKING FOR A SHAPE THAT CONTAINS THIS POINT
        Point2D.Double testPoint = new Point2D.Double(x, y);
        
        // GO THROUGH ALL THE SHAPES STARTING FROM THE TOP
        for (int i = imageList.size()-1; i > 0; i--)
        {
            // TEST IT
            GreetImage testShape = imageList.get(i);
            if (testShape.containsPoint(testPoint))
            {
                // FOUND!
                return testShape;
            }
        }
        return null;
    }
    
   
}
