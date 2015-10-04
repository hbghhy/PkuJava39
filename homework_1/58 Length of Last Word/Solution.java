public class Solution {
    public int lengthOfLastWord(String s) {
        String a=s.trim();
		if(a==""){
			return 0;
		}
		char[] b = a.toCharArray();
		int wStart=0;
		for(int i=b.length-1;i>=0;i--){
			if(b[i]==' '){
				wStart=i+1;
				break;
			}
		}
		return b.length-wStart;
    }
}