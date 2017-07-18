// TestProgram.java - test program for data structures assignment
// Author: Chris Wilcox
// Date:   2/18/2017
// Class:  CS165
// Email:  wilcox@cs.colostate.edu

import java.util.Iterator;

public class TestProgram {

    static TrainCar car0 = new TrainCar(TrainCar.eType.ENGINE,    320983, "Santa Fe");  
    static TrainCar car1 = new TrainCar(TrainCar.eType.PASSENGER, 239384, "Santa Fe");
    static TrainCar car2 = new TrainCar(TrainCar.eType.DINING,    878742, "Santa Fe");
    static TrainCar car3 = new TrainCar(TrainCar.eType.FREIGHT,   739829, "Santa Fe");
    static TrainCar car4 = new TrainCar(TrainCar.eType.TANKER,    598282, "Santa Fe");
    static TrainCar car5 = new TrainCar(TrainCar.eType.CABOOSE,   629837, "Santa Fe");
    static TrainCar car6 = new TrainCar(TrainCar.eType.CABOOSE,   888888, "Santa Fe");

    public static void main(String[] args) {

        if (args.length != 2) {
            System.err.println("usage1: java TestProgram testNumber <-arraylist>");
            System.err.println("usage1: java TestProgram testNumber <-linkedlist>");
            System.exit(-1);
        }

        // Initialize test number
        int testNumber = Integer.parseInt(args[0]);

        // Select between ArrayList and LinkedList
        String listType = args[1];
        
        // Print train cars
        System.out.println("car0: " + car0.toString()); 
        System.out.println("car1: " + car1.toString()); 
        System.out.println("car2: " + car2.toString()); 
        System.out.println("car3: " + car3.toString()); 
        System.out.println("car4: " + car4.toString()); 
        System.out.println("car5: " + car5.toString()); 
        System.out.println("car6: " + car6.toString()); 

        // Call appropriate test method
        if (listType.equals("-arraylist"))
            testArrayList(testNumber);
        else // if (listType.equals("-linkedlist"))
            testLinkedList(testNumber);
    }

    private static void testArrayList(int testNumber) {

        CoolArrayList<TrainCar> arraylist = new CoolArrayList<>();
        ListInterface<TrainCar> list = arraylist;


        switch (testNumber) {

        case 0: // sanity test, no reallocation
            sanityTest(list);
            break;

        case 1: // reallocation test
            reallocationTest(arraylist);
            break;
        }
    }
    private static void testLinkedList(int testNumber) {

        CoolLinkedList<TrainCar> linkedlist = new CoolLinkedList<>();
        ListInterface<TrainCar> list = linkedlist;

        switch (testNumber) {

        case 0: // sanity test, no reallocation
            sanityTest(list);
            break;

        case 1: // iterator test
            iteratorTest(linkedlist);
            break;
        }
    }

    //
    // Test methods
    //
    private static void sanityTest(ListInterface<TrainCar> list) {

        // Test adds/removes/set
        System.out.println("list.add(car0)");    list.add(car0); 
        System.out.println("list.add(car1)");    list.add(car1); 
        System.out.println("list.add(car2)");    list.add(car2); 
        System.out.println("list.add(car4)");    list.add(car4); 
        System.out.println("list.add(3, car3)"); list.add(3, car3); 
        System.out.println("list.add(car4)");    list.add(car4); 
        System.out.println("list.set(5, car5)"); list.set(5, car5); 
        System.out.println("list.add(1, car0)"); list.add(1, car0); 
        System.out.println("list.add(2, car1)"); list.add(2, car1); 
        System.out.println("list.remove(2)");    list.remove(2); 
        System.out.println("list.remove(car0)"); list.remove(car0); 
        System.out.println("list.add(car2)");    list.add(car2); 

        // Test size/isEmpty/get
        System.out.println("list.size(): " + list.size()); 
        System.out.println("list.isEmpty(): " + list.isEmpty()); 
        for (int i = 0; i < list.size(); i++)
            System.out.println("list.get(" + i + "): " + list.get(i)); 

        // Test contains/indexOf/lastIndexOf
        System.out.println("list.contains(car5): " + list.contains(car5)); 
        System.out.println("list.contains(car6): " + list.contains(car6)); 
        System.out.println("list.indexOf(car4): " + list.indexOf(car4)); 
        System.out.println("list.lastIndexOf(car2): " + list.lastIndexOf(car2)); 
    }

    private static void reallocationTest(CoolArrayList<TrainCar> list) {

        // Add 1000 elements
        for (int i = 0; i < 1000; i++)
            list.add(new TrainCar(TrainCar.eType.ENGINE, i, "Union Pacific"));

        // Remove 500 elements
        for (int i = 999; i >= 0; i-=2)
            list.remove(i); 

        System.out.println("list.size(): " + list.size());
        System.out.println("list.get(10): " + list.get(10)); 
        System.out.println("list.get(77): " + list.get(77)); 
        System.out.println("list.get(123): " + list.get(123)); 
        System.out.println("list.get(256): " + list.get(256)); 
        System.out.println("list.get(299): " + list.get(299)); 
        System.out.println("list.get(386): " + list.get(386)); 
        System.out.println("list.get(499): " + list.get(499)); 
    }
    
    private static void iteratorTest(CoolLinkedList<TrainCar> list) {
        System.out.println("list.add(car0)");    list.add(car0); 
        System.out.println("list.add(car1)");    list.add(car1); 
        System.out.println("list.add(car2)");    list.add(car2); 
        System.out.println("list.add(car3)");    list.add(car3); 
        System.out.println("list.add(car4)");    list.add(car4); 
        System.out.println("list.add(car5)");    list.add(car5);
        Iterator<TrainCar> it = list.iterator();
        int index = 0;
        System.out.println("list.size(): " + list.size());
        while (it.hasNext()) {
            TrainCar t = it.next();
            System.out.println(index + ": " + t.toString());
            index++;
        }
    }
}
