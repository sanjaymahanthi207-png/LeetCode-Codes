class Solution(object):
    def twoSum(self, nums, target):
        num_map = {}  # Dictionary to store numbers and their indices
        for i, num in enumerate(nums):
            complement = target - num  # The number we need to reach the target
            if complement in num_map:  # Check if we've already seen the complement
                return [num_map[complement], i]  # Return indices of complement and current number
            num_map[num] = i 