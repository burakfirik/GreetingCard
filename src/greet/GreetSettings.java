/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author burak firik
 * cse333
 * 108428236
 */
package greet;

import java.awt.Color;
import java.awt.Font;

/**
 *
 * @author burak
 */
public class GreetSettings {
    
    public static final int TEMP_PANEL_WIDTH=600;
    public static final int TEMP_PANEL_HEIGHT=500;
    public static final int FRAME_WIDTH=1220;
    public static final int FRAME_HEIGHT=550;
    
    
    public static final int WIDTH = 680;
    public static final int HEIGHT = 480;
    private static final int GRID_ROWS = 8;
    private static final int GRID_COLS = 6;
    private static final int GAP = 3;
    
    
   /************* FILE LOADING CONSTANTS *************/

    /***** PATHS *****/
    // FIRST, ALL THE PATHS WE'LL WANT TO USE FOR LOCATING FILES 
    public static final String APP_DATA_PATH = "./data/";
    public static final String POSES_PATH = APP_DATA_PATH + "poses/";
    public static final String SETTINGS_PATH = APP_DATA_PATH + "settings/";
    public static final String EXPORTED_IMAGES_PATH = APP_DATA_PATH + "exported_images/";
    public static final String BUTTON_IMAGES_PATH = "./data/buttons/";
    public static final String SNAPSHOT_IMAGES_PATH = "./data/snapshots/";
    public static final String GREETINGCARDS="./data/Greetingcards/";
    public static final String GREETING_NAME_REQUEST_TEXT="What do you want to name your sprite?";
    public static final String GREETING_NAME_REQUEST_TITLE_TEXT="What do you want to name your sprite?";
    public static final String GREETING_SAVED="You greeting card is saved.";
    public static final String GREETING_SAVING="Your card is being saved";
    
    /***** SCHEMAS *****/
    // ALL THE .xsd FILES WE'LL NEED TO VALIDATE XML 
    public static final String SCHEMA_STANDARD_SPEC_URL = "http://www.w3.org/2001/XMLSchema";    
    public static final String POSE_SCHEMA = POSES_PATH + "poseur_pose.xsd";
    public static final String WINDOW_SETTINGS_SCHEMA = SETTINGS_PATH + "window_settings.xsd";
    public static final String COLOR_PALLET_SETTINGS_SCHEMA = SETTINGS_PATH + "color_pallet_settings.xsd";

    /***** XML SETTINGS FILES *****/
    // ALL THE .xml FILES WE'LL USE TO SETUP THE APPLICATION
    public static final String WINDOW_SETINGS_XML = SETTINGS_PATH + "poseur_window_settings.xml";
    public static final String COLOR_PALLET_SETTINGS_XML = SETTINGS_PATH + "poseur_color_pallet_settings.xml";
    
    /***** FILE EXTENSIONS *****/
    // WE'LL NEED THESE TO DYNAMICALLY BUILD TEXT
    public static final String PNG_FORMAT_NAME = "png";
    public static final String PNG_FILE_EXTENSION = "." + PNG_FORMAT_NAME;
    public static final String POSE_FILE_EXTENSION = ".pose";
    public static final String STROKE_SELECTION_FILE_PREFIX = BUTTON_IMAGES_PATH + "Stroke";
    public static final String ZOOM_LABEL_TEXT_PREFIX = "Zoom: ";
    public static final String ZOOM_LABEL_TEXT_POSTFIX = "X";
    public static final int    NUM_STROKES_TO_CHOOSE_FROM = 9;  
    public static final String APP_NAME_FILE_NAME_SEPARATOR = " - ";
    
    /***** BUTTON IMAGE FILES *****/
    // THIS LISTS ALL THE IMAGES WE'LL USE ON OUR BUTTONS. THEY
    // ARE GROUPED BY TOOLBAR
    
    // BUTTON IMAGES FOR FILE TOOLBAR
    public static final String NEW_IMAGE_FILE = BUTTON_IMAGES_PATH + "New.png";
    public static final String OPEN_IMAGE_FILE = BUTTON_IMAGES_PATH + "Open.png";
    public static final String SAVE_IMAGE_FILE = BUTTON_IMAGES_PATH + "Save.png";
    public static final String SAVEAS_IMAGE_FILE = BUTTON_IMAGES_PATH + "SaveAs.png";
    public static final String EXPORT_IMAGE_FILE = BUTTON_IMAGES_PATH + "Export.png";
    public static final String EXIT_IMAGE_FILE = BUTTON_IMAGES_PATH + "Exit.png";

