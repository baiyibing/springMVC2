package com.test.controller;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.test.util.FastDFS;

@Controller
@RequestMapping(value="/file")
public class fileController {
	@RequestMapping(value="/hello", method=RequestMethod.GET)
	public @ResponseBody String Hello(){
		return "hello";
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String index(){
		return "fileUpload";
	}

	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public @ResponseBody String upload(@RequestParam("testFile")CommonsMultipartFile pic){
		System.out.println(pic.getName());
		System.out.println(pic.getSize());
		System.out.println(pic.getOriginalFilename());
		try {
			System.out.println(pic.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(pic.getBytes());
		return "success";
	}
	
	@RequestMapping(value="/upload2", method=RequestMethod.POST)
	public @ResponseBody String upload2(@RequestParam MultipartFile file, @RequestParam("filename")String filename){
		String[] temp = filename.split("\\.");
		try {
			FastDFS f = new FastDFS();
			InputStream inputStream = file.getInputStream();
			ByteArrayOutputStream swapStream = new ByteArrayOutputStream(); 
			byte[] buff = new byte[100]; //buff用于存放循环读取的临时数据 
			int rc = 0; 
			while ((rc = inputStream.read(buff, 0, 100))>0) { 
				swapStream.write(buff, 0, rc); 
			} 
			byte[] in_b = swapStream.toByteArray(); //in_b为转换之后的结果 
			String fileEnd = temp[temp.length-1];
			String[] result = f.upload(in_b, fileEnd);
			return "http://10.0.0.111/"+result[0]+"/"+result[1];
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "上传失败";
	}
}
