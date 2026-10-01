class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int sum = 0;
        int max = 0;
        for(int i =0 ; i<nums.length; i++){
            sum+=nums[i];
            max = Math.max(max, sum);
            if(nums[i]==0){
                sum=0;
            }
        }
        return max;
    }
}