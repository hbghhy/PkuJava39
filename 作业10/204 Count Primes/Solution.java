public class Solution {
    public int countPrimes(int n) {
        boolean[] isPrimeArr=new boolean[n];
        for(int i=2;i<n;i++){
            isPrimeArr[i]=true;
        }
        
        for(int i=2;i*i<n;i++){
            if(!isPrimeArr[i]){
                continue;
            }
            
            for(int j=i*i;j<n;j+=i){
                isPrimeArr[j]=false;
            }
        }
        
        int c=0;
        for(int k=2;k<n;k++){
            if(isPrimeArr[k]){
                c++;
            }
        }
        
        return c;
    }
}