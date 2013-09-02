/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package greet.event;

import greet.Greet;
import greet.GreetState;
import greet.GreetStateManager;
import greet.gui.GreetGUI;
import greet.gui.PreviewGUI;
import greet.gui.TemplateCanvas;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author burak
 */
public class PreviewHandler implements ActionListener{
    private TemplateCanvas [] rightPanels;
    private TemplateCanvas right;
    private int numberOfPanels;
    private Image image;
    private BufferedImage bufimg;
    private PreviewGUI test;
    @Override
    public void actionPerformed(ActionEvent e) {
       
        
        JLabel [] label=new JLabel[5];
       
        
       test=new PreviewGUI();
        Greet gree=Greet.getGreet();
        GreetGUI gui=gree.getGUI();
        
        
        for(int i=0;i<gui.getInnerPanelIndex();i++){
            right=gui.getInnerPanel()[i];
            bufimg=gui.getSnapShot(right);
            bufimg=reSizeImage(bufimg,right.getSize().width/2, right.getSize().height/2);
            label[i]=new JLabel(new ImageIcon(bufimg));
            test.add(label[i]);
            
        }
        //test.add(label[0]);
        test.setSize(680, 480);
        test.setVisible(true);
        test.repaint();
      
    }
    
   
    public BufferedImage reSizeImage(BufferedImage img,int nWidth, int nHeight){
    
        Greet singleton= Greet.getGreet();
        GreetGUI gui=singleton.getGUI();
        
       BufferedImage scaledImage = new BufferedImage(nWidth, nHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = scaledImage.createGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,

        RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        graphics2D.drawImage(img, 0, 0, nWidth, nHeight, null);

        graphics2D.dispose();
        return scaledImage;
    
    }
    
}
