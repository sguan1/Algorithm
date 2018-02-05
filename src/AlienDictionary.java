import java.util.*;

/**
 * Created by candy on 2/5/18.
 */
public class AlienDictionary {

    Map<Character, Integer> indegree;
    Map<Character, List<Character>> outdegree;
    public String alienOrder(String[] words) {
        indegree = new HashMap<>();
        outdegree = new HashMap<>();
        initialize(words);
        String prev = words[0];
        for (int i = 1; i < words.length; i++) {
            String curr = words[i];
            int len = Math.min(curr.length(), prev.length());
            int j = 0;
            for (; j < len; j++) {
                char c1 = prev.charAt(j);
                char c2 = curr.charAt(j);
                if (c1 == c2) continue;
                indegree.put(c2, indegree.get(c2) + 1);
                outdegree.get(c1).add(c2);
                break;
            }
            prev = curr;
        }
        StringBuilder sb = new StringBuilder();
        Queue<Character> queue = new LinkedList<>();
        for (char key : indegree.keySet()) {
            if (indegree.get(key) == 0) {
                queue.offer(key);
            }
        }
        Set<Character> set = new HashSet<>();
        while (!queue.isEmpty()) {
            char curr = queue.poll();
            if (!set.add(curr)) {
                return "";
            }
            sb.append(curr);
            for (char c : outdegree.get(curr)) {
                indegree.put(c, indegree.get(c) - 1);
                if (indegree.get(c) == 0) {
                    queue.offer(c);
                }
            }
        }
        if (sb.length() == indegree.size()) return sb.toString();
        return "";
    }

    private void initialize(String[] words) {
        for (String word : words) {
            for(char c : word.toCharArray()) {
                if (!indegree.containsKey(c)) {
                    indegree.put(c, 0);
                    outdegree.put(c, new ArrayList<>());
                }
            }
        }
    }
}
