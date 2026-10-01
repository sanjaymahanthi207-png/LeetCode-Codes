class Solution {
    public String reverseParentheses(String s) {
        Stack<String> st = new Stack<>();
        String cur = "";
        for(char ch : s.toCharArray()) {
            if(ch == '(') {
                st.push(cur);
                cur = "";
            } else if(ch == ')') {
                cur = new StringBuilder(cur).reverse().toString();
                cur = st.pop() + cur;
            } else {
                cur += ch;
            }
        }
        return cur;
    }
}