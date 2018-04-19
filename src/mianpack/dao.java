package mianpack;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class dao {
	
	static ApplicationContext spring=new ClassPathXmlApplicationContext("spring.xml");
	static DriverManagerDataSource dataSource=(DriverManagerDataSource) spring.getBean("dataSource");
	static JdbcTemplate jdbc=new JdbcTemplate(dataSource);
	
	
	
	//添加入库
	public static JSONObject insert(String sql,Object...args){
		System.out.println(sql);
		JSONObject re=new JSONObject();
		try{
			int i=jdbc.update(sql, args);
			re.put("re", true);
		}catch(Exception e){
			e.printStackTrace();
			re.put("re", false);
			re.put("why",e.getMessage());
		}
		return re;
	}
	
	//删除
	public static JSONObject delete(String sql,Object...args){
		System.out.println(sql);
		JSONObject re=new JSONObject();
		try{
			int i=jdbc.update(sql, args);
			if(i>0){
				re.put("re", true);
			}
			
			else{
				re.put("re",false);
				re.put("why","the oprated item is not exsit");
			}
		}catch(Exception e){
			e.printStackTrace();
			re.put("re",false);
			re.put("why",e.getMessage());
		}
		return re;
	}	
	
	//修改
	public static JSONObject update(String sql,Object...args){
		System.out.println(sql);
		JSONObject re=new JSONObject();
		try{
			int i=jdbc.update(sql, args);
			if(i>0){
				re.put("re", true);
			}else{
				re.put("re", false);
				re.put("why","the oprated item is not exsit");
			}
		}catch(Exception e){
			e.printStackTrace();
			re.put("re", false);
			re.put("why",e.getMessage());
		}
		return re;		
	}
	
	//查询
	public static JSONArray queryArray(String sql,Object...args){
		System.out.println(sql);
		SqlRowSet set=jdbc.queryForRowSet(sql, args);
		JSONArray array=new JSONArray();
		SqlRowSetMetaData data=set.getMetaData();
		int cc=data.getColumnCount();
		System.out.println("data.getColumnCount():"+cc);
		while(set.next()){
			JSONObject item=new JSONObject();
			for(int i=1;i<=cc;i++){
				String key=data.getColumnName(i);
				String value=set.getString(i);
				item.put(key, value);
			}
			array.add(item);
		}
		return array;
	}	
	
	//查询
	public static JSONObject queryUniqe(String sql,Object...args){
		System.out.println(sql);
		SqlRowSet set=jdbc.queryForRowSet(sql, args);
		SqlRowSetMetaData data=set.getMetaData();
		int cc=data.getColumnCount();
		JSONObject item=new JSONObject();
		if(set.next()){
			item=new JSONObject();
			for(int i=1;i<=cc;i++){
				String key=data.getColumnName(i);
				String value=set.getString(i);
				item.put(key, value);
			}
		}
		return item;
	}	
	
	public static boolean isexist(String table,String key,String value){
		
		String sql="select count(*) from "+table+" where "+key+"=?";
		System.out.println(sql);
		int i=jdbc.queryForObject(sql, Integer.class, value);
		if(i>0){
			return true;
		}else{
			return false;
		}
	}
	
	public static JSONArray getKeys(String table){
		JSONArray array=new JSONArray();
		try{
			String sql="show full columns from " + table;
			System.out.println(sql);
			SqlRowSet set=jdbc.queryForRowSet(sql);
			JSONArray a=new JSONArray();
			while(set.next()){
				array.add(set.getString(1));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return array;
	
	}
	

	
	

			
	
}
