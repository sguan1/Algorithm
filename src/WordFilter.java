import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by candy on 1/30/18.
 */
public class WordFilter {
    TrieNode root;
    public WordFilter(String[] words) {
        root = new TrieNode();
        for(int i = 0; i < words.length; i++) {
            String word = words[i];
            for (int j = 0; j <= word.length(); j++) {
                add(word.substring(j) + "{" + word, i);
            }
        }
    }

    public int f(String prefix, String suffix) {
        String word = suffix + "{" + prefix;
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (node.links[c - 'a'] == null) {
                return -1;
            }
            node = node.links[c - 'a'];
        }
        return node.weight;
    }

    private void add(String s, int weight) {
        TrieNode node = root;
        for (char c : s.toCharArray()) {
            if (node.links[c - 'a'] == null) {
                node.links[c - 'a'] = new TrieNode();
            }
            node = node.links[c - 'a'];
            node.weight = weight;
        }

    }

    class TrieNode {
        TrieNode[] links;
        int weight;
        public TrieNode() {
            links = new TrieNode[27];
            weight = 0;
        }
    }
}
