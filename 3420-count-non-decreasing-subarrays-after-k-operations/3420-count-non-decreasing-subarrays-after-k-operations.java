class Solution {

    public long countNonDecreasingSubarrays(int[] nums, int k) {

        int n = nums.length;
        int LOG = 17;

        long[] prefix = new long[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        int[] next = new int[n];
        int[] stack = new int[n];
        int top = -1;

        for (int i = n - 1; i >= 0; i--) {

            while (top >= 0 && nums[stack[top]] <= nums[i]) {
                top--;
            }

            next[i] = (top == -1) ? n : stack[top];
            stack[++top] = i;
        }

        int[][] up = new int[LOG][n];
        long[][] cost = new long[LOG][n];

        for (int i = 0; i < n; i++) {

            up[0][i] = next[i];

            int end = next[i];

            cost[0][i] =
                (long) nums[i] * (end - i)
                - (prefix[end] - prefix[i]);
        }

        for (int j = 1; j < LOG; j++) {

            for (int i = 0; i < n; i++) {

                int mid = up[j - 1][i];

                if (mid < n) {

                    up[j][i] = up[j - 1][mid];

                    cost[j][i] =
                        cost[j - 1][i] +
                        cost[j - 1][mid];

                } else {

                    up[j][i] = n;
                    cost[j][i] = cost[j - 1][i];
                }
            }
        }

        long answer = 0;

        for (int r = 0; r < n; r++) {

            int low = 0;
            int high = r;

            while (low <= high) {

                int mid = low + (high - low) / 2;

                long operations = getCost(
                    mid, r, nums, prefix, up, cost, n, LOG
                );

                if (operations <= k) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }

            answer += r - low + 1;
        }

        return answer;
    }

    private long getCost(
        int l,
        int r,
        int[] nums,
        long[] prefix,
        int[][] up,
        long[][] cost,
        int n,
        int LOG
    ) {

        long operations = 0;
        int pos = l;

        for (int j = LOG - 1; j >= 0; j--) {

            if (up[j][pos] <= r) {

                operations += cost[j][pos];
                pos = up[j][pos];
            }
        }

        operations +=
            (long) nums[pos] * (r - pos + 1)
            - (prefix[r + 1] - prefix[pos]);

        return operations;
    }
}