package com.lin.util;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.alibaba.fastjson.JSON;
import com.mysql.jdbc.StringUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ListMapBusInfo {

	
	public static void main(String[] args) {
		
		net.sf.json.JSONObject jsonObject=new JSONObject();
		
		List<HashMap<String, Object>> listMap=new ArrayList<HashMap<String, Object>>();
		
		try {
			String input = FileUtils.readFileToString(new File("E:/flux.txt"), "UTF-8");    
			
			    jsonObject = net.sf.json.JSONObject.fromObject(input);
			    
			    JSONObject josn=(JSONObject) jsonObject.get("object");
			    
			    JSONObject josnResult=(JSONObject) josn.get("result");
			    
			    JSONArray jsonArray=josnResult.getJSONArray("busiInfos");
			    
			    
			   listMap=StringToListMapUtils.getJsonListByString(jsonArray.toString());
			   
			   
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		    
		
		HashMap<String,Boolean> flagMap=new HashMap<String,Boolean>();
		
		
		HashMap<String,Object> returnMap=new HashMap<String,Object>();
		
		returnMap.put("resultFlag","0");
		
		returnMap.put("sendFlag", "0");
		
		
		
		HashMap<String,String> hashMap=new HashMap<String,String>();
		
		//查询出来的多个结果
		for(HashMap<String,Object> map:listMap) {
			
			//判断取值 获得类型
			String type=map.get("type").toString();			
			
			switch(type) {
			
			
			//流量
			case "GPRS":
				
				boolean flag=false;
				
				//用户类型为7则为不限制流量
				String servType=map.get("servType").toString();
				if("7".equals(servType)||"0".equals(servType)) {
					flag=true;
				}
				
				StringBuilder stringBuilder=new StringBuilder();
				
				String bizType=map.get("bizType").toString();
				
				flagMap.put(bizType, flag);
				
				//流量里面还要分类
				//国内通用流量
				if("GNTY".equals(bizType)) {
					
					stringBuilder.append("国内通用流量");		
					
				//省内通用流量
				}else if("SNTY".equals(bizType)) {
					stringBuilder.append("省内通用流量");		
					
				//不限流量通用流量
				}else if("BZLTY".equals(bizType)) {
					stringBuilder.append("不限流量通用流量");		
					
				//不限流量其他流量
				}else if("BZLOTH".equals(bizType)) {
					
					stringBuilder.append("不限流量其他流量");		
					
					
				}else {
					stringBuilder.append("其他流量");	
					
				}
				
				
				String str=returnStr(map.get("privSet").toString(),flag,map.get("unit").toString(),map.get("leftNum").toString(),map.get("sumNum").toString());
				stringBuilder.append(str);
				
				
				hashMap.put(bizType,stringBuilder.toString());
				
				break;
				//短信
			case "SMS":
				
				StringBuilder stringBuilderToSms=new StringBuilder();
				
				stringBuilderToSms.append("共");
				
				String sumNum=map.get("sumNum").toString();
				
				stringBuilderToSms.append(sumNum+"条");
			
				Double sumNumToInt=new Double(sumNum);
				
				String leftNum=map.get("leftNum").toString();
				
				Double leftNumToInt=new Double(leftNum);
				
				Double useNum=sumNumToInt-leftNumToInt;
				
				stringBuilderToSms.append("已用"+useNum+"条");
				
				stringBuilderToSms.append("剩余"+leftNum+"条。");
				
				
				hashMap.put("SMS",stringBuilderToSms.toString());
			break;
			//通话
			case "GSM":
				
				StringBuilder stringBuilderToGSM=new StringBuilder();
				
				String sumNumTel=map.get("sumNum").toString();
				
				String leftNumTel=map.get("leftNum").toString();
				
				stringBuilderToGSM.append("已用"+sumNumTel+"分钟,");
				
				stringBuilderToGSM.append("剩余"+leftNumTel+"分钟。");
				
				
				hashMap.put("GSM",stringBuilderToGSM.toString());
			break;
				
			case "WLAN":
			
				StringBuilder stringBuilderToWlan=new StringBuilder();
				
				stringBuilderToWlan.append("共");
				
				String sumNumWlan=map.get("sumNum").toString();
				
				Double sumNumWlanToInt=new Double(sumNumWlan);
				
				String leftNumWlan=map.get("leftNum").toString();
				
				Double sumNumWlanToIntWlan=new Double(leftNumWlan);
				
				stringBuilderToWlan.append(sumNumWlanToInt+"分钟(G/小时),已用");
				
				stringBuilderToWlan.append(sumNumWlanToInt-sumNumWlanToIntWlan);
				
				stringBuilderToWlan.append(", 剩余");
				
				stringBuilderToWlan.append(sumNumWlanToIntWlan+"分钟(G/小时)。");
				
				hashMap.put("WLAN",stringBuilderToWlan.toString());
				
			break;
			
			default :
				break;
			
			}
			
			
		}
		
		//语音 拼接字符串开始
		
		
		Date date=new Date();
		
		SimpleDateFormat sdf=new SimpleDateFormat("MM月dd日HH时");
		
		String dateToStr=sdf.format(date);
		
		StringBuilder strBuilder=new StringBuilder();
		
		
		
		
		
		String[] strType= {"GNTY","OTHER","SNTY","BZLTY","BZLOTH"};
		
		for(String str:strType) {
			Boolean flag=flagMap.get(str);
			
			if(flag!=null) {
				
			
			if(flag) {
				
				String bzloth=hashMap.get(str);
				
				if(org.apache.commons.lang.StringUtils.isNotEmpty(bzloth)) {
					String newbzloth=bzloth.replace("G", "寄");
					
					 newbzloth=newbzloth.replace("M", "兆");
					
					 strBuilder.insert(0,newbzloth);
				}
				
				 
			}else {
				String bzloth=hashMap.get(str);
				
				if(org.apache.commons.lang.StringUtils.isNotEmpty(bzloth)) {
					String newbzloth=bzloth.replace("G", "寄");
					
					 newbzloth=newbzloth.replace("M", "兆");
					
					 strBuilder.append(newbzloth);
				}
				
				
				
			}
			
			}
			
			
		}
		
		
		strBuilder.insert(0,dateToStr+"您");
		
		strBuilder.insert(0,"尊敬的客户,您好! 截止");
		
		
	//	strBuilder.append(newgnty+newother+newsnty+newbzlty+newbzloth+"。");
		
		if(hashMap.get("WLAN")!=null) {
			strBuilder.append("WLAN:");
		}
		
		strBuilder.append(hashMap.get("WLAN")==null?"":hashMap.get("WLAN"));
		
		if(hashMap.get("GSM")!=null) {
			strBuilder.append("通话:");
		}
		
		strBuilder.append(hashMap.get("GSM")==null?"":hashMap.get("GSM"));
		
		if(hashMap.get("SMS")!=null) {
			strBuilder.append("短信:");
		}
		
		strBuilder.append(hashMap.get("SMS")==null?"":hashMap.get("SMS"));
		
		
		returnMap.put("voiceContent", strBuilder.toString());
		
		
		StringBuilder strBuilderToMsg=new StringBuilder();
		
		strBuilderToMsg.append("【套餐余量信息】尊敬的客户，您好！以下是截至");
		
		strBuilderToMsg.append(dateToStr+"套餐具体使用情况:");
		
		String wlanStr=returnStrL(hashMap,flagMap);
		
		//流量套餐
		strBuilderToMsg.append(wlanStr);
		
		
		if(hashMap.get("GSM")!=null) {
			strBuilderToMsg.append("【通话时长】:");
		}
		
		strBuilderToMsg.append(hashMap.get("GSM")==null?"":hashMap.get("GSM"));
		
		if(hashMap.get("SMS")!=null) {
			strBuilderToMsg.append("【短信包月】:");
		}
		
		strBuilderToMsg.append(hashMap.get("SMS")==null?"":hashMap.get("SMS"));
		
		strBuilderToMsg.append("套餐余量详情请点击安全链接：https://wx.online-cmcc.cn/website/personalHome/new/index 【中国移动】");
	
		returnMap.put("smsMessage",strBuilderToMsg.toString());
		
		
		System.out.println(JSON.toJSONString(returnMap));
	}
	
	
	/**
	 * 
	 * @param flag 是否无限流量 用户类型为7 则无限
	 * @param unit 流量单位
	 * @param residueFlux 剩余流量
	 * @param fluxNum 总流量
	 * @return
	 */
	public static String returnStr(String privSet,boolean flag,String unit,String residueFlux,String fluxNum) {
		
		StringBuilder stringBuilder=new StringBuilder();
		
		//总流量
		Double fluxNumDouble=new Double(fluxNum);
				
		//剩余流量
		Double residueFluxDouble=new Double(residueFlux);
		
		//说明不限流量
		if(flag) {
			stringBuilder.append(privSet);
			
			stringBuilder.append(",已用");
			
			HashMap<String,Object> hashMapUseFlux=calculateNum(fluxNumDouble-residueFluxDouble,unit);
			//后续如果是voice 要把G M 替换为寄 兆
			stringBuilder.append(hashMapUseFlux.get("flux").toString());
			stringBuilder.append(hashMapUseFlux.get("unit").toString());
			stringBuilder.append("达到");
			HashMap<String,Object> hashMapNum=calculateNum(new Double(fluxNum),unit);
			stringBuilder.append(hashMapNum.get("flux").toString());
			stringBuilder.append(hashMapNum.get("unit").toString());
			stringBuilder.append("会降速,降速后可以继续使用,不额外收费;");
			
		}else {
			//限制流量
			stringBuilder.append("共");
			HashMap<String,Object> hashMapUseFlux=calculateNum(new Double(fluxNum),unit);
			//后续如果是voice 要把G M 替换为寄 兆
			stringBuilder.append(hashMapUseFlux.get("flux").toString());
			stringBuilder.append(hashMapUseFlux.get("unit").toString());
			
			stringBuilder.append("已用");
			HashMap<String,Object> hashMapUseFluxTotal=calculateNum(fluxNumDouble-residueFluxDouble,unit);
			//后续如果是voice 要把G M 替换为寄 兆
			stringBuilder.append(hashMapUseFluxTotal.get("flux").toString());
			stringBuilder.append(hashMapUseFluxTotal.get("unit").toString());
			
			stringBuilder.append("剩余");
			HashMap<String,Object> hashMapresidueTotal=calculateNum(residueFluxDouble,unit);
			//后续如果是voice 要把G M 替换为寄 兆
			stringBuilder.append(hashMapresidueTotal.get("flux").toString());
			stringBuilder.append(hashMapresidueTotal.get("unit").toString());
			stringBuilder.append(";");
		}
		
		
		
		return stringBuilder.toString();
	}
	
	//流量转换单位
	public static HashMap<String,Object> calculateNum(Double num,String unit) {
		
		HashMap<String,Object> hashmap=new HashMap<String,Object>();
		
		Double useFluxDouble=new Double(num);
		
		if(useFluxDouble==0.0) {
			
			hashmap.put("unit",unit);
			hashmap.put("flux",useFluxDouble);
			
		}else {
			
			if("KB".equals(unit)) {
				
				Double doubleUseFluxInt= (useFluxDouble/1024);
				//转换
				BigDecimal   b   =   new   BigDecimal(doubleUseFluxInt);  
				//保留两位
				double   f1   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();  
				
				
				if(f1>0.0&&f1<1024.0) {
					//单位则为MB
					
					hashmap.put("unit","M");
					hashmap.put("flux",f1);
				}else {
					
					if(f1<0.0) {
						hashmap.put("unit","KB");
						hashmap.put("flux",useFluxDouble);
						
					}else {
						//单位为GB
						Double doubleUseFluxToGB= (f1/1024);
						
						hashmap.put("unit","G");
						hashmap.put("flux",doubleUseFluxToGB);
					}
					
					
				}
				
			}else if("M".equals(unit)) {
				
				
				Double doubleUseFluxInt= (useFluxDouble/1024);
				//转换
				BigDecimal   b   =   new   BigDecimal(doubleUseFluxInt);  
				//保留两位
				double   f1   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();  
				
				if(f1<1.0) {
					hashmap.put("unit","M");
					hashmap.put("flux",useFluxDouble);
				}else {
					hashmap.put("unit","G");
					hashmap.put("flux",f1);
				}
				
				
			}
			
			
		}
		
		
		
		return hashmap;
	}
	
	
	
	public static String returnStrL(HashMap<String,String> hashMap,HashMap<String,Boolean> flagMap) {
		
		StringBuilder strBuilder =new StringBuilder();
		
		String[] strType= {"GNTY","OTHER","SNTY","BZLTY","BZLOTH"};
		
		for(String str:strType) {
			Boolean flag=flagMap.get(str);
			
			if(flag!=null) {
				
			if(flag) {
				
				String bzloth=hashMap.get(str);
				
				
				 strBuilder.insert(0,bzloth==null?"":bzloth);
				 
			}else {
				String bzloth=hashMap.get(str);
				
				
				 strBuilder.append(bzloth==null?"":bzloth);
				
			}
			
			}
			
		}	
		
		return strBuilder.toString();
		
	}
	
	
}