    // BUTTON IMAGES FOR EDIT TOOLBAR
    public static final String SELECTION_IMAGE_FILE = BUTTON_IMAGES_PATH + "Selection.png";
    public static final String ADD_IMAGE_FILE = BUTTON_IMAGES_PATH + "Add.png";
    public static final String ADDIMAGE_IMAGE_FILE = BUTTON_IMAGES_PATH + "Addimage.png";
    public static final String SELECT_IMAGE_FILE = BUTTON_IMAGES_PATH + "Selecticon.png";
    public static final String ZOOMIN_IMAGE_FILE = BUTTON_IMAGES_PATH + "Zin.png";
    public static final String ZOOMOUT_IMAGE_FILE = BUTTON_IMAGES_PATH + "Zout.png";
    public static final String CUSTOM_IMAGE_FILE = BUTTON_IMAGES_PATH + "Custom.png";
    public static final String DOIT_IMAGE_FILE = BUTTON_IMAGES_PATH + "Doit.png";
    public static final String PREVIEW_IMAGE_FILE = BUTTON_IMAGES_PATH + "Preview.png";
    
    public static final String CUT_IMAGE_FILE = BUTTON_IMAGES_PATH + "Cut.png";
    public static final String COPY_IMAGE_FILE = BUTTON_IMAGES_PATH + "Copy.png";
    public static final String PASTE_IMAGE_FILE = BUTTON_IMAGES_PATH + "Paste.png";
    public static final String MOVETOBACK_IMAGE_FILE = BUTTON_IMAGES_PATH + "MoveToBack.png";
    public static final String MOVETOFRONT_IMAGE_FILE = BUTTON_IMAGES_PATH + "MoveToFront.png";
    public static final String BROWSE_IMAGE_FILE = BUTTON_IMAGES_PATH + "Next.png";
    
    public static final String MOVEBACK_IMAGE_FILE = BUTTON_IMAGES_PATH + "MoveToFront.png";
    public static final String MOVEFRONT_IMAGE_FILE = BUTTON_IMAGES_PATH + "MoveToBack.png";
    
    
    

    // BUTTON IMAGES FOR ZOOM TOOLBAR
    public static final String ZOOM_IN_IMAGE_FILE = BUTTON_IMAGES_PATH + "ZoomIn.png";
    public static final String ZOOM_OUT_IMAGE_FILE = BUTTON_IMAGES_PATH + "ZoomOut.png";
    public static final String POSE_DIMENSIONS_IMAGE_FILE = BUTTON_IMAGES_PATH + "Dimensions.png";
    
    
    
   
   
    
    // BUTTON IMAGES FOR THE SHAPES TOOLBAR
    public static final String RECT_SELECTION_IMAGE_FILE = BUTTON_IMAGES_PATH + "Rect.png";
    public static final String CIRCLE_IMAGE_FILE=BUTTON_IMAGES_PATH+"Circle.png";
    public static final String LINE_IMAGE_FILE = BUTTON_IMAGES_PATH + "Line.png";
    
    public static final String COLOR_IMAGE_FILE=BUTTON_IMAGES_PATH+"Color.png";
    
    
    // BUTTON IMAGES FOR THE COLORS TOOLBAR
    public static final String CUSTOM_COLOR_SELECTOR_IMAGE_FILE = BUTTON_IMAGES_PATH + "CustomColor.png";
    public static final String FILL_COLOR_IMAGE_FILE = BUTTON_IMAGES_PATH + "Fill.png";
    public static final String OUTLINE_COLOR_IMAGE_FILE = BUTTON_IMAGES_PATH + "Outline.png";
    
    
    /***** TOOLTIPS (MOUSE-OVER TEXT) *****/
    // THIS LISTS ALL THE TOOLTIPS FOR OUR CONTROLS. THEY
    // ARE GROUPED BY TOOLBAR
    
