package com.jsorg.controller;
 
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
 
import org.apache.commons.lang.xwork.RandomStringUtils;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.jsorg.pojo.UploadedImageFile;

import cn.hutool.core.lang.Console;

 
@Controller
public class ImageController {
	@ResponseBody
    @RequestMapping("uploadImage")
    public Object upload(@RequestBody Map map,HttpServletRequest request)
            throws IllegalStateException, IOException {
		Console.log(map.get("image"));
    	File file_f = (File) map.get("image");
    	FileInputStream in_file = new FileInputStream(file_f);
    	MultipartFile file = new MockMultipartFile("image", in_file);
    	UploadedImageFile imageFile = new UploadedImageFile();
    	imageFile.setImage(file);
    	
        String name = RandomStringUtils.randomAlphanumeric(10);
        String newFileName = name + ".jpg";
        File newFile = new File(request.getServletContext().getRealPath("/image"), newFileName);
        newFile.getParentFile().mkdirs();
        imageFile.getImage().transferTo(newFile);
 
        
        return "success";
    }
}