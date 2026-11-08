class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length;
        int m = tasks.length;

        PriorityQueue<int[]> free = new PriorityQueue<>(
            (a, b) -> {
                if (a[0] != b[0]) {
                    return a[0] - b[0];
                }
                return a[1] - b[1];
            }
        );

        PriorityQueue<long[]> busy = new PriorityQueue<>(
            (a, b) -> Long.compare(a[0], b[0])
        );

        for (int i = 0; i < n; i++) {
            free.offer(new int[]{servers[i], i});
        }

        int[] ans = new int[m];
        long currentTime = 0;

        for (int i = 0; i < m; i++) {

            currentTime = Math.max(currentTime, i);

            while (!busy.isEmpty() &&
                   busy.peek()[0] <= currentTime) {

                long[] s = busy.poll();
                int index = (int) s[1];

                free.offer(new int[]{servers[index], index});
            }

            if (free.isEmpty()) {
                currentTime = busy.peek()[0];

                while (!busy.isEmpty() &&
                       busy.peek()[0] <= currentTime) {

                    long[] s = busy.poll();
                    int index = (int) s[1];

                    free.offer(new int[]{servers[index], index});
                }
            }

            int[] server = free.poll();
            int index = server[1];

            ans[i] = index;

            busy.offer(new long[]{
                currentTime + tasks[i],
                index
            });
        }

        return ans;
    }
}