class Solution {
    public int longestPalindrome(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for(char ch : s.toCharArray()){
            map.put(ch,map.getOrDefault(ch,0)+1);
        }
        int len = 0;
        boolean isOdd = false;
        for(int n : map.values()){
            len += (n/2)*2;
            if(n%2==1) isOdd = true;
        }
        if(isOdd) len++;
        return len;
    }
}