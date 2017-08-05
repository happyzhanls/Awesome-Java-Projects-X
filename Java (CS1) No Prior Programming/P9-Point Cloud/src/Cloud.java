import java.util.ArrayList;
import java.util.function.Predicate;

/* Insert your name here */

public class Cloud {
	private ArrayList<Point> points;	
    private boolean debug = false;

	/**
	 * Given Constructor
	 */
	public Cloud(){
		points = new ArrayList<Point>();
	}

	public void setDebug(Boolean debug){
		this.debug = debug;	
	}
	

	//TODO Implement Cloud.isEmpty
	/**
	 * @return whether cloud is empty or not
	 */
	public boolean isEmpty(){
		return points.isEmpty();
	}


	//TODO Implement Cloud.size
	/**
	 * @return number of points in the cloud
	 */
	public int size(){
		return points.size();
	}

	
	//TODO Implement Cloud.hasPoint
	/**
	 * 
	 * @param p a Point
	 * @return whether p in the cloud
	 */
	public boolean hasPoint(Point p){
		for (Point pe : points)
			if (pe.equals(p))
				return true;
		return false;
	}

	//TODO Implement Cloud.addPoint
	/**
	 * 
	 * @param p
	 * if p not in points, add p ***at the end*** of points (to keep same order)

	 */
	public void addPoint(Point p){
		if (!this.hasPoint(p))
			points.add(p);
		else
			return;
	}

	//Given Cloud.toString
	public String toString(){
		return points.toString();
	}

	//TODO Implement Cloud.extremes
	/**
	 * 
	 * @return an array of doubles: left, right, top, and bottom of points, 
	 *         null for empty cloud
	 */
	public double[] extremes(){
		if (points.isEmpty() || points.size() < 4)
			return null;
		else {
			double left = points.get(0).getX();
			double right = points.get(0).getX();
			double top = points.get(0).getY();
			double bot = points.get(0).getY();
			
			for (int i = 1; i < points.size(); i++) {
				if (points.get(i).getX() < left) 
					left = points.get(i).getX();
				if (points.get(i).getX() > right)
					right = points.get(i).getX();
				if (points.get(i).getY() > top)
					top = points.get(i).getY();
				if (points.get(i).getY() < bot)
					bot = points.get(i).getY();
			}
				
			double[] result = {left, right, top, bot};
			
			return result;
		}
	}
	
	//TODO Implement Cloud.centerP
	/**
	 * 
	 * @return: if (cloud not empty) create and return the center point
	 *          else null;
	 */
	public Point centerP(){
		double averageX = 0;
		double averageY = 0;
		for (Point p : points) {
			averageX += p.getX();
			averageY += p.getY();
		}
		averageX /= points.size();
		averageY /= points.size();
		return new Point(averageX, averageY);
	}

	//TODO Implement Cloud.minDist
	/**
	 * 
	 * @return minimal distance between 2 points in the cloud
	 *         0.0 for a cloud with less than 2 points
	 */
	public double minDist(){
		if (points.isEmpty() || points.size() == 1)
			return 0.0;
		else if (points.size() == 2) {
			return points.get(0).euclidDist(points.get(1));
		} else {
			double minDist = points.get(0).euclidDist(points.get(1));
			for (int i = 0; i < points.size() - 1; i++) {
				for (int j = 1; i + j < points.size(); j++) {
					if (points.get(i).euclidDist(points.get(i+j)) < minDist)
						minDist = points.get(i).euclidDist(points.get(i+j));
				}
			}
			return minDist;
		}
	}

	//TODO Implement Cloud.crop
	/**
	 * 
	 * @param p1 
	 * @param p2
	 * 
	 * all Points outside the rectangle, line or point spanned
	 * by p1 and p2 are removed
	 *  
	 */
	public void crop(Point p1, Point p2){
		
		if (p1 == null || p2 == null) {
			System.out.println("ERROR: Either p1 or p2 is null!");
			return;
		}
		
		if (p1.getX() == p2.getX() && p1.getY() == p2.getY()) {
			// deal with two equal input points.
			Predicate<Point> notEqual = (p) -> (p.getX() != p1.getX() || 
					p.getY() != p1.getY());
			points.removeIf(notEqual);
		} else if (p1.getX() == p2.getX()) {
			// deal with two input points on a vertical line segment.
			double minY = Math.min(p1.getY(), p2.getY());
			double maxY = Math.max(p1.getY(), p2.getY());
			Predicate<Point> notVertical = (p) -> (p.getX() != p1.getX() || 
					p.getY() > maxY || p.getY() < minY);
			points.removeIf(notVertical);
		} else if (p1.getY() == p2.getY()){
			// deal with two input points on a horizontal line segment.
			double minX = Math.min(p1.getX(), p2.getX());
			double maxX = Math.max(p1.getX(), p2.getX());
			Predicate<Point> notHorizontal = (p) -> (p.getY() != p1.getY() || 
					p.getX() > maxX || p.getX() < minX);
			points.removeIf(notHorizontal);
		} else {
			// deal with the real "CROP" method.
			double minX = Math.min(p1.getX(), p2.getX());
			double maxX = Math.max(p1.getX(), p2.getX());
			double minY = Math.min(p1.getY(), p2.getY());
			double maxY = Math.max(p1.getY(), p2.getY());
			Predicate<Point> notRectangular = (p) -> (p.getX() < minX || 
					p.getX() > maxX || p.getY() < minY || p.getY() > maxY);
			points.removeIf(notRectangular);
		}
 	}

	/**
	 * @param args:no args
	 */
	public static void main(String[] args) {

		Cloud cloud = new Cloud();
		
		cloud.setDebug(false);
		System.out.println("cloud.debug OFF");
		System.out.println("cloud: " + cloud);
		
		if(!cloud.isEmpty())
			System.out.println("Error: cloud should be empty!");
		
		if(cloud.extremes()!=null)
			System.out.println("Error: extremes should be null!");
		
		if(cloud.minDist()!=0.0)
			System.out.println("Error: minDist should return 0.0!");

			
		Point p31 = new Point(3.0,1.0);
		cloud.addPoint(p31);
		
		Point p22 = new Point(2.0,2.0);
		cloud.addPoint(p22);
			
		Point p42 = new Point(4.0,2.0);
		cloud.addPoint(p42);
		
		Point p33 = new Point(3.0,3.0);
		cloud.addPoint(p33);

		System.out.println("cloud 1: " + cloud);
		
		System.out.println("center point in cloud: " + cloud.centerP());

		System.out.println("cloud: " + cloud);
		System.out.println("cloud 2: " + cloud);
		

		Point p77 = new Point(7,7);
		if (cloud.hasPoint(p77))
			System.out.println("Error: point " + p77 + " should not be in cloud!");
		else
			System.out.println("OK: point " + p77 + " is not in cloud");

		double[] extrs = cloud.extremes();
		if(extrs!=null){
			System.out.println("left: " + extrs[0]);
		    System.out.println("right: " + extrs[1]);
		    System.out.println("top: " + extrs[2]);
		    System.out.println("bottom: " + extrs[3]);
		}
		double minD = cloud.minDist();			
		System.out.printf("min dist in cloud: %5.3f \n", minD);				
		
		System.out.println("Test cloud with 1 point");
		Cloud cloud1 = new Cloud();
		Point p = new Point();
		cloud1.addPoint(p);
	    minD = cloud1.minDist();
		System.out.printf("min dist in cloud1: %5.3f \n",  minD);

	}

}
