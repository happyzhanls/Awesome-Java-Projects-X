
public class RoundPrimitive extends Primitive {
	
	public int x, y, width, height;
	
	public void draw(UserInterface ui) {
		ui.drawOval(x, y, width, height, isFilled);
		if (isFilled) {
			ui.fillColor(color);
		} else {
			ui.lineColor(color);
		}
	};
}
