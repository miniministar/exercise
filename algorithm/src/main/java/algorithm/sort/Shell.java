package algorithm.sort;

import java.util.Arrays;

public class Shell<T extends Comparable<T>> extends Sort<T> {

    @Override
    public void sort(T[] nums) {
        int N = nums.length;
        int h = 1;
        while ( h < N/3) {
            h = 3 * h + 1;
        }
        while (h >= 1) {
            System.out.println("h=" + h);
            System.out.println(Arrays.asList(nums));
            for (int i = h; i < N; i++) {
                System.out.println(Arrays.asList(nums));
                for (int j = i; j >= h && less(nums[j], nums[j-h]) ; j -= h) {
                    swap(nums, j, j-h);
                }
            }
            h = h / 3;
        }
    }
}
