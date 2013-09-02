/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package greet.gui;

/**
 *
 * @author burak
 */


import greet.event.ExitHandler;
import greet.event.GreetWindowHandler;
import greet.event.serialize.OpenHandler;
import greet.event.serialize.SaveAsHandler;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class MenuGUI extends JPanel 
{
	private final int	ITEM_PLAIN	=	0;	// Item types
	private final int	ITEM_CHECK	=	1;
	private final int	ITEM_RADIO	=	2;

	private	JPanel		topPanel;
	private	JMenuBar	menuBar;
	private	JMenu		menuFile;
	private	JMenu		menuEdit;
	private	JMenu		menuProperty;
	private	JMenuItem	menuPropertySystem;
	private	JMenuItem	menuPropertyEditor;
	private	JMenuItem	menuPropertyDisplay;
	private	JMenuItem	menuFileNew;
	private	JMenuItem	menuFileOpen;
	private	JMenuItem	menuFileSave;
	private	JMenuItem	menuFileSaveAs;
	private	JMenuItem	menuFileExit;
	private	JMenuItem	menuEditCopy;
	private	JMenuItem	menuEditCut;
	private	JMenuItem	menuEditPaste;
        private ExitHandler windowHandler;
        private SaveAsHandler saveAs;
        private OpenHandler openHand;

	public MenuGUI()
	{
		//setTitle( "Complete Menu Application" );
		setSize( 310, 130 );

		topPanel = new JPanel();
		topPanel.setLayout( new BorderLayout() );
		//getContentPane().add( topPanel );

		// Create the menu bar
		menuBar = new JMenuBar();

		// Set this instance as the application's menu bar
		//setJMenuBar( menuBar );
                add(menuBar);
		
		// Build the property sub-menu
		menuProperty = new JMenu( "Properties" );
		menuProperty.setMnemonic( 'P' );

		// Create property items
		menuPropertySystem = CreateMenuItem( menuProperty, ITEM_PLAIN,
								"System...", null, 'S', null );
		menuPropertyEditor = CreateMenuItem( menuProperty, ITEM_PLAIN,
								"Editor...", null, 'E', null );
		menuPropertyDisplay = CreateMenuItem( menuProperty, ITEM_PLAIN,
								"Display...", null, 'D', null );
		
		// Create the file menu
		menuFile = new JMenu( "File" );
		menuFile.setMnemonic( 'F' );
		menuBar.add( menuFile );

		// Create the file menu
		// Build a file menu items
		menuFileNew = CreateMenuItem( menuFile, ITEM_PLAIN,
								"New", null, 'N', null );
		menuFileOpen = CreateMenuItem( menuFile, ITEM_PLAIN, "Open...",
								new ImageIcon( "open.gif" ), 'O',
								"Open a new file" );
                menuFile.addSeparator();
		menuFileSave = CreateMenuItem( menuFile, ITEM_PLAIN, "Save",
								new ImageIcon( "save.gif" ), 'S',
								" Save this file" );
		menuFileSaveAs = CreateMenuItem( menuFile, ITEM_PLAIN,
								"Save As...", null, 'A',
								"Save this data to a new file" );
		// Add the property menu
		menuFile.addSeparator();
		//menuFile.add( menuProperty );	
		//menuFile.addSeparator();
		menuFileExit = CreateMenuItem( menuFile, ITEM_PLAIN,
								"Exit", null, 'x',
								"Exit the program" );
                
                
		// Create the file menu
		menuEdit = new JMenu( "Edit" );
		menuEdit.setMnemonic( 'E' );
		menuBar.add( menuEdit );

		// Create edit menu options
		menuEditCut = CreateMenuItem( menuEdit, ITEM_PLAIN,
								"Cut", null, 't',
								"Cut data to the clipboard" );
		menuEditCopy = CreateMenuItem( menuEdit, ITEM_PLAIN,
								"Copy", null, 'C',
								"Copy data to the clipboard" );
		menuEditPaste = CreateMenuItem( menuEdit, ITEM_PLAIN,
								"Paste", null, 'P',
								"Paste data from the clipboard" );
                windowHandler=new ExitHandler();
                menuFileExit.addActionListener(windowHandler);
                
                saveAs=new SaveAsHandler();
                menuFileSave.addActionListener(saveAs);
                
                openHand=new OpenHandler();
                menuFileOpen.addActionListener(openHand);
                
                
                
                
	}

	public JMenuItem CreateMenuItem( JMenu menu, int iType, String sText,
								ImageIcon image, int acceleratorKey,
								String sToolTip )
	{
		// Create the item
		JMenuItem menuItem;

		switch( iType )
		{
			case ITEM_RADIO:
				menuItem = new JRadioButtonMenuItem();
				break;

			case ITEM_CHECK:
				menuItem = new JCheckBoxMenuItem();
				break;

			default:
				menuItem = new JMenuItem();
				break;
		}

		// Add the item test
		menuItem.setText( sText );

		// Add the optional icon
		if( image != null )
			menuItem.setIcon( image );

		// Add the accelerator key
		if( acceleratorKey > 0 )
			menuItem.setMnemonic( acceleratorKey );

		// Add the optional tool tip text
		if( sToolTip != null )
			menuItem.setToolTipText( sToolTip );

		// Add an action handler to this menu item
		

		menu.add( menuItem );

		return menuItem;
	}

	public void actionPerformed( ActionEvent event )
	{
		System.out.println( event );
	}

       
}