/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package greet.gui;

import de.schimikowski.jfontchooser.JFontChooserToolBar;
import greet.Greet;
import javax.swing.JFrame;

import static greet.GreetSettings.*;
import greet.GreetState;
import greet.GreetStateManager;
import greet.event.AddImageHandler;
import greet.event.AddPageHandler;
import greet.event.ApplyFontHandler;
import greet.event.BrowseTemplateHandler;
import greet.event.GreetWindowHandler;
import greet.event.JFontToolHandler;
import greet.event.PreviewHandler;
import greet.event.SelectImageHandler;
import greet.event.SelectTemplateHandler;
import greet.event.TabPaneSelectedHandler;
import greet.eventmouse.CanvasMouseHandler;
import greet.eventmouse.MoveTextHandler;
import greet.eventzoom.ZoomInHandler;
import greet.eventzoom.ZoomOutHandler;
import greet.files.GreetIO;
import greet.image.GreetImage;
import greet.image.GreetShape;
import greet.movebackfront.MoveBackHandler;
import greet.movebackfront.MoveFrontHandler;
import greet.state.GreetPose;
import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Label;
import java.awt.MediaTracker;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.font.FontRenderContext;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.ImageFilter;
import java.awt.image.WritableRaster;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import sun.awt.geom.AreaOp;
import sun.misc.BASE64Decoder;

/**
 *
 * @author burak
 */
public class GreetGUI extends JFrame {
    
   
    private GreetIO greetIO;
    
    protected String appName;
    
    protected int index;
    
    protected int panelIndex;
        
    // WE'LL HAVE TWO CANVASES IN THE CENTER, THE
    // ONE ON THE LEFT IS THE TRUE ONE, AND WILL NEVER
    // ZOOM THE VIEW. THE ONE ON THE RIGHT WILL ALLOW 
    // FOR ZOOMED IN AND OUT VIEWS. NOTE THAT WE'LL PUT
    // THEM INTO A SPLIT PANE
    private JSplitPane canvasSplitPane;
    
    // NORTH PANEL - EVERYTHING ELSE GOES IN HERE
    private JPanel northPanel;
    private JPanel northOfNorthPanel;
    private JPanel southOfNorthPanel;
    
     // SHAPE SELECTION CONTROLS
    private JToolBar shapeToolbar;
    private JToolBar editToolbar;
    private JFontChooserToolBar fontChooserToolBar;
    private JToolBar textToolbar;
    //private JToolBar selection
    
    private TemplateLeftCanvas canvasLeft;
    private TemplateCanvas canvasRight;
    
    private JTabbedPane tabPane;
    
    private TemplateCanvas [] innerPanels;
    
    // FILE CONTROLS 
    private JButton browseButton;
    private JButton selectButton;
    private JButton addTabButton;
    private JButton addImageButton;
    private JButton selectImageButton;
    private JButton zoomInButton;
    private JButton zoomOutButton;
    private JButton customColorSelectorButton;
    private JButton doIt;
    private JButton preview;
    
    //
    
    
    private JButton moveBackButton;
    private JButton moveFrontButton;
    private JLabel fontLabel;
    private JTextArea textArea;
    private BufferedImage img;
    private Font font;
    private String userText="Test";
    private Graphics g ;
    private FontRenderContext frc;
    private Rectangle2D rect;
    
    //To chose the name of the file
    private JFileChooser chooser;
    private JTabbedPane pane;
        
    /**
       * The label, which font we will change.
    */
    
    private MenuGUI menuBar;
    
    private JFileChooser fileChooser;
    
    private JComboBox pageComboBox;
    
    private JLabel alphaLabel;
    
    ImageIcon icon ;
    
    private int selectedTabPane;
    
    private GreetPose greetPose=new GreetPose();
    
    private String nameOfTemplate="img/9.jpg";
    
    private TabPaneSelectedHandler tpsh;
    
    String [] names=new String[10];
    
    public GreetGUI(){
        super();
    
        
        
        // CONSTRUCT AND LAYOUT THE COMPONENTS
        initGUI();
        
         // AND SETUP THE HANDLERS
        initHandlers();
        
        
        this.setSize(FRAME_WIDTH,FRAME_HEIGHT);
        this.setResizable(false);
        this.setVisible(true);
    
    }

