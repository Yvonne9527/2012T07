package cgfm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


//读取MYSQL的.ini配置文件
public class CM {
	
	private Map<String,String> map;
	
	public CM()
	{
		map=new HashMap<String,String>();
	}
	
	//读取配置文件
	public void ProcessFile(String fileaddress) throws IOException
	{
		File file=new File(fileaddress);
		BufferedReader reader=null;
		String tempString=null;
		String field=null;
		try {
			reader = new BufferedReader(new FileReader(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
		while((tempString=reader.readLine()) != null)
		{
			if(tempString.contains("[")&&tempString.contains("]")&&!tempString.contains("#")) field=tempString;
			if(tempString.contains("=")&&!tempString.contains("#")&&!tempString.contains("[")&&!tempString.contains("]"))
				{
				String[] aString=tempString.split("=");
				String[] bString=aString[0].split(" ");
				String keyString=field+bString[0];
				
				map.put(keyString, aString[1]);
				//System.out.println(keyString+"---"+aString[1]);
				}
			else continue;
		}
	}
	
	//按照键值对查找
	public void SearchKey(String key)
	{
		if(!map.containsKey(key)) System.out.println("配置文件中不含此配置");
		else System.out.println(key+"属性的配置为:"+map.get(key));
	}
	
	//查找配置规模
	public void getSize()
	{
		System.out.println("共含有"+map.size()+"条配置信息");
	}
	
	//查找所有信息
	public void list()
	{
		Iterator<String> iterator=map.keySet().iterator();
		String key=null;
		String value=null;
		while(iterator.hasNext())
		{
			key=iterator.next();
			value=map.get(key);
			System.out.println(key+"="+value);
		}
	}
	
	
}
