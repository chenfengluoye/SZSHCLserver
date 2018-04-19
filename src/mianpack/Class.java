package mianpack;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class Class {
	
	
	/**
	 * 获取班级的详细信息，包括老师
	 */
	@RequestMapping("/getclassandTeacherbyclassId")
	public void getclassandTeacherbyclassId(HttpServletRequest req,HttpServletResponse resp){
		try {
			JSONObject re=new JSONObject();
			PrintWriter writer=resp.getWriter();
			JSONObject js=Net.getReqJson(req);
			String classId=js.optString("classId");
			String sql="select classes.*,teachers.* from classes,teachers where classId=? and classes.teacherId=teachers.teacherId";
			re=dao.queryUniqe(sql,classId);
			writer.write(re.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据班级编号获取班级详细信息
	 * @param classId 班级ID
	 */
	@RequestMapping("/getclassbyclassId")
	public void getclassbyclassId(HttpServletRequest req,HttpServletResponse resp){
		try {
			JSONObject re=new JSONObject();
			PrintWriter writer=resp.getWriter();
			JSONObject js=Net.getReqJson(req);
			String classId=js.optString("classId");
			String sql="select * from classes where classId=?";
			re=dao.queryUniqe(sql,classId);
			writer.write(re.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取关于某个用户的班级
	 * @param userId 用户ID
	 */
	@RequestMapping("/getclassbyadmin")
	public void getclassbyadmin(HttpServletRequest req,HttpServletResponse resp){
		try {
			JSONArray re=new JSONArray();
			PrintWriter writer=resp.getWriter();
			JSONObject js=Net.getReqJson(req);
			String userId=js.optString("userId");
			String sql="select * from classes where teacherId=? or stumember like '%\""+userId+"\"%'";
			re=dao.queryArray(sql,userId);
			writer.write(re.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	/**
	 * 根据班级编号或者班级名称查找班级，模糊搜索
	 * 
	 * @param value 班级编号或者班级名称,模糊搜索
	 */
	@RequestMapping("/getclassbyvalue")
	public void getclassbyvalue(HttpServletRequest req,HttpServletResponse resp){
		try {
			JSONArray re=new JSONArray();
			PrintWriter writer=resp.getWriter();
			JSONObject js=Net.getReqJson(req);
			String value=js.optString("value");
			String sql="select * from classes where classId like '%"+value+"%' or classname like '%"+value+"%'";
			re=dao.queryArray(sql);
			writer.write(re.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 创建班级
	 */
	@RequestMapping("/create")
	public void create(HttpServletRequest req,HttpServletResponse resp){
		try {
			JSONObject re=new JSONObject();
			PrintWriter writer=resp.getWriter();
			JSONObject js=Net.getReqJson(req);
			String classId=js.optString("classId");
			boolean isclass=dao.isexist("classes", "classId", classId);
			if(isclass){
				re.put("re",false);
				re.put("why","已有该班级");
			}else{
				js.put("stunumber", 0);
				re=db.add("classes", js);
			}
			writer.write(re.toString());
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	/**
	 * 解散班级
	 * @param userId 操作者id
	 * @param classId 班级ID
	 */
	@RequestMapping("/dismiss")
	public void dismiss(HttpServletRequest req,HttpServletResponse resp){
		try {
			JSONObject re=new JSONObject();
			PrintWriter writer=resp.getWriter();
			JSONObject js=Net.getReqJson(req);
			String user=js.optString("userId");
			String classId=js.optString("classId");
			JSONObject c=db.query("classes","classId", classId);
			String tec=c.optString("teacherId");
			if(!user.equals("")&&user.equals(tec)){
				re=db.delete("classes", "classId", classId);
			}else{
				re.put("re",false);
				re.put("why","你无权进行此操作");
			}
			writer.write(re.toString());
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	/**
	 * 学生加入班级
	 * @param classId 班级id 
	 * @param stuId 欲加入学生的ID
	 */
	@RequestMapping("/jointo")
	public void jointo(HttpServletRequest req,HttpServletResponse resp){
		try {
			JSONObject re=new JSONObject();
			PrintWriter writer=resp.getWriter();
			JSONObject js=Net.getReqJson(req);
			String stuId=js.optString("stuId");
			String classId=js.optString("classId");
			JSONObject cla=db.query("classes", "classId",classId);
			JSONArray stumember=cla.optJSONArray("stumember");
			if(stumember==null){
				stumember=new JSONArray();
			}
			if(stumember.contains(stuId)){
				re.put("re",false);
				re.put("why","你已经在该班级了");
			}else{
				stumember.add(stuId);
				
				JSONObject newdata=new JSONObject();
				newdata.put("stunumber",stumember.size());
				newdata.put("stumember",stumember.toString());
				re=db.update("classes", "classId", classId, newdata);
			}
			writer.write(re.toString());
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 学生退出班级
	 * @param classId 班级id 
	 * @param stuId 欲退出学生的ID
	 */
	@RequestMapping("/tuichu")
	public void tuichu(HttpServletRequest req,HttpServletResponse resp){
		try {
			JSONObject re=new JSONObject();
			PrintWriter writer=resp.getWriter();
			JSONObject js=Net.getReqJson(req);
			String stuId=js.optString("stuId");
			String classId=js.optString("classId");
			JSONObject cla=db.query("classes", "classId",classId);
			JSONArray stumember=cla.optJSONArray("stumember");
			if(stumember==null){
				stumember=new JSONArray();
			}
			if(!stumember.contains(stuId)){
				re.put("re",false);
				re.put("why","你不在该班级");
			}else{
				stumember.remove(stuId);
				JSONObject newdata=new JSONObject();
				newdata.put("stunumber",stumember.size());
				newdata.put("stumember",stumember.toString());
				re=db.update("classes", "classId", classId, newdata);
			}
			writer.write(re.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
