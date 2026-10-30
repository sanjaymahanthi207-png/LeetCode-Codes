
import java.util.*;

class Solution {
    int n;
    List<Integer>[] adj;
    int[] tin, tout, depth, parent;
    int[][] up;
    int timer;
    int[] bit;
    char[] s;

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        this.n = n;
        this.s = s.toCharArray();

        adj = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        tin = new int[n];
        tout = new int[n];
        depth = new int[n];
        parent = new int[n];

        int LOG = 17;
        up = new int[LOG][n];

        int[] stack = new int[2 * n];
        int[] state = new int[n];
        int top = 0;

        stack[top++] = 0;
        parent[0] = 0;

        timer = 0;

        while (top > 0) {
            int u = stack[top - 1];

            if (state[u] == 0) {
                tin[u] = ++timer;
                state[u] = 1;
            }

            if (state[u] <= adj[u].size()) {
                int v = adj[u].get(state[u] - 1);
                state[u]++;

                if (v != parent[u]) {
                    parent[v] = u;
                    depth[v] = depth[u] + 1;
                    stack[top++] = v;
                }
            } else {
                tout[u] = ++timer;
                top--;
            }
        }

        for (int i = 0; i < n; i++) {
            up[0][i] = parent[i];
        }

        for (int k = 1; k < LOG; k++) {
            for (int i = 0; i < n; i++) {
                up[k][i] = up[k - 1][up[k - 1][i]];
            }
        }

        bit = new int[2 * n + 2];

        for (int i = 0; i < n; i++) {
            int mask = 1 << (this.s[i] - 'a');

            updateBit(tin[i], mask);
            updateBit(tout[i], mask);
        }

        List<Boolean> result = new ArrayList<>();

        for (String q : queries) {
            String[] p = q.split(" ");

            if (p[0].equals("update")) {
                int u = Integer.parseInt(p[1]);
                char c = p[2].charAt(0);

                if (this.s[u] != c) {
                    int oldMask = 1 << (this.s[u] - 'a');
                    int newMask = 1 << (c - 'a');

                    int change = oldMask ^ newMask;

                    updateBit(tin[u], change);
                    updateBit(tout[u], change);

                    this.s[u] = c;
                }
            } else {
                int u = Integer.parseInt(p[1]);
                int v = Integer.parseInt(p[2]);

                int lca = findLCA(u, v);

                int mask = getPrefix(tin[u])
                         ^ getPrefix(tin[v])
                         ^ (1 << (this.s[lca] - 'a'));

                result.add((mask & (mask - 1)) == 0);
            }
        }

        return result;
    }

    void updateBit(int i, int val) {
        while (i < bit.length) {
            bit[i] ^= val;
            i += i & -i;
        }
    }

    int getPrefix(int i) {
        int ans = 0;

        while (i > 0) {
            ans ^= bit[i];
            i -= i & -i;
        }

        return ans;
    }

    int findLCA(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int diff = depth[u] - depth[v];

        for (int k = 0; k < up.length; k++) {
            if ((diff & (1 << k)) != 0) {
                u = up[k][u];
            }
        }

        if (u == v) {
            return u;
        }

        for (int k = up.length - 1; k >= 0; k--) {
            if (up[k][u] != up[k][v]) {
                u = up[k][u];
                v = up[k][v];
            }
        }

        return parent[u];
    }
}
