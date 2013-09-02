/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package greet.test;

/**
 *
 * @author burak
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author burak
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DragLabelOnLayeredPane extends JLayeredPane {
    public static final int WIDTH = 680;
    public static final int HEIGHT = 480;
    private static final int GRID_ROWS = 8;
    private static final int GRID_COLS = 6;
    private static final int GAP = 3;
    private static final Dimension LAYERED_PANE_SIZE = new Dimension(WIDTH, HEIGHT);
    private static final Dimension LABEL_SIZE = new Dimension(60, 40);
    private GridLayout gridlayout = new GridLayout(GRID_ROWS, GRID_COLS, GAP, GAP);
    private JPanel backingPanel = new JPanel(gridlayout);
    private JPanel[][] panelGrid = new JPanel[GRID_ROWS][GRID_COLS];
    private JLabel redLabel = new JLabel("Red", SwingConstants.CENTER);
    private JLabel blueLabel = new JLabel("Blue", SwingConstants.CENTER);

    public DragLabelOnLayeredPane() {
        backingPanel.setSize(LAYERED_PANE_SIZE);
        backingPanel.setLocation(2 * GAP, 2 * GAP);
        backingPanel.setBackground(Color.black);
        for (int row = 0; row < GRID_ROWS; row++) {
            for (int col = 0; col < GRID_COLS; col++) {
                panelGrid[row][col] = new JPanel(new GridBagLayout());
                backingPanel.add(panelGrid[row][col]);
            }
        }

        redLabel.setOpaque(true);
        redLabel.setBackground(Color.red.brighter().brighter());
        redLabel.setPreferredSize(LABEL_SIZE);
        panelGrid[4][3].add(redLabel);

        blueLabel.setOpaque(true);
        blueLabel.setBackground(Color.blue.brighter().brighter());
        blueLabel.setPreferredSize(LABEL_SIZE);
        panelGrid[1][1].add(blueLabel);

        //backingPanel.setBorder(BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP));
        setPreferredSize(LAYERED_PANE_SIZE);
        add(backingPanel, JLayeredPane.DEFAULT_LAYER);
        DragLabelOnLayeredPane.MyMouseAdapter myMouseAdapter = new DragLabelOnLayeredPane.MyMouseAdapter();
        addMouseListener(myMouseAdapter);
        addMouseMotionListener(myMouseAdapter);
    }

    private class MyMouseAdapter extends MouseAdapter {
        private JLabel dragLabel = null;
        private int dragLabelWidthDiv2;
        private int dragLabelHeightDiv2;
        private JPanel clickedPanel = null;

        @Override
        public void mousePressed(MouseEvent me) {
            clickedPanel = (JPanel) backingPanel.getComponentAt(me.getPoint());
            Component[] components = clickedPanel.getComponents();
            if (components.length == 0) {
                return;
            }
            // if we click on jpanel that holds a jlabel
            if (components[0] instanceof JLabel) {

                // remove label from panel
                dragLabel = (JLabel) components[0];
                clickedPanel.remove(dragLabel);
                clickedPanel.revalidate();
                clickedPanel.repaint();

                dragLabelWidthDiv2 = dragLabel.getWidth() / 2;
                dragLabelHeightDiv2 = dragLabel.getHeight() / 2;

                int x = me.getPoint().x - dragLabelWidthDiv2;
                int y = me.getPoint().y - dragLabelHeightDiv2;
                dragLabel.setLocation(x, y);
                add(dragLabel, JLayeredPane.DRAG_LAYER);
                repaint();
            }
        }

        @Override
        public void mouseDragged(MouseEvent me) {
            if (dragLabel == null) {
                return;
            }
            int x = me.getPoint().x - dragLabelWidthDiv2;
            int y = me.getPoint().y - dragLabelHeightDiv2;
            dragLabel.setLocation(x, y);
            repaint();
        }

        @Override
        public void mouseReleased(MouseEvent me) {
            if (dragLabel == null) {
                return;
            }
            remove(dragLabel); // remove dragLabel for drag layer of JLayeredPane
            JPanel droppedPanel = (JPanel) backingPanel.getComponentAt(me.getPoint());
            if (droppedPanel == null) {
                // if off the grid, return label to home
                clickedPanel.add(dragLabel);
                clickedPanel.revalidate();
            } else {
                int r = -1;
                int c = -1;
                searchPanelGrid: for (int row = 0; row < panelGrid.length; row++) {
                    for (int col = 0; col < panelGrid[row].length; col++) {
                        if (panelGrid[row][col] == droppedPanel) {
                            r = row;
                            c = col;
                            break searchPanelGrid;
                        }
                    }
                }

                if (r == -1 || c == -1) {
                    // if off the grid, return label to home
                    clickedPanel.add(dragLabel);
                    clickedPanel.revalidate();
                } else {
                    droppedPanel.add(dragLabel);
                    droppedPanel.revalidate();
                }
            }

            repaint();
            dragLabel = null;
        }
    }

    private static void createAndShowUI() {
        JFrame frame = new JFrame("DragLabelOnLayeredPane");
        frame.getContentPane().add(new DragLabelOnLayeredPane());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                createAndShowUI();
            }
        });
    }
}


