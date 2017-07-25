// BubbleSort.java - normal bubbleSort for assignment
// Author: Chris Wilcox
// Date:   3/4/2017
// Class:  CS165
// Email:  wilcox@cs.colostate.edu
// Edited by cole

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BubbleSort {
    public static void bubbleSort(List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            boolean swap_during_pass = false;
            // only go up to (size - i) since after i passes,
            // the i largest elements are in position
            for (int j = 1; j < (list.size() - i); j++) {
                if (list.get(j - 1) > list.get(j)) {
                    swap_during_pass = true;
                    System.out.println(list);
                    Collections.swap(list, j - 1, j);
                }
            }
            // exit early if sorted
            if(!swap_during_pass)
                break;
        }
        assert SortTest.isSorted(list);
    }
}
