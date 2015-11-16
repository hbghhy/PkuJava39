import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.regex.Pattern;

import org.omg.CORBA.UnionMember;


public class vocabulary {
	private static Pattern p=Pattern.compile("[^\\w]+");
	public static void main(String[] args) {
		String path1,path2;
		Scanner s=new Scanner(System.in);
		System.out.println("please input the first path:");
		path1=s.nextLine();
		System.out.println("please input the second path:");
		path2=s.nextLine();
		//System.out.println(path1);System.out.println(path2);
		s.close();
		HashSet<String> wordList1=turnToWordList(path1);
		HashSet<String> wordList2=turnToWordList(path2);
		printUnion(wordList1,wordList2);
		printCommon(wordList1,wordList2);
		System.out.println("the number of word in first file but not in second file conunt:");
		printOnly(wordList1, wordList2);
		System.out.println("the number of word in second file but not in first file conunt:");
		printOnly(wordList2, wordList1);
	}
	public static HashSet<String> turnToWordList(String path) {
		File f=new File(path);
		Scanner s=null;
		try {
			s=new Scanner(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		StringBuilder str=new StringBuilder();
		while(s.hasNext())
		{
			str.append(s.next()).append(" ");
		}
		//String[] words=s.next().split(" ");
		String[] words=p.split(str.toString());
		HashSet<String> ans=new HashSet<String>();
		for(String word:words)
		{
			if(word!=" ")
			{
				ans.add(word.toLowerCase());
			}
			
		}
		s.close();
		return ans;
	}
	public static void printUnion(HashSet<String> s1,HashSet<String> s2) {
		HashSet<String> ans=new HashSet<String>();
		for(String s:s1)
		{
			ans.add(s);
		}
		for(String s:s2)
		{
			ans.add(s);
		}
		System.out.print("all the word is:");
		int count=0;
		for(String s:ans)
		{
			if(count%100==0)
			{
				System.out.println();
			}
			System.out.print(s+" ");
			count++;
		}
		System.out.println();
		
	}
	public static void printCommon(HashSet<String> s1,HashSet<String> s2) {
		HashSet<String> ans=new HashSet<String>();
		for(String s:s1)
		{
			if(s2.contains(s))
			{
				ans.add(s);
			}
		}
		System.out.print("the common word is:");
		int count=0;
		for(String s:ans)
		{
			if(count%100==0)
			{
				System.out.println();
			}
			System.out.print(s+" ");
			count++;
		}
		System.out.println();
	}
	public static int count(HashSet<String> s1) {
		int ans=0;
		for(String s:s1)
		{
			ans++;
		}
		return ans;
	}
	public static void printOnly(HashSet<String> s1,HashSet<String> s2)
	{
		//print percentage of word belong to s1 but not in s2 
		int number=0;
		for(String s:s1)
		{
			if(!s2.contains(s))
			{
				number++;
			}
		}
		System.out.print(100*(float)number/count(s1));
		System.out.println("%");
	}
}
