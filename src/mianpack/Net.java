package mianpack;

import java.io.BufferedReader;
import java.io.IOException;


import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;



public class Net {
	
	public static String getReqStr(HttpServletRequest req){
		String str="";
		BufferedReader reader;
		try {
			reader = req.getReader();
			String line=null;
			StringBuilder buider=new StringBuilder();
			while((line=reader.readLine())!=null){
				buider.append(line);
			}
			str=buider.toString();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return str;
	}
	
	public static JSONObject getReqJson(HttpServletRequest req){
		
		JSONObject reqst=new JSONObject();
		String str="";
		BufferedReader reader;
		try {
			reader = req.getReader();
			String line=null;
			StringBuilder buider=new StringBuilder();
			while((line=reader.readLine())!=null){
				buider.append(line);
			}
			str=buider.toString();
			System.out.println(str);
			reqst=JSONObject.fromObject(str);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return reqst;
	}

}
