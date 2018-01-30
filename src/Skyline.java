import java.util.*;

/**
 * Created by candy on 1/30/18.
 */
public class Skyline {
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> heights = new ArrayList<>();
        for (int[] building : buildings) {
            heights.add(new int[]{building[0], building[2]});
            heights.add(new int[]{building[1], -building[2]});
        }
        Collections.sort(heights, new Comparator<int[]>(){
            public int compare(int[] array1, int[] array2) {
                if (array1[0] != array2[0])
                    return array1[0] - array2[0];
                return array2[1] - array1[1];
            }
        });
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        pq.offer(0);
        List<int[]> res = new ArrayList<>();
        int prev = 0;
        for (int[] height : heights) {
            if (height[1] > 0) {
                pq.offer(height[1]);
            } else {
                pq.remove(-height[1]);
            }
            int curr = pq.peek();
            if (curr != prev) {
                res.add(new int[]{height[0], pq.peek()});
                prev = curr;
            }
        }
        return res;
    }
}
