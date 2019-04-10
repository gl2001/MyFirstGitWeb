package cn.sz.gl.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.sz.gl.pojo.Book;
import cn.sz.gl.service.IBookService;

@Controller
@RequestMapping("/")
public class FirstPageController {

	@Autowired
	private IBookService bookServiceImpl;
	
	@RequestMapping("/")
	public String firstpage(Model model){
		List<Book> booklist = bookServiceImpl.findAllBook();
		model.addAttribute("booklist", booklist);
		return "main";
	}
}
