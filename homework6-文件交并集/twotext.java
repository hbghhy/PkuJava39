import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;


public class twotext {

	/**
	 * @param args
	 */
	public static void main(String[] args)throws Exception{
		// TODO Auto-generated method stub
		int i,j=1;
		BufferedReader br1=new BufferedReader(new FileReader(new File("C:\\Users\\min\\Desktop\\1.txt")));
		BufferedReader br2=new BufferedReader(new FileReader(new File("C:\\Users\\min\\Desktop\\2.txt")));
		String str1=br1.readLine();
		String str2=br2.readLine();
		String []a=str1.trim().split(" ");
		String []b=str2.trim().split(" ");
		Hashtable <String,Integer>ht1=new Hashtable <String,Integer>();
		Hashtable <String,Integer>ht2=new Hashtable <String,Integer>();
		String strjiao="";
		String strbing="";
		System.out.println("����3��");
		for(i=0;i<a.length;i++)
		{
			if(!ht1.containsKey(a[i]))
			{
			
				 ht1.put(a[i], 1);
			}
			else
			{
				j=ht1.get(a[i]);
				ht1.remove(a[i]);
				ht1.put(a[i], j+1);
			}
		}
		System.out.println("�ļ�1��������ĸ���"+ht1.size());
		for(i=0;i<b.length;i++)
		{
			if(!ht2.containsKey(b[i]))
			{
				ht2.put(b[i], 1);
				strbing+=b[i]+" ";
			}
			else
			{
				j=ht2.get(b[i]);
				ht2.remove(b[i]);
				ht2.put(b[i], j+1);
			}
		}
		System.out.println("�ļ�2��������ĸ���"+ht2.size());
		Iterator itkey=ht1.keySet().iterator();
		String s="";
		double p=0.0;
		while(itkey.hasNext())
		{
			
			s=itkey.next().toString();
			if(ht2.containsKey(s))//jiao,����
			{
				strjiao+=s+" ";
			}
			else
			{
				strbing+=s+" ";
				p=(double)ht1.get(s)/a.length;
				System.out.println("�����ļ�1�����������ļ�2�ĵ���"+s+"ռ�ļ�1�İٷֱ�:"+p);
			}
		}
		Iterator itkey2=ht2.keySet().iterator();
		while(itkey2.hasNext())
		{
			s=itkey2.next().toString();
			if(!ht1.containsKey(s))//�����ļ�2�����������ļ�1
			{
				p=(double)ht2.get(s)/b.length;
				System.out.println("�����ļ�2�����������ļ�1�ĵ���"+s+"ռ�ļ�2�İٷֱ�:"+p);
			}
		}
		System.out.println("����1��");
		System.out.println("������"+strbing);
		System.out.println("����2��");
		System.out.println("������"+strjiao);
				
	}

}
