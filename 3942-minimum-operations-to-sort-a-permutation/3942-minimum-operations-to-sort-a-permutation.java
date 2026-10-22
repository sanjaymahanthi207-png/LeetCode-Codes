
class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int pos = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                pos = i;
                break;
            }
        }

        int ans = Integer.MAX_VALUE;

        boolean normal = true;
        for (int i = 0; i < n; i++) {
            if (nums[(pos + i) % n] != i) {
                normal = false;
                break;
            }
        }

        if (normal) {
            ans = Math.min(pos, n - pos + 2);
        }

        int shift = n - 1 - pos;
        boolean reversed = true;

        for (int i = 0; i < n; i++) {
            int idx = (shift + i) % n;
            if (nums[n - 1 - idx] != i) {
                reversed = false;
                break;
            }
        }

        if (reversed) {
            ans = Math.min(ans, Math.min(shift, n - shift) + 1);
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
