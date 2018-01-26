/**
 * Created by candy on 1/26/18.
 */
public class paintHouse {
    //O(nk) time complexity solution
    public int minCostII(int[][] costs) {
        if (costs.length == 0) return 0;
        //save the space by only storing the current and previous costs
        int[][] f = new int[2][costs[0].length];
        int old;
        int now = 0;
        for (int i = 0; i < costs.length; i++) {
            old = now;
            now = 1 - now;
            //loop through the different color to find the minimum two costs
            int min1 = Integer.MAX_VALUE;
            int min2 = Integer.MAX_VALUE;
            for (int j = 0; j < costs[0].length; j++) {
                if (f[old][j] < min1) {
                    min2 = min1;
                    min1 = f[old][j];
                } else if (f[old][j] < min2) {
                    min2 = f[old][j];
                }
            }
            for (int j = 0; j < costs[0].length; j++) {
                //if the previous house already use the color to gain the minimum cost and there is more than one color to select
                //choose the color with the second minimum cost
                if (f[old][j] == min1 && min2 != Integer.MAX_VALUE) {
                    f[now][j] = min2 + costs[i][j];
                } else {
                    f[now][j] = min1 + costs[i][j];
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < costs[0].length; i++) {
            min = Math.min(min, f[now][i]);
        }
        return min;
    }
}
