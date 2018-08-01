package pojo;

public class collect {

	private int collect_id;				//收藏编号
	private int use_id;					//用户收藏
	private int talk_id;				//分享编号
	private String collectcontent;		//收藏内容
	private String collecttime;			//收藏时间
	private String photo;				//内容所带的图片url
	private String condition;			//搜索条件
	
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public int getCollect_id() {
		return collect_id;
	}
	public void setCollect_id(int collect_id) {
		this.collect_id = collect_id;
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
	public String getCollectcontent() {
		return collectcontent;
	}
	public void setCollectcontent(String collectcontent) {
		this.collectcontent = collectcontent;
	}
	public String getCollecttime() {
		return collecttime;
	}
	public void setCollecttime(String collecttime) {
		this.collecttime = collecttime;
	}
	
}
