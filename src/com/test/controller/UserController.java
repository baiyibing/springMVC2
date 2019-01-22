package com.test.controller;
 
 
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
 
 
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.test.domain.User;
import com.test.util.FastDFS;
 
 
@Controller
@RequestMapping(value="/user")
public class UserController {
	
	private static List<User> userList;
	
	public UserController(){
		userList = new ArrayList<User>();
	}
	
	private static final Log logger =LogFactory.getLog(UserController.class);
	
	@RequestMapping(value="/registerForm",method=RequestMethod.GET)
	public String registerForm(){
		return "registerForm";
	}
 
 
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public String register(@RequestParam("loginname")String loginname, @RequestParam("password")String password, @RequestParam("username")String username, @RequestParam("headerImg")CommonsMultipartFile headerImg){
		
		//获取输出流
		try {
			FastDFS f = new FastDFS();
			InputStream inputStream = headerImg.getInputStream();
			ByteArrayOutputStream swapStream = new ByteArrayOutputStream(); 
			byte[] buff = new byte[100]; //buff用于存放循环读取的临时数据 
			int rc = 0; 
			while ((rc = inputStream.read(buff, 0, 100))>0) { 
				swapStream.write(buff, 0, rc); 
			} 
			byte[] in_b = swapStream.toByteArray(); //in_b为转换之后的结果 
			String[] uploadResult = f.upload(in_b, "jpg");
			for(int i=0; i<uploadResult.length; i++){
				System.out.println(uploadResult[i]);
			}
			User user = new User();
			user.setLoginname(loginname);
			user.setPassword(password);
			user.setUsername(username);
			user.setHeaderImg("http://10.0.0.111/"+uploadResult[0]+"/"+uploadResult[1]);//fastDFS
			System.out.println(user.toString());
			userList.add(user);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "loginForm";
	}
	
	@RequestMapping(value="/login")
	public String login(
			@RequestParam("loginname")String loginname,
			@RequestParam("password")String password,
			Model model){
		logger.info("loginname:"+loginname+" password:"+password);
		
		for(User user : userList){
			System.out.println(user.toString());
			System.out.println(user.getLoginname().equals(loginname));
			System.out.println(user.getPassword().equals(password));
			if(user.getLoginname().equals(loginname) && user.getPassword().equals(password)){
				model.addAttribute("user", user);
				System.out.println("��½�ɹ�");
				return "welcome";
			}
		}
		return "loginForm";
	}
}