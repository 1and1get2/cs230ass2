/*
 *	===============================================================================
 *	MovingRectangle.java : A shape that is a rectangle.
 *	A rectangle has 4 handles shown when it is selected (by clicking on it).
 *	===============================================================================
 */
import java.awt.*;
import java.awt.font.TextLayout;

public class MovingOutline extends MovingShape {

	protected String text = "Hello";
	private float outlineWidth;
	private Stroke stroke;
	private TextLayout testLayout;
	private Shape shape;

	/**
	 * constuctor to create a rectangle with default values
	 */
	public MovingOutline() {
		super();
	}

	/**
	 * constuctor to create a rectangle shape
	 */
	public MovingOutline(int x, int y, int w, int h, int mw, int mh,
			int pathType, Color fillColor, Color borderColor, String t, float outlineWidth ) {
		super(x, y, w, h, mw, mh, pathType, fillColor, borderColor);
		//
		this.text = t;
		this.outlineWidth = outlineWidth;
	}

	/**
	 * draw the rectangle with the fill colour If it is selected, draw the
	 * handles
	 * 
	 * @param g
	 *            the Graphics control
	 */
	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(p.x, p.y, width, height);
		g.setColor(currentBorderColor);
		g.drawRect(p.x, p.y, width, height);
		drawHandles(g);
		g.setColor(currentFillColor);
		drawCenteredString(text, p, width, height, g);
		Graphics2D g2d = (Graphics2D) g;
		
//		if (false)
//		{
//			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//			g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
//			g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
//		}
		Font f = new Font("Helvetica", Font.PLAIN, 22);
		testLayout = new TextLayout(this.text, f, g2d.getFontRenderContext());
		g2d.translate(p.x, p.y);
		shape = testLayout.getOutline(null);
		g2d.setColor(new Color(255, 0, 0, 255));
		g2d.setColor(new Color(255, 0, 0, 255));
		g2d.draw(shape);
//		g2d.translate(-200, -100);
	}

	public void drawCenteredString(String s,/*int x, int y,*/ Point p,  int w, int h, Graphics g) {
		FontMetrics fm = g.getFontMetrics();
		int x = (w - fm.stringWidth(s)) / 2;
		int y = (fm.getAscent() + (h - (fm.getAscent() + fm.getDescent())) / 2);
		g.drawString(s, x + p.x, y + p.y);
	}

	/**
	 * Returns whether the point is in the rectangle or not
	 * 
	 * @return true if and only if the point is in the rectangle, false
	 *         otherwise.
	 */
	public boolean contains(Point mousePt) {
		return (p.x <= mousePt.x && mousePt.x <= (p.x + width + 1)
				&& p.y <= mousePt.y && mousePt.y <= (p.y + height + 1));
	}
}