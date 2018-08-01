package web.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
//为什么继承MethodFilterInterceptor？因为可以指定哪些方法不拦截，哪些拦截
public class LoginInterceptor extends MethodFilterInterceptor{

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> session = ActionContext.getContext().getSession();
		Object object = session.get("use_id");
		if(object==null){
			return "toLogin";
		}else {
			return invocation.invoke();
		}
	}

}
