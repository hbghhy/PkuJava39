public class Solution {
    public String intToRoman(int num) {
        String[][] c ={
        		{"","I","II","III","IV","V","VI","VII","VIII","IX"},
        		{"","X","XX","XXX","XL","L","LX","LXX","LXXX","XC"},
        		{"","C","CC","CCC","CD","D","DC","DCC","DCCC","CM"},
        		{"","M","MM","MMM"}};

        int tmp=num;
        String ret="";
        if(tmp/1000!=0) ret+=c[3][tmp/1000];
        if((tmp%1000)/100!=0) ret+=c[2][(tmp%1000)/100];
        if((tmp%100)/10!=0) ret+=c[1][(tmp%100)/10];
        if(tmp%10!=0) ret+=c[0][tmp%10];
        
        return ret;
    }
}