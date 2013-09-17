/*
 *  =============================================================
 *  A2.java : Extends JApplet and contains a panel where
 *  shapes move around on the screen. Also contains start and stop
 *  buttons that starts animation and stops animation respectively.
 *  ==============================================================
 */

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.border.*;
import javax.swing.event.*;

import java.util.Vector;

public class A2 extends JApplet {
	AnimationPanel panel; // panel for bouncing area
	// buttons to start and stop the animation
	JButton startButton, stopButton, fillButton, borderButton;
	private Color currentFillColor = Color.green,
			currentBorderColor = Color.black;
	private int currentShapeType = 0; // cuttentPathType = 0,
	private JTextField messageStr, outLineWith;

	/**
	 * main method for A1
	 */
	public static void main(String[] args) {
		A2 applet = new A2();
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(applet, BorderLayout.CENTER);
		frame.setTitle("Bouncing Application , glia769");
		applet.init();
		applet.start();
		frame.setSize(800, 500);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = frame.getSize();
		frame.setLocation((d.width - frameSize.width) / 2,
				(d.height - frameSize.height) / 2);
		frame.setVisible(true);
	}

	/**
	 * init method to initialise components
	 */
	public void init() {
		panel = new AnimationPanel();
		add(panel, BorderLayout.CENTER);
		add(setUpToolsPanel(), BorderLayout.NORTH);
		add(setUpButtons(), BorderLayout.SOUTH);
		addComponentListener(new ComponentAdapter() { // resize the frame and
														// reset all margins for
														// all shapes
			public void componentResized(ComponentEvent componentEvent) {
				panel.resetMarginSize();
			}
		});
	}

