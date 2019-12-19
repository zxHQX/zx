package cn.powertime.utils.config;
import javax.servlet.*;
import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter  implements Filter{
	public void destroy() {		
	}
	public void doFilter(ServletRequest req, ServletResponse res,FilterChain chain) throws IOException, ServletException {
		System.out.println("进入了");
		HttpServletRequest request=(HttpServletRequest)req;
		HttpServletResponse response=(HttpServletResponse)res;
		response.addHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));	// 解决跨域共享session问题
		response.addHeader("Access-Control-Allow-Credentials","true");					// 允许跨域
		response.addHeader("Access-Control-Allow-Methods","POST,GET,OPTIONS,DELETE");
		response.addHeader("Access-Control-Allow-Headers","Content-Type,X-Requested-With,accept,Origin,Access-Control-Request-Method,Access-Control-Request-Headers,token");
		String method=request.getMethod();
		if(method.equalsIgnoreCase("OPTIONS"))
			return;
		HttpSession session=request.getSession(false);
		//获取传过来的URI
		String path = request.getRequestURI();
		//login页面
		 int port = request.getRemotePort();
		 if(path.indexOf("/logins.do") > -1 ) {
			 chain.doFilter(request, response);
			 delSession(session,port);
			 return;
		 }		 
		 if (session == null) {
			 // 跳转到登陆页面
			 response.sendRedirect("/index.html");
			 //chain.doFilter(request, response);
		 } else {
			 // 已经登陆,继续此次请求
			 chain.doFilter(request, response);
		 }
		 if(path.indexOf("/ClearOut.do")<0)
			 delSession(session,port);
	}
	public void init(FilterConfig arg0) throws ServletException {
		
	}
	private void delSession(HttpSession session,int port){
		if(session!=null){
			if(session.getAttribute("user_request"+port)!=null)
				session.removeAttribute("user_request"+port);
		}
	}
}
