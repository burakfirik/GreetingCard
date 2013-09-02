/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package greet.test;

/**
 *
 * @author burak
 */

 
import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
 
public class ImageToXML {
    private JFrame frame;
 
    public ImageToXML()  {
        frame = new JFrame("Image to XML");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container content = frame.getContentPane();
 
        java.net.URL url = this.getClass().getResource("Add.png");
 
        ImageIcon icon = new ImageIcon("Add.png");
        //ImageIcon icon = new ImageIcon(imagePath);
        BufferedImage bImage;
        Image image = icon.getImage();
 
        bImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bImage.createGraphics();
        g.drawImage(image, null, null);
 
        BufferedImage bImage2;
        Icon icon2;
 
        //image to string
        String imageString = imageToString(bImage, "./data/buttons/Add.png");
 
        //transport
        String imageString2 = imageString;
 
        //string to image
        bImage2 = stringToImage(imageString2);
        icon2 = new ImageIcon(bImage2);
 
        JLabel label2 = new JLabel(icon2);
        content.add(label2);
        
        frame.pack();
        frame.setVisible(true);
    }
 
    public String imageToString(BufferedImage bImage, String path)   {
        String imageString = null;
 
        String formatName = path.substring(path.lastIndexOf('.')+1, path.length());
 
        //image to bytes
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(bImage, formatName, baos);
            baos.flush();
            byte[] imageAsRawBytes = baos.toByteArray();
            baos.close();
 
            //bytes to string
            BASE64Encoder b64enc = new BASE64Encoder();
            imageString = b64enc.encode(imageAsRawBytes);
            //don't do this!!!
            //imageString = new String(imageAsRawBytes);
        } catch (IOException ex) {
            Logger.getLogger(ImageToXML.class.getName()).log(Level.SEVERE, null, ex);
        }
 
        return imageString;
    }
 
    public BufferedImage stringToImage(String imageString)    {
        //string to ByteArrayInputStream
        BufferedImage bImage = null;
        BASE64Decoder b64dec = new BASE64Decoder();
        try {
            byte[] output = b64dec.decodeBuffer(imageString);
            ByteArrayInputStream bais = new ByteArrayInputStream(output);
            bImage = ImageIO.read(bais);
        } catch (IOException e) {
            System.err.println("Error");
        }
 
        return bImage;
    }
 
    public static void main(String[] args) {
        new ImageToXML();
    }
}
