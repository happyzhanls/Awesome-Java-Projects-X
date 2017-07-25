// QuickSort.java - modified quickSort for assignment
// Author: Chris Wilcox
// Date:   3/4/2017
// Class:  CS165
// Email:  wilcox@cs.colostate.edu
// Original from Liang textbook
// Edited by cole

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class QuickSort {
    // Implement quicksort by using the first element as the pivot.
    // Partition elements below and above pivot then recurse using the sublist method
    public static void quickSort(List<Integer> list, int base_case_size) {
    	assert base_case_size > 0 : String.format("base_case_size %d should be positive!", base_case_size);
	
    	// REPLACE THIS COMMENT WITH YOUR CODE
    	if (list.size() <= base_case_size) {
    		System.out.println("The list to be bubble sorted is " + list);
    		BubbleSort.bubbleSort(list);
    	} else {
    		int pivotIndex = partition(list);
    		System.out.println(pivotIndex);
    		// Quick sort the firstHalf recursively
    		List<Integer> firstHalf = list.subList(0, pivotIndex);
    		quickSort(firstHalf, base_case_size);
    		// Quick sort the secondHalf recursively
    		List<Integer> secondHalf = list.subList(pivotIndex + 1, list.size());
    		quickSort(secondHalf, base_case_size);
    	}
    	
		assert SortTest.isSorted(list);
    }

    public static boolean isPartitionedAroundIndex(int pivot_index, List<Integer> list) {
    	// REPLACE THIS COMMENT WITH YOUR CODE
        return false;
    }
    
    // Partition the array using the first element as the pivot.
    // Return the final position of pivot
    public static int partition(List<Integer> list) {
    	
    	// REPLACE THIS COMMENT WITH YOUR CODE
    	LinkedList<Integer> q = new LinkedList<Integer>(list);
    	int pivot = q.element();
    	int pivotIndex = q.size() - 1;
    	for (int i = q.size() - 1; i > 0; i--) {
    		if (q.get(i) >= pivot) {
    			Collections.swap(q, pivotIndex, i);
    			pivot--;
    		}
    	}
    	Collections.swap(q, pivotIndex, 0);
    	return pivotIndex;
    }
    
    public static void main(String[] args) {
    	GenerativeSortTesting.TestCase case1 = GenerativeSortTesting.generateTestCase(6448765244830161737L);
    	System.out.println("The test list is " + case1.list);
    	System.out.println("The base_case_size is " + case1.base_case_size);
    	ArrayList<Integer> list = new ArrayList<>(case1.list);
    	quickSort(list, case1.base_case_size);
	}
}
