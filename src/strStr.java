/**
 * Created by candy on 2/8/18.
 */
public class strStr {
    public static void main(String[] args) {
        System.out.println(strStr("mississippi","issip"));
    }

    public static int strStr(String haystack, String needle) {
        if(needle.equals("")) return 0;
        if(haystack.equals("")) return -1;
        int[] next = new int[needle.length()];
        for (int i = 1; i < next.length; i++) {
            int j = next[i - 1];
            while (j > 0 && needle.charAt(i) != needle.charAt(j)) {
                j = next[j - 1];
            }
            j += needle.charAt(i) == needle.charAt(j) ? 1 : 0;
            next[i] = j;
        }

        int i, j;
        for (i = 0, j = 0; i < haystack.length() && j < needle.length(); i++) {
            while (j > 0 && haystack.charAt(i) != needle.charAt(j)) j = next[j - 1];
            if (haystack.charAt(i) == needle.charAt(j)) j++;
        }
        return j == needle.length() ? i - j : -1;
    }
}
