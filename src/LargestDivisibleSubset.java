import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by candy on 2/10/18.
 */
public class LargestDivisibleSubset {

    public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> list = new ArrayList<>();
        if (nums == null || nums.length == 0) return list;
        Arrays.sort(nums);
        int len = nums.length;
        int[] pre = new int[len];
        int[] dp = new int[len];
        Arrays.fill(dp, 1);
        int max = 1;
        int max_index = 0;
        for (int i = 1; i < len; i++) {
            pre[i] = i;
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0 && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    pre[i] = j;
                }
            }
            if (dp[i] > max) {
                max = dp[i];
                max_index = i;
            }
        }
        list.add(nums[max_index]);
        while (max_index != pre[max_index]) {
            max_index = pre[max_index];
            list.add(0, nums[max_index]);
        }
        return list;

    }
}
