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
public class Student {

	/**
	 * 根据班级编号获取学生信息
	 * @param classId 班级ID
	 */
	@RequestMapping("/getStubyclassId")
	public void getStubyclassId(HttpServletRequest req,HttpServletResponse resp){
		try {
			JSONObject re=new JSONObject();
			PrintWriter writer=resp.getWriter();
			JSONObject js=Net.getReqJson(req);
			String classId=js.optString("classId");
			String sql="select stumember from classes where classId=?";
			re=dao.queryUniqe(sql,classId);
			JSONArray stuIdArray=re.optJSONArray("stumember");
			if(stuIdArray==null){
				stuIdArray=new JSONArray();
			}
			if(stuIdArray.size()==0){
				writer.write(stuIdArray.toString());
				return;
			}
			System.out.println(stuIdArray.toString());
			String sql2="select * from students where stuId in "+Format.formStringCollectionForDB(stuIdArray);
			JSONArray stuarray=dao.queryArray(sql2);
			writer.write(stuarray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
