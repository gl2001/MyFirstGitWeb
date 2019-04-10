package cn.sz.gl.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 *  ����ʵ��HandlerInterceptor�ӿڣ�Ҳ���Լ̳�HandlerInterceptorAdapter
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
	 * ��Ŀ�귽��ִ��֮ǰ�Ĳ���(Ҳ���ǿ��������ж�Ӧ�ķ���ִ��֮ǰ)
	 * ����һ��booleanֵ��
	 * �������true,��ô����������������������������������ִ��������������preHandle���������û�У���ִ��Controller�е�ҵ�񷽷�
	 * �������false,��ô��������ͽ����ˣ�����������������ִ�У��������еķ���Ҳ����ִ�У����������������еĺ�������Ҳ����ִ����(����postHandle�ȷ���)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		System.out.println("ִ����preHandle����....");
		
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("users");//��session�л�ȡusers����
		
		String uri = request.getRequestURI();
		String contextpath = request.getContextPath();
		String requestname = uri.substring(contextpath.length()+1);//��ȡ��ǰ���������
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
	 * ��Ŀ�귽��ִ��֮��Ĳ���
	 * �÷���ִ����һ��ǰ�ᣬ����preHandle�������ص���true;
	 * 
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("ִ����postHandle����");
	}
	
	
	/**
	 * ������ɵ�ʱ����ִ�еķ���;
	 * ִ�е�ǰ�ᣬҲ��Ҫ��preHandle�������뷵��true
	 * һ�������ͷ���Դ
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("ִ����afterCompletion����");
	}
	
	
	
	
}
