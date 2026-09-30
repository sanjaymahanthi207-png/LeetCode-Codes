class Solution {
    public int removeElement(int[] nums, int val) {
        // Pointer 'k' tracks the index for elements that are NOT equal to val
        int k = 0;

        // Iterate through each element in the array
        for (int i = 0; i < nums.length; i++) {
            // If the element is not equal to val, copy it to index k
            if (nums[i] != val) {
                nums[k] = nums[i];
                k++; // Move writer pointer forward
            }
        }

        // Return the count of elements not equal to val
        return k;
    }
}