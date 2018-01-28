import java.util.HashMap;
import java.util.Map;

/**
 * Created by candy on 1/27/18.
 */
public class maxPointsOnaLine {
    public int maxPoints(Point[] points) {
        Map<String, Integer> map = new HashMap<>();
        int max = 0;
        for (int i = 0; i < points.length; i++) {
            map.clear();
            int dup = 0;
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].x == points[j].x && points[i].y == points[j].y) {
                    //count the duplicates
                    dup++;
                    continue;
                }
                int a = points[j].y - points[i].y;
                int b = points[j].x - points[i].x;
                int gcd = gcd(a, b);
                //divided by the greatest common divisor to remove the common factor
                a /= gcd;
                b /= gcd;
                String key = a + "/" + b;
                if (!map.containsKey(key)) {
                    map.put(key, 2);
                } else {
                    map.put(key, map.get(key) + 1);
                }
            }
            for (int val : map.values()) {
                if (val + dup > max) {
                    max = val + dup;
                }
            }
            //if all the points are just the same
            if (map.size() == 0) {
                max = Math.max(max, dup + 1);
            }
        }
        return max;
    }
    /*
     * calculate the greatest common divisor
     */
    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    class Point {
        int x;
        int y;
        Point(int a, int b) { x = a; y = b; }
    }
}