	/**
	 * Set up the tools panel
	 * 
	 * @return toolsPanel the Panel
	 */
	public JPanel setUpToolsPanel() {
		// Set up the shape combo box
		ImageIcon squareButtonIcon = createImageIcon("square.gif");
		ImageIcon rectangleButtonIcon = createImageIcon("rectangle.gif");
		ImageIcon rightAngleButtonIcon = createImageIcon("rightAngle.gif");
		ImageIcon massageButtonIcon = createImageIcon("message.gif");
		ImageIcon outlineButtonIcon = createImageIcon("outline.gif");

		JComboBox<ImageIcon> shapesComboBox = new JComboBox<ImageIcon>(
				new ImageIcon[] { rectangleButtonIcon, squareButtonIcon,
						rightAngleButtonIcon, massageButtonIcon,
						outlineButtonIcon, });
		shapesComboBox.setToolTipText("Set shape");
		shapesComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox) e.getSource();
				// set the Current shape type based on the selection: 0 for
				// Circle, 1 for Rectangle etc
				panel.setCurrentShapeType(currentShapeType = cb
						.getSelectedIndex());
				// set enablity
				messageStr.setEnabled(currentShapeType == 3
						|| currentShapeType == 4);
				outLineWith.setEnabled(currentShapeType == 4);
			}
		});
		// Set up the path combo box
		ImageIcon boundaryButtonIcon = createImageIcon("boundary.gif");
		ImageIcon fallingButtonIcon = createImageIcon("falling.gif");
		ImageIcon myButtonIcon = createImageIcon("copycat.gif");
		JComboBox<ImageIcon> pathComboBox = new JComboBox<ImageIcon>(
				new ImageIcon[] { boundaryButtonIcon, fallingButtonIcon,
						myButtonIcon });
		pathComboBox.setToolTipText("Set Path");
		pathComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox) e.getSource();
				// set the Current path type based on the selection from combo
				// box: 0 for Boundary Path, 1 for bouncing Path
				panel.setCurrentPathType(cb.getSelectedIndex());
			}
		});
		// Set up the height TextField
		JTextField heightTxt = new JTextField("20");
		heightTxt.setToolTipText("Set Height");
		heightTxt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextField tf = (JTextField) e.getSource();
				try {
					int newValue = Integer.parseInt(tf.getText());
					if (newValue > 0) // if the value is valid, then change the
										// current height
						panel.setCurrentHeight(newValue);
					else
						tf.setText(panel.getCurrentHeight() + "");
				} catch (Exception ex) {
					tf.setText(panel.getCurrentHeight() + "");
					// if the number entered is invalid, reset it
				}
			}
		});
		// Set up the width TextField
		JTextField widthTxt = new JTextField("50");
		widthTxt.setToolTipText("Set Width");
		widthTxt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextField tf = (JTextField) e.getSource();
				try {
					int newValue = Integer.parseInt(tf.getText());
					if (newValue > 0)
						panel.setCurrentWidth(newValue);
					else
						tf.setText(panel.getCurrentWidth() + "");
				} catch (Exception ex) {
					tf.setText(panel.getCurrentWidth() + "");
				}
			}
		});
		messageStr = new JTextField("Hello");
		messageStr.setToolTipText("Set Message");
		// messageStr.setEditable(false);
		messageStr.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JTextField tf = (JTextField) e.getSource();
				try {
					panel.setMessage(tf.getText());
				} catch (Exception ex) {
					panel.setMessage("Error");
				}
			}
		});
		outLineWith = new JTextField("1.0");
		widthTxt.setToolTipText("outline width");
		widthTxt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextField tf = (JTextField) e.getSource();
				try {
					float f = Float.parseFloat(tf.getText()) - 0.001f;
					if (f > 0)
						panel.setOutlineWidth(f);
					else
						tf.setText(panel.getOutlineWidth() + "");
				} catch (Exception ex) {
					tf.setText(panel.getOutlineWidth() + "");
				}
			}
		});
		messageStr.setEnabled(false);
		outLineWith.setEnabled(false);
		JPanel toolsPanel = new JPanel();
		toolsPanel.setLayout(new BoxLayout(toolsPanel, BoxLayout.X_AXIS));
		toolsPanel.add(new JLabel(" Shape: ", JLabel.RIGHT));
		toolsPanel.add(shapesComboBox);
		toolsPanel.add(new JLabel(" Path: ", JLabel.RIGHT));
		toolsPanel.add(pathComboBox);
		toolsPanel.add(new JLabel(" Height: ", JLabel.RIGHT));
		toolsPanel.add(heightTxt);
		toolsPanel.add(new JLabel(" Width: ", JLabel.RIGHT));
		toolsPanel.add(widthTxt);
		toolsPanel.add(new JLabel(" Message: ", JLabel.RIGHT));
		toolsPanel.add(messageStr);
		toolsPanel.add(new JLabel(" Outline Width: ", JLabel.RIGHT));
		toolsPanel.add(outLineWith);
		return toolsPanel;
	}

	/**
	 * Set up the buttons panel
	 * 
	 * @return buttonPanel the Panel
	 */
	public JPanel setUpButtons() {
		JPanel buttonPanel = new JPanel(new FlowLayout());
		// Set up the start button
		startButton = new JButton("Start");
		startButton.setToolTipText("Start Animation");
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startButton.setEnabled(false);
				stopButton.setEnabled(true);
				panel.start(); // start the animation
			}
		});
		// Set up the stop button
		stopButton = new JButton("Stop");
		stopButton.setToolTipText("Stop Animation");
		stopButton.setEnabled(false);
		stopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stopButton.setEnabled(false);
				startButton.setEnabled(true); // stop the animation
				panel.stop();
			}
		});
		// Set up fillButton & borderButton
		fillButton = new JButton("Fill");
		fillButton
				.setToolTipText("The foreground colour of the Fill button indicates the current fill colour.");
		fillButton.setEnabled(true);
		fillButton.setForeground(currentFillColor);
		fillButton.addActionListener(new ActionListener() {
			// TODO: if
			public void actionPerformed(ActionEvent e) {
				Color newFillColor = JColorChooser.showDialog(panel, "fill",
						currentFillColor);
				fillButton.setForeground(newFillColor);
				panel.setCurrentFillColor(newFillColor);

			}
		});
		// fillButton
		borderButton = new JButton("Border");
		borderButton
				.setToolTipText("The foreground colour of the Border button indicates the current border colour.");
		borderButton.setEnabled(true);
		borderButton.setForeground(currentBorderColor);
		borderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color newBorderColor = JColorChooser.showDialog(panel,
						"border", currentFillColor);
				borderButton.setForeground(newBorderColor);
				panel.setCurrentBorderColor(newBorderColor);
				// currentBorderColor = newBorderColor;
			}
		});

		// Slider to adjust the speed of the animation
		JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 200, 30);
		slider.setToolTipText("Adjust Speed");
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider) e.getSource();
				if (!source.getValueIsAdjusting()) {
					int value = (int) (source.getValue()); // get the value from
															// slider
					TitledBorder tb = (TitledBorder) source.getBorder();
					tb.setTitle("Anim delay = " + String.valueOf(value) + " ms");
					// adjust the tilted border to indicate the speed of the
					// animation
					panel.adjustSpeed(value); // set the speed
					source.repaint();
				}
			}
		});
		TitledBorder title = BorderFactory
				.createTitledBorder("Anim delay = 30 ms");
		slider.setBorder(title);
		// Add buttons and slider control
		buttonPanel.add(startButton);
		buttonPanel.add(stopButton);
		buttonPanel.add(slider);
		buttonPanel.add(fillButton);
		buttonPanel.add(borderButton);
		return buttonPanel;
	}

	/**
	 * create the imageIcon
	 * 
	 * @param filename
	 *            the filename of the image
	 * @return ImageIcon the imageIcon
	 */
	protected static ImageIcon createImageIcon(String filename) {
		java.net.URL imgURL = A2.class.getResource(filename);
		return new ImageIcon(imgURL);
	}
}
