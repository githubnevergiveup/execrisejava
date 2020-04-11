package com.lin.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class StringToListMapUtils {

	
	
	//test
	
	
	//123qeqe
	public static List<HashMap<String, Object>> getJsonListByString(String jsonFile) 
	{
		List<HashMap<String, Object>> rsList = new ArrayList<HashMap<String, Object>>();
JSONArray arry = JSONArray.fromObject(jsonFile);
 for (int i = 0; i < arry.size(); i++)
{
JSONObject jsonObject = arry.getJSONObject(i);
HashMap<String, Object> map = new HashMap<String, Object>();
for (Iterator<?> iter = jsonObject.keys(); iter.hasNext();)
{
String key = (String) iter.next();
String value = jsonObject.get(key).toString();
map.put(key, value);
}
 rsList.add(map);
}
   return rsList;
   
	}
	
	
	
	
}
