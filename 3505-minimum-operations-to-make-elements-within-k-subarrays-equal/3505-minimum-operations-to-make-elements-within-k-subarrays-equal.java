class Solution {

    class Fenwick {
        int n;
        long[] tree;

        Fenwick(int n) {
            this.n = n;
            tree = new long[n + 1];
        }

        void update(int i, long val) {
            while (i <= n) {
                tree[i] += val;
                i += i & -i;
            }
        }

        long query(int i) {
            long sum = 0;

            while (i > 0) {
                sum += tree[i];
                i -= i & -i;
            }

            return sum;
        }

        int findKth(int k) {
            int index = 0;

            for (int bit = Integer.highestOneBit(n);
                 bit > 0; bit >>= 1) {

                int next = index + bit;

                if (next <= n && tree[next] < k) {
                    index = next;
                    k -= (int) tree[next];
                }
            }

            return index + 1;
        }
    }

    public long minOperations(int[] nums, int x, int k) {

        int n = nums.length;

        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        int m = 0;

        for (int v : sorted) {
            if (m == 0 || sorted[m - 1] != v) {
                sorted[m++] = v;
            }
        }

        int[] rank = new int[n];

        for (int i = 0; i < n; i++) {
            rank[i] = Arrays.binarySearch(
                sorted, 0, m, nums[i]
            ) + 1;
        }

        Fenwick count = new Fenwick(m);
        Fenwick sum = new Fenwick(m);

        long total = 0;

        long[] cost = new long[n - x + 1];

        for (int i = 0; i < x; i++) {
            count.update(rank[i], 1);
            sum.update(rank[i], nums[i]);
            total += nums[i];
        }

        for (int i = 0; i <= n - x; i++) {

            int mid = count.findKth((x + 1) / 2);

            long median = sorted[mid - 1];

            long leftCount = count.query(mid);
            long leftSum = sum.query(mid);

            long rightCount = x - leftCount;
            long rightSum = total - leftSum;

            cost[i] =
                median * leftCount - leftSum
                + rightSum - median * rightCount;

            if (i < n - x) {

                count.update(rank[i], -1);
                sum.update(rank[i], -nums[i]);

                count.update(rank[i + x], 1);
                sum.update(rank[i + x], nums[i + x]);

                total += (long) nums[i + x] - nums[i];
            }
        }

        long INF = Long.MAX_VALUE / 4;

        long[][] dp = new long[n + 1][k + 1];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], INF);
            dp[i][0] = 0;
        }

        for (int i = 1; i <= n; i++) {

            for (int j = 1; j <= k; j++) {

                dp[i][j] = dp[i - 1][j];

                if (i >= x && dp[i - x][j - 1] != INF) {

                    dp[i][j] = Math.min(
                        dp[i][j],
                        dp[i - x][j - 1] + cost[i - x]
                    );
                }
            }
        }

        return dp[n][k];
    }
}
