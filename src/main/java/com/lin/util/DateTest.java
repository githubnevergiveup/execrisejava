package com.lin.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;




import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
public class DateTest {

	
	public static void main(String []args) {
		
		Date date=new Date();
		
		SimpleDateFormat sdf=new SimpleDateFormat("MM月dd日HH时");
		
		String dateToStr=sdf.format(date);
		
		System.out.println(dateToStr);
		
		String str="达到**G会降速M";
		
		String newgnty=str.replace("G", "寄");
		
		newgnty=newgnty.replace("M", "兆");		
		
		
		System.out.println(newgnty.contains("会降速"));
		
		
		/*
		 * try { String input = FileUtils.readFileToString(new File("E:/flux.txt"),
		 * "UTF-8");
		 * 
		 * JSONObject jsonObject = net.sf.json.JSONObject.fromObject(input);
		 * 
		 * JSONObject josn=(JSONObject) jsonObject.get("object");
		 * 
		 * JSONObject josnResult=(JSONObject) josn.get("result");
		 * 
		 * JSONArray jsonArray=josnResult.getJSONArray("busiInfos");
		 * 
		 * 
		 * List<HashMap<String, Object>>
		 * listMap=StringToListMapUtils.getJsonListByString(jsonArray.toString());
		 * 
		 * System.out.println(listMap.toString());
		 * 
		 * } catch (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
	
		
		/*
		 * StringBuffer strbuffer=new StringBuffer();
		 * 
		 * //strbuffer.append("sssasdasdad");
		 * 
		 * strbuffer.insert(0,"");
		 * 
		 * System.out.println(strbuffer.toString()+"555555555555");
		 */
		
		
		HashMap<String,Boolean>booleanHashMap=new HashMap<String,Boolean>();
		
		Boolean bo=booleanHashMap.get("1");
		
		/*
		 * System.out.println(bo); if(bo) { System.out.println("eeeeeeeee"); }else {
		 * System.out.println("qqqqqqqqqqqqqq"); }
		 */
		
		
		
	}
	
	
	
	
	
	
	
}