    private void initGUI() {
       
        // MAKE THE COMPONENTS
        constructGUIControls();
        
        layoutGUIControls();
    }

    private void constructGUIControls() {
        
        
        
        index=0;
        names[0]="img/1.jpg";
        names[1]="img/2.jpg";
        names[2]="img/3.jpg";
        names[3]="img/4.jpg";
        names[4]="img/5.jpg";
        names[5]="img/6.jpg";
        names[6]="img/7.jpg";
        names[7]="img/8.jpg";
        names[8]="img/9.jpg";
        names[9]="img/10.jpg";
        
        canvasSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        icon= new ImageIcon("data/buttons/Front.png");
        
        innerPanels=new TemplateCanvas[5];
        tabPane=new JTabbedPane();
        
        shapeToolbar=new JToolBar();
        editToolbar=new JToolBar();
        fontChooserToolBar=new JFontChooserToolBar();
        textToolbar=new JToolBar();
        
        greetIO=new GreetIO();
        
        img=new BufferedImage(1, 1, BufferedImage.TYPE_4BYTE_ABGR);
        font = new Font(Font.MONOSPACED, Font.PLAIN, 24); 
        g= img.getGraphics();  
        frc= g.getFontMetrics().getFontRenderContext();  
        g.setFont(font);
        
        
        rect= font.getStringBounds(userText, frc);  
        // release resources  
        g.dispose();  

        // LET'S MAKE THE CANVAS ON THE LEFT SIDE, WHICH
        // WILL NEVER ZOOM
       
       
        
       // canvasLeft = new TemplateCanvas("img/t1.jpg");
        
        canvasLeft=new TemplateLeftCanvas(nameOfTemplate);
        canvasLeft.setBackground(TRUE_CANVAS_COLOR);
        
        
        canvasRight = new TemplateCanvas(greetPose);
        canvasRight.setBackground(TRUE_CANVAS_COLOR);
        innerPanels[0]=canvasRight;
        panelIndex=1;
        tabPane.addTab("", icon,innerPanels[0]);
        
        pane= new JTabbedPane();
        
        northPanel = new JPanel();
        northOfNorthPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        southOfNorthPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        
        
        // WE'LL BATCH LOAD THE IMAGES
        MediaTracker tracker = new MediaTracker(this);
        int idCounter = 0;
        
        browseButton  = (JButton)initButton(BROWSE_IMAGE_FILE,  shapeToolbar, tracker, idCounter++, JButton.class, null, BROWSE_TOOLTIP);
        selectButton  = (JButton)initButton(SELECTION_IMAGE_FILE,  shapeToolbar, tracker, idCounter++, JButton.class, null, SELECT_TOOLTIP);
        addTabButton  = (JButton)initButton(ADD_IMAGE_FILE,  shapeToolbar, tracker, idCounter++, JButton.class, null, ADD_TOOLTIP);
        addImageButton  = (JButton)initButton(ADDIMAGE_IMAGE_FILE,  shapeToolbar, tracker, idCounter++, JButton.class, null, ADDIMAGE_TOOLTIP);
        selectImageButton  = (JButton)initButton(SELECT_IMAGE_FILE,  shapeToolbar, tracker, idCounter++, JButton.class, null, SELECTIMAGE_TOOLTIP);
        zoomInButton  = (JButton)initButton(ZOOMIN_IMAGE_FILE,  shapeToolbar, tracker, idCounter++, JButton.class, null, ZOOMIN_TOOLTIP);
        zoomOutButton  = (JButton)initButton(ZOOMOUT_IMAGE_FILE,  shapeToolbar, tracker, idCounter++, JButton.class, null, ZOOMOUT_TOOLTIP);
        customColorSelectorButton=(JButton)initButton(CUSTOM_IMAGE_FILE,  shapeToolbar, tracker, idCounter++, JButton.class, null, CUSTOM_TOOLTIP);
        doIt=(JButton)initButton(DOIT_IMAGE_FILE,  shapeToolbar, tracker, idCounter++, JButton.class, null, DOIT_TOOLTIP);
        preview=(JButton)initButton(PREVIEW_IMAGE_FILE,  shapeToolbar, tracker, idCounter++, JButton.class, null, DOIT_TOOLTIP);
        
        
        
        //Edit Toolbar
        moveBackButton= (JButton)initButton(MOVEBACK_IMAGE_FILE,  editToolbar, tracker, idCounter++, JButton.class, null, MOVEBACK_TOOLTIP);
        moveFrontButton= (JButton)initButton(MOVEFRONT_IMAGE_FILE,  editToolbar, tracker, idCounter++, JButton.class, null, MOVEFRONT_TOOLTIP);
        
        fontLabel=new JLabel("Test Pane");
        
        
        fileChooser = new JFileChooser("data/template");
        fileChooser.setAcceptAllFileFilterUsed(false);
        
        textArea=new JTextArea(2,17);
        menuBar=new MenuGUI();
        chooser=new JFileChooser();
        
        selectedTabPane=0;
        
        
    }
  
    
    
    
     private AbstractButton initButton(   String imageFile, 
                                        Container parent, 
                                        MediaTracker tracker, 
                                        int id, 
                                        Class buttonType,
                                        ButtonGroup bg,
                                        String tooltip)
    {
        try 
        {
            // LOAD THE IMAGE AND MAKE AN ICON
            Image img = batchLoadImage(imageFile, tracker, id);
            ImageIcon ii = new ImageIcon(img);
            
            // HERE'S REFLECTION MAKING OUR OBJECT USING IT'S CLASS
            // NOTE THAT DOING IT LIKE THIS CALLS THE buttonType
            // CLASS' DEFAULT CONSTRUCTOR, SO WE MUST MAKE SURE IT HAS ONE
            AbstractButton createdButton;
            createdButton = (AbstractButton)buttonType.newInstance();
            
            // NOW SETUP OUR BUTTON FOR USE
            createdButton.setIcon(ii);
            createdButton.setToolTipText(tooltip);
            parent.add(createdButton);
            
            // INSETS ARE SPACING INSIDE THE BUTTON,
            // TOP LEFT RIGHT BOTTOM
            Insets buttonMargin = new Insets(   
                    BUTTON_INSET, BUTTON_INSET, BUTTON_INSET, BUTTON_INSET);
            createdButton.setMargin(buttonMargin);
            
            // ADD IT TO ITS BUTTON GROUP IF IT'S IN ONE
            if (bg != null)
            {
                bg.add(createdButton);
            }
            
            // AND RETURN THE SETUP BUTTON
            return createdButton;
        } 
        catch (InstantiationException | IllegalAccessException ex) 
        {
            // WE SHOULD NEVER GET THIS ERROR, BUT WE HAVE TO PUT
            // A TRY CATCH BECAUSE WE'RE USING REFLECTION TO DYNAMICALLY
            // CONSTRUCT OUR BUTTONS BY CLASS NAME
            Logger.getLogger(GreetGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        // THIS WOULD MEAN A FAILURE OF SOME SORT OCCURED
        return null;
    }
     
      private Image batchLoadImage(String imageFile, MediaTracker tracker, int id)
    {
        Toolkit tk = Toolkit.getDefaultToolkit();
        Image img = tk.getImage(imageFile);
        tracker.addImage(img, id);        
        return img;
    }

   
    private void layoutGUIControls() {
        
         // LET'S PUT THE TWO CANVASES INSIDE 
        // THE SPLIT PANE. WE'LL PUT THE DIVIDER
        // RIGHT IN THE MIDDLE AND WON'T LET
        // THE USER MOVE IT - FOOLPROOF DESIGN!
        canvasSplitPane.setLeftComponent(canvasLeft);
        //canvasSplitPane.setRightComponent(canvasRight);
        innerPanels[selectedTabPane].add(fontLabel);
        
        canvasSplitPane.setRightComponent(tabPane);
        
        canvasSplitPane.setResizeWeight(0.5);
        canvasSplitPane.setDividerLocation(TEMP_PANEL_WIDTH);
        canvasSplitPane.setEnabled(false);
         shapeToolbar=new JToolBar();
        // PUT THE COMBO BOX IN THE SHAPE TOOLBAR
       // shapeToolbar.add(pageComboBox);
        shapeToolbar.add(browseButton);
        shapeToolbar.add(selectButton);
        shapeToolbar.add(addTabButton);
        shapeToolbar.add(addImageButton);
        shapeToolbar.add(selectImageButton);
        shapeToolbar.add(zoomInButton);
        shapeToolbar.add(zoomOutButton);
        shapeToolbar.add(customColorSelectorButton);
        
        //Edittoolbar 
        editToolbar.add(moveBackButton);
        editToolbar.add(moveFrontButton);
        editToolbar.add(preview);
        
        //TextToolbar
        textToolbar.add(textArea);
        textToolbar.add(doIt);
       // textToolbar.add(preview);
        
        //north of north panel
        northOfNorthPanel.add(shapeToolbar);
        northOfNorthPanel.add(editToolbar);
        northOfNorthPanel.add(fontChooserToolBar);
        northOfNorthPanel.add(textToolbar);
        
        
        
        northPanel.setLayout(new BorderLayout());
        northPanel.add(northOfNorthPanel, BorderLayout.NORTH);
        northPanel.add(southOfNorthPanel, BorderLayout.SOUTH);  
        
        add(northPanel, BorderLayout.NORTH);
        add(canvasSplitPane, BorderLayout.CENTER);
        canvasLeft.setLayout(new FlowLayout(FlowLayout.LEFT));
        canvasLeft.add(menuBar);
        canvasLeft.repaint();
       //canvasRight.repaint();
        tabPane.repaint();
       
        
        
        
    }

    private void initHandlers() {
        //THIS WILL HANDLE THE SCENARIO WHEN THE USER CLICKS ON
        // THE X BUTTON, WE'LL WANT A CUSTOM RESPONSE
        GreetWindowHandler pwh = new GreetWindowHandler();
        this.addWindowListener(pwh);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
         // FILE TOOLBAR HANDLER
        BrowseTemplateHandler bth = new BrowseTemplateHandler();
        browseButton.addActionListener(bth);
        SelectTemplateHandler sth = new SelectTemplateHandler();
        selectButton.addActionListener(sth);
        AddPageHandler aph = new AddPageHandler();
        addTabButton.addActionListener(aph);
        
        AddImageHandler aih = new AddImageHandler();
        addImageButton.addActionListener(aih);
        
        tpsh=new TabPaneSelectedHandler();
        tabPane.addChangeListener(tpsh);
        
        SelectImageHandler sih=new SelectImageHandler();
        selectImageButton.addActionListener(sih);
        
        CanvasMouseHandler cmh=new CanvasMouseHandler();
        canvasRight.addMouseListener(cmh);
        canvasRight.addMouseMotionListener(cmh);  
        
        ZoomInHandler zih=new ZoomInHandler();
        zoomInButton.addActionListener(zih);
        
        ZoomOutHandler zoh=new ZoomOutHandler();
        zoomOutButton.addActionListener(zoh);
        
        MoveBackHandler mbh= new MoveBackHandler();
        moveBackButton.addActionListener(mbh);
        
        MoveFrontHandler mfh= new MoveFrontHandler();
        moveFrontButton.addActionListener(mfh);
        
        JFontToolHandler jfh= new JFontToolHandler();
        fontChooserToolBar.addFontChooserListener(jfh);
        
        ApplyFontHandler afh=new ApplyFontHandler();
        doIt.addActionListener(afh);
        Image img;
        img=canvasRight.createImage(50, 50);
        
        
        PreviewHandler ph=new PreviewHandler();
        preview.addActionListener(ph);
        
        MoveTextHandler mth = new MoveTextHandler();
        fontLabel.addMouseListener(mth);
        fontLabel.addMouseListener(cmh);
        
        
       // repaint();
            
        
    }
    
    
  
    
    public String getAppName() { 
        return appName; 
    }
    
    public TemplateLeftCanvas getLeftCanvas(){
        return this.canvasLeft;
    }
    
    public TemplateCanvas getRightCanvas(){
        return this.canvasRight;
    }
    
    public void setNameTemp(String name){this.nameOfTemplate=name; }
    
    public int getIndex(){
        return this.index;
    }
    
    public void incrementIndex(){
        if(this.index<9){
            index++;
        }else{
            index=0;
        }
    }
    
    public TemplateCanvas[] getInnerPanel(){
    
        return innerPanels;
    }
    
    public int getInnerPanelIndex(){
        return panelIndex;
    }
    public void setPanelIndex(int i){
        panelIndex=i;
    }
    
    public void incrementPanelIndex(){
        panelIndex++;
    }
    
    public String [] getNames(){
        return names;
    }

    public void addTabPane() {
        TemplateCanvas panel=new TemplateCanvas();
        if(panelIndex>3){
            return;
        }
        innerPanels[panelIndex]=panel;
        tabPane.addTab(panelIndex+".page", innerPanels[panelIndex]);
        
        CanvasMouseHandler cmh=new CanvasMouseHandler();
        innerPanels[panelIndex].addMouseListener(cmh);
        innerPanels[panelIndex].addMouseMotionListener(cmh);
        
        if(panelIndex<4){
            panelIndex++;
        }
        
        repaint();
        
    }
    
    public void addTabPane(TemplateCanvas canvas) {
        TemplateCanvas panel=canvas;
        if(panelIndex>3){
            return;
        }
        innerPanels[panelIndex]=panel;
        tabPane.addTab(panelIndex+".page", innerPanels[panelIndex]);
        
        CanvasMouseHandler cmh=new CanvasMouseHandler();
        innerPanels[panelIndex].addMouseListener(cmh);
        innerPanels[panelIndex].addMouseMotionListener(cmh);
        
        if(panelIndex<4){
            panelIndex++;
        }
        
        repaint();
        
    }
    
    public void removePanels(){
        while(panelIndex!=1){
            tabPane.removeTabAt(panelIndex);
            panelIndex--;
        }
        
    }

    public void addImage() {
        
        BufferedImage img = null;
        int retVal = fileChooser.showOpenDialog(this);
        
           
	if (retVal == JFileChooser.APPROVE_OPTION) {
            
                File myFile = fileChooser.getSelectedFile();
                
		try {
                    img = ImageIO.read(myFile);
                }catch( IOException e ){ 
                    e.printStackTrace(); 
		}
                
                GreetImage t1=new GreetImage(img);
                innerPanels[selectedTabPane].getPose().addShape(t1);
                repaint();
        }
                
    }

    public void selectedTabPane(int in) {
        selectedTabPane=in;
       
    }
    
    public TemplateCanvas getSelectedTabPane() {
        
        return innerPanels[selectedTabPane];
    }
   
    public void startShapeSelection(){
        
       //setState(GreetState.SELECT_SHAPE_STATE);
         
    }
    
    public void setLabel(Font f){
        fontLabel.setFont(f);
        this.font=f;
        repaint();
        
    }

    public void setLocale(int x, int y){
        //innerPanels[selectedTabPane].remove(fontLabel);
        fontLabel.setLocation(x, y);
       // innerPanels[selectedTabPane].add(fontLabel);
        
       // repaint();
    }
    
    public void setText() {
        String s="testing";
        try{
             s=textArea.getText();
            if(s!=null){
                fontLabel.setText(s);
                userText=s;
            }
        }catch(NullPointerException e){
            return;
        } 
        
       
        
        
        
        repaint();
        
    }
    
    

   
    
   
    public BufferedImage getSnapShot(JPanel panel ){  
       BufferedImage bufImg = new BufferedImage(panel.getSize().width, panel.getSize().height,BufferedImage.TYPE_INT_RGB);  
       panel.paint(bufImg.createGraphics());
        return bufImg;
    } 

    public void seriablizePanels() {
       
   
       try {
            FileOutputStream fileOut = new FileOutputStream("employee.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(tabPane);
            out.close();
            fileOut.close();
       }catch (IOException i) {
            i.printStackTrace();
      }
    
       
    }
    
    
    public void load() {
            
               
                tabPane.removeChangeListener(tpsh);
		// show file chooser dialog
		int r = chooser.showOpenDialog(null);
                LinkedList<byte []>temp=new LinkedList<>();
		// if file selected, open
		if (r == JFileChooser.APPROVE_OPTION) {
			try {
				System.out.println("i entered here");
				File file = chooser.getSelectedFile();
			
                                XMLDecoder decoder = new XMLDecoder(new FileInputStream(file.getAbsolutePath()));
				pane=(JTabbedPane)decoder.readObject();
                                //temp=(LinkedList<byte[]>)decoder.readObject();
                                //getImages(temp);
                                
				decoder.close();
			}catch (IOException e) {
				JOptionPane.showMessageDialog(null, e);
                                e.printStackTrace();
			}
		}
                
                if(pane!=null){
               // tabPane.addTab("TEST", pane);
                 tabPane.removeAll();
                 
                 
                 panelIndex=0;
                 
                }
               
               tabPane.addChangeListener(tpsh);
               repaint();
               
    }

	/*
	 * save logic, mainly for HW2
	 */
	
    
    public void save(){
        tabPane.removeChangeListener(tpsh);
        if(chooser.showSaveDialog(null)==JFileChooser.APPROVE_OPTION)
        {
             
           
            try{
                File file = chooser.getSelectedFile();
                XMLEncoder encoder = new XMLEncoder(new FileOutputStream(file));
                //if (tabPane != null) {
                 canvasRight.getPose().getImagesList();
                 
                 encoder.writeObject(canvasRight);
                 
                //}
                encoder.close();
           }
           catch(IOException e)
           {
                JOptionPane.showMessageDialog(null, e);
           }
        }
        tabPane.addChangeListener(tpsh);
        
    }
    
    
    public void save1(){
      if(chooser.showSaveDialog(null)==JFileChooser.APPROVE_OPTION){  
        try { 
            File file = chooser.getSelectedFile();
           
            
            LinkedList<byte []> temp=canvasRight.getPose().getByteImageList();
            System.out.println("object1: " + temp); 
            FileOutputStream fos = new FileOutputStream(file.getName()); 
            ObjectOutputStream oos = new ObjectOutputStream(fos); 
            oos.writeObject(temp); 
            oos.flush(); 
            oos.close(); 
        } 
        catch(Exception e) { 
            System.out.println("Exception during serialization: " + e); 
            e.printStackTrace();
            } 
    
    
    }
    }

    public void load1() {
            
               
                
        
                tabPane.removeChangeListener(tpsh);
		// show file chooser dialog
		int r = chooser.showOpenDialog(null);
                LinkedList<byte []>temp=new LinkedList<>();
                LinkedList<BufferedImage>tempBuffered=new LinkedList();
		// if file selected, open
		if (r == JFileChooser.APPROVE_OPTION) {
			try {
				System.out.println("i entered here");
				File file = chooser.getSelectedFile();
			
                       
                                //MyClass object2; 
                                FileInputStream fis = new FileInputStream(file.getAbsolutePath()); 
                                ObjectInputStream ois = new ObjectInputStream(fis); 
                                temp = (LinkedList<byte []>)ois.readObject(); 
                                tempBuffered=getImages(temp);
                                ois.close(); 
                                 System.out.println("object2: " + temp); 
                        } 
                        catch(Exception e) { 
                        System.out.println("Exception during deserialization: " + 
                           e); 
                        }
                }
                
               canvasRight.add(new JLabel(new ImageIcon( tempBuffered.get(2) )));
               
               repaint();
               
    }
    
    public LinkedList<BufferedImage> getImages(LinkedList<byte []> byteImages) throws IOException{
    
        LinkedList<BufferedImage> listImage=new LinkedList<>();
        
        for(int i=0;i<byteImages.size();i++){
            
            InputStream in = new ByteArrayInputStream(byteImages.get(i));
            BufferedImage img = ImageIO.read(in);  
            listImage.add(img);
        }
        return listImage;
    }
    
    
    public JTabbedPane getTabPane(){
    
        return tabPane;
    }
    
    
    public GreetIO getGreetIO(){
        
        return greetIO;
    }
    
    public GreetPose getGreetPose(){
    
        return greetPose;
    }
    
    public void setInnerPanels(TemplateCanvas [] temp){
        
        tabPane.removeChangeListener(tpsh);
     
        TemplateCanvas canvasRight2=new TemplateCanvas(temp[0].getPose());
        canvasRight2.updateUI();
        //removePanels();
        
        CanvasMouseHandler cmh=new CanvasMouseHandler();
       
        canvasRight2.addMouseMotionListener(cmh); 
       
       // this.addTabPane(temp[0]);
        this.addTabPane(temp[1]);
        this.addTabPane(temp[2]);
        this.addTabPane(temp[3]);
        temp[1].addMouseListener(cmh);
        temp[1].addMouseMotionListener(cmh);
        temp[2].addMouseListener(cmh);
        temp[2].addMouseMotionListener(cmh);
        temp[3].addMouseListener(cmh);
        temp[3].addMouseMotionListener(cmh);
        
       
        tabPane.addChangeListener(tpsh);    
       
        repaint();
      
    }
}

