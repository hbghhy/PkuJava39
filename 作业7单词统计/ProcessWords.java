import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;


public class ProcessWords {
	public static void main(String[] args) {		
		//��ȡ�ļ�1����
		Map<String,Integer> map1 = readWordsFromFile("words1.txt");
		//��ȡ�ļ�2����
		Map<String,Integer> map2 = readWordsFromFile("words2.txt");
		
		//����ļ�1�в��ظ��ĵ���
		System.out.println("�ļ�1�еĲ��ظ����ʣ�");
		for(Map.Entry<String,Integer> entry:map1.entrySet()){
			if(entry.getValue().intValue()<2){
				System.out.println(entry.getKey());
			}
		}
		
		//����ļ�2�в��ظ��ĵ���
		System.out.println("�ļ�2�еĲ��ظ����ʣ�");
		for(Map.Entry<String,Integer> entry:map2.entrySet()){
			if(entry.getValue().intValue()<2){
				System.out.println(entry.getKey());
			}
		}
		
		//���ͬʱ�����������ļ��еĵ���
		System.out.println("ͬʱ�����������ļ��еĵ��ʣ�");
		for(Map.Entry<String,Integer> entry:map1.entrySet()){
			if(map2.containsKey(entry.getKey())){
				System.out.println(entry.getKey());
			}
		}
		
		//���е��ʵ�ͳ��
		int wordCount=0;	//ͳ���ļ��еĵ��ʸ���
		int dupWordCount=0;	//ͳ���ļ��е��ظ����ʸ���
		double dupPercent=0; //ͳ���ظ����ʸ������ļ�����ռ�İٷֱ�
		
		wordCount=map1.size();
		for(Map.Entry<String,Integer> entry:map1.entrySet()){
			if(entry.getValue().intValue()>1){
				dupWordCount++;
			}
		}
		
		//����3λС��
		java.text.DecimalFormat df=new java.text.DecimalFormat("##.###"); 
		dupPercent = dupWordCount*1.0/wordCount;
		System.out.println("�ļ�1���ʸ�����"+String.valueOf(wordCount)+",�ظ����ʸ�����"+String.valueOf(dupWordCount)+",�ظ�����ռ�����İٷֱȣ�"+String.valueOf(df.format(dupPercent))+"%");
		
		
		wordCount=map2.size();
		dupWordCount=0;
		for(Map.Entry<String,Integer> entry:map2.entrySet()){
			if(entry.getValue().intValue()>1){
				dupWordCount++;
			}
		}
		dupPercent = dupWordCount*1.0/wordCount;
		System.out.println("�ļ�2���ʸ�����"+String.valueOf(wordCount)+",�ظ����ʸ�����"+String.valueOf(dupWordCount)+",�ظ�����ռ�����İٷֱȣ�"+String.valueOf(df.format(dupPercent))+"%");
	}
	
	//��ȡ�ļ��еĵ��ʣ������õ�HashMap<String,Integer>
	public static Map<String,Integer> readWordsFromFile(String filePath){
		HashMap<String,Integer> map = new HashMap<String,Integer>();
		try{
			FileReader fr = new FileReader(filePath);
			BufferedReader br = new BufferedReader(fr);
			String s;
			while((s=br.readLine())!=null){
				String[] words = s.split(" ");
				for(int i=0;i<words.length;i++){
					String tmp = words[i].trim();
					if(tmp==""){
						break;
					}
					if(map.containsKey(tmp)){
						map.put(tmp, new Integer(map.get(tmp).intValue()+1));
					}else{
						map.put(tmp, new Integer(1));
					}
				}
			}
		}catch(Exception e){
			return null;
		}
		
		return map;
	}

}
