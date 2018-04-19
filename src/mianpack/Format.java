package mianpack;

import net.sf.json.JSONArray;

public class Format {
	
	public static String formStringCollectionForDB(JSONArray array){
		String form="(";
		int i;
		int len=array.size();
		if(len==0){
			return "()";
		}
		for(i=0;i<array.size()-1;i++){
			form+="'"+array.getString(i)+"',";
		}
		form+="'"+array.get(i)+"')";
		return form;
	}
	
	public static String formStringCollectionForDB(String[] array){
		String form="(";
		int len=array.length;
		if(len==0){
			return "()";
		}
		int i;
		for(i=0;i<array.length-1;i++){
			form+="'"+array[i]+"',";
		}
		form+="'"+array[i]+"')";
		return form;
	}
	
	public static String formStringCollectionForDB(String jsonArrayStr){
		JSONArray ar=JSONArray.fromObject(jsonArrayStr);
		String form="(";
		int i;
		int len=ar.size();
		if(len==0){
			return "()";
		}
		for(i=0;i<ar.size()-1;i++){
			form+="'"+ar.getString(i)+"',";
		}
		form+="'"+ar.get(i)+"')";
		return form;
	}
	
	public static String formIntCollectionForDB(JSONArray array){
		String form="(";
		int i;
		int len=array.size();
		if(len==0){
			return "()";
		}
		for(i=0;i<array.size()-1;i++){
			form+=array.getString(i)+",";
		}
		form+=array.get(i)+")";
		return form;
	}
	

}
