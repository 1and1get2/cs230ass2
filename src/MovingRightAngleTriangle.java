/*
 *	===============================================================================
 *	MovingRightAngleTriangle.java : A shape that is a right-angle triangle.
 *	A right-angle triangle has 4 handles shown when it is selected (by clicking on it).
 *	===============================================================================
 */
import java.awt.*;
public class MovingRightAngleTriangle extends MovingShape {
	private int npoints = 3;
	protected double[] xRatio;
  	protected double[] yRatio;

	/** constuctor to create a right-angle Triangle with default values
	 */
	public MovingRightAngleTriangle() {
		super();
	}

	/** constuctor to create a right-angle Triangle shape
	 */
	public MovingRightAngleTriangle(int x, int y, int w, int h,  int mw, int mh, int pathType,  Color fillColor, Color borderColor) {
		super(x ,y ,w, h ,mw ,mh ,pathType, fillColor, borderColor);
		xRatio = new double[] {0, 1, 0};
		yRatio = new double[] {0, 1, 1};
	}

	/** Returns whether the point is in the right-angle triangle or not
	 * @return true if and only if the point is in the right-angle triangle, false otherwise.
	 */
	public boolean contains(Point mousePt) {
		return polyShape().contains(mousePt);
	}

	/** Returns the Polygon object
	   * @return poly
	*/
	private Polygon polyShape() {
		int x, y;
		Polygon poly = new Polygon();
		for (int i = 0; i< npoints; i++) {
			x = p.x + (int) (xRatio[i] * width);
			y = p.y + (int) (yRatio[i] * height);
			poly.addPoint(x, y);
		}
		return poly;
	}

	/** draw the right-angle Triangle with the fill colour
	 *  If it is selected, draw the handles
	 *  @param g	the Graphics control
	 */
	public void draw(Graphics g) {
		Polygon polygon = polyShape();
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(currentFillColor);
		g2d.fillPolygon(polygon); //draw the outer triangles
		g2d.setColor(currentBorderColor);
		g2d.drawPolygon(polygon);  //draw the inner diamond
		drawHandles(g2d);
	}
}