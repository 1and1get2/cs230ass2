/*
 *  ======================================================================
 *  AnimationPanel.java : Moves shapes around on the screen according to different paths.
 *  It is the main drawing area where shapes are added and manipulated.
 *  It also contains a popup menu to clear all shapes.
 *  ======================================================================
 */

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
/* 
 * Name:Ian Wang
 * UPI: iwan013
 *	ID : 1193035
 */
public class AnimationPanel extends JComponent implements Runnable {
  private Thread animationThread = null;  // the thread for animation
  private Vector<MovingShape> shapes;		// the vector to store all shapes
  private int defaultShapeType, // the default shape type
    defaultPath, 				// the default path type
    defaultWidth = 20,
  	defaultHeight = 20;
  // the default width of a shape
  private int delay = 30; 		// the default animation speed
  JPopupMenu popup;				// popup menu
  private Color defaultFillColor = Color.green;
  private Color defaultBorderColor = Color.black;
   /** Constructor of the AnimationPanel
    */
   public AnimationPanel() {
    shapes = new Vector<MovingShape>(); //create the vector to store shapes
    popup = new JPopupMenu(); //create the popup menu
    makePopupMenu();

    // add the mouse event to handle popup menu and create new shape
    addMouseListener( new MouseAdapter() {
      public void mousePressed(MouseEvent e) {
        maybeShowPopup(e);
      }

      public void mouseReleased(MouseEvent e) {
        maybeShowPopup(e);
      }

      private void maybeShowPopup(MouseEvent e) {
        if (e.isPopupTrigger()) {
          popup.show(e.getComponent(), e.getX(), e.getY());
        }
      }
      public void mouseClicked( MouseEvent e ) {
        if (animationThread != null) {  // if the animation has started, then
          boolean found = false;
          MovingShape currentShape = null;
          for (int i = 0; i < shapes.size(); i++) {
            currentShape = (MovingShape) shapes.elementAt(i);
            if ( currentShape.contains( e.getPoint()) ) { // if the mousepoint is within a shape, then set the shape to be selected/deselected
              found = true;
              currentShape.setSelected( ! currentShape.isSelected() );
              System.out.println(currentShape);
            }
          }
          if (! found) createNewShape(e.getX(), e.getY()); // if the mousepoint is not within a shape, then create a new one according to the mouse position
        }
      }
    });
  }

  /** create a new shape
   * @param x 	the x-coordinate of the mouse position
   * @param y	the y-coordinate of the mouse position
   */
  protected void createNewShape(int x, int y) {
    // get the margin of the frame
    Insets insets = getInsets();
    int marginWidth = getWidth() - insets.left - insets.right;
    int marginHeight = getHeight() - insets.top - insets.bottom;
    // create a new shape dependent on all current properties and the mouse position
    switch (defaultShapeType) {
      case 0: { //cirle
        shapes.add( new MovingCircle(x, y, defaultWidth,defaultHeight, marginWidth, marginHeight, defaultPath,defaultFillColor,defaultBorderColor));
        break;
      }
      case 1: { //rectnage
        shapes.add( new MovingRectangle(x, y, defaultWidth, defaultHeight, marginWidth, marginHeight, defaultPath,defaultFillColor,defaultBorderColor));
        break;
      }
      case 2:{ 
    	  shapes.add( new MovingPacMan(x, y, defaultWidth, defaultHeight, marginWidth, marginHeight, defaultPath,defaultFillColor,defaultBorderColor));
          break;
      }
      case 3:{ 
    	  shapes.add( new MovingBowTie(x, y, defaultWidth, defaultHeight, marginWidth, marginHeight, defaultPath,defaultFillColor,defaultBorderColor));
          break;
      }

    }
        
  }
  
  public void setSelectedFillColor(Color color){
	  MovingShape currentShape = null;
	    for (int i = 0; i < shapes.size(); i++) {
	    	currentShape = (MovingShape) shapes.get(i);
	    	if(currentShape.isSelected()){
	    		currentShape.setFillColor(color);
		    }
	    }

	    	
  }
  
