// MergeSort.java - modified mergeSort for assignment
// Author: Chris Wilcox
// Date:   3/4/2017
// Class:  CS165
// Email:  wilcox@cs.colostate.edu
// Edited by cole

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MergeSort {
    public static void mergeSort(List<Integer> list, int base_case_size) {
        assert base_case_size > 0 : String.format("base_case_size %d should be positive!", base_case_size);

	// REPLACE THIS COMMENT WITH YOUR CODE
        if (list.size() <= base_case_size) {
        	BubbleSort.bubbleSort(list);
        } else {
        	// Merge sort the first Half 
        	List<Integer> firstHalf = list.subList(0, list.size() / 2);
        	mergeSort(firstHalf, base_case_size);
        	// Merge sort the second Half
        	List<Integer> secondHalf = list.subList(list.size() / 2, list.size());
        	mergeSort(secondHalf, base_case_size);
        	// Merge firstHalf with secondHalf into list
        	Collections.copy(list, merge(firstHalf, secondHalf));
        }
        assert SortTest.isSorted(list);
    }

    public static boolean isMerged(List<Integer> maybe_merged, List<Integer> L1, List<Integer> L2) {
	// REPLACE THIS COMMENT WITH YOUR CODE
    	return false;
    }

    // Merge two sorted lists
    public static List<Integer> merge(List<Integer> L1, List<Integer> L2) {
        assert SortTest.isSorted(L1) && SortTest.isSorted(L2);
        List<Integer> merged = new ArrayList<>();
        Queue<Integer> q1 = new LinkedList<Integer>(L1);
        Queue<Integer> q2 = new LinkedList<Integer>(L2);
        
	// REPLACE THIS COMMENT WITH YOUR CODE
        while (!q1.isEmpty() && !q2.isEmpty()) {
        	if (q1.element() > q2.element()) {
        		merged.add(merged.size(), q2.element());
        		q2.poll();
        	} else {
        		merged.add(merged.size(), q1.element());
        		q1.poll();
        	}
        }
        
        while (!q1.isEmpty()) {
        	merged.add(merged.size(), q1.element());
    		q1.poll();
        }
        
        while (!q2.isEmpty()) {
        	merged.add(merged.size(), q2.element());
    		q2.poll();
        }
        
        assert merged.size() == L1.size() + L2.size() && SortTest.isSorted(merged);
        return merged;
    }
}
