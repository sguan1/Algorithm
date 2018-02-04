import java.util.HashMap;
import java.util.Map;

/**
 * Created by candy on 2/2/18.
 */
public class containDuplicates {
    public static void main(String[] args) {
        System.out.println(containsNearbyAlmostDuplicate(new int[]{-3,3},2 ,4));
    }
    public static boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums.length <= 1) return false;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            for (int num = nums[i] - t; num <= nums[i] + t; num++) {
                if (!map.containsKey(num)) {
                    continue;
                }
                if (i - map.get(num) <= k) return true;
            }
            map.put(nums[i], i);
        }
        return false;
    }
}
