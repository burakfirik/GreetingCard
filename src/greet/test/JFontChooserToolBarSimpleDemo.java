/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package greet.test;

/**
 *
 * @author burak
 */
import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

import de.schimikowski.jfontchooser.JFontChooserListener;
import de.schimikowski.jfontchooser.JFontChooserToolBar;
/**
 * Simple Demonstration how to use {@link JFontChooserToolBar} and {@link JFontChooserListener}.
 * @author Daniel schimikowski
 */
public class JFontChooserToolBarSimpleDemo extends JFrame implements JFontChooserListener{
        /**
         * The JFontChooserToolbar.
         */
        private JFontChooserToolBar fontChooserToolBar;
        /**
         * The label, which font we will change.
         */
        private JLabel label;
        /**
         * Constructor.
         */
        public JFontChooserToolBarSimpleDemo(){
                super("JFontChooserToolBarSimpleDemo");
                setDefaultCloseOperation(EXIT_ON_CLOSE);
                setLayout(new BorderLayout());
                
                fontChooserToolBar = new JFontChooserToolBar();
                fontChooserToolBar.addFontChooserListener(this);
                label = new JLabel("Lorem ipsum dolor sit amet, consectetur adipisicing elit");
        
                add(label, BorderLayout.NORTH);
                add(fontChooserToolBar,BorderLayout.SOUTH);
                pack();
                setLocationRelativeTo(null);
                setVisible(true);
        }
        
        public static void main(String args[]){
                new JFontChooserToolBarSimpleDemo();
        }

        @Override
        public void fontChanged(Font font) {
                label.setFont(font);
                pack();
                
        }
}
