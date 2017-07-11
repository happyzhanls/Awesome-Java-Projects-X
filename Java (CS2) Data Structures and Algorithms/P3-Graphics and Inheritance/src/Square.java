
public class Square extends PolygonPrimitive {
	
	public Square(int x, int y, int size) {
		this.xPoints = new int[4];
		this.yPoints = new int[4];
		this.xPoints[0] = x;
		this.yPoints[0] = y;
		this.xPoints[1] = x;
		this.yPoints[1] = y + size;
		this.xPoints[2] = x + size;
		this.yPoints[2] = y + size;
		this.xPoints[3] = x + size;
		this.yPoints[3] = y;
	}
}
