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
		System.out.println("book��Ϣ��");
		System.out.println(book.getBookName()+","+book.getBookAuth());
		System.out.println("�ϴ��ļ������֣�"+pic.getOriginalFilename());
		
		//����ϣ�����ļ��ϴ���upload�ļ�����
		//��Ҫ�Ȼ�ȡupload�ļ��еľ���·��
		String realpath = request.getSession().getServletContext().getRealPath("/upload");
		
		InputStream is = null;
		OutputStream os = null;
		try {
			is = pic.getInputStream();
			//�������ʹ��ԭ�����ļ��������������ͬ�����ļ��������ϴ����ļ��Ḳ��ǰ���ͬ���ļ�
			//File file = new File(realpath+"/"+pic.getOriginalFilename());
			
			//���ﲻ����ʹ��ԭ�����ļ�����һ�㶼�ɷ�������ͨ������ַ�������ʱ����������ļ�
			
			//�����������һ���ַ���������Ϊ�ļ�������
			String uuid = UUID.randomUUID().toString();
			String oldname = pic.getOriginalFilename();//ԭ�����ļ���
			String endname = oldname.substring(oldname.lastIndexOf("."));//��ȡԭ�����ļ��ĺ�׺
			
			//׼��File����
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
		System.out.println("book��Ϣ��");
		System.out.println(book.getBookName()+","+book.getBookAuth());
		
		//��������
		//System.out.println("mydate:"+mydate);
		System.out.println("���ڣ�"+book.getPublicDate());
		
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
			//Ȼ���book���󱣴浽���ݿ���
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
		//����ͷ���������������������ضԻ���
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
		System.out.println("�������е�prebuybook����");
		Book book = bookServiceImpl.findById(bookid);
		model.addAttribute("book", book);
		
		//��ѯbookid�������ж���
		int book_count = bookServiceImpl.findCountByBookid(bookid);
		model.addAttribute("book_count", book_count);
		
		//��Ҫ��ѯ��ǰ�û�������Щ�˻�
		Users u = (Users) request.getSession().getAttribute("users");
		List<Integer> acclist = accService.findAccidByUserid(u.getUserid());
		model.addAttribute("acclist", acclist);
		return "buy_book";
	}
	
	@RequestMapping(value="buybook",method=RequestMethod.POST)
	public String buybook(Integer bookid,Integer accid,HttpServletRequest request){
		System.out.println("buybook...");
		boolean buy_flag = bookServiceImpl.buybook(bookid, accid);
		String msg = "����ʧ��";
		if(buy_flag){
			msg = "����ɹ�";
		}
		request.setAttribute("msg", msg);
		return "forward";
	}
	
	/*@RequestMapping(value="showBalance",method=RequestMethod.POST)
	public void showBalance(Integer accid,HttpServletResponse response){
		//�����˺ţ���ѯ�˻���Ϣ
		Account acc = accService.findAccByAccid(accid);
		
		//�Ѷ���ת��Ϊjson��ʽ
		String accStr = JSON.toJSONString(acc);
		//�������acc����Ӧ��Ҫ���ص�ҳ����ȥ
		//��Ϊ��������Ӧ��ajax���������Կ��Լ���ͨ��out����������
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
		//�����˺ţ���ѯ�˻���Ϣ
		Account acc = accService.findAccByAccid(accid);
		return acc;
	}
	
	
	
	
	
}
