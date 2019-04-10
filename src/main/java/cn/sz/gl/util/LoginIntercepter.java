package cn.sz.gl.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 *  可以实现HandlerInterceptor接口，也可以继承HandlerInterceptorAdapter
 * @author THINK
 *
 */
public class LoginIntercepter extends HandlerInterceptorAdapter {
	
	private static String [] urls = {"bc/showbook","uc/login","bc/addbook","bc/downloadfile"};
	
	public static boolean checkUrl(String requestname){
		if(requestname==null||requestname.equals("")){
			return true;
		}
		
		if(requestname.endsWith(".html")||requestname.endsWith(".js")||requestname.endsWith(".css")||requestname.endsWith(".jpg")){
			return true;
		}
		
		for (int i = 0; i < urls.length; i++) {
			if(urls[i].equals(requestname)){
				return true;
			}
		}
		return false;
	}
	

	/**
	 * 在目标方法执行之前的操作(也就是控制器类中对应的方法执行之前)
	 * 返回一个boolean值：
	 * 如果返回true,那么请求继续，后续如果有其他拦截器，继续执行其他拦截器的preHandle方法，如果没有，就执行Controller中的业务方法
	 * 如果返回false,那么请求到这里就结束了，其他拦截器都不再执行，控制器中的方法也不再执行，甚至本拦截器类中的后续方法也不再执行了(比如postHandle等方法)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		System.out.println("执行了preHandle方法....");
		
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("users");//从session中获取users对象
		
		String uri = request.getRequestURI();
		String contextpath = request.getContextPath();
		String requestname = uri.substring(contextpath.length()+1);//获取当前请求的名字
		System.out.println("requestname:"+requestname);
		
		if(obj==null){
			if(checkUrl(requestname)){
				return true;
			}else{
				String path = request.getContextPath();
				String basepath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
				//request.getRequestDispatcher("/login.jsp").forward(request, response);
				response.sendRedirect(basepath+"login.jsp");
				return false;
			}
		}else{
			
			return true;
		}
	}
	
	/**
	 * 在目标方法执行之后的操作
	 * 该方法执行有一个前提，就是preHandle方法返回的是true;
	 * 
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("执行了postHandle方法");
	}
	
	
	/**
	 * 请求完成的时候，再执行的方法;
	 * 执行的前提，也是要求preHandle方法必须返回true
	 * 一般用来释放资源
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("执行了afterCompletion方法");
	}
	
	
	
	
}
