class Solution {
    public char repeatedCharacter(String s) {
        HashMap<Character , Integer> map = new HashMap<>();
        for(char ch : s.toCharArray()){
            if(map.containsKey(ch)){
                return ch;
            }
            else{
                map.put(ch,map.getOrDefault(ch,0)+1);
            }
        }
        return 0;
    }
}