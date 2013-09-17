 /* 
  * Name:Ian Wang
  * UPI: iwan013
  *	ID : 1193035
  */

import java.awt.*;
import java.awt.geom.Arc2D;
public class MovingPacMan extends MovingCircle {
	private int angleStart = 45;
	private int angleExtent = 270;

	private boolean open = false;
	public MovingPacMan() {
		   super();
		  }

		  /** constuctor to create an oval shape
		   */
		  public MovingPacMan(int x, int y, int w, int h, int mw, int mh, int pathType,Color fillColor, Color borderColor) {
			    super(x ,y ,w ,h ,mw ,mh ,pathType, fillColor,  borderColor);
		  }

		  /** draw the oval with the fill colour
		   *  If it is selected, draw the handles
		   *  @param g	the Graphics control
		   */
		  public void draw(Graphics g) {
			Graphics2D g2d = (Graphics2D) g;

			if(!open){
				g.setColor(defaultFillColor);
			    g2d.fill(new Arc2D.Float(p.x, p.y, width, HEIGHT,angleStart, angleExtent, Arc2D.PIE));
			    g.setColor(defaultBorderColor);
			    g2d.draw(new Arc2D.Float(p.x, p.y, width, HEIGHT,angleStart, angleExtent, Arc2D.PIE));
			    angleStart-= 5;
			    angleExtent+=10;
				if(angleStart <= 0 && angleExtent >= 360){
					open = true;
				    
				}
		    }else{
		    	g.setColor(defaultFillColor);
			    g2d.fill(new Arc2D.Float(p.x, p.y, width, HEIGHT,angleStart, angleExtent, Arc2D.PIE));
			    g.setColor(defaultBorderColor);
			    g2d.draw(new Arc2D.Float(p.x, p.y, width, HEIGHT,angleStart, angleExtent, Arc2D.PIE));
			    angleStart+= 5;
			    angleExtent-=10;
			    
				if (angleStart >= 45 && angleExtent <= 270){
				
				    open = false;
				}
		    }
		    drawHandles(g);
			
		  }

		  /** Returns whether the point is in the oval or not
		   * @return true if and only if the point is in the oval, false otherwise.
		   */
		
	
}