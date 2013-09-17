/*
 *  ===============================================================================
 *  MovingCircle.java : A shape that is an oval.
 *  An oval/circle has 4 handles shown when it is selected (by clicking on it).
 *  ===============================================================================
 */

 /* 
  * Name:Ian Wang
  * UPI: iwan013
  *	ID : 1193035
  */
import java.awt.*;
public class MovingCircle extends MovingShape {

  /** constuctor to create an oval with default values
   */
  public MovingCircle() {
   super();
  }

  /** constuctor to create an oval shape
   */
  public MovingCircle(int x, int y, int w, int h, int mw, int mh, int pathType,Color fillColor, Color borderColor) {
	    super(x ,y ,w ,h ,mw ,mh ,pathType, fillColor,  borderColor);
  }

  /** draw the oval with the fill colour
   *  If it is selected, draw the handles
   *  @param g	the Graphics control
   */
  public void draw(Graphics g) {
    g.setColor(defaultFillColor);
    g.fillOval(p.x, p.y, width, HEIGHT);
    g.setColor(defaultBorderColor);
    g.drawOval(p.x, p.y, width, HEIGHT);
    drawHandles(g);
  }

  /** Returns whether the point is in the oval or not
   * @return true if and only if the point is in the oval, false otherwise.
   */
  public boolean contains(Point mousePt) {
    double dx, dy;
    Point EndPt = new Point(p.x + width, p.y + HEIGHT);
    dx = (2 * mousePt.x - p.x - EndPt.x) / (double) width;
    dy = (2 * mousePt.y - p.y - EndPt.y) / (double) HEIGHT;
    return dx * dx + dy * dy < 1.0;
  }
}