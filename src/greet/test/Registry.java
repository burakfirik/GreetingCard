/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package greet.test;




import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Registry {
JFrame frame;
public Registry(){

     frame = new JFrame();
    frame.setLayout(new FlowLayout());
    frame.setSize(400,200);

    JLabel nameL = new JLabel("Name:");
    JTextField nameF = new JTextField(8);

    frame.add(nameL);
    frame.add(nameF);


    frame.setVisible(true);

}


public JFrame getFrame() {
   return frame;
}
}