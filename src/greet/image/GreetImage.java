/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package greet.image;

import greet.Greet;
import greet.gui.GreetGUI;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import org.w3c.dom.Element;
import sun.security.provider.PolicyParser;

/**
 *
 * @author burak
 */
public class GreetImage extends GreetShape {
     

    // THIS GUY WILL BE USED FOR RENDERNIG RECTANGLES. WE HAVE IT BECAUSE
    // WE'LL BE STORING OUR RECTANGLE GEOMETRY INFORMATION IN "Pose" COORDINATES,
    // WHICH MEANS IN IT'S OWN LITTLE BOX, BUT WE'LL BE RENDERNIG IT ON THE
    // SCREEN INSIDE THAT BOX IN THE MIDDLE OF A PANEL USING PANEL COORDINATES.
    // SO, WE DON'T WANT TO CHANGE geometry EACH TIME WE RENDER, BECAUSE THEN
    // WE'LL HAVE TO CHANGE IT BACK. 
    private BufferedImage sharedGeometry;
    
    private int x,y;
    
    
    /**
     * PoseurRectangle objects are constructed with their geometry, which
     * can be updated later via service methods.
     * 
     * @param initGeometry The geometry to associate with this rectangle.
     */
    public GreetImage( BufferedImage initGeometry)
    {
        
        sharedGeometry = initGeometry;
        x=sharedGeometry.getMinX();
        y=sharedGeometry.getMinY();
        
    }

   public BufferedImage getImage(){
       return sharedGeometry;
   }

     
   
   public int getX(){
       return x;
   }
   public int getY(){
       return y;
   }

    
   public void setX(int xx){
       x=xx;
   }
   public void setY(int yy){
       y=yy;
   }
        
    /**
     * This method tests if the testPoint argument is inside this
     * rectangle. If it does, we return true, if not, false.
     * 
     * @param testPoint The point we want to test and see if it is
     * inside this rectangle
     * 
     * @return true if the point is inside this rectangle, false otherwise.
     */
    @Override
    public boolean containsPoint(Point2D testPoint)
    {
        Rectangle2D.Double imageBounds=new Rectangle2D.Double(this.x, this.y,this.x+sharedGeometry.getWidth(), this.y+sharedGeometry.getHeight());
        
        if((testPoint.getX()>this.x&&testPoint.getX()<(this.x+sharedGeometry.getWidth()))&&(testPoint.getY()>this.y&&testPoint.getY()<(this.y+sharedGeometry.getHeight()))){
            return true;
        }
        
       
       //return imageBounds.contains(testPoint);
        return false;
    }
   
     
    @Override
    public void render(Graphics2D g2)
    {   
       
        g2.drawImage(sharedGeometry, x, y,null);
        //renderImage(g2, x,y);
    }
    
    
 
    /**
     * This method moves this shape to the x, y location without doing
     * any error checking on whether it's a good location or not.
     * 
     * @param x The x coordinate of where to move this rectangle.
     * 
     * @param y The y coordinate of where to move this rectangle.
     */
    @Override
    public void move(int x1, int y1)
    {
        
         x += x1;
         y += y1;
        
    }

        
    public void reSizeImage(int nWidth, int nHeight){
    
        Greet singleton= Greet.getGreet();
        GreetGUI gui=singleton.getGUI();
        
       BufferedImage scaledImage = new BufferedImage(nWidth, nHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = scaledImage.createGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,

        RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        graphics2D.drawImage(sharedGeometry, 0, 0, nWidth, nHeight, null);

        graphics2D.dispose();
        sharedGeometry=scaledImage;
            
        gui.repaint();
        
    
    }
    
    public BufferedImage deepCopy(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }
    
    @Override
    public GreetShape clone() {
        
        BufferedImage copyGeometry = this.deepCopy(sharedGeometry);
        
        GreetImage copy=new GreetImage(copyGeometry);
        copy.x=this.x;
        copy.y=this.y;
        
        // SINCE Color AND Stroke ARE IMMUTABLE,
        // WE DON'T MIND SHARING THEM 
       // GreetShape copy = new GreetImage( copyGeometry);
        copy.setImage(copyGeometry);
        
        
        return copy;
        
    }

    @Override
    public void moveShape(int incX, int incY, Double poseArea) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean completesValidShape(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void updateShapeInProgress(int updateX, int updateY) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getWidth() {
        return sharedGeometry.getWidth();
    }

    @Override
    public int getHeight() {
        return sharedGeometry.getHeight();
    }

     
    
   
}
