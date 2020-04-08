package com.ly.controller;


import com.ly.service.FileService;
import com.ly.vo.EasyUIImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


/**
 * 如果用户使用文件上传,重定向到上传页面
 * @author tarena
 *
 */
@Controller
public class FileController {
	
	@Autowired
	private FileService fileService;
	/**
	 * SpringMVC负责流操作API
	 * 1.准备文件路径
	 * 2.准备文件名称后后缀 abc.jpg
	 * 3.利用IO流进行写盘操作.
	 * @param fileImage
	 * @return
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	@RequestMapping("/file")
	public String file(MultipartFile fileImage) throws IllegalStateException, IOException {
		File dirFile = new File("D:/jt-image");
		if(!dirFile.exists()) {
			//如果文件不存在需要新建文件夹/目录
			//dirFile.mkdir(); //只创建一级目录
			dirFile.mkdirs();//创建多级目录
		}
		//动态获取文件名称
		String fileName = fileImage.getOriginalFilename();
		File file = new File("D:/jt-image/"+fileName);
		//文件上传
		fileImage.transferTo(file);
		//转发 重定向不经过视图解析器
		return "redirect:/easy-ui/easyui-14-upload.html";
	}

	@RequestMapping("/pic/upload")
	@ResponseBody
	public EasyUIImage uploadFile(MultipartFile uploadFile) {
		return fileService.uploadFile(uploadFile);
	}
}
