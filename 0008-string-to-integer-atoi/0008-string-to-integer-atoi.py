class Solution(object):
    def myAtoi(self, s):
        """
        :type s: str
        :rtype: int
        """
        # Step 1: Trim whitespace
        s = s.strip()
        if len(s) == 0:
            return 0

        # Step 2: Handle sign
        sign = 1
        if s[0] == '-':
            sign = -1
            s = s[1:]
        elif s[0] == '+':
            s = s[1:]

        # Step 3: Convert digits
        ret = 0
        i = 0
        while i < len(s) and s[i].isdigit():
            ret = ret * 10 + (ord(s[i]) - ord('0'))
            i += 1

        # Step 4: Apply sign
        ret *= sign

        # Step 5: Clamp to 32-bit signed integer range
        INT_MIN, INT_MAX = -2**31, 2**31 - 1
        if ret < INT_MIN:
            return INT_MIN
        if ret > INT_MAX:
            return INT_MAX
        return ret


# -------------------------------
# Example test cases
# -------------------------------
if __name__ == "__main__":
    s = Solution()
    print(s.myAtoi("42"))            # Expected: 42
    print(s.myAtoi("   -42"))        # Expected: -42
    print(s.myAtoi("4193 with words")) # Expected: 4193
    print(s.myAtoi("words and 987")) # Expected: 0
    print(s.myAtoi("-91283472332"))  # Expected: -2147483648 (clamped)