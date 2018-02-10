/**
 * Created by candy on 2/10/18.
 */
public class BestTimeToBuyAndSellStock4 {
    public int maxProfit(int k, int[] prices) {
        if (k == 0) return 0;
        if (k >= prices.length / 2) {
            int profit = 0;
            for (int i = 1; i < prices.length; i++) {
                if (prices[i] > prices[i - 1]) {
                    profit += prices[i] - prices[i - 1];
                }
            }
            return profit;
        }
        int n = prices.length;
        int[][] mustsell = new int[n][k + 1];
        int[][] globalbest = new int[n][k + 1];

        mustsell[0][0] = 0;
        globalbest[0][0] = 0;
        for (int i = 1; i <= k; i++) {
            mustsell[0][i] = 0;
            globalbest[0][i] = 0;
        }
        for (int i = 1; i < n; i++) {
            int diff = prices[i] - prices[i - 1];
            mustsell[i][0] = 0;
            for (int j = 1; j <= k; j++) {
                mustsell[i][j] = Math.max(mustsell[i - 1][j], globalbest[i - 1][j - 1]) + diff;
                globalbest[i][j] = Math.max(globalbest[i - 1][j], mustsell[i][j]);
            }
        }
        return globalbest[n - 1][k];
    }
}
