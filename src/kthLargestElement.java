/**
 * Created by candy on 2/5/18.
 */
public class kthLargestElement {

    public int findKthLargest(int[] nums, int k) {
        int index = quickSelect(nums, 0, nums.length - 1, nums.length - k + 1);
        return nums[index];
    }

    private int quickSelect(int[] nums, int start, int end, int k) {

        int left = start;
        int right = end;
        int pivot = nums[end];
        while (true) {
            while (nums[left] < pivot) {
                left++;
            }
            while(right > left && nums[--right] > pivot);
            if (left >= right) {
                break;
            }
            swap(nums, left, right);
        }
        swap(nums, left, end);
        if (left - start == k - 1) {
            return left;
        } else if (left - start > k - 1) {
            return quickSelect(nums, start, left - 1, k);
        } else {
            return quickSelect(nums, left + 1, end, k - left + start - 1);
        }
    }

    private void swap(int[] nums, int x, int y) {
        int tmp = nums[x];
        nums[x] = nums[y];
        nums[y] = tmp;
    }

}
