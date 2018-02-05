import java.util.HashMap;
import java.util.Map;

/**
 * Created by candy on 2/5/18.
 */
public class containDuplicates_Bucket {

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (k < 1 || t < 0) return false;
        Map<Long, Long> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            long remap = (long) nums[i] - Integer.MIN_VALUE;
            long bucket = remap / ((long) t + 1);
            if (map.containsKey(bucket) ||
                    map.containsKey(bucket - 1) && remap - map.get(bucket - 1) <= t ||
                    map.containsKey(bucket + 1) && map.get(bucket + 1) - remap <= t) {
                return true;
            }
            if (map.entrySet().size() >= k) {
                map.remove(((long) nums[i - k] - Integer.MIN_VALUE) / ((long) t + 1));
            }
            map.put(bucket, remap);
        }
        return false;
    }
}
