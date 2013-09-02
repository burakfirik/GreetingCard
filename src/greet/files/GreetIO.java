/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package greet.files;

import greet.Greet;

import java.awt.HeadlessException;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import static greet.GreetSettings.*;
import greet.gui.GreetGUI;
import greet.gui.TemplateCanvas;
import greet.image.GreetImage;
import greet.state.GreetPose;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JTabbedPane;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import poseur.files.InvalidXMLFileFormatException;

/**
 *
 * @author burak
 */
public class GreetIO {
    
    
    public boolean saveGreet() {
        
        TemplateCanvas [] tabPane= Greet.getGreet().getGUI().getInnerPanel();
        
      //  LinkedList<GreetImage>imageList=pose.getImagesList();
       // Iterator imageIterator=pose.getShapesIterator();
        
        GreetGUI gui=Greet.getGreet().getGUI();
        String fileName = JOptionPane.showInputDialog(
                gui,
                GREETING_NAME_REQUEST_TEXT,
                GREETING_NAME_REQUEST_TITLE_TEXT,
                JOptionPane.QUESTION_MESSAGE);
        
         // AND NOW ASK THE USER FOR THE POSE TO OPEN
       // JFileChooser poseFileChooser = new JFileChooser(GREETINGCARDS);
       // int buttonPressed = poseFileChooser.showOpenDialog(gui);
        File currentFile;
        String currentFileName = null;
        // ONLY OPEN A NEW FILE IF THE USER SAYS OK
       // if (buttonPressed == JFileChooser.APPROVE_OPTION)
       
        try{
            //.data/Greetingcards/card1
            String path= "./data/Greetingcards/"+fileName;
            File currentFile1 = new File(path);
            currentFile1.mkdirs();
            //.data/Greetincards/card1.greet
            File xmlOutSideFolder = new File(("./data/Greetingcards/"+fileName+".greet"));
            //.data/Greetincards/card1/card1.greet
            File newXML=new File(path+"/"+fileName+".greet");
        
           
        try{
         // THESE WILL US BUILD A DOC
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
       
        
        
        // FIRST MAKE THE DOCUMENT
        Document doc = docBuilder.newDocument();
        Document docFolder=docBuilder.newDocument();
        // THEN THE ROOT ELEMENT
        Element rootElement = doc.createElement("greet");
        Element rootElementFolder=docFolder.createElement("name");
        
        
        
        doc.appendChild(rootElement);
        docFolder.appendChild(rootElementFolder);
       Element imageElementFolder = makeElement(docFolder, rootElementFolder, 
                   "greet", ""+fileName);
        int index=0;
        for(int i=0;i<gui.getInnerPanelIndex();i++){
        //##########here we start to iterate by the number of tabs in the greeting cards
        // THEN MAKE AND ADD THE WIDTH, HEIGHT, AND NUM SHAPES
            Element tabElement=makeElement(doc, rootElement,"tab");
        
            TemplateCanvas tempCanvas=(TemplateCanvas) tabPane[i];
            //GreetPose ps=gui.getGreetPose();
            GreetPose ps=tempCanvas.getPose();
            Iterator <GreetImage> imageIterator =ps.getShapesIterator();
            if(tempCanvas!=null)
            while(imageIterator.hasNext()){
                
                GreetImage image=imageIterator.next();
                BufferedImage imageToExport=image.getImage();
                
                File imageFile = new File(("./data/Greetingcards/" +fileName+"/"+index)+PNG_FILE_EXTENSION);
                
                try {
                    ImageIO.write(imageToExport, PNG_FORMAT_NAME, imageFile);
                } catch (IOException ex) {
                    Logger.getLogger(GreetIO.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                Element imageElement = makeElement(doc, tabElement,"image_file", ""+index+PNG_FILE_EXTENSION);
                imageElement.setAttribute("x", ""+image.getX());
                imageElement.setAttribute("y", ""+image.getY());
                
                /*
                Element heightTypeElement = makeElement(doc, rootElement, 
                    HEIGHT_NODE, ""+sprite.getHeight());
                Element imagesListTypeElement = makeElement(doc, rootElement, 
                    IMAGES_LIST_NODE, "" );
                */
            
            index++;
            }
            
        }
           
            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, YES_VALUE);
            transformer.setOutputProperty(XML_INDENT_PROPERTY, XML_INDENT_VALUE);
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(newXML);
            
            DOMSource sourceOutside = new DOMSource(docFolder);
            StreamResult resultOutside = new StreamResult(xmlOutSideFolder);
            
            // SAVE THE POSE TO AN XML FILE
            transformer.transform(source, result); 
            transformer.transform(sourceOutside, resultOutside); 
          
       
            
          
            
            
            JOptionPane.showMessageDialog(
                gui,
                GREETING_SAVED,
                GREETING_SAVING,
                JOptionPane.INFORMATION_MESSAGE);
            gui.setTitle(path+".greet");
            
            return true;
        }
        catch( ParserConfigurationException | TransformerException | DOMException | HeadlessException ex)
        {
            // SOMETHING WENT WRONG WRITING THE XML FILE
           
            JOptionPane.showMessageDialog(
                gui,
                POSE_SAVING_ERROR_TEXT,
                POSE_SAVING_ERROR_TITLE_TEXT,
                JOptionPane.ERROR_MESSAGE);  
            ex.printStackTrace();
            return false;
        }   
        
        }catch(NullPointerException e){
            e.printStackTrace();
            return false;
        }
        
    }
    
    
    public void openGreet() {
        GreetGUI gui=Greet.getGreet().getGUI();
        TemplateCanvas [] innerPans=new TemplateCanvas[5];
        
        for(int i=0;i<5;i++){
            innerPans[i]=new TemplateCanvas();
        }
        
        // AND NOW ASK THE USER FOR THE POSE TO OPEN
        JFileChooser poseFileChooser = new JFileChooser("./data/Greetingcards/");
        
        int buttonPressed = poseFileChooser.showOpenDialog(gui);
        String nameFile=poseFileChooser.getSelectedFile().getName();
        
         // THIS WILL HELP US LOAD STUFF
        XMLUtilities xmlUtil = new XMLUtilities();
        
        // WE'RE GOING TO LOAD IT INTO THE POSE
       
       int len=nameFile.length();
       String xmlFile=nameFile.substring(0,len-6)+"/"+poseFileChooser.getSelectedFile().getName();
            // LOAD THE XML FILE
            Document doc = null;
            WhitespaceFreeXMLDoc cleanDoc=null;
        try {
            doc = xmlUtil.loadXMLDocument("./data/Greetingcards/"+xmlFile);
             cleanDoc= loadXMLDocument("./data/Greetingcards/"+xmlFile);
        } catch (InvalidXMLFileFormatException ex) {
            Logger.getLogger(GreetIO.class.getName()).log(Level.SEVERE, null, ex);
        }
           
            
            // AND THEN EXTRACT ALL THE DATA
            WhitespaceFreeXMLNode tabRoot = cleanDoc.getRoot();
            
            Iterator<WhitespaceFreeXMLNode> tabIterator = tabRoot.getChildren();
            nameFile=nameFile.substring(0,nameFile.length()-6);
            NodeList tabs=doc.getElementsByTagName("tab");
            int i=0;      
            while(tabIterator.hasNext()){
               
                WhitespaceFreeXMLNode tab=tabIterator.next();
                
                Iterator<WhitespaceFreeXMLNode> imageIterator=tab.getChildren();
                GreetPose p1=new GreetPose();
                while(imageIterator.hasNext()){
                    WhitespaceFreeXMLNode imageNode=imageIterator.next();
                    String name =imageNode.getData();
                    int x=Integer.valueOf(imageNode.getAttributeValue("x"));
                    int y=Integer.valueOf(imageNode.getAttributeValue("y"));
                    BufferedImage imageTest = null;
                    GreetImage img;
                    try {
                        imageTest = ImageIO.read(new File("./data/Greetingcards/"+nameFile+"/"+name));
                    }catch (IOException ex) {
                        Logger.getLogger(TemplateCanvas.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    img=new GreetImage(imageTest);
                    img.setX(x);
                    img.setY(y);
                    p1.addShape(img);
                    
                }
                
               // xmlUtil.getChildNodeWithName(tab, "image_file");
              
                    
                   
               innerPans[i].setPose(p1);
               i++;
                
               
            }
            gui.setInnerPanels(innerPans);
            JOptionPane.showMessageDialog(
                gui,
                POSE_LOADED_TEXT,
                POSE_LOADED_TITLE_TEXT,
                JOptionPane.INFORMATION_MESSAGE);
    

        
        
          
        
    }
    
    
    
     /**
     * This helper method builds elements (nodes) for us to help with building
     * a Doc which we would then save to a file.
     * 
     * @param doc The document we're building.
     * 
     * @param parent The node we'll add our new node to.
     * 
     * @param elementName The name of the node we're making.
     * 
     * @param textContent The data associated with the node we're making.
     * 
     * @return A node of name elementName, with textComponent as data, in the doc
     * document, with parent as its parent node.
     */
    private Element makeElement(Document doc, Element parent, String elementName, String textContent)
    {
        Element element = doc.createElement(elementName);
        element.setTextContent(textContent);
        parent.appendChild(element);
        return element;
    }
    
    private Element makeElement(Document doc, Element parent, String elementName)
    {
        Element element = doc.createElement(elementName);
       // element.setTextContent(textContent);
        parent.appendChild(element);
        return element;
    }
    
    
     public WhitespaceFreeXMLDoc loadXMLDocument(String xmlFile)
            throws InvalidXMLFileFormatException
    {
        // FIRST LET'S VALIDATE IT
        
       

        // THIS IS JAVA API STUFF
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try
        {            
            // FIRST RETRIEVE AND LOAD THE FILE INTO A TREE
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document xmlDoc = db.parse(xmlFile);
            
            // THEN PUT IT INTO A FORMAT WE LIKE
            WhitespaceFreeXMLDoc cleanDoc = new WhitespaceFreeXMLDoc();
            cleanDoc.loadDoc(xmlDoc);
            return cleanDoc;
        }
        // THESE ARE XML-RELATED ERRORS THAT COULD HAPPEN DURING
        // LOADING AND PARSING IF THE XML FILE IS NOT WELL FORMED
        // OR IS NOW WHERE AND WHAT WE SAY IT IS
        catch(ParserConfigurationException pce)
        {
            pce.printStackTrace();
            return null;
        }
        catch(SAXException se)
        {
            se.printStackTrace();
            return null;
        }
        catch(IOException io)
        {
            io.printStackTrace();
            return null;
        }           
    }

   
    
}
