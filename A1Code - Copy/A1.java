/*
 *  =============================================================
 *  A1.java : Extends JApplet and contains a panel where
 *  shapes move around on the screen. Also contains start and stop
 *  buttons that starts animation and stops animation respectively.
 *  ==============================================================
 */

 /* 
  * Name:Ian Wang
  * UPI: iwan013
  * ID : 1193035
  */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.event.*;
import java.util.Vector;

public class A1 extends JApplet {
  AnimationPanel panel;  // panel for bouncing area
  JButton startButton, stopButton, fillButton, borderButton, clearButton;  //buttons to start and stop the animation
  String textValue = ""+20, textValue2 = ""+20;
  private Color newFillColor;
  private Color newBorderColor;
  private Color defaultFillColor=Color.green;
  private Color defaultBorderColor=Color.black;  
  

  /** main method for A1
   */
  public static void main(String[] args) {
    A1 applet = new A1();
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().add(applet, BorderLayout.CENTER);
    frame.setTitle("Bouncing Application");
    applet.init();
    applet.start();
    frame.setSize(500, 500);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = frame.getSize();
    frame.setLocation((d.width - frameSize.width) / 2, (d.height - frameSize.height) / 2);
    frame.setVisible(true);
 
  }

  /** init method to initialise components
   */
  public void init() {
    panel = new AnimationPanel();
    add(panel, BorderLayout.CENTER);
    add(setUpToolsPanel(), BorderLayout.NORTH);
    add(setUpButtons(), BorderLayout.SOUTH);
 
    addComponentListener(
      new ComponentAdapter() { // resize the frame and reset all margins for all shapes
        public void componentResized(ComponentEvent componentEvent) {
          panel.resetMarginSize();
       }
     });
  }

