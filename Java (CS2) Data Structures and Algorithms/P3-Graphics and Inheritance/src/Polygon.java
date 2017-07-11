
public class Polygon extends PolygonPrimitive{
	
	public Polygon(int[] xPoints, int[] yPoints) {
		int size = xPoints.length;
		this.xPoints = new int[size];
		this.yPoints = new int[size];
		for (int i = 0; i < size; i++) {
			this.xPoints[i] = xPoints[i];
			this.yPoints[i] = yPoints[i];
		}
	}
}
