public class Solution {
    public int addDigits(int num) {
        if(num==0) return 0;
        int k=num%9;
        return k==0?9:k;
    }
}