package mianpack;

import java.text.DecimalFormat;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class tess {
	static JSONArray a;
	public static void main(String[] args) {

		
//		DecimalFormat df=new DecimalFormat('#.###');
//	
//		System.out.println(df.format(-0.45511));
//		System.out.println(df.format(-214.45511));
		String s="[{'name':'name0','sex':'sex0'},{'name':'name1','sex':'sex1'}]";
		a=JSONArray.fromObject(s);
//		for(int i=0;i<2;i++){
//			JSONObject o=new JSONObject();
//			o.put("name","name"+i);
//			o.put("sex","sex"+i);
//			a.add(o);
//		}
		System.out.println(a.toString());
		JSONObject o=new JSONObject();
		o.put("name","name"+4);
		o.put("sex","sex"+4);
		a.add(0,o);
		System.out.print(a.toString());
	}
}
