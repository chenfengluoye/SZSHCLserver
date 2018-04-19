package mianpack;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import net.sf.json.JSONObject;

@Controller
public class Change {
	
	public Change(){
		System.out.println("the constractor is excuted......");
	}
	
	/**
	 * 修改学生信息
	 */
	@RequestMapping("/changestu")
	public static void changestu(HttpServletRequest req,HttpServletResponse resp){
		try {
			JSONObject re=new JSONObject();
			PrintWriter writer=resp.getWriter();
			JSONObject js=Net.getReqJson(req);
			String stuId=js.optString("stuId");
			re=db.update("students", "stuId", stuId, js);
			writer.write(re.toString());
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	/**
	 * 修改老师信息
	 */
	@RequestMapping("/changetec")
	public void changetec(HttpServletRequest req,HttpServletResponse resp){
		try {
			JSONObject re=new JSONObject();
			PrintWriter writer=resp.getWriter();
			JSONObject js=Net.getReqJson(req);
			String teacherId=js.optString("teacherId");
			re=db.update("teachers", "teacherId", teacherId, js);
			writer.write(re.toString());
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	/**
	 * 修改班级信息
	 */
	@RequestMapping("/changecla")
	public void changecla(HttpServletRequest req,HttpServletResponse resp){
		try {
			JSONObject re=new JSONObject();
			PrintWriter writer=resp.getWriter();
			JSONObject js=Net.getReqJson(req);
			String classId=js.optString("classId");
			re=db.update("classes", "classId", classId, js);
			writer.write(re.toString());
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	/**
	 * 打分及评价
	 */
	@RequestMapping("/changesy")
	public void changesy(HttpServletRequest req,HttpServletResponse resp){
		try {
			JSONObject re=new JSONObject();
			PrintWriter writer=resp.getWriter();
			JSONObject js=Net.getReqJson(req);
			String scoreId=js.optString("scoreId");
			String syId=js.optString("syId");
		
			re=db.update(syId, "scoreId",scoreId, js);
			
			writer.write(re.toString());
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
}
