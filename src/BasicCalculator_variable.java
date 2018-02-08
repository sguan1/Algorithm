import java.util.*;

/**
 * Created by candy on 2/8/18.
 */
public class BasicCalculator_variable {


    public List<String> basicCalculatorIV(String expression, String[] evalvars, int[] evalints) {
        Map<String, Integer> evalMap = new HashMap<>();
        for (int i = 0; i < evalvars.length; i++) {
            evalMap.put(evalvars[i], evalints[i]);
        }
        return parse(expression).evaluate(evalMap).toList();
    }

    private Exp parse(String exp) {
        List<Exp> bucket = new ArrayList<>();
        List<Character> operators = new ArrayList<>();
        int i = 0;

        while (i < exp.length()) {
            char c = exp.charAt(i);
            if (c == '(') {
                int count = 0;
                int j = i;
                for (; j < exp.length(); j++) {
                    if (exp.charAt(j) == '(') count++;
                    if (exp.charAt(j) == ')') count--;
                    if (count == 0) break;
                }
                bucket.add(parse(exp.substring(i + 1, j)));
                i = j;
            } else if (Character.isLetterOrDigit(c)) {
                int j = i;
                for (; j < exp.length(); j++) {
                    if (exp.charAt(j) == ' ') {
                        break;
                    }
                }
                bucket.add(make(exp.substring(i, j)));
                i = j;
            } else if (c != ' ') {
                operators.add(c);
            }
            i++;
        }
        for (int j = operators.size() - 1; j >= 0; j--) {
            if (operators.get(j) == '*') {
                bucket.set(j, combine(bucket.get(j), bucket.remove(j + 1), operators.remove(j)));
            }
        }
        if (bucket.isEmpty()) return new Exp();
        Exp ans = bucket.get(0);
        for (int j = 0; j < operators.size(); j++) {
            ans = combine(ans, bucket.get(j + 1), operators.get(j));
        }
        return ans;
    }

    private Exp make(String exp) {
        Exp ans = new Exp();
        List<String> list = new ArrayList<>();
        if (Character.isDigit(exp.charAt(0))) {
            ans.update(list, Integer.valueOf(exp));
        } else {
            list.add(exp);
            ans.update(list, 1);
        }
        return ans;
    }

    private Exp combine(Exp left, Exp right, char operator) {
        if (operator == '+') return left.add(right);
        if (operator == '-') return left.sub(right);
        if (operator == '*') return left.mul(right);
        return null;
    }

    class Exp {
        HashMap<List<String>, Integer> count;
        public Exp() {
            count = new HashMap<>();
        }

        public void update(List<String> key, int val) {
            this.count.put(key, this.count.getOrDefault(key, 0) + val);
        }

        public Exp add(Exp that) {
            Exp ans = new Exp();
            for (List<String> k : this.count.keySet()) {
                ans.update(k, this.count.get(k));
            }
            for (List<String> k : that.count.keySet()) {
                ans.update(k, that.count.get(k));
            }
            return ans;
        }
        public Exp sub(Exp that) {
            Exp ans = new Exp();
            for (List<String> k : this.count.keySet()) {
                ans.update(k, this.count.get(k));
            }
            for (List<String> k : that.count.keySet()) {
                ans.update(k, -that.count.get(k));
            }
            return ans;
        }
        public Exp mul(Exp that) {
            Exp ans = new Exp();
            for (List<String> k1 : this.count.keySet()) {
                for (List<String> k2 : that.count.keySet()) {
                    List<String> kNew = new ArrayList<>();
                    for (String x : k1) kNew.add(x);
                    for (String x : k2) kNew.add(x);
                    Collections.sort(kNew);
                    ans.update(kNew, this.count.get(k1) * that.count.get(k2));
                }
            }
            return ans;
        }

        public Exp evaluate(Map<String, Integer> evalMap) {
            Exp ans = new Exp();
            for (List<String> k : this.count.keySet()) {
                int c = this.count.get(k);
                List<String> free = new ArrayList<>();
                for (String token : k) {
                    if (evalMap.containsKey(token)) {
                        c *= evalMap.get(token);
                    } else {
                        free.add(token);
                    }
                }
                ans.update(free, c);
            }
            return ans;
        }

        public List<String> toList() {
            List<String> ans = new ArrayList<>();
            List<List<String>> keys = new ArrayList<>(this.count.keySet());
            Collections.sort(keys, new Comparator<List<String>>(){
                public int compare(List<String> l1, List<String> l2) {
                    if (l1.size() != l2.size()) {
                        return l2.size() - l1.size();
                    }
                    return compareList(l1, l2);
                }
            });
            for (List<String> key: keys) {
                int v = this.count.get(key);
                if (v == 0) continue;
                StringBuilder word = new StringBuilder();
                word.append("" + v);
                for (String token : key) {
                    word.append('*');
                    word.append(token);
                }
                ans.add(word.toString());
            }
            return ans;
        }

        private int compareList(List<String> l1, List<String> l2) {
            int i = 0;
            for (String x : l1) {
                String y = l2.get(i++);
                if (x.compareTo(y) != 0) return x.compareTo(y);
            }
            return 0;
        }
    }
}
