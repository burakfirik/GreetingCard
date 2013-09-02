/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package greet.test;

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.io.*;
import javax.swing.*;


public class BeansTest {
  private static JFileChooser chooser;
private JFrame frame;
Registry re;
public static void main(String[] args){
    chooser = new JFileChooser();
    chooser.setCurrentDirectory(new File("."));
    BeansTest test = new BeansTest();
    test.init();
}

public void init(){
    frame = new JFrame();
    frame.setLayout(new FlowLayout());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("PersistentFrameTest");
    frame.setSize(400,200);

    JButton registryButton = new JButton("Registry");
    frame.add(registryButton);
    registryButton.addActionListener(EventHandler.create(ActionListener.class, this, "registry"));

    JButton saveButton = new JButton("Save");
    frame.add(saveButton);
    saveButton.addActionListener(EventHandler.create(ActionListener.class, this, "save"));

    frame.setVisible(true);
}


public void registry(){

  re = new Registry();
}

public void save()
{
    if(chooser.showSaveDialog(null)==JFileChooser.APPROVE_OPTION)
    {
        try{
            File file = chooser.getSelectedFile();
            XMLEncoder encoder = new XMLEncoder(new FileOutputStream(file));
            if (re.getFrame() != null) {
                 encoder.writeObject(re.getFrame());
          }
            encoder.close();
        }
        catch(IOException e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}

}

