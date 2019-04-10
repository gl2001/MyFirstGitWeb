package cn.sz.gl.control;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import cn.sz.gl.pojo.Account;
import cn.sz.gl.pojo.Book;
import cn.sz.gl.pojo.Users;
import cn.sz.gl.service.IAccountService;
import cn.sz.gl.service.IBookService;

@Controller
@RequestMapping("/bc")
public class BookController {

	
	
	@Autowired
	private IBookService bookServiceImpl;
	@Autowired
	private IAccountService accService;
	
	/*@RequestMapping(value="addbook",method=RequestMethod.POST)
	public String addBook(Book book,@RequestParam MultipartFile pic,HttpServletRequest request){
		System.out.println("book信息：");
		System.out.println(book.getBookName()+","+book.getBookAuth());
		System.out.println("上传文件的名字："+pic.getOriginalFilename());
		
		//现在希望把文件上传到upload文件夹下
		//需要先获取upload文件夹的绝对路径
		String realpath = request.getSession().getServletContext().getRealPath("/upload");
		
		InputStream is = null;
		OutputStream os = null;
		try {
			is = pic.getInputStream();
			//这里可以使用原来的文件名，但如果出现同名的文件，后来上传的文件会覆盖前面的同名文件
			//File file = new File(realpath+"/"+pic.getOriginalFilename());
			
			//这里不建议使用原来的文件名，一般都由服务器来通过随机字符串或者时间戳来命名文件
			
			//这里随机产生一个字符串，来作为文件的名字
			String uuid = UUID.randomUUID().toString();
			String oldname = pic.getOriginalFilename();//原来的文件名
			String endname = oldname.substring(oldname.lastIndexOf("."));//获取原来的文件的后缀
			
			//准备File对象
			File file = new File(realpath+"/"+uuid+endname);
			
			os = new FileOutputStream(file);
			
			FileCopyUtils.copy(is, os);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		return "add_suc";
	}*/
	
	@RequestMapping(value="addbook",method=RequestMethod.POST)
	/*public String addBook(Book book,@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date mydate,HttpServletRequest request){*/
	public String addBook(Book book,HttpServletRequest request){
		System.out.println("book信息：");
		System.out.println(book.getBookName()+","+book.getBookAuth());
		
		//接收日期
		//System.out.println("mydate:"+mydate);
		System.out.println("日期："+book.getPublicDate());
		
		MultipartRequest req = (MultipartRequest) request;
		MultipartFile pic = req.getFile("pic");
		
		OutputStream os = null;
		try {
			String realpath = request.getSession().getServletContext().getRealPath("/upload");
			String oldname = pic.getOriginalFilename();
			String endname = oldname.substring(oldname.lastIndexOf("."));
			String uuid = UUID.randomUUID().toString();
			File file = new File(realpath + "/" + uuid + endname);
			os = new FileOutputStream(file);
			
			
			FileCopyUtils.copy(pic.getInputStream(), os);
			
			
			book.setImgPath(uuid + endname);
			//然后把book对象保存到数据库中
			request.setAttribute("pic", book.getImgPath());
			
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "add_suc";
	}
	
	
	@RequestMapping(value="downloadfile",method=RequestMethod.GET)
	public ResponseEntity<byte[]> downloadFile(String picpath,HttpServletRequest request){
		String realpath = request.getSession().getServletContext().getRealPath("/upload");
		File file = new File(realpath+"/"+picpath);
		
		HttpHeaders header = new HttpHeaders();
		//请求头，设置在浏览器里面打开下载对话框
		header.setContentDispositionFormData("attachment", picpath);
		
		try {
			return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), header, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	@RequestMapping(value="showbook",method=RequestMethod.GET)
	public String showBook(Integer bookid,Model model){
		System.out.println("bookid:"+bookid);
		Book book = bookServiceImpl.findById(bookid);
		model.addAttribute("book", book);
		return "book_info";
	}
	
	@RequestMapping(value="prebuy",method=RequestMethod.GET)
	public String prebuybook(Integer bookid,Model model,HttpServletRequest request){
		System.out.println("控制器中的prebuybook方法");
		Book book = bookServiceImpl.findById(bookid);
		model.addAttribute("book", book);
		
		//查询bookid的数量有多少
		int book_count = bookServiceImpl.findCountByBookid(bookid);
		model.addAttribute("book_count", book_count);
		
		//还要查询当前用户，有哪些账户
		Users u = (Users) request.getSession().getAttribute("users");
		List<Integer> acclist = accService.findAccidByUserid(u.getUserid());
		model.addAttribute("acclist", acclist);
		return "buy_book";
	}
	
	@RequestMapping(value="buybook",method=RequestMethod.POST)
	public String buybook(Integer bookid,Integer accid,HttpServletRequest request){
		System.out.println("buybook...");
		boolean buy_flag = bookServiceImpl.buybook(bookid, accid);
		String msg = "购买失败";
		if(buy_flag){
			msg = "购买成功";
		}
		request.setAttribute("msg", msg);
		return "forward";
	}
	
	/*@RequestMapping(value="showBalance",method=RequestMethod.POST)
	public void showBalance(Integer accid,HttpServletResponse response){
		//根据账号，查询账户信息
		Account acc = accService.findAccByAccid(accid);
		
		//把对象转换为json格式
		String accStr = JSON.toJSONString(acc);
		//查出来的acc对象，应该要返回到页面上去
		//因为这里是响应的ajax的请求，所以可以继续通过out对象来返回
		try {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.write(accStr);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}*/
	
	@ResponseBody
	@RequestMapping(value="showBalance",method=RequestMethod.POST)
	public Account showBalance(Integer accid){
		//根据账号，查询账户信息
		Account acc = accService.findAccByAccid(accid);
		return acc;
	}
	
	
	
	
	
}
