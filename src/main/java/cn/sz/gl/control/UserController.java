package cn.sz.gl.control;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.sz.gl.pojo.Users;
import cn.sz.gl.service.IUserService;

@Controller
@RequestMapping("/uc")
public class UserController {

	@Autowired
	private IUserService userServiceImpl;
	
	@RequestMapping(value="login",method=RequestMethod.POST)
	public String islogin(Users user,HttpServletRequest request){
		Users u = userServiceImpl.islogin(user);
		if(u!=null){
			request.getSession().setAttribute("users", u);
			return "forward:/";
		}
		return "login";
	}
	
	@RequestMapping(value="prelogin",method=RequestMethod.GET)
	public String preLogin(){
		return "login";
	}
	
}
