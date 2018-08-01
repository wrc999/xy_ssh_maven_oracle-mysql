package web.action;

import java.io.File;
import java.io.PrintWriter;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.Talk;
import serviceDao.talk.talkServiceDao;

public class talk extends ActionSupport{

	private Talk talk = new Talk();
	private String use_id;
	private String talkcontent;
	private File file;
	private String fileFileName;
	private talkServiceDao talkSD;
	private HttpServletResponse response= ServletActionContext.getResponse ();
	private HttpServletRequest request = ServletActionContext.getRequest();
	
	
	public String add() throws Exception {
		//上传照片
		String p =this.getClass().getResource("").getPath();
        int p1 = p.indexOf("webapps");
		String savePath = p.substring(1, p1)+"pic"+File.separator+"xl";
		File filePath = new File(savePath);
		if(!filePath.exists()) {filePath.mkdirs();}
		file.renameTo(new File(filePath,fileFileName));
		//发布内容
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String talktime = df.format(new Date());
		String fileFileName1="/pic/xl/"+fileFileName;
		talk.setUse_id(Integer.parseInt(use_id));
		talk.setTalkcontent(talkcontent);
		talk.setTalkphoto(fileFileName1);
		talk.setTalktime(talktime);
		talkSD.talkAdd(talk);
		// TODO Auto-generated method stub
		return "toList";
	}
	public String delete() throws Exception {
		talk.setTalk_id(Integer.parseInt(request.getParameter("talk_id")));
		talkSD.talkDel(talk);
		// TODO Auto-generated method stub
		return "toList";
	}
	public void get() throws Exception {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
  		String condition = request.getParameter("condition");
  		if (condition != null && condition.length() > 0){
	    	talk.setCondition("talkcontent like '%"+condition+"%' or account like '%"+condition+"%' or school like '%"+condition+"%'");
	    }else{
	    	talk.setCondition("");
	    }
  		ArrayList<Talk> talkList = (ArrayList<Talk>) talkSD.talkFind(talk);
  		JSONArray stuMsg = JSONArray.fromObject(talkList);
 	    out.print(stuMsg);
 	    out.close();
	}
	
	public void adminGet() throws Exception {
		// TODO Auto-generated method stub
		String _pageSize = request.getParameter("rows");
		String _page = request.getParameter("page");
		int pageSize = 10;
		if(_pageSize != null && !_pageSize.equals("")){
			pageSize = Integer.parseInt(_pageSize);
		}
		int page = 1;
		if(_page != null && !_pageSize.equals("")){
			page = Integer.parseInt(_page);
		}
	    String condition = request.getParameter("condition");
  
  		if (condition != null && condition.length() > 0){
	    	talk.setCondition("talkcontent like '%"+condition+"%' or account like '%"+condition+"%' or school like '%"+condition+"%'");
	    }else{
	    	talk.setCondition("");
	    }
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		ArrayList<Talk> talkList = (ArrayList<Talk>) talkSD.talkFind(talk);
		int total = talkList.size();
		ArrayList<Talk> userList1 = new ArrayList<Talk>();
		Map<String, Serializable> resultMap = new HashMap<String, Serializable>();
		resultMap.put("total", total);
		if(_page != null && _page.length() > 0 && _pageSize!=null && _pageSize.length()>0){
			for(int i = (page-1)*pageSize ; i< page*pageSize && i< total;i++){
				userList1.add(talkList.get(i));
			}
			resultMap.put("rows", userList1);
	        JSONObject jsonObject = JSONObject.fromObject(resultMap);
	        System.out.println("======="+jsonObject);
			out.print(jsonObject);
		}else{
			resultMap.put("rows", talkList);
	        JSONObject jsonObject = JSONObject.fromObject(resultMap);
	        System.out.println("-------"+jsonObject);
			out.print(jsonObject);
		}
	}
	public String getUse_id() {
		return use_id;
	}
	public void setUse_id(String use_id) {
		this.use_id = use_id;
	}
	public String getTalkcontent() {
		return talkcontent;
	}
	public void setTalkcontent(String talkcontent) {
		this.talkcontent = talkcontent;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	public void setTalkSD(talkServiceDao talkSD) {
		this.talkSD = talkSD;
	}

}
