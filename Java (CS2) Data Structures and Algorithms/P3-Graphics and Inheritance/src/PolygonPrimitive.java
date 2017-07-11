
public class PolygonPrimitive extends Primitive {
	
	public int[] xPoints;
	public int[] yPoints;
	
	public void draw(UserInterface ui) {
		ui.drawPolygon(xPoints, yPoints, isFilled);
		if (isFilled) {
			ui.fillColor(color);
		} else {
			ui.lineColor(color);
		}
	}
}
