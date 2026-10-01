class Solution {
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> list = new ArrayList<>();
        for(int i = left; i<=right;i++){
            int num = i;
            boolean isRight = true;
            while(num>0){
                int digit = num%10;
                if(digit == 0||i%digit!=0){
                    isRight = false;
                    break;
                }
                num/=10;
            }
            if(isRight){
                list.add(i);
            }
        }
        return list;
    }
}