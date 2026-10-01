import java.util.*;

class Solution {
    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();

        int[][] a = new int[n][4];

        for (int i = 0; i < n; i++) {
            a[i][0] = intervals.get(i).get(0);
            a[i][1] = intervals.get(i).get(1);
            a[i][2] = intervals.get(i).get(2);
            a[i][3] = i;
        }

        // Sort by start
        Arrays.sort(a, (x, y) -> x[0] - y[0]);

        // next[i] = first interval whose start > a[i].end
        int[] next = new int[n];

        for (int i = 0; i < n; i++) {
            int l = i + 1, r = n;

            while (l < r) {
                int m = (l + r) / 2;

                if (a[m][0] > a[i][1])
                    r = m;
                else
                    l = m + 1;
            }

            next[i] = l;
        }

        long[][] dp = new long[n + 1][5];
        int[][][] ans = new int[n + 1][5][];

        for (int i = n - 1; i >= 0; i--) {
            for (int k = 1; k <= 4; k++) {

                // Don't take current interval
                dp[i][k] = dp[i + 1][k];
                ans[i][k] = ans[i + 1][k];

                // Take current interval
                long score = a[i][2] + dp[next[i]][k - 1];

                int[] take = new int[1 +
                        (ans[next[i]][k - 1] == null ? 0 :
                        ans[next[i]][k - 1].length)];

                take[0] = a[i][3];

                if (ans[next[i]][k - 1] != null)
                    System.arraycopy(ans[next[i]][k - 1], 0,
                            take, 1, ans[next[i]][k - 1].length);

                Arrays.sort(take);

                if (score > dp[i][k] ||
                    (score == dp[i][k] && smaller(take, ans[i][k]))) {

                    dp[i][k] = score;
                    ans[i][k] = take;
                }
            }
        }

        return ans[0][4] == null ? new int[0] : ans[0][4];
    }

    private boolean smaller(int[] a, int[] b) {
        if (b == null)
            return true;

        for (int i = 0; i < Math.min(a.length, b.length); i++) {
            if (a[i] != b[i])
                return a[i] < b[i];
        }

        return a.length < b.length;
    }
}