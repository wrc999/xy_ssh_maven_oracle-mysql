package serviceDao.talk;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import pojo.Talk;
import pojo.collect;

public interface talkServiceDaoInterface {
	
	  public ArrayList<Talk> talkFind(Talk talk);//用户查询业务
	  public void talkAdd(Talk talk);//添加业务
	  public void talkDel(Talk talk);//删除业务
	  
	  
	  public boolean talkCollect_add(collect collect);//添加收藏业务
	  public void talkCollect_delete(collect collect);//删除收藏业务
	  public List<collect> talkCollect_find(DetachedCriteria dc);//查询收藏业务
}
