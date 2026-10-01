import java.util.*;

class Solution {
    String s;
    int i;

    public List<String> braceExpansionII(String expression) {
        s = expression;
        i = 0;
        Set<String> set = parse();
        List<String> ans = new ArrayList<>(set);
        Collections.sort(ans);
        return ans;
    }

    Set<String> parse() {
        Set<String> result = new HashSet<>();
        result.add("");

        while (i < s.length() && s.charAt(i) != '}') {
            Set<String> cur = new HashSet<>();

            if (s.charAt(i) == '{') {
                i++;
                cur = parse();
                i++;
            } else {
                cur.add(String.valueOf(s.charAt(i)));
                i++;
            }

            result = multiply(result, cur);

            if (i < s.length() && s.charAt(i) == ',') {
                i++;
                result.addAll(parse());
                break;
            }
        }

        return result;
    }

    Set<String> multiply(Set<String> a, Set<String> b) {
        Set<String> result = new HashSet<>();

        for (String x : a) {
            for (String y : b) {
                result.add(x + y);
            }
        }

        return result;
    }
}