
import java.util.Arrays;

class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        int ans = 0;

        int[] prefix = new int[n];
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1];
            if (nums[i - 1] > nums[i]) {
                prefix[i]++;
            }
        }

        for (int k = 1; k <= n; k++) {
            if (n % k != 0) continue;

            boolean valid = true;

            for (int l = 0; l < n; l += k) {
                int r = l + k;

                int[] a = Arrays.copyOfRange(nums, l, r);
                int[] b = Arrays.copyOfRange(sorted, l, r);

                Arrays.sort(a);

                if (!Arrays.equals(a, b)) {
                    valid = false;
                    break;
                }
                int descents = prefix[r - 1] - prefix[l];

                if (descents > 1 ||
                    (descents == 1 && nums[r - 1] > nums[l])) {
                    valid = false;
                    break;
                }
            }

            if (valid) ans += k;
        }

        return ans;
    }
}
