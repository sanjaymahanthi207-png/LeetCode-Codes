class Solution {
    List<String> result = new ArrayList<>();
    boolean[] isVisited;

    private void solve(int n, StringBuilder permutation) {
        if (permutation.length() == n) {
            result.add(permutation.toString());
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (!isVisited[i]) {
                isVisited[i] = true;
                permutation.append(i);
                solve(n, permutation);
                isVisited[i] = false;
                permutation.deleteCharAt(permutation.length() - 1);
            }
        }
    }

    public String getPermutation(int n, int k) {
        isVisited = new boolean[n + 1];
        solve(n, new StringBuilder());
        return result.get(k - 1);
    }
} 