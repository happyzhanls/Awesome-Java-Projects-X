import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Scanner;

public class P10 implements Interface {
	
	public static void main(String[] args) {
	    // Instantiate student code
	    P10 p10 = new P10();
	    
	    // Test readTemperatures
	    Temperature[] data = p10.readTemperatures("Temperatures.csv");
	    
	    // Test findMinimum
	    Date start = Temperature.createDate("04-Jul-2008", "06:00");
	    Date end = Temperature.createDate("17-Aug-2010", "23:00");
	    System.out.println("Verifying findMinimum method:");
	    System.out.println("Start date: " + start.toString());
	    System.out.println("End date: " + end.toString());
	    System.out.printf("Minimum = %.1f degrees\n", p10.findMinimum(start, end, data));

	    // Test findMaximum 
	    start = Temperature.createDate("19-Sep-2011", "07:00");
	    end = Temperature.createDate("23-Mar-2015", "13:00");
	    System.out.println("Verifying findMaximum method:");
	    System.out.println("Start date: " + start.toString());
	    System.out.println("End date: " + end.toString());
	    System.out.printf("Maximum = %.1f degrees\n", p10.findMaximum(start, end, data));

	    // Test findAverage
	    start = Temperature.createDate("09-Apr-2006", "19:00");
	    end = Temperature.createDate("31-Oct-2013", "10:00");
	    System.out.println("Verifying findAverage method:");
	    System.out.println("Start date: " + start.toString());
	    System.out.println("End date: " + end.toString());
	    System.out.printf("Average = %.1f degrees\n", p10.findAverage(start, end, data));

	    // Test findHighest
	    start = Temperature.createDate("01-Jan-2015", "00:00");
	    end = Temperature.createDate("31-Dec-2015", "23:00");
	    System.out.println("Verifying findHighest method:");
	    System.out.println("Start date: " + start.toString());
	    System.out.println("End date: " + end.toString());
	    System.out.printf("Highest windspeed = %.1f\n", p10.findHighest(start, end, data));
	}
	
	@Override
	public Temperature[] readTemperatures(String filename) {
		File f = new File(filename);
		try {
			Scanner sc = new Scanner(f);
			int length = Integer.parseInt(sc.nextLine().trim()) + 1;
			Temperature[] temperatureArray = new Temperature[length];
			String dayMonthYear, hour;
			double degrees, speed;
			int i = 0;
			while (sc.hasNextLine()) {
				String[] field = sc.nextLine().split(" ");
				dayMonthYear = field[0];
				hour = field[1];
				degrees = Double.parseDouble(field[2]);
				speed = Double.parseDouble(field[3]);
				temperatureArray[i] = new Temperature(dayMonthYear, hour, degrees, speed);
				i++;
			}
			return temperatureArray;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public double findMinimum(Date start, Date end, Temperature[] data) {
		int length = data.length;
		double minTemp = Double.MAX_VALUE;
		for (int i = 0; i < length; i++) {
			if (data[i].inInterval(start, end) && data[i].temperature < minTemp) {
				minTemp = data[i].temperature;
			}
		}
		return minTemp;
	}

	@Override
	public double findMaximum(Date start, Date end, Temperature[] data) {
		int length = data.length;
		double maxTemp = Double.MIN_VALUE;
		for (int i = 0; i < length; i++) {
			if (data[i].inInterval(start, end) && data[i].temperature > maxTemp) {
				maxTemp = data[i].temperature;
			}
		}
		return maxTemp;
	}

	@Override
	public double findAverage(Date start, Date end, Temperature[] data) {
		int length = data.length;
		double totalTemp = 0;
		int num = 0;
		for (int i = 0; i < length; i++) {
			if (data[i].inInterval(start, end)) {
				totalTemp += data[i].temperature;
				num++;
			}
		}
		if (num == 0)
			return 0;
		double averTemp = totalTemp / num;
		return averTemp;
	}

	@Override
	public double findHighest(Date start, Date end, Temperature[] data) {
		int length = data.length;
		double maxWindspeed = Double.MIN_VALUE;
		for (int i = 0; i < length; i++) {
			if (data[i].inInterval(start, end) && data[i].windspeed > maxWindspeed) {
				maxWindspeed = data[i].windspeed;
			}
		}
		return maxWindspeed;
	}

}
