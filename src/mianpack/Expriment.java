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
public class Expriment {
	
	
	@RequestMapping("/getsy1data")
	public void getsy1data(HttpServletRequest req,HttpServletResponse resp){
		try {
			PrintWriter writer=resp.getWriter();
			JSONObject js=Net.getReqJson(req);
			String userId=js.optString("userId");
			String classId=js.optString("classId");
			String sql="select * from sy1scores where bltstuId=? and bltclassId=? order by scoreId desc";
			JSONArray array=dao.queryArray(sql, userId,classId);
			writer.write(array.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/addsy1data")
	public void addsy1data(HttpServletRequest req,HttpServletResponse resp){
		try {
			PrintWriter writer=resp.getWriter();
			JSONObject js=Net.getReqJson(req);
			JSONObject re=new JSONObject();
			re=db.add("sy1scores", js);
			writer.write(re.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/getsy2data")
	public void getsy2data(HttpServletRequest req,HttpServletResponse resp){
		try {
			PrintWriter writer=resp.getWriter();
			JSONObject js=Net.getReqJson(req);
			String userId=js.optString("userId");
			String classId=js.optString("classId");
			String sql="select * from sy2scores where bltstuId=? and bltclassId=? order by scoreId desc";
			JSONArray array=dao.queryArray(sql, userId,classId);
		
			writer.write(array.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/addsy2data")
	public void addsy2data(HttpServletRequest req,HttpServletResponse resp){
		try {
			PrintWriter writer=resp.getWriter();
			JSONObject js=Net.getReqJson(req);
			JSONObject re=new JSONObject();
			re=db.add("sy2scores", js);
			writer.write(re.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/getsy3data")
	public void getsy3data(HttpServletRequest req,HttpServletResponse resp){
		try {
			PrintWriter writer=resp.getWriter();
			JSONObject js=Net.getReqJson(req);
			String userId=js.optString("userId");
			String classId=js.optString("classId");
			String sql="select * from sy3scores where bltstuId=? and bltclassId=? order by scoreId desc";
			JSONArray array=dao.queryArray(sql, userId,classId);
			writer.write(array.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/addsy3data")
	public void addsy3data(HttpServletRequest req,HttpServletResponse resp){
		try {
			PrintWriter writer=resp.getWriter();
			JSONObject js=Net.getReqJson(req);
			JSONObject re=new JSONObject();
			re=db.add("sy3scores", js);
			writer.write(re.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
