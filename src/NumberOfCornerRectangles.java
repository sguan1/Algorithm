import java.util.*;

/**
 * Created by candy on 1/29/18.
 */
public class NumberOfCornerRectangles {
    public int countCornerRectangles(int[][] grid) {
        List<List<Integer>> list = new ArrayList<>();
        int N = 0;
        int ans = 0;
        for (int row = 0; row < grid.length; row++) {
            list.add(new ArrayList<>());
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 0) {
                    continue;
                }
                list.get(row).add(col);
                N++;
            }
        }
        int sqrtN = (int) Math.sqrt(N);
        Map<Integer, Integer> map = new HashMap<>();
        for (int row = 0; row < grid.length; row++) {
            if (list.get(row).size() >= sqrtN) {
                Set<Integer> target = new HashSet<>(list.get(row));
                for (int r = 0; r < grid.length; r++) {
                    if (r <= row && list.get(r).size() >= sqrtN) {
                        continue;
                    }
                    int found = 0;
                    for (int c : list.get(r)) {
                        if (target.contains(c)) {
                            found++;
                        }
                    }
                    ans += found * (found - 1) / 2;
                }
            } else {
                for (int i1 = 0; i1 < list.get(row).size(); i1++) {
                    int c1 = list.get(row).get(i1);
                    for (int i2 = i1 + 1; i2 < list.get(row).size(); i2++) {
                        int c2 = list.get(row).get(i2);
                        int key = c1 * 200 + c2;
                        int count = map.getOrDefault(key, 0);
                        ans += count;
                        map.put(key, count + 1);
                    }
                }
            }
        }
        return ans;
    }
}
