package web.action;

import java.io.PrintWriter;
import java.io.Serializable;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.userInfo;
import serviceDao.user.userServiceDao;

public class user extends ActionSupport implements ModelDriven<userInfo>{

	private userInfo user = new userInfo();
	private userServiceDao useSD;
	private HttpServletResponse response= ServletActionContext.getResponse ();
	private HttpServletRequest request = ServletActionContext.getRequest();

	public void login() throws Exception {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		if(user.getAccount().equals("享悦管理员")&&user.getPassWord().equals("xiangyue")) {
			out.print("2");
			return;
		}
		DetachedCriteria dc = DetachedCriteria.forClass(userInfo.class);
		dc.add(Restrictions.and(Restrictions.eq("account", user.getAccount())).add(Restrictions.eq("passWord", user.getPassWord())));
		int flag = useSD.login(dc);
		Cookie co = new Cookie("account",URLEncoder.encode(user.getAccount(),"utf-8"));
		response.addCookie(co);
		if(flag!=0){
			String i = String.valueOf(flag);
			Cookie ID = new Cookie("use_id",i);
			response.addCookie(ID);
			ActionContext.getContext().getSession().put("use_id", i);
			out.print("1");
	    }else {
	        	out.print("0");
	        }
		out.close();
		
	}

	public String add() throws Exception {
		useSD.useAdd(user);
		System.out.println(user);
		// TODO Auto-generated method stub
		return "toList";
	}
	public String edit() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("----"+user);
		useSD.useSave(user);
		return "toList";
	}
	public String delete() throws Exception {
		useSD.useDel(user);
		// TODO Auto-generated method stub
		return "toList";
	}
	public void get() throws Exception {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		DetachedCriteria dc = DetachedCriteria.forClass(userInfo.class);
  		String condition = user.getCondition();
  		System.out.println(condition);
  		if(condition!=null && !"".equals(condition)){
  			dc.add(Restrictions.or(Restrictions.eq("use_id",Integer.parseInt(condition)),Restrictions.like("account","%"+condition+"%")));
  		}
  		ArrayList<userInfo> useList = (ArrayList<userInfo>) useSD.useFind(dc);
  		JSONArray stuMsg = JSONArray.fromObject(useList);
 	    out.print(stuMsg);
 	    out.close();
	}
	public void PassWordEdit() throws Exception {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String newpassWord = request.getParameter("newpassWord");
		DetachedCriteria dc = DetachedCriteria.forClass(userInfo.class);
		dc.add(Restrictions.and(Restrictions.eq("account", user.getAccount())).add(Restrictions.eq("passWord", user.getPassWord())));
		int flag = useSD.login(dc);
		if(flag!=0) {
			user.setPassWord(newpassWord);
			System.out.println("新密码"+user.getPassWord());
			useSD.UsePassWord_edit(user);
			out.println("1");
		}
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
	    DetachedCriteria dc = DetachedCriteria.forClass(userInfo.class);
  		if(condition!=null && !"".equals(condition)){
  			dc.add(Restrictions.or(Restrictions.eq("use_id",Integer.parseInt(condition)),Restrictions.like("account","%"+condition+"%")));
  		}
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		ArrayList<userInfo> userList = (ArrayList<userInfo>) useSD.useFind(dc);
		int total = userList.size();
		ArrayList<userInfo> userList1 = new ArrayList<userInfo>();
		Map<String, Serializable> resultMap = new HashMap<String, Serializable>();
		resultMap.put("total", total);
		if(_page != null && _page.length() > 0 && _pageSize!=null && _pageSize.length()>0){
			for(int i = (page-1)*pageSize ; i< page*pageSize && i< total;i++){
				userList1.add(userList.get(i));
			}
			resultMap.put("rows", userList1);
	        JSONObject jsonObject = JSONObject.fromObject(resultMap);
	        System.out.println("======="+jsonObject);
			out.print(jsonObject);
		}else{
			resultMap.put("rows", userList);
	        JSONObject jsonObject = JSONObject.fromObject(resultMap);
	        System.out.println("-------"+jsonObject);
			out.print(jsonObject);
		}
	}
	@Override
	public userInfo getModel() {
		// TODO Auto-generated method stub
		return user;
	}

	public void setUseSD(userServiceDao useSD) {
		this.useSD = useSD;
	}
	
}
