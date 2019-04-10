<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<h3>这里是book_add.jsp页面</h3>
<form action="bc/addbook" method="post" enctype="multipart/form-data" >
	书籍名字：<input type="text" name="bookName" value="寻秦记" /><br/>
	出版社:<input type="text" name="publicDept" value="云南人民出版社" /><br/>
	价格：<input type="text" name="bookPrice" value="40" /><br/>
	作者：<input type="text" name="bookAuth" value="黄易" /><br/>
	出版日期:<input type="text" name="publicDate" value="2019-01-02 10:20:30" /><br/>
	封面照片:<input type="file" name="pic"  /><br/>
	简介：<textarea rows="5" cols="40" name="summary" >
		全书叙事为第三人称，共二十五卷。这本小说叙事结构宏大，人物塑造丰满而深具魅力；并且剧情跌宕起伏、一波三折，堪称是新武侠小说中的佳作
	</textarea><br/>
	<input type="submit" value="增加" />
</form>


<!-- 
	springmvc环境下，要想实现文件上传：
	1.页面上，通过浏览器的file组件，由用户来选择要上传的文件
		a.form表单，必须添加enctype属性：enctype="multipart/form-data"
			有文件要上传时，form表单的封装方式就与原来不一样了
		b.对于form表单中的file组件，必须添加name属性，而且这个name属性，最好不要跟实体类中的属性名一致
	2.要想实现文件上传，需要添加commons-fileupload组件：
		要添加的依赖，一般由两个：
			commons-fileupload
			commons-io
	3.需要在springmvc的配置文件中，准备文件上传的操作对象:
		该操作对象中，id只能叫multipartResolver
		<bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver" >
			<property name="defaultEncoding" value="utf-8" ></property>
			<property name="maxUploadSize" value="50000000" ></property>
		</bean>
	4.在控制器类中，接收页面传递的参数和文件
		控制器类中，可以跟以前普通表单一样，通过一个对象来接收form表单中文件以外的其他参数;
		对于form表单中的文件，通过  @RequestParam MultipartFile pic 方式，来接收文件
			其中，pic是form表单中文件组件的name属性值
		通过流的方式，把接收到的文件保存到硬盘上即可

 -->




</body>
</html>