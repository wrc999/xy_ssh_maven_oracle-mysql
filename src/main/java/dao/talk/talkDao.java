package dao.talk;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import pojo.Talk;
import pojo.collect;

public class talkDao extends HibernateDaoSupport implements talkDaoInterface {
	  
	public talkDao() {
		super();
	}

	@Override
	//查询记录
	public ArrayList<Talk> talkSelect(final Talk talk) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return getHibernateTemplate().execute(new HibernateCallback<ArrayList<Talk>>() {
			@Override
			public ArrayList<Talk> doInHibernate(Session session) throws HibernateException {
				String hql = "select t.*,u.account,u.school from Talk t,userInfo u where t.use_id=u.use_id ";
			    String condition = talk.getCondition();
			    if(condition !=null && !condition.equals("")){
			      hql += "and " + condition;
			    }
			    SQLQuery query = session.createSQLQuery(hql);
				List<Object> list = query.list();
				ArrayList<Talk> talkList = new ArrayList<Talk>();
				for(Object obj:list) {
					 Object[] objs = (Object[])obj;
			         Talk t = new Talk();
			         t.setTalk_id((int)objs[0]);
			         t.setUse_id((int)objs[1]);
			         t.setTalkcontent((String) objs[2]);
			         t.setTalkphoto((String) objs[3]);
			         t.setTalktime(objs[4].toString().substring(0, 19));
			         t.setAccount((String) objs[5]);
			         t.setSchool((String) objs[6]);
			         talkList.add(t);
				}
			    return talkList;
			}
		});
	}

	@Override
	//插入记录
	public void talkInsert(Talk talk) throws SQLException {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(talk);
	}

	@Override
	//删除记录
	public void talkDelete(Talk talk) throws SQLException {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(talk);
	}

	@Override
	public void talkCollect_add(collect collect) throws SQLException {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(collect);
	}

	@Override
	public void talkCollect_delete(collect collect) throws SQLException {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(collect);
	}

	@Override
	public List<collect> talkCollect_find(DetachedCriteria dc) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		List<collect> talklist = (List<collect>) getHibernateTemplate().findByCriteria(dc);
	    return talklist;
	}
}