import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by candy on 2/5/18.
 */
public class slidingWindowMaximum {

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new int[0];
        int len = nums.length;
        int[] ans = new int[len - k + 1];
        Deque<Integer> dq = new LinkedList<>();
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            while (!dq.isEmpty() && dq.peek() < i - k + 1) {
                dq.poll();
            }
            while (!dq.isEmpty() && nums[dq.peekLast()] < nums[i]) {
                dq.pollLast();
            }
            dq.offer(i);
            if (i >= k - 1) {
                ans[index++] = nums[dq.peek()];
            }
        }
        return ans;
    }
}
