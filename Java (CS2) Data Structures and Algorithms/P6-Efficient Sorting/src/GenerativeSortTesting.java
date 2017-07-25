import java.io.PrintStream;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

/**
 * Generative testing for sorting routines.
 * Use the main method or call the public static methods to test your
 * sorting methods with pseudo-random data.
 *
 * exercise_sorter repeatedly runs verifyTestCase with different seeds.
 * verifyTestCase uses the seed to generate a test case, then it uses the sort
 * method provided to sort the generated list, and verifies that the list is now correctly sorted.
 *
 * If verifyTestCase or the sort method throw, then exercise_sorter will report
 * the seed used to create the failed test case before rethrowing. You can reproduce that
 * same failing case by calling verifyTestCase with the same seed. This way you can keep working
 * with the failing case until your method is fixed and it passes.
 *
 * For your convenience, after you fix a failing test case, paste that seed into the
 * verifyMyRegressionTests method so that you can easily verify that those
 * tests still pass after you make changes to your codebase.
 *
 * Created by cole on 7/14/17.
 */
public class GenerativeSortTesting {
    private static int nextIntBetween(Random r, int lower, int upper) {
        return lower + r.nextInt(Math.max(1, upper - lower));
    }

    private static int clamp(int x, int low, int high) {
        return Math.max(low, Math.min(high, x));
    }

    public static class TestCase {
        final public int base_case_size;
        final public List<Integer> list;
        public TestCase(int bcs, List<Integer> list) {
            this.base_case_size = bcs;
            this.list = list;
        }
    }

    public static TestCase generateTestCase(final long seed) {
        final Random r = new Random(seed);
        final int which_magnitude = 1 + r.nextInt(100);
        int x;
        {
            if (which_magnitude <= 50) {
                // small size
                x = 1 + r.nextInt(50);
            } else if (which_magnitude <= 85) {
                // medium size
                x = 50 + r.nextInt(1000);
            } else if (which_magnitude <= 98) {
                // large size
                x = 1000 + r.nextInt(100000); // one hundred thousand
            } else {
                // extra large size
                x = 100000 + r.nextInt(4000000); // four million
            }
        }
        final int list_size = x;
        assert list_size >= 1;
        final int base_case_size = 1 + r.nextInt(clamp(list_size / 4, 1, 50));
        assert base_case_size >= 1;
        final int which_density = 1 + r.nextInt(10);
        {
            final int ninety = (int) (0.9 * list_size);
            if (which_density <= 2) {
                // high density
                x = nextIntBetween(r, (int) (0.3 * list_size), ninety);
            } else if (which_density <= 7) {
                // normal density
                x = nextIntBetween(r, ninety, 2 * list_size);
            } else {
                // low density
                final int upper = 100 * list_size; // could overflow. use max int value instead
                x = nextIntBetween(r, 2 * list_size, upper >= 0 ? upper : Integer.MAX_VALUE);
            }
        }
        final int value_upper_bound = Math.max(2, x);
        List<Integer> numbers = r.ints(list_size, 0, value_upper_bound)
                .boxed().collect(Collectors.toCollection(ArrayList::new));

        return new TestCase(base_case_size, Collections.unmodifiableList(numbers));
    }

    private static boolean should_log = true;
    public static void should_log(boolean b) { should_log = b; }
    private static void log(String s) { if (should_log) System.out.println(s); }

    private static String shortListToString(List<?> list, int max_length) {
        StringBuilder sb = new StringBuilder(max_length);
        boolean broke = false;
        sb.append("[");
        for (Object e : list) {
            String s = e.toString();
            if (sb.length() + s.length() + 4 > max_length) {
                broke = true;
                break;
            }
            sb.append(s);
            sb.append(", ");
        }
        if (broke)
            sb.append("...]");
        else {
            sb.setLength(sb.length() - 2);
            sb.append("]");
        }
        return sb.toString();
    }

    public static void verifyTestCase(final long seed, BiConsumer<List<Integer>, Integer> sorter) {
        final TestCase testCase = generateTestCase(seed);
        final List<Integer> immutable_list = testCase.list;
        final int base_case_size = testCase.base_case_size;
        final ArrayList<Integer> list = new ArrayList<>(immutable_list);

        log(String.format("Testing - list.size() => %7d, base_case_size => %2d, contents: %s",
                list.size(), base_case_size, shortListToString(list, 100)));

        sorter.accept(list, base_case_size);
        if (!is_sorted(list))
            throw new RuntimeException("Returned list was not sorted!");
        if (!is_permutation(immutable_list, list))
            throw new RuntimeException("Returned list does not contain the same elements as original!");
    }

    public static boolean is_permutation(List<Integer> a, List<Integer> b) {
        if (a.size() != b.size()) return false;
        Map<Integer, Integer> a_counts = new HashMap<>(a.size());
        Map<Integer, Integer> b_counts = new HashMap<>(b.size());
        a.forEach(i -> a_counts.merge(i, 1, Integer::sum));
        b.forEach(i -> b_counts.merge(i, 1, Integer::sum));
        return a_counts.equals(b_counts);
    }

    public static boolean is_sorted(final List<Integer> list) {
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i - 1) > list.get(i)) {
                return false;
            }
        }
        return true;
    }

    public static void exercise_sorter(BiConsumer<List<Integer>, Integer> sorter) {
        exercise_sorter(sorter, Integer.MAX_VALUE);
    }

    public static void exercise_sorter(BiConsumer<List<Integer>, Integer> sorter, int max_tests) {
        new Random().longs(max_tests).forEach((long seed) -> {
            try {
                verifyTestCase(seed, sorter);
            } catch (Throwable ex) {
                final PrintStream e = System.err;
                e.println("Found failing case!!");
                e.printf("Using the test case returned by: generateTestCase(%d)\n", seed);
                e.printf("Reproduce it with: verifyTestCase(%d, <sorter-you-are-testing>);\n", seed);
                e.println("Hint: <sorter-you-are-testing> is either MergeSort::mergeSort or QuickSort::quickSort.");
                throw ex;
            }
                }
        );
    }

    /**
     * Test previously failing cases.
     * Add failing test case seeds to the appropriate array below,
     * and this method will regression test these cases to ensure
     * your implementations haven't regressed.
     *
     * Generative testing is randomly making up test cases, some are run-of-the-mill,
     * others are diamonds in the rough. When you find a failing test case, work out bugs until
     * it begins passing, then add its seed here to ensure you can easily check that case again.
     */
    public static void verifyMyRegressionTests() {
        final long[] merge_tests = {-7930781901242033804L}; // add seeds here
        Arrays.stream(merge_tests).forEach(seed -> verifyTestCase(seed, MergeSort::mergeSort));

        final long[] quick_tests = {}; // add seeds here
        Arrays.stream(quick_tests).forEach(seed -> verifyTestCase(seed, QuickSort::quickSort));
    }

    public static void main(String[] args) {
        // should_log(false); // uncomment to turn off logging

        verifyMyRegressionTests(); // utility method to test the seeds you specify

        // exercise_sorter(MergeSort::mergeSort); // find new failing cases for mergesort
        exercise_sorter(QuickSort::quickSort); // find new failing cases for quicksort
    }
}
