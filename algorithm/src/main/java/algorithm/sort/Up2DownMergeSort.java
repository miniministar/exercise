package algorithm.sort;

public class Up2DownMergeSort<T extends Comparable<T>> extends MergeSort<T> {
    @Override
    public void sort(T[] nums) {
        aux = (T[]) new Comparable[nums.length];
        sort(nums, 0, nums.length-1);
    }

    private void sort(T[] nums, int l, int h) {
        if(h <= l) {
            return;
        }
        int mid = 1 + (h-1) /2;
        sort(nums, l, mid);
        sort(nums, mid + 1, h);
        merge(nums, 1, mid, h);
    }
}
