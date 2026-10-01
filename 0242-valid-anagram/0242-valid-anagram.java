class Solution {
    public boolean isAnagram(String s, String t) {
        char ch [] = s.toCharArray();
        char ch1 [] = t.toCharArray();
        Arrays.sort(ch);
        Arrays.sort(ch1);
        String str = new String(ch);
        String str1 = new String(ch1);
        if(str.equals(str1)){
            return true;
        }
        return false;
    }
}