  /** Set up the tools panel
  * @return toolsPanel  the Panel
   */
  public JPanel setUpToolsPanel() {
    //Set up the shape combo box
    ImageIcon circleButtonIcon = createImageIcon("circle.gif");
    ImageIcon rectangleButtonIcon = createImageIcon("rectangle.gif");
    ImageIcon pacManButtonIcon = createImageIcon("pacman.gif");
    ImageIcon bowTieButtonIcon = createImageIcon("bowtie.gif");
    
    JComboBox shapesComboBox = new JComboBox(new Object[] {circleButtonIcon, rectangleButtonIcon,pacManButtonIcon,bowTieButtonIcon} );
    shapesComboBox.setToolTipText("Set shape");
    shapesComboBox.addActionListener( new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        JComboBox cb = (JComboBox)e.getSource();
        //set the default shape type based on the selection: 0 for Circle, 1 for Rectangle
        panel.setDefaultShapeType(cb.getSelectedIndex());
      }
    });
    //Set up the path combo box
    ImageIcon fallingButtonIcon = createImageIcon("falling.gif");
    ImageIcon boundaryButtonIcon = createImageIcon("boundary.gif");

    JComboBox pathComboBox = new JComboBox(new Object[] {boundaryButtonIcon, fallingButtonIcon});
    pathComboBox.setToolTipText("Set Path");
    pathComboBox.addActionListener( new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        JComboBox cb = (JComboBox)e.getSource();
        //set the default path type based on the selection from combo box: 0 for Boundary Path, 1 for Falling Path
        panel.setDefaultPathType(cb.getSelectedIndex());
      }
    });
    //Set up the height TextField
    JTextField heightTxt = new JTextField("20");
    heightTxt.setToolTipText("Set Height");
    heightTxt.addActionListener( new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        JTextField tf = (JTextField)e.getSource();
  //complete this
        try {
         
            int newValue = Integer.parseInt(tf.getText());
            
            
            if (newValue > 0){
            textValue = newValue +"";
             panel.setDefaultHeight(newValue);
            }else{
             tf.setText(textValue);
            }
          } catch (Exception ex) {
            tf.setText(textValue);
          }

      }
    });
    //Set up the width TextField
    JTextField widthTxt = new JTextField("20");
    widthTxt.setToolTipText("Set Width");
    widthTxt.addActionListener( new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        JTextField tf = (JTextField)e.getSource();
        try {
          int newValue = Integer.parseInt(tf.getText());
          
          if (newValue > 0){
           textValue2 = newValue +"";
           panel.setDefaultWidth(newValue);
          }else{
           tf.setText(textValue2);
          }
        } catch (Exception ex) {
          tf.setText(textValue2);
        }
      }
    });
    /*clearButton = new JButton("clear");
    clearButton.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e) {
            panel.makePopupMenu();

           }
        });*/



    JPanel toolsPanel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.weightx=1;
    add(toolsPanel, new JLabel(" Shape: ", JLabel.RIGHT), gbc, 0, 0, 1, 1);
    add(toolsPanel, shapesComboBox, gbc, 1, 0, 1, 1);
    add(toolsPanel, new JLabel(" Path: ", JLabel.RIGHT), gbc, 2, 0, 1, 1);
    add(toolsPanel, pathComboBox, gbc, 3, 0, 1, 1);
    add(toolsPanel, new JLabel(" Height: ", JLabel.RIGHT), gbc, 4, 0, 1, 1);
    add(toolsPanel, heightTxt, gbc, 5, 0, 1, 1);
    add(toolsPanel, new JLabel(" Width: ", JLabel.RIGHT), gbc, 6, 0, 1, 1);
    add(toolsPanel, widthTxt, gbc, 7, 0, 1, 1);
    return toolsPanel;
  }

  /** Set up the buttons panel
     * @return buttonPanel  the Panel
   */
  public JPanel setUpButtons() {
    JPanel buttonPanel= new JPanel(new FlowLayout());
    //Set up the start button
    startButton = new JButton("Start");
    startButton.setToolTipText("Start Animation");
    
    startButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        startButton.setEnabled(false);
        stopButton.setEnabled(true);
        panel.start();  //start the animation
      }
    });
    //Set up the stop button
    stopButton = new JButton("Stop");
    startButton.setToolTipText("Stop Animation");
    stopButton.setEnabled(false);
    stopButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e) {
        stopButton.setEnabled(false);
        startButton.setEnabled(true); //stop the animation
        panel.stop();
       }
    });
    fillButton = new JButton("Fill");
    fillButton.setForeground(Color.green);
    fillButton.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e) {
         newFillColor = JColorChooser.showDialog(panel, "fill", Color.green);
         
   if(newFillColor == null){
    fillButton.setForeground(defaultFillColor);
          panel.setDefaultFillColor(defaultFillColor);
          panel.setSelectedFillColor(defaultFillColor);
   }else{
    defaultFillColor = newFillColor;
    fillButton.setForeground(newFillColor);
          panel.setDefaultFillColor(newFillColor);
          panel.setSelectedFillColor(newFillColor);
   }
           }
        });
    borderButton = new JButton("Border");
    borderButton.setForeground(Color.black);
    borderButton.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e) {
         newBorderColor = JColorChooser.showDialog(panel, "border", Color.black);
   if(newBorderColor == null){
    borderButton.setForeground(defaultBorderColor);
          panel.setDefaultBorderColor(defaultBorderColor);
          panel.setSelectedBorderColor(defaultBorderColor);

   }else{
    defaultBorderColor = newBorderColor;
    borderButton.setForeground(newBorderColor);
          panel.setDefaultBorderColor(newBorderColor);
          panel.setSelectedBorderColor(newBorderColor);
   }

           }
        });

    // Slider to adjust the speed of the animation
    JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 200, 30);
    slider.setToolTipText("Adjust Speed");
    slider.addChangeListener(new ChangeListener() {
     public void stateChanged(ChangeEvent e) {
       JSlider source = (JSlider)e.getSource();
       if (!source.getValueIsAdjusting()) {
         int value = (int) (source.getValue());  // get the value from slider
         TitledBorder tb = (TitledBorder) source.getBorder();
         tb.setTitle("Anim delay = " + String.valueOf(value) + " ms"); //adjust the tilted border to indicate the speed of the animation
         panel.adjustSpeed(value); //set the speed
         source.repaint();
       }
      }
    });
    TitledBorder title = BorderFactory.createTitledBorder("Anim delay = 30 ms");
    slider.setBorder(title);
    // Add buttons and slider control
    buttonPanel.add(startButton);
    buttonPanel.add(stopButton);
    buttonPanel.add(fillButton);
    buttonPanel.add(borderButton);
    
    buttonPanel.add(slider);
    return buttonPanel;
  }

  /** create the imageIcon
   * @param  filename  the filename of the image
   * @return ImageIcon  the imageIcon
   */
  protected static ImageIcon createImageIcon(String filename) {
    java.net.URL imgURL = A1.class.getResource(filename);
    return new ImageIcon(imgURL);
  }

  /** Adds a component to a Panel.
      @param p is the panel
      @param c is the component to add
      @param gbc the grid bag constraints
      @param x the grid bag column
      @param y the grid bag row
      @param w the number of grid bag columns spanned
      @param h the number of grid bag rows spanned
   */
   private void add(JPanel p, Component c, GridBagConstraints gbc, int x, int y, int w, int h) {
      gbc.gridx = x;
      gbc.gridy = y;
      gbc.gridwidth = w;
      gbc.gridheight = h;
      p.add(c, gbc);
   }
}

