/*
 *  ===============================================================================
 *  MovingRectangle.java : A shape that is a rectangle.
 *  A rectangle has 4 handles shown when it is selected (by clicking on it).
 *  ===============================================================================
 */

 /* 
  * Name:Ian Wang
  * UPI: iwan013
  *	ID : 1193035
  */
import java.awt.*;
public class MovingRectangle extends MovingShape {

  /** constuctor to create a rectangle with default values
   */
  public MovingRectangle() {
    super();
  }
  

  /** constuctor to create a rectangle shape
   */
  public MovingRectangle(int x, int y, int w, int h, int mw, int mh, int pathType,Color fillColor, Color borderColor) {
    super(x ,y ,w ,h ,mw ,mh ,pathType, fillColor,  borderColor);
  }

  /** draw the rectangle with the fill colour
   *  If it is selected, draw the handles
   *  @param g	the Graphics control
   */
  public void draw(Graphics g) {
    g.setColor(defaultFillColor);
    g.fillRect(p.x, p.y, width, HEIGHT);
    g.setColor(defaultBorderColor);
    g.drawRect(p.x, p.y, width, HEIGHT);
    drawHandles(g);
  }

  /** Returns whether the point is in the rectangle or not
   * @return true if and only if the point is in the rectangle, false otherwise.
   */
  public boolean contains(Point mousePt) {
    return (p.x <= mousePt.x && mousePt.x <= (p.x + width + 1)  &&  p.y <= mousePt.y && mousePt.y <= (p.y + HEIGHT + 1));
  }
}