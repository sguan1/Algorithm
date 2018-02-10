import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by candy on 2/9/18.
 */
public class RussianDollEnvelopes {

    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes.length == 0 || envelopes[0].length == 0) {
            return 0;
        }
        Arrays.sort(envelopes, new Comparator<int[]>(){
            public int compare(int[] e1, int[] e2) {
                if (e1[0] == e2[0]) {
                    return Integer.compare(e2[1], e1[1]);
                }
                return Integer.compare(e1[0], e2[0]);
            }
        });
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < envelopes.length; i++) {
            if (list.size() == 0 || list.get(list.size() - 1) < envelopes[i][1] ) {
                list.add(envelopes[i][1]);
            }
            int left = 0;
            int right= list.size() - 1;
            while (left + 1 < right) {
                int mid = left + (right - left) / 2;
                if (list.get(mid) < envelopes[i][1]) {
                    left = mid;
                }
                else{
                    right = mid;
                }
            }
            if (list.get(left) >= envelopes[i][1]) {
                list.set(left, envelopes[i][1]);
            } else {
                list.set(right, envelopes[i][1]);
            }
        }
        return list.size();
    }
}
