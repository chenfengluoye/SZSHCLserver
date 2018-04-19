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
public class MyMessage {
	
	@RequestMapping("/addleavemsg")
	public void addleavemsg(HttpServletRequest req,HttpServletResponse resp){
		try {
			JSONObject re=new JSONObject();
			PrintWriter writer=resp.getWriter();
			JSONObject js=Net.getReqJson(req);
			re=db.add("messages", js);
			writer.write(re.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/getleavemsg")
	public void getleavemsg(HttpServletRequest req,HttpServletResponse resp){
		try {
			PrintWriter writer=resp.getWriter();
			JSONObject js=Net.getReqJson(req);
			String userId=js.optString("userId");
			String classId=js.optString("classId");
			
			String sql="select * from messages where touserId=? and bltclassId=?";
			JSONArray array=dao.queryArray(sql, userId,classId);
			writer.write(array.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
