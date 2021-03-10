package algorithm.sort;

import org.junit.Test;

import java.util.Arrays;

public class SortTest {

    @Test
    public void SelectSort() {
        Integer[] nums = {5, 23, 10, 20, 12, 53, 6};
        new Selection().sort(nums);
        System.out.println(Arrays.asList(nums));
    }

    @Test
    public void BubbleSort() {
        Integer[] nums = {5, 23, 10, 20, 12, 53, 6};
        new Bubble().sort(nums);
        System.out.println(Arrays.asList(nums));
    }

    @Test
    public void InsertionSort() {
        Integer[] nums = {5, 23, 10, 20, 1, 12, 53, 6};
        new Insertion().sort(nums);
        System.out.println(Arrays.asList(nums));
    }
}