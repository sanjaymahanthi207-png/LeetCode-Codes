import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        
        if (s == null || s.length() == 0 || words == null || words.length == 0) {
            return result;
        }

        int wordLen = words[0].length();
        int wordCount = words.length;
        int totalLen = wordLen * wordCount;
        int sLen = s.length();

        if (sLen < totalLen) {
            return result;
        }

        // Count frequencies of all words in the target array
        Map<String, Integer> wordFreq = new HashMap<>();
        for (String word : words) {
            wordFreq.put(word, wordFreq.getOrDefault(word, 0) + 1);
        }

        // Run sliding window starting at each offset from 0 to wordLen - 1
        for (int i = 0; i < wordLen; i++) {
            int left = i;
            int right = i;
            Map<String, Integer> currentFreq = new HashMap<>();
            int count = 0;

            while (right + wordLen <= sLen) {
                // Get word from current window tail
                String sub = s.substring(right, right + wordLen);
                right += wordLen;

                if (wordFreq.containsKey(sub)) {
                    currentFreq.put(sub, currentFreq.getOrDefault(sub, 0) + 1);
                    count++;

                    // If a word appears more times than required, shrink window from left
                    while (currentFreq.get(sub) > wordFreq.get(sub)) {
                        String leftWord = s.substring(left, left + wordLen);
                        currentFreq.put(leftWord, currentFreq.get(leftWord) - 1);
                        count--;
                        left += wordLen;
                    }

                    // If all words are matched in frequency and count, add starting index
                    if (count == wordCount) {
                        result.add(left);
                    }
                } else {
                    // Reset sliding window if word is not in target list
                    currentFreq.clear();
                    count = 0;
                    left = right;
                }
            }
        }

        return result;
    }
}