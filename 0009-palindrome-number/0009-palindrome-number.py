class Solution:
    def isPalindrome(self, x):
        # Step 1: Negative numbers are not palindromes
        if x < 0:
            return False

        # Step 2: Reverse the number mathematically
        original = x
        reverse = 0
        while x > 0:
            reverse = reverse * 10 + (x % 10)
            x //= 10

        # Step 3: Compare reversed number with original
        return reverse == original


# -------------------------------
# Example test cases
# -------------------------------
if __name__ == "__main__":
    s = Solution()
    print(s.isPalindrome(121))    # True
    print(s.isPalindrome(-121))   # False
    print(s.isPalindrome(10))     # False
    print(s.isPalindrome(0))      # True