package mianpack;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import net.sf.json.JSONObject;

@Controller
public class RegistAndLogin{
	
	

	@RequestMapping("/sturegist")
	public void sturegist(HttpServletRequest req,HttpServletResponse resp){
		try {
			JSONObject re=new JSONObject();
			PrintWriter writer=resp.getWriter();
			JSONObject js=Net.getReqJson(req);
			System.out.println(js.toString());
			String stuId=js.optString("stuId");
			boolean isstu=dao.isexist("students", "stuId", stuId);
			boolean istec=dao.isexist("teachers", "teacherId", stuId);
			if(isstu||istec){
				re.put("re",false);
				re.put("why","已有该账号");
			}else{
				re=db.add("students", js);
			}
			writer.write(re.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/stulogin")
	public void stulogin(HttpServletRequest req,HttpServletResponse resp){
		try {
			PrintWriter writer=resp.getWriter();
			JSONObject js=Net.getReqJson(req);
			System.out.println(js.toString());
			String sql="select * from students where stuId=? and stupass=?";
			JSONObject re=dao.queryUniqe(sql,js.optString("stuId"),js.optString("stupass"));
			writer.write(re.toString());
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/tecregist")
	public void tecregist(HttpServletRequest req,HttpServletResponse resp){
		try {
			JSONObject re=new JSONObject();
			
			PrintWriter writer=resp.getWriter();
			JSONObject js=Net.getReqJson(req);
			System.out.println(js.toString());
			String teacherId=js.optString("teacherId");
			boolean isstu=dao.isexist("students", "stuId", teacherId);
			boolean istec=dao.isexist("teachers", "teacherId", teacherId);
			if(isstu||istec){
				re.put("re",false);
				re.put("why","已有该账号");
			}else{
				re=db.add("teachers", js);
			}
			writer.write(re.toString());
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/teclogin")
	public void teclogin(HttpServletRequest req,HttpServletResponse resp){
		try {
			PrintWriter writer=resp.getWriter();
			JSONObject js=Net.getReqJson(req);
			System.out.println(js.toString());
			String sql="select * from teachers where teacherId=? and tecpass=?";
			JSONObject re=dao.queryUniqe(sql,js.optString("teacherId"),js.optString("tecpass"));
			writer.write(re.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
