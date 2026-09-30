class Solution {
    public int divide(int dividend, int divisor) {
        // Handle overflow case: Integer.MIN_VALUE / -1 = Integer.MAX_VALUE + 1 (overflows 32-bit signed integer)
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        // Determine the sign of the result
        // True if both have the same sign, false if different
        boolean isPositive = (dividend > 0) == (divisor > 0);

        // Convert both numbers to negative values to avoid integer overflow when using Math.abs(Integer.MIN_VALUE)
        int absDividend = dividend > 0 ? -dividend : dividend;
        int absDivisor = divisor > 0 ? -divisor : divisor;

        int quotient = 0;

        // Since both numbers are negative, absDividend <= absDivisor means |dividend| >= |divisor|
        while (absDividend <= absDivisor) {
            int tempDivisor = absDivisor;
            int multiple = 1;

            // Exponentially double the divisor using left shifts until it exceeds dividend
            // Prevent underflow by checking tempDivisor >= Integer.MIN_VALUE >> 1
            while (tempDivisor >= Integer.MIN_VALUE >> 1 && absDividend <= (tempDivisor << 1)) {
                tempDivisor <<= 1;
                multiple <<= 1;
            }

            // Subtract the chunk from dividend and add multiple to quotient
            absDividend -= tempDivisor;
            quotient += multiple;
        }

        return isPositive ? quotient : -quotient;
    }
}