    // TOOLTIPS FOR CONTROLS ON FILE TOOLBAR
    public static final String NEW_TOOLTIP = "New Pose";
    public static final String OPEN_TOOLTIP = "Open Pose";
    public static final String SAVE_TOOLTIP = "Save Pose";
    public static final String SAVEAS_TOOLTIP = "Save As Pose";
    public static final String EXPORT_TOOLTIP = "Export Pose to Image";
    public static final String EXIT_TOOLTIP = "Exit Application";

    // TOOLTIPS FOR CONTROLS ON EDIT TOOLBAR
    public static final String SELECT_TOOLTIP = "Select Currently Displayed Template";
    public static final String ADD_TOOLTIP = "Add a new page";
    public static final String ADDIMAGE_TOOLTIP = "Add a new image";
    public static final String SELECTIMAGE_TOOLTIP = "Select an image";
    public static final String ZOOMIN_TOOLTIP = "Enlarge the image";
    public static final String ZOOMOUT_TOOLTIP = "Reduce the image ";
    public static final String CUSTOM_TOOLTIP = "Chose a custom color.";
    public static final String DOIT_TOOLTIP = "Apply the text to the Canvas.";
    
    public static final String CUT_TOOLTIP = "Cut Selected Item";
    public static final String COPY_TOOLTIP = "Copy Selected Item";
    public static final String PASTE_TOOLTIP = "Paste from Clipboard";
    public static final String MOVETOBACK_TOOLTIP = "Move the selected Item to back";
    public static final String MOVETOFRONT_TOOLTIP = "Move the selected Item to front";
    public static final String BROWSE_TOOLTIP = "Move back the selected item";
    
    public static final String MOVEBACK_TOOLTIP = "Move back the selected item";
    public static final String MOVEFRONT_TOOLTIP = "Move front the selected item";

    // TOOLTIPS FOR CONTROLS ON ZOOM TOOLBAR
    public static final String ZOOM_IN_TOOLTIP = "Zoom In";
    public static final String ZOOM_OUT_TOOLTIP = "Zoom Out";
    public static final String CHANGE_POSE_DIMENSIONS_TOOLTIP = "Change Pose Dimensions";

    // TOOLIPS FOR CONTROLS ON SHAPES TOOLBAR
    public static final String RECT_TOOLTIP = "Draw a Rectangle";
    public static final String STROKE_TOOLTIP = "Select Stroke";
    public static final String CIRC_TOOLTIP = "Draw a Circle";
    public static final String LINE_TOOLTIP = "Draw a Line";

    // TOOLTIPS FOR CONTROLS ON COLORS TOOLBAR
    public static final String OUTLINE_TOOLTIP = "Select Outline Color";
    public static final String FILL_TOOLTIP = "Select Fill Color";
    public static final String PALLET_TOOLTIP = "Select Pallet Color";
    public static final String CUSTOM_COLOR_TOOLTIP = "Make a Custom Color";
    public static final String ALPHA_TOOLTIP = "Select Shape Alpha Value";

    /***** COLORS *****/
    // WE'LL USE THESE COLOR FOR OUR USER INTERFACE
    public static final Color   UNASSIGNED_COLOR_PALLET_COLOR = Color.GRAY;
    public static final Color   POSE_BACKGROUND_COLOR = new Color(220,220,220);
    public static final Color   TRANSPARENT_BACKGROUND_COLOR = new Color(255,255,255,0);
    public static final Color   TRUE_CANVAS_COLOR = Color.LIGHT_GRAY;
    public static final Color   ZOOMABLE_CANVAS_COLOR = new Color(012,123,234);    
    public static final Color   SELECTED_OUTLINE_COLOR = new Color(255, 130, 130);
    public static final Color   MOUSE_OVER_OUTLINE_COLOR = new Color(130, 255, 255);
    public static final Color   DEBUG_TEXT_COLOR = Color.WHITE;
    public static final Color   ALPHA_BACKGROUND_COLOR = new Color(255, 255, 160);
    
    /***** XML FILE PROCESSING *****/
    // WE'LL BE LOADING AND SAVING STUFF FROM AND TO ZML FILES, SO THESE WILL HELP
    
