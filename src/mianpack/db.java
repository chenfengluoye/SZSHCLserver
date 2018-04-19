package mianpack;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class db {
	
	public static JSONObject add(String table,JSONObject data){
		Iterator<String> keys=data.keys();
		String sql="insert into "+table;
		List<String> values=new ArrayList();
		JSONArray keyarray=new JSONArray();
		JSONArray vararray=new JSONArray();
		JSONArray ks=dao.getKeys(table);
		while(keys.hasNext()){
			String key=keys.next();
			if(ks.contains(key)){
				keyarray.add(key);
				vararray.add("?");
				values.add(data.optString(key));
			}else{
				System.out.println(table+" is not contains "+key);
			}
		}
		sql+=Format.formIntCollectionForDB(keyarray)+"values"+Format.formIntCollectionForDB(vararray);
		String[] ars=values.toArray(new String[values.size()]);
		JSONObject re=dao.insert(sql, ars);
		return re;
	}
	
	public static JSONObject update(String table,String key,String value,JSONObject newData){
		Iterator<String> keys=newData.keys();
		String sql="update "+table+" set ";
		List<String> values=new ArrayList();
		JSONArray keyarray=new JSONArray();
		JSONArray vararray=new JSONArray();
		JSONArray ks=dao.getKeys(table);
		boolean isback=false;
		while(keys.hasNext()){
			String keyitem=keys.next();
			if(ks.contains(keyitem)&&!keyitem.equals(key)){
				if(isback){
					sql+=",";
				}
				sql+=keyitem+"=?";
				isback=true;
				vararray.add(newData.optString(keyitem));
			}
		}
		sql+=" where "+key+"=?";
		vararray.add(value);
		String[] ars=new String[vararray.size()];
		for(int i=0;i<vararray.size();i++){
			ars[i]=vararray.getString(i);
		}
		JSONObject re=dao.update(sql, ars);
		return re;
		
	}
	
	public static JSONObject delete(String table,String key,String value){
		String sql="delete from "+table +" where "+key+"=?";
		JSONObject re=dao.delete(sql, value);
		return re;
		
	}
	
	public static JSONObject query(String table,String key,String value){
		String sql="select * from "+table+" where "+key+"=?";
		JSONObject re=dao.queryUniqe(sql, value);
		return re;
	}
	
	public static JSONArray querys(String table,String key,JSONArray values){
		String sql="select * from "+table+" where "+key+" in "+Format.formStringCollectionForDB(values);
		JSONArray re=dao.queryArray(sql);
		return re;
	}
	
	public static JSONArray querys(String table,String key,String value){
		String sql="select * from "+table+" where "+key+" like '%"+value+"%'";
		JSONArray re=dao.queryArray(sql);
		return re;
	}
	
	
}
