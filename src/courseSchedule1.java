import java.util.*;

/**
 * Created by candy on 1/29/18.
 */
public class courseSchedule1 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, Integer> indegree = new HashMap<>();
        Map<Integer, List<Integer>> outdegree = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            indegree.put(i, 0);
            outdegree.put(i, new ArrayList<>());
        }
        for (int[] prerequisite : prerequisites) {
            int pre = prerequisite[0];
            int post = prerequisite[1];
            indegree.put(post, indegree.get(post) + 1);
            outdegree.get(pre).add(post);
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int key : indegree.keySet()) {
            if (indegree.get(key) == 0) {
                queue.offer(key);
            }
        }
        List<Integer> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            for (int i = 0; i < queue.size(); i++) {
                Integer curr = queue.poll();
                list.add(curr);
                List<Integer> nexts = outdegree.get(curr);
                for (int next : nexts) {
                    indegree.put(next, indegree.get(next) - 1);
                    if (indegree.get(next) == 0) {
                        queue.offer(next);
                    }
                }
            }
        }
        return list.size() == numCourses;
    }
}