    // COLOR XML FILE NODE NAMES - NOTE THAT WE'LL USE THESE
    // FOR BOTH .pose FILES AND .xml SETTINGS FILES
    public static final String ALPHA_NODE = "alpha";
    public static final String RED_NODE = "red";
    public static final String GREEN_NODE = "green";
    public static final String BLUE_NODE = "blue";

    // .pose DATA FILE NODE AND ATTRIBUTE NAMES
    public static final String POSEUR_POSE_NODE = "poseur_pose";
    public static final String POSE_WIDTH_NODE = "pose_width";
    public static final String POSE_HEIGHT_NODE = "pose_height";
    public static final String NUM_SHAPES_NODE = "num_shapes";
    public static final String SHAPES_LIST_NODE = "shapes_list";
    public static final String POSEUR_SHAPE_NODE = "poseur_shape";
    public static final String OUTLINE_THICKNESS_NODE = "outline_thickness";
    public static final String OUTLINE_COLOR_NODE = "outline_color";
    public static final String FILL_COLOR_NODE = "fill_color";
    public static final String GEOMETRY_NODE = "geometry";
    public static final String SHAPE_TYPE_ATTRIBUTE = "shape_type";
    public static final String X_ATTRIBUTE = "x";
    public static final String Y_ATTRIBUTE = "y";
    public static final String WIDTH_ATTRIBUTE = "width";
    public static final String HEIGHT_ATTRIBUTE = "height";
    public static final String X1_ATTRIBUTE = "x1";
    public static final String Y1_ATTRIBUTE = "y1";
    public static final String X2_ATTRIBUTE = "x2";
    public static final String Y2_ATTRIBUTE = "y2";
    
    // COLOR PALLET .xml FILE NODE NAMES
    public static final String PALLET_SIZE_NODE    = "pallet_size";
    public static final String PALLET_ROWS_NODE    = "pallet_rows";
    public static final String PALLET_COLOR_NODE   = "pallet_color";
    public static final String DEFAULT_COLOR_NODE  = "default_color";

    // WINDOW SETTINGS .xml FILE NODE NAMES 
    public static final String APP_TITLE_NODE           = "app_title";
    public static final String WINDOW_WIDTH_NODE        = "window_width";
    public static final String WINDOW_HEIGHT_NODE       = "window_height";
    public static final String WINDOW_RESIZABLE_NODE    = "window_resizable";
    public static final String WINDOW_CENTERED_NODE     = "window_centered";
    
    // FOR NICELY FORMATTED XML OUTPUT
    public static final String XML_INDENT_PROPERTY = "{http://xml.apache.org/xslt}indent-amount";
    public static final String XML_INDENT_VALUE = "5";
    public static final String YES_VALUE = "yes";

    /***** DIALOG MESSAGES AND TITLES *****/
    // DIALOG BOX MESSAGES TO GIVE FEEDBACK BACK TO THE USER
    public static final String SELECT_CUSTOM_COLOR_TEXT = "Select Custom Color";
    public static final String PROMPT_TO_SAVE_TEXT = "Would you like to save your Pose?";
    public static final String PROMPT_TO_SAVE_TITLE_TEXT = "Save your pose?";
    public static final String POSE_NAME_REQUEST_TEXT = "What do you want to name your pose?";
    public static final String POSE_NAME_REQUEST_TITLE_TEXT = "Enter Pose File Name";
    public static final String IMAGE_EXPORTED_TEXT = "Your pose has been exportetd to ";
    public static final String IMAGE_EXPORTED_TITLE_TEXT = "Image Exported";
    public static final String IMAGE_EXPORTING_ERROR_TEXT = "An Error Occured While Exporting the Image";
    public static final String IMAGE_EXPORTING_ERROR_TITLE_TEXT = "Image Exporting Error";
    public static final String POSE_SAVED_TEXT = "Pose File has been Saved";
    public static final String POSE_SAVED_TITLE_TEXT = "Pose File Saved";
    public static final String POSE_SAVING_ERROR_TEXT = "An Error Occured While Saving the Pose";
    public static final String POSE_SAVING_ERROR_TITLE_TEXT = "Pose Saving Error";
    public static final String POSE_LOADED_TEXT = "Pose File has been Loaded";
    public static final String POSE_LOADED_TITLE_TEXT = "Pose File Loaded";
    public static final String POSE_LOADING_ERROR_TEXT = "An Error Occured While Loading the Pose";
    public static final String POSE_LOADING_ERROR_TITLE_TEXT = "Pose Loading Error";
    public static final String CHANGE_POSE_DIMENSIONS_TEXT = "Enter New Pose Dimensions";
    public static final String CHANGE_POSE_DIMENSIONS_TITLE_TEXT = "Change Pose Dimensions";
    public static final String ENTER_POSE_WIDTH_TEXT = "Enter Pose Width: ";
    public static final String ENTER_POSE_HEIGHT_TEXT = "Enter Pose Height: ";
    public static final String INVALID_POSE_DIMENSIONS_ENTRY_TEXT = "Invalid Pose Dimensions Entered";
    public static final String INVALID_POSE_DIMENSIONS_TITLE_TEXT = "Invalid Dimensions Entry";
    public static final String LOADING_XML_ERROR_TEXT = "Error Loading XML";
    public static final String EXPORT_POSE_TEXT = "Pose will be exported to ";
    public static final String EXPORT_POSE_TITLE_TEXT = "Export Pose?";
    
