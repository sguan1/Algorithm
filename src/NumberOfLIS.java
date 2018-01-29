/**
 * Created by candy on 1/29/18.
 */
public class NumberOfLIS {
    public int findNumberOfLIS(int[] nums) {
        int len = nums.length;
        int[] length = new int[len];
        int[] count = new int[len];
        int max = 0;
        for (int i = 0; i < len; i++) {
            length[i] = 1;
            count[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] <= nums[j]) {
                    continue;
                }
                if (length[j] + 1 > length[i]) {
                    length[i] = length[j] + 1;
                    count[i] = count[j];
                } else if (length[j] + 1 == length[i]) {
                    count[i] += count[j];
                }
            }
            max = Math.max(max, length[i]);
        }
        int ans = 0;
        for (int i = 0; i < len; i++) {
            if (length[i] == max) {
                ans += count[i];
            }
        }
        return ans;

    }
}
