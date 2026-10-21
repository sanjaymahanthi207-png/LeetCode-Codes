
class Solution {
    public int minOperations(String s) {
        int n = s.length();
        int[] freq = new int[26];

        boolean sorted = true;

        for (int i = 0; i < n; i++) {
            freq[s.charAt(i) - 'a']++;

            if (i > 0 && s.charAt(i - 1) > s.charAt(i)) {
                sorted = false;
            }
        }

        if (sorted) return 0;
        if (n == 2) return -1;

        char min = 'a';
        char max = 'z';

        while (freq[min - 'a'] == 0) min++;
        while (freq[max - 'a'] == 0) max--;

        if (s.charAt(0) == min || s.charAt(n - 1) == max) {
            return 1;
        }

        if (s.charAt(0) == max && s.charAt(n - 1) == min) {
            if (freq[max - 'a'] == 1 && freq[min - 'a'] == 1) {
                return 3;
            }
        }

        return 2;
    }
}
