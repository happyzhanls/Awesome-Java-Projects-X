
public class Triangle extends PolygonPrimitive {
	
	public Triangle(int x0, int y0, int x1, int y1, int x2, int y2) {
		this.xPoints = new int[3];
		this.yPoints = new int[3];
		this.xPoints[0] = x0;
		this.yPoints[0] = y0;
		this.xPoints[1] = x1;
		this.yPoints[1] = y1;
		this.xPoints[2] = x2;
		this.yPoints[2] = y2;
	}
}
