public class Solution {
    public String addBinary(String a, String b) {
        if(a.length()<b.length()){
        	return addBinary(b,a);
        }else{
        	String ret="";
        	int len = a.length()-b.length();
    		String tmp="";
    		for(int i=0;i<len;i++){
    			tmp+="0";
    		}
    		b = tmp+b;
    		
    		char[] arrA=a.toCharArray();
    		char[] arrB=b.toCharArray();
    		
    		int c=0;
    		int cur=0;
    		
    		for(int i=arrA.length-1;i>=0;i--){
    			cur=(arrA[i]-'0')+(arrB[i]-'0')+c;
    			if(cur==0){
    				arrA[i]='0';
    				c=0;
    			}else if(cur==1){
    				arrA[i]='1';
    				c=0;
    			}else if(cur==2){
    				arrA[i]='0';
    				c=1;
    			}else if(cur==3){
    				arrA[i]='1';
    				c=1;
    			}
    		}
    		if(c==1){
    			ret = "1"+new String(arrA);
    		}else{
    			ret = new String(arrA);
    		}
    		return ret;
        }
    }
}