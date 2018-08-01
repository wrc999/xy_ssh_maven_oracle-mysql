package serviceDao.talk;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dao.talk.talkDao;
import pojo.Talk;
import pojo.collect;
@Transactional(isolation=Isolation.SERIALIZABLE,propagation=Propagation.REQUIRED,readOnly=false)
public class talkServiceDao implements talkServiceDaoInterface {
	talkDao talkDao;
	@Override
	//查询业务
	@Transactional(isolation=Isolation.SERIALIZABLE,propagation=Propagation.REQUIRED,readOnly=true)
	public ArrayList<Talk> talkFind(Talk talk) {
		// TODO Auto-generated method stub
		ArrayList<Talk> talkList = null;
	      try {
			talkList = talkDao.talkSelect(talk);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      return talkList;
	}

	@Override
	//添加业务
	public void talkAdd(Talk talk) {
		// TODO Auto-generated method stub
	     try {
			talkDao.talkInsert(talk);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void talkDel(Talk talk) {
		// TODO Auto-generated method stub
	     try {
			talkDao.talkDelete(talk);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean talkCollect_add(collect collect) {
		// TODO Auto-generated method stub
	     try {
	    	 talkDao.talkCollect_add(collect);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     return true;
	}

	@Override
	public void talkCollect_delete(collect collect) {
		// TODO Auto-generated method stub
	     try {
	    	 talkDao.talkCollect_delete(collect);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	@Transactional(isolation=Isolation.SERIALIZABLE,propagation=Propagation.REQUIRED,readOnly=true)
	public List<collect> talkCollect_find(DetachedCriteria dc) {
		// TODO Auto-generated method stub
	      List<collect> talkList = null;
	      try {
			talkList = talkDao.talkCollect_find(dc);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      return talkList;
	}

	public void setTalkDao(talkDao talkDao) {
		this.talkDao = talkDao;
	}
	
}
