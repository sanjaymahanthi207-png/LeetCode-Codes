class Solution{
    public long[] resultArray(int[] nums,int k){
        long[] ans=new long[k];
        long[] dp=new long[k];
        for(int n:nums){
            long[] next=new long[k];
            int v=n%k;
            next[v]++;
            for(int r=0;r<k;r++){
                next[(r*v)%k]+=dp[r];
            }
            for(int r=0;r<k;r++){
                ans[r]+=next[r];
            }
            dp=next;
        }
        return ans;
    }
}