    /***** POSE DIMENSIONS DIALOG TEXT *****/
    public static final String POSE_DIMENSIONS_DIALOG_TITLE = "Changing Pose Dimensions";
    public static final String POSE_DIMENSIONS_PROMPT_TEXT = "Enter New Pose Dimensions";
    public static final String POSE_WIDTH_PROMPT_TEXT = "Enter Pose Width: ";
    public static final String POSE_HEIGHT_PROMPT_TEXT = "Enter Pose Height: ";
    public static final String OK_TEXT = "Ok";
    public static final String CANCEL_TEXT = "Cancel";
    
    /***** ZOOM CONTROL SETTINGS *****/
    public static final float   INIT_ZOOM_LEVEL = 1.0f;
    public static final float   ZOOM_FACTOR = 0.1f;
    public static final float   MIN_ZOOM_LEVEL = 0.1f;
    
    /***** TRANSPARENCY SLIDER SETTINGS *****/
    public static final String  ALPHA_LABEL_TEXT = "  " + (char)(0x03B1) + ": ";
    public static final Font    ALPHA_LABEL_FONT = new Font("Serif", Font.BOLD, 24);
    public static final int     TRANSPARENT = 0;
    public static final int     OPAQUE = 255;
    public static final int     ALPHA_MINOR_TICK_SPACING = 8;
    public static final int     ALPHA_MAJOR_TICK_SPACING = 255;

    /***** FONTS *****/
    public static final Font    DEBUG_TEXT_FONT = new Font("Monospaced", Font.BOLD, 16);
    public static final Font    ZOOM_LABEL_FONT = new Font("Serif", Font.PLAIN, 18);
    
    /***** POSE DIMENSIONS AND GEOMETRY SETTINGS ****/
    public static final int     DEFAULT_POSE_WIDTH = 450;
    public static final int     DEFAULT_POSE_HEIGHT = 450;
    public static final int     LINE_SELECTION_TOLERANCE = 5;
    
    /***** DEBUG TEXT SETTINGS *****/
    public static final int     DEBUG_TEXT_START_X = 50;
    public static final int     DEBUG_TEXT_START_Y = 50;
    public static final int     DEBUG_TEXT_LINE_SPACING = 20;
    public static final boolean DEFAULT_DEBUG_TEXT_ENABLED = true;
    
    /***** POSE DIMENSIONS DIALOG SETTINGS *****/
    public static final int     CONTROL_INSET = 3;
    public static final int     MIN_POSE_DIM = 1;
    public static final int     MAX_POSE_DIM = 2000;
    public static final String  INVALID_POSE_DIM_TEXT = "Error - You have entered an invalid dimension";
    public static final String  INVALID_POSE_DIM_TITLE_TEXT = "Invalid Dimension Entry";
    
    /***** ALL BUTTONS WILL USE THESE INSETS *****/
    public static final int     BUTTON_INSET = 2;
    
}
