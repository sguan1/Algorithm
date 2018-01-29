import java.util.*;

/**
 * Created by candy on 1/29/18.
 */
public class courseSchedule1 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> indegree = new HashMap<>();
        Map<Integer, List<Integer>> outdegree = new HashMap<>();
        for (int[] prerequisite : prerequisites) {
            int pre = prerequisite[0];
            int post = prerequisite[1];
            if (!indegree.containsKey(pre)) {
                indegree.put(pre, new ArrayList<>());
                outdegree.put(pre, new ArrayList<>());
            }
            if (!indegree.containsKey(post)) {
                indegree.put(post, new ArrayList<>());
                outdegree.put(post, new ArrayList<>());
            }
            indegree.get(post).add(pre);
            outdegree.get(pre).add(post);
        }
        //start from the course with zero ingree
        Queue<Integer> queue = new LinkedList<>();
        for (int key : indegree.keySet()) {
            if (indegree.get(key).size() == 0) {
                queue.offer(key);
            }
        }
        Set<Integer> set = new HashSet<>();
        while (!queue.isEmpty()) {
            for (int i = 0; i < queue.size(); i++) {
                Integer curr = queue.poll();
                set.add(curr);
                List<Integer> nexts = outdegree.get(curr);
                for (int next : nexts) {
                    indegree.get(next).remove(curr);
                    if (indegree.get(next).size() == 0) {
                        queue.offer(next);
                    }
                }
            }
        }
        return set.size() == indegree.size();
    }
}
