 /* 
  * Name:Ian Wang
  * UPI: iwan013
  *	ID : 1193035
  */

import java.awt.*;

public class MovingBowTie extends MovingShape {



	public MovingBowTie() {
		   super();
		  }

		  /** constuctor to create an oval shape
		   */
		  public MovingBowTie(int x, int y, int w, int h, int mw, int mh, int pathType,Color fillColor, Color borderColor) {
			    super(x ,y ,w ,h ,mw ,mh ,pathType, fillColor,  borderColor);
		  }

		  /** draw the oval with the fill colour
		   *  If it is selected, draw the handles
		   *  @param g	the Graphics control
		   */
		  
		  public Polygon PolyShape(){
			  int [] x = {p.x,p.x+width/2,p.x+width,p.x+width,p.x+width/2,p.x};
			  int [] y = {p.y,p.y+HEIGHT/3,p.y,p.y+HEIGHT,p.y+HEIGHT*2/3,p.y+HEIGHT};
			  Polygon poly = new Polygon(x,y,6);
			  return poly;
		  }
		  public Polygon PolyShape2(){
			  int [] x = {p.x+width/4,p.x+width/2,p.x+width*3/4,p.x+width/2};
			  int [] y = {p.y+HEIGHT/2,p.y+HEIGHT/3,p.y+HEIGHT/2,p.y+HEIGHT*2/3};
			  Polygon poly = new Polygon(x,y,4);
			  return poly;
		  }
		  
		  
		  public void draw(Graphics g) {
		  
			g.setColor(defaultFillColor);
			g.fillPolygon(PolyShape());
			g.setColor(defaultBorderColor);
			g.drawPolygon(PolyShape());
			g.setColor(defaultBorderColor);
			g.fillPolygon(PolyShape2());
			g.setColor(defaultBorderColor);
			g.drawPolygon(PolyShape2());	
		    drawHandles(g);
			
		  }

		  /** Returns whether the point is in the oval or not
		   * @return true if and only if the point is in the oval, false otherwise.
		   */
		  public boolean contains(Point mousePt) {
			    return (p.x <= mousePt.x && mousePt.x <= (p.x + width + 1) && 
			    		//mousePt.y >=(p.y+HEIGHT/3+1)  && mousePt.y <=(p.y+HEIGHT*2/3+1)
			    		   p.y <= mousePt.y && mousePt.y <= (p.y + HEIGHT + 1));
						   //&& mousePt.x >=(p.x+width/4+1) &&  mousePt.x <=(p.x+width*3/4+1)
		  }
		
	
}