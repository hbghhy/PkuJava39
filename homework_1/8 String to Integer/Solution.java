public class Solution {
    public int myAtoi(String str) {
        long ret=0;
		if(str.length()==0){
			return 0;
		}
		char[] a=str.toCharArray();
		int sign=1;
		int i=0;
		for(;i<a.length;i++){
			if(a[i]==' '){
				continue;
			}else{
				break;
			}
		}
		
		if(a[i]=='+'){
			sign=1;
			i++;
		}else if(a[i]=='-'){
			sign=-1;
			i++;
		}
		
		for(;i<a.length;i++){
			if(a[i]>='0'&&a[i]<='9'){
				ret = ret*10+(a[i]-'0');
				if(ret> Integer.MAX_VALUE){
					return sign==-1?Integer.MIN_VALUE:Integer.MAX_VALUE;
				}
			}else{
				break;
			}
		}
		ret = sign*ret;
		return (int)ret;
    }
}