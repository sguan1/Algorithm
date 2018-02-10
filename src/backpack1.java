/**
 * Created by candy on 2/10/18.
 */
public class backpack1 {

    public  int backPack(int m, int[] A) {
        boolean[][] f = new boolean[A.length + 1][m + 1];
        f[0][0] = true;
        for (int i = 1; i < f.length; i++) {
            for (int j = 0; j < f[0].length; j++) {
                f[i][j] = f[i - 1][j];
                if (j >= A[i - 1] && f[i - 1][j - A[i - 1]]) {
                    f[i][j] = true;
                }
            }
        }
        for (int i = m; i >= 0; i--) {
            if (f[A.length][i]) {
                return i;
            }
        }
        return 0;
    }

}
