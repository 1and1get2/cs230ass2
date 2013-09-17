/*
 *	===============================================================================
 *	MovingSquare.java : A shape that is a square.
 *	A square has 4 handles shown when it is selected (by clicking on it).
 *	===============================================================================
 */
import java.awt.*;
public class MovingSquare extends MovingRectangle {

	/** constuctor to create a square with default values
	 */
	public MovingSquare() {
		super();
	}

	/** constuctor to create a square shape
	 */
	public MovingSquare(int x, int y, int s, int mw, int mh, int pathType, Color fillColor, Color borderColor) {
		super(x ,y ,s, s ,mw ,mh ,pathType, fillColor, borderColor);
	}

	/** draw the square with the fill colour
	 *	If it is selected, draw the handles
	 *	@param g	the Graphics control
	 */
/*	public void draw(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(p.x, p.y, width, height);
		g.setColor(Color.black);
		g.drawRect(p.x, p.y, width, height);
		drawHandles(g);
	}*/

	/** Returns whether the point is in the rectangle or not
	 * @return true if and only if the point is in the rectangle, false otherwise.
	 */
	public boolean contains(Point mousePt) {
		return (p.x <= mousePt.x && mousePt.x <= (p.x + width + 1)	&&	p.y <= mousePt.y && mousePt.y <= (p.y + height + 1));
	}
}