  public void setSelectedBorderColor(Color color){
	  MovingShape currentShape = null;
	    for (int i = 0; i < shapes.size(); i++) {
	    	currentShape = (MovingShape) shapes.get(i);
	    	if(currentShape.isSelected()){
	    		currentShape.setBorderColor(color);
		    }
	    }

	    	
  }

  /** set the default shape type
   * @param s	the new shape type
   */
  public void setDefaultShapeType(int s) {
    defaultShapeType = s;
  }

  /** set the default path type and the path type for all currently selected shapes
   * @param t	the new path type
   */
  public void setDefaultPathType(int t) {
    defaultPath = t;
    MovingShape currentShape = null;
    for (int i = 0; i < shapes.size(); i++) {
      currentShape = (MovingShape) shapes.get(i);
      if ( currentShape.isSelected()) {
        currentShape.setPath(defaultPath);
      }
    }
  }

  /** set the default width and the width for all currently selected shapes
   * @param w	the new width value
   */
  public void setDefaultWidth(int w) {
    MovingShape currentShape = null;
    defaultWidth = w;
    for (int i = 0; i < shapes.size(); i++) {
      currentShape = (MovingShape) shapes.get(i);
      if ( currentShape.isSelected()) {
        currentShape.setWidth(defaultWidth);
      }
    }
  }
  
  public void setDefaultHeight(int h) {
	  MovingShape currentShape = null;
	  defaultHeight = h;
	  for (int i = 0; i < shapes.size(); i++) {
		  currentShape = (MovingShape) shapes.get(i);
		  if ( currentShape.isSelected()) {
		  	currentShape.setHeight(defaultHeight);
		  }
	  }
  }
  public void setDefaultFillColor(Color color){
	defaultFillColor = color;
  }
  public void setDefaultBorderColor(Color color){
	defaultBorderColor = color;
  }

	

 /** remove all shapes from our vector
   */
  public void clearAllShapes() {
    shapes.clear();
  }

  // you don't need to make any changes after this line ______________

  /** create the popup menu for our animation program
   */
  protected void makePopupMenu() {
    JMenuItem menuItem;
   // clear all
    menuItem = new JMenuItem("Clear All");
    menuItem.addActionListener( new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        clearAllShapes();
      }
    });
    popup.add(menuItem);
   }

  /** reset the margin size of all shapes from our vector
   */
  public void resetMarginSize() {
    Insets insets = getInsets();
    int marginWidth = getWidth() - insets.left - insets.right;
    int marginHeight = getHeight() - insets.top - insets.bottom ;
     for (int i = 0; i < shapes.size(); i++)
      ((MovingShape) shapes.elementAt(i)).setMarginSize(marginWidth, marginHeight);
  }

  /**  update the painting area
   * @param g	the graphics control
   */
  public void update(Graphics g){
    paint(g);
  }

  /**  move and paint all shapes within the animation area
   * @param g	the Graphics control
   */
  public void paintComponent(Graphics g) {
    MovingShape currentShape;
    for (int i = 0; i < shapes.size(); i++) {
      currentShape = (MovingShape) shapes.elementAt(i);
      currentShape.move();
      currentShape.draw(g);
    }
  }

  /** change the speed of the animation
   * @param newValue 	the speed of the animation in ms
   */
  public void adjustSpeed(int newValue) {
    if (animationThread != null) {
      stop();
      delay = newValue;
      start();
    }
  }

  /**  When the "start" button is pressed, start the thread
   */
  public void start() {
    animationThread = new Thread(this);
    animationThread.start();
  }

  /**  When the "stop" button is pressed, stop the thread
   */
  public void stop() {
    if (animationThread != null) {
      animationThread = null;
    }
  }

  /** run the animation
   */
  public void run() {
    Thread myThread = Thread.currentThread();
    while(animationThread==myThread) {
      repaint();
      pause(delay);
    }
  }

  /** Sleep for the specified amount of time
   */
  private void pause(int milliseconds) {
    try {
      Thread.sleep((long)milliseconds);
    } catch(InterruptedException ie) {}
  }
}
