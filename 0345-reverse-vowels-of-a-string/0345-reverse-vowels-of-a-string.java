class Solution {
    public boolean isVowel(char ch){
        return ch == 'a' || ch == 'e' || ch == 'i' ||
               ch == 'o' || ch == 'u' ||
               ch == 'A' || ch == 'E' || ch == 'I' ||
               ch == 'O' || ch == 'U';
    }
    public String reverseVowels(String s) {
        char ch[] = s.toCharArray();
        int l = 0;
        int r = ch.length-1;
        while(l<r){
            while(l<r&&!isVowel(ch[l])){
                l++;
            }
            while(l<r&&!isVowel(ch[r])){
                r--;
            }
            char temp = ch[l];
            ch[l] = ch[r];
            ch[r] = temp;
            l++;
            r--;
        }
        return new String(ch);
    }
}