package com.jsorg.controller;
 
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.xwork.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.jsorg.pojo.UploadedImageFile;
import com.jsorg.pojo.User;
import com.jsorg.service.UserService;
import com.jsorg.util.FileUtil;
import com.jsorg.util.RedisUtil;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Console;

 
@Controller
@RequestMapping("image")
public class ImageController {
	@Autowired
	FileUtil fileUtil;
	@Autowired
	RedisUtil redisUtil;
	@Autowired
	UserService userService;
	
	@ResponseBody
    @RequestMapping("uploadImage")
    public Object upload(HttpServletRequest request)
            throws IllegalStateException, IOException {
//		Console.log(map.get("image"));
//    	File file_f = (File) map.get("image");
//    	FileInputStream in_file = new FileInputStream(file_f);
//    	MultipartFile file = new MockMultipartFile("image", in_file);
//    	UploadedImageFile imageFile = new UploadedImageFile();
//    	imageFile.setImage(file);
//    	
//        String name = RandomStringUtils.randomAlphanumeric(10);
//        String newFileName = name + ".jpg";
//        File newFile = new File(request.getServletContext().getRealPath("/image"), newFileName);
//        newFile.getParentFile().mkdirs();
//        imageFile.getImage().transferTo(newFile);
		System.out.println("开始上传文件");
		Console.log(request);
        List<MultipartFile> fileList = ((MultipartHttpServletRequest) request).getFiles("file");
        if (fileList.size() > 0) {

            for (MultipartFile file : fileList) {
                fileUtil.saveFile(file,"sss");
            }
        }
        return "success";
    }
	@ResponseBody
	@RequestMapping("changeavatar")
	public Object changeavatar(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("status", "success");
		User user = (User) redisUtil.get("user");
		
		String student_id = user.getStudent_id();
		
		System.out.println("开始更换头像");
		List<MultipartFile> fileList = ((MultipartHttpServletRequest) request).getFiles("file");
		if (fileList.size() > 0) {
            MultipartFile file = fileList.get(0);
            String filename = DateUtil.now().replaceAll(" ", "").replaceAll("-", "").replaceAll(":", "") 
            		+ file.getOriginalFilename();
            fileUtil.saveFile(file,filename);
            userService.updateavatarimage(student_id, "static/image/"+filename);
        }
		
		JSONObject jsonObject = (JSONObject) JSONObject.toJSON(resultMap);
		return jsonObject;
	}
	
}