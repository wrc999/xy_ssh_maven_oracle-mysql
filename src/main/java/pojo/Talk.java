package pojo;

public class Talk {
	
	private int talk_id;		//分享编号
	private int use_id;			//用户编号
	private String talktime;	//分享时间
	private String talkcontent;	//分享内容
	private String talkphoto;	//分享照片url
	private String condition;	//查询条件
	private String account;		//获取用户名
	private String school;		//获取学校名

	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public int getTalk_id() {
		return talk_id;
	}
	public void setTalk_id(int talk_id) {
		this.talk_id = talk_id;
	}
	public int getUse_id() {
		return use_id;
	}
	public void setUse_id(int use_id) {
		this.use_id = use_id;
	}
	public String getTalktime() {
		return talktime;
	}
	public void setTalktime(String talktime) {
		this.talktime = talktime;
	}
	public String getTalkcontent() {
		return talkcontent;
	}
	public void setTalkcontent(String talkcontent) {
		this.talkcontent = talkcontent;
	}
	public String getTalkphoto() {
		return talkphoto;
	}
	public void setTalkphoto(String talkphoto) {
		this.talkphoto = talkphoto;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	@Override
	public String toString() {
		return "Talk [talk_id=" + talk_id + ", use_id=" + use_id + ", talktime=" + talktime + ", talkcontent="
				+ talkcontent + ", talkphoto=" + talkphoto + ", condition=" + condition + ", account=" + account
				+ ", school=" + school + "]";
	}
	
}
