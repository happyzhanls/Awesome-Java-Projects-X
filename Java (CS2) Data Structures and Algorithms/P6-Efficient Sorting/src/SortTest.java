// SortTest.java - test code for sorting assignment
// Author: Chris Wilcox
// Date:   3/4/2017
// Class:  CS165
// Email:  wilcox@cs.colostate.edu
// Edited by cole

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


public class SortTest {
    private static void test0(int base_case_size) {
        List<Integer> list = initialize(10);
        System.err.println("Verifying mergeSort (small list):");
        System.err.println("Start " + list.toString());
        MergeSort.mergeSort(list, base_case_size);
        System.err.println("End " + list.toString());
    }

    private static void test1(int base_case_size) {
        List<Integer> list = initialize(10);
        System.err.println("Verifying quickSort (small list):");
        System.err.println("Start " + list.toString());
        QuickSort.quickSort(list, base_case_size);
        System.err.println("End " + list.toString());
    }

    private static void test2(int base_case_size) {
        List<Integer> list = initialize(base_case_size);
        System.err.println("Verifying mergeSort (large list):");
        long begin = System.currentTimeMillis();
        MergeSort.mergeSort(list, base_case_size);
        System.out.println("Total millis: " + (System.currentTimeMillis() - begin));
    }

    private static void test3(int base_case_size) {
        List<Integer> list = initialize(base_case_size);
        System.err.println("Verifying quickSort (large list):");
        long begin2 = System.currentTimeMillis();
        QuickSort.quickSort(list, base_case_size);
        System.out.println("Total millis: " + (System.currentTimeMillis() - begin2));
    }

    public static void main(String[] args) {
	// Hint: you can hard code these number or change main entirely.
	// Do whatever you want here.
	
        int testNumber = Integer.parseInt(args[0]);
        int base_case_size = args.length > 1 ? Integer.parseInt(args[1]) : 20; // default of 20

        switch (testNumber) {
            case 0: // testMerge, small list
                test0(base_case_size);
                break;
            case 1: // testQuick, small list
                test1(base_case_size);
                break;
            case 2: // testMerge, large list
                test2(base_case_size);
                break;
            case 3: // testQuick, large list
                test3(base_case_size);
                break;
        }
    }

    // Initialize data set
    private static ArrayList<Integer> initialize(int dataSize) {
        final int upperBound = 100000, seed = 0;
        return new Random(seed).ints(dataSize, 0, upperBound)
                .boxed().collect(Collectors.toCollection(ArrayList::new));
    }

    // Verify sorted list
    public static boolean isSorted(List<Integer> list) {
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i - 1) > list.get(i)) {
                System.err.println("Not sorted correctly!");
                System.err.println("list.get(" + (i - 1) + ") = " + list.get(i - 1));
                System.err.println("list.get(" + (i) + ") = " + list.get(i));
                return false;
            }
        }
        return true;
    }
}
