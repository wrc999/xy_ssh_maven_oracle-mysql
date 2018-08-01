package web.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;
import pojo.collect;
import serviceDao.talk.talkServiceDao;

public class collection extends ActionSupport{

	private int use_id;
	private int talk_id;
	private String collectcontent;
	private String collecttime;
	private String photo;
	private String condition;
	private collect collect = new collect();
	private talkServiceDao talkSD;
	private HttpServletResponse response= ServletActionContext.getResponse ();
	private HttpServletRequest request = ServletActionContext.getRequest();

	public void add() throws Exception {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		collecttime = df.format(new Date());
		photo = request.getParameter("talkphoto");
		collect.setUse_id(use_id);
		collect.setTalk_id(talk_id);
		collect.setCollectcontent(collectcontent);
		collect.setCollecttime(collecttime);
		collect.setPhoto(photo);
		boolean a = talkSD.talkCollect_add(collect);
		out.println(a);
		out.close();
	}
	public void delete() throws Exception {
		// TODO Auto-generated method stub
		collect.setUse_id(use_id);
		collect.setTalk_id(talk_id);
		talkSD.talkCollect_delete(collect);
	}
	public void find() throws Exception {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		condition = request.getParameter("condition");
		DetachedCriteria dc = DetachedCriteria.forClass(collect.class);
		dc.add(Restrictions.eq("use_id",use_id));
		if(condition!=null && !"".equals(condition)){
  			dc.add(Restrictions.like("collectcontent","%"+condition+"%"));
  		}
		ArrayList<collect> talkCollectList = (ArrayList<collect>) talkSD.talkCollect_find(dc);
	    JSONArray stuMsg = JSONArray.fromObject(talkCollectList);
	    out.print(stuMsg);
	}

	
	public String getCollectcontent() {
		return collectcontent;
	}
	public void setCollectcontent(String collectcontent) {
		this.collectcontent = collectcontent;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public int getUse_id() {
		return use_id;
	}

	public void setUse_id(int use_id) {
		this.use_id = use_id;
	}

	public int getTalk_id() {
		return talk_id;
	}

	public void setTalk_id(int talk_id) {
		this.talk_id = talk_id;
	}
	public void setTalkSD(talkServiceDao talkSD) {
		this.talkSD = talkSD;
	}
